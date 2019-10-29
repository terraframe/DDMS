import { Synonym } from './synonym';

export class BasicCategory {
  id: string;
  label: string; 
}

export class Category extends BasicCategory {
  descendants : BasicCategory[];
  synonyms : Synonym[];
  siblings : BasicCategory[];
}