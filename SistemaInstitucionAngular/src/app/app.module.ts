import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { ListarMatriculasComponent } from './pages/listar-matriculas/listar-matriculas.component';
import { HttpClientModule } from '@angular/common/http';
import { ListarPersonasComponent } from './pages/listar-personas/listar-personas.component';
import { LibroDiarioComponent } from './pages/libro-diario/libro-diario.component'

@NgModule({
  declarations: [
    AppComponent,
    ListarMatriculasComponent,
    ListarPersonasComponent,
    LibroDiarioComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
