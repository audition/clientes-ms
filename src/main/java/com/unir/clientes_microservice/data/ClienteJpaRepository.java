package com.unir.clientes_microservice.data;

import com.unir.products.model.pojo.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface ClienteJpaRepository  extends JpaRepository<Cliente, Long>, JpaSpecificationExecutor<Cliente> {

}
