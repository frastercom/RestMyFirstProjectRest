package com.example.restmyfirstproject.sevirces;

import com.example.restmyfirstproject.domain.Clients;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ClientsDaoTest {

    @Autowired
    private ClientsDao clientsDao;

    @Test
    void create() {
        Clients clients = new Clients();
        clients.setName("testmoc");
        clientsDao.create(clients);
    }
}