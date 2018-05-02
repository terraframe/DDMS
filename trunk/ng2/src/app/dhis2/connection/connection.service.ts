import { Injectable } from '@angular/core';
import { Headers, Http, Response, URLSearchParams } from '@angular/http';

import 'rxjs/add/operator/toPromise';

import { EventService, BasicService } from '../../core/service/core.service';
import { EventHttpService } from '../../core/service/event-http.service';
import { AnalyticsService } from '../../core/service/analytics.service';


import { Connection } from './connection';

declare var acp: any;

@Injectable()
export class ConnectionService extends BasicService {

  constructor(service: EventService, private ehttp: EventHttpService, private http: Http, private analyticsService: AnalyticsService) { super(service); }

  getInstance(): Promise<Connection> {
    let headers = new Headers({
      'Content-Type': 'application/json'
    });  
    
    return this.ehttp
      .post(acp + '/dhis2/instance', JSON.stringify({}), {headers: headers})
      .toPromise()
      .then(response => {
    	  
        this.analyticsService.pushAalyticsTrackingTagEvent("SUCCESS", "/dhis2/instance", "post", {});

        return response.json() as Connection;
      })
      .catch(this.handleError.bind(this));      
  }  
  
  connect(connection:Connection): Promise<Response> {
    let headers = new Headers({
      'Content-Type': 'application/json'
    });  
    
    this.analyticsService.pushAalyticsTrackingTagEvent("SEND", "/dhis2/connect", "post", {connection:connection});
    
    return this.ehttp
      .post(acp + '/dhis2/connect', JSON.stringify({connection:connection}), {headers: headers})
      .toPromise()
      .catch(this.handleError.bind(this));      
  }  
}
