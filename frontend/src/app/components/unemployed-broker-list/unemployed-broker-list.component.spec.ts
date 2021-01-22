/* tslint:disable:no-unused-variable */
import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import { By } from '@angular/platform-browser';
import { DebugElement } from '@angular/core';

import { UnemployedBrokerListComponent } from './unemployed-broker-list.component';

describe('UnemployedBrokerListComponent', () => {
  let component: UnemployedBrokerListComponent;
  let fixture: ComponentFixture<UnemployedBrokerListComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ UnemployedBrokerListComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(UnemployedBrokerListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
