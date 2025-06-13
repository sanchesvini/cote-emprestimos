package br.com.cote.coteemprestimos.services;

import br.com.cote.coteemprestimos.entities.Cliente;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ClienteService {

    void cadastrar(Cliente cliente);

    List<Cliente> listar();

    void atualizar(Cliente cliente);

    Cliente buscarPeloId(long id);

    void deletar(Long id);
}
