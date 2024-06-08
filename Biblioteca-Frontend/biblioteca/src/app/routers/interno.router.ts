import { Routes } from '@angular/router';
import { loginGuard } from '../guards/ward'; 

export const internoRoutes: Routes = [
  {
    path: 'login',
    loadComponent: () => import('../auth/login/login.personal.interno.component').then(m => m.LoginPersonalInternoComponent),
    canActivate: [loginGuard],
  },

];
