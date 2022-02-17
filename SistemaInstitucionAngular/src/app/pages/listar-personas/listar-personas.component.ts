import { Component, OnInit } from '@angular/core';
import { Persona } from 'src/app/domain/persona';
import { ListarPersonasService } from 'src/app/services/listar-personas.service';

@Component({
  selector: 'app-listar-personas',
  templateUrl: './listar-personas.component.html',
  styleUrls: ['./listar-personas.component.css']
})
export class ListarPersonasComponent implements OnInit {

  persona: Persona = new Persona();

  personas:any;

  constructor(private personaService : ListarPersonasService) { }

  ngOnInit(): void {
      this.cargarUsuarios();
  }

  cargarUsuarios(){
    this.personas=this.personaService.getUsuarios();
  }


}
