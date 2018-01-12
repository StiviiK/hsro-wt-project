// import { Thread } from '../../forum/Thread';

export interface ApiResponse {
  status: boolean;
  message: string;
  error?: string;

  data?: object; // result of api call
}

export interface JWTApiResponse extends ApiResponse {
  data: {
    token: string;
    user: {
      id: number;
    };
  };
}

// Example code
// export interface ThreadApiResponse extends ApiResponse {
//   data: {
//     id: number;
//     views: number;
//     creator: number;
//     topic: string;
//     question: string;
//     answers: number[];
//     lastUpdate: number;
//   };
// }

// export interface UserApiResponse extends ApiResponse {
//   data: {
//     id: number;
//     name: string;
//     email: string;
//     avatar_url: string;
//   };
// }
