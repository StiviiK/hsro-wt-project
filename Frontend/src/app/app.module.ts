import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppMaterialModule } from './modules/material.module';
import { AppRoutingModule } from './modules/routing.module';

import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { AuthGuard } from './guards/authentication/auth.guard';
import { AuthInterceptor } from './services/authentication/auth.interceptor';

import { ThreadService } from './services/forum/thread';
import { AuthenticationService } from './services/authentication/authentication';

import { AppComponent } from './app.component';
import { ChatComponent } from './components/chat/chat.component';
import { EnterpageComponent } from './components/enterpage/enterpage.component';
import { ForumComponent } from './components/forum/forum.component';
import { ForumContentComponent } from './components/forum/forum-content/forum-content.component';
import { ForumThreadComponent } from './components/forum/thread/thread.component';
import { ErrorComponent } from './components/error/error.component';
import { LoginComponent } from './components/user/auth/login.component';
import { LogoutComponent } from './components/user/auth/logout.component';


import { SocialLoginModule, AuthServiceConfig, GoogleLoginProvider } from 'angular4-social-login';
const socialConfig = new AuthServiceConfig([
  {
    id: GoogleLoginProvider.PROVIDER_ID,
    provider: new GoogleLoginProvider('46761239813-oumj8o0oh51ipa90d6gf88jkp2d946n3.apps.googleusercontent.com')
  }
]);

@NgModule({
  declarations: [
    AppComponent,
    ChatComponent,
    EnterpageComponent,
    ForumComponent,
    ForumContentComponent,
    ErrorComponent,
    ForumThreadComponent,
    LoginComponent,
    LogoutComponent
  ],
  imports: [
    BrowserModule,
    AppMaterialModule,
    AppRoutingModule,
    HttpClientModule,
    SocialLoginModule.initialize(socialConfig)
  ],
  providers: [
    {
      provide: HTTP_INTERCEPTORS,
      useClass: AuthInterceptor,
      multi: true
    },
    AuthGuard,

    // Services
    ThreadService, AuthenticationService],
  bootstrap: [AppComponent]
})
export class AppModule { }
