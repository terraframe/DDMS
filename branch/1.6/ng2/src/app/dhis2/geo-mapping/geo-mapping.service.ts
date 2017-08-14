import { Injectable } from '@angular/core';
import { Headers, Http, Response, URLSearchParams } from '@angular/http';

import 'rxjs/add/operator/toPromise';

import { EventService, BasicService } from '../../core/service/core.service';
import { EventHttpService } from '../../core/service/event-http.service';

import { GeoMapping } from './geo-mapping';

declare var acp: any;

@Injectable()
export class GeoMappingService extends BasicService {

  constructor(service: EventService, private ehttp: EventHttpService, private http: Http) { super(service); }

  getRoots(): Promise<GeoMapping[]> {
    let headers = new Headers({
      'Content-Type': 'application/json'
    });  
    
    return this.ehttp
      .post(acp + '/dhis2/roots', JSON.stringify({}), {headers: headers})
      .toPromise()
      .then(response => {
        return response.json() as GeoMapping[];
      })
      .catch(this.handleError.bind(this));      
  }    
  
  getChildren(parentId:string): Promise<GeoMapping[]> {
    let headers = new Headers({
      'Content-Type': 'application/json'
    });  
    
    return this.ehttp
    .post(acp + '/dhis2/children', JSON.stringify({parentId:parentId}), {headers: headers})
    .toPromise()
    .then(response => {
      return response.json() as {id:string, label:string}[];
    })
    .catch(this.handleError.bind(this));      
  }    
  
  search(text:String): Promise<{data:string, text:string}[]> {
    let headers = new Headers({
      'Content-Type': 'application/json'
    });  
    
    return this.http
    .post(acp + '/dhis2/search', JSON.stringify({text:text}), {headers: headers})
    .toPromise()
    .then(response => {
      return response.json() as {data:string, text:string}[];
    })    
    .catch(this.handleError.bind(this));      
  }    
  
  apply(mapping:GeoMapping): Promise<GeoMapping> {
    let headers = new Headers({
      'Content-Type': 'application/json'
    });  
    
    return this.ehttp
    .post(acp + '/dhis2/apply-geo-mapping', JSON.stringify({mapping:mapping}), {headers: headers})
    .toPromise()
    .then(response => {
      return response.json() as GeoMapping;
    })    
    .catch(this.handleError.bind(this));      
  }    
}
