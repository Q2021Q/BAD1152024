import { Component, OnInit } from '@angular/core';
import { HttpClient, HttpErrorResponse, HttpHeaders } from '@angular/common/http';
import { catchError, tap } from 'rxjs/operators';
import { throwError } from 'rxjs';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { Router } from '@angular/router';

// imports
import { CategoriaFormComponent } from '../form/categoria.form/categoria.form.component';

@Component({
  selector: 'app-categoria.recurso',
  standalone: true,
  imports: [ReactiveFormsModule, CommonModule, CategoriaFormComponent],
  templateUrl: './categoria.recurso.component.html',
  styleUrl: './categoria.recurso.component.css'
})
export class CategoriaRecursoComponent implements OnInit{

  //constructor(private http: HttpClient) {}
  constructor(private fb: FormBuilder, private http: HttpClient, private router: Router) { 
    
  }

  public addCatRe!: FormGroup;
  ngOnInit(): void {
    // Inicializa el formulario en el ngOnInit
    this.addCatRe = this.fb.group({
      nombreCategoria: ['', [Validators.required, Validators.minLength(1)]],
      descripcionCategoria: ['', [Validators.required, Validators.minLength(10)]]
    });
  }

  addCatRecurso() {

    // Verifica si el formulario es válido antes de enviar la solicitud
    if (this.addCatRe.valid) {
      console.log("json ", this.addCatRe.value);
      const token = localStorage.getItem('JWT');
      console.log("jwt  ", token);

      const headers = new HttpHeaders().set('Authorization', `Bearer ${token}`);
      this.http.post('http://localhost:8080/bibliotecario/addcategoriarecurso', this.addCatRe.value, { headers }).pipe(
        tap((response: any) => {
        if (response.token) {
         console.log("exito")
        }
        }),
        catchError((error: HttpErrorResponse) => {
          console.error('Error al guardar el usuario:', error.error);
          return throwError(() => new Error('Error al guardar el usuario')); // Pasa una función que devuelve el error
        })
      ).subscribe();
    } else {
      console.log('El formulario no es válido. No se enviará la solicitud.');
      // Puedes mostrar un mensaje de error o realizar otras acciones aquí
    }
  }


}
