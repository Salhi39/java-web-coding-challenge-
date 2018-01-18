import {Observable} from 'rxjs/Observable';
import {AuthService} from '../../server/auth/auth.service';
import {Component, OnInit} from '@angular/core';
import {Router} from '@angular/router';
import {Security} from '../../server/security';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})


export class HeaderComponent implements OnInit {

  isLoggedIn$: Observable<boolean>;
  isFavoriteEnabled: boolean;
  isListEnabled: boolean;

  constructor(private router: Router, private authService: AuthService) {
    this.isFavoriteEnabled = false;
    this.isListEnabled = true;
  }

  ngOnInit() {
    this.isFavoriteEnabled = false;
    this.isListEnabled = true;
    console.log('Favorite: ' + this.isFavoriteEnabled + ' ---  List: ' + this.isListEnabled );
    this.isLoggedIn$ = this.authService.isLoggedIn;
    this.isLoggedIn$.subscribe(value => {
      if (!value) {
        this.router.navigate(['/login']);
      }
    });
  }

  onLogout() {
    this.isFavoriteEnabled = false;
    this.isListEnabled = true;
    this.authService.logout();
  }


  goToPreferred() {
    console.log('M_Favorite(): ' + this.isFavoriteEnabled + ' ---  List: ' + this.isListEnabled );
    if (this.isListEnabled) {
      const userEmail = this.authService.userEmail;
      this.router.navigate(['/my_favorite'], {queryParams: {id: Security.encrypt(userEmail)}});
      this.isFavoriteEnabled = true;
      this.isListEnabled = false;
    }
  }

  goToAllShop() {
    console.log('Favorite: ' + this.isFavoriteEnabled + ' ---  M_List(): ' + this.isListEnabled );
    if (this.isFavoriteEnabled) {
      const userEmail = this.authService.userEmail;
      this.router.navigate(['/'], {queryParams: {id: Security.encrypt(userEmail)}});
      this.isListEnabled = true;
      this.isFavoriteEnabled = false;
    }
  }
}
