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
  
  @Output() onProblemChange = new EventEmitter();
  
  show: boolean;
  hasSynonym: boolean;
  
  constructor(private uploadService: UploadService, private idService: IdService) {
  }   
  
  ngOnInit(): void {
    this.problem.synonym = null;
    this.show = false;	  
    this.hasSynonym = false;	      
  }
  
  source = (keyword: string) => {
    let limit = '20';

    return this.uploadService.getGeoEntitySuggestions(this.problem.parentId, this.problem.universalId, keyword, limit);
  }
        
  setSynonym(item: {text: string, data: any}) {	
    this.problem.synonym = item.data;
    this.hasSynonym = (this.problem.synonym != null);        	  
  }
    
  createSynonym(): void {
    if(this.hasSynonym){    	
      this.uploadService.createGeoEntitySynonym(this.problem.synonym, this.problem.label)
        .then(response => {
          this.problem.resolved = true;
          this.problem.action = {
            name : 'SYNONYM',
            synonymId : response.synonymId,
            label : response.label,
            ancestors : response.ancestors            
          };
          
          this.onProblemChange.emit(this.problem);          
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
        
        this.onProblemChange.emit(this.problem);        
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
    
    this.onProblemChange.emit(this.problem);
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
            this.problem.synonym = null;
            this.problem.action = null;
            
            this.hasSynonym = (this.problem.synonym != null);
            
            this.onProblemChange.emit(this.problem);
          });
      }
      else if(action.name === 'IGNOREATLOCATION'){
        this.problem.resolved = false;
        this.problem.action = null;
        
        this.removeLocationExclusion(action.id);
        
        this.onProblemChange.emit(this.problem);
      }      
      else if(action.name === 'SYNONYM')  {    	
        this.uploadService.deleteGeoEntitySynonym(action.synonymId)
          .then(response => {
          this.problem.resolved = false;
          this.problem.synonym = null;
          this.problem.action = null;
          
          this.hasSynonym = (this.problem.synonym != null);        	  
          
          this.onProblemChange.emit(this.problem);
        });
      }
        
    }
  }
  
  toggle(): void {
    this.show = !this.show;
  }
  
}
