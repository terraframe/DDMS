import { Component, Input, Output, EventEmitter} from '@angular/core';
import { FormGroup} from '@angular/forms';

import { Page } from './uploader-model';
import { NavigationService } from './navigation.service';

@Component({
  
  selector: 'paging',
  templateUrl: './paging.component.html',
  styleUrls: []
})
export class PagingComponent {
  @Input() form: FormGroup;
  @Input() page: Page;
  @Input() global: boolean = true;
  
  constructor(private service: NavigationService) { } 
  
  next(): void {
    this.service.navigate('next');
  }
  
  prev(): void {
    this.service.navigate('prev');
  }
  
  cancel(): void {
    this.service.navigate('cancel');
  }
  
  ready(): void {
    this.service.navigate('ready');
  }
}
