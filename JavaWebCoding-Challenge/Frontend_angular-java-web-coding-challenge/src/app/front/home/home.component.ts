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
  shopsList = [];

  constructor(private ap: ApiService) {
  }

//  apis = this.apiService;
  result: any;


  ngOnInit() {
   /* var s = {};
    this.result = this.ap.getAllShops().subscribe(
      val => {
        this.result = val;
        s = val;
      });

     var shops1 = new Shops();
     var shops2 = new Shops();
     var shops3 = new Shops();
     var shops4 = new Shops();

     shops1.setCity("Rabat");
     shops1.setName("Shop 1");
     shops1.setEmail("s1@gmail.com");
     shops1.setPicture("http://placehold.it/150x150");

     shops2.setCity("Rabat");
     shops2.setName("Shop 2");
     shops2.setEmail("s2@gmail.com");
     shops2.setPicture("http://placehold.it/150x150");

     shops3.setCity("Rabat");
     shops3.setName("Shop 3");
     shops3.setEmail("s3@gmail.com");
     shops3.setPicture("http://placehold.it/150x150");

     shops4.setCity("Rabat");
     shops4.setName("Shop 4");
     shops4.setEmail("s4@gmail.com");
     shops4.setPicture("http://placehold.it/150x150");

     this.shopsList.push(shops1);
     this.shopsList.push(shops2);
     this.shopsList.push(shops3);
     this.shopsList.push(shops4);*/
/*
     this.result = this.ap.getAllShops().subscribe(val => {this.result = val;  });


     /*for(let item of this.result){
       const t = (Shops) item;
       this.result = t.getId();

     }
 */
    /*// Make the HTTP request:
    //this.http.get('http://localhost:8085').subscribe(data => {
      // Read the result field from the JSON response.
      //this.result = data[0];


      /!*
      this.results.forEach(item => {
        this.results = );
      });
      foreach (val in this.result) {
        statement
      }*!/

    });*/
  }


}
