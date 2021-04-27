import { Injectable } from '@angular/core';
import {CompteComponent} from "../compte/compte.component";
import {OverlayService} from "./overlay.service";
import {MessageComponent} from "../message/message.component";

@Injectable({
  providedIn: 'root'
})
export class DialogueService {

  private _message: string;
  private _accountCreated: boolean;

  constructor() {
    this._message = '';
    this._accountCreated = false;
  }

  get message(): string {
    return this._message;
  }

  set message(value: string) {
    this._message = value;
  }

  get accountCreated(): boolean {
    return this._accountCreated;
  }

  set accountCreated(value: boolean) {
    this._accountCreated = value;
  }

  displayMessage(mess: string): void {
    this.message = mess;
  }
}
