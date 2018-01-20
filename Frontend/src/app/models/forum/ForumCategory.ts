import { Thread } from './Thread';
import { ForumCategoryJson } from '../interfaces/api/JsonResponse';

export class ForumCategory {
  public static categories: Map<number, ForumCategory> = new Map();

  public id: number;
  public name: String;
  public threads: Thread[];
  public _threads: number[];
  public color: String;

  public constructor(id: number, name: String, color: String, threads: number[]) {
    this.id = id;
    this.name = name;
    this.color = color;
    this._threads = threads;
  }

  public addThread(thread: Thread) {
    if (this.threads.findIndex((other: Thread) => other === thread) === -1) {
      this.threads.push(thread);
    }
  }

  public static get(data: ForumCategoryJson) {
    const category = new ForumCategory(data.id, data.name, data.color, data.threads);
    ForumCategory.categories.set(data.id, category);
    return category;
  }

  public static getById(id: number) {
    return ForumCategory.categories.get(id);
  }
}
