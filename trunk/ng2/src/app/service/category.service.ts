import { Injectable } from '@angular/core';
import { Headers, Http, Response, URLSearchParams } from '@angular/http';

import 'rxjs/add/operator/toPromise';

import { EventService, BasicService } from '../core/service/core.service';
import { EventHttpService } from '../core/service/event-http.service';

import { Category, BasicCategory } from '../model/category';

declare var acp: any;

@Injectable()
export class CategoryService extends BasicService {
  
  constructor(service: EventService, private ehttp: EventHttpService, private http: Http) {
    super(service); 
  }
  
  getAll(): Promise<BasicCategory[]> {
    return this.ehttp
      .get(acp + '/category/all')
      .toPromise()
      .then(response => {
        return response.json() as BasicCategory[];
      })
      .catch(this.handleError.bind(this));
  }
  
  edit(parentId: string, id : string): Promise<Category> {
    let headers = new Headers({
      'Content-Type': 'application/json'
    });    
    
    return this.ehttp
      .post(acp + '/category/edit', JSON.stringify({parentId:parentId, id:id}), { headers: headers })
      .toPromise()
      .then(response => {
        return response.json() as Category;
      })      
      .catch(this.handleError.bind(this));
  }
  
  get(id : string): Promise<Category> {
    let headers = new Headers({
      'Content-Type': 'application/json'
    });    
    
    return this.ehttp
    .post(acp + '/category/get', JSON.stringify({id:id}), { headers: headers })
    .toPromise()
    .then(response => {
      return response.json() as Category;
    })
    .catch(this.handleError.bind(this));
  }
  
  unlock(category: Category): Promise<Response> {
    let headers = new Headers({
      'Content-Type': 'application/json'
    });    
    
    return this.ehttp
      .post(acp + '/category/unlock', JSON.stringify({id:category.id}), { headers: headers })
      .toPromise()
      .catch(this.handleError.bind(this));
  }
  
  apply(config: any): Promise<Response> {
    let headers = new Headers({
      'Content-Type': 'application/json'
    });    
       
    return this.ehttp
    .post(acp + '/category/apply', JSON.stringify({config:config}), { headers: headers })
    .toPromise()
    .catch(this.handleError.bind(this));
  }
  
  remove(id: string): Promise<Response> {
    let headers = new Headers({
      'Content-Type': 'application/json'
    });
    
    return this.ehttp
      .post(acp + '/category/remove', JSON.stringify({id:id}), { headers: headers })
      .toPromise()
      .catch(this.handleError.bind(this));
  }
  
  validate(name: string, id:string): Promise<Response> {
    
    let params: URLSearchParams = new URLSearchParams();
    params.set('name', name);
    params.set('id', id);    
    
    return this.http
      .get(acp + '/category/validate', {search: params})
      .toPromise();
  }
  
    
  create(label: string, parentId: string, validate: boolean): Promise<BasicCategory> {
    let headers = new Headers({
      'Content-Type': 'application/json'
    });    
    
    let option = {label:label, parentId:parentId, validate:validate};
    
    return this.ehttp
    .post(acp + '/category/create', JSON.stringify({option:option}), { headers: headers })
    .toPromise()
    .then((response:any) => {
      return response.json() as BasicCategory;
    })          
    .catch(this.handleError.bind(this));
  }
  
  update(category:Category): Promise<Category> {
    let headers = new Headers({
      'Content-Type': 'application/json'
    });    
    
    return this.ehttp
    .post(acp + '/category/update', JSON.stringify({category:category}), { headers: headers })
    .toPromise()
    .then((response:any) => {
      return response.json() as BasicCategory;
    })          
    .catch(this.handleError.bind(this));
  }
  
}
