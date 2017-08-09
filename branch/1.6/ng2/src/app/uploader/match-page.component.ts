import { Component, Input, Output, EventEmitter } from '@angular/core';

import { UploadInformation, Step, Sheet, Page } from './uploader-model';

import { UploadService } from './service/upload.service';


@Component({
  
  selector: 'match-page',
  templateUrl: './match-page.component.html',
  styleUrls: []
})
export class MatchPageComponent {
	
  @Input() sheet: Sheet;
  @Output() onSelect = new EventEmitter<Sheet>();

  constructor(private service: UploadService) { }
  
  select(match:any, overwrite:boolean) : void {
   
    this.service.getSavedConfiguration(match.id, this.sheet.name)
      .then(response => {
    	let sheet = response.datasets;
        sheet.replaceExisting = overwrite;          
        sheet.exists = true;          
    	  
    	this.onSelect.emit(sheet);    	
      });
  }
}
