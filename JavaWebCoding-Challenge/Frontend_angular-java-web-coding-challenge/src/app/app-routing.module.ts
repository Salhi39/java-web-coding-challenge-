import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import {LoginComponent} from './front/login/login.component';
import {AuthGuard} from './server/auth/auth.guard';
import {HomeComponent} from './front/home/home.component';
import {ShopComponent} from './front/shop/shop.component';
import {RegisterComponent} from './front/register/register.component';

const routes: Routes = [
  { path: '', component: HomeComponent, canActivate: [AuthGuard] },
  { path: 'login', component: LoginComponent },
  { path: 'register', component: RegisterComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
