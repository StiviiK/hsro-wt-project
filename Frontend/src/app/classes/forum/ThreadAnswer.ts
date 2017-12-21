import { Thread } from './Thread';
import { User } from '../user/User';

export class ThreadAnswer {
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
}
