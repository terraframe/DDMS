import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule} from '@angular/forms';
import { HttpModule, XHRBackend, RequestOptions, Http} from '@angular/http';

import './rxjs-extensions';

import { FileUploadModule } from 'ng2-file-upload/ng2-file-upload';
import { BsDropdownModule } from 'ngx-bootstrap/dropdown';

import { CoreModule } from './core/core.module';
import { ConfirmModule } from './confirm/confirm-modal.module';
import { UploaderModule } from './uploader/uploader.module';

import { AppComponent } from './app.component';
import { AppRoutingModule, routedComponents } from './app-routing.module';

import { DatasetService } from './service/dataset.service';
import { CategoryService } from './service/category.service';

@NgModule({
  imports: [
    BrowserModule,
    FormsModule,
    AppRoutingModule,
    HttpModule,
    FileUploadModule,
    BsDropdownModule.forRoot(),
    CoreModule,
    ConfirmModule,
    UploaderModule
  ],
  declarations: [
	// Global components
    AppComponent,
    
    // Routing components
    routedComponents
  ],
  providers: [
    DatasetService,
    CategoryService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
