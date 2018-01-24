import { Component, OnInit } from '@angular/core';
import { AuthenticationService } from '../../../services/authentication/authentication';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {

  public isLoading: boolean;

  constructor(private _authService: AuthenticationService, private _router: Router) { }

  ngOnInit() {
  }

  login() {
    this.isLoading = true;
    this._authService.login().subscribe(
      (result) => {
        this.isLoading = false;
        this._router.navigate(['/!/dashboard']);
      },
      (err) => {
        this.isLoading = false;
        console.error(err);
      }
    );
  }

  logout() {
    console.log(this._authService.logout());
  }
}
