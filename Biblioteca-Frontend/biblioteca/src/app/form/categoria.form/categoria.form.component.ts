import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-categoria.form',
  standalone: true,
  imports: [FormsModule],
  templateUrl: './categoria.form.component.html',
  styleUrl: './categoria.form.component.css'
})
export class CategoriaFormComponent {
  public categoria: any = {
    nombreCategoria: '',
    descripcionCategoria: ''
  }

  async ngOnInit() {
    const res = await fetch("")
  }

  async agregarCategoria() {
    const token: string = localStorage.getItem("JWT")!;
    //const token: string = "eyJhbGciOiJSUzI1NiJ9.eyJzdWIiOiIxIiwicm9sZSI6IkJJQlVTT05MIiwiZXhwIjoxNzE3ODkxNjM3LCJpYXQiOjE3MTc4NzcyMzd9.bdWkFMq20EZoEZ_CxhBtyrQDQ7wuw3sWV4Pbw8BRLyA2AiUpNzSlhF6qv4_bxugeFDeglzqJ36KJVYxCS9NFIWi0vNTovFr1N5yrV5BI4SNyZQXD4-PpMQYRst72Wr0wDE15Lco9Bge-ac-Y483vfijVUr2yy1u7glehjWOCwY1BGN0qv2bt4_0Mj8gnSxff7bWKA6olK8nLpUdouTYLl_f5UrGSBoYKOOCaDyIOnmksC9EAIgRIVEW-2kO_mnrgJZy2QNVq31CRKPvdqaQmWL_4ldkP8e0ETJJPTW7NtvM2_GlwGI48x9Q_OVqEn1ToGo24SUo16RQ2uM3LMzdTUw"
    const res = await fetch("http://localhost:8080/bibliotecario/addcategoriarecurso", {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
        "Authorization": "Bearer " + token
      },
      body: JSON.stringify(this.categoria)
    })
  }
}
