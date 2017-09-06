import {Component, Input, Output, EventEmitter, AfterViewInit, OnDestroy} from '@angular/core';

declare var MDSS:any;
declare var dss:any;
declare var acp:string;

@Component({    
  selector: 'geo-field',
  templateUrl: './geo-field.component.html',
  styleUrls: []
})
export class GeoFieldComponent implements AfterViewInit, OnDestroy {
  private search:any;
  private cxpath:string = acp;

  @Input() attribute:string = '';  
  @Input() root:string = "";
  @Input() universal:string = "";

  @Input() model:any = {id:'', geoId:''};
  
  @Output() modelChange = new EventEmitter<any>();

  constructor(){
  }
  
  ngAfterViewInit():any {
    let geoInput = document.getElementById(this.attribute);    
    
    var geoPicker = new MDSS.GeoPicker(true, this.root);
    geoPicker.setFilter(this.universal);
    
    this.model = {id:'', label:'', geoId:''};
    
    this.search = new MDSS.GeoSearch(geoInput, geoPicker.getGeoFilterCriteria(), geoPicker);
    this.search.setFilter(this.universal);
    this.search.addListener(this.updateModel.bind(this));
    
    geoPicker.render();    
  }
  
  updateModel(event:any):void {
    if(event.getType() === MDSS.Event.AFTER_VALID_SELECTION) {
      let value = event.getValue();
      let selection = value.selected;
          
      if(selection !== undefined && selection instanceof dss.vector.solutions.geo.GeoEntityView) {
        this.model.id = selection.getGeoEntityId();  
        this.model.geoId = selection.getGeoId();  
             
        this.modelChange.emit(this.model);         
      }
    }      
  }
  
  ngOnDestroy() {
  }    
}