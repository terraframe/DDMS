import { Dataset } from '../model/dataset';
import { Pair } from '../model/pair';

export class Universal {
  value: string;
  label: string; 
}

export class Country {
  options : Universal[];
  value: string;
  label: string;
}

export class Options {
  countries : Country[];  
}

export class Classifier {
  value: string;
  label: string;  
}

export class Field {
  columnType: string;
  fieldPosition: number;
  name: string;
  aggregatable: boolean;
  accepted: boolean;
  label: string;
  type: string;

  // Properties for category fields 
  categoryLabel: string;
  rootType: string;
  root: Pair;

  // Properties for location fields
  universal: string;
  assigned: boolean;
}

export class LocationAttribute {
  label: string;
  name: string;
  universal: string;
  fields: { [key:string]:string};
  id: string;
  editing: boolean;
}

export class Locations {
  ids: string[];
  values: { [key:string]:LocationAttribute}; 
}

export class CoordinateAttribute {
  label : string;
  latitude : string;
  longitude : string;
  featureLabel : string;
  location : string;
  featureId : string;
  id : string;  
  universal: string;
}

export class Coordinates {
  ids: string[];
  values: { [key:string]:CoordinateAttribute};
}

export class Sheet {
  value: string;
  label: string;
  name: string;
  description: string;
  source: string;
  replaceExisting: boolean;
  exists: boolean;

  fields : Field[];
  attributes: Locations;
  coordinates: CoordinateAttribute[];
  categories: any;
  matches: any[];
}

export class LocationExclusion {
  constructor(public id: string, public universal: string, public locationLabel: string, public parentId: string) {}
}

export class Workbook {
  filename: string;
  directory: string;
  sheets: Sheet[];
  locationExclusions: LocationExclusion[];
  categoryExclusion: { [key:string]:string[]};
}

export class UploadInformation {
  options: Options;
  classifiers: Classifier[];
  information: Workbook;
}

export class Step {
  constructor(public label: string, public page: string) {}
}

export class Snapshot {

  constructor(public page: string, public sheet: Sheet) {}
}

export class Page {
  snapshot: Sheet;

  hasNext: boolean;
  isReady: boolean;
  confirm: boolean;
  layout: string;

  constructor(public name: string, public prev: Page) {
    this.layout = 'holder';
    this.hasNext = true;
    this.isReady = false;
    this.confirm = false;
  }
}

export class LocationContext {
  label: string;
  universal: string;
}

export class LocationProblem {
  type: string;
  label: string;
  parentId: string;
  universalId: string;
  universalLabel: string;
  universalType: string;
  context: LocationContext[];
  resolved: boolean;
  synonym: {
    id:string,
    geoId:string
  };
  action: any;
}

export class CategoryProblem {
  label: string;
  mdAttributeId: string;
  categoryId: string;
  optionId: string;
  resolved: boolean;
  synonym: Pair;
  action: any;
}

export class Problems {
  locations  : LocationProblem[];
  categories  : CategoryProblem[];
  options: { [key:string]:Pair[]};
}

export class DatasetResponse {
  success: boolean;
  datasets: Dataset[];
  sheets: Sheet[];
  problems: Problems;
}

export class Label {
  label: string;
}

export class GeoSynonym {
  synonymId: string;
  label: string;
  ancestors: Label[];
}

export class ClassifierSynonym {
  synonymId: string;
  label: string;
}

export class Entity {
  entityId: string;
}


