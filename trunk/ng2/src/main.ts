import { platformBrowserDynamic } from '@angular/platform-browser-dynamic';
import { enableProdMode } from '@angular/core';

import { AppModule } from './app/app.module';
import { Dhis2Module } from './app/dhis2/dhis2.module';

declare var appname: string;

if (process.env.ENV === 'production') {
  enableProdMode();
}

if(appname !== undefined && appname === 'dhis2-app' ) {
  platformBrowserDynamic().bootstrapModule(Dhis2Module)
    .then(success => console.log('DHIS2 Bootstrap success'))
    .catch(error => console.log(error));  
}
else {
  platformBrowserDynamic().bootstrapModule(AppModule)
    .then(success => console.log('App bootstrap success'))
    .catch(error => console.log(error));  
}
