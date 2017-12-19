import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { ChatComponent } from './chat/chat.component';
import { EnterpageComponent } from './enterpage/enterpage.component';

const routes: Routes = [
  { path: '', redirectTo: '/dashboard', pathMatch: 'full' },
  { path: 'dashboard', component: EnterpageComponent },
  { path: 'chat', component: ChatComponent }
];

@NgModule({
  imports: [ RouterModule.forRoot(routes) ],
  exports: [ RouterModule ]
})
export class AppRoutingModule {}
