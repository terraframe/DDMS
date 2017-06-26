import { Injectable } from '@angular/core';
import { Headers, Http, Response, URLSearchParams } from '@angular/http';

import { Observable } from 'rxjs/Observable';
import 'rxjs/add/operator/toPromise';

import { EventService, BasicService } from './core.service';
import { EventHttpService } from './event-http.service';

import { Pair } from '../model/pair';
import { Sheet, Workbook, GeoSynonym, ClassifierSynonym, Entity, DatasetResponse } from '../uploader/uploader-model';

declare var acp: any;

@Injectable()
export class UploadService extends BasicService {

  constructor(service: EventService, private ehttp: EventHttpService, private http: Http) { super(service); }

  getSavedConfiguration(id: string, sheetName: string): Promise<any> {
    
    let headers = new Headers({
      'Content-Type': 'application/json'
    });    
    
    let data = JSON.stringify({id:id, sheetName: sheetName});
    
    return this.ehttp
      .post(acp + '/uploader/getSavedConfiguration', data, {headers: headers})
      .toPromise() 
      .then((response: any) => {
        return response.json();
      })           
      .catch(this.handleError.bind(this));
  }
    
  cancelImport(workbook: Workbook): Promise<Response> {
    
    let headers = new Headers({
      'Content-Type': 'application/json'
    });    
    
    let data = JSON.stringify({configuration : workbook });
    
    return this.ehttp
      .post(acp + '/uploader/cancelImport', data, {headers: headers})
      .toPromise() 
      .catch(this.handleError.bind(this));
  }
  
  importData(workbook: Workbook): Promise<DatasetResponse> {
    let headers = new Headers({
      'Content-Type': 'application/json'
    });    
    
    let data = JSON.stringify({configuration : workbook });
    
    return this.ehttp
      .post(acp + '/uploader/importData', data, {headers: headers})
      .toPromise() 
      .then((response: any) => {
        return response.json() as DatasetResponse;
      })      
      .catch(this.handleError.bind(this));
  }
  
  getGeoEntitySuggestions(parentId: string, universalId: string, text: string, limit: string): Promise<Array<{ text: string, data: any }>> {
    
    let params: URLSearchParams = new URLSearchParams();
    params.set('parentId', parentId);
    params.set('universalId', universalId);    
    params.set('text', text);    
    params.set('limit', limit);    
    
    return this.http
      .get(acp + '/uploader/getGeoEntitySuggestions', {search: params})
      .toPromise()
      .then((response: any) => {
        return response.json() as Array<{ text: string, data: any }>;
      })
      .catch(this.handleError.bind(this));    
  }

  createGeoEntitySynonym(entityId: string, label: string): Promise<GeoSynonym> {
    let headers = new Headers({
      'Content-Type': 'application/json'
    });    
    
    let data = JSON.stringify({entityId: entityId, label: label });
    
    return this.ehttp
      .post(acp + '/uploader/createGeoEntitySynonym', data, {headers: headers})
      .toPromise() 
      .then((response: any) => {
        return response.json() as GeoSynonym;
      })      
      .catch(this.handleError.bind(this));
  }
  
  createGeoEntity(parentId: string, universalId: string, label: string): Promise<Entity> {
    let headers = new Headers({
      'Content-Type': 'application/json'
    });    
    
    let data = JSON.stringify({parentId: parentId, universalId: universalId, label: label });
    
    return this.ehttp
      .post(acp + '/uploader/createGeoEntity', data, {headers: headers})
      .toPromise() 
      .then((response: any) => {
        return response.json() as Entity;
      })      
      .catch(this.handleError.bind(this));    
  }
  
  deleteGeoEntity(entityId: string): Promise<Response> {
    let headers = new Headers({
      'Content-Type': 'application/json'
    });    
    
    let data = JSON.stringify({entityId: entityId});
    
    return this.ehttp
    .post(acp + '/uploader/deleteGeoEntity', data, {headers: headers})
    .toPromise() 
    .catch(this.handleError.bind(this));    
  }
  
  deleteGeoEntitySynonym(synonymId: string): Promise<Response> {
    let headers = new Headers({
      'Content-Type': 'application/json'
    });    
    
    let data = JSON.stringify({synonymId: synonymId});
    
    return this.ehttp
    .post(acp + '/uploader/deleteGeoEntitySynonym', data, {headers: headers})
    .toPromise() 
    .catch(this.handleError.bind(this));    
  }
    
  getClassifierSuggestions(mdAttributeId: string, text: string, limit: string): Promise<Array<{ text: string, data: any }>> {
    
    let params: URLSearchParams = new URLSearchParams();
    params.set('mdAttributeId', mdAttributeId);
    params.set('text', text);    
    params.set('limit', limit);    
  
    return this.http
      .get(acp + '/uploader/getClassifierSuggestions', {search: params})
      .toPromise()
      .then((response: any) => {
        return response.json() as Array<{ text: string, data: any }>;
      })
      .catch(this.handleError.bind(this));    
  }
  
  createClassifierSynonym(classifierId: string, label: string): Promise<ClassifierSynonym> {
    let headers = new Headers({
      'Content-Type': 'application/json'
    });    
    
    let data = JSON.stringify({classifierId: classifierId, label: label });
    
    return this.ehttp
    .post(acp + '/uploader/createClassifierSynonym', data, {headers: headers})
    .toPromise() 
    .then((response: any) => {
      return response.json() as ClassifierSynonym;
    })      
    .catch(this.handleError.bind(this));
  }
  
  deleteClassifierSynonym(synonymId: string): Promise<Response> {
    let headers = new Headers({
      'Content-Type': 'application/json'
    });    
    
    let data = JSON.stringify({synonymId: synonymId});
    
    return this.ehttp
    .post(acp + '/uploader/deleteClassifierSynonym', data, {headers: headers})
    .toPromise() 
    .catch(this.handleError.bind(this));    
  }
  
  
}
