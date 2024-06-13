package com.unir.clientes_microservice.data;



import com.unir.clientes_microservice.model.pojo.Cliente;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ClienteRepository {

    private final ClienteJpaRepository repository;

    public List<Cliente> getClientes() {
        return repository.findAll();
    }

    public Cliente getById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public Cliente save(Cliente cliente) {
        return repository.save(cliente);
    }

    public void delete(Cliente cliente) {
        repository.delete(cliente);
    }

}
