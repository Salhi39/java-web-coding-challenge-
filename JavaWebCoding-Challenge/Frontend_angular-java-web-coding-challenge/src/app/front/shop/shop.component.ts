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
  @Input() private _shop: Shops;
  // shops that will be shown ( depend of paginator )
  private _shopList: Shops[];
  // all shops
  private _constShopList: Shops[];

  // Number of column on the page
  private _nbCols: number;

  // if icon is on (true) or off (false)
  private _like: boolean[];
  private _dislike: boolean[];

  private _userEmail: string;

  // MatPaginator Output
  private _pageEvent: PageEvent;

  //  View city dialog
  private _detailsDialogRef: MatDialogRef<DetailsDialogComponent>;


  // MatPaginator Inputs
  private _length = 100;
  private _pageSize = 10;
  private _pageSizeOptions = [5, 10, 25, 100];

  // Pagination Interval
  private _from = 0;
  private _to = 10;


  constructor(private _router: ActivatedRoute, private _apiService: ApiService, private _dialog: MatDialog) {
    this._shopList = [];
    this._constShopList = [];
  }


  ngOnInit() {
    // init grid column
    this._nbCols = this.adaptColumn(window.screen.width);
    this._like = [];
    this._dislike = [];
    // get all shops
    this._router.queryParams.forEach(value => {
      console.log('-------- ' + Security.decrypt(value['id']));
      this._apiService.getAllNotCommentedShops(Security.decrypt(value['id'])).subscribe(
        val => {
          this._length = val.length;

          for (const elm of val) {
            this._shop = new Shops();
            this._shop.setId(elm['_id']);
            this._shop.setCity(elm['city']);
            this._shop.setEmail(elm['email']);
            this._shop.setPicture(elm['picture']);
            this._shop.setLocation(elm['location']);
            this._shop.setName(elm['name']);
            this._constShopList.push(this._shop);

            this._like.push(false);
            this._dislike.push(false);
          }
          // in the first time only 10 shops will be display
          let max = 10;
          // if there is less than 10 shops in my database
          // show me what I have
          if (this._constShopList.length < 10) {
            max = this._constShopList.length;
          }
          // 10 items
          this._shopList = this._constShopList.slice(0, max);
        });
    });
  }


  // open Dialog by given location
  openDialog(location) {
    this._detailsDialogRef = this._dialog.open(DetailsDialogComponent, {
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
    const lastValue = this._to;

    // calculate start and end of the new interval
    this._from = this._pageEvent.pageSize * this._pageEvent.pageIndex;
    this._to = this._pageEvent.pageSize * (this._pageEvent.pageIndex + 1);
    if (this._to > this._pageEvent.length) {
      this._to = this._pageEvent.length;
    }

    // update the display list
    this._shopList = this._constShopList.slice(this._from, this._to);
  }

  // update the column number to show per row
  onResize(event) {
    const element = event.target.innerWidth;
    this._nbCols = this.adaptColumn(element);
  }


  // adapt my column by giving the width of the page
  adaptColumn(width) {
    if (width > 1200) return 4;
    else if (width > 900) return 3;
    else if (width > 630) return 2;
    else return 1;
  }

  onLike(index) {
    this._dislike[index] = false;
    this._like[index] = !this._like[index];

    this._router.queryParams.forEach(value => {
      if (this._like[index]) {
        const userEmail = Security.decrypt(value['id']);
        this._apiService.removeShopFromDisliked(userEmail, this._constShopList[index].getId());
        this._apiService.addShopAsLiked(userEmail, this._constShopList[index].getId());
      }
      else {
        const userEmail = Security.decrypt(value['id']);
        this._apiService.removeShopFromLiked(userEmail, this._constShopList[index].getId());
      }

    });
  }

  onDislike(index) {
    this._like[index] = false;
    this._dislike[index] = !this._dislike[index];

    this._router.queryParams.forEach(value => {
      if (this._dislike[index]) {
        this._userEmail = Security.decrypt(value['id']);
        this._apiService.removeShopFromLiked(this._userEmail, this._constShopList[index].getId());
        this._apiService.addShopAsDisliked(this._userEmail, this._constShopList[index].getId());
      }
      else {
        const userEmail = Security.decrypt(value['id']);
        this._apiService.removeShopFromDisliked(userEmail, this._constShopList[index].getId());
      }

    });
  }


  get shop(): Shops {
    return this._shop;
  }

  set shop(value: Shops) {
    this._shop = value;
  }

  get shopList(): Shops[] {
    return this._shopList;
  }

  set shopList(value: Shops[]) {
    this._shopList = value;
  }

  get constShopList(): Shops[] {
    return this._constShopList;
  }

  set constShopList(value: Shops[]) {
    this._constShopList = value;
  }

  get nbCols(): number {
    return this._nbCols;
  }

  set nbCols(value: number) {
    this._nbCols = value;
  }

  get userEmail(): string {
    return this._userEmail;
  }

  set userEmail(value: string) {
    this._userEmail = value;
  }

  get pageEvent(): PageEvent {
    return this._pageEvent;
  }

  set pageEvent(value: PageEvent) {
    this._pageEvent = value;
  }

  get detailsDialogRef(): MatDialogRef<DetailsDialogComponent> {
    return this._detailsDialogRef;
  }

  set detailsDialogRef(value: MatDialogRef<DetailsDialogComponent>) {
    this._detailsDialogRef = value;
  }

  get length(): number {
    return this._length;
  }

  set length(value: number) {
    this._length = value;
  }

  get pageSize(): number {
    return this._pageSize;
  }

  set pageSize(value: number) {
    this._pageSize = value;
  }

  get pageSizeOptions(): number[] {
    return this._pageSizeOptions;
  }

  set pageSizeOptions(value: number[]) {
    this._pageSizeOptions = value;
  }

  get from(): number {
    return this._from;
  }

  set from(value: number) {
    this._from = value;
  }

  get to(): number {
    return this._to;
  }

  set to(value: number) {
    this._to = value;
  }


  get like(): boolean[] {
    return this._like;
  }

  set like(value: boolean[]) {
    this._like = value;
  }

  get dislike(): boolean[] {
    return this._dislike;
  }

  set dislike(value: boolean[]) {
    this._dislike = value;
  }
}
