package ec.edu.hogwarts.SistemaInstitucion.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.junit.Test;

import ec.edu.hogwarts.SistemaInstitucion.model.Carrera;
import ec.edu.hogwarts.SistemaInstitucion.model.Docente;
import ec.edu.hogwarts.SistemaInstitucion.model.Estudiante;
import ec.edu.hogwarts.SistemaInstitucion.model.Inscripcion;
import ec.edu.hogwarts.SistemaInstitucion.model.MallaCurricular;
import ec.edu.hogwarts.SistemaInstitucion.model.Materia;
import ec.edu.hogwarts.SistemaInstitucion.model.Persona;

public class CasosDePruebas{

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
		estudiante.setPassword("tracy");
		estudiante.setRepresentante("José Cardenas");
		
		assertTrue(estudiante.getId()==1);
		assertTrue(estudiante.getCedula()=="0152154875");
		assertTrue(estudiante.getNombre()=="Tracy");
		assertTrue(estudiante.getApellido()=="Cardenas");
		assertTrue(estudiante.getPassword()=="tracy");
		assertEquals(estudiante.getDireccion(), "Huaynacapac");
		assertEquals(estudiante.getTelefono(), "0741458563");
		assertEquals(estudiante.getEmail(), "tcardenas@est.ups.edu.ec");
		assertEquals(estudiante.getFechaNacimiento(), fechaNacimiento);
		assertEquals(estudiante.getRepresentante(), "José Cardenas");
	}
	
	@Test
	public void testCrearDocente() {
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
		docente.setPassword("david");
		docente.setEspecialidad("Ingeniero Comercial");
		
		assertTrue(docente.getId()==2);
		assertTrue(docente.getCedula()=="0201154875");
		assertTrue(docente.getNombre()=="David");
		assertTrue(docente.getApellido()=="Paguay");
		assertTrue(docente.getPassword()=="david");
		assertEquals(docente.getDireccion(), "Azogues");
		assertEquals(docente.getTelefono(), "074585421");
		assertEquals(docente.getEmail(), "dpaguay@ups.edu.ec");
		assertEquals(docente.getFechaNacimiento(), fechaNacimiento);
		assertEquals(docente.getEspecialidad(), "Ingeniero Comercial");
	}
	
	@Test
	public void testEdadPersona() {
		Persona persona = new Persona();
		Date fechaNacimiento = StringToDate("7/12/2000");
		persona.setFechaNacimiento(fechaNacimiento);
		assertEquals(persona.getEdad(), 21);
	}
	
	public Date StringToDate(String s){
	    Date result = null;
	    try{
	    	SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	        result  = dateFormat.parse(s);
	    }catch(ParseException e){ e.printStackTrace();}
	    return result ;
	}

	@Test
	public void testCrearMateria() {
		
		Materia mat = getMateria();
		assertEquals(mat.getId(), 1);
		assertEquals(mat.getNombre(), "Ciencias Sociales");
		assertEquals(mat.getNivel(), 1);
		assertEquals(mat.getH_docencia(), 80);
		assertEquals(mat.getH_practica(), 40);
		assertEquals(mat.getH_autonomo(), 40);
		assertEquals(mat.getPeriodo(), 56);
		assertEquals(mat.getN_matricula(), 1);
		assertEquals(mat.getEstado(), "activa");
		assertTrue(mat.getPrerequisitos().size()==1);
		
	}
	
	public Materia getMateria() {
		Materia materia = new Materia();
		int id = 1;
		String nombre = "Ciencias Sociales";
		int nivel = 1;
		int h_docencia = 80;
		int h_practica = 40;
		int h_autonomo = 40;
		int periodo = 56;
		int n_matricula = 1;
		String estado = "activa";
		Materia prerequisito = new Materia();
		prerequisito.setNombre("Computación");
		List<Materia> prerequisitos = new ArrayList<Materia>();
		prerequisitos.add(prerequisito);
		
		materia.setId(id);
		materia.setNombre(nombre);
		materia.setNivel(nivel);
		materia.setH_docencia(h_docencia);
		materia.setH_practica(h_practica);
		materia.setH_autonomo(h_autonomo);
		materia.setPeriodo(periodo);
		materia.setN_matricula(n_matricula);
		materia.setEstado(estado);
		materia.setPrerequisitos(prerequisitos);
		
		return materia;
	}
	
	@Test
	public void testMallaCurricular() {
		MallaCurricular mallaCurricular = getMallaCurricular();
		assertEquals(mallaCurricular.getId(), 1);
		assertEquals(mallaCurricular.getDescripcion(), "Malla Filosofia");
		assertEquals(mallaCurricular.getNiveles(), 8);
		assertEquals(mallaCurricular.isEstado(), true);
		assertEquals(mallaCurricular.getMaterias().size(), 1);
	}
	
	public MallaCurricular getMallaCurricular() {
		int id = 1;
		String descripcion = "Malla Filosofia";
		int niveles = 8;
		boolean estado = true;
		List<Materia> materias = new ArrayList<Materia>();
		materias.add(getMateria());
		MallaCurricular mallaCurricular = new MallaCurricular();
		mallaCurricular.setId(id);
		mallaCurricular.setDescripcion(descripcion);
		mallaCurricular.setNiveles(niveles);
		mallaCurricular.setEstado(estado);
		mallaCurricular.setMaterias(materias);
		return mallaCurricular;
	}
	
	@Test
	public void testCarrera() {
		Carrera carrera = getCarrera();
		assertEquals(carrera.getId(), 1);
		assertEquals(carrera.getNombre(), "Filosofía");
		assertEquals(carrera.getDescripcion(), "Profesionales comprometidos con"
				+ " la búsqueda de la verdad y la transformación de su entorno.");
		assertEquals(carrera.getMallas().size(), 1);
	}
	
	public Carrera getCarrera() {
		Carrera carrera = new Carrera();
		int id = 1;
		String nombre = "Filosofía";
		String descripcion = "Profesionales comprometidos con la búsqueda de "
				+ "la verdad y la transformación de su entorno.";
		List<MallaCurricular> mallas = new ArrayList<MallaCurricular>();
		mallas.add(getMallaCurricular());
		
		carrera.setId(id);
		carrera.setNombre(nombre);
		carrera.setDescripcion(descripcion);
		carrera.setMallas(mallas);
		return carrera;
	}
	
	@Test
	public void testInscripcion() {
		Inscripcion inscripcion = getInscripcion();
		assertEquals(inscripcion.getId(), 1);
		assertEquals(inscripcion.getFecha(), StringToDate("01/02/2022"));
		assertTrue(inscripcion.getCarrera()!=null);
		assertTrue(inscripcion.getEstudiante()!=null);
	}
	
	public Inscripcion getInscripcion() {
		Inscripcion inscripcion = new Inscripcion();
		int id = 1;		
		Date fecha = StringToDate("01/02/2022");
		Carrera carrera = getCarrera();
		Estudiante estudiante = new Estudiante();
		
		inscripcion.setId(id);
		inscripcion.setFecha(fecha);
		inscripcion.setCarrera(carrera);
		inscripcion.setEstudiante(estudiante);
		return inscripcion;
	}
	
}
