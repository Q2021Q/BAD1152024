import { Routes } from '@angular/router';
import { authLectorGuard, loginGuard } from '../guards/ward'; 

export const lectorRoutes: Routes = [
  {
    path: 'loginLector',
    loadComponent: () => import('../auth/login/login.component').then(m => m.LoginComponent),
    canActivate: [loginGuard],
  },

  {
    path: 'lectorIndex',
    loadComponent: () => import('../lector/lectorIndex/lector.index.component').then(m => m.LectorIndexComponent),
    canActivate: [authLectorGuard],
    //component: DashboardComponent,
  },
  // Otras rutas hijas...
];
