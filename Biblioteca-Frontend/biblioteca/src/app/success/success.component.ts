import { Component } from '@angular/core';
import { RouterModule } from '@angular/router'; // Importar RouterModule
import { Router } from '@angular/router';

@Component({
  selector: 'app-success',
  templateUrl: './success.component.html',
  styleUrls: ['./success.component.css'],
  standalone: true,
  imports: [RouterModule] // Asegúrate de importar RouterModule
})
export class SuccessComponent {}
