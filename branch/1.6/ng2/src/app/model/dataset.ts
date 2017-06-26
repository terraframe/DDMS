import { DatasetAttribute } from './dataset-attribute';

export class Dataset {
  id: string;
  label: string;
  description: string;
  type: string;
  value: string;
  source: string;
  removable: boolean;
  attributes : DatasetAttribute[];
}

export class DatasetCollection {
  canExport: boolean;
  datasets: Dataset[];
}