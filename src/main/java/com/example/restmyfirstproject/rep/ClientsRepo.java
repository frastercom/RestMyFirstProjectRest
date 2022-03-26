package com.example.restmyfirstproject.rep;

import com.example.restmyfirstproject.domain.Clients;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ClientsRepo extends CrudRepository<Clients, Long> {

    List<Clients> findByName(String name);

}
