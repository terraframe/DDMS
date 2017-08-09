import {Component, Input, Output, EventEmitter} from '@angular/core';

declare var MDSS:any;
declare var dss:any;
declare var acp:string;

@Component({    
  selector: 'boolean-field',
  templateUrl: './boolean-field.component.html',
  styleUrls: []
})
export class BooleanFieldComponent {

  @Input() value:boolean = false;  
  
  @Output() public valueChange = new EventEmitter<boolean>();

  constructor(){}
  
  toggle():void {
    this.value = !this.value;
    
    this.valueChange.emit(this.value);
  }
}