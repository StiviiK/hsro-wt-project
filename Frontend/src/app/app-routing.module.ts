import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { EnterpageComponent } from './enterpage/enterpage.component';
import { ChatComponent } from './chat/chat.component';
import { ForumComponent } from './forum/forum.component';

const routes: Routes = [
  { path: '', redirectTo: '/dashboard', pathMatch: 'full' }, // If path routing is empty redirect to dashboard
  // linking rout paths with components
  { path: 'dashboard', component: EnterpageComponent },
  { path: 'chat', component: ChatComponent },
  { path: 'forum', component: ForumComponent },
  { path: 'forum/test', component: ForumComponent},
];

@NgModule({
  imports: [ RouterModule.forRoot(routes) ],
  exports: [ RouterModule ]
})
export class AppRoutingModule {}
