import { NgModule } from '@angular/core';
import { MatSliderModule } from '@angular/material';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';

@NgModule({
  imports: [
    BrowserAnimationsModule,
    MatSliderModule,
  ],
  exports: [
    BrowserAnimationsModule,
    MatSliderModule,
  ],
})
export class AppMaterialModule { }
