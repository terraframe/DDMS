import {Component, Input, Output, EventEmitter, AfterViewInit, OnDestroy} from '@angular/core';

import { Pair } from '../model/pair';

declare var MDSS:any;
declare var acp:string;

@Component({    
  selector: 'term-field',
  templateUrl: './term-field.component.html',
  styleUrls: []
})
export class TermFieldComponent implements AfterViewInit, OnDestroy {
  private browser:any;
  private cxpath:string = acp;

  @Input() attribute:string = "";
  @Input() model:Pair = {id :'', value:''};
  @Output() modelChange = new EventEmitter<Pair>();

  constructor(){
  }
  
  ngAfterViewInit():any {
    var config = {
      attributeName : this.attribute,
      enabled : true,
      defaultRoot : true
    };
  
    this.browser = new MDSS.GenericOntologyBrowser('', [config]);
    this.browser.addRoot(['ROOT', 'false']);
    this.browser.addTermSelectedListener((event:any) => {
      this.model.id = event.getTermId();	
      this.model.value = event.getLabel();	
      
      this.modelChange.emit(this.model);
    });
  }
  
  ngOnDestroy() {
  }    
}