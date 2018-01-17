import {Component, OnInit} from '@angular/core';
import {Shops} from '../../server/model/shops';
import {ApiService} from '../../server/rest-api/api.service';
import {HttpClient} from '@angular/common/http';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  constructor() {
  }

  ngOnInit() {
  }


}
