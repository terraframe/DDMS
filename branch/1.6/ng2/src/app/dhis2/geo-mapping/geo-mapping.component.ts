import { Component, OnInit} from '@angular/core';
import {TreeNode} from 'angular-tree-component';

import { Observable } from 'rxjs/Observable';
import 'rxjs/add/observable/of';
import { TypeaheadMatch } from 'ngx-bootstrap/typeahead';

import { GeoMappingService } from './geo-mapping.service'
import { GeoMapping } from './geo-mapping'

@Component({
  selector: 'geo-mapping',
  templateUrl: './geo-mapping.component.html',
  styleUrls: []
})
export class GeoMappingComponent implements OnInit{
  
  nodes:GeoMapping[] = [];
  options:any = {};
  
  constructor(private service:GeoMappingService) {
   this.options = {
     getChildren: (node:TreeNode) => {
       return this.service.getChildren(node.id);
     }
   };     
  }
  
  ngOnInit():void {
    this.service.getRoots().then(nodes => {
      this.nodes = nodes;
    });  
  }
  
  source = (keyword: string) => {
    return this.service.search(keyword);
  }
  
  toggle(mapping:GeoMapping, confirmed:boolean):void {
    this.service.apply(mapping).then(data => {
      mapping.mappingId = data.mappingId;
      mapping.orgId = data.orgId;
      mapping.orgLabel = data.orgLabel;
      mapping.confirmed = data.confirmed;
    });
  }
  
  onSelect(mapping:GeoMapping, match:TypeaheadMatch):void {
    let item = match.item;
    
    mapping.orgId = item.data;
    mapping.orgLabel = item.text;    
  }
  
  setup(mapping:GeoMapping):Observable<any> {
    return Observable.create((observer: any) => {
      // Runs on every search
      observer.next(mapping.orgLabel);
    })
    .mergeMap((token: string) => this.service.search(token));     
  }
}
