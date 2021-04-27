import { Component, OnInit } from '@angular/core';
import {NgForm} from "@angular/forms";
import {Router} from "@angular/router";
import {OverlayService} from "../service/overlay.service";
import {HttpClient} from "@angular/common/http";
import {DialogueService} from "../service/dialogue.service";
import {MessageComponent} from "../message/message.component";
import {CommunicationService} from "../service/communication.service";
import {error} from "@angular/compiler/src/util";

@Component({
  selector: 'app-compte',
  templateUrl: './compte.component.html',
  styleUrls: ['./compte.component.css']
})
export class CompteComponent implements OnInit {

  constructor(private router: Router,
              public overlay: OverlayService,
              private dialogueService: DialogueService,
              private communication: CommunicationService) { }

  ngOnInit(): void {
  }

  onCreate(form: NgForm): void{
    this.communication
      .postAccount(form.value.firstname, form.value.lastname, form.value.email, form.value.password)
      .then(returnedData => {
        console.log(returnedData);
        this.overlay.dispose();
        this.dialogueService.displayMessage("Compte créé");
        this.displayMessage();
      }, (error) => {
        if(error.status===409) {
          this.overlay.dispose();
          this.dialogueService.displayMessage("Cet email est déjà \n associé à un compte");
          this.displayMessage();
        }else {
          this.overlay.dispose();
          this.dialogueService.displayMessage("Une erreur est survenue. \n Veuillez recommencer");
          this.displayMessage();
        }
      });
    form.resetForm();
  }

  onLeft(): void{
    this.overlay.dispose();
  }

  displayMessage(): void {
    this.overlay.open(MessageComponent, true);
  }
  
}
