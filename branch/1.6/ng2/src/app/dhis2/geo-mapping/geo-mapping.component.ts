import { Component, OnInit} from '@angular/core';
import {TreeNode} from 'angular-tree-component';

import { GeoMappingService } from './geo-mapping.service'
import { GeoMapping } from './geo-mapping'

@Component({
  selector: 'geo-mapping',
  templateUrl: './geo-mapping.component.html',
  styleUrls: ['./geo-mapping.component.css']
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
    
  setOrgUnit(mapping:GeoMapping, item: {text: string, data: any}):void {  
    mapping.orgId = item.data;
    mapping.orgLabel = item.text;
  }
  
  toggle(mapping:GeoMapping, confirmed:boolean):void {
    this.service.apply(mapping).then(data => {
      mapping.mappingId = data.mappingId;
      mapping.orgId = data.orgId;
      mapping.orgLabel = data.orgLabel;
      mapping.confirmed = data.confirmed;
    });
  }  
}
