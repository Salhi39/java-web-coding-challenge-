import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { AgmCoreModule } from '@agm/core';

import { AppRoutingModule } from './app-routing.module';

import { AppComponent } from './app.component';
import { AuthService } from './server/auth/auth.service';
import { AuthGuard } from './server/auth/auth.guard';
import { HeaderComponent } from './front/header/header.component';
import { HomeComponent } from './front/home/home.component';
import { LoginComponent } from './front/login/login.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { ReactiveFormsModule } from '@angular/forms';

import { HttpClientModule } from '@angular/common/http';

import {AppMaterialModule} from './app-material/app-material.module';

import {
  MatToolbarModule,
  MatCardModule,
  MatInputModule,
  MatButtonModule,
  MatIconModule,
  MatDialogModule
} from '@angular/material';

import {MatGridListModule} from '@angular/material/grid-list';


import 'rxjs/operators';


import {CommonModule} from '@angular/common';
import { ShopComponent } from './front/shop/shop.component';
import { DetailsDialogComponent } from './front/details-dialog/details-dialog.component';
import { ApiService } from './server/rest-api/api.service';
import { RegisterComponent } from './front/register/register.component';
import { PreferredShopComponent } from './front/preferred-shop/preferred-shop.component';

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    HomeComponent,
    LoginComponent,
    ShopComponent,
    DetailsDialogComponent,
    RegisterComponent,
    PreferredShopComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    ReactiveFormsModule,
    BrowserAnimationsModule,
    AppMaterialModule,
    CommonModule,
    MatToolbarModule,
    MatCardModule,
    MatInputModule,
    MatButtonModule,
    MatIconModule,
    MatGridListModule,
    MatIconModule,
    MatDialogModule,
    AgmCoreModule.forRoot({
      apiKey: 'AIzaSyBT9uwjlLbNakrhVlU0rGwzSGJCBmtpOXI'
    }),
    HttpClientModule,
  ],
  entryComponents: [DetailsDialogComponent],
  providers: [AuthService, AuthGuard, ApiService],
  bootstrap: [AppComponent]
})
export class AppModule { }
