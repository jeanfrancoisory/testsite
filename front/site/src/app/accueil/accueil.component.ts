import {Component, Injectable, OnInit} from '@angular/core';
import {Router} from "@angular/router";
import {HttpClient} from "@angular/common/http";
import {NgForm} from "@angular/forms";
import {OverlayService} from "../service/overlay.service";
import {CompteComponent} from "../compte/compte.component";
import {Personne} from "../model/personne";
import {DialogueService} from "../service/dialogue.service";
import {CommunicationService} from "../service/communication.service";
import {MessageComponent} from "../message/message.component";

@Component({
  selector: 'app-accueil',
  templateUrl: './accueil.component.html',
  styleUrls: ['./accueil.component.css']
})
@Injectable()
export class AccueilComponent implements OnInit {

  constructor(private router: Router,
              public overlay: OverlayService,
              private communication: CommunicationService,
              private dialogueService: DialogueService) {
  }

  ngOnInit(): void {
  }

  onConnect(form: NgForm): void {
    this.communication.getPersonne(form.value.email, form.value.password)
      .then(returnedData => {
        console.log(returnedData);
        this.communication.personne = (returnedData as Personne);
        this.router.navigate(['mainpage']);
      }, (error) => {
        console.log("error");
        this.dialogueService.displayMessage("Email ou Mot-de-passe inconnus");
        this.displayMessage();
      });
    form.resetForm();
  }

  onCreateNewAccount(): void {
    this.overlay.open(CompteComponent, false);
  }

  displayMessage(): void {
    this.overlay.open(MessageComponent, true);
  }

}
