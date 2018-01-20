import { User } from "../../user/User";
import { ThreadAnswer } from "../../forum/ThreadAnswer";
import { Thread } from "../../forum/Thread";
import { ThreadJson, ForumCategoryJson } from "./JsonResponse";

export interface ApiResponse {
  status: boolean;
  message: string;

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

export interface ThreadApiResponse extends ApiResponse {
  data: ThreadJson;
}

export interface ForumCategoryApiResponse extends ApiResponse {
  data: ForumCategoryJson;
}