export interface LoginRequest {
  username: string;
  password: string;
}

export interface RegisterRequest {
  username: string;
  password: string;
  email: string;
  firstName: string;
  lastName: string;
}

export interface AuthResponse {
  token: string;
  type: string;
  username: string;
  email: string;
  firstName: string;
  lastName: string;
}

export interface ErrorResponse {
  message: string;
}