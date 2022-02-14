package ec.edu.hogwarts.SistemaInstitucion.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.PrimeFaces;
import org.primefaces.util.LangUtils;

import ec.edu.hogwarts.SistemaInstitucion.business.CarreraONLocal;
import ec.edu.hogwarts.SistemaInstitucion.business.MallaCurricularONLocal;
import ec.edu.hogwarts.SistemaInstitucion.business.MateriaONLocal;
import ec.edu.hogwarts.SistemaInstitucion.model.Carrera;
import ec.edu.hogwarts.SistemaInstitucion.model.MallaCurricular;
import ec.edu.hogwarts.SistemaInstitucion.model.Materia;
import ec.edu.hogwarts.SistemaInstitucion.model.Nivel;

@Named
@ViewScoped
public class MallasCurricularesView implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Inject
	private MallaCurricularONLocal mallaCurricularON; 
	@Inject
	private MateriaONLocal materiaON;
	@Inject
	private CarreraONLocal carreraON;
	
	private MallaCurricular mallaSeleccionada;
	
	private List<MallaCurricular> mallas;
	private List<MallaCurricular> mallasFiltro;
	
	private Nivel nivelSelecionado;
	private List<Nivel> niveles;
	
	
	private Materia materiaSeleccionada;
	private List<Materia> materias;
	private List<Materia> materiasFiltro;
	
	private Carrera carreraSeleccionada;
	private List<Carrera> carreras;
	private List<Carrera> carrerasFiltro;
	
	
	private static boolean bandera=true;
	
	@PostConstruct
	public void init() {
		if(bandera) {
			crearObjetos();
			bandera = false;
		}
		cargarMaterias();
		cargarCarreras();
		cargarMallas();
		//carreraSeleccionada = new Carrera();
		materiasFiltro = materias;
		carrerasFiltro = carreras;
		mallasFiltro = mallas;
	}
	/*
	 * 		CRUD MATERIAS
	 * */
	
	public void insertMalla() {
		try {
			this.mallaCurricularON.insert(this.mallaSeleccionada);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("error al persistir");
		}
	}
	public void deleteMalla() {
		try {
			this.mallaCurricularON.delete(this.mallaSeleccionada.getId());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("error al borrar malla");
		}
	}
	
	public void borrarMalla() {
		this.mallas.remove(this.mallaSeleccionada);
		this.deleteMalla();
		this.mallaSeleccionada = null;
	}
	
	public String guardarMalla() {
		this.mallaSeleccionada = new MallaCurricular();
		this.mallaSeleccionada.setEstado(true);
		this.mallaSeleccionada.setNiveles(niveles);
		
		if(this.carreraSeleccionada!=null && niveles.size()>0) {
			this.mallaSeleccionada.setDescripcion(this.carreraSeleccionada.getNombre());			
			this.carreraSeleccionada.getMallas().add(mallaSeleccionada);
			this.updateCarrera();
			return "Lista-MallasCurricular?faces-redirect=true";
		}else if(niveles.size()>1){
			this.insertMalla();
			return "Lista-MallasCurricular?faces-redirect=true";
		}
		return null;
	}
	
	public void cargarMallas() {
		this.mallas = mallaCurricularON.getMallaCurricular();
	}
	
	public void updateCarrera() {
		try {
			this.carreraON.insert(carreraSeleccionada);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Error al agregar malla a carrera");
		}
	}
	public void cargarMaterias() {
		this.materias = materiaON.getMaterias();
	}
	public void cargarCarreras() {
		this.carreras = carreraON.getCarrera();
	}
	
	public void agregarCarrera() {
		PrimeFaces.current().ajax().update("form:messages","form:dt-carrera-seleccionada");
	}
	public void eliminarCarrera() {
		this.carreraSeleccionada = null;
		PrimeFaces.current().ajax().update("form:messages","form:dt-carrera-seleccionada");
	}
	
	public void agregarNivel() {
		if(this.niveles==null)
			this.niveles = new ArrayList<Nivel>();
		this.nivelSelecionado = new Nivel();
		this.nivelSelecionado.setNivel(this.materiaSeleccionada.getNumeroNivel());
		this.nivelSelecionado.setMateria(this.materiaSeleccionada);
		this.niveles.add(nivelSelecionado);
		this.materias.remove(this.materiaSeleccionada);
		PrimeFaces.current().ajax().update("form:messages","form:tb-niveles");
		PrimeFaces.current().ajax().update("form:messages","form:dt-materias-seleccion");
	}
	
	public void eliminarNivel() {
		materias.add(nivelSelecionado.getMateria());
		niveles.remove(nivelSelecionado);
		PrimeFaces.current().ajax().update("form:messages","form:tb-niveles");
		PrimeFaces.current().ajax().update("form:messages","form:dt-materias-seleccion");
	}
	/*
	 * 		FILTROS DE OBJETOS
	 * */
	public boolean globalFilterFunctionMaterias(Object value, Object filter, Locale locale) {	//FILTRO DE MATERIAS
		 String filterText = (filter == null) ? null : filter.toString().trim().toLowerCase();
	        if (LangUtils.isBlank(filterText)) {
	            return true;
	        }
	        int filterInt = getInteger(filterText);
	        Materia materia = (Materia) value;
	        return materia.getCodigo().toLowerCase().contains(filterText)
	                || materia.getNombre().toLowerCase().contains(filterText)
	                || materia.getDescripcion().toLowerCase().contains(filterText)
	                || materia.getNumeroNivel()< filterInt;
	}
	
	public boolean globalFilterFunctionCarreras(Object value, Object filter, Locale locale) {	//FILTRO DE MATERIAS
		 String filterText = (filter == null) ? null : filter.toString().trim().toLowerCase();
	        if (LangUtils.isBlank(filterText)) {
	            return true;
	        }
	        int filterInt = getInteger(filterText);
	        Carrera obj = (Carrera) value;
	        return obj.getNombre().toLowerCase().contains(filterText)
	                || obj.getModalidad().toLowerCase().contains(filterText)
	                || obj.getDuracion() < filterInt;
	}
	
	private int getInteger(String string) {
        try {
            return Integer.parseInt(string);
        }
        catch (NumberFormatException ex) {
            return 0;
        }
    }
	/*
	 * 		CONTRUCCIÓN DE OBJETOS
	 * */
	public void crearObjetos() {
		Materia m1 = new Materia();
		Materia m2 = new Materia();
		Materia m3 = new Materia();
		Materia m4 = new Materia();
		Materia m5 = new Materia();
		Materia m6 = new Materia();
		
		m1.setCodigo("C-CE-ICO-001");
		m2.setCodigo("G-RF-ICO-001");
		m3.setCodigo("C-CE-ICO-002");
		m4.setCodigo("G-HU-ICO-001");
		m5.setCodigo("E-CT-ICO-001");
		m6.setCodigo("G-HU-ICO-002");
		
		m1.setNombre("ÁLGEBRA LINEAL");
		m2.setNombre("ANTROPOLOGÍA FILOSÓFICO-TEOLÓGICA");
		m3.setNombre("CÁLCULO DE UNA VARIABLE");
		m4.setNombre("COMUNICACIÓN ORAL Y ESCRITA");
		m5.setNombre("INTRODUCCIÓN A LAS CIENCIAS DE LA COMPUTACIÓN");
		m6.setNombre("LÓGICA");
		
		m1.setHorasClase(64);
		m2.setHorasClase(32);
		m3.setHorasClase(96);
		m4.setHorasClase(32);
		m5.setHorasClase(64);
		m6.setHorasClase(32);
		
		m1.setHorasPracticas(64);
		m2.setHorasPracticas(16);
		m3.setHorasPracticas(96);
		m4.setHorasPracticas(32);
		m5.setHorasPracticas(64);
		m6.setHorasPracticas(32);
		
		m1.setHorasUnidad(32);
		m2.setHorasUnidad(32);
		m3.setHorasUnidad(48);
		m4.setHorasUnidad(16);
		m5.setHorasUnidad(32);
		m6.setHorasUnidad(16);
		
		m1.setDescripcion("FUNDAMENTOS TEÓRICOS");
		m2.setDescripcion("INTEGRACIÓN DE SABERES, CONTEXTOS Y CULTURA");
		m3.setDescripcion("FUNDAMENTOS TEÓRICOS");
		m4.setDescripcion("COMUNICACIÓN Y LENGUAJES");
		m5.setDescripcion("EPISTEMOLOGÍA Y METODOLOGÍA DE LA INVESTIGACIÓN");
		m6.setDescripcion("EPISTEMOLOGÍA Y METODOLOGÍA DE LA INVESTIGACIÓN");
		
		m1.setUnidad("BÁSICA");
		m2.setUnidad("BÁSICA");
		m3.setUnidad("BÁSICA");
		m4.setUnidad("BÁSICA");
		m5.setUnidad("BÁSICA");
		m6.setUnidad("BÁSICA");

		m1.setNumeroNivel(1);
		m2.setNumeroNivel(1);
		m3.setNumeroNivel(1);
		m4.setNumeroNivel(1);
		m5.setNumeroNivel(1);
		m6.setNumeroNivel(1);
		//m1.setPlanAnalitico(planAnalitico);
		//m2.setPlanAnalitico(planAnalitico);
		//m3.setPlanAnalitico(planAnalitico);
		//m4.setPlanAnalitico(planAnalitico);
		//m5.setPlanAnalitico(planAnalitico);
		//m6.setPlanAnalitico(planAnalitico);
		try {
			this.materiaON.insert(m1);
			this.materiaON.insert(m2);
			this.materiaON.insert(m3);
			this.materiaON.insert(m4);
			this.materiaON.insert(m5);
			this.materiaON.insert(m6);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("error al crear materia");
		}
		/*
		Docente d1 = new Docente();
		Docente d2 = new Docente();
		Docente d3 = new Docente();
		Docente d4 = new Docente();
		Docente d5 = new Docente();
		
		d1.setCedula("1321");
		d2.setCedula("2321");
		d3.setCedula("3321");
		d4.setCedula("4321");
		d5.setCedula("5321");
		
		d1.setNombre("Juan");
		d2.setNombre("Pablo");
		d3.setNombre("María");
		d4.setNombre("Esther");
		d5.setNombre("Jorge");
		
		d1.setApellido("Arizaga");
		d2.setApellido("Berrezueta");
		d3.setApellido("Calle");
		d4.setApellido("Dúmas");
		d5.setApellido("Estrella");
		
		d1.setEmail("juan@mail.com");
		d2.setEmail("pablo@mail.com");
		d3.setEmail("maria@mail.com");
		d4.setEmail("esther@mail.com");
		d5.setEmail("jorge@mail.com");
		
		d1.setEspecialidad("INGENIERÍA EN SISTEMAS");
		d2.setEspecialidad("INGENIERÍA MECÁNICA");
		d3.setEspecialidad("ADMINISTRACIÓN DE EMPRESAS");
		d4.setEspecialidad("PSICOLOGÍA SOCIAL");
		d5.setEspecialidad("MEDICINA GENERAL");
		
		d1.setDireccion("Ricaurte");
		d2.setDireccion("Sayausi");
		d3.setDireccion("Girón");
		d4.setDireccion("Paute");
		d5.setDireccion("Paccha");
		
		d1.setTelefono("098");
		d2.setTelefono("097");
		d3.setTelefono("096");
		d4.setTelefono("095");
		d5.setTelefono("094");
		
		d1.setFechaNacimiento(new Date());
		d2.setFechaNacimiento(new Date());
		d3.setFechaNacimiento(new Date());
		d4.setFechaNacimiento(new Date());
		d5.setFechaNacimiento(new Date());
		
		d1.setRol("Docente");
		d2.setRol("Docente");
		d3.setRol("Docente");
		d4.setRol("Docente");
		d5.setRol("Docente");
		try {
			this.docenteON.insert(d1);
			this.docenteON.insert(d2);
			this.docenteON.insert(d3);
			this.docenteON.insert(d4);
			this.docenteON.insert(d5);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Error de creación docente");
		}
		
		EspacioFisico eF1 = new EspacioFisico();
		EspacioFisico eF2 = new EspacioFisico();
		EspacioFisico eF3 = new EspacioFisico();
		EspacioFisico eF4 = new EspacioFisico();
		EspacioFisico eF5 = new EspacioFisico();
		
		eF1.setDescripcion("Aula 01");
		eF2.setDescripcion("Laboratorio 01");
		eF3.setDescripcion("Sala 01");
		eF4.setDescripcion("Aula 02");
		eF5.setDescripcion("Laboratorio 02");
		
		eF1.setUbicacion("Edificio 1");
		eF2.setUbicacion("Edificio 2");
		eF3.setUbicacion("Edificio 3");
		eF4.setUbicacion("Edificio 4");
		eF5.setUbicacion("Edificio 5");
		
		try {
			this.espacioFisicoON.insert(eF1);
			this.espacioFisicoON.insert(eF2);
			this.espacioFisicoON.insert(eF3);
			this.espacioFisicoON.insert(eF4);
			this.espacioFisicoON.insert(eF5);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Error al insertar espacio físico");
		}*/
		Carrera carr1 = new Carrera();
		Carrera carr2 = new Carrera();
		Carrera carr3 = new Carrera();
		Carrera carr4 = new Carrera();
		Carrera carr5 = new Carrera();
		
		carr1.setNombre("COMPUTACIÓN");
		carr2.setNombre("PSICOLOGÍA");
		carr3.setNombre("MEDICÍNA");
		carr4.setNombre("FILOSOFÍA");
		carr5.setNombre("ARQUITECTURA");
		
		carr1.setModalidad("PRESENCIAL");
		carr2.setModalidad("EN LINEA");
		carr3.setModalidad("PRESENCIAL");
		carr4.setModalidad("PRESENCIAL");
		carr5.setModalidad("EN LINEA");
		
		carr1.setDuracion(4);
		carr2.setDuracion(4);
		carr3.setDuracion(4);
		carr4.setDuracion(4);
		carr5.setDuracion(4);
		
		try {
			carreraON.insert(carr1);
			carreraON.insert(carr2);
			carreraON.insert(carr3);
			carreraON.insert(carr4);
			carreraON.insert(carr5);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("error al agregar carreras");
		}
	}
	/*
	 * 		GETTERS - SETTERS
	 * */
	public Materia getMateriaSeleccionada() {
		return materiaSeleccionada;
	}
	public void setMateriaSeleccionada(Materia materiaSeleccionada) {
		this.materiaSeleccionada = materiaSeleccionada;
	}
	public List<Materia> getMaterias() {
		return materias;
	}
	public void setMaterias(List<Materia> materias) {
		this.materias = materias;
	}
	public List<Materia> getMateriasFiltro() {
		return materiasFiltro;
	}
	public void setMateriasFiltro(List<Materia> materiasFiltro) {
		this.materiasFiltro = materiasFiltro;
	}
	public Carrera getCarreraSeleccionada() {
		return carreraSeleccionada;
	}
	public void setCarreraSeleccionada(Carrera carreraSeleccionada) {
		this.carreraSeleccionada = carreraSeleccionada;
	}
	public List<Carrera> getCarreras() {
		return carreras;
	}
	public void setCarreras(List<Carrera> carreras) {
		this.carreras = carreras;
	}
	public List<Carrera> getCarrerasFiltro() {
		return carrerasFiltro;
	}
	public void setCarrerasFiltro(List<Carrera> carrerasFiltro) {
		this.carrerasFiltro = carrerasFiltro;
	}
	public List<Nivel> getNiveles() {
		return niveles;
	}
	public void setNiveles(List<Nivel> niveles) {
		this.niveles = niveles;
	}
	public Nivel getNivelSelecionado() {
		return nivelSelecionado;
	}
	public void setNivelSelecionado(Nivel nivelSelecionado) {
		this.nivelSelecionado = nivelSelecionado;
	}

	public List<MallaCurricular> getMallas() {
		return mallas;
	}
	public void setMallas(List<MallaCurricular> mallas) {
		this.mallas = mallas;
	}
	public List<MallaCurricular> getMallasFiltro() {
		return mallasFiltro;
	}
	public void setMallasFiltro(List<MallaCurricular> mallasFiltro) {
		this.mallasFiltro = mallasFiltro;
	}
	public MallaCurricular getMallaSeleccionada() {
		return mallaSeleccionada;
	}
	public void setMallaSeleccionada(MallaCurricular mallaSeleccionada) {
		this.mallaSeleccionada = mallaSeleccionada;
	}
}
