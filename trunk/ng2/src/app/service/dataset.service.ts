import { Injectable } from '@angular/core';
import { Headers, Http, Response, URLSearchParams } from '@angular/http';

import 'rxjs/add/operator/toPromise';

import { EventService, BasicService } from '../core/service/core.service';
import { EventHttpService } from '../core/service/event-http.service';
import { AnalyticsService } from '../core/service/analytics.service';

import { Dataset, DatasetCollection, IndicatorField, DatasetAttribute } from '../model/dataset';
import { Pair } from '../model/pair';

declare var acp: any;

@Injectable()
export class DatasetService extends BasicService {

  constructor(service: EventService, private ehttp: EventHttpService, private http: Http, private analyticsService: AnalyticsService) { super(service); }

  getDatasets(): Promise<DatasetCollection> {
    return this.ehttp
      .get(acp + '/dss.vector.solutions.kaleidoscope.DataSetController.getAll.mojo')
      .toPromise()
      .then(response => {
    	    
    	this.analyticsService.pushAalyticsTrackingTagEvent("SUCCESS", "/dss.vector.solutions.kaleidoscope.DataSetController.getAll.mojo", "get", {});

        return response.json() as DatasetCollection;
      })
      .catch(e => {
    		  this.handleError.bind(this)
    	      this.analyticsService.pushAalyticsTrackingTagEvent("FAILURE", "/dss.vector.solutions.kaleidoscope.DataSetController.getAll.mojo", "get", {});

      });
  }
  
  edit(id : string): Promise<Dataset> {

    let headers = new Headers({
      'Content-Type': 'application/json'
    });  
  
    return this.ehttp
      .post(acp + '/dss.vector.solutions.kaleidoscope.DataSetController.edit.mojo', JSON.stringify({id:id}), {headers: headers})
      .toPromise()
      .then((response: any) => {
    	  
      	this.analyticsService.pushAalyticsTrackingTagEvent("SUCCESS", "/dss.vector.solutions.kaleidoscope.DataSetController.edit.mojo", "post", {id:id});

        return response.json() as Dataset;
      })
      .catch(e => {
    	   this.handleError.bind(this)
    	   this.analyticsService.pushAalyticsTrackingTagEvent("FAILURE", "/dss.vector.solutions.kaleidoscope.DataSetController.edit.mojo", "post", {id:id});
      });      
  }
  
  unlock(dataset: Dataset): Promise<Response> {
    let headers = new Headers({
      'Content-Type': 'application/json'
    });    
    
  	this.analyticsService.pushAalyticsTrackingTagEvent("SEND", "/dss.vector.solutions.kaleidoscope.DataSetController.cancel.mojo", "post", {id:dataset.id});
    
    return this.ehttp
      .post(acp + '/dss.vector.solutions.kaleidoscope.DataSetController.cancel.mojo', JSON.stringify({id:dataset.id}), {headers: headers})
      .toPromise()
      .catch(e => {
    	  this.handleError.bind(this)
    	  this.analyticsService.pushAalyticsTrackingTagEvent("FAILURE", "/dss.vector.solutions.kaleidoscope.DataSetController.cancel.mojo", "post", {id:dataset.id});
      });
  }
  
