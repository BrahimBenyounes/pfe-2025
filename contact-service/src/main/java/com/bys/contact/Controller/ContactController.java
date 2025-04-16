package com.bys.contact.Controller;


import com.bys.contact.entities.Contact;
import com.bys.contact.services.ContactService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

@RequestMapping("pfe/api/contact")
@CrossOrigin(origins = "http://localhost:4200")
@Slf4j
public class ContactController {


    @Autowired
    ContactService contactService;

    @GetMapping("/retrieve-all-contacts")
    @ResponseBody
    public List<Contact> getContacts() {
        List<Contact> list = contactService.retrieveAllContacts();
        return list;
    }

    @GetMapping("/retrieve-contact/{contact-id}")
    @ResponseBody
    public Contact retrieveContact(@PathVariable("contact-id") Long contactId) {
        return contactService.retrieveContact(contactId);
    }

    @PostMapping("/add-contact")

    @ResponseBody
    public Contact addContact(@RequestBody Contact c) {
        Contact contact = contactService.addContact(c);
        return contact;
    }



}
