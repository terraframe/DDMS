
export class ExportResults { 
  status:string;
  metadataStats:Stats;
  dataStats:Stats;
}

export class Stats {
  created:number;
  updated:number;
  deleted:number;
  ignored:number;
  total:number;
}