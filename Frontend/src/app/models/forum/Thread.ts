import { User } from '../user/User';
import { ThreadAnswer } from './ThreadAnswer';
import { ForumCategory } from './ForumCategory';
import { ThreadJson } from '../interfaces/api/JsonResponse';

export class Thread {
  public static threads: Map<number, Thread> = new Map();

  public id: number;
  public views: number;
  public creator: User;
  public topic: string;
  public question: string;
  public answers: ThreadAnswer[];
  public lastUpdate: Date;
  public votes: number;
  public category: ForumCategory;
  public _category: number;

  public constructor(id: number, views: number, creator: User, topic: string, question: string, lastUpdate: Date, category: number) {
    this.id = id;
    this.views = views;
    this.creator = creator;
    this.topic = topic;
    this.question = question;
    this.answers = [];
    this.lastUpdate = lastUpdate;
    this.votes = Math.floor(Math.random() * 20 - 10);
    this._category = category;
  }

  public addAnswer(answer: ThreadAnswer): number {
    answer.thread = this;
    return this.answers.push(answer);
  }

  public setCategory(category: ForumCategory) {
    this.category = category;
  }


  public static get(data: ThreadJson) {
    const thread = new Thread(data.id, data.views, User.get(data.creator), data.topic, data.question, new Date(data.lastUpdate), data.category);
    Thread.threads.set(data.id, thread);
    return thread;
  }

  public static getById(id: number) {
    return Thread.threads.get(id);
  }
}
