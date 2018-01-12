// external modules
import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { SocialLoginModule, AuthServiceConfig, GoogleLoginProvider } from 'ng4-social-login';

// internal modules
import { AppMaterialModule } from './modules/material.module';
import { AppRoutingModule } from './modules/routing.module';

// auth
import { AuthGuard } from './guards/authentication/auth.guard';
import { AuthInterceptor } from './services/authentication/auth.interceptor';

// services
import { ThreadService } from './services/forum/thread';
import { AuthenticationService } from './services/authentication/authentication';

// components
import { AppComponent } from './app.component';
import { ChatComponent } from './components/chat/chat.component';
import { EnterpageComponent } from './components/enterpage/enterpage.component';
import { ForumComponent } from './components/forum/forum.component';
import { ForumContentComponent } from './components/forum/forum-content/forum-content.component';
import { ForumThreadComponent } from './components/forum/thread/thread.component';
import { ErrorComponent } from './components/error/error.component';
import { LoginComponent } from './components/user/auth/login.component';
import { LogoutComponent } from './components/user/auth/logout.component';

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
    SocialLoginModule
  ],
  providers: [
    {
      provide: HTTP_INTERCEPTORS,
      useClass: AuthInterceptor,
      multi: true
    },
    {
      provide: AuthServiceConfig,
      useFactory: () => new AuthServiceConfig([
        {
          id: GoogleLoginProvider.PROVIDER_ID,
          provider: new GoogleLoginProvider('46761239813-oumj8o0oh51ipa90d6gf88jkp2d946n3.apps.googleusercontent.com')
        }
      ])
    },
    AuthGuard,

    // Services
    ThreadService, AuthenticationService],
  bootstrap: [AppComponent]
})
export class AppModule { }
