import { Injectable } from '@angular/core';
import { Headers, Http, Response, URLSearchParams } from '@angular/http';

import 'rxjs/add/operator/toPromise';

import { EventService, BasicService } from './core.service';
import { EventHttpService } from './event-http.service';

import { Dataset, DatasetCollection, IndicatorField, DatasetAttribute } from '../model/dataset';
import { Pair } from '../model/pair';

declare var acp: any;

@Injectable()
export class DatasetService extends BasicService {

  constructor(service: EventService, private ehttp: EventHttpService, private http: Http) { super(service); }

  getDatasets(): Promise<DatasetCollection> {
    return this.ehttp
      .get(acp + '/dss.vector.solutions.kaleidoscope.DataSetController.getAll.mojo')
      .toPromise()
      .then(response => {
        return response.json() as DatasetCollection;
      })
      .catch(this.handleError.bind(this));
  }
  
  edit(id : string): Promise<Dataset> {

    let headers = new Headers({
      'Content-Type': 'application/json'
    });  
  
    return this.ehttp
      .post(acp + '/dss.vector.solutions.kaleidoscope.DataSetController.edit.mojo', JSON.stringify({id:id}), {headers: headers})
      .toPromise()
      .then((response: any) => {
        return response.json() as Dataset;
      })
      .catch(this.handleError.bind(this));      
  }
  
  unlock(dataset: Dataset): Promise<Response> {
    let headers = new Headers({
      'Content-Type': 'application/json'
    });    
    
    return this.ehttp
      .post(acp + '/dss.vector.solutions.kaleidoscope.DataSetController.cancel.mojo', JSON.stringify({id:dataset.id}), {headers: headers})
      .toPromise()
      .catch(this.handleError.bind(this));
  }
  
  apply(dataset: Dataset): Promise<Dataset> {
    let headers = new Headers({
      'Content-Type': 'application/json'
    });    
    
    return this.ehttp
    .post(acp + '/dss.vector.solutions.kaleidoscope.DataSetController.applyDatasetUpdate.mojo', JSON.stringify({datasetJSON:dataset}), {headers: headers})
    .toPromise() 
    .then((response: any) => {
      return response.json() as Dataset;
    })          
    .catch(this.handleError.bind(this));
  }
  
  addIndicator(datasetId:string, indicator:IndicatorField): Promise<DatasetAttribute> {
    let headers = new Headers({
      'Content-Type': 'application/json'
    });    
    
    let param = JSON.stringify({datasetId:datasetId, indicator:indicator});
    
    return this.ehttp
     .post(acp + '/dss.vector.solutions.kaleidoscope.DataSetController.addIndicator.mojo', param, {headers: headers})
     .toPromise() 
     .then((response: any) => {
       return response.json() as DatasetAttribute;
      })          
     .catch(this.handleError.bind(this));
  }
  
  remove(dataset: Dataset): Promise<Response> {
    let headers = new Headers({
      'Content-Type': 'application/json'
    });  
  
    return this.ehttp
      .post(acp + '/dss.vector.solutions.kaleidoscope.DataSetController.remove.mojo', JSON.stringify({id:dataset.id}), {headers: headers})
      .toPromise()
      .catch(this.handleError.bind(this));
  }
  
  removeAttribute(attribute:DatasetAttribute): Promise<Response> {
    let headers = new Headers({
      'Content-Type': 'application/json'
    });  
    
    return this.ehttp
      .post(acp + '/dss.vector.solutions.kaleidoscope.DataSetController.removeAttribute.mojo', JSON.stringify({id:attribute.id}), {headers: headers})
      .toPromise()
      .catch(this.handleError.bind(this));
  }
  
  unlockAttribute(id:string): Promise<Response> {
    let headers = new Headers({
      'Content-Type': 'application/json'
    });  
    
    return this.ehttp
     .post(acp + '/dss.vector.solutions.kaleidoscope.DataSetController.unlockAttribute.mojo', JSON.stringify({id:id}), {headers: headers})
     .toPromise()
     .catch(this.handleError.bind(this));
  }
  
  editAttribute(attribute:DatasetAttribute): Promise<IndicatorField> {
    let headers = new Headers({
      'Content-Type': 'application/json'
    });  
    
    return this.ehttp
      .post(acp + '/dss.vector.solutions.kaleidoscope.DataSetController.editAttribute.mojo', JSON.stringify({id:attribute.id}), {headers: headers})
      .toPromise()
      .then((response: any) => {
        return response.json() as IndicatorField;
      })          
     .catch(this.handleError.bind(this));
  }
  
  validateDatasetName(name: string, id: string): Promise<Response> {
	let params: URLSearchParams = new URLSearchParams();
    params.set('name', name);
    params.set('id', id);	  
	  
    return this.http
      .get(acp + '/uploader/validateDatasetName', {search: params})
      .toPromise();
  }  
}
