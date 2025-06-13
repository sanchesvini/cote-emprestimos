package br.com.cote.coteemprestimos.services;

import br.com.cote.coteemprestimos.entities.Emprestimo;
import br.com.cote.coteemprestimos.entities.Simulacao;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface EmprestimoService {
    void cadastrar(Emprestimo emprestimo);

    List<Emprestimo> listar();

    void atualizar(Emprestimo emprestimo);

    Emprestimo buscarPeloId(long id);

    Simulacao simular(String moeda, Double valor, String dataEmprestimo, String dataVencimento);

    void deletar(Long id);
}
