import { Component, EventEmitter, Output, Input } from '@angular/core';

import { Dataset, DatasetAttribute, IndicatorField} from '../model/dataset';
import { Pair } from '../model/pair';

import { DatasetService } from '../service/dataset.service';

@Component({  
  selector: 'indicator-modal',
  templateUrl: './indicator-modal.component.html',
  styleUrls: []
})
export class IndicatorModalComponent {

  @Output() onSuccess = new EventEmitter<DatasetAttribute>();
  @Input() datasetId:string;
  
  indicator: IndicatorField;
  show: boolean;
  aggregations: Pair[];
  attributes: DatasetAttribute[];
  
  constructor(private datasetService: DatasetService) {}
  
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
	  
    this.datasetService.addIndicator(this.datasetId, this.indicator)
      .then(attribute => {
        this.onSuccess.emit(attribute);
        this.cancel();
      })
//	  
//	
//    this.onSuccess.emit(this.indicator);
//    this.cancel();
  }
}
