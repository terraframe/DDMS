import { Injectable } from '@angular/core';
import { Headers, Http, Response, URLSearchParams } from '@angular/http';

import 'rxjs/add/operator/toPromise';

import { EventService, BasicService } from '../core/service/core.service';
import { EventHttpService } from '../core/service/event-http.service';
import { AnalyticsService } from '../core/service/analytics.service';

import { Category, BasicCategory } from '../model/category';

declare var acp: any;

@Injectable()
export class CategoryService extends BasicService {
  
  constructor(service: EventService, private ehttp: EventHttpService, private http: Http, private analyticsService: AnalyticsService) {
    super(service); 
  }
  
  getAll(): Promise<BasicCategory[]> {
    return this.ehttp
      .get(acp + '/category/all')
      .toPromise()
      .then(response => {
    	  
        this.analyticsService.pushAalyticsTrackingTagEvent("SUCCESS", "/category/all", "post", {});

        return response.json() as BasicCategory[];
      })
      .catch(e => {
    		  this.analyticsService.pushAalyticsTrackingTagEvent("FAILURE", "/category/all", "post", {});
    		  this.handleError.bind(this)
      });
  }
  
  edit(parentId: string, id : string): Promise<Category> {
    let headers = new Headers({
      'Content-Type': 'application/json'
    });    
    
    return this.ehttp
      .post(acp + '/category/edit', JSON.stringify({parentId:parentId, id:id}), { headers: headers })
      .toPromise()
      .then(response => {
    	
    	this.analyticsService.pushAalyticsTrackingTagEvent("SUCCESS", "/category/edit", "post", {parentId:parentId, id:id});
        
    	return response.json() as Category;
      })      
      .catch(e => {
    	    	this.analyticsService.pushAalyticsTrackingTagEvent("FAILURE", "/category/edit", "post", {parentId:parentId, id:id});
    	    	this.handleError.bind(this)
      });
  }
  
  get(id : string): Promise<Category> {
    let headers = new Headers({
      'Content-Type': 'application/json'
    });    
    
    return this.ehttp
    .post(acp + '/category/get', JSON.stringify({id:id}), { headers: headers })
    .toPromise()
    .then(response => {
    	
      this.analyticsService.pushAalyticsTrackingTagEvent("SUCCESS", "/category/get", "post", {id:id});

      return response.json() as Category;
    })
    .catch(e => {
    		this.analyticsService.pushAalyticsTrackingTagEvent("FAILURE", "/category/get", "post", {id:id});
    		this.handleError.bind(this)
    });
  }
  
  unlock(category: Category): Promise<Response> {
    let headers = new Headers({
      'Content-Type': 'application/json'
    });    
    
    this.analyticsService.pushAalyticsTrackingTagEvent("SEND", "/category/unlock", "post", {id:category.id});
    
    return this.ehttp
      .post(acp + '/category/unlock', JSON.stringify({id:category.id}), { headers: headers })
      .toPromise()
      .catch(e => {
    		    this.analyticsService.pushAalyticsTrackingTagEvent("FAILURE", "/category/unlock", "post", {id:category.id});

    		    this.handleError.bind(this)
      });
  }
  
  apply(config: any): Promise<Response> {
    let headers = new Headers({
      'Content-Type': 'application/json'
    });    
       
    this.analyticsService.pushAalyticsTrackingTagEvent("SEND", "/category/apply", "post", {config:config});

    return this.ehttp
    .post(acp + '/category/apply', JSON.stringify({config:config}), { headers: headers })
    .toPromise()
    .catch(e => {
    		this.analyticsService.pushAalyticsTrackingTagEvent("FAILURE", "/category/apply", "post", {config:config});
    		this.handleError.bind(this)
    });
  }
  
  remove(id: string): Promise<Response> {
    let headers = new Headers({
      'Content-Type': 'application/json'
    });
    
    this.analyticsService.pushAalyticsTrackingTagEvent("SEND", "/category/apply", "post", {id:id});
    
    return this.ehttp
      .post(acp + '/category/remove', JSON.stringify({id:id}), { headers: headers })
      .toPromise()
      .catch(e => {
    		  this.analyticsService.pushAalyticsTrackingTagEvent("FAILURE", "/category/apply", "post", {id:id});
    		  this.handleError.bind(this)
      });
  }
  
  validate(name: string, id:string): Promise<Response> {
    
    let params: URLSearchParams = new URLSearchParams();
    params.set('name', name);
    params.set('id', id);  
    
    this.analyticsService.pushAalyticsTrackingTagEvent("SEND", "/category/validate", "post", params);
    
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
        
      this.analyticsService.pushAalyticsTrackingTagEvent("SUCCESS", "/category/create", "post", {option:option});

      return response.json() as BasicCategory;
    })          
    .catch(e => {
    		this.analyticsService.pushAalyticsTrackingTagEvent("FAILURE", "/category/create", "post", {option:option});
    		this.handleError.bind(this)
    });
  }
  
  update(category:Category): Promise<Category> {
    let headers = new Headers({
      'Content-Type': 'application/json'
    });    
    
    return this.ehttp
    .post(acp + '/category/update', JSON.stringify({category:category}), { headers: headers })
    .toPromise()
    .then((response:any) => {
    	
      this.analyticsService.pushAalyticsTrackingTagEvent("SUCCESS", "/category/update", "post", {category:category});

      return response.json() as BasicCategory;
    })          
    .catch(e => {
    		this.analyticsService.pushAalyticsTrackingTagEvent("FAILURE", "/category/update", "post", {category:category});
    		this.handleError.bind(this)
    });
  }
  
}