  apply(dataset: Dataset): Promise<Dataset> {
    let headers = new Headers({
      'Content-Type': 'application/json'
    });    
    
    return this.ehttp
    .post(acp + '/dss.vector.solutions.kaleidoscope.DataSetController.applyDatasetUpdate.mojo', JSON.stringify({datasetJSON:dataset}), {headers: headers})
    .toPromise() 
    .then((response: any) => {
    	
      this.analyticsService.pushAalyticsTrackingTagEvent("SUCCESS", "/dss.vector.solutions.kaleidoscope.DataSetController.applyDatasetUpdate.mojo", "post", {datasetJSON:dataset});

      return response.json() as Dataset;
    })          
    .catch(e => {
    		this.handleError.bind(this)
    	    this.analyticsService.pushAalyticsTrackingTagEvent("FAILURE", "/dss.vector.solutions.kaleidoscope.DataSetController.applyDatasetUpdate.mojo", "post", {datasetJSON:dataset});
    });
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
    	 
       this.analyticsService.pushAalyticsTrackingTagEvent("SUCCESS", "/dss.vector.solutions.kaleidoscope.DataSetController.addIndicator.mojo", "post", {datasetId:datasetId, indicator:indicator});

       return response.json() as DatasetAttribute;
      })          
     .catch(e => {
    		 this.handleError.bind(this)
    	     this.analyticsService.pushAalyticsTrackingTagEvent("FAILURE", "/dss.vector.solutions.kaleidoscope.DataSetController.addIndicator.mojo", "post", {datasetId:datasetId, indicator:indicator});
     });
  }
  
  remove(dataset: Dataset): Promise<DatasetCollection> {
    let headers = new Headers({
      'Content-Type': 'application/json'
    });  
  
    return this.ehttp
      .post(acp + '/dss.vector.solutions.kaleidoscope.DataSetController.remove.mojo', JSON.stringify({id:dataset.id}), {headers: headers})
      .toPromise()
      .then(response => {
    	  
        this.analyticsService.pushAalyticsTrackingTagEvent("SUCCESS", "/dss.vector.solutions.kaleidoscope.DataSetController.remove.mojo", "post", {id:dataset.id});

        return response.json() as DatasetCollection;
      })
      .catch(e => {
    		  this.handleError.bind(this)
    	      this.analyticsService.pushAalyticsTrackingTagEvent("FAILURE", "/dss.vector.solutions.kaleidoscope.DataSetController.remove.mojo", "post", {id:dataset.id});
      });
  }
  
  removeAttribute(attribute:DatasetAttribute): Promise<Response> {
    let headers = new Headers({
      'Content-Type': 'application/json'
    });  
    
    this.analyticsService.pushAalyticsTrackingTagEvent("SEND", "/dss.vector.solutions.kaleidoscope.DataSetController.removeAttribute.mojo", "post", {id:attribute.id});
    
    return this.ehttp
      .post(acp + '/dss.vector.solutions.kaleidoscope.DataSetController.removeAttribute.mojo', JSON.stringify({id:attribute.id}), {headers: headers})
      .toPromise()
      .catch(e => {
    		  this.handleError.bind(this)
    		  this.analyticsService.pushAalyticsTrackingTagEvent("FAILURE", "/dss.vector.solutions.kaleidoscope.DataSetController.removeAttribute.mojo", "post", {id:attribute.id});
      });
  }
  
  unlockAttribute(id:string): Promise<Response> {
    let headers = new Headers({
      'Content-Type': 'application/json'
    });  
    
    this.analyticsService.pushAalyticsTrackingTagEvent("SEND", "/dss.vector.solutions.kaleidoscope.DataSetController.unlockAttribute.mojo", "post", {id:id});

    return this.ehttp
     .post(acp + '/dss.vector.solutions.kaleidoscope.DataSetController.unlockAttribute.mojo', JSON.stringify({id:id}), {headers: headers})
     .toPromise()
     .catch(e => {
    		 this.handleError.bind(this)
    		 this.analyticsService.pushAalyticsTrackingTagEvent("FAILURE", "/dss.vector.solutions.kaleidoscope.DataSetController.unlockAttribute.mojo", "post", {id:id});
     });
  }
  
  editAttribute(attribute:DatasetAttribute): Promise<IndicatorField> {
    let headers = new Headers({
      'Content-Type': 'application/json'
    });  
    
    return this.ehttp
      .post(acp + '/dss.vector.solutions.kaleidoscope.DataSetController.editAttribute.mojo', JSON.stringify({id:attribute.id}), {headers: headers})
      .toPromise()
      .then((response: any) => {
    	  
    	this.analyticsService.pushAalyticsTrackingTagEvent("SUCCESS", "/dss.vector.solutions.kaleidoscope.DataSetController.editAttribute.mojo", "post", {});

        return response.json() as IndicatorField;
      })          
     .catch(e => {
    		 this.handleError.bind(this)
    	    this.analyticsService.pushAalyticsTrackingTagEvent("FAILURE", "/dss.vector.solutions.kaleidoscope.DataSetController.editAttribute.mojo", "post", {});
     });
  }
  
  validateDatasetName(name: string, id: string): Promise<Response> {
	let params: URLSearchParams = new URLSearchParams();
    params.set('name', name);
    params.set('id', id);	  
    
	this.analyticsService.pushAalyticsTrackingTagEvent("SEND", "/uploader/validateDatasetName", "get", params);
	  
    return this.http
      .get(acp + '/uploader/validateDatasetName', {search: params})
      .toPromise();
  }  
}
