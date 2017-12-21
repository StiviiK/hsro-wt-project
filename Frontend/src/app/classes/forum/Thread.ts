import { User } from '../user/User';
import { ThreadAnswer } from './ThreadAnswer';

export class Thread {
  public id: number;
  public views: number;
  public creator: User;
  public topic: string;
  public question: string;
  public answers: ThreadAnswer[];
  public lastUpdate: Date;

  public constructor(id: number, views: number, creator: User, topic: string, question: string, lastUpdate: Date) {
    this.id = id;
    this.views = views;
    this.creator = creator;
    this.topic = topic;
    this.question = question;
    this.answers = [];
    this.lastUpdate = lastUpdate;
  }

  public addAnswer(answer: ThreadAnswer): number {
    answer.thread = this;
    return this.answers.push(answer);
  }
}
