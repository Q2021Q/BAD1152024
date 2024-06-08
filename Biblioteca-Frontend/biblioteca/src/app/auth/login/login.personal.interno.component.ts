import { Component, OnInit } from '@angular/core';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { catchError, tap } from 'rxjs/operators';
import { throwError } from 'rxjs';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { Router } from '@angular/router';


@Component({
  selector: 'app-login',
  standalone: true,
  imports: [ReactiveFormsModule, CommonModule],
  templateUrl: '../../template/login/login.component.html',
  styleUrl: '../../template/login/login.component.css'
})


export class LoginPersonalInternoComponent implements OnInit {

  //constructor(private http: HttpClient) {}
  constructor(private fb: FormBuilder, private http: HttpClient, private router: Router) { 
    
  }

  public formLogin!: FormGroup;
  ngOnInit(): void {
    // Inicializa el formulario en el ngOnInit
    this.formLogin = this.fb.group({
      userName: ['', [Validators.required, Validators.minLength(3)]],
      password: ['', [Validators.required, Validators.minLength(8)]]
    });
  }

  login() {

    // Verifica si el formulario es válido antes de enviar la solicitud
    if (this.formLogin.valid) {
      this.http.post('http://localhost:8080/auth/loginForUsersWithRoles', this.formLogin.value).pipe(
        tap((response: any) => {
        if (response.token) {
          localStorage.setItem('JWT', response.token);
          localStorage.setItem('nombre', response.firstName);
          localStorage.setItem('apellido', response.firstLastName);
          localStorage.setItem('claveRol', response.claveRol);
          if (localStorage.getItem('claveRol') == "SUPERADMIN") {
            console.log("no")
            this.router.navigate(['/admin/adminindex']);                    
          }else{
            this.router.navigate(['/bibio/bibindex']); 
          }
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
