import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BibliotecarioIndexComponent } from './bibliotecario.index.component';

describe('BibliotecarioIndexComponent', () => {
  let component: BibliotecarioIndexComponent;
  let fixture: ComponentFixture<BibliotecarioIndexComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [BibliotecarioIndexComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(BibliotecarioIndexComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
