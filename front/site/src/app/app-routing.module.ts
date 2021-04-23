import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {AccueilComponent} from "./accueil/accueil.component";
import {CompteComponent} from "./compte/compte.component";

const routes: Routes = [
  { path: 'accueil', component: AccueilComponent},
  { path: 'compte', component: CompteComponent},
  { path: '', redirectTo: '/accueil', pathMatch: 'full'}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
