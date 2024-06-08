import { Routes } from '@angular/router';
import { authAdminGuard } from '../guards/ward'; 

export const adminRoutes: Routes = [

  {
    path: 'adminindex',
    loadComponent: () => import('../admin/admin.index.component').then(m => m.AdminIndexComponent),
    canActivate: [authAdminGuard],
  },
  // Otras rutas hijas...
];
