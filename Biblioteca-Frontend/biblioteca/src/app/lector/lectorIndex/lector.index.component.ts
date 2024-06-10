import { Component, OnInit, Input,  ElementRef } from '@angular/core';
import { HttpClient, HttpErrorResponse, HttpHeaders } from '@angular/common/http';
import { catchError, tap } from 'rxjs/operators';
import { throwError } from 'rxjs';
import { CommonModule } from '@angular/common';
import { Router } from '@angular/router';

declare var bootstrap: any;

@Component({
  selector: 'app-lector-index',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './lector.index.component.html',
  styleUrl: './lector.index.component.css'
})
export class LectorIndexComponent {

  private apiUrl = 'http://localhost:8080/lector/getAllBook';
  private apiUrlFind = 'http://localhost:8080/lector/buscarLibro';
  books: any[] = [];
  loading = false;
   placeholders: any[] = []; // Arreglo de placeholders
  public nombreUser: string | null = null;
  currentDate: Date = new Date();

  @Input() book: any; // Recibe datos del libro del componente padre
  selectedBook: any;

  constructor( private router: Router, private http: HttpClient, private el: ElementRef) { 
    this.setNombrre();
    this.getAllBook();
  }
  
  ngOnInit(): void {
    this.updateDate();
  }

  updateDate(): void {
    setInterval(() => {
      this.currentDate = new Date();
    }, 1000); // Actualiza cada segundo
  }
  
  getAllBook() {
    const token = localStorage.getItem('JWT');
    const headers = new HttpHeaders().set('Authorization', `Bearer ${token}`);
    this.http.get<any[]>(this.apiUrl, { headers }).pipe(
      tap((response: any[]) => {
        // Aquí accedes a la lista de objetos
        response.forEach((book: any) => {
          this.books = response;
          console.log(book); // Aquí puedes acceder a cada objeto individualmente
        });
      }),
      catchError((error: HttpErrorResponse) => {
        console.error('Error al guardar el usuario:', error.error);
        return throwError(() => new Error('Error al guardar el usuario')); // Pasa una función que devuelve el error
      })
    ).subscribe();
}


buscarLibro(event: any): void { // Cambio en el tipo del parámetro
  const parametroFiltro = (event.target as HTMLInputElement).value; // Obtener el valor del input
  const token = localStorage.getItem('JWT');
  const headers = new HttpHeaders().set('Authorization', `Bearer ${token}`);
  const url = `${this.apiUrlFind}?parametroFiltro=${parametroFiltro}`;

  this.loading = true;
  setTimeout(() => {
    this.placeholders = Array.from({ length: 3 }); // Crea 3 placeholders
    setTimeout(() => {
      this.placeholders = []; // Limpia los placeholders
      this.loading = false;
    }, Math.random() * 1000 + 2000); // Simula un tiempo de carga de 3 a 5 segundos
  }, 0);

  this.http.get<any[]>(url, { headers }).pipe(
    tap((response: any[]) => {
      this.books = response; // Actualiza la lista de libros con la respuesta de la búsqueda
      this.loading = false;
    }),
    catchError((error: HttpErrorResponse) => {
      console.error('Error al buscar libros:', error.error);
      // Manejo del error: mostrar un mensaje al usuario, redirigir, etc.
      return throwError(() => new Error('Error al buscar libros'));
    })
  ).subscribe();

  
}


openModal(book: any) {
  this.selectedBook = book;
  const modalElement = this.el.nativeElement.querySelector('#bookDetailsModal');
  if (modalElement) {
    const modal = new bootstrap.Modal(modalElement);
    console.log("la modal");
    modal.show();
  }
}

closeModal() {
  const modalElement = this.el.nativeElement.querySelector('#bookDetailsModal');
  if (modalElement) {
    const modal = bootstrap.Modal.getInstance(modalElement);
    if (modal) {
      modal.hide();
    }
  }
}

 setNombrre(): void{
  this.nombreUser = localStorage.getItem("nombre") + " " + localStorage.getItem("apellido");
 }

logOut(): void{
  localStorage.clear();
    this.router.navigate(['/lector/loginLector']);
}
}
