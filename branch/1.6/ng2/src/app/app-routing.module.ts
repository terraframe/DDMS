import { NgModule, Injectable, Inject } from '@angular/core';
import { LocationStrategy, HashLocationStrategy } from '@angular/common';
import { Routes, RouterModule, Resolve, ActivatedRouteSnapshot, RouterStateSnapshot } from '@angular/router';
import { Observable } from 'rxjs/Observable';

import { DatasetsComponent } from './datasets/datasets.component';
import { DatasetDetailComponent, DatasetResolver} from './datasets/dataset-detail.component';

declare var acp: any;

const routes: Routes = [
  {
    path: '',
    redirectTo: '/datasets',
    pathMatch: 'full'
  },
  {
    path: 'datasets',
    component: DatasetsComponent
  },
  {
    path: 'dataset/:id',
    component: DatasetDetailComponent,
    resolve: {
      dataset: DatasetResolver
    }    
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
  providers: [{provide: LocationStrategy, useClass: HashLocationStrategy}, DatasetResolver]
})
export class AppRoutingModule { }

export const routedComponents = [DatasetsComponent, DatasetDetailComponent];
