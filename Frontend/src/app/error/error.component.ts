import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-error',
  templateUrl: './error.component.html',
  styleUrls: ['./error.component.scss']
})
export class ErrorComponent implements OnInit {

  valid_types = {404: true, 500: true};
  type: number;

  constructor(private _route: ActivatedRoute, private _router: Router) {
    this._route.params.subscribe(params => {
      if (this.valid_types[params['id']] === true) {
        this.type = params['id'];
      } else {
        this._router.navigate(['error', '404']);
      }
    });
  }

  ngOnInit() {
  }

}
