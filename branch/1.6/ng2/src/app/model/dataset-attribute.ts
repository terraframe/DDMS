import { BasicCategory } from '../model/category';

export class DatasetAttribute { 
  id: string;
  label: string;
  type: string
  selected: boolean;
  root: BasicCategory;
}
