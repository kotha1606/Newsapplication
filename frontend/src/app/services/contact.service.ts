import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { ContactRequest, ContactResponse, ContactPage } from '../models/contact.model';
import { AuthService } from './auth.service';

@Injectable({
  providedIn: 'root'
})
export class ContactService {
  private apiUrl = 'http://localhost:8080/api/contacts';

  constructor(
    private http: HttpClient,
    private authService: AuthService
  ) {}

  getAllContacts(): Observable<ContactResponse[]> {
    return this.http.get<ContactResponse[]>(this.apiUrl, {
      headers: this.authService.getAuthHeaders()
    });
  }

  getContactsPaginated(page: number = 0, size: number = 10): Observable<ContactPage> {
    const params = new HttpParams()
      .set('page', page.toString())
      .set('size', size.toString());

    return this.http.get<ContactPage>(`${this.apiUrl}/paginated`, {
      headers: this.authService.getAuthHeaders(),
      params
    });
  }

  searchContacts(query: string, page: number = 0, size: number = 10): Observable<ContactPage> {
    const params = new HttpParams()
      .set('query', query)
      .set('page', page.toString())
      .set('size', size.toString());

    return this.http.get<ContactPage>(`${this.apiUrl}/search`, {
      headers: this.authService.getAuthHeaders(),
      params
    });
  }

  getContactById(id: number): Observable<ContactResponse> {
    return this.http.get<ContactResponse>(`${this.apiUrl}/${id}`, {
      headers: this.authService.getAuthHeaders()
    });
  }

  createContact(contact: ContactRequest): Observable<ContactResponse> {
    return this.http.post<ContactResponse>(this.apiUrl, contact, {
      headers: this.authService.getAuthHeaders()
    });
  }

  updateContact(id: number, contact: ContactRequest): Observable<ContactResponse> {
    return this.http.put<ContactResponse>(`${this.apiUrl}/${id}`, contact, {
      headers: this.authService.getAuthHeaders()
    });
  }

  deleteContact(id: number): Observable<any> {
    return this.http.delete(`${this.apiUrl}/${id}`, {
      headers: this.authService.getAuthHeaders()
    });
  }

  getContactCount(): Observable<number> {
    return this.http.get<number>(`${this.apiUrl}/count`, {
      headers: this.authService.getAuthHeaders()
    });
  }
}