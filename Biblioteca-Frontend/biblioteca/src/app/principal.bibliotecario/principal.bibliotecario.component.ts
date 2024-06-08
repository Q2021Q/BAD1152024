
import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { Router } from '@angular/router';


@Component({
  selector: 'app-principal.bibliotecario',
  standalone: true,
  imports: [RouterOutlet],
  templateUrl: './principal.bibliotecario.component.html',
  styleUrl: './principal.bibliotecario.component.css'
})
export class PrincipalBibliotecarioComponent {

  public nombreUser: string | null = null;


  setNombrre(): void {
    this.nombreUser = localStorage.getItem("nombre") + " " + localStorage.getItem("apellido");
  }
  constructor(private router: Router) {
    this.setNombrre();
  }
  logOut(): void {
    localStorage.clear();
    this.router.navigate(['/lector/loginLector']);
  }

}
