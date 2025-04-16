package com.bys.contact.services;


import com.bys.contact.entities.Contact;
import com.bys.contact.repositories.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class ContactServiceImpl implements ContactService  {




    @Autowired
    ContactRepository contactRepository;
    @Override
    public List<Contact> retrieveAllContacts() {
        return (List<Contact>) contactRepository.findAll();
    }

    @Override
    public Contact addContact(Contact c) {
        contactRepository.save(c);
        return c;
    }

    @Override
    public void deleteContact(Long id) {
        contactRepository.deleteById(id);

    }

    @Override
    public Contact updateContact(Contact c) {
        contactRepository.save(c);
        return c;
    }

    @Override
    public Contact retrieveContact(Long id) {
        Contact contact = contactRepository.findById(id).orElse(null);
        return contact;
    }

}
