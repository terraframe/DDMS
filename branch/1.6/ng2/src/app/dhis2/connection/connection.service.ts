import { Injectable } from '@angular/core';
import { Headers, Http, Response, URLSearchParams } from '@angular/http';

import 'rxjs/add/operator/toPromise';

import { EventService, BasicService } from '../../core/service/core.service';
import { EventHttpService } from '../../core/service/event-http.service';

import { Connection } from './connection';

declare var acp: any;

@Injectable()
export class ConnectionService extends BasicService {

  constructor(service: EventService, private ehttp: EventHttpService, private http: Http) { super(service); }

  getInstance(): Promise<Connection> {
    let headers = new Headers({
      'Content-Type': 'application/json'
    });  
    
    return this.ehttp
      .post(acp + '/dhis2/instance', JSON.stringify({}), {headers: headers})
      .toPromise()
      .then(response => {
        return response.json() as Connection;
      })
      .catch(this.handleError.bind(this));      
  }  
  
  connect(connection:Connection): Promise<Response> {
    let headers = new Headers({
      'Content-Type': 'application/json'
    });  
    
    return this.ehttp
      .post(acp + '/dhis2/connect', JSON.stringify({connection:connection}), {headers: headers})
      .toPromise()
      .catch(this.handleError.bind(this));      
  }  
}
