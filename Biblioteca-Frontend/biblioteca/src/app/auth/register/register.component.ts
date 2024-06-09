import { Component, OnInit } from '@angular/core';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { catchError, tap } from 'rxjs/operators';
import { throwError } from 'rxjs';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router'; // Importar RouterModule
import { Router } from '@angular/router';

@Component({
  selector: 'app-register',
  standalone: true,
  imports: [ReactiveFormsModule, CommonModule, RouterModule],
  templateUrl: '../../template/register/register.component.html',
  styleUrls: ['../../template/register/register.component.css']
})
export class RegisterComponent implements OnInit {

  public registerForm!: FormGroup;

  constructor(private fb: FormBuilder, private http: HttpClient, private router: Router) {
    // Inicializa el formulario en el constructor
    this.registerForm = this.fb.group({
      userName: ['', [Validators.required, Validators.minLength(3)]],
      firstName: ['', Validators.required],
      middleName: [''],
      lastName: ['', Validators.required],
      secondLastName: [''],
      email: ['', [Validators.required, Validators.email]],
      password: ['', [Validators.required, Validators.minLength(8)]],
      codeSentEmail: ['', Validators.required]
    });
  }

  ngOnInit(): void {
    // No es necesario inicializar el formulario aquí
  }

  register() {
    console.log("Datos del formulario:", this.registerForm.value);

    // Verifica si el formulario es válido antes de enviar la solicitud
    if (this.registerForm.valid) {
      this.http.post('http://localhost:8080/auth/register', this.registerForm.value).pipe(
        tap((response: any) => {
          console.log('User registered successfully', response);
          this.router.navigate(['/success']); // Cambia esta ruta a donde quieras redirigir después del registro
        }),
        catchError((error: HttpErrorResponse) => {
          console.error('Error al registrar el usuario:', error.error);
          return throwError(() => new Error('Error al registrar el usuario')); // Pasa una función que devuelve el error
        })
      ).subscribe();
    } else {
      console.log('El formulario no es válido. No se enviará la solicitud.');
      // Puedes mostrar un mensaje de error o realizar otras acciones aquí
    }
  }
}
