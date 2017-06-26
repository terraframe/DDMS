import { Component, Input} from '@angular/core';

import { Page, Problems, Workbook} from './uploader-model';

@Component({
  
  selector: 'geo-validation-page',
  templateUrl: './geo-validation-page.component.html',
  styleUrls: []
})
export class GeoValidationPageComponent {

  @Input() workbook: Workbook;
  @Input() page: Page;
  @Input() problems: Problems;
  
  constructor() {
  }  

  hasProblems(): boolean {
    if(this.problems.locations != null) {      
      for(let i = 0; i < this.problems.locations.length; i++) {
        
        if(!this.problems.locations[i].resolved) {
          return true;
        }
      }
    }
      
    return false;
  }    
}
