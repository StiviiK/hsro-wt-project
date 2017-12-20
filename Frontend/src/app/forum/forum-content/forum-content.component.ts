import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-forum-content',
  templateUrl: './forum-content.component.html',
  styleUrls: ['./forum-content.component.css']
})
export class ForumContentComponent implements OnInit {  // we want to Get this info from Server
  computerTiles = [
    {text: 'Web technology', cols: 1, rows: 1, color: 'lightblue' },
    {text: 'Java', cols: 1, rows: 1, color: 'lightgreen'},
    {text: 'Unity', cols: 1, rows: 1, color: 'lightpink'},
    {text: 'Hardware', cols: 1, rows: 1, color: '#DDBDF1'},
  ]
  etcTiles = [
    {text: 'Food', cols: 1, rows: 1, color: 'red' },
    {text: 'Crafting', cols: 1, rows: 1, color: 'orange'},
    {text: 'Sport', cols: 1, rows: 1, color: 'yellow'},
    {text: 'RL', cols: 1, rows: 1, color: 'grey'},
  ];
  constructor() { }

  ngOnInit() {
  }
}

