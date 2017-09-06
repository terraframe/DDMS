import { Component, EventEmitter, OnInit, Output, Inject} from '@angular/core';
import { ActivatedRoute, Params, Resolve, ActivatedRouteSnapshot, RouterStateSnapshot } from '@angular/router';
import { Location } from '@angular/common';

import { Observable } from 'rxjs/Observable';
import 'rxjs/add/operator/switchMap';

import { Category, BasicCategory } from '../model/category';
import { Synonym } from '../model/synonym';

import { EventService } from '../core/service/core.service';
import { CategoryService } from '../service/category.service';


export class OptionResolver implements Resolve<Category> {
  constructor(@Inject(CategoryService) private categoryService: CategoryService, @Inject(EventService) private eventService: EventService) {}
  
  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot):Promise<Category> {
    return this.categoryService.edit(route.params['parentId'], route.params['id'])
      .catch((error:any) => {
        this.eventService.onError(error); 
    	  
        return Promise.reject(error);
      });
  }
}

class Action {
  synonym: string;
  restore: string[];   

  constructor() {
    this.synonym = '';
    this.restore = [];
  }
}

@Component({
  
  selector: 'option-detail',
  templateUrl: './option-detail.component.html',
  styleUrls: []
})
export class OptionDetailComponent implements OnInit {
  @Output() close = new EventEmitter();
  
  category: Category;
  action: Action;
  
  constructor(
    private categoryService: CategoryService,
    private route: ActivatedRoute,
    private location: Location) {
  }

  ngOnInit(): void {
    this.category = this.route.snapshot.data['category'];
    
    this.action = new Action();
  }
  
  onSubmit(): void {
    let config = {
      option : this.category,
      synonym : this.action.synonym,
      restore : this.action.restore
    }
      
    this.categoryService.apply(config)
      .then(response => {
        this.goBack(this.category);
      });
  }
  
  cancel(): void {
    this.categoryService.unlock(this.category)
      .then(response => {
        this.goBack(this.category);
      })
  } 
  
  goBack(category : Category): void {
    this.close.emit(category);
    
    this.location.back();
  }
  
  restore(synonym: Synonym): void {
	  
      this.action.restore.push(synonym.id);
        	
      this.category.synonyms = this.category.synonyms.filter(h => h !== synonym);            	        	
  } 
  
}
