import { Directive, forwardRef, Attribute, Input } from '@angular/core';
import { Validator, AbstractControl, NG_ASYNC_VALIDATORS } from '@angular/forms';

export interface RemoteValidator {
  validate(value:string, config:string): Promise<{[key : string] : any}>;
}

@Directive({
  selector: '[asyncValidator][ngModel]',
  providers: [
    { provide: NG_ASYNC_VALIDATORS, useExisting: forwardRef(() => AsyncValidator), multi: true }
  ]
})
export class AsyncValidator implements Validator {
  
  @Input() validator: RemoteValidator;  
  @Input() config: string;

  constructor(){}

  validate(c: AbstractControl): Promise<{[key : string] : any}> {
    if(c.value != null && c.value.length > 0) {
      return this.validator.validate(c.value, this.config);    
    }
    
    return null;
  }
}