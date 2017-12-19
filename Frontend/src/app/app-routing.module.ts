import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { EnterpageComponent } from './enterpage/enterpage.component';
import { ChatComponent } from './chat/chat.component';

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
