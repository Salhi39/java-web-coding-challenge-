import {Component, Input, OnInit} from '@angular/core';
import {Shops} from '../../server/model/shops';

import {MatDialog, MatDialogRef, PageEvent} from '@angular/material';
import {DetailsDialogComponent} from '../details-dialog/details-dialog.component';
import {ApiService} from '../../server/rest-api/api.service';
import {ActivatedRoute} from '@angular/router';
import {Security} from '../../server/security';

import {AES} from 'crypto-js';
import CryptoJS = require('crypto-js');


@Component({
  selector: 'app-shop',
  templateUrl: './shop.component.html',
  styleUrls: ['./shop.component.css']
})


export class ShopComponent implements OnInit {

  // shop object
  @Input() private shop: Shops;
  // shops that will be shown ( depend of paginator )
  private shopList: Shops[];
  // all shops
  private constShopList: Shops[];

  // Number of column on the page
  private nbCols: number;

  // if icon is on (true) or off (false)
  private like: boolean[];
  private dislike: boolean[];

  private userEmail: string;

  // MatPaginator Output
  pageEvent: PageEvent;

  //  View city dialog
  detailtsDialogRef: MatDialogRef<DetailsDialogComponent>;


  // MatPaginator Inputs
  private length = 100;
  private pageSize = 10;
  private pageSizeOptions = [5, 10, 25, 100];

  // Paginator intetrval
  private from = 0;
  private to = 10;


  constructor(private router: ActivatedRoute, private apiService: ApiService, private dialog: MatDialog) {
    this.shopList = [];
    this.constShopList = [];
  }


  ngOnInit() {
    // init grid column
    this.nbCols = this.adaptColumn(window.screen.width);
    this.like = [];
    this.dislike = [];
    // get all shops
    this.router.queryParams.forEach(value => {
      this.apiService.getAllNotCommentedShops(Security.decrypt(value['id'])).subscribe(
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

            this.like.push(false);
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


  // open Dialog by given location
  openDialog(location) {
    this.detailtsDialogRef = this.dialog.open(DetailsDialogComponent, {
      width: '100%',
      height: '90%',
      data: {'location': location},
      hasBackdrop: true
    });
  }


  /**
   * Set the paginator after the view init since this component will
   * be able to query its view for the initialized paginator.
   */
  updatePagination() {

    // to avoid the overflowing of the page length
    const lastValue = this.to;

    // calculate start and end of the new interval
    this.from = this.pageEvent.pageSize * this.pageEvent.pageIndex;
    this.to = this.pageEvent.pageSize * (this.pageEvent.pageIndex + 1);
    if (this.to > this.pageEvent.length) {
      this.to = this.pageEvent.length;
    }

    // update the display list
    this.shopList = this.constShopList.slice(this.from, this.to);
  }

  // update the column number to show per row
  onResize(event) {
    const element = event.target.innerWidth;
    this.nbCols = this.adaptColumn(element);
  }


  // adapt my column by giving the width of the page
  adaptColumn(width) {
    if (width > 1200) return 4;
    else if (width > 900) return 3;
    else if (width > 630) return 2;
    else return 1;
  }

  onLike(index) {
    this.dislike[index] = false;
    this.like[index] = !this.like[index];

    this.router.queryParams.forEach(value => {
      if (this.like[index]) {
        const userEmail = Security.decrypt(value['id']);
        this.apiService.removeShopFromDisliked(userEmail, this.constShopList[index].getId());
        this.apiService.addShopAsLiked(userEmail, this.constShopList[index].getId());
      }
      else {
        const userEmail = Security.decrypt(value['id']);
        this.apiService.removeShopFromLiked(userEmail, this.constShopList[index].getId());
      }

    });
  }

  onDislike(index) {
    this.like[index] = false;
    this.dislike[index] = !this.dislike[index];

    this.router.queryParams.forEach(value => {
      if (this.dislike[index]) {
        this.userEmail = Security.decrypt(value['id']);
        this.apiService.removeShopFromLiked(this.userEmail, this.constShopList[index].getId());
        this.apiService.addShopAsDisliked(this.userEmail, this.constShopList[index].getId());
      }
      else {
        const userEmail = Security.decrypt(value['id']);
        this.apiService.removeShopFromDisliked(userEmail, this.constShopList[index].getId());
      }

    });
  }
}
