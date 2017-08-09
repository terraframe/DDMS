import {Directive, AfterViewInit, OnDestroy, ElementRef} from '@angular/core';
import {NgModel} from '@angular/forms';

import { LocalizationService } from './service/localization.service';

@Directive({ 
  selector: '[ngModel][dateField]',
  providers: [NgModel],
})
export class DateFieldDirective implements AfterViewInit, OnDestroy {
  constructor(private localizationService:LocalizationService, private el:ElementRef, private model:NgModel){}
  
  ngAfterViewInit():any {
    this.localizationService.addCalendar(this.el.nativeElement, (value:string) => {
      this.model.viewToModelUpdate(value);
      this.model.valueAccessor.writeValue(value);
    });
  }
  
  ngOnDestroy() {
    this.localizationService.destroyCalendar(this.el.nativeElement);	  
  }    
}