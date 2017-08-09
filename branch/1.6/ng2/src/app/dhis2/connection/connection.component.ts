import { Component, OnInit } from '@angular/core';

import { ConnectionService } from './connection.service'
import { Connection } from './connection'

@Component({
  selector: 'connection',
  templateUrl: './connection.component.html',
  styleUrls: []
})
export class ConnectionComponent implements OnInit {
  
  connection:Connection = new Connection();
  
  constructor(private service:ConnectionService) {}

  ngOnInit(): void {
    this.service.getInstance().then(connection => {
      this.connection = connection;
    });
  }
  
  onSubmit(): void {
    this.service.connect(this.connection).then(response => {
      console.log('Connection successful');
    });    
  }
}
