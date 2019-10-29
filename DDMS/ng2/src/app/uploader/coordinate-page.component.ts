import { Component, OnInit, Input, Output, EventEmitter, Directive} from '@angular/core';
import { Validator, AbstractControl, NG_VALIDATORS } from '@angular/forms';

import { LocalValidator } from '../core/function-validator.directive';
import { UploadInformation, Sheet, Page, Field, Universal, CoordinateAttribute, Coordinates} from './uploader-model';
import { LocalizationService } from '../core/service/localization.service';

@Component({
  
  selector: 'coordinate-page',
  templateUrl: './coordinate-page.component.html',
  styleUrls: []
})
export class CoordinatePageComponent implements OnInit, LocalValidator {

  @Input() info: UploadInformation;
  @Input() sheet: Sheet;
  @Input() page: Page;
  
  locations: {[key : string] : string}[];
  
  longitudes: Field[];
  featureLabels: Field[];
  featureIds: Field[];
  
  universals: Universal[];
  labels: {[key : string] : string};  
  
  constructor(private localizationService: LocalizationService) {
    this.longitudes = [];
    this.featureLabels = [];
    this.locations = [];
    this.featureIds = [];
    this.labels = {};	      
  }  

  ngOnInit(): void {
    let countries = this.info.options.countries;
    
    let country = countries[0];
    this.universals = country.options;
    
    for(let j = 0; j < country.options.length; j++) {
      let universal = country.options[j];
          
      this.labels[universal.value] = universal.label;
    }
      
    for(let i = 0; i < this.sheet.fields.length; i++) {
      let field = this.sheet.fields[i];
        
      if(field.type === 'LATITUDE') {
          
        if(!this.hasCoordinateField(field)) {
          let coordinate = new CoordinateAttribute();
          coordinate.label = "";
          coordinate.latitude = field.label;
          coordinate.longitude = this.getSuggestedLongitude(field);
          coordinate.featureLabel = "";
          coordinate.location = "";
          coordinate.featureId = "";
          coordinate.id = field.label
            
          this.sheet.coordinates.push(coordinate);
        }
      }
      else if(field.type === 'LONGITUDE') {
        this.longitudes.push(field);          
      }
      else if(field.type === 'TEXT' || field.type === 'CHARACTER') {
        this.featureLabels.push(field);          
      }
      else if(this.isBasic(field)) {
        this.featureIds.push(field);          
      }
    }
      
    /*
     * If there is only 1 longitude field then set that value
     * automatically and don't give the user a drop-down that
     * they need to select from
     */
    if(this.longitudes.length === 1) {
      for(let i = 0; i < this.sheet.coordinates.length; i++) {
        let coordinate = this.sheet.coordinates[i];          
          
        coordinate.longitude = this.longitudes[0].label;
      }
    }
      
    if(this.sheet.attributes != null) {
      for(let i = 0; i < this.sheet.attributes.ids.length; i++) {
        let id = this.sheet.attributes.ids[i];
        let attribute = this.sheet.attributes.values[id];          
        
        this.locations.push({
          label : attribute.label,
          universal : attribute.universal
        });
      }
    }
  }
    
  getSuggestedLongitude(targetField: Field): string {
    let fields = this.sheet.fields;
    let trackingPosition:any = null;
    let mostLikelyLongitudeField:any = null;
    let label = targetField.label.toLowerCase();
    
    let labels = [
      this.localizationService.localize("dataUploader", "attributeLatAbbreviation").toLowerCase(),
      this.localizationService.localize("dataUploader", "attributeLatitudeName").toLowerCase(),     
      this.localizationService.localize("dataUploader", "attributeLngAbbreviation").toLowerCase(),
      this.localizationService.localize("dataUploader", "attributeLongAbbreviation").toLowerCase(),
      this.localizationService.localize("dataUploader", "attributeLongitudeName").toLowerCase()   
    ];
    
    for(let i=0; i<fields.length; i++){
      let field = fields[i];
      if(field.type === "LATITUDE" && field.name === targetField.label){
        trackingPosition = field.fieldPosition;
      }
      else if(field.type === "LONGITUDE"){
        // if fields are located next to each other in the source data (spreadsheet)
        if(field.fieldPosition === trackingPosition + 1 || field.fieldPosition === trackingPosition - 1){
          return field.name;
        }
        else {        	
          for(let j = 0; j < labels.length; j++) {
            if(label.includes(labels[j]) ){
              return field.name;
            }
          }
        }
      }
    }
    
    return null;
  }
    
  hasCoordinateField(field: Field): boolean {
    for(let i = 0; i < this.sheet.coordinates.length; i++) {
      let coordinate = this.sheet.coordinates[i];
        
      if(coordinate.id === field.label) {
        return true;
      }
    }
      
    return false;
  }
    
  isBasic(field: Field): boolean {
    return (field.type === 'TEXT' || field.type === 'CHARACTER' || field.type === 'LONG' || field.type === 'DOUBLE');  
  }
  
  localValidate(value: string, config: string): {[key : string] : any} {
    if(config === 'label') {
      return this.validateLabel(value);  
    }
    
    return null;
  }
        
  validateLabel(label: string): {[key : string] : any} {
    if(this.sheet != null) {
      let count = 0;
        
      for(let i = 0; i < this.sheet.fields.length; i++) {
        let field = this.sheet.fields[i];
          
        if(field.label === label) {
          count++;
        }            
      }
        
      if(this.sheet.attributes != null) {
        for(let i = 0; i < this.sheet.attributes.ids.length; i++) {
          let id = this.sheet.attributes.ids[i];
          let attribute = this.sheet.attributes.values[id];          
            
          if(attribute.label === label) {
            count++;
          }            
        }          
      }
        
      for(let i = 0; i < this.sheet.coordinates.length; i++) {
        let coordinate = this.sheet.coordinates[i];          
          
        if(coordinate.label === label) {
          count++;
        }            
      }
        
      if(count > 1) {
        return {unique:false};
      }
    }  
      
    return null;
  }
}
