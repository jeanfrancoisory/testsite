import {Component, Injectable, OnInit} from '@angular/core';
import {Router} from "@angular/router";
import {HttpClient} from "@angular/common/http";
import {NgForm} from "@angular/forms";
import {OverlayService} from "../service/overlay.service";
import {CompteComponent} from "../compte/compte.component";

@Component({
  selector: 'app-accueil',
  templateUrl: './accueil.component.html',
  styleUrls: ['./accueil.component.css']
})
@Injectable()
export class AccueilComponent implements OnInit {

  private _t: String = "non";

  constructor(private router: Router, public overlay: OverlayService) { }

  get t(): String {
    return this._t;
  }

  set t(value: String) {
    this._t = value;
  }

  ngOnInit(): void {
    //this.t = this.testService.tes;
  }

  onConnect(form: NgForm): void {
    console
      .log("o");
  }

  onCreateNewAccount(): void {
    this.overlay.open(CompteComponent, true);
  }

}
