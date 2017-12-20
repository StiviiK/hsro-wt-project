import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppMaterialModule } from './app.material.module';

import { AppComponent } from './app.component';
import { AppRoutingModule } from './/app-routing.module';
import { ChatComponent } from './chat/chat.component';
import { EnterpageComponent } from './enterpage/enterpage.component';
import { ForumComponent } from './forum/forum.component';


@NgModule({
  declarations: [
    AppComponent,
    ChatComponent,
    EnterpageComponent,
    ForumComponent
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
