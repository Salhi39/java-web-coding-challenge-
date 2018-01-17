import {Component, OnInit} from '@angular/core';
import {ShopComponent} from '../shop/shop.component';
import {ApiService} from '../../server/rest-api/api.service';
import {ActivatedRoute} from '@angular/router';
import {MatDialog, MatDialogRef, MatIconModule, MatIcon} from '@angular/material';
import {Shops} from '../../server/model/shops';
import {Security} from '../../server/security';
import {ConfirmDialogComponent} from '../confirm-dialog/confirm-dialog.component';

@Component({
  selector: 'app-preferred-shop',
  templateUrl: './preferred-shop.component.html',
  styleUrls: ['./preferred-shop.component.css']
})
export class PreferredShopComponent extends ShopComponent implements OnInit {

  private confirmeDialog: MatDialogRef<ConfirmDialogComponent>;

  constructor(private router: ActivatedRoute, private apiService: ApiService, private dialog: MatDialog) {
    super(router, apiService, dialog);


    this.shopList = [];
    this.constShopList = []
  }

  ngOnInit() {;
    // init grid column
    this.nbCols = this.adaptColumn(window.screen.width);
    this.dislike = [];
    // get all shops
    this.router.queryParams.forEach(value => {
      this.userEmail = Security.decrypt(value['id']);
      console.log(this.userEmail);
      this.apiService.getLikedShops(Security.decrypt(value['id'])).subscribe(
        val => {
          this.length = val.length;

          for (const elm of val) {
            this.shop = new Shops();
            this.shop.setId(elm['_id']);
            this.shop.setCity(elm['city']);
            this.shop.setEmail(elm['email']);
            this.shop.setPicture(elm['picture']);
            this.shop.setLocation(elm['location']);
            this.shop.setName(elm['name']);
            this.constShopList.push(this.shop);

            this.dislike.push(false);
          }
          // in the first time only 10 shops will be display
          let max = 10;
          // if there is less than 10 shops in my database
          // show me what I have
          if (this.constShopList.length < 10) {
            max = this.constShopList.length;
          }
          // 10 items
          this.shopList = this.constShopList.slice(0, max);
        });
    });
  }


  removeFromPreferred(index) {
    this.dislike[index] = !this.dislike[index];
    this.confirmeDialog = this.dialog.open(ConfirmDialogComponent, {
      data: index,
      hasBackdrop: true
    });

    this.confirmeDialog.afterClosed().subscribe(value => {
        if (value[1]){
          this.shopList.splice(index, 1);
          this.apiService.removeShopFromLiked(this.userEmail, this.constShopList[value[0]].getId());
        }
    });
  }

}
