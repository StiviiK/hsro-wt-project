import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppMaterialModule } from './app.material.module';

import { AppComponent } from './app.component';
import { AppRoutingModule } from './/app-routing.module';
import { ChatComponent } from './chat/chat.component';
import { EnterpageComponent } from './enterpage/enterpage.component';
import { ForumComponent } from './forum/forum.component';
import { ForumContentComponent } from './forum/forum-content/forum-content.component';
import { ErrorComponent } from './error/error.component';


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
