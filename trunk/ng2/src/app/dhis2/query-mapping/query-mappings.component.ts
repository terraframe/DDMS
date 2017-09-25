import { Component, OnInit, OnDestroy } from '@angular/core';

import { BsModalService } from 'ngx-bootstrap/modal';
import { BsModalRef } from 'ngx-bootstrap/modal/modal-options.class';

import { QueryMappingComponent } from './query-mapping.component'
import { QueryMappingService } from './query-mapping.service'
import { QueryMapping } from './query-mapping'
import { ExportResults } from './export-results'

import { Subscription } from 'rxjs/Subscription';

@Component({
  selector: 'query-mappings',
  templateUrl: './query-mappings.component.html',
  styleUrls: ['./query-mappings.component.css']
})
export class QueryMappingsComponent implements OnInit, OnDestroy {
  
  mappings:QueryMapping[] = [];
  strategy:string = "CREATE_AND_UPDATE";

  bsModalRef: BsModalRef;  
  subscription: Subscription = null;
  
  results: ExportResults[];

  constructor(private service:QueryMappingService, private modalService: BsModalService) {}

  ngOnInit(): void {
	this.getAll();
	
    this.subscription = this.modalService.onHidden.subscribe((reason: string) => {
      if(reason === null) {
        this.getAll();
      }
    });    
  }
  
  ngOnDestroy() {
    this.subscription.unsubscribe();
  }  
  
  getAll(): void {
    this.service.getAll().then(mappings => {
      this.mappings = mappings;
    });        
  }
  
  remove(mapping:QueryMapping): void {
    this.service.remove(mapping.id).then(response => {
      this.mappings = this.mappings.filter(h => h.id !== mapping.id);        
    });        
  }
  
  newInstance(): void {
    this.service.newInstance().then(options => {
      this.bsModalRef = this.modalService.show(QueryMappingComponent, {backdrop: 'static', class: 'gray modal-lg'});
      this.bsModalRef.content.options = options;      
    });    
  }
  
  selectAll() : void {
    this.mappings.forEach(mapping => {
      mapping.xport = true;
    });        	  
  }
  
  selectNone() : void {
    this.mappings.forEach(mapping => {
      mapping.xport = false;
    });            
  }
  
  upload(): void {
    let uploads = this.mappings.filter(h => h.xport === true);        
	  
    this.service.xport(uploads, this.strategy).then(response => {
      this.results = response;
    });        
  }
}
