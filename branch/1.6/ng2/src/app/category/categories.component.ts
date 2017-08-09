import { Component, OnInit, ViewChild, ElementRef } from '@angular/core';
import { Router } from '@angular/router';

import { Category, BasicCategory } from '../model/category';

import { EventService } from '../core/service/core.service';
import { CategoryService } from '../service/category.service';

class Instance {
  active: boolean;
  label: string;   
}

@Component({
  
  selector: 'categories',
  templateUrl: './categories.component.html',
  styleUrls: []
})
export class CategoriesComponent implements OnInit {
  categories: BasicCategory[];
  instance : Instance = new Instance();  

  constructor(
    private router: Router,
    private eventService: EventService,
    private categoryService: CategoryService) { }

  ngOnInit(): void {
    this.getCategories();    
  }
  
  getCategories() : void {
    this.categoryService
      .getAll()
      .then(categories => {
        this.categories = categories
      })
  }
  
  remove(category: BasicCategory) : void {    
    this.categoryService
      .remove(category.id)
      .then(response => {
        this.categories = this.categories.filter(h => h !== category);    
      });
  }
  
  edit(category: BasicCategory) : void {
    this.router.navigate(['/category', category.id]);
  }
  
  newInstance() : void {
    this.instance.active = true;
  }
  
  create() : void {
    this.categoryService.create(this.instance.label, '', true)
      .then((category:BasicCategory) => {
        this.categories.push(category);
        
        this.instance.active = false;
        this.instance.label = '';
      });
  }
  
  cancel(): void {
    this.instance.active = false;
    this.instance.label = '';
  }  
}
