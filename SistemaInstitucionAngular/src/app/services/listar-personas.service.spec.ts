import { TestBed } from '@angular/core/testing';

import { ListarPersonasService } from './listar-personas.service';

describe('ListarPersonasService', () => {
  let service: ListarPersonasService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ListarPersonasService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
