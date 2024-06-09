import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-autor-form',
  standalone: true,
  imports: [FormsModule, CommonModule],
  templateUrl: './autor.form.component.html',
  styleUrl: './autor.form.component.css'
})
export class AutorFormComponent {
  public autores: any;
  public autor: any = {
    firstName: '',
    secondName: '',
    firstLastName: '',
    secondLastName: ''
  }

  async ngOnInit() {
    this.fetchAutores();
  }

  async fetchAutores() {
    const token: string = localStorage.getItem("JWT")!;
    console.log(token)
    const res = await fetch("http://localhost:8080/autor/getAllAutor", {
      method: "GET",
      headers: {
        "Content-Type": "application/json",
        "Authorization": "Bearer " + token
      }
    });

    this.autores = await res.json();
    console.log(this.autores)
  }

  async agregarAutor() {
    console.log(this.autor)
    const token: string = localStorage.getItem("JWT")!;
    //const token: string = "eyJhbGciOiJSUzI1NiJ9.eyJzdWIiOiIxIiwicm9sZSI6IkJJQlVTT05MIiwiZXhwIjoxNzE3ODkxNjM3LCJpYXQiOjE3MTc4NzcyMzd9.bdWkFMq20EZoEZ_CxhBtyrQDQ7wuw3sWV4Pbw8BRLyA2AiUpNzSlhF6qv4_bxugeFDeglzqJ36KJVYxCS9NFIWi0vNTovFr1N5yrV5BI4SNyZQXD4-PpMQYRst72Wr0wDE15Lco9Bge-ac-Y483vfijVUr2yy1u7glehjWOCwY1BGN0qv2bt4_0Mj8gnSxff7bWKA6olK8nLpUdouTYLl_f5UrGSBoYKOOCaDyIOnmksC9EAIgRIVEW-2kO_mnrgJZy2QNVq31CRKPvdqaQmWL_4ldkP8e0ETJJPTW7NtvM2_GlwGI48x9Q_OVqEn1ToGo24SUo16RQ2uM3LMzdTUw"
    const res = await fetch("http://localhost:8080/autor/addAutor", {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
        "Authorization": "Bearer " + token
      },
      body: JSON.stringify(this.autor)
    })

    this.fetchAutores();
    console.log(await res.text())
  }

  async modificarAutor(autor: any) {
    this.autor = autor;
  }

  async cambiarAutor() {
    const token: string = localStorage.getItem("JWT")!;
    //const token: string = "eyJhbGciOiJSUzI1NiJ9.eyJzdWIiOiIxIiwicm9sZSI6IkJJQlVTT05MIiwiZXhwIjoxNzE3ODkxNjM3LCJpYXQiOjE3MTc4NzcyMzd9.bdWkFMq20EZoEZ_CxhBtyrQDQ7wuw3sWV4Pbw8BRLyA2AiUpNzSlhF6qv4_bxugeFDeglzqJ36KJVYxCS9NFIWi0vNTovFr1N5yrV5BI4SNyZQXD4-PpMQYRst72Wr0wDE15Lco9Bge-ac-Y483vfijVUr2yy1u7glehjWOCwY1BGN0qv2bt4_0Mj8gnSxff7bWKA6olK8nLpUdouTYLl_f5UrGSBoYKOOCaDyIOnmksC9EAIgRIVEW-2kO_mnrgJZy2QNVq31CRKPvdqaQmWL_4ldkP8e0ETJJPTW7NtvM2_GlwGI48x9Q_OVqEn1ToGo24SUo16RQ2uM3LMzdTUw"
    const res = await fetch("http://localhost:8080/autor/addAutor", {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
        "Authorization": "Bearer " + token
      },
      body: JSON.stringify(this.autor)
    })
  }

  async borrarAutor(autor: any) {
    const token: string = localStorage.getItem("JWT")!;
    const res = await fetch("http://localhost:8080/autor/deleteAutor", {
      method: "DELETE",
      headers: {
        "Content-Type": "application/json",
        "Authorization": "Bearer " + token
      },
      body: JSON.stringify(autor)
    })

    this.fetchAutores();
  }
}
