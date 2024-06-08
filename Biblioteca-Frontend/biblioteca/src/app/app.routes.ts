import { Routes } from '@angular/router';
import { lectorRoutes } from './routers/lector.router';
import { adminRoutes } from './routers/admin.router';
import { internoRoutes } from './routers/interno.router';
import { bibioRoutes } from './routers/bibio.router';
import { PrincipalBibliotecarioComponent } from './principal.bibliotecario/principal.bibliotecario.component';


export const routes: Routes = [

    {
        path: 'lector',
        children: lectorRoutes,
    },

    {
        path: 'interno',
        children: internoRoutes,
    },

    {
        path: 'admin',
        children: adminRoutes,
    },

    {
        path: 'bibio',
        component: PrincipalBibliotecarioComponent,
        children: bibioRoutes,
    }
];
