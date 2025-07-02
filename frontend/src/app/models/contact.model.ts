export interface ContactRequest {
  firstName: string;
  lastName: string;
  phoneNumber: string;
  email?: string;
  address?: string;
  company?: string;
  notes?: string;
}

export interface ContactResponse {
  id: number;
  firstName: string;
  lastName: string;
  phoneNumber: string;
  email?: string;
  address?: string;
  company?: string;
  notes?: string;
  createdAt: string;
  updatedAt: string;
}

export interface ContactPage {
  content: ContactResponse[];
  totalElements: number;
  totalPages: number;
  size: number;
  number: number;
  first: boolean;
  last: boolean;
}