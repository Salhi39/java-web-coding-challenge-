import {Injectable} from '@angular/core';
import {environment} from '../../../environments/environment';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs/Observable';
import {User} from '../model/user';
import {Shops} from '../model/shops';
import 'rxjs/add/operator/map';
import {Security} from '../security';

const API_URL = environment.apiUrl;


@Injectable()
export class ApiService {

  constructor(private httpClient: HttpClient) {
  }

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
  public getAllShops(): Observable<any> {
    return this.httpClient.get('http://localhost:8085/');
  }


  public getAllNotCommentedShops(email: string): Observable<any> {
    console.log('EMAIL: ' + email);
    let hex = '';
    for (let i = 0; i < email.length; i++) {
      hex += '' + email.charCodeAt(i).toString(16);
    }
    return this.httpClient.get('http://localhost:8085/not_commented_shop/' + hex);
  }

  // API: GET /likedShops
  public getLikedShops(id: string): Observable<any> {
    return this.httpClient.get(API_URL + '/preferred_shops' + id);
  }


  // API: GET /dislikedShops
  public getDisikedShops(id: string): Observable<any> {
    return this.httpClient.get(API_URL + '/disliked_shops/' + id);
  }

  // API: GET /check User Login
  public checkUserLogin(user: User): Observable<any> {
    return this.httpClient.post<User>(API_URL + '/check_login', '{"email": "' + user.email + '" , "password": "' + user.password + '" }');
  }


  // API: PUT /forgot password
  public updateUserPassword(email: string): Observable<string> {
    return this.httpClient.put<string>('${API_URL}/forgot_password', email);
  }

  // API: POST /create user
  public createUser(user: User): any {
    return this.httpClient.post(API_URL + '/create_user', '{"email": "' + user.email + '" , "password": "' + user.password + '" }')
      .subscribe(value => {
        console.log(
          'TASK: ' + value['task'] + '\n' +
          'STATUS: ' + value['status'] + '\n'
        );
      });
  }


  public addShopAsLiked(userEmail: string, idShop: string) {
    console.log(idShop);
    return this.httpClient.post(API_URL + '/add_shop_as_liked', '{"userEmail": "' + userEmail + '" , "idShop": "' + idShop + '" }')
      .subscribe(value => {
        console.log(
          'TASK: ' + value['task'] + '\n' +
          'STATUS: ' + value['status'] + '\n'
        );
      });
  }

  public addShopAsDisliked(userEmail: string, idShop: string) {
    return this.httpClient.post(API_URL + '/add_shop_as_disliked', '{"userEmail": "' + userEmail + '" , "idShop": "' + idShop + '" }')
      .subscribe(value => {
        console.log(
          'TASK: ' + value['task'] + '\n' +
          'STATUS: ' + value['status'] + '\n'
        );
      });
  }


  public removeShopFromLiked(userEmail: string, idShop: string) {
    return this.httpClient.post(API_URL + '/remove_from_liked_shop', '{"userEmail": "' + userEmail + '" , "idShop": "' + idShop + '" }')
      .subscribe(value => {
        console.log(
          'TASK: ' + value['task'] + '\n' +
          'STATUS: ' + value['status'] + '\n'
        );
      });
  }

  public removeShopFromDisliked(userEmail: string, idShop: string) {
    return this.httpClient.post(API_URL + '/remove_from_disliked_shop', '{"userEmail": "' + userEmail + '" , "idShop": "' + idShop + '" }')
      .subscribe(value => {
        console.log(
          'TASK: ' + value['task'] + '\n' +
          'STATUS: ' + value['status'] + '\n'
        );
      });
  }


}
