import { Routes } from '@angular/router';
import { authBibioGuard } from '../guards/ward'; 


export const bibioRoutes: Routes = [
  {
    path: 'bibindex',
    loadComponent: () => import('../bibliotecario/bibliotecario.index.component').then(m => m.BibliotecarioIndexComponent),
    canActivate: [authBibioGuard],
    //component: DashboardComponent,
  },
  {
    path: 'catrecurso',
    loadComponent: () => import('../categoria.recurso/categoria.recurso.component').then(m => m.CategoriaRecursoComponent),
    //canActivate: [authBibioGuard],
  },
    {
    path: 'editorial',
    loadComponent: () => import('../editorial/editorial.component').then(m => m.EditorialComponent),
    //canActivate: [authBibioGuard],
  },{
    path: 'idioma',
    loadComponent: () => import('../idioma/idioma.component').then(m => m.IdiomaComponent),
    //canActivate: [authBibioGuard],
  },
  // Otras rutas hijas...
];
