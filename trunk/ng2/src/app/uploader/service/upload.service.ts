import { Injectable } from '@angular/core';
import { Headers, Http, Response, URLSearchParams } from '@angular/http';

import { Observable } from 'rxjs/Observable';
import 'rxjs/add/operator/toPromise';

import { EventService, BasicService } from '../../core/service/core.service';
import { EventHttpService } from '../../core/service/event-http.service';

import { Sheet, Workbook, GeoSynonym, ClassifierSynonym, Entity, DatasetResponse } from '../uploader-model';

import { Pair } from '../../model/pair';
import { Category, BasicCategory } from '../../model/category';

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
  
//   legacyImportFromVault(vaultId: String): Promise<Response> {
//     
//     return this.http
//       .get(acp + 'dss.vector.solutions.generator.ExcelController.excelImportFromVault', {vaultId: vaultId})
//       .toPromise()
//       .then((response: any) => {
//       })
//       .catch(this.handleError.bind(this));
//   }
  
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
    
  createTermSynonym(termId: string, label: string): Promise<ClassifierSynonym> {
    let headers = new Headers({
      'Content-Type': 'application/json'
    });    
    
    let data = JSON.stringify({termId: termId, label: label });
    
    return this.ehttp
    .post(acp + '/uploader/createTermSynonym', data, {headers: headers})
    .toPromise() 
    .then((response: any) => {
      return response.json() as ClassifierSynonym;
    })      
    .catch(this.handleError.bind(this));
  }
  
  deleteTermSynonym(synonymId: string): Promise<Response> {
    let headers = new Headers({
      'Content-Type': 'application/json'
    });    
    
    let data = JSON.stringify({synonymId: synonymId});
    
    return this.ehttp
    .post(acp + '/uploader/deleteTermSynonym', data, {headers: headers})
    .toPromise() 
    .catch(this.handleError.bind(this));    
  }
  
  createTerm(label: string, parentId: string): Promise<BasicCategory> {
    let headers = new Headers({
      'Content-Type': 'application/json'
    });    
    
    let data = JSON.stringify({parentId: parentId, label: label });
    
    return this.ehttp
    .post(acp + '/uploader/createTerm', data, {headers: headers})
    .toPromise() 
    .then((response: any) => {
      return response.json() as ClassifierSynonym;
    })      
    .catch(this.handleError.bind(this));
  }
  
  deleteTerm(termId: string): Promise<Response> {
    let headers = new Headers({
      'Content-Type': 'application/json'
    });    
    
    let data = JSON.stringify({termId: termId});
    
    return this.ehttp
    .post(acp + '/uploader/deleteTerm', data, {headers: headers})
    .toPromise() 
    .catch(this.handleError.bind(this));    
  }
  
  
}
