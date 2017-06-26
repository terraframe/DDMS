import { Component, Input, Output, EventEmitter, OnInit} from '@angular/core';

import { Page, CategoryProblem, Workbook} from './uploader-model';
import { Pair } from '../model/pair';

import { CategoryService } from '../service/category.service';
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
  @Input() options: Pair[];

  @Output() onProblemChange = new EventEmitter();
  
  show: boolean;
  hasSynonym: boolean;
  
  constructor(private uploadService: UploadService, private categoryService: CategoryService, private idService: IdService) {
  }   
  
  ngOnInit(): void {
    this.problem.synonym = null;
    this.show = false;    
    this.hasSynonym = false;        
  }
  
  source = (text: string) => {
    let limit = '20';

    return this.uploadService.getClassifierSuggestions(this.problem.mdAttributeId, text, limit);
  }
        
  setSynonym() {  
    this.hasSynonym = (this.problem.synonym != null && this.problem.synonym.length > 0);            
  }
    
  createSynonym(): void {
    if(this.hasSynonym){      
      this.uploadService.createClassifierSynonym(this.problem.synonym, this.problem.label)
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
    this.categoryService.create(this.problem.label, this.problem.categoryId, false)
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
        this.uploadService.deleteClassifierSynonym(action.synonymId)
          .then(response => {
          this.problem.resolved = false;
          this.problem.synonym = null;
          this.problem.action = null;
          
          this.hasSynonym = (this.problem.synonym != null);
          
          this.onProblemChange.emit(this.problem);
        });
      }
      else if(action.name === 'OPTION')  {    	
        this.categoryService.remove(action.optionId)
          .then(response => {
            this.problem.resolved = false;
            this.problem.optionId = null;
            this.problem.action = null;
            
            this.hasSynonym = (this.problem.synonym != null);
            
            this.onProblemChange.emit(this.problem);
          });
      }
    }
  }
}
