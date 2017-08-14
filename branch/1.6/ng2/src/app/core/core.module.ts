import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule} from '@angular/forms';
import { HttpModule, XHRBackend, RequestOptions, Http} from '@angular/http';

import { LocalizeComponent } from './localize/localize.component';
import { LoadingBarComponent } from './loading-bar/loading-bar.component';
import { MessageComponent } from './message/message.component';
import { AsyncValidator } from './async-validator.directive';
import { FunctionValidator } from './function-validator.directive';
import { KeysPipe } from './keys.pipe';
import { LocalizePipe } from './localize/localize.pipe';

import { DateFieldDirective } from './date-field.directive';
import { TermFieldComponent } from './term-field/term-field.component';
import { GeoFieldComponent } from './geo-field/geo-field.component';
import { BooleanFieldComponent } from './boolean-field/boolean-field.component';

import { EventService, IdService, BasicService} from './service/core.service';
import { LocalizationService } from './service/localization.service';
import { EventHttpService } from './service/event-http.service';

import { AutoCompleteDirective } from './autocomplete/auto-complete.directive';
import { AutoCompleteComponent } from './autocomplete/auto-complete.component';


@NgModule({
  imports: [
    BrowserModule,
    FormsModule,  
    HttpModule
  ],
  declarations: [
    LocalizeComponent,
    LoadingBarComponent,
    MessageComponent,
    AsyncValidator,
    FunctionValidator,
    KeysPipe,
    LocalizePipe,
    
    DateFieldDirective,
    TermFieldComponent,   
    GeoFieldComponent,
    BooleanFieldComponent,
    AutoCompleteDirective,
    AutoCompleteComponent    
  ],
  providers: [
    LocalizationService,
    IdService,
    EventService,
    { 
      provide : EventHttpService,
      useFactory: (xhrBackend: XHRBackend, requestOptions: RequestOptions, service: EventService) => {
        return new EventHttpService(xhrBackend, requestOptions, service)
      },
      deps: [XHRBackend, RequestOptions, EventService]
    }   
  ],
  exports: [
    LocalizeComponent,
    LoadingBarComponent,
    MessageComponent,
    AsyncValidator,
    FunctionValidator,
    KeysPipe,
    LocalizePipe,
    
    DateFieldDirective,
    TermFieldComponent,   
    GeoFieldComponent,
    BooleanFieldComponent,
    AutoCompleteDirective,
    AutoCompleteComponent    
  ],
  entryComponents: [AutoCompleteComponent]  
})
export class CoreModule { }
