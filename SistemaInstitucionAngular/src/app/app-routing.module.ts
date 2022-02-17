import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ListarMatriculasComponent } from './pages/listar-matriculas/listar-matriculas.component';

const routes: Routes = [
  {path:"matriculas",component:ListarMatriculasComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
