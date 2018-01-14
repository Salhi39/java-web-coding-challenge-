import {Injectable} from '@angular/core';
import {Router} from '@angular/router';
import {BehaviorSubject} from 'rxjs/BehaviorSubject';
import {User} from './../model/user';
import {ApiService} from '../rest-api/api.service';
import {observable} from 'rxjs/symbol/observable';
import {Observable} from 'rxjs/Observable';

@Injectable()
export class AuthService {
  private loggedIn = new BehaviorSubject<boolean>(false);
  private myObservable: Observable<any> = null;
  get isLoggedIn() {
    return this.loggedIn.asObservable();
  }

  constructor(private router: Router, private apiService: ApiService) {
  }

  login(user: User, status) {
    if (user.email !== '' && user.password != '') {
      this.myObservable = this.apiService.checkUserLogin(user);
      this.myObservable.subscribe(value => {
        console.log('STATUS -' + value['status']);
        if (value['status'] == 'SUCCESSFUL') {
          this.loggedIn.next(true);
          this.router.navigate(['/']);
        }

        status = value['status'];
      });
    }

    return this.myObservable;
  }

  navogateToRogister() {
    this.router.navigate(['/register']);
  }

  register(user: User) {
    if (user.email !== '' && user.password != '') {
      this.apiService.createUser(user);

      this.loggedIn.next(true);
      this.router.navigate(['/login']);
    }
  }

  logout() {
    this.loggedIn.next(false);
    this.router.navigate(['/login']);
  }
}
