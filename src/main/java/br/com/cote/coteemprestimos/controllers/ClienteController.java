package br.com.cote.coteemprestimos.controllers;

import br.com.cote.coteemprestimos.entities.Cliente;
import br.com.cote.coteemprestimos.entities.Emprestimo;
import br.com.cote.coteemprestimos.services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @PostMapping("/cadastrar")
    public ResponseEntity<?> cadastrarCliente(@RequestBody Cliente cliente){
        clienteService.cadastrar(cliente);
        return ResponseEntity.ok("Cliente cadastrado com sucesso!");
    }
    @GetMapping("/listar")
    public ResponseEntity<?> listarClientes(){
        return ResponseEntity.ok(clienteService.listar());
    }
    @GetMapping("/buscar/{id}")
    public ResponseEntity<?> buscarPeloId(@PathVariable Long id){
        return ResponseEntity.ok(clienteService.buscarPeloId(id));
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<?> atualizar(@RequestBody Cliente cliente, @PathVariable Long id){
        cliente.setId(id);
        clienteService.atualizar(cliente);
        return ResponseEntity.ok(cliente);
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<?> deletarEmprestimo(@PathVariable Long id){
        Cliente cliente = clienteService.buscarPeloId(id);
        clienteService.deletar(id);
        return ResponseEntity.ok(cliente);
    }
}
