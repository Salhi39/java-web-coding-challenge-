import { NgModule} from '@angular/core';
import { CommonModule } from '@angular/common';
import { AgmCoreModule } from '@agm/core';

import {
  MatToolbarModule,
  MatCardModule,
  MatInputModule,
  MatButtonModule,
  MatIconModule,
  MatGridListModule,
  MatDialogModule
} from '@angular/material';




@NgModule({
  imports: [CommonModule],
  exports: [
    MatToolbarModule,
    MatCardModule,
    MatInputModule,
    MatButtonModule,
    MatIconModule,
    MatGridListModule,
    MatIconModule,
    MatDialogModule
  ]
})
export class AppMaterialModule {}
