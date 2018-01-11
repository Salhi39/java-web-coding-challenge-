import {Component, Input, OnInit} from '@angular/core';
import {Shops} from '../../server/model/shops';

import { MatDialog, MatDialogRef } from '@angular/material';
import {DetailsDialogComponent} from '../details-dialog/details-dialog.component';
import {ApiService} from '../../server/rest-api/api.service';



@Component({
  selector: 'app-shop',
  templateUrl: './shop.component.html',
  styleUrls: ['./shop.component.css']
})
export class ShopComponent implements OnInit {

  @Input() shop: Shops;

  shopList: Shops[];

  constructor(private apiService: ApiService ,private dialog: MatDialog) {
    this.shopList = [];
  }


  ngOnInit() {
      var s = {};
      this.apiService.getAllShops().subscribe(
        val => {
          for(let elm of val){
            this.shop = new Shops();
            this.shop.setId(elm['_id']);
            this.shop.setCity(elm['city']);
            this.shop.setEmail(elm['email']);
            this.shop.setPicture(elm['picture']);
            this.shop.setLocation(elm['picture']['coordinates']);
            this.shop.setName(elm['name']);
            this.shopList.push(this.shop);
          }
        });
  }

  detailtsDialogRef: MatDialogRef<DetailsDialogComponent>;


  openDialog(){
    this.detailtsDialogRef = this.dialog.open(DetailsDialogComponent, {
      width: '100%',
      height: '90%',
      data: 'This text is passed into the dialog!',
      hasBackdrop: false
    });
  }
}
