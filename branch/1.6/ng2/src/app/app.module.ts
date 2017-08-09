import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule} from '@angular/forms';
import { HttpModule, XHRBackend, RequestOptions, Http} from '@angular/http';

import './rxjs-extensions';

import { FileUploadModule } from 'ng2-file-upload/ng2-file-upload';
import { DropdownModule } from 'ng2-bootstrap'

import { CoreModule } from './core/core.module';
import { ConfirmModule } from './confirm/confirm-modal.module';
import { UploaderModule } from './uploader/uploader.module';

import { AppComponent } from './app.component';
import { AppRoutingModule, routedComponents } from './app-routing.module';

import { AutoCompleteDirective } from './autocomplete/auto-complete.directive';
import { AutoCompleteComponent } from './autocomplete/auto-complete.component';

import { DatasetService } from './service/dataset.service';
import { CategoryService } from './service/category.service';

@NgModule({
  imports: [
    BrowserModule,
    FormsModule,
    AppRoutingModule,
    HttpModule,
    FileUploadModule,
    DropdownModule.forRoot(),
    CoreModule,
    ConfirmModule,
    UploaderModule
  ],
  declarations: [
	// Global components
    AppComponent,
    
    AutoCompleteDirective,
    AutoCompleteComponent,

    // Routing components
    routedComponents
  ],
  providers: [
    DatasetService,
    CategoryService
  ],
  bootstrap: [AppComponent],
  entryComponents: [AutoCompleteComponent]
})
export class AppModule { }
