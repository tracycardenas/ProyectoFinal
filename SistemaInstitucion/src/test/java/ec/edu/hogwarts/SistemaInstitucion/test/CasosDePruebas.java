package ec.edu.hogwarts.SistemaInstitucion.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;

import ec.edu.hogwarts.SistemaInstitucion.model.Calificacion;
import ec.edu.hogwarts.SistemaInstitucion.model.Carrera;
import ec.edu.hogwarts.SistemaInstitucion.model.DetalleFactura;
import ec.edu.hogwarts.SistemaInstitucion.model.Docente;
import ec.edu.hogwarts.SistemaInstitucion.model.EspacioFisico;
import ec.edu.hogwarts.SistemaInstitucion.model.Estudiante;
import ec.edu.hogwarts.SistemaInstitucion.model.Factura;
import ec.edu.hogwarts.SistemaInstitucion.model.Grupo;
import ec.edu.hogwarts.SistemaInstitucion.model.Inscripcion;
import ec.edu.hogwarts.SistemaInstitucion.model.Institucion;
import ec.edu.hogwarts.SistemaInstitucion.model.LibroDiario;
import ec.edu.hogwarts.SistemaInstitucion.model.MallaCurricular;
import ec.edu.hogwarts.SistemaInstitucion.model.Materia;
import ec.edu.hogwarts.SistemaInstitucion.model.Matricula;
import ec.edu.hogwarts.SistemaInstitucion.model.Movimiento;
import ec.edu.hogwarts.SistemaInstitucion.model.Persona;

public class CasosDePruebas{

