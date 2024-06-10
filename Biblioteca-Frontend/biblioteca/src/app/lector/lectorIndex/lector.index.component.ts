import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { CommonModule } from '@angular/common'; // Importar CommonModule

@Component({
  selector: 'app-lector-index',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './lector.index.component.html',
  styleUrl: './lector.index.component.css',
})
export class LectorIndexComponent {
  public nombreUser: string | null = null;
  currentDate: Date = new Date();


  constructor(private router: Router) {
    this.setNombrre();
  }

  ngOnInit(): void {
    this.updateDate();
  }

  setNombrre(): void {
    this.nombreUser = localStorage.getItem("nombre") + " " + localStorage.getItem("apellido");
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
