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


  static parseShopsFromJson (json: JSON): Shops{
    const id = json['_id'];
    const city = json['_id'];
    const name = json['_id'];
    const email = json['_id'];
    const picture = json['picture'];
    const location = json['location']['coordinates'];

    /*const shop = new Shops();
    shop.setId(id);
    shop.setName(name);
    shop.setEmail(email);
    shop.setCity(city);
    shop.setPicture(picture);
    shop.setLocation(location);
    */
    return null;
  }
  // API: GET /shops
  public getAllShops (): Observable<any>{
   // return this.httpClient.get<Array<Shops>>( 'http://localhost:8085/');

    return this.httpClient.get( 'http://localhost:8085/');
    /*  .map((res: Response) => {
        //console.log(typeof(mres));
        return res.json().result.map(item => {
          return new Shops(
            item.getId(),
            item.getPicture(),
            item.getName(),
            item.getEmail(),
            item.getCity(),
            item.getLocation()
          );
        });
    }*/

  }

    //.subscribe(data => {
      //console.log(data);
    //});

    // return this.httpClient.get<Array<Shops>>(API_URL+'/shops');


  // API: GET /likedShops
  public getLikedShops (id: string): Observable<Shops[]>{
    return this.httpClient.get<Array<Shops>>(API_URL + '/prefered_shops' + id);
  }


  // API: GET /dislikedShops
  public getDisikedShops (id: string): Observable<Shops[]>{
    return this.httpClient.get<Array<Shops>>(API_URL + '/disliked_shops/' + id);
  }

  // API: GET /check User Login
  public checkUserLogin (user: User): Observable<User>{
    return this.httpClient.post<User>(API_URL + '/check_login', user);
  }


  // API: PUT /forgot password
  public updateUserPassword (email: string): Observable<string>{
    return this.httpClient.put<string>('${API_URL}/forgot_password', email);
  }




}
