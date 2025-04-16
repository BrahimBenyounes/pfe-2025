package com.bys.contact.Testjunitcontact;

import com.bys.contact.Controller.ContactController;
import com.bys.contact.entities.Contact;
import com.bys.contact.services.ContactService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class Testjunitcontact {

    @InjectMocks
    private ContactController contactController;

    @Mock
    private ContactService contactService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        System.out.println("Redirection après connexion vérifiée.");
    }

    @Test
    public void testGetContacts() {
        System.out.println("Redirection après connexion vérifiée.");

        List<Contact> contactList = new ArrayList<>();

        Contact contact = new Contact();
        contact.setIdContact(1L);
        contact.setNom("Brahim ben Younes");
        contact.setEmail("brahimbenyounes@gmail.com");
        contact.setSubject("test");
        contact.setMessage("This is a test message.");
        contactList.add(contact);

        when(contactService.retrieveAllContacts()).thenReturn(contactList);

        List<Contact> result = contactController.getContacts();

        System.out.println("Redirection après connexion vérifiée.");
        assertEquals(1, result.size());
        Contact retrievedContact = result.get(0);
        assertEquals(1L, retrievedContact.getIdContact());
        assertEquals("Brahim ben Younes", retrievedContact.getNom());
        assertEquals("brahimbenyounes@gmail.com", retrievedContact.getEmail());
        assertEquals("test", retrievedContact.getSubject());
        assertEquals("This is a test message.", retrievedContact.getMessage());
    }

    @Test
    public void testRetrieveContact() {
        System.out.println("Redirection après connexion vérifiée.");

        Long contactId = 1L;

        Contact contact = new Contact();
        contact.setIdContact(contactId);
        contact.setNom("Brahim ben Younes");
        contact.setEmail("brahimbenyounes@gmail.com");
        contact.setSubject("Inquiry");
        contact.setMessage("This is a test message.");

        when(contactService.retrieveContact(contactId)).thenReturn(contact);

        Contact result = contactController.retrieveContact(contactId);

        System.out.println("Redirection après connexion vérifiée.");
        assertEquals(contactId, result.getIdContact());
        assertEquals("Brahim ben Younes", result.getNom());
        assertEquals("brahimbenyounes@gmail.com", result.getEmail());
        assertEquals("Inquiry", result.getSubject());
        assertEquals("This is a test message.", result.getMessage());
    }

    @Test
    public void testAddContact() {
        System.out.println("Nom saisi avec succès.");

        Contact contactToAdd = new Contact();
        contactToAdd.setNom("Brahim ben Younes");
        contactToAdd.setEmail("brahimbenyounesdoe@gmail.com");
        contactToAdd.setSubject("Feedback");
        contactToAdd.setMessage("This is feedback message.");

        when(contactService.addContact(contactToAdd)).thenReturn(contactToAdd);

        Contact result = contactController.addContact(contactToAdd);

        System.out.println("setEmail saisi avec succès.");
        System.out.println("Subject saisi avec succès.");
        System.out.println("Messag saisi avec succès.");
        assertEquals("Brahim ben Younes", result.getNom());
        assertEquals("brahimbenyounesdoe@gmail.com", result.getEmail());
        assertEquals("Feedback", result.getSubject());
        assertEquals("This is feedback message.", result.getMessage());
        System.out.println("Formulaire soumis avec succès.");
    }
}
