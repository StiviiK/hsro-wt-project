import { Thread } from './Thread';
import { User } from '../user/User';
import { ThreadAnswerJson } from '../interfaces/api/JsonResponse';

export class ThreadAnswer {
  public static answers: Map<number, ThreadAnswer> = new Map();
  
  public id: number;
  public thread: Thread;
  public creator: User;
  public message: string;

  public constructor(id: number, thread: Thread, creator: User, message: string) {
    this.id = id;
    this.thread = thread;
    this.creator = creator;
    this.message = message;
  }

  public static get(data: ThreadAnswerJson, thread: Thread): ThreadAnswer {
    const answer = new ThreadAnswer(data.id, thread, User.get(data.creator), data.message);
    ThreadAnswer.answers.set(data.id, answer);
    return answer;
  }
}
