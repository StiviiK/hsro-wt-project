import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-enterpage',
  templateUrl: './enterpage.component.html',
  styleUrls: ['./enterpage.component.scss']
})
export class EnterpageComponent implements OnInit { // fill this with backend later
  hotTopics = [
    {
      name: 'Angular 4 is not working',
      updated: new Date('1/1/16'),
      answers: 5,
    },
    {
      name: 'PC doesnt start, help please',
      updated: new Date('1/17/16'),
      answers: 1,
    },
    {
      name: 'Work',
      updated: new Date('1/28/16'),
      answers: 20,
    }
  ];
  lastTopics = [
    {
      name: 'How do i do this computing thing',
      updated: new Date('2/20/16'),
      answers: 10,
    },
    {
      name: 'Website Remodel',
      updated: new Date('1/18/16'),
      answers: 60,
    }
  ];

  constructor() { }

  ngOnInit() {
  }

}
