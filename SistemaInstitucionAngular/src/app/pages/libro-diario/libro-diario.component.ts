import { Component, OnInit } from '@angular/core';
import { LibroDiario } from 'src/app/domain/librodiario';
import { LibroDiarioService } from 'src/app/services/libro-diario.service';

@Component({
  selector: 'app-libro-diario',
  templateUrl: './libro-diario.component.html',
  styleUrls: ['./libro-diario.component.css']
})
export class LibroDiarioComponent implements OnInit {

  libro: LibroDiario = new LibroDiario();

  libros:any;

  constructor(private libroService : LibroDiarioService) { }

  ngOnInit(): void {
      this.cargarLibros();
  }

  cargarLibros(){
    this.libros=this.libroService.getLibros();
  }

}
