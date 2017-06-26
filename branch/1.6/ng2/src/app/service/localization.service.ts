import { Injectable } from '@angular/core';

declare var MDSS: any;
declare var com: any

@Injectable()
export class LocalizationService {

  public parseNumber(value: string) : number {
    if(value != null && value.length > 0) {            
      //convert data from view format to model format
      var number = MDSS.parseNumber( value, false );
        
      return number;
    }
          
    return null;
  }    
    
  public formatNumber(value:any): string {
    if(value != null) {
      var num = value;
            
      if(typeof num === 'string') {
        if(num.length > 0 && Number(num)) {
          num = Number(value);            
        }
        else {
          return "";
        }
      }
            
      //convert data from model format to view format
      return MDSS.formatNumber(num);
    }
            
    return null;
  }
  
  public addCalendar(element: any, callback: any) {
    MDSS.Calendar.addCalendar(element, callback);
  }
    
  public destroyCalendar(element: any) {
    MDSS.Calendar.destroyCalendar(element);
  }    
    
  public localize(bundle: string, key: string): string {
    return com.runwaysdk.Localize.localize(bundle, key);
  }
    
  public get(key: string): string {
    return com.runwaysdk.Localize.get(key);
  }
  
  public decode(key: string): string {
    let index = key.lastIndexOf('.');
    
    if(index !== -1) {
      
      let temp = [key.slice(0, index), key.slice(index + 1)]
    
      return this.localize(temp[0], temp[1]);
    }
    else {
      return this.get(key);
    }
  }
  
  validateInteger(value: string): Promise<{[key : string] : any}> {
    return new Promise((resolve, reject) => {
      let number = MDSS.parseNumber(value);
      
      if(number != null && number === Math.floor(number)) {
        resolve(number);    	  
      }
      else {
        reject(value);
      }
    });   
  }  
  
  validateNumber(value: string): Promise<{[key : string] : any}> {
    return new Promise((resolve, reject) => {
      let number = MDSS.parseNumber(value);
      
      if(number != null) {
        resolve(number);        
      }
      else {
        reject(value);
      }
    });   
  }  
}
