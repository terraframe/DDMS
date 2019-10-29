import { Component, EventEmitter, Input, OnInit, Output, Inject } from '@angular/core';
import { Router, ActivatedRoute, Params, Resolve, ActivatedRouteSnapshot, RouterStateSnapshot } from '@angular/router';
import { Location } from '@angular/common';

import { Observable } from 'rxjs/Observable';
import 'rxjs/add/operator/switchMap';

import { Category, BasicCategory } from '../model/category';

import { EventService } from '../core/service/core.service';

import { CategoryService } from '../service/category.service';

export class CategoryResolver implements Resolve<Category> {
  constructor(@Inject(CategoryService) private categoryService: CategoryService, @Inject(EventService) private eventService: EventService) {}
  
  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot):Promise<Category> {
    return this.categoryService.get(route.params['id'])
      .catch((error:any) => {
        this.eventService.onError(error); 
        
        return Promise.reject(error);
      });
  }
}

class Instance {
  active: boolean;
  label: string;   
}

@Component({
  
  selector: 'category-detail',
  templateUrl: './category-detail.component.html',
  styleUrls: []
})
export class CategoryDetailComponent implements OnInit {
  @Input() category: Category;
  @Output() close = new EventEmitter();
  
  instance : Instance = new Instance();  
  validName: boolean = true;


  constructor(
    private router: Router,      
    private categoryService: CategoryService,
    private route: ActivatedRoute,
    private location: Location) {
  }

  ngOnInit(): void {
    this.category = this.route.snapshot.data['category'];
    
    this.instance.active = false; 
    this.instance.label = '';
  }
  
  onSubmit(): void {
    this.categoryService.update(this.category)
      .then(category => {
        this.goBack(category);
      });
  }
  
  goBack(category : Category): void {
    this.close.emit(category);
    
    this.location.back();
  }
  
  newInstance() : void {
    this.instance.active = true;
  }
  
  create() : void {
    this.categoryService.create(this.instance.label, this.category.id, false)
      .then((category:BasicCategory) => {
        this.category.descendants.push(category);
        
        this.instance.active = false;
        this.instance.label = '';
      });
  }
  
  cancel(): void {
    this.instance.active = false;
    this.instance.label = '';
  }
  
  remove(descendant: BasicCategory) {
      this.categoryService.remove(descendant.id)
       .then((response:any) => {
         this.category.descendants = this.category.descendants.filter(h => h !== descendant);        
       });
  }
  
  
  edit(descendant: BasicCategory) : void {
    this.router.navigate(['/category-option', this.category.id, descendant.id]);
  }
  
  validateName(name: string) {
    this.categoryService.validate(name, this.category.id)
      .then((response:any) => {
        this.validName = true;
      })
     .catch((error:any) => {
        this.validName = false;       
     });        
  }  
}
