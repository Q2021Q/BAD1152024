import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-libro-form',
  standalone: true,
  imports: [FormsModule, CommonModule],
  templateUrl: './libro.form.component.html',
  styleUrl: './libro.form.component.css'
})
export class LibroFormComponent {
  public libros: any;
  public libro: any = {
    titulo: '',
    fechapuBlicacion: '',
    fechaAdQuisicion: '',
    codigoISBN: '',
    descripcion: '',
    edicion: '',
    numPaginas: 0,
    recursoDigital: null,
    idCategoria: 0,
    idEditorial: 0,
    idIdioma: 0,
    idAutor: 0,
    rutaAcceso: ''
  }

  public categorias: any;
  public categoria: any = {
    nombreCategoria: '',
    descripcionCategoria: ''
  }

  public editoriales: any;
  public editorial: any = {
    nombreEditorial: '',
    email: '',
    telefonoEditorial: ''
  }

  public idiomas: any;
  public idioma: any = {
    nombreidioma: '',
    email: '',
    telefonoidioma: ''
  }

  public autores: any;
  public autor: any = {
    firstName: '',
    secondName: '',
    firstLastName: '',
    secondLastName: ''
  }

  async ngOnInit() {
    
    this.fetchCategorias();
    this.fetchEditoriales();
    this.fetchIdiomas();
    this.fetchAutores();
    this.fetchLibros();
    
  }

  async fetchCategorias() {
    const token: string = localStorage.getItem("JWT")!;
    const res = await fetch("http://localhost:8080/bibliotecario/getAllCateRecur", {
      method: "GET",
      headers: {
        "Content-Type": "application/json",
        "Authorization": "Bearer " + token
      }});

      this.categorias = await res.json();
  }

  async fetchEditoriales() {
    this.categorias = []
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

  async fetchLibros() {
    const token: string = localStorage.getItem("JWT")!;
    console.log(token)
    const res = await fetch("http://localhost:8080/libro/getAllBook", {
      method: "GET",
      headers: {
        "Content-Type": "application/json",
        "Authorization": "Bearer " + token
      }
    });

    this.libros = await res.json();
    console.log(this.libros)
  }

  async agregarLibro() {
    console.log(this.libro)
    const token: string = localStorage.getItem("JWT")!;
    //const token: string = "eyJhbGciOiJSUzI1NiJ9.eyJzdWIiOiIxIiwicm9sZSI6IkJJQlVTT05MIiwiZXhwIjoxNzE3ODkxNjM3LCJpYXQiOjE3MTc4NzcyMzd9.bdWkFMq20EZoEZ_CxhBtyrQDQ7wuw3sWV4Pbw8BRLyA2AiUpNzSlhF6qv4_bxugeFDeglzqJ36KJVYxCS9NFIWi0vNTovFr1N5yrV5BI4SNyZQXD4-PpMQYRst72Wr0wDE15Lco9Bge-ac-Y483vfijVUr2yy1u7glehjWOCwY1BGN0qv2bt4_0Mj8gnSxff7bWKA6olK8nLpUdouTYLl_f5UrGSBoYKOOCaDyIOnmksC9EAIgRIVEW-2kO_mnrgJZy2QNVq31CRKPvdqaQmWL_4ldkP8e0ETJJPTW7NtvM2_GlwGI48x9Q_OVqEn1ToGo24SUo16RQ2uM3LMzdTUw"
    const res = await fetch("http://localhost:8080/libro/addBook", {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
        "Authorization": "Bearer " + token
      },
      body: JSON.stringify(this.libro)
    })

    this.fetchLibros();
    
  }

  async modificarLibro(libro: any) {
    this.libro = libro;
    this.libro.rutaAcceso = libro.recursoDigital.rutaAcceso
    console.log(libro)
  }

  async cambiarLibro() {
    const token: string = localStorage.getItem("JWT")!;
    let libro2 = {...this.libro}
    libro2.idRecursoDigital = this.libro.recursoDigital.idRecursoDigital
    libro2.idCategoria = this.libro.categoria.idCategoriaRecurso
    libro2.idEditorial = this.libro.editorial.idEditorial
    libro2.idIdioma = this.libro.idioma.idIdioma
    libro2.idAutor = this.libro.autor.idAutor
    libro2.rutaAcceso = this.libro.recursoDigital.rutaAcceso
    delete libro2.categoria
    delete libro2.editorial
    delete libro2.autor
    delete libro2.recursoDigital
    delete libro2.idioma
    //const token: string = "eyJhbGciOiJSUzI1NiJ9.eyJzdWIiOiIxIiwicm9sZSI6IkJJQlVTT05MIiwiZXhwIjoxNzE3ODkxNjM3LCJpYXQiOjE3MTc4NzcyMzd9.bdWkFMq20EZoEZ_CxhBtyrQDQ7wuw3sWV4Pbw8BRLyA2AiUpNzSlhF6qv4_bxugeFDeglzqJ36KJVYxCS9NFIWi0vNTovFr1N5yrV5BI4SNyZQXD4-PpMQYRst72Wr0wDE15Lco9Bge-ac-Y483vfijVUr2yy1u7glehjWOCwY1BGN0qv2bt4_0Mj8gnSxff7bWKA6olK8nLpUdouTYLl_f5UrGSBoYKOOCaDyIOnmksC9EAIgRIVEW-2kO_mnrgJZy2QNVq31CRKPvdqaQmWL_4ldkP8e0ETJJPTW7NtvM2_GlwGI48x9Q_OVqEn1ToGo24SUo16RQ2uM3LMzdTUw"
    const res = await fetch("http://localhost:8080/libro/updateBook", {
      method: "PUT",
      headers: {
        "Content-Type": "application/json",
        "Authorization": "Bearer " + token
      },
      body: JSON.stringify(libro2)
    })

    console.log(libro2)
  }

  async borrarLibro(libro: any) {
    const { idLibro } = libro;
    const token: string = localStorage.getItem("JWT")!;
    const res = await fetch("http://localhost:8080/libro/deleteBook", {
      method: "DELETE",
      headers: {
        "Content-Type": "application/json",
        "Authorization": "Bearer " + token
      },
      body: JSON.stringify({idLibro})
    })

    this.fetchLibros();
    console.log(JSON.stringify(idLibro))
  }
}