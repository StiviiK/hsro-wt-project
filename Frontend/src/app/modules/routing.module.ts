import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { AuthGuard } from '../guards/authentication/auth.guard';

import { EnterpageComponent } from '../components/enterpage/enterpage.component';
import { ChatComponent } from '../components/chat/chat.component';
import { ForumComponent } from '../components/forum/forum.component';
import { ErrorComponent } from '../components/error/error.component';
import { ForumThreadComponent } from '../components/forum/thread/thread.component';
import { LoginComponent } from '../components/user/auth/login.component';
import { LogoutComponent } from '../components/user/auth/logout.component';

const routes: Routes = [
  { path: 'login', component: LoginComponent },
  { path: 'logout', component: LogoutComponent },
  { path: '', redirectTo: '/dashboard', pathMatch: 'full' }, // If path routing is empty redirect to dashboard

  // error routes
  { path: 'error', component: ErrorComponent },
  { path: 'error/:id', component: ErrorComponent },

  // linking rout paths with components
  { path: 'dashboard', component: EnterpageComponent, canActivate: [AuthGuard] },
  { path: 'chat', component: ChatComponent, canActivate: [AuthGuard] },
  { path: 'forum', component: ForumComponent, canActivate: [AuthGuard] },
  // { path: 'forum/thread', component: ForumThreadComponent },
  { path: 'forum/thread/:id', component: ForumThreadComponent, canActivate: [AuthGuard] },

  // cath all other routes and redirect to 404 page
  { path: '**', redirectTo: '/error/404' },
];

@NgModule({
  imports: [ RouterModule.forRoot(routes) ],
  exports: [ RouterModule ]
})
export class AppRoutingModule {}
