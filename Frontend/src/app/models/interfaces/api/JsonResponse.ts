import { User } from "../../user/User";
import { ThreadAnswer } from "../../forum/ThreadAnswer";

export interface UserJson {
    id: number;
    name: string;
    email: string;
    avatar: string;
}

export interface ThreadJson {
    id: number;
    views: number;
    creator: UserJson;
    topic: string;
    question: string;
    answers: ThreadAnswerJson[];
    lastUpdate: number;
    votes: number;
    category: number;
}

export interface ThreadAnswerJson {
    id: number;
    creator: UserJson;
    message: string;
}

export interface ForumCategoryJson {
    id: number;
    name: String;
    threads: number[];
    color: String;
}