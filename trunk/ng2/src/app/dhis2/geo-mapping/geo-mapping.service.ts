import { Injectable } from '@angular/core';
import { Headers, Http, Response, URLSearchParams } from '@angular/http';

import 'rxjs/add/operator/toPromise';

import { EventService, BasicService } from '../../core/service/core.service';
import { EventHttpService } from '../../core/service/event-http.service';

import { GeoMapping, UniversalMapping, OrgLevel } from './geo-mapping'

declare var acp: any;

@Injectable()
export class GeoMappingService extends BasicService {

  constructor(service: EventService, private ehttp: EventHttpService, private http: Http) { super(service); }

  getRoots(): Promise<{roots:GeoMapping[],mappings:UniversalMapping[],levels:OrgLevel[]}> {
    let headers = new Headers({
      'Content-Type': 'application/json'
    });  
    
    return this.ehttp
      .post(acp + '/dhis2/roots', JSON.stringify({}), {headers: headers})
      .toPromise()
      .then(response => {
        return response.json() as {roots:GeoMapping[],mappings:UniversalMapping[],levels:OrgLevel[]};
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
  
  search(text:String, geoId:String): Promise<{data:string, text:string}[]> {
    let headers = new Headers({
      'Content-Type': 'application/json'
    });  
    
    return this.http
    .post(acp + '/dhis2/search', JSON.stringify({text:text, geoId:geoId}), {headers: headers})
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
  
  applyLevelMapping(mapping:UniversalMapping): Promise<UniversalMapping> {
    let headers = new Headers({
      'Content-Type': 'application/json'
    });  
    
    return this.ehttp
    .post(acp + '/dhis2/apply-level-mapping', JSON.stringify({mapping:mapping}), {headers: headers})
    .toPromise()
    .then(response => {
      return response.json() as UniversalMapping;
    })    
    .catch(this.handleError.bind(this));      
  }    
}
