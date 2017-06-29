import { Component, EventEmitter, Output } from '@angular/core';

import { Dataset, DatasetAttribute, IndicatorField} from '../model/dataset';
import { Pair } from '../model/pair';

@Component({  
  selector: 'indicator-modal',
  templateUrl: './indicator-modal.component.html',
  styleUrls: []
})
export class IndicatorModalComponent {

  @Output() onSuccess = new EventEmitter<IndicatorField>();
  
  indicator: IndicatorField;
  show: boolean;
  aggregations: Pair[];
  attributes: DatasetAttribute[];
  
  constructor() {}
  
  initialize(aggregations: Pair[], attributes:DatasetAttribute[]): void {
    this.indicator = {
      label: '',
      left: {
        aggregation: '',
        attribute: ''       
      },
      operation: 'divide', 
      right: {
        aggregation: '',
        attribute: ''         
      }
    }; 
    
    this.aggregations = aggregations;;
    this.attributes = attributes;
    this.show = true;	  
  }
  
  cancel(): void{
    this.aggregations = null;
    this.attributes = null;
    this.show = false;
  }
  
  onSubmit(): void{
//	  event.stopPropagation();  
	
    this.onSuccess.emit(this.indicator);
    this.cancel();
  }
}
