import { Component, OnInit, ViewChild, ElementRef } from '@angular/core';
import { Router } from '@angular/router';

import { FileSelectDirective, FileDropDirective, FileUploader, FileUploaderOptions } from 'ng2-file-upload/ng2-file-upload';

import { Dataset } from '../model/dataset';

import { EventService } from '../core/service/core.service';
import { LocalizationService } from '../core/service/localization.service';

import { DatasetService } from '../service/dataset.service';

import { UploadWizardComponent } from '../uploader/upload-wizard.component';

declare let acp: string;

@Component({
  selector: 'datasets',
  templateUrl: './datasets.component.html',
  styleUrls: ['./datasets.component.css']
})
export class DatasetsComponent implements OnInit {
  public datasets: Dataset[] = [];
  public forms: Dataset[] = [];
  
  public canExport: boolean;

  public uploader:FileUploader;
  public dropActive:boolean = false;

  @ViewChild(UploadWizardComponent)
  private wizard: UploadWizardComponent;
  
  @ViewChild('uploadEl') 
  private uploadElRef: ElementRef;

  constructor(
    private router: Router,
    private datasetService: DatasetService,
    private localizationService: LocalizationService,
    private eventService: EventService) { }

  ngOnInit(): void {
    this.getDatasets();
    
    let options:FileUploaderOptions =  {
      autoUpload: true,
      queueLimit: 1,
      removeAfterUpload: true,
      url: acp + '/uploader/getAttributeInformation'      
    };    
    
    this.uploader = new FileUploader(options);
    this.uploader.onBeforeUploadItem = (fileItem: any) => {
      this.eventService.start();
    };    
    this.uploader.onCompleteItem = (item:any, response:any, status:any, headers:any) => {
      this.eventService.complete();
    };    
    this.uploader.onSuccessItem = (item: any, response: string, status: number, headers: any) => {
      this.wizard.initialize(response);
    };
    this.uploader.onErrorItem = (item: any, response: string, status: number, headers: any) => {
      this.eventService.onError(response);	
    }
  };
  
  ngAfterViewInit() {
    this.uploader.onAfterAddingFile = (item => {
      this.uploadElRef.nativeElement.value = '';
    });
  }  
  
  getDatasets() : void {
    this.datasetService
      .getDatasets()
      .then(datasetCollection => {
        this.datasets = [];
        this.forms = [];
        this.canExport = datasetCollection.canExport;
        
        datasetCollection.datasets.forEach(dataset => {
          if(dataset.removable) {
            this.datasets.push(dataset);
          }
          else {
            this.forms.push(dataset);        	  
          }
        });
        
      })
  };
  
  remove(dataset: Dataset, event: any) : void {
    this.datasetService
      .remove(dataset)
      .then(collection => {
        this.datasets = [];
        this.forms = [];
          
        collection.datasets.forEach(dataset => {
          if(dataset.removable) {
            this.datasets.push(dataset);
          }
          else {
            this.forms.push(dataset);        	  
          }
        });
      });
  }
  
  edit(dataset: Dataset, event: any) : void {
    this.router.navigate(['/dataset', dataset.id]);
  }
  
  fileOver(e:any):void {
    this.dropActive = e;
  }
  
  onSuccess(data: any): void {
    if(data.datasets != null) {
      this.addDatasets(data.datasets);
    }
  }
  
  getIndex(dataset: Dataset) {
    for(var i = 0; i < this.datasets.length; i++) {
      if(this.datasets[i].id === dataset.id) {
        return i;
      }
    }
      
    return -1;
  }
  
  addDatasets(datasets:Dataset[]) {
    for(let i = 0; i < datasets.length; i++) {
      let dataset = datasets[i];
      
      let index = this.getIndex(dataset)
        
      if(index === -1) {
        this.datasets.push(dataset);        
      }
      else {
        this.datasets[index] = dataset;
      }
    }
  }
}
