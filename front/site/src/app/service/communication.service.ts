import { Injectable } from '@angular/core';
import {OverlayService} from "./overlay.service";
import {HttpClient} from "@angular/common/http";
import {error} from "@angular/compiler/src/util";
import {Personne} from "../model/personne";

@Injectable({
  providedIn: 'root'
})
export class CommunicationService {

  private _personne: Personne;

  constructor(private http: HttpClient) {
    this._personne = new Personne();
  }

  getPersonne(email: string, password: string): Promise<any> {
    return this.http
      .get(`site/pers/${email}/${password}`,{})
      .toPromise();
  }

  postAccount(firstname: string, lastname: string, email: string, password: string): Promise<any> {
    return this.http
      .post(`site/compte/${firstname}/${lastname}/${email}/${password}`,{},{})
      .toPromise();
  }

  get personne(): Personne {
    return this._personne;
  }

  set personne(value: Personne) {
    this._personne = value;
  }
}
