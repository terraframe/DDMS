import { Component, Output, EventEmitter } from '@angular/core';

@Component({
  
  selector: 'match-initial-page',
  templateUrl: './match-initial-page.component.html',
  styleUrls: []
})
export class MatchInitialPageComponent {
  @Output() public onNextPage = new EventEmitter<any>();

  constructor() { }
  
  next(targetPage: string, sourcePage: string) : void {
    this.onNextPage.emit({targetPage:targetPage, sourcePage:sourcePage});
  }
}
