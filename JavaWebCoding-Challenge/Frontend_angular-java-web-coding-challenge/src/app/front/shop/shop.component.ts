import {Component, EventEmitter, Input, OnInit, Output, ViewChild} from '@angular/core';
import {Shops} from '../../server/model/shops';

import {MatDialog, MatDialogRef, MatPaginator, PageEvent} from '@angular/material';
import {DetailsDialogComponent} from '../details-dialog/details-dialog.component';
import {ApiService} from '../../server/rest-api/api.service';



@Component({
  selector: 'app-shop',
  templateUrl: './shop.component.html',
  styleUrls: ['./shop.component.css']
})



export class ShopComponent implements OnInit {

  // shop object
  @Input() private  shop: Shops;
  // shops that will be shown ( depend of paginator )
  private shopList: Shops[];
  // all shops
  private constShopList: Shops[];

  // Number of column on the page
  private nbCols: number;


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


  constructor(private apiService: ApiService ,private dialog: MatDialog) {
    this.shopList = [];
    this.constShopList = [];
  }


  ngOnInit() {
    // init grid column
      this.nbCols = this.adaptColumn(window.screen.width);

      // get all shops
      this.apiService.getAllShops().subscribe(
        val => {
          this.length = val.length;
          for (let elm of val){
            this.shop = new Shops();
            this.shop.setId(elm['_id']);
            this.shop.setCity(elm['city']);
            this.shop.setEmail(elm['email']);
            this.shop.setPicture(elm['picture']);
            this.shop.setLocation(elm['picture']['coordinates']);
            this.shop.setName(elm['name']);
            this.constShopList.push(this.shop);
          }
          // in the first time only 10 shops will be display
          let max = 10;
          // if there is less than 10 shops in my database
          // show me what I have
          if(this.constShopList.length<10){
            max = this.constShopList.length;
          }
          // 10 items
          this.shopList = this.constShopList.slice(0, max);
        });

  }



  // open Dialog by given location
  openDialog(location) {
    this.detailtsDialogRef = this.dialog.open(DetailsDialogComponent, {
      width: '100%',
      height: '90%',
      data: {location: location},
      hasBackdrop: false
    });
  }




  /**
   * Set the paginator after the view init since this component will
   * be able to query its view for the initialized paginator.
   */
  updatePagination() {
    console.log('EVENT P.SIZE === ' + this.pageEvent.pageSize);
    console.log('EVENT P.INDEX === ' + this.pageEvent.pageIndex);
    console.log('EVENT P.LENGTH === ' + this.pageEvent.length);

    // to avoid the overflowing of the page length
    let lastValue = this.to;
    console.log('last  === ' + lastValue);
    console.log('----------------');

    // calculate start and end of the new interval
    this.from =	this.pageEvent.pageSize * this.pageEvent.pageIndex;
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
    console.log(element);
    this.nbCols = this.adaptColumn(element);
  }


  // adapt my column by giving the width of the page
  adaptColumn (width){
    if (width > 1200) return 4;
    else if (width > 900) return 3;
    else if (width > 630) return 2;
    else return 1;
  }

}
