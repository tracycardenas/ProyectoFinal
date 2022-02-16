package ec.edu.hogwarts.SistemaInstitucion.services;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import ec.edu.hogwarts.SistemaInstitucion.business.PersonaONLocal;
import ec.edu.hogwarts.SistemaInstitucion.model.Persona;


@Path("busquedas")
public class BusquedasRest {
	
	@Inject
	private PersonaONLocal personaON;
		
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("searh")
	public Persona getPersona(@QueryParam("cedula") String cedula){
		return personaON.getPersona(cedula);
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List< Persona> getPersonas(){
		
		return personaON.getPersonas();
	}
	
}
