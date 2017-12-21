import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppMaterialModule } from './modules/material.module';
import { AppRoutingModule } from './modules/routing.module';

import { AppComponent } from './app.component';
import { ChatComponent } from './components/chat/chat.component';
import { EnterpageComponent } from './components/enterpage/enterpage.component';
import { ForumComponent } from './components/forum/forum.component';
import { ForumContentComponent } from './components/forum/forum-content/forum-content.component';
import { ForumThreadComponent } from './components/forum/thread/thread.component';
import { ErrorComponent } from './components/error/error.component';
import { ThreadService } from './services/forum/thread.service';

@NgModule({
  declarations: [
    AppComponent,
    ChatComponent,
    EnterpageComponent,
    ForumComponent,
    ForumContentComponent,
    ErrorComponent,
    ForumThreadComponent
  ],
  imports: [
    BrowserModule,
    AppMaterialModule,
    AppRoutingModule,
  ],
  providers: [ThreadService],
  bootstrap: [AppComponent]
})
export class AppModule { }
