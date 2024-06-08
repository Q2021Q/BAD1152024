import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-lector-index',
  standalone: true,
  imports: [],
  templateUrl: './lector.index.component.html',
  styleUrl: './lector.index.component.css'
})
export class LectorIndexComponent {
  public nombreUser: string | null = null;
  constructor( private router: Router) { 
    this.setNombrre();
  }

 setNombrre(): void{
  this.nombreUser = localStorage.getItem("nombre") + " " + localStorage.getItem("apellido");
 }

logOut(): void{
  localStorage.clear();
    this.router.navigate(['/lector/loginLector']);
}
}
