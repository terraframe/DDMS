import { Component, Input} from '@angular/core';
import { Page } from './uploader-model';

@Component({
  
  selector: 'beginning-info-page',
  templateUrl: './beginning-info-page.component.html',
  styleUrls: []
})
export class BeginningInfoPageComponent {
  @Input() page: Page;
  
  constructor() { }  
}
