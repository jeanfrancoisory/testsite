import { Component, OnInit } from '@angular/core';
import {NgForm} from "@angular/forms";
import {Router} from "@angular/router";
import {OverlayService} from "../service/overlay.service";

@Component({
  selector: 'app-compte',
  templateUrl: './compte.component.html',
  styleUrls: ['./compte.component.css']
})
export class CompteComponent implements OnInit {

  constructor(private router: Router, public overlay: OverlayService) { }

  ngOnInit(): void {
  }

  onCreate(form: NgForm): void{

  }

  onLeft(): void{
    this.overlay.dispose();
  }
}
