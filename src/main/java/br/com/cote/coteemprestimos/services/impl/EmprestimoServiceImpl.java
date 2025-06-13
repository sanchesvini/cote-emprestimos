package br.com.cote.coteemprestimos.services.impl;

import br.com.cote.coteemprestimos.entities.Emprestimo;
import br.com.cote.coteemprestimos.entities.Simulacao;
import br.com.cote.coteemprestimos.repositories.EmprestimoRepository;
import br.com.cote.coteemprestimos.services.EmprestimoService;
import br.com.cote.coteemprestimos.utils.DateUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class EmprestimoServiceImpl implements EmprestimoService {
    private final RestTemplate restTemplate = new RestTemplate();

    @Autowired
    private EmprestimoRepository emprestimoRepository;

    @Override
    public void cadastrar(Emprestimo emprestimo) {
        emprestimoRepository.save(emprestimo);
    }

    @Override
    public List<Emprestimo> listar() {
        return emprestimoRepository.findAll();
    }

    @Override
    public void atualizar(Emprestimo emprestimo) {
        emprestimoRepository.save(emprestimo);
    }

    @Override
    public Emprestimo buscarPeloId(long id) {
        return emprestimoRepository.findById(id).orElse(null);
    }

    @Override
    public Simulacao simular(String moeda, Double valor, String dataEmprestimo, String dataVencimento) {
        LocalDate dataVencimentoLD = LocalDate.parse(dataVencimento);
        LocalDate dataAtual = LocalDate.now();
        LocalDate dataCotacao = DateUtils.getLastBusinessDay(dataAtual);
        double taxaConversao = 0.0;
        boolean semRetornoCotacaoDoDia = true;
        while(semRetornoCotacaoDoDia){

            String dataCotacaoFormatada = dataCotacao.format(DateTimeFormatter.ofPattern("MM-dd-yyyy"));
            System.out.println(dataCotacaoFormatada);
            String url = "https://olinda.bcb.gov.br/olinda/servico/PTAX/versao/v1/odata/CotacaoMoedaDia(moeda='" + moeda + "',dataCotacao='" + dataCotacaoFormatada + "')?$format=json";
            String response = restTemplate.getForObject(url, String.class);

            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNode = null;
            try {
                rootNode = objectMapper.readTree(response);
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
            JsonNode valueNode = rootNode.path("value");
            if(valueNode.size() > 0){
                semRetornoCotacaoDoDia = false;
                taxaConversao = valueNode.get(0).path("cotacaoVenda").asDouble();
            }else{
                 dataCotacao = dataCotacao.minusDays(1);
            }
        }
        double taxaJuros = 0.02;
        long numeroMeses = ChronoUnit.MONTHS.between(dataAtual, dataVencimentoLD);
        double valorPagoVencimento = valor * taxaConversao * Math.pow(1 + taxaJuros, numeroMeses);

        Simulacao simulacao = new Simulacao(numeroMeses, valorPagoVencimento, taxaConversao);

        return simulacao;
    }

    @Override
    public void deletar(Long id) {
        emprestimoRepository.deleteById(id);
    }
}
