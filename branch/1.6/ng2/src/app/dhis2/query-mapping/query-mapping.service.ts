import { Injectable } from '@angular/core';
import { Headers, Http, Response, URLSearchParams } from '@angular/http';

import 'rxjs/add/operator/toPromise';

import { EventService, BasicService } from '../../core/service/core.service';
import { EventHttpService } from '../../core/service/event-http.service';

import { QueryMapping } from './query-mapping';

import { ExportResults } from './export-results'

declare var acp: any;
declare var alert: any;

@Injectable()
export class QueryMappingService extends BasicService {

  constructor(service: EventService, private ehttp: EventHttpService, private http: Http) { super(service); }

  getAll(): Promise<QueryMapping[]> {
    let headers = new Headers({
      'Content-Type': 'application/json'
    });  
    
    return this.ehttp
      .post(acp + '/dhis2/mappings', JSON.stringify({}), {headers: headers})
      .toPromise()
      .then(response => {
        return response.json() as QueryMapping[];
      })
      .catch(this.handleError.bind(this));      
  }    
  
  newInstance(): Promise<{id:string, label:string}[]> {
    let headers = new Headers({
      'Content-Type': 'application/json'
    });  
    
    return this.ehttp
    .post(acp + '/dhis2/newInstance', JSON.stringify({}), {headers: headers})
    .toPromise()
    .then(response => {
      return response.json() as {id:string, label:string}[];
    })
    .catch(this.handleError.bind(this));      
  }    
  
  apply(mapping:QueryMapping): Promise<Response> {
    let headers = new Headers({
      'Content-Type': 'application/json'
    });  
    
    return this.ehttp
    .post(acp + '/dhis2/apply', JSON.stringify({mapping:mapping}), {headers: headers})
    .toPromise()
    .catch(this.handleError.bind(this));      
  }    
  
  remove(id:string): Promise<Response> {
    let headers = new Headers({
      'Content-Type': 'application/json'
    });  
    
    return this.ehttp
    .post(acp + '/dhis2/remove', JSON.stringify({id:id}), {headers: headers})
    .toPromise()
    .catch(this.handleError.bind(this));      
  }    
  
  
  xport(datasets:QueryMapping[], strategy:string): Promise<ExportResults> {
    let headers = new Headers({
      'Content-Type': 'application/json'
    });  
    
    return this.ehttp
     .post(acp + '/dhis2/xport', JSON.stringify({datasets:datasets, strategy:strategy}), {headers: headers})
     .toPromise()
     .then(response => {
       return response.json() as ExportResults;
     })
     .catch(this.handleError.bind(this));      
  }    
}
