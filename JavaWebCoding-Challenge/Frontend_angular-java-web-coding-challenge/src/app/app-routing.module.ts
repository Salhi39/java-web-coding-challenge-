import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import {LoginComponent} from './front/login/login.component';
import {AuthGuard} from './server/auth/auth.guard';
import {HomeComponent} from './front/home/home.component';
import {ShopComponent} from './front/shop/shop.component';
import {RegisterComponent} from './front/register/register.component';
import {PreferredShopComponent} from './front/preferred-shop/preferred-shop.component';

const routes: Routes = [
  { path: '', component: HomeComponent, canActivate: [AuthGuard] },
  { path: 'my_favorite', component: PreferredShopComponent },
  { path: 'login', component: LoginComponent },
  { path: 'register', component: RegisterComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
