import { Component } from '@angular/core';

declare var acp: any;

@Component({
  
  selector: 'my-app',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  context: string;

  constructor() {
    this.context = acp as string;
  }
}
