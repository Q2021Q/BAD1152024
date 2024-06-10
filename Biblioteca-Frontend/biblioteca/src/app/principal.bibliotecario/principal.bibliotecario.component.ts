
import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { Router } from '@angular/router';
import { CommonModule } from '@angular/common'; // Importar CommonModule


@Component({
  selector: 'app-principal.bibliotecario',
  standalone: true,
  imports: [RouterOutlet,CommonModule],
  templateUrl: './principal.bibliotecario.component.html',
  styleUrl: './principal.bibliotecario.component.css'
})
export class PrincipalBibliotecarioComponent {

  public nombreUser: string | null = null;
  currentDate: Date = new Date();


  setNombrre(): void {
    this.nombreUser = localStorage.getItem("nombre") + " " + localStorage.getItem("apellido");
  }
  constructor(private router: Router) {
    this.setNombrre();
  }

  ngOnInit(): void {
    this.updateDate();
  }

  logOut(): void {
    localStorage.clear();
    this.router.navigate(['/lector/loginLector']);
  }

  updateDate(): void {
    setInterval(() => {
      this.currentDate = new Date();
    }, 1000); // Actualiza cada segundo
  }

}
