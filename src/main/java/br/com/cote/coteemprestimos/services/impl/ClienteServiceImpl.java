package br.com.cote.coteemprestimos.services.impl;

import br.com.cote.coteemprestimos.entities.Cliente;
import br.com.cote.coteemprestimos.repositories.ClienteRepository;
import br.com.cote.coteemprestimos.services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteServiceImpl implements ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Override
    public void cadastrar(Cliente cliente) {
        clienteRepository.save(cliente);
    }

    @Override
    public List<Cliente> listar() {
        return clienteRepository.findAll();
    }

    @Override
    public void atualizar(Cliente cliente) {
        clienteRepository.save(cliente);
    }

    @Override
    public Cliente buscarPeloId(long id) {
        return clienteRepository.findById(id).orElse(null);
    }

    @Override
    public void deletar(Long id) {
        clienteRepository.deleteById(id);
    }
}
