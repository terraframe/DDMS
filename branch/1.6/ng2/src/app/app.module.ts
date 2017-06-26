import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule} from '@angular/forms';
import { HttpModule, XHRBackend, RequestOptions, Http} from '@angular/http';

import './rxjs-extensions';

import { FileUploadModule } from 'ng2-file-upload/ng2-file-upload';
import { DropdownModule } from 'ng2-bootstrap'

import { AppComponent } from './app.component';
import { AppRoutingModule, routedComponents } from './app-routing.module';

import { LocalizeComponent } from './core/localize.component';
import { LoadingBarComponent } from './core/loading-bar.component';
import { MessageComponent } from './core/message.component';
import { AsyncValidator } from './core/async-validator.directive';
import { FunctionValidator } from './core/function-validator.directive';
import { KeysPipe } from './core/keys.pipe';
import { LocalizePipe } from './core/localize.pipe';
import { DateFieldDirective } from './core/date-field.directive';

import { AutoCompleteDirective } from './autocomplete/auto-complete.directive';
import { AutoCompleteComponent } from './autocomplete/auto-complete.component';

import { ConfirmModalDirective } from './confirm/confirm-modal.directive';
import { ConfirmModalComponent } from './confirm/confirm-modal.component';
import { ConfirmService } from './confirm/confirm-modal.service';

import { EventService, IdService} from './service/core.service';
import { LocalizationService } from './service/localization.service';

import { DatasetService } from './service/dataset.service';
import { CategoryService } from './service/category.service';

// Upload wizard imports
import { NavigationService } from './uploader/navigation.service';
import { UploadWizardComponent } from './uploader/upload-wizard.component';
import { PagingComponent } from './uploader/paging.component';
import { MatchInitialPageComponent } from './uploader/match-initial-page.component';
import { MatchPageComponent } from './uploader/match-page.component';
import { BeginningInfoPageComponent } from './uploader/beginning-info-page.component';
import { NamePageComponent } from './uploader/name-page.component';
import { AttributesPageComponent } from './uploader/attributes-page.component';
import { LocationPageComponent } from './uploader/location-page.component';
import { CoordinatePageComponent } from './uploader/coordinate-page.component';
import { SummaryPageComponent } from './uploader/summary-page.component';
import { GeoValidationPageComponent } from './uploader/geo-validation-page.component';
import { GeoValidationProblemComponent } from './uploader/geo-validation-problem.component';
import { CategoryValidationPageComponent } from './uploader/category-validation-page.component';
import { CategoryValidationProblemComponent } from './uploader/category-validation-problem.component';
import { UploadService } from './service/upload.service';

import { EventHttpService } from './service/event-http.service';


@NgModule({
  imports: [
    BrowserModule,
    FormsModule,
    AppRoutingModule,
    HttpModule,
    FileUploadModule,
    DropdownModule.forRoot()
  ],
  declarations: [
	// Global components
    AppComponent,
    LocalizeComponent,
    LoadingBarComponent,
    MessageComponent,
    AsyncValidator,
    FunctionValidator,
    KeysPipe,
    LocalizePipe,
    
    AutoCompleteDirective,
    AutoCompleteComponent,

    DateFieldDirective,
    ConfirmModalDirective,
    ConfirmModalComponent,
    
    // Upload Wizard components
    UploadWizardComponent,
    PagingComponent,
    MatchInitialPageComponent,
    MatchPageComponent,
    BeginningInfoPageComponent,
    NamePageComponent,
    AttributesPageComponent,
    LocationPageComponent,
    CoordinatePageComponent,
    SummaryPageComponent,
    GeoValidationPageComponent,
    GeoValidationProblemComponent,
    CategoryValidationPageComponent,
    CategoryValidationProblemComponent,
    
    // Routing components
    routedComponents
  ],
  providers: [
	LocalizationService,
	IdService,
    DatasetService,
    CategoryService,
    UploadService,
    NavigationService,
    ConfirmService,
    EventService,
    { 
      provide : EventHttpService,
      useFactory: (xhrBackend: XHRBackend, requestOptions: RequestOptions, service: EventService) => {
        return new EventHttpService(xhrBackend, requestOptions, service)
      },
      deps: [XHRBackend, RequestOptions, EventService]
    }   
  ],
  bootstrap: [AppComponent],
  entryComponents: [AutoCompleteComponent]
})
export class AppModule { }
