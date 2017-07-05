import { Component, Input, Output, EventEmitter, OnInit} from '@angular/core';

import { Page, CategoryProblem, Workbook} from './uploader-model';
import { Pair } from '../model/pair';

import { UploadService } from '../service/upload.service';
import { IdService } from '../service/core.service';

@Component({
  
  selector: 'category-validation-problem',
  templateUrl: './category-validation-problem.component.html',
  styleUrls: []
})
export class CategoryValidationProblemComponent implements OnInit {

  @Input() problem: CategoryProblem;
  @Input() index: number;
  @Input() workbook: Workbook;

  @Output() onProblemChange = new EventEmitter();
  
  show: boolean;
  
  constructor(private uploadService:UploadService, private idService:IdService) {
  }   
  
  ngOnInit(): void {
    this.problem.synonym = {id :'', value:''};
    this.show = false;    
  }
  
  createSynonym(): void {
    if(this.problem.synonym.id !== ''){      
      this.uploadService.createTermSynonym(this.problem.synonym.id, this.problem.label)
        .then(response => {
          this.problem.resolved = true;
          this.problem.action = {
            name : 'SYNONYM',
            synonymId : response.synonymId,
            label : response.label
          };
          
          this.onProblemChange.emit(this.problem);          
        });
    }
  }
  
  createOption(): void {
    this.uploadService.createTerm(this.problem.label, this.problem.categoryId)
      .then(response => {
        this.problem.resolved = true;
        this.problem.action = {
          name : 'OPTION',
          optionId : response.id
        };
        
        this.onProblemChange.emit(this.problem);
      });
  }
    
  ignoreValue(): void {
    this.problem.resolved = true;
      
    this.problem.action = {
      name : 'IGNORE'
    };
      
    let mdAttributeId = this.problem.mdAttributeId;
      
    if(!this.workbook.categoryExclusion){
      this.workbook.categoryExclusion = {};
    }
      
    if(!this.workbook.categoryExclusion[mdAttributeId]) {
      this.workbook.categoryExclusion[mdAttributeId] = [];
    }
      
    this.workbook.categoryExclusion[mdAttributeId].push(this.problem.label);
    
    this.onProblemChange.emit(this.problem);
  }
    
  removeExclusion(): void {
      
    let mdAttributeId = this.problem.mdAttributeId;
    let label = this.problem.label;
      
    if(this.workbook.categoryExclusion && this.workbook.categoryExclusion[mdAttributeId]){          
      this.workbook.categoryExclusion[mdAttributeId] = this.workbook.categoryExclusion[mdAttributeId].filter(h => h !== label);
    }
      
    if(this.workbook.categoryExclusion[mdAttributeId].length === 0) {
      delete this.workbook.categoryExclusion[mdAttributeId];
    }
  }    
    
  undoAction(): void {
      
    if(this.problem.resolved) {
        
      let action = this.problem.action;
        
      if(action.name === 'IGNORE'){
        this.problem.resolved = false;
          
        this.removeExclusion();
        
        this.onProblemChange.emit(this.problem);
      }
      else if(action.name === 'SYNONYM')  {    	
        this.uploadService.deleteTermSynonym(action.synonymId)
          .then(response => {
          this.problem.resolved = false;
          this.problem.synonym = {id :'', value:''};
          this.problem.action = null;
          
          this.onProblemChange.emit(this.problem);
        });
      }
      else if(action.name === 'OPTION')  {    	
        this.uploadService.deleteTerm(action.optionId)
          .then(response => {
            this.problem.resolved = false;
            this.problem.optionId = null;
            this.problem.action = null;
            
            this.onProblemChange.emit(this.problem);
          });
      }
    }
  }
}
