package ec.edu.hogwarts.SistemaInstitucion.services;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import ec.edu.hogwarts.SistemaInstitucion.business.LibroDiarioONLocal;
import ec.edu.hogwarts.SistemaInstitucion.business.MatriculaONLocal;
import ec.edu.hogwarts.SistemaInstitucion.model.LibroDiario;
import ec.edu.hogwarts.SistemaInstitucion.model.Matricula;

@Path("librosdiarios")
public class ListarLibrosDiariosRest {
	@Inject
	private LibroDiarioONLocal libroDiarioON;
		
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<LibroDiario> getLibrosDiarios(){
		return libroDiarioON.getLibroDiario();
	}
	
	

}
