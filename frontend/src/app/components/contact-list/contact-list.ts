import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';
import { ContactService } from '../../services/contact.service';
import { ContactResponse } from '../../models/contact.model';

@Component({
  selector: 'app-contact-list',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './contact-list.html',
  styleUrls: ['./contact-list.css']
})
export class ContactListComponent implements OnInit {
  contacts: ContactResponse[] = [];
  searchQuery = '';
  isLoading = false;
  errorMessage = '';
  
  selectedContact: ContactResponse | null = null;
  showContactForm = false;

  constructor(
    private contactService: ContactService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.loadContacts();
  }

  loadContacts(): void {
    this.isLoading = true;
    this.contactService.getAllContacts().subscribe({
      next: (contacts) => {
        this.contacts = contacts;
        this.isLoading = false;
      },
      error: (error) => {
        this.errorMessage = 'Failed to load contacts';
        this.isLoading = false;
      }
    });
  }

  searchContacts(): void {
    if (this.searchQuery.trim()) {
      this.isLoading = true;
      this.contactService.searchContacts(this.searchQuery, 0, 100).subscribe({
        next: (response) => {
          this.contacts = response.content;
          this.isLoading = false;
        },
        error: (error) => {
          this.errorMessage = 'Search failed';
          this.isLoading = false;
        }
      });
    } else {
      this.loadContacts();
    }
  }

  addNewContact(): void {
    this.selectedContact = null;
    this.showContactForm = true;
  }

  editContact(contact: ContactResponse): void {
    this.selectedContact = contact;
    this.showContactForm = true;
  }

  deleteContact(contact: ContactResponse): void {
    if (confirm(`Are you sure you want to delete ${contact.firstName} ${contact.lastName}?`)) {
      this.contactService.deleteContact(contact.id).subscribe({
        next: () => {
          this.loadContacts();
        },
        error: (error) => {
          this.errorMessage = 'Failed to delete contact';
        }
      });
    }
  }

  onContactSaved(): void {
    this.showContactForm = false;
    this.selectedContact = null;
    this.loadContacts();
  }

  onFormCancelled(): void {
    this.showContactForm = false;
    this.selectedContact = null;
  }
}
