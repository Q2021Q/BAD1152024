import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CategoriaRecursoComponent } from './categoria.recurso.component';

describe('CategoriaRecursoComponent', () => {
  let component: CategoriaRecursoComponent;
  let fixture: ComponentFixture<CategoriaRecursoComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [CategoriaRecursoComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(CategoriaRecursoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
