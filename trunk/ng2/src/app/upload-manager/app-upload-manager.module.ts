import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { LocationStrategy, HashLocationStrategy } from '@angular/common';
import { FormsModule} from '@angular/forms';
import { HttpModule} from '@angular/http';

import { ModalModule } from 'ngx-bootstrap/modal'
import { TypeaheadModule } from 'ngx-bootstrap/typeahead';

import { CoreModule } from '../core/core.module';

import { AppUploadManagerComponent } from './app-upload-manager.component';

import { UploadManagerComponent } from './widget/upload-manager.component';

import { UploadManagerService } from './widget/upload-manager.service';

import { RouterModule, Routes } from '@angular/router';

const appRoutes: Routes = [
						   {
						     path: '',
						     redirectTo: '/manager',
						     pathMatch: 'full'
						   },
                           { path: 'manager', component: UploadManagerComponent }
                         ];

@NgModule({
  imports: [
    RouterModule.forRoot(appRoutes),
    BrowserModule,
    FormsModule,
    HttpModule,
    ModalModule.forRoot(),
    TypeaheadModule.forRoot(),
    CoreModule
  ],
  declarations: [
    AppUploadManagerComponent,
    UploadManagerComponent,
  ],
  providers: [
    UploadManagerService,
    {provide: LocationStrategy, useClass: HashLocationStrategy}
  ],
  entryComponents: [
  ],    
  bootstrap: [AppUploadManagerComponent]
})
export class AppUploadManagerModule { }

export const routedComponents = [UploadManagerComponent];
