import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-editorial-form',
  standalone: true,
  imports: [FormsModule, CommonModule],
  templateUrl: './editorial.form.component.html',
  styleUrl: './editorial.form.component.css'
})
export class EditorialFormComponent {
  public editoriales: any;
  public editorial: any = {
    nombreEditorial: '',
    email: '',
    telefonoEditorial: ''
  }

  async ngOnInit() {
    this.fetchEditoriales();
  }

  async fetchEditoriales() {
    const token: string = localStorage.getItem("JWT")!;
    console.log(token)
    const res = await fetch("http://localhost:8080/editorial/getAllEditorial", {
      method: "GET",
      headers: {
        "Content-Type": "application/json",
        "Authorization": "Bearer " + token
      }
    });

    this.editoriales = await res.json();
  }

  async agregarEditorial() {
    console.log(this.editorial)
    const token: string = localStorage.getItem("JWT")!;
    //const token: string = "eyJhbGciOiJSUzI1NiJ9.eyJzdWIiOiIxIiwicm9sZSI6IkJJQlVTT05MIiwiZXhwIjoxNzE3ODkxNjM3LCJpYXQiOjE3MTc4NzcyMzd9.bdWkFMq20EZoEZ_CxhBtyrQDQ7wuw3sWV4Pbw8BRLyA2AiUpNzSlhF6qv4_bxugeFDeglzqJ36KJVYxCS9NFIWi0vNTovFr1N5yrV5BI4SNyZQXD4-PpMQYRst72Wr0wDE15Lco9Bge-ac-Y483vfijVUr2yy1u7glehjWOCwY1BGN0qv2bt4_0Mj8gnSxff7bWKA6olK8nLpUdouTYLl_f5UrGSBoYKOOCaDyIOnmksC9EAIgRIVEW-2kO_mnrgJZy2QNVq31CRKPvdqaQmWL_4ldkP8e0ETJJPTW7NtvM2_GlwGI48x9Q_OVqEn1ToGo24SUo16RQ2uM3LMzdTUw"
    const res = await fetch("http://localhost:8080/editorial/addeditorial", {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
        "Authorization": "Bearer " + token
      },
      body: JSON.stringify(this.editorial)
    })

    this.fetchEditoriales();
  }

  async modificarEditorial(editorial: any) {
    this.editorial = editorial;
  }

  async cambiarEditorial() {
    console.log(this.editorial)
    const token: string = localStorage.getItem("JWT")!;
    //const token: string = "eyJhbGciOiJSUzI1NiJ9.eyJzdWIiOiIxIiwicm9sZSI6IkJJQlVTT05MIiwiZXhwIjoxNzE3ODkxNjM3LCJpYXQiOjE3MTc4NzcyMzd9.bdWkFMq20EZoEZ_CxhBtyrQDQ7wuw3sWV4Pbw8BRLyA2AiUpNzSlhF6qv4_bxugeFDeglzqJ36KJVYxCS9NFIWi0vNTovFr1N5yrV5BI4SNyZQXD4-PpMQYRst72Wr0wDE15Lco9Bge-ac-Y483vfijVUr2yy1u7glehjWOCwY1BGN0qv2bt4_0Mj8gnSxff7bWKA6olK8nLpUdouTYLl_f5UrGSBoYKOOCaDyIOnmksC9EAIgRIVEW-2kO_mnrgJZy2QNVq31CRKPvdqaQmWL_4ldkP8e0ETJJPTW7NtvM2_GlwGI48x9Q_OVqEn1ToGo24SUo16RQ2uM3LMzdTUw"
    const res = await fetch("http://localhost:8080/editorial/addeditorial", {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
        "Authorization": "Bearer " + token
      },
      body: JSON.stringify(this.editorial)
    })
  }

  async borrarEditorial(categoria: any) {
    
  }
}
