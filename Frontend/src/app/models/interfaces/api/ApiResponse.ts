import { User } from "../../user/User";
import { ThreadAnswer } from "../../forum/ThreadAnswer";
import { Thread } from "../../forum/Thread";
import { ThreadJson, ForumCategoryJson } from "./JsonResponse";
import { ForumCategory } from "../../forum/ForumCategory";

export interface ApiResponse {
  status: boolean;
  message: string;

  data?: any; // result of api call
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

export interface ThreadCreateApiResponse extends ApiResponse {
  data: {
    id: number
  };
}

export interface ThreadViewApiResponse extends ApiResponse { }

export interface ForumCategoryApiResponse extends ApiResponse {
  data: ForumCategoryJson;
}

export interface ForumCategorysApiResponse extends ApiResponse {
  data: ForumCategoryJson[];
}