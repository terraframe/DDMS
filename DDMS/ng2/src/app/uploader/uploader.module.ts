import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule} from '@angular/forms';
import { HttpModule} from '@angular/http';

import { FileUploadModule } from 'ng2-file-upload/ng2-file-upload';
import { BsDropdownModule } from 'ngx-bootstrap/dropdown'

import { CoreModule } from '../core/core.module';
import { ConfirmModule } from '../confirm/confirm-modal.module';

// Upload wizard imports
import { UploadWizardComponent } from './upload-wizard.component';
import { PagingComponent } from './paging.component';
import { MatchInitialPageComponent } from './match-initial-page.component';
import { MatchPageComponent } from './match-page.component';
import { BeginningInfoPageComponent } from './beginning-info-page.component';
import { NamePageComponent } from './name-page.component';
import { AttributesPageComponent } from './attributes-page.component';
import { LocationPageComponent } from './location-page.component';
import { CoordinatePageComponent } from './coordinate-page.component';
import { SummaryPageComponent } from './summary-page.component';
import { GeoValidationPageComponent } from './geo-validation-page.component';
import { GeoValidationProblemComponent } from './geo-validation-problem.component';
import { CategoryValidationPageComponent } from './category-validation-page.component';
import { CategoryValidationProblemComponent } from './category-validation-problem.component';

import { UploadService } from './service/upload.service';
import { NavigationService } from './service/navigation.service';

@NgModule({
  imports: [
    BrowserModule,
    FormsModule,
    HttpModule,
    FileUploadModule,
    BsDropdownModule.forRoot(),
    CoreModule,
    ConfirmModule    
  ],
  declarations: [
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
  ],
  providers: [
    NavigationService,	  
    UploadService
  ],
  exports: [
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
  ]  
})
export class UploaderModule { }
