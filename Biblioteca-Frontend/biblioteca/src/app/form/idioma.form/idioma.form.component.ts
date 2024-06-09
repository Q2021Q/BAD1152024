import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-idioma-form',
  standalone: true,
  imports: [FormsModule, CommonModule],
  templateUrl: './idioma.form.component.html',
  styleUrl: './idioma.form.component.css'
})
export class IdiomaFormComponent {
  public idiomas: any;
  public idioma: any = {
    nombreidioma: '',
    email: '',
    telefonoidioma: ''
  }

  async ngOnInit() {
    this.fetchIdiomas();
  }

  async fetchIdiomas() {
    const token: string = localStorage.getItem("JWT")!;
    console.log(token)
    const res = await fetch("http://localhost:8080/idioma/getAllIdioma", {
      method: "GET",
      headers: {
        "Content-Type": "application/json",
        "Authorization": "Bearer " + token
      }
    });

    this.idiomas = await res.json();
    console.log(this.idiomas)
  }

  async agregarIdioma() {
    console.log(this.idioma)
    const token: string = localStorage.getItem("JWT")!;
    //const token: string = "eyJhbGciOiJSUzI1NiJ9.eyJzdWIiOiIxIiwicm9sZSI6IkJJQlVTT05MIiwiZXhwIjoxNzE3ODkxNjM3LCJpYXQiOjE3MTc4NzcyMzd9.bdWkFMq20EZoEZ_CxhBtyrQDQ7wuw3sWV4Pbw8BRLyA2AiUpNzSlhF6qv4_bxugeFDeglzqJ36KJVYxCS9NFIWi0vNTovFr1N5yrV5BI4SNyZQXD4-PpMQYRst72Wr0wDE15Lco9Bge-ac-Y483vfijVUr2yy1u7glehjWOCwY1BGN0qv2bt4_0Mj8gnSxff7bWKA6olK8nLpUdouTYLl_f5UrGSBoYKOOCaDyIOnmksC9EAIgRIVEW-2kO_mnrgJZy2QNVq31CRKPvdqaQmWL_4ldkP8e0ETJJPTW7NtvM2_GlwGI48x9Q_OVqEn1ToGo24SUo16RQ2uM3LMzdTUw"
    const res = await fetch("http://localhost:8080/idioma/addIdioma", {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
        "Authorization": "Bearer " + token
      },
      body: JSON.stringify(this.idioma)
    })

    this.fetchIdiomas();
  }

  async modificarIdioma(idioma: any) {
    this.idioma = idioma;
  }

  async cambiarIdioma() {
    const token: string = localStorage.getItem("JWT")!;
    //const token: string = "eyJhbGciOiJSUzI1NiJ9.eyJzdWIiOiIxIiwicm9sZSI6IkJJQlVTT05MIiwiZXhwIjoxNzE3ODkxNjM3LCJpYXQiOjE3MTc4NzcyMzd9.bdWkFMq20EZoEZ_CxhBtyrQDQ7wuw3sWV4Pbw8BRLyA2AiUpNzSlhF6qv4_bxugeFDeglzqJ36KJVYxCS9NFIWi0vNTovFr1N5yrV5BI4SNyZQXD4-PpMQYRst72Wr0wDE15Lco9Bge-ac-Y483vfijVUr2yy1u7glehjWOCwY1BGN0qv2bt4_0Mj8gnSxff7bWKA6olK8nLpUdouTYLl_f5UrGSBoYKOOCaDyIOnmksC9EAIgRIVEW-2kO_mnrgJZy2QNVq31CRKPvdqaQmWL_4ldkP8e0ETJJPTW7NtvM2_GlwGI48x9Q_OVqEn1ToGo24SUo16RQ2uM3LMzdTUw"
    const res = await fetch("http://localhost:8080/idioma/addIdioma", {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
        "Authorization": "Bearer " + token
      },
      body: JSON.stringify(this.idioma)
    })
  }

  async borrarIdioma(idioma: any) {
    const token: string = localStorage.getItem("JWT")!;
    const res = await fetch("http://localhost:8080/idioma/deleteIdioma", {
      method: "DELETE",
      headers: {
        "Content-Type": "application/json",
        "Authorization": "Bearer " + token
      },
      body: JSON.stringify(idioma)
    })

    this.fetchIdiomas();
  }
}