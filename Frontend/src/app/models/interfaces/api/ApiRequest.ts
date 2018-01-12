export interface ApiRequest { }

export interface AuthenticatedApiRequest extends ApiRequest {
  token: string;
}

export interface JWTApiRequest extends ApiRequest {
  googleToken: string;
  user: {
    email: string;
    name: string;
    avatar: string;
  };
}
