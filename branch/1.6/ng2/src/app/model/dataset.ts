import { Pair } from './pair';

import { BasicCategory } from '../model/category';

export class DatasetAttribute { 
  id: string;
  label: string;
  type: string
  numeric: boolean;  
  selected: boolean;
  root: BasicCategory;
}

export class Dataset {
  id: string;
  label: string;
  description: string;
  type: string;
  value: string;
  source: string;
  removable: boolean;
  attributes : DatasetAttribute[];
  aggregations: Pair[];
  indicators: IndicatorField[];
}

export class DatasetCollection {
  canExport: boolean;
  datasets: Dataset[];
}

export class Indicator {
  aggregation: string;
  attribute: string; 
}

export class IndicatorField {
  label: string 
  left: Indicator;
  operation : string;
  right: Indicator; 
}
