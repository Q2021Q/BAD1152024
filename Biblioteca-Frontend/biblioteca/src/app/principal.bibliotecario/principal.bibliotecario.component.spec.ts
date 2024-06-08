import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PrincipalBibliotecarioComponent } from './principal.bibliotecario.component';

describe('PrincipalBibliotecarioComponent', () => {
  let component: PrincipalBibliotecarioComponent;
  let fixture: ComponentFixture<PrincipalBibliotecarioComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [PrincipalBibliotecarioComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(PrincipalBibliotecarioComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
