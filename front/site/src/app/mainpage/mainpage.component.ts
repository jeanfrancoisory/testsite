import { Component, OnInit } from '@angular/core';
import {CommunicationService} from "../service/communication.service";

@Component({
  selector: 'app-mainpage',
  templateUrl: './mainpage.component.html',
  styleUrls: ['./mainpage.component.css']
})
export class MainpageComponent implements OnInit {

  constructor(public communication: CommunicationService) { }

  ngOnInit(): void {
  }

}
