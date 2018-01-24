// tslint:disable-next-line:no-empty-interface
export interface ApiRequest {}

export interface JWTApiRequest extends ApiRequest {
  googleToken: string;
  user: {
    email: string;
    name: string;
    avatar: string;
  };
}

export interface ThreadApiRequest extends ApiRequest {
  topic: string;
  question: string;
  creator: number;
}

export interface ThreadAnswerApiRequest extends ApiRequest {
  message: string;
  creator: number;
}
