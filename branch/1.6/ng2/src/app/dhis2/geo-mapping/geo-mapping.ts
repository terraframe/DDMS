
export class GeoMapping { 
  id:string;
  name:string;
  mappingId:string;
  orgId:string;
  orgLabel:string;
  confirmed:boolean;
  hasChildren:boolean;
}

export class UniversalMapping {
  id:string;
  label:string;    
  levelId:string;    
  confirmed:boolean;
}

export class OrgLevel {
  id:string;
  label:string;    
}