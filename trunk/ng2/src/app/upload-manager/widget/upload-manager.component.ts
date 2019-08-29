import { Component, OnInit, ViewChild, ElementRef } from '@angular/core';
import { Router } from '@angular/router';
import { Http } from '@angular/http';

import { ExcelImportHistory } from './upload-manager.model';

import { EventService } from '../../core/service/core.service';
import { LocalizationService } from '../../core/service/localization.service';

import { UploadManagerService } from './upload-manager.service';

import { Observable } from 'rxjs/Observable';

declare let acp: string;
declare let hasWritePermission: boolean;

@Component({
  selector: 'upload-manager',
  templateUrl: './upload-manager.component.html',
  styleUrls: ['./upload-manager.component.css']
})
export class UploadManagerComponent implements OnInit {
  public histories: ExcelImportHistory[] = [];
  
  sortAttribute: string = "startTime";
  isAscending: boolean = false;
  pageSize: number = 10;
  pageNumber: number = 1;
  
  intervalTimeCounter: number = 0;
  intervalTime: number = 1;
  total: number = 0;
  
  hasWritePermission: boolean;

  constructor(
    private router: Router,
    private uploadManagerService: UploadManagerService,
    private localizationService: LocalizationService,
    private eventService: EventService,
    private http: Http) { }

  ngOnInit(): void {
    this.hasWritePermission = hasWritePermission;
    this.getPaginatedHistory();
    
    Observable.interval(1000).subscribe(() => {
      this.intervalTimeCounter++
    
      if (this.intervalTimeCounter >= this.intervalTime)
      {
        this.http.get(
          acp + '/dss.vector.solutions.generator.ExcelController.getPaginatedHistory.mojo'
          + '?sortAttribute=' + this.sortAttribute + "&"
          + 'isAscending=' + this.isAscending + "&"
          + 'pageSize=' + this.pageSize + "&"
          + 'pageNumber=' + this.pageNumber
        )
        .map(res => res.json() as any)
        .toPromise()
        .then(resp => {
          this.histories = resp.histories;
          this.total = resp.total;
        })
        
        this.intervalTimeCounter = 0;
      }
    });
  };
  
  onValueChange( value: any ): void
  {
    if (Number(this.pageSize) === 10)
    {
      this.intervalTime = 1;
    }
    else if (Number(this.pageSize) === 25)
    {
      this.intervalTime = 3;
    }
    else if (Number(this.pageSize) === 50)
    {
      this.intervalTime = 5;
    }
    else if (Number(this.pageSize) === 100)
    {
      this.intervalTime = 10;
    }
    
    this.getPaginatedHistory();
  }
  
  onPageChange( pageNumber: number ): void
  {
    this.pageNumber = pageNumber;
    
    this.getPaginatedHistory();
  }
  
  getPaginatedHistory() : void {
    this.uploadManagerService
      .getPaginatedHistory(this.sortAttribute, this.isAscending, this.pageSize, this.pageNumber)
      .then(resp => {
        this.histories = resp.histories;
        this.total = resp.total;
      })
  };
  
  clearHistory() : void {
    this.uploadManagerService
      .clearHistory()
      .then(response => {
        this.getPaginatedHistory();
      })
  }
  
  displayHistoryInformation(text: string) : void {
    alert(text);
  }
}
