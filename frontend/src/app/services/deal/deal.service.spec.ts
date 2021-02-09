/* tslint:disable:no-unused-variable */

import { TestBed, async, inject } from '@angular/core/testing';
import { DealService } from './deal.service';

describe('Service: Deal', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [DealService]
    });
  });

  it('should ...', inject([DealService], (service: DealService) => {
    expect(service).toBeTruthy();
  }));
});
