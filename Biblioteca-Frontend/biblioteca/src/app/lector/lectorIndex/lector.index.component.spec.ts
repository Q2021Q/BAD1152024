import { ComponentFixture, TestBed } from '@angular/core/testing';

import { LectorIndexComponent } from './lector.index.component';

describe('LectorIndexComponent', () => {
  let component: LectorIndexComponent;
  let fixture: ComponentFixture<LectorIndexComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [LectorIndexComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(LectorIndexComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
