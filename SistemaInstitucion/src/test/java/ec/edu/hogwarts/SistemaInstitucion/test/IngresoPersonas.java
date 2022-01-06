package ec.edu.hogwarts.SistemaInstitucion.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;

import ec.edu.hogwarts.SistemaInstitucion.model.Docente;
import ec.edu.hogwarts.SistemaInstitucion.model.Estudiante;
import ec.edu.hogwarts.SistemaInstitucion.model.Persona;

public class IngresoPersonas{

	@Test
	public void testCrearEstudiante1() {
		Estudiante estudiante = new Estudiante();
		estudiante.setId(1);
		estudiante.setCedula("0152154875");
		estudiante.setNombre("Tracy");
		estudiante.setApellido("Cardenas");
		estudiante.setDireccion("Huaynacapac");
		estudiante.setTelefono("0741458563");
		estudiante.setEmail("tcardenas@est.ups.edu.ec");
		Date fechaNacimiento = StringToDate("15/12/2000");
		estudiante.setFechaNacimiento(fechaNacimiento);
		estudiante.setRepresentante("José Cardenas");
		
		assertTrue(estudiante.getId()==1);
		assertTrue(estudiante.getCedula()=="0152154875");
		assertTrue(estudiante.getNombre()=="Tracy");
		assertTrue(estudiante.getApellido()=="Cardenas");
		assertEquals(estudiante.getDireccion(), "Huaynacapac");
		assertEquals(estudiante.getTelefono(), "0741458563");
		assertEquals(estudiante.getEmail(), "tcardenas@est.ups.edu.ec");
		assertEquals(estudiante.getFechaNacimiento(), fechaNacimiento);
		assertEquals(estudiante.getRepresentante(), "José Cardenas");
		
	}
	
	@Test
	public void testCrearDocente2() {
		Docente docente = new Docente();
		docente.setId(2);
		docente.setCedula("0201154875");
		docente.setNombre("David");
		docente.setApellido("Paguay");
		docente.setDireccion("Azogues");
		docente.setTelefono("074585421");
		docente.setEmail("dpaguay@ups.edu.ec");
		Date fechaNacimiento = StringToDate("15/06/2000");
		docente.setFechaNacimiento(fechaNacimiento);
		docente.setEspecialidad("Ingeniero Comercial");
		
		assertTrue(docente.getId()==3);
		assertTrue(docente.getCedula()=="0201154875");
		assertTrue(docente.getNombre()=="David");
		assertTrue(docente.getApellido()=="Paguay");
		assertEquals(docente.getDireccion(), "Azogues");
		assertEquals(docente.getTelefono(), "074585421");
		assertEquals(docente.getEmail(), "dpaguay@ups.edu.ec");
		assertEquals(docente.getFechaNacimiento(), fechaNacimiento);
		assertEquals(docente.getEspecialidad(), "Ingeniero Comercial");
		assertTrue(docente!=null);
	}
	
	@Test
	public void testEdadPersona() {
		Persona persona = new Persona();
		Date fechaNacimiento = StringToDate("16/01/1992");
		persona.setFechaNacimiento(fechaNacimiento);
		assertEquals(persona.getEdad(), 29);
	}
	
	public Date StringToDate(String s){
	    Date result = null;
	    try{
	    	SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	        result  = dateFormat.parse(s);
	        System.out.println("Fecha: "+result);
	    }
	    catch(ParseException e){
	        e.printStackTrace();
	    }
	    return result ;
	}
	
}
