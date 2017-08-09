import { Component} from '@angular/core';

import { BsModalRef } from 'ngx-bootstrap/modal/modal-options.class';

import { QueryMappingService } from './query-mapping.service'
import { QueryMapping } from './query-mapping'

@Component({
  selector: 'query-mapping',
  templateUrl: './query-mapping.component.html',
  styleUrls: []
})
export class QueryMappingComponent{
  
  public options: {id:string, label:string}[] = [];
  
  mapping = new QueryMapping();
  
  constructor(private service:QueryMappingService, public bsModalRef: BsModalRef) {}
  
  cancel():void {
    this.bsModalRef.hide();
  }
  
  onSubmit():void {
    this.service.apply(this.mapping).then(response => {
      this.bsModalRef.hide();      
    });
  }
}
