import { Injectable } from '@angular/core';
import { Headers, Http, Response, URLSearchParams } from '@angular/http';

import 'rxjs/add/operator/toPromise';

import { EventService, BasicService } from '../../core/service/core.service';
import { EventHttpService } from '../../core/service/event-http.service';

import { ExcelImportHistory } from './upload-manager.model';

declare var acp: any;

@Injectable()
export class UploadManagerService extends BasicService {

  constructor(service: EventService, private ehttp: EventHttpService, private http: Http) { super(service); }

  getAllHistory(): Promise<ExcelImportHistory[]> {
    return this.ehttp
      .get(acp + '/dss.vector.solutions.generator.ExcelController.getAllHistory.mojo')
      .toPromise()
      .then(response => {
        return response.json() as ExcelImportHistory[];
      })
      .catch(this.handleError.bind(this));
  }
}
