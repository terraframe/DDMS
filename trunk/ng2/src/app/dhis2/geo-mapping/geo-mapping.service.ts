import { Injectable } from '@angular/core';
import { Headers, Http, Response, URLSearchParams } from '@angular/http';

import 'rxjs/add/operator/toPromise';

import { EventService, BasicService } from '../../core/service/core.service';
import { EventHttpService } from '../../core/service/event-http.service';
import { AnalyticsService } from '../../core/service/analytics.service';


import { GeoMapping, UniversalMapping, OrgLevel } from './geo-mapping'

declare var acp: any;

@Injectable()
export class GeoMappingService extends BasicService {

  constructor(service: EventService, private ehttp: EventHttpService, private http: Http, private analyticsService: AnalyticsService) { super(service); }

  getRoots(): Promise<{roots:GeoMapping[],mappings:UniversalMapping[],levels:OrgLevel[]}> {
    let headers = new Headers({
      'Content-Type': 'application/json'
    });  
    
    return this.ehttp
      .post(acp + '/dhis2/roots', JSON.stringify({}), {headers: headers})
      .toPromise()
      .then(response => {
          
    	this.analyticsService.pushAalyticsTrackingTagEvent("SUCCESS", "/dhis2/roots", "post", {});

        return response.json() as {roots:GeoMapping[],mappings:UniversalMapping[],levels:OrgLevel[]};
      })
      .catch(e => {
    		  this.analyticsService.pushAalyticsTrackingTagEvent("FAILURE", "/dhis2/roots", "post", {});
    		  this.handleError.bind(this)
      });      
  }    
  
  getChildren(parentId:string): Promise<GeoMapping[]> {
    let headers = new Headers({
      'Content-Type': 'application/json'
    });  
    
    return this.ehttp
    .post(acp + '/dhis2/children', JSON.stringify({parentId:parentId}), {headers: headers})
    .toPromise()
    .then(response => {
      
      this.analyticsService.pushAalyticsTrackingTagEvent("SUCCESS", "/dhis2/children", "post", {parentId:parentId});

      return response.json() as {id:string, label:string}[];
    })
    .catch(e => {
    		this.analyticsService.pushAalyticsTrackingTagEvent("FAILURE", "/dhis2/children", "post", {parentId:parentId});
    		this.handleError.bind(this)
    });      
  }    
  
  search(text:String, geoId:String): Promise<{data:string, text:string}[]> {
    let headers = new Headers({
      'Content-Type': 'application/json'
    });  
    
    return this.http
    .post(acp + '/dhis2/search', JSON.stringify({text:text, geoId:geoId}), {headers: headers})
    .toPromise()
    .then(response => {
    	
      this.analyticsService.pushAalyticsTrackingTagEvent("SUCCESS", "/dhis2/search", "post", {text:text, geoId:geoId});

      return response.json() as {data:string, text:string}[];
    })    
    .catch(e => {
    		this.analyticsService.pushAalyticsTrackingTagEvent("FAILURE", "/dhis2/search", "post", {text:text, geoId:geoId});
    		this.handleError.bind(this)
    });      
  }    
  
  apply(mapping:GeoMapping): Promise<GeoMapping> {
    let headers = new Headers({
      'Content-Type': 'application/json'
    });  
    
    return this.ehttp
    .post(acp + '/dhis2/apply-geo-mapping', JSON.stringify({mapping:mapping}), {headers: headers})
    .toPromise()
    .then(response => {
    	
      this.analyticsService.pushAalyticsTrackingTagEvent("SUCCESS", "/dhis2/apply-geo-mapping", "post", {mapping:mapping});

      return response.json() as GeoMapping;
    })    
    .catch(e => {
    		this.analyticsService.pushAalyticsTrackingTagEvent("FAILURE", "/dhis2/apply-geo-mapping", "post", {mapping:mapping});

    		this.handleError.bind(this)
    });      
  }    
  
  applyLevelMapping(mapping:UniversalMapping): Promise<UniversalMapping> {
    let headers = new Headers({
      'Content-Type': 'application/json'
    });  
    
    return this.ehttp
    .post(acp + '/dhis2/apply-level-mapping', JSON.stringify({mapping:mapping}), {headers: headers})
    .toPromise()
    .then(response => {
    	
      this.analyticsService.pushAalyticsTrackingTagEvent("SUCCESS", "/dhis2/apply-level-mapping", "post", {mapping:mapping});

      return response.json() as UniversalMapping;
    })    
    .catch(e => {
    		this.analyticsService.pushAalyticsTrackingTagEvent("FAILURE", "/dhis2/apply-level-mapping", "post", {mapping:mapping});

    		this.handleError.bind(this)
    });      
  }    
}
