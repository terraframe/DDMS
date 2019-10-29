import { Component, OnInit } from '@angular/core';

import { EventService, IEventListener } from '../service/core.service';

declare var MDSS:any;

@Component({
  
  selector: 'message',
  templateUrl: './message.component.html',
  styleUrls: ['./message.component.css']
})
export class MessageComponent implements OnInit, IEventListener {
	
  constructor(private service: EventService) { }

  ngOnInit(): void {
    this.service.registerListener(this);
  }
  
  ngOnDestroy(): void {
    this.service.deregisterListener(this);
  }
  
  start(): void {
  }
  
  complete(): void {
  }  
  
  onError(error: any): void {
    MDSS.ErrorModal(error.localizedMessage);
  }
  
  onMessage(msg: string): void {
    MDSS.ErrorModal(msg);    
  }
}
