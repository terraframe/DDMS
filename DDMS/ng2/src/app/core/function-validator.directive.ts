import { Directive, forwardRef, Attribute, Input } from '@angular/core';
import { Validator, AbstractControl, NG_VALIDATORS } from '@angular/forms';

export interface LocalValidator {
  localValidate(value:string, config: string): {[key : string] : any};
}

@Directive({
  selector: '[funcValidator][ngModel]',
  providers: [
    { provide: NG_VALIDATORS, useExisting: forwardRef(() => FunctionValidator), multi: true }
  ]
})
export class FunctionValidator implements Validator {
  
  @Input() validator: LocalValidator;
  @Input() config: string;
  
  constructor(){}

  validate(c: AbstractControl): {[key : string] : any} {
    if(c.value != null && c.value.length > 0) {
      return this.validator.localValidate(c.value, this.config);    
    }
    
    return null;
  }
}