import { Injectable } from '@angular/core';
import { environment } from '../../../environments/environment';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import {User} from '../model/user';
import {Shops} from '../model/shops';
import 'rxjs/add/operator/map';

const API_URL = environment.apiUrl;


@Injectable()
export class ApiService {

  constructor(private httpClient: HttpClient) { }
/*

  static parseShopsFromJson (json: JSON): Shops{
    const id = json['_id'];
    const city = json['_id'];
    const name = json['_id'];
    const email = json['_id'];
    const picture = json['picture'];
    const location = json['location']['coordinates'];

    /!*const shop = new Shops();
    shop.setId(id);
    shop.setName(name);
    shop.setEmail(email);
    shop.setCity(city);
    shop.setPicture(picture);
    shop.setLocation(location);
    *!/
    return null;
  }*/



  // API: GET /shops
  public getAllShops (): Observable<any>{
    return this.httpClient.get( 'http://localhost:8085/');
     }

  // API: GET /likedShops
  public getLikedShops (id: string): Observable<any>{
    return this.httpClient.get(API_URL + '/prefered_shops' + id);
  }


  // API: GET /dislikedShops
  public getDisikedShops (id: string): Observable<any>{
    return this.httpClient.get(API_URL + '/disliked_shops/' + id);
  }

  // API: GET /check User Login
  public checkUserLogin (user: User): Observable<any>{
    return this.httpClient.post<User>(API_URL + '/check_login', '{"email": "' + user.email + '" , "password": "' + user.password + '" }');
  }


  // API: PUT /forgot password
  public updateUserPassword (email: string): Observable<string>{
    return this.httpClient.put<string>('${API_URL}/forgot_password', email);
  }

  // API: POST /create user
  public createUser (user: User): any {
    return this.httpClient.post(API_URL + '/create_user', '{"email": "' + user.email + '" , "password": "' + user.password + '" }')
      .subscribe(value => {
          console.log(
            'TASK: ' + value['task'] + '\n' +
            'STATUS: ' + value['status'] + '\n'
          );
      });
  }




}
