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
  public verificationCodeSent: boolean = false;
  public verificationMessage: string = '';
  public canRequestCode: boolean = true;
  public requestCodeText: string = 'Solicitar codigo de verificacion';
  private idUserName!: number;
  private idPerName!: number;
  public errorMessage: string = ''; // Variable para almacenar el mensaje de error

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

  requestVerificationCode() {
    const email = this.registerForm.get('email')?.value;

    if (!email) {
      console.log('Please enter an email address');
      return;
    }

    this.http.post('http://localhost:8080/auth/registerEmail', { email }).pipe(
      tap((response: any) => {
        console.log('Codigo de verificacion enviado con exito');
        this.verificationCodeSent = true;
        this.verificationMessage = 'Codigo enviado a tu correo.';
        this.canRequestCode = false;
        this.requestCodeText = 'Reenviar solicitud de código';

        this.idUserName = response.data.idPerName;
        //setTimeout(() => {
        //  this.canRequestCode = true;
        //}, 10000); // 30 segundos
        setTimeout(() => {
          this.verificationMessage = '';
        }, 10000); // 10 segundos para ocultar el mensaje
      }),
      catchError((error: HttpErrorResponse) => {
        console.error('Error sending verification code:', error.error);
        return throwError(() => new Error('Error enviando codigo de verificacion'));
      })
    ).subscribe();
  }

  register() {
    this.errorMessage = ''; // Limpiar mensaje de error previo
    console.log("Datos del formulario:", this.registerForm.value);

    if (this.registerForm.valid && this.verificationCodeSent) {
      const formValue = this.registerForm.value;

      // Paso 1: Insertar nombres y apellidos
      this.http.post('http://localhost:8080/auth/insertusername', {
        firstName: formValue.firstName,
        secondName: formValue.middleName,
        firstLastName: formValue.lastName,
        secondLastName: formValue.secondLastName
      }).pipe(
        tap((response: any) => {
          if (response.numOfErrors > 0) {
            throw new Error(response.message);
          }
          this.idPerName = response.data.idPerName;
        }),
        catchError((error: HttpErrorResponse | Error) => {
          this.errorMessage = `Error inserting user name: ${error.message}`;
          return throwError(() => new Error('Error inserting user name'));
        })
      ).toPromise().then(() => {

        // Paso 3: Asociar nombre de usuario y correo electrónico
        return this.http.put('http://localhost:8080/auth/addrelacionusername', {
          email: formValue.email,
          codeSentEmail: formValue.codeSentEmail,
          idUserName: this.idPerName
        }).pipe(
          tap((response: any) => {
            if (response.numOfErrors > 0) {
              throw new Error(response.message);
            }
            console.log('Asociación de nombre de usuario y correo', response);
          }),
          catchError((error: HttpErrorResponse | Error) => {
            this.errorMessage = `Error associating user name and email: ${error.message}`;
            return throwError(() => new Error('Error associating user name and email'));
          })
        ).toPromise();

      }).then(() => {

        // Paso 4: Completar el registro
        return this.http.post('http://localhost:8080/auth/register', {
          userName: formValue.userName,
          email: formValue.email,
          password: formValue.password,
          codeSentEmail: formValue.codeSentEmail
        }).pipe(
          tap((response: any) => {
            if (response.numOfErrors > 0) {
              throw new Error(response.message);
            }
            console.log('Registro completado con éxito', response);
            this.router.navigate(['/success']);
          }),
          catchError((error: HttpErrorResponse | Error) => {
            this.errorMessage = `Error completing registration: ${error.message}`;
            return throwError(() => new Error('Error completing registration'));
          })
        ).toPromise();

      }).catch(error => {
        console.error('Error en el proceso de registro:', error);
      });
    } else {
      this.errorMessage = 'El formulario no es válido o el código de verificación no ha sido enviado. No se enviará la solicitud.';
    }
  }
}
