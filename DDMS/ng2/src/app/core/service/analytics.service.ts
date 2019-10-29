import { Injectable } from '@angular/core';


declare var acp: any;

@Injectable()
export class AnalyticsService {

  constructor() { }

  /*
   * param requestState: send, complete, success, or failure
   */
  pushAalyticsTrackingTagEvent(requestState:string, url:string, requestMethod:string, requestParams:any): void
  {
  	console.log("Sending Google Analytics data...");
  	
    // Push to the global Google Tag Manager Variable which triggers the GTM event listener.
    // Each variable in the dataLayer should map to a variable in GTM
  	(<any>window).dataLayer.push({
  	  "event":'mdssAjaxRequest',
  	  "requestParams":  requestParams,
  	  "requestMethod": requestMethod,
  	  "url": url,
  	  "options": "",
  	  "xhrResponse": "",
  	  "requestState": requestState,
  	  "mainIdentifier": url
    });
  }
  
}
