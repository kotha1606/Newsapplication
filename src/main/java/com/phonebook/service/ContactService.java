package com.phonebook.service;

import com.phonebook.dto.ContactRequest;
import com.phonebook.dto.ContactResponse;
import com.phonebook.entity.Contact;
import com.phonebook.entity.User;
import com.phonebook.repository.ContactRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ContactService {

    private final ContactRepository contactRepository;
    private final UserService userService;

    public List<ContactResponse> getAllContacts(String username) {
        User user = userService.getCurrentUser(username);
        List<Contact> contacts = contactRepository.findByUserOrderByFirstNameAsc(user);
        return contacts.stream()
                .map(this::mapToContactResponse)
                .collect(Collectors.toList());
    }

    public Page<ContactResponse> getAllContactsPaginated(String username, Pageable pageable) {
        User user = userService.getCurrentUser(username);
        Page<Contact> contacts = contactRepository.findByUserOrderByFirstNameAsc(user, pageable);
        return contacts.map(this::mapToContactResponse);
    }

    public Page<ContactResponse> searchContacts(String username, String search, Pageable pageable) {
        User user = userService.getCurrentUser(username);
        Page<Contact> contacts = contactRepository.searchContacts(user, search, pageable);
        return contacts.map(this::mapToContactResponse);
    }

    public ContactResponse getContactById(String username, Long contactId) {
        User user = userService.getCurrentUser(username);
        Contact contact = contactRepository.findByIdAndUser(contactId, user)
                .orElseThrow(() -> new RuntimeException("Contact not found"));
        return mapToContactResponse(contact);
    }

    public ContactResponse createContact(String username, ContactRequest request) {
        User user = userService.getCurrentUser(username);
        
        if (contactRepository.existsByPhoneNumberAndUser(request.getPhoneNumber(), user)) {
            throw new RuntimeException("Contact with this phone number already exists");
        }

        Contact contact = new Contact();
        contact.setFirstName(request.getFirstName());
        contact.setLastName(request.getLastName());
        contact.setPhoneNumber(request.getPhoneNumber());
        contact.setEmail(request.getEmail());
        contact.setAddress(request.getAddress());
        contact.setCompany(request.getCompany());
        contact.setNotes(request.getNotes());
        contact.setUser(user);

        Contact savedContact = contactRepository.save(contact);
        return mapToContactResponse(savedContact);
    }

    public ContactResponse updateContact(String username, Long contactId, ContactRequest request) {
        User user = userService.getCurrentUser(username);
        Contact contact = contactRepository.findByIdAndUser(contactId, user)
                .orElseThrow(() -> new RuntimeException("Contact not found"));

        // Check if phone number is being changed and if it already exists
        if (!contact.getPhoneNumber().equals(request.getPhoneNumber()) &&
            contactRepository.existsByPhoneNumberAndUser(request.getPhoneNumber(), user)) {
            throw new RuntimeException("Contact with this phone number already exists");
        }

        contact.setFirstName(request.getFirstName());
        contact.setLastName(request.getLastName());
        contact.setPhoneNumber(request.getPhoneNumber());
        contact.setEmail(request.getEmail());
        contact.setAddress(request.getAddress());
        contact.setCompany(request.getCompany());
        contact.setNotes(request.getNotes());

        Contact updatedContact = contactRepository.save(contact);
        return mapToContactResponse(updatedContact);
    }

    public void deleteContact(String username, Long contactId) {
        User user = userService.getCurrentUser(username);
        Contact contact = contactRepository.findByIdAndUser(contactId, user)
                .orElseThrow(() -> new RuntimeException("Contact not found"));
        contactRepository.delete(contact);
    }

    public long getContactCount(String username) {
        User user = userService.getCurrentUser(username);
        return contactRepository.countByUser(user);
    }

    private ContactResponse mapToContactResponse(Contact contact) {
        ContactResponse response = new ContactResponse();
        response.setId(contact.getId());
        response.setFirstName(contact.getFirstName());
        response.setLastName(contact.getLastName());
        response.setPhoneNumber(contact.getPhoneNumber());
        response.setEmail(contact.getEmail());
        response.setAddress(contact.getAddress());
        response.setCompany(contact.getCompany());
        response.setNotes(contact.getNotes());
        response.setCreatedAt(contact.getCreatedAt());
        response.setUpdatedAt(contact.getUpdatedAt());
        return response;
    }
}