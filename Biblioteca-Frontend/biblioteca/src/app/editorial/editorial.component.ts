import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';

// impports
import { EditorialFormComponent } from '../form/editorial.form/editorial.form.component';
import { ReactiveFormsModule } from '@angular/forms';

@Component({
  selector: 'app-editorial',
  standalone: true,
  imports: [ReactiveFormsModule, CommonModule, EditorialFormComponent],
  templateUrl: './editorial.component.html',
  styleUrl: './editorial.component.css'
})
export class EditorialComponent {

}
