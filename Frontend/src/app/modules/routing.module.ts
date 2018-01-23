import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { AuthGuard } from '../guards/authentication/auth.guard';

import { LoginComponent } from '../components/user/auth/login.component';
import { LogoutComponent } from '../components/user/auth/logout.component';
import { NavComponent } from '../components/nav/nav.component';
import { EnterpageComponent } from '../components/enterpage/enterpage.component';
import { ChatComponent } from '../components/chat/chat.component';
import { ForumComponent } from '../components/forum/forum.component';
import { ErrorComponent } from '../components/error/error.component';
import { ForumThreadComponent } from '../components/forum/thread/thread.component';
import { ForumContentComponent } from '../components/forum/forum-content/forum-content.component';
import {ThreadCreateComponent} from '../components/forum/thread-create/thread-create.component';

const routes: Routes = [
  { path: 'login', component: LoginComponent },
  { path: 'logout', component: LogoutComponent },

  // create layout page in which all pages get renderd (no login/logout)
  {
    path: '!', component: NavComponent,
    children: [
      // linking rout paths with components
      { path: 'dashboard', component: EnterpageComponent, canActivate: [AuthGuard] },
      { path: 'chat', component: ChatComponent, canActivate: [AuthGuard] },
      { path: 'forum', component: ForumComponent, canActivate: [AuthGuard] },
      { path: 'forum/:categoryId', component: ForumContentComponent, canActivate: [AuthGuard] },
      { path: 'forum/:categoryId/thread/:id', component: ForumThreadComponent, canActivate: [AuthGuard] },
      { path: 'forum/:categoryId/thread-create', component: ThreadCreateComponent, canActivate: [AuthGuard] },
    ]
  },
  { path: '', redirectTo: '/!/dashboard', pathMatch: 'full' }, // If path routing is empty redirect to dashboard

  // error routes
  { path: 'error', component: ErrorComponent },
  { path: 'error/:id', component: ErrorComponent },

  // cath all other routes and redirect to 404 page
  { path: '**', redirectTo: '/error/404' },
];

@NgModule({
  imports: [ RouterModule.forRoot(routes) ],
  exports: [ RouterModule ]
})
export class AppRoutingModule {}
