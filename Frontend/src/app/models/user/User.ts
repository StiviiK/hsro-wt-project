import { SocialUser } from 'ng4-social-login';
import { UserJson, ThreadAnswerJson } from '../interfaces/api/JsonResponse';

export class User { // represents any user
  public static users: Map<number, User> = new Map();

  public id: number;
  public name: string;
  public email: string; // unique + primary key
  public avatar: string;
  public _answers?: ThreadAnswerJson[];
  public _topics?: number[];

  public constructor(id: number, name: string, email: string, avatar: string) {
    this.id = id;
    this.name = name;
    this.email = email;
    this.avatar = avatar;
  }

  // Creates an user from UserJson
  public static get(data: UserJson) {
    if (User.users.get(data.id)) {
      const user = User.users.get(data.id);
      return user;
    }

    const user = new User(data.id, data.name, data.email, data.avatar);
    User.users.set(data.id, user);
    return user;
  }
}

export class AuthenticatedUser extends User { // represents the loggedin user
  public token: string; // the jwt token for communication with the backend
  public socialUser: SocialUser; // represents the google user

  public constructor(id: number, socialUser: SocialUser, token: string) {
    super(id, socialUser.name, socialUser.email, socialUser.photoUrl);

    this.token = token;
    this.socialUser = socialUser;
  }

  // deletes the user from the localstorage
  public static clear(): void {
    localStorage.removeItem('user');
  }

  // loads the current user from localstorage
  public static load(): AuthenticatedUser {
    return JSON.parse(localStorage.getItem('user')) as AuthenticatedUser;
  }

  // saves the user to the localstorage
  public static save(user: User): void {
    localStorage.setItem('user', JSON.stringify(user));
  }
}
