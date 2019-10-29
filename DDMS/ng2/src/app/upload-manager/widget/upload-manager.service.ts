import { Injectable } from '@angular/core';
import { Headers, Http, Response, URLSearchParams } from '@angular/http';

import 'rxjs/add/operator/toPromise';

import { EventService, BasicService } from '../../core/service/core.service';
import { EventHttpService } from '../../core/service/event-http.service';
import { AnalyticsService } from '../../core/service/analytics.service';

import { ExcelImportHistory } from './upload-manager.model';

import { Observable } from 'rxjs/Observable';

declare var acp: any;

@Injectable()
export class UploadManagerService extends BasicService {

  constructor(service: EventService, private ehttp: EventHttpService, private http: Http, private analyticsService: AnalyticsService) { super(service); }

  getAllHistory(): Promise<ExcelImportHistory[]> {
    return this.ehttp
      .get(acp + '/dss.vector.solutions.generator.ExcelController.getAllHistory.mojo')
      .toPromise()
      .then(response => {
    	  
    	this.analyticsService.pushAalyticsTrackingTagEvent("SUCCESS", "/dss.vector.solutions.generator.ExcelController.getAllHistory.mojo", "get", {});
 
        return response.json() as ExcelImportHistory[];
      })
      .catch(e => {
    	    	this.analyticsService.pushAalyticsTrackingTagEvent("FAILURE", "/dss.vector.solutions.generator.ExcelController.getAllHistory.mojo", "get", {});

    	    	this.handleError.bind(this)
      });
  }
  
  getPaginatedHistory(sortAttribute: string, isAscending: boolean, pageSize: number, pageNumber: number): Promise<any> {
    return this.ehttp
      .get(acp + '/dss.vector.solutions.generator.ExcelController.getPaginatedHistory.mojo'
        + '?sortAttribute=' + sortAttribute + "&"
        + 'isAscending=' + isAscending + "&"
        + 'pageSize=' + pageSize + "&"
        + 'pageNumber=' + pageNumber)
      .toPromise()
      .then(response => {
        
      this.analyticsService.pushAalyticsTrackingTagEvent("SUCCESS", "/dss.vector.solutions.generator.ExcelController.getPaginatedHistory.mojo", "get", {});
 
        return response.json() as any;
      })
      .catch(e => {
            this.analyticsService.pushAalyticsTrackingTagEvent("FAILURE", "/dss.vector.solutions.generator.ExcelController.getPaginatedHistory.mojo", "get", {});

            this.handleError.bind(this)
      });
  }
  
  pollPaginatedHistory(sortAttribute: string, isAscending: boolean, pageSize: number, pageNumber: number): Observable<any> {
    return Observable.interval(10000)
      .flatMap(() => this.http.get(
        acp + '/dss.vector.solutions.generator.ExcelController.getPaginatedHistory.mojo'
        + '?sortAttribute=' + sortAttribute + "&"
        + 'isAscending=' + isAscending + "&"
        + 'pageSize=' + pageSize + "&"
        + 'pageNumber=' + pageNumber
      )
      .catch(err => {console.log("ignoring error: " + err); return Observable.empty() as Observable<Response>}))
      .map(res => res.json() as any)
  }
  
  pollAllHistory(): Observable<any> {
    return Observable.interval(10000)
      .flatMap(() => this.http.get(acp + '/dss.vector.solutions.generator.ExcelController.getAllHistory.mojo').catch(err => {console.log("ignoring error: " + err); return Observable.empty() as Observable<Response>}))
      .map(res => res.json() as any)
  }
  
  clearHistory(): Promise<void | Response> {
	
  	this.analyticsService.pushAalyticsTrackingTagEvent("SEND", "/dss.vector.solutions.generator.ExcelController.clearHistory.mojo", "get", {});

    return this.ehttp
    .get(acp + '/dss.vector.solutions.generator.ExcelController.clearHistory.mojo')
    .toPromise()
    .catch(e => {
    	  	this.analyticsService.pushAalyticsTrackingTagEvent("FAILURE", "/dss.vector.solutions.generator.ExcelController.clearHistory.mojo", "get", {});

    	  	this.handleError.bind(this)
    });
  }
}
