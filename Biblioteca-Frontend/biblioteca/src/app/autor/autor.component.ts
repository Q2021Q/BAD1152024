import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';

import { AutorFormComponent } from '../form/autor.form/autor.form.component';

@Component({
  selector: 'app-autor',
  standalone: true,
  imports: [CommonModule, AutorFormComponent],
  templateUrl: './autor.component.html',
  styleUrl: './autor.component.css'
})
export class AutorComponent {

}
