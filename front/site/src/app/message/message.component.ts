import { Component, OnInit } from '@angular/core';
import {DialogueService} from "../service/dialogue.service";
import {OverlayService} from "../service/overlay.service";

@Component({
  selector: 'app-message',
  templateUrl: './message.component.html',
  styleUrls: ['./message.component.css']
})
export class MessageComponent implements OnInit {

  constructor(public dialogueService: DialogueService, private overlay: OverlayService) { }

  ngOnInit(): void {
  }

  onLeft(): void {
    this.overlay.dispose();
  }

}
