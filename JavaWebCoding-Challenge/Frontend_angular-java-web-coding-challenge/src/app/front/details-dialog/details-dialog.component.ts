import {Component, Inject, OnInit} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialogRef} from '@angular/material';


@Component({
  selector: 'app-details-dialog',
  templateUrl: './details-dialog.component.html',
  styleUrls: ['./details-dialog.component.css']
})
export class DetailsDialogComponent implements OnInit {

  title: string = 'Google Maps Addeed Successfully';

  lat: number;
  lng: number;

  constructor(@Inject(MAT_DIALOG_DATA) public data: any, private dialogRef: MatDialogRef<DetailsDialogComponent>) {
  }

  ngOnInit() {
    this.lat = this.data['location'][1];
    this.lng = this.data['location'][0];
  }


  onCloseConfirm() {
    this.dialogRef.close('Confirm');
  }

  onCloseCancel() {
    this.dialogRef.close('Cancel');
  }
}
