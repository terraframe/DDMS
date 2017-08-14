import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule} from '@angular/forms';
import { HttpModule} from '@angular/http';

import { ModalModule } from 'ngx-bootstrap/modal'
import { TreeModule } from 'angular-tree-component';

import { CoreModule } from '../core/core.module';
import { ConfirmModule } from '../confirm/confirm-modal.module';

import { Dhis2Component } from './dhis2.component';

import { ConnectionService } from './connection/connection.service';
import { QueryMappingService } from './query-mapping/query-mapping.service';
import { GeoMappingService } from './geo-mapping/geo-mapping.service';

import { QueryMappingComponent } from './query-mapping/query-mapping.component';

import { Dhis2RoutingModule, routedComponents } from './dhis2-routing.module';


@NgModule({
  imports: [
    BrowserModule,
    FormsModule,
    HttpModule,
    ModalModule.forRoot(),
    TreeModule,    
    Dhis2RoutingModule,    
    CoreModule,
    ConfirmModule,
  ],
  declarations: [
    Dhis2Component,
    QueryMappingComponent,    
    // Routing components
    routedComponents    
  ],
  providers: [
    ConnectionService,
    QueryMappingService,
    GeoMappingService    
  ],
  entryComponents: [
    QueryMappingComponent,
  ],    
  bootstrap: [Dhis2Component]
})
export class Dhis2Module { }
