import { TestBed } from '@angular/core/testing';

import { LibroDiarioService } from './libro-diario.service';

describe('LibroDiarioService', () => {
  let service: LibroDiarioService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(LibroDiarioService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
