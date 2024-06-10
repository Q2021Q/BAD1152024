import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';

import { LibroFormComponent } from '@app/form/libro.form/libro.form.component';

@Component({
  selector: 'app-libro',
  standalone: true,
  imports: [CommonModule, LibroFormComponent],
  templateUrl: './libro.component.html',
  styleUrl: './libro.component.css'
})
export class LibroComponent {

}
