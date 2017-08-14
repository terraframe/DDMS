import { NgModule, Injectable, Inject } from '@angular/core';
import { LocationStrategy, HashLocationStrategy } from '@angular/common';
import { Routes, RouterModule, Resolve, ActivatedRouteSnapshot, RouterStateSnapshot } from '@angular/router';
import { Observable } from 'rxjs/Observable';

import {ConnectionComponent} from './connection/connection.component'
import {QueryMappingsComponent} from './query-mapping/query-mappings.component'
import {GeoMappingComponent} from './geo-mapping/geo-mapping.component'

declare var acp: any;

const routes: Routes = [
  {
    path: '',
    redirectTo: '/connection',
    pathMatch: 'full'
  },
  {
    path: 'connection',
    component: ConnectionComponent
  },
  {
    path: 'export',
    component: QueryMappingsComponent
  },
  {
    path: 'mapping',
    component: GeoMappingComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
  providers: [{provide: LocationStrategy, useClass: HashLocationStrategy}]
})
export class Dhis2RoutingModule { }

export const routedComponents = [ConnectionComponent, QueryMappingsComponent, GeoMappingComponent];
