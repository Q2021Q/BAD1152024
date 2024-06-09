import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { IdiomaFormComponent } from '../form/idioma.form/idioma.form.component';

@Component({
  selector: 'app-idioma',
  standalone: true,
  imports: [CommonModule, IdiomaFormComponent],
  templateUrl: './idioma.component.html',
  styleUrl: './idioma.component.css'
})
export class IdiomaComponent {
}
