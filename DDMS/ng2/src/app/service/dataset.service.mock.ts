import { Injectable } from '@angular/core';
import { Response} from '@angular/http';
import 'rxjs/add/operator/toPromise';

import { Dataset } from '../model/dataset';

@Injectable()
export class MockDatasetService {

  getMockDataset(): Dataset {
    let dataset = new Dataset();
    dataset.id = 'test-id';
    dataset.label = 'Test Label';
    dataset.description = 'Test Description';
    dataset.source = 'Test Source';
    
    return dataset;
  }
  
  getDatasets(): Promise<Dataset[]> {
    return new Promise((resolve, reject) => {     
      let datasets:Dataset[] = [];
      datasets.push(this.getMockDataset());
      
      resolve(datasets);  
    });  
  }
  
  edit(id : string): Promise<Dataset> {
    return new Promise((resolve, reject) => {     
      resolve(this.getMockDataset());  
    });  
  }
  
  unlock(dataset: Dataset): Promise<Response> {
    return new Promise((resolve, reject) => {     
      resolve(null);  
    });  
  }
  
  apply(dataset: Dataset): Promise<Dataset> {
    return new Promise((resolve, reject) => {     
      resolve(this.getMockDataset());  
    });  
  }
  
  remove(dataset: Dataset): Promise<Response> {
    return new Promise((resolve, reject) => {     
      resolve(null);  
    });  
  }  
      
  validateDatasetName(name: string, id: string): Promise<Response> {
    return new Promise((resolve, reject) => {     
      resolve(null);  
    });  
  }  
  
  xport(id : string): Promise<Dataset> {
    return new Promise((resolve, reject) => {     
      resolve(this.getMockDataset());  
    });  
  }
}
