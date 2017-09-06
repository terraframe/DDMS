import { Component, Input} from '@angular/core';

import { Page, Problems, Workbook} from './uploader-model';

@Component({
  
  selector: 'category-validation-page',
  templateUrl: './category-validation-page.component.html',
  styleUrls: []
})
export class CategoryValidationPageComponent {

  @Input() workbook: Workbook;
  @Input() page: Page;
  @Input() problems: Problems;
  
  constructor() {
  }  

  hasProblems(): boolean {
    if(this.problems.categories != null) {      
      for(let i = 0; i < this.problems.categories.length; i++) {
        
        if(!this.problems.categories[i].resolved) {
          return true;
        }
      }
    }
      
    return false;
  }    
}
