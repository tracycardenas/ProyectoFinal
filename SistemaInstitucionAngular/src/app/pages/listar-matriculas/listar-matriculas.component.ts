import { Component, OnInit } from '@angular/core';
import { Matricula } from 'src/app/domain/matricula';
import { MatriculasService } from 'src/app/services/matriculas.service';

@Component({
  selector: 'app-listar-matriculas',
  templateUrl: './listar-matriculas.component.html',
  styleUrls: ['./listar-matriculas.component.css']
})
export class ListarMatriculasComponent implements OnInit {

  matricula: Matricula = new Matricula();

  matriculas:any;

  constructor(private matriculasService: MatriculasService) { }

  ngOnInit(): void {
      this.cargarMatriculas();
  }

  cargarMatriculas(){
    this.matriculas=this.matriculasService.getMatriculas();
  }

}
