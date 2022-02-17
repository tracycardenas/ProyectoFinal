import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LibroDiarioComponent } from './pages/libro-diario/libro-diario.component';
import { ListarMatriculasComponent } from './pages/listar-matriculas/listar-matriculas.component';
import { ListarPersonasComponent } from './pages/listar-personas/listar-personas.component';

const routes: Routes = [
  {path:"matriculas",component:ListarMatriculasComponent},
  {path:"usuarios",component:ListarPersonasComponent},
  {path:"LibroDiario",component:LibroDiarioComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
