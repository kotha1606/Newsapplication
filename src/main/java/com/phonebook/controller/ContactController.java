package com.phonebook.controller;

import com.phonebook.dto.ContactRequest;
import com.phonebook.dto.ContactResponse;
import com.phonebook.service.ContactService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/contacts")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", maxAge = 3600)
public class ContactController {

    private final ContactService contactService;

    @GetMapping
    public ResponseEntity<List<ContactResponse>> getAllContacts(Authentication authentication) {
        List<ContactResponse> contacts = contactService.getAllContacts(authentication.getName());
        return ResponseEntity.ok(contacts);
    }

    @GetMapping("/paginated")
    public ResponseEntity<Page<ContactResponse>> getAllContactsPaginated(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            Authentication authentication) {
        
        Pageable pageable = PageRequest.of(page, size);
        Page<ContactResponse> contacts = contactService.getAllContactsPaginated(authentication.getName(), pageable);
        return ResponseEntity.ok(contacts);
    }

    @GetMapping("/search")
    public ResponseEntity<Page<ContactResponse>> searchContacts(
            @RequestParam String query,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            Authentication authentication) {
        
        Pageable pageable = PageRequest.of(page, size);
        Page<ContactResponse> contacts = contactService.searchContacts(authentication.getName(), query, pageable);
        return ResponseEntity.ok(contacts);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getContactById(@PathVariable Long id, Authentication authentication) {
        try {
            ContactResponse contact = contactService.getContactById(authentication.getName(), id);
            return ResponseEntity.ok(contact);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<?> createContact(@Valid @RequestBody ContactRequest contactRequest, 
                                         Authentication authentication) {
        try {
            ContactResponse contact = contactService.createContact(authentication.getName(), contactRequest);
            return ResponseEntity.status(HttpStatus.CREATED).body(contact);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(new ErrorResponse(e.getMessage()));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateContact(@PathVariable Long id, 
                                         @Valid @RequestBody ContactRequest contactRequest,
                                         Authentication authentication) {
        try {
            ContactResponse contact = contactService.updateContact(authentication.getName(), id, contactRequest);
            return ResponseEntity.ok(contact);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(new ErrorResponse(e.getMessage()));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteContact(@PathVariable Long id, Authentication authentication) {
        try {
            contactService.deleteContact(authentication.getName(), id);
            return ResponseEntity.ok(new SuccessResponse("Contact deleted successfully"));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(new ErrorResponse(e.getMessage()));
        }
    }

    @GetMapping("/count")
    public ResponseEntity<Long> getContactCount(Authentication authentication) {
        long count = contactService.getContactCount(authentication.getName());
        return ResponseEntity.ok(count);
    }

    // Helper classes for responses
    private static class ErrorResponse {
        private String message;

        public ErrorResponse(String message) {
            this.message = message;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }

    private static class SuccessResponse {
        private String message;

        public SuccessResponse(String message) {
            this.message = message;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }
}