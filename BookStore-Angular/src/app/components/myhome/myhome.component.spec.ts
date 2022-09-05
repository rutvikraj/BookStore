import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MyhomeComponent } from './myhome.component';

describe('MyhomeComponent', () => {
  let component: MyhomeComponent;
  let fixture: ComponentFixture<MyhomeComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ MyhomeComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(MyhomeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
