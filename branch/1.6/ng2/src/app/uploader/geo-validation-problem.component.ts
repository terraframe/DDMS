import { Component, Input, Output, EventEmitter, OnInit} from '@angular/core';

import { Page, LocationProblem, Workbook, LocationExclusion} from './uploader-model';
import { UploadService } from '../service/upload.service';
import { IdService } from '../service/core.service';

@Component({
  
  selector: 'geo-validation-problem',
  templateUrl: './geo-validation-problem.component.html',
  styleUrls: []
})
export class GeoValidationProblemComponent implements OnInit {

  @Input() problem: LocationProblem;
  @Input() index: number;
  @Input() workbook: Workbook;
  
  show: boolean;
  
  constructor(private uploadService: UploadService, private idService: IdService) {
  }   
  
  ngOnInit(): void {
    this.problem.synonym = {id :'', geoId:''};
    this.show = false;	  
	  
//    this.problem.synonym = null;
  }
  
//  source = (keyword: string) => {
//    let limit = '20';
//
//    return this.uploadService.getGeoEntitySuggestions(this.problem.parentId, this.problem.universalId, keyword, limit);
//  }
//        
//  setSynonym(item: {text: string, data: any}) {	
//    this.problem.synonym = item.data;
//    this.hasSynonym = (this.problem.synonym != null);        	  
//  }
    
  createSynonym(): void {
    if(this.problem.synonym.id !== ''){      
      this.uploadService.createGeoEntitySynonym(this.problem.synonym.id, this.problem.label)
        .then(response => {
          this.problem.resolved = true;
          this.problem.action = {
            name : 'SYNONYM',
            synonymId : response.synonymId,
            label : response.label,
            ancestors : response.ancestors            
          };          
        });
    }      
  }
    
  createEntity(): void {
	  
    this.uploadService.createGeoEntity(this.problem.parentId, this.problem.universalId, this.problem.label)
      .then(response => {
        this.problem.resolved = true;
        this.problem.action = {
          name : 'ENTITY',
          entityId : response.entityId
        };
      });
  }
    
  removeLocationExclusion(exclusionId: string): void {

    if(this.workbook.locationExclusions){         
      this.workbook.locationExclusions = this.workbook.locationExclusions.filter(h => h.id !== exclusionId);
    }
  }
    
  ignoreDataAtLocation(): void {
    let locationLabel = this.problem.label;
    let universal = this.problem.universalId;
    let id = this.idService.generateId();
      
    this.problem.resolved = true;
      
    this.problem.action = {
      name : 'IGNOREATLOCATION',
      label : locationLabel,
      id : id
    };
      
    let exclusion = new LocationExclusion(id, universal, locationLabel, this.problem.parentId);
      
    if(this.workbook.locationExclusions){
      this.workbook.locationExclusions.push(exclusion);
    }
    else{
      this.workbook.locationExclusions = [exclusion];
    }
  }    

  undoAction(): void {
    let locationLabel = this.problem.label;
    let universal = this.problem.universalId;
      
    if(this.problem.resolved) {
      let action = this.problem.action;
    	
      if(action.name === 'ENTITY')  {    	
        this.uploadService.deleteGeoEntity(action.entityId)
          .then(response => {
            this.problem.resolved = false;
            this.problem.synonym = {id :'', geoId:''};
            this.problem.action = null;
          });
      }
      else if(action.name === 'IGNOREATLOCATION'){
        this.problem.resolved = false;
        this.problem.action = null;
        
        this.removeLocationExclusion(action.id);
      }      
      else if(action.name === 'SYNONYM')  {    	
        this.uploadService.deleteGeoEntitySynonym(action.synonymId)
          .then(response => {
          this.problem.resolved = false;
          this.problem.synonym = {id :'', geoId:''};
          this.problem.action = null;
        });
      }
        
    }
  }
  
  toggle(): void {
    this.show = !this.show;
  }
  
}
