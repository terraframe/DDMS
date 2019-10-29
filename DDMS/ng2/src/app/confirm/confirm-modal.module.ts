import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule} from '@angular/forms';

import { CoreModule } from '../core/core.module';

import { ConfirmModalDirective } from './confirm-modal.directive';
import { ConfirmModalComponent } from './confirm-modal.component';
import { ConfirmService } from './confirm-modal.service';

@NgModule({
  imports: [
    BrowserModule,
    FormsModule,
    CoreModule    
  ],
  declarations: [
    ConfirmModalDirective,
    ConfirmModalComponent,
  ],
  providers: [
    ConfirmService
  ],
  exports:[
    ConfirmModalDirective,
    ConfirmModalComponent
  ]
})
export class ConfirmModule { }
