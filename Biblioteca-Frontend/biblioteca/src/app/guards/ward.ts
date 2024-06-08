import { inject } from '@angular/core';
import { CanActivateFn, Router } from '@angular/router';

export const loginGuard: CanActivateFn = () => {
  const router = inject(Router);

  if (localStorage.getItem('JWT')) {
    //el local storage almacena json, al paserle un null el lo toma como string y por lo tanto al recuperarlo lo devuelve como string
    //usuario ya registrado, redirige a la vista prinsipal
    if (localStorage.getItem('claveRol') == "BIBUSONL") {
      router.navigate(['/lector/lectorIndex']);
    }
    if (localStorage.getItem('claveRol') == "SUPERADMIN") {
      router.navigate(['/admin/adminindex']);
    }

    if (localStorage.getItem('claveRol') == "BIBLIOTECARIO") {
      router.navigate(['/bibio/bibindex']); 
    }
    
    return false
  }
  //la ruta se ejecuta, continua con el flujo
  return true;
};

export const authLectorGuard: CanActivateFn = () => {
  const router = inject(Router);

  if (!localStorage.getItem('JWT')) {
    //usuario ya registrado, redirige a la vista prinsipal
    router.navigate(['/lector/loginLector']);
    return false
  }

  if (localStorage.getItem('claveRol') != "BIBUSONL") {
    // Eliminar todos los elementos del localStorage
    var claveRol = localStorage.getItem('claveRol');
    localStorage.clear();
    if (claveRol === "BIBUSONL") {
      router.navigate(['/lector/loginLector']);
    }else{
      router.navigate(['/interno/login']);
    }
  
    return false
  }
  //la ruta se ejecuta, continua con el flujo
  return true;
};

export const authAdminGuard: CanActivateFn = () => {
  const router = inject(Router);

  if (!localStorage.getItem('JWT')) {
    //usuario ya registrado, redirige a la vista prinsipal
    router.navigate(['/interno/login']);
    return false
  }

  if (localStorage.getItem('claveRol') != "SUPERADMIN") {
    // Eliminar todos los elementos del localStorage
    var claveRol = localStorage.getItem('claveRol');
    localStorage.clear();
    if (claveRol === "BIBUSONL") {
      router.navigate(['/lector/loginLector']);
    }else{
      router.navigate(['/interno/login']);
    }
  
    return false
  }
  //la ruta se ejecuta, continua con el flujo
  return true;
};

export const authBibioGuard: CanActivateFn = () => {
  const router = inject(Router);

  if (!localStorage.getItem('JWT')) {
    //usuario ya registrado, redirige a la vista prinsipal
    router.navigate(['/interno/login']);
    return false
  }

  if (localStorage.getItem('claveRol') != "BIBLIOTECARIO") {
    // Eliminar todos los elementos del localStorage
    var claveRol = localStorage.getItem('claveRol');
    localStorage.clear();
    if (claveRol === "BIBUSONL") {
      router.navigate(['/lector/loginLector']);
    }else{
      router.navigate(['/interno/login']);
    }
  
    return false
  }
  //la ruta se ejecuta, continua con el flujo
  return true;
};


