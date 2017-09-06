import { Component, OnInit } from '@angular/core';

import { EventService, IEventListener } from '../service/core.service';

declare var MDSS: any;

@Component({
  
  selector: 'loading-bar',
  templateUrl: './loading-bar.component.html',
  styleUrls: []
})
export class LoadingBarComponent implements OnInit, IEventListener {
  showIndicator: boolean = false;
  request:any = new MDSS.Request();

  constructor(private service: EventService) { }

  ngOnInit(): void {
    this.service.registerListener(this);
  }
  
  ngOnDestroy(): void {
    this.service.deregisterListener(this);
  }
  
  start(): void {
    //this.showIndicator = true;   
	  
	this.request.onSend();
  }
  
  complete(): void {
//    this.showIndicator = false;    
	this.request.onComplete();
  }  
  
  onError(error:any): void {
    console.log('error');
  }
  
  onMessage(message:string): void {
    console.log('message : ' + message);
  }
}