	@Test
	public void testCrearEstudiante1() {
		Estudiante estudiante = new Estudiante();
		//estudiante.setId(1);
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
		
		//assertTrue(estudiante.getId()==1);
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
		//docente.setId(2);
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
		
		//assertTrue(docente.getId()==2);
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
	
	public Materia getMateria() {
		Materia materia = new Materia();
		/*int id = 1;
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
		materia.setPrerequisitos(prerequisitos);*/
		
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
	
	@Test
	public void testCrearEspacioFisico() {
		EspacioFisico espacioFisico = new EspacioFisico();
		espacioFisico.setId(1);
		espacioFisico.setDescripcion("Aula 1");
		espacioFisico.setUbicacion("Edificio Cornelio Merchan");
		espacioFisico.setDisponibilidad("Diaria");
		
		assertEquals(espacioFisico.getId(), 1);
		assertEquals(espacioFisico.getDescripcion(), "Aula 1");
		assertEquals(espacioFisico.getUbicacion(), "Edificio Cornelio Merchan");
		assertEquals(espacioFisico.getDisponibilidad(), "Diaria");
		assertTrue(espacioFisico!=null);
	}
	
	@Test
	public void testCrearGrupo() {
		Grupo grupo = new Grupo();
		grupo.setId(1);
		grupo.setNombre("Grupo 1");
		grupo.setModalidad("Virtual");
		grupo.setCupos(30);
		grupo.setCosto(500.0);
		
		Materia materia = new Materia();
		/*materia.setId(1);
		materia.setNombre("Programacion");*/
		grupo.setMateria(materia);
		
		EspacioFisico espacioFisico = new EspacioFisico();
		espacioFisico.setId(1);
		grupo.setEspacioFisico(espacioFisico);
		
		Docente docente = new Docente();
		//docente.setId(1);
		grupo.setDocente(docente);
		
		assertEquals(grupo.getId(), 1);
		assertEquals(grupo.getNombre(), "Grupo 1");
		assertEquals(grupo.getModalidad(), "Virtual");
		assertTrue(grupo.getCupos()==30);
		assertTrue(grupo.getCosto()==500.0);
		assertTrue(grupo.getDocente()==docente);
		assertTrue(grupo.getEspacioFisico()==espacioFisico);
		assertTrue(grupo.getMateria()==materia);
		
	}
	
	@Test
	public void testCrearCalificacion() {
		Calificacion calificacion = new Calificacion();
		calificacion.setId(1);
		calificacion.setAporte1(10);
		calificacion.setAporte2(10);
		calificacion.setEstado(true);
		
		Estudiante estudiante= new Estudiante();
		//estudiante.setId(1);
		calificacion.setEstudiante(estudiante);
		calificacion.setExamen1(10);
		calificacion.setExamen2(10);
		
		Grupo grupo = new Grupo();
		grupo.setId(1);
		calificacion.setGrupo(grupo);
		
		calificacion.setEstado(true);
		
		assertEquals(calificacion.getId(), 1);
		assertTrue(calificacion.getAporte1()==10);
		assertTrue(calificacion.getAporte2()==10);
		assertTrue(calificacion.getEstudiante()==estudiante);
		assertTrue(calificacion.getExamen1()==10);
		assertTrue(calificacion.getExamen2()==10);
		assertTrue(calificacion.getGrupo()==grupo);
		assertTrue(calificacion.isEstado()==true);
	}
	
	@Test
	public void testCrearMatricula() {
		Matricula matricula = new Matricula();
		matricula.setId(1);
		matricula.setMatricula(1);
		Estudiante estudiante = new Estudiante();
		//estudiante.setId(1);
		matricula.setEstudiante(estudiante);
		
		Grupo grupo = new Grupo();
		grupo.setId(1);
		matricula.setGrupo(grupo);
		
		assertEquals(matricula.getId(), 1);
		assertEquals(matricula.getMatricula(), 1);
		assertTrue(matricula.getEstudiante()==estudiante);
		assertTrue(matricula.getGrupo()==grupo);
		
	}
	
	@Test
	public void testCrearFactura() {
		Factura factura = new Factura();
		factura.setId(1);
		Date date = new Date();
		factura.setFecha(date);
		factura.setTotal(5000);
		
		Estudiante estudiante = new Estudiante();
		//estudiante.setId(1);
		factura.setEstudiante(estudiante);
		
		List<DetalleFactura> detalles = new ArrayList<DetalleFactura>();
		
		DetalleFactura detalle1 = new DetalleFactura();
		detalle1.setId(1);
		detalle1.setCantidad(5);
		detalle1.setCosto(500);
		detalle1.setDetalle("5 materias");
		detalles.add(detalle1);
		
		factura.setDetalles(detalles);
		
		assertEquals(factura.getId(), 1);
		assertTrue(factura.getDetalles()==detalles);
		assertTrue(factura.getEstudiante()==estudiante);
		assertTrue(factura.getFecha()==date);
		assertTrue(factura.getTotal()==5000);
		
		assertEquals(detalle1.getCantidad(), 5);
		assertTrue(detalle1.getCosto()==500);
		assertTrue(detalle1.getDetalle()=="5 materias");
		assertTrue(detalle1.getId()==1);
	}
	
	@Test
	public void testLibroDiario() {
		
		LibroDiario libro = new LibroDiario();
		Date fecha = StringToDate("15/06/2000");
		libro.setId(1);
		libro.setInicioDia(1000);
		libro.setFecha(fecha);
		libro.setObservacion("Todo bien");
		libro.setIngresos(2900.00);
		libro.setEgresos(900.00);
		libro.setSaldo(1900.00);
		libro.setMovimientos(new ArrayList<Movimiento>());
		libro.getMovimientos().add(getMovimiento());
	
		assertTrue(libro.getId()==1);
		assertTrue(libro.getInicioDia()==1000);
		assertTrue(libro.getFecha()==fecha);
		assertTrue(libro.getObservacion()=="Todo bien");
		assertTrue(libro.getIngresos()!=0);
		assertTrue(libro.getEgresos()!=0);
		assertTrue(libro.getSaldo()==1900);	
		assertTrue(libro.getMovimientos().size()==1);
	}

	public Movimiento getMovimiento() {
		Movimiento mov = new Movimiento();
		mov.setId(1);
		mov.setTipo("entrada");
		mov.setDescripcion("cobro de pension");
		mov.setValor(250.90);
		return mov;
	}

	@Test
	public void testIngresarInstituto() {
		
		Institucion insti = new Institucion();
		
		insti.setId(1);
		insti.setNombre("Hogwarts");
		insti.setRUC("1234567890");
		insti.setDireccion("Cuenca");
		insti.setTelefono("0979038347");
		
		assertTrue(insti.getId()==1);
		assertTrue(insti.getNombre()=="Hogwarts");
		assertTrue(insti.getRUC()=="1234567890");
		assertTrue(insti.getDireccion()=="Cuenca");
		assertTrue(insti.getTelefono()=="0979038347");
	}


	@Test
	public void testMovimiento() {
		
		Movimiento mov = new Movimiento();
		
		mov.setId(1);
		mov.setTipo("entrada");
		mov.setDescripcion("cobro de pension");
		mov.setValor(250.90);
		
		assertTrue(mov.getId()==1);
		assertTrue(mov.getTipo()=="entrada");
		assertTrue(mov.getDescripcion()=="cobro de pension");
		assertTrue(mov.getValor()==250.90);
		
		
	}
	
}
