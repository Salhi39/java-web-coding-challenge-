import { Component, OnInit} from '@angular/core';
import {MatDialogRef} from '@angular/material';


@Component({
  selector: 'app-details-dialog',
  templateUrl: './details-dialog.component.html',
  styleUrls: ['./details-dialog.component.css']
})
export class DetailsDialogComponent implements OnInit {

  title: string = 'Google Maps Addeed Successfully';

  lat: number = 28.38 ;
  lng: number = 77.12;

  constructor(private dialogRef: MatDialogRef<DetailsDialogComponent>) {}

  ngOnInit() {
  }



  onCloseConfirm() {
    this.dialogRef.close('Confirm');
  }
  onCloseCancel() {
    this.dialogRef.close('Cancel');
  }
}
