import { EmailValidator } from '@angular/forms';
import { SocialUser } from 'ng4-social-login';

export class User { // represents any user
  public id: number;
  public name: string;
  public email: string; // unique + primary key
  public avatar_url: string;

  public constructor(id: number, name: string, email: string, avatar_url: string) {
    this.id = id;
    this.name = name;
    this.email = email;
    this.avatar_url = avatar_url;
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
