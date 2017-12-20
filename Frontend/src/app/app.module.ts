import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppMaterialModule } from './modules/material.module';
import { AppRoutingModule } from './modules/routing.module';

import { AppComponent } from './app.component';
import { ChatComponent } from './components/chat/chat.component';
import { EnterpageComponent } from './components/enterpage/enterpage.component';
import { ForumComponent } from './components/forum/forum.component';
import { ForumContentComponent } from './components/forum/forum-content/forum-content.component';
import { ErrorComponent } from './components/error/error.component';


@NgModule({
  declarations: [
    AppComponent,
    ChatComponent,
    EnterpageComponent,
    ForumComponent,
    ForumContentComponent,
    ErrorComponent
  ],
  imports: [
    BrowserModule,
    AppMaterialModule,
    AppRoutingModule,
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
