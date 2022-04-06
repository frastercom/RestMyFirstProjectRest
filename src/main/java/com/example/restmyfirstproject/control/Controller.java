package com.example.restmyfirstproject.control;

import com.example.restmyfirstproject.domain.BankAccaunt;
import com.example.restmyfirstproject.domain.Clients;
import com.example.restmyfirstproject.domain.Contacts;
import com.example.restmyfirstproject.domain.Message;
import com.example.restmyfirstproject.rep.ClientsRepo;
import com.example.restmyfirstproject.sevirces.ClientsDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
public class Controller {

    private static final Logger LOGGER = LoggerFactory.getLogger(Controller.class);

    @Autowired
    private ClientsDao clientsDao;

//    @Autowired
//    private ClientsRepo clientsRepo;
//работа с таблицой клиент
    @GetMapping("/clients")
    public List<Clients> getAllClients() {
        LOGGER.info("Поиск всех клиентов");
        return clientsDao.getAll();
    }

    @GetMapping("/clients/{clientsId}")
    public Clients getClientsById(@PathVariable("clientsId") long id) {
        LOGGER.info("Поиск всех клиентов по id");
        return clientsDao.getById(id);
    }

    @GetMapping("/clients/filter")
    @ResponseStatus(HttpStatus.CREATED)
    public List<Clients> filterClients(@RequestParam("name") String name, @RequestParam("money") int money) {
        LOGGER.info("Поиск всех клиентов по фильтрам name и money");
        return clientsDao.getFindByTag(name, money);
    }

    @GetMapping("/clients/filter/name")
    @ResponseStatus(HttpStatus.CREATED)
    public List<Clients> filterClients(@RequestParam("name") String name) {
        LOGGER.info("Поиск всех клиентов по name");
        return clientsDao.getFindByTag(name);
    }

    @GetMapping("/clients/filter/money")
    @ResponseStatus(HttpStatus.CREATED)
    public List<Clients> filterClients(@RequestParam("money") int money) {
        LOGGER.info("Поиск всех клиентов по money");
        return clientsDao.getFindByTag(money);
    }

    @PostMapping("/clients")
    @ResponseStatus(HttpStatus.CREATED)
    public Clients createClients(@RequestBody Clients clients) {
        LOGGER.info("Создание клиента");
        return clientsDao.create(clients);
    }

    @PutMapping("/clients/{clientsId}")
    public Clients updateClients(@PathVariable("clientsId") long id, @RequestBody Clients clients) {
        LOGGER.info("Обновление клиента");
        return clientsDao.update(id, clients);
    }

    @DeleteMapping("/clients/{clientsId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteClients(@PathVariable("clientsId") Long id) {
        LOGGER.info("Удаление клиента");
        clientsDao.delete(id);
    }

    //работа с таблицой bank
    @GetMapping("/clients/banks")
    public List<BankAccaunt> getAllBanks() {
        LOGGER.info("Все счета");
        return clientsDao.getAllBankAccaunt();
    }

    @GetMapping("/clients/banks/{banksId}")
    public BankAccaunt getBanksById(@PathVariable("banksId") long id) {
        LOGGER.info("Счет по id");
        return clientsDao.getByIdBank(id);
    }

    @GetMapping("/clients/{clientsId}/banks")
    public List<BankAccaunt> getBanksByClient(@PathVariable("clientsId") long id) {
        LOGGER.info("Счета, которые принадлежат клиенту");
        if (clientsDao.getById(id)!=null)
            return clientsDao.getById(id).getBankAccaunt();
        else
            return null;
    }

    @PostMapping("/clients/banks")
    @ResponseStatus(HttpStatus.CREATED)
    public BankAccaunt createBankAccaunt(@RequestBody BankAccaunt bankAccaunt) {
        LOGGER.info("Создание счета");
        return clientsDao.create(bankAccaunt);
    }

    @PutMapping("/clients/banks/{banksId}")
    public BankAccaunt updateBankAccaunt(@PathVariable("banksId") long id, @RequestBody BankAccaunt bankAccaunt) {
        LOGGER.info("Изменение счета");
        return clientsDao.update(id, bankAccaunt);
    }

    @DeleteMapping("/clients/banks/{banksId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBankAccaunt(@PathVariable("banksId") Long id) {
        LOGGER.info("Удаление счета");
        clientsDao.deleteBankAccaunt(id);
    }

    //contacts
    @GetMapping("/clients/contacts")
    public List<Contacts> getAllContacts() {
        LOGGER.info("Все контакты");
        return clientsDao.getAllContacts();
    }

    @GetMapping("/clients/contacts/{contactsId}")
    public Contacts getContactsById(@PathVariable("contactsId") long id) {
        LOGGER.info("Контакт по id");
        return clientsDao.getByIdContacts(id);
    }

    @GetMapping("/clients/{contactsId}/contacts")
    public List<Contacts> getContactsByClient(@PathVariable("contactsId") long id) {
        LOGGER.info("Контакты, которые принадлежат клиенту");
        if (clientsDao.getById(id)!=null)
            return clientsDao.getById(id).getContacts();
        else
            return null;
    }

    @PostMapping("/clients/contacts")
    @ResponseStatus(HttpStatus.CREATED)
    public Contacts createContacts(@RequestBody Contacts contacts) {
        LOGGER.info("Создание контакта");
        return clientsDao.create(contacts);
    }

    @PutMapping("/clients/contacts/{contactsId}")
    public Contacts updateContacts(@PathVariable("contactsId") long id, @RequestBody Contacts contacts) {
        LOGGER.info("Измение контакта");
        return clientsDao.update(id, contacts);
    }

    @DeleteMapping("/clients/contacts/{contactsId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteContacts(@PathVariable("contactsId") Long id) {
        LOGGER.info("Удаление контакта");
        clientsDao.deleteContacts(id);
    }

    @GetMapping("/message")
    public Message filterMessage(@RequestParam(value = "in_user", required = false) String in_name, @RequestParam(value = "out_user", required = false) String out_name) {
        if (in_name != null && out_name != null) {
            LOGGER.info("Поиск по имени");
            return clientsDao.getMessage(in_name, out_name);
        }
        else {
            LOGGER.info("Все сообщения");
            return clientsDao.getAllMessage();
        }
    }

    @PostMapping("/message")
    @ResponseStatus(HttpStatus.CREATED)
    public Message createClients(@RequestBody Message message) {
        LOGGER.info("Создание сообщения");
        //message.setDate((Timestamp) new Date());
        return clientsDao.createMessage(message);
    }
}
