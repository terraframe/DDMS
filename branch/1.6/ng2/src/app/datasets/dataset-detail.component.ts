import { Component, EventEmitter, Input, OnInit, OnChanges, Output, Inject, ViewChild } from '@angular/core';
import { ActivatedRoute, Params, Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Router } from '@angular/router';
import { Location } from '@angular/common';

import { Observable } from 'rxjs/Observable';
import 'rxjs/add/operator/switchMap';

import { Dataset, DatasetAttribute, IndicatorField } from '../model/dataset';
import { BasicCategory } from '../model/category';

import { EventService } from '../service/core.service';
import { DatasetService } from '../service/dataset.service';

import { IndicatorModalComponent } from './indicator-modal.component';

export class DatasetResolver implements Resolve<Dataset> {
  constructor(@Inject(DatasetService) private datasetService: DatasetService, @Inject(EventService) private eventService: EventService) {}
  
  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot):Promise<Dataset> {
    return this.datasetService.edit(route.params['id'])
      .catch((error:any) => {
        this.eventService.onError(error); 
        
        return Promise.reject(error);
      });    
  }
}

@Component({
  
  selector: 'dataset-detail',
  templateUrl: './dataset-detail.component.html',
  styleUrls: []
})
export class DatasetDetailComponent implements OnInit {
  @Input() dataset: Dataset;
  @Output() close = new EventEmitter();
  
  @ViewChild(IndicatorModalComponent)
  private modal: IndicatorModalComponent;


  validName: boolean = true;

  constructor(
    private datasetService: DatasetService,
    private router: Router,      
    private route: ActivatedRoute,
    private location: Location) {
  }

  ngOnInit(): void {
    this.dataset = this.route.snapshot.data['dataset'];
  }
  
  cancel(): void {
    this.datasetService.unlock(this.dataset)
     .then(response => {
       this.goBack(this.dataset);
     })
  } 
  
  onSubmit(): void {
    this.datasetService.apply(this.dataset)
      .then(dataset => {
        this.goBack(dataset);
      })
  }
  
  open(category: BasicCategory, event: any) : void {
    this.router.navigate(['/category', category.id]);
  }
  
  goBack(dataset : Dataset): void {
    this.close.emit(dataset);
    
    this.location.back();
  }
  
  addIndicator() : void {
    let attributes:DatasetAttribute[] = [];
    
  for (let i = 0; i < this.dataset.attributes.length; i++) {
    let attribute:DatasetAttribute = this.dataset.attributes[i];
    
      if(attribute.numeric) {
        attributes.push(attribute);        
    }
  }        
    
    this.modal.initialize(this.dataset.aggregations, attributes);
  }
  
  onIndicatorSuccess(field:IndicatorField): void {
    if(this.dataset.indicators === undefined) {
      this.dataset.indicators = [];
    }    
    
    this.dataset.indicators.push(field);    
  }
}
