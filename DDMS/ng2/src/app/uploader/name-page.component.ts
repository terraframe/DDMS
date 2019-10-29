import { Component, Input} from '@angular/core';

import { Sheet, Options, Field, Page } from './uploader-model';

import { RemoteValidator } from '../core/async-validator.directive';
import { DatasetService } from '../service/dataset.service';

@Component({
  
  selector: 'name-page',
  templateUrl: './name-page.component.html',
  styleUrls: []
})
export class NamePageComponent implements RemoteValidator {
  @Input() options: Options;
  @Input() sheet: Sheet;
  @Input() page: Page;  

  constructor(private service: DatasetService) { }
 
  validate(value:string): Promise<{[key : string] : any}> {
    return this.service.validateDatasetName(value, '')
      .then((response:any) => {
        return null;
      })
      .catch((error:any) => {
        return {uniqueName: false};
      });            
  }  
}
