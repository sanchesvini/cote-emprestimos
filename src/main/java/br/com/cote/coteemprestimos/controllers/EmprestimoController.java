package br.com.cote.coteemprestimos.controllers;

import br.com.cote.coteemprestimos.entities.Emprestimo;
import br.com.cote.coteemprestimos.entities.Simulacao;
import br.com.cote.coteemprestimos.services.EmprestimoService;
import br.com.cote.coteemprestimos.utils.DateUtils;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

@RestController
@RequestMapping("/api/emprestimos")
public class EmprestimoController {

    private final RestTemplate restTemplate = new RestTemplate();

    @Autowired
    private EmprestimoService emprestimoService;

    @GetMapping("/simular")
    public ResponseEntity<?> simularEmprestimo(@RequestParam String moeda, @RequestParam Double valor, @RequestParam String dataEmprestimo, @RequestParam String dataVencimento) throws IOException {

        Simulacao simulacao = emprestimoService.simular(moeda, valor, dataEmprestimo, dataVencimento);

        return ResponseEntity.ok(simulacao);
    }

    @PostMapping("/cadastrar")
    public ResponseEntity<?> cadastrarEmprestimo(@RequestBody Emprestimo emprestimo){
        emprestimoService.cadastrar(emprestimo);
        return ResponseEntity.ok(emprestimo);
    }

    @GetMapping("/listar")
    public ResponseEntity<?> listarEmprestimos(){
        return ResponseEntity.ok(emprestimoService.listar());
    }
    @GetMapping("/buscar/{id}")
    public ResponseEntity<?> buscarPeloId(@PathVariable Long id){
        return ResponseEntity.ok(emprestimoService.buscarPeloId(id));
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<?> atualizarEmprestimo(@RequestBody Emprestimo emprestimo, @PathVariable Long id){
        emprestimo.setId(id);
        emprestimoService.atualizar(emprestimo);
        return ResponseEntity.ok(emprestimo);
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<?> deletarEmprestimo(@PathVariable Long id){
        Emprestimo emprestimo = emprestimoService.buscarPeloId(id);
        emprestimoService.deletar(id);
        return ResponseEntity.ok(emprestimo);
    }
}

