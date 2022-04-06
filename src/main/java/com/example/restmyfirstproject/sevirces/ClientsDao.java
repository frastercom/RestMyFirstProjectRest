package com.example.restmyfirstproject.sevirces;

import com.example.restmyfirstproject.domain.BankAccaunt;
import com.example.restmyfirstproject.domain.Clients;
import com.example.restmyfirstproject.domain.Contacts;
import com.example.restmyfirstproject.domain.Message;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class ClientsDao {
    @PersistenceContext
    private EntityManager entityManager;
//clients table
    public List<Clients> getAll() {
        List<Clients> resultList = entityManager.createQuery("from Clients c order by c.id desc", Clients.class).getResultList();
        return resultList;
    }

    public Clients getById(Long id) {
        return entityManager.find(Clients.class, id);
    }

    public List<Clients> getFindByTag(String name, int money) {
        List<Clients> resultList = entityManager.createQuery("select c from Clients c JOIN c.bankAccaunt ba WHERE c.name = :name and  ba.money=:money", Clients.class).setParameter("name", name).setParameter("money", money).getResultList();
        return resultList;
    }

    public List<Clients> getFindByTag(String name) {
        List<Clients> resultList = entityManager.createQuery("select c from Clients c WHERE c.name = :name", Clients.class).setParameter("name", name).getResultList();
        return resultList;
    }

    public List<Clients> getFindByTag(int money) {
        List<Clients> resultList = entityManager.createQuery("select c from Clients c JOIN c.bankAccaunt ba WHERE ba.money=:money", Clients.class).setParameter("money", money).getResultList();
        return resultList;
    }

    public Clients create(Clients clients) {
        if (clients.getBankAccaunt()!=null)
        for (BankAccaunt bankAccaunt : clients.getBankAccaunt()) {
            bankAccaunt.setClient(clients);
        }
        if (clients.getContacts()!=null)
        for (Contacts contacts : clients.getContacts()) {
            contacts.setClients(clients);
        }
        entityManager.persist(clients);
        return clients;
    }

    public Clients update(long id, Clients clients) {
        Clients original = entityManager.find(Clients.class, id);
        if (original != null) {
            original.setName(clients.getName());
//            if (original.getBankAccaunt() != null) {
//                for (BankAccaunt bankAccaunt : original.getBankAccaunt()) {
//                    entityManager.remove(bankAccaunt);
//                }
//                original.getBankAccaunt().clear();
//                for (BankAccaunt bankAccaunt : clients.getBankAccaunt()) {
//                    bankAccaunt.setClient(original);
//                    original.getBankAccaunt().add(bankAccaunt);
//                    entityManager.persist(bankAccaunt);
//                }
//            }
//            if (original.getContacts() != null) {
//                for (Contacts contacts : original.getContacts()) {
//                    entityManager.remove(contacts);
//                }
//                original.getContacts().clear();
//                for (Contacts contacts : original.getContacts()) {
//                    contacts.setClients(original);
//                    original.getContacts().add(contacts);
//                    entityManager.persist(contacts);
//                }
//            }
            entityManager.merge(original);
        }
        return original;
    }

    public void delete(Long id) {
        Clients clients = entityManager.find(Clients.class, id);
        if (clients != null) {
            entityManager.remove(clients);
        }
    }

//bank table
    public List<BankAccaunt> getAllBankAccaunt() {
        List<BankAccaunt> resultList = entityManager.createQuery("from BankAccaunt c order by c.id desc", BankAccaunt.class).getResultList();
        return resultList;
    }

    public BankAccaunt getByIdBank(long id) {
        return entityManager.find(BankAccaunt.class, id);
    }

    public BankAccaunt create(BankAccaunt bankAccaunt) {
        entityManager.persist(bankAccaunt);
        return bankAccaunt;
    }

    public BankAccaunt update(long id, BankAccaunt bankAccaunt) {
        BankAccaunt original = entityManager.find(BankAccaunt.class, id);
        if (original != null) {
            original.setMoney(bankAccaunt.getMoney());
            entityManager.merge(original);
        }
        return original;
    }

    public void deleteBankAccaunt(Long id) {
        BankAccaunt bankAccaunt = entityManager.find(BankAccaunt.class, id);
        if (bankAccaunt != null) {
            entityManager.remove(bankAccaunt);
        }
    }

    //contacts table
    public List<Contacts> getAllContacts() {
        List<Contacts> resultList = entityManager.createQuery("from Contacts c order by c.id desc", Contacts.class).getResultList();
        return resultList;
    }

    public Contacts getByIdContacts(long id) {
        return entityManager.find(Contacts.class, id);
    }

    public Contacts create(Contacts contacts) {
        entityManager.persist(contacts);
        return contacts;
    }

    public Contacts update(long id, Contacts contacts) {
        Contacts original = entityManager.find(Contacts.class, id);
        if (original != null) {
            original.setNumberPhone(contacts.getNumberPhone());
            entityManager.merge(original);
        }
        return original;
    }

    public void deleteContacts(Long id) {
        Contacts contacts = entityManager.find(Contacts.class, id);
        if (contacts != null) {
            entityManager.remove(contacts);
        }
    }

    public Message getAllMessage() {
        List<Message> resultList = entityManager.createQuery("from Message c order by c.id desc", Message.class).getResultList();
        int i = resultList.size();
        if (i>0)
            return resultList.get(i-1);
        else return null;
    }

    public Message getMessage(String in_name, String out_name) {
        List<Message> resultList = entityManager.createQuery("select c from Message c WHERE c.in_user=:in and c.out_user=:out", Message.class).setParameter("in", in_name).setParameter("out", out_name).getResultList();
        int i = resultList.size();
        if (i>0)
        return resultList.get(i-1);
        else return null;
    }

    public Message createMessage(Message message) {
        entityManager.persist(message);
        return message;
    }
}
