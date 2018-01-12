import { Thread } from './Thread';

export class ForumCategory {
  public id: number;
  public name: String;
  public threads: Thread[];
  public color: String;

  public constructor(id: number, name: String, color: String) {
    this.id = id;
    this.name = name;
    this.color = color;
    this.threads = [];
  }

  public addThread(thread: Thread) {
    this.threads.push(thread);
  }
}
