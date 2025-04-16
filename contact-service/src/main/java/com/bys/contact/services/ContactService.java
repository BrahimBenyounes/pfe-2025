package com.bys.contact.services;


import com.bys.contact.entities.Contact;

import java.util.List;

public interface ContactService {

    List<Contact> retrieveAllContacts();

    Contact addContact(Contact c);

    void deleteContact(Long id);

    Contact updateContact(Contact c);

    Contact retrieveContact(Long id);

}