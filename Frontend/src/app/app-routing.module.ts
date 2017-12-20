import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { EnterpageComponent } from './enterpage/enterpage.component';
import { ChatComponent } from './chat/chat.component';
import { ForumComponent } from './forum/forum.component';
import { ErrorComponent } from './error/error.component';

const routes: Routes = [
  { path: '', redirectTo: '/dashboard', pathMatch: 'full' }, // If path routing is empty redirect to dashboard

  // error routes
  { path: 'error', component: ErrorComponent },
  { path: 'error/:id', component: ErrorComponent },

  // linking rout paths with components
  { path: 'dashboard', component: EnterpageComponent },
  { path: 'chat', component: ChatComponent },
  { path: 'forum', component: ForumComponent },
  { path: 'forum/test', component: ForumComponent},

  // cath all other routes and redirect to 404 page
  { path: '**', redirectTo: '/error/404' },
];

@NgModule({
  imports: [ RouterModule.forRoot(routes) ],
  exports: [ RouterModule ]
})
export class AppRoutingModule {}
