package ec.edu.hogwarts.SistemaInstitucion.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
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
import ec.edu.hogwarts.SistemaInstitucion.model.MateriaPrerrequisito;
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
	
	private Carrera carreraSeleccionada;
	private List<Carrera> carreras;
	private List<Carrera> carrerasFiltro;
	
	private Materia materiaSeleccionada;
	private List<Materia> materias;
	private List<Materia> materiasFiltro;
	
	private static boolean bandera=true;
	
	@PostConstruct
	public void init() {
		if(bandera) {
			//crearObjetos();
			bandera = false;
		}
		cargarMaterias();
		cargarMallas();
		cargarCarreras();
		setCarrerasFiltro(carreras);
		materiasFiltro = materias;
		mallasFiltro = mallas;
		niveles = new ArrayList<Nivel>();
	}
	/*
	 * 		CRUD MALLA
	 **/
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
		if(niveles.size()>0){
			this.mallaSeleccionada = new MallaCurricular();
			this.mallaSeleccionada.setEstado(true);
			this.mallaSeleccionada.setNiveles(niveles);
			this.mallaSeleccionada.setCarrera(carreraSeleccionada);
			this.insertMalla();
			return "Listar-MallasCurricular?faces-redirect=true";
		}else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("No hay niveles selecionados."));
		}
		
		return null;
	}
	public void cargarCarreras() {
		this.carreras = carreraON.getCarrera();
	}
	public void cargarMallas() {
		this.mallas = mallaCurricularON.getMallaCurricular();
	}
	
	public void cargarMaterias() {
		this.materias = materiaON.getMaterias();
	}
	public void agregarCarrera() {
		PrimeFaces.current().ajax().update("form:messages","form:dt-carrera-seleccionada");
	}
	
	public void deseleccionarCarrera() {
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
	        Carrera materia = (Carrera) value;
	        return materia.getNombre().toLowerCase().contains(filterText)
	                || materia.getModalidad().toLowerCase().contains(filterText)
	                || materia.getDuracion()< filterInt;
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
		Materia m7 = new Materia();
		
		m1.setCodigo("C-CE-ICO-001");
		m2.setCodigo("G-RF-ICO-001");
		m3.setCodigo("C-CE-ICO-002");
		m4.setCodigo("G-HU-ICO-001");
		m5.setCodigo("E-CT-ICO-001");
		m6.setCodigo("G-HU-ICO-002");
		m7.setCodigo("C-CE-ICO-003");
		
		m1.setNombre("ÁLGEBRA LINEAL");
		m2.setNombre("ANTROPOLOGÍA FILOSÓFICO-TEOLÓGICA");
		m3.setNombre("CÁLCULO DE UNA VARIABLE");
		m4.setNombre("COMUNICACIÓN ORAL Y ESCRITA");
		m5.setNombre("INTRODUCCIÓN A LAS CIENCIAS DE LA COMPUTACIÓN");
		m6.setNombre("LÓGICA");
		m7.setNombre("ECUACIONES DIFERENCIALES");
		
		m1.setHorasClase(64);
		m2.setHorasClase(32);
		m3.setHorasClase(96);
		m4.setHorasClase(32);
		m5.setHorasClase(64);
		m6.setHorasClase(32);
		m7.setHorasClase(64);
		
		m1.setHorasPracticas(64);
		m2.setHorasPracticas(16);
		m3.setHorasPracticas(96);
		m4.setHorasPracticas(32);
		m5.setHorasPracticas(64);
		m6.setHorasPracticas(32);
		m7.setHorasPracticas(64);
		
		m1.setHorasUnidad(32);
		m2.setHorasUnidad(32);
		m3.setHorasUnidad(48);
		m4.setHorasUnidad(16);
		m5.setHorasUnidad(32);
		m6.setHorasUnidad(16);
		m7.setHorasUnidad(32);
		
		m1.setDescripcion("FUNDAMENTOS TEÓRICOS");
		m2.setDescripcion("INTEGRACIÓN DE SABERES, CONTEXTOS Y CULTURA");
		m3.setDescripcion("FUNDAMENTOS TEÓRICOS");
		m4.setDescripcion("COMUNICACIÓN Y LENGUAJES");
		m5.setDescripcion("EPISTEMOLOGÍA Y METODOLOGÍA DE LA INVESTIGACIÓN");
		m6.setDescripcion("EPISTEMOLOGÍA Y METODOLOGÍA DE LA INVESTIGACIÓN");
		m7.setDescripcion("FUNDAMENTOS TEÓRICOS");
		
		m1.setUnidad("BÁSICA");
		m2.setUnidad("BÁSICA");
		m3.setUnidad("BÁSICA");
		m4.setUnidad("BÁSICA");
		m5.setUnidad("BÁSICA");
		m6.setUnidad("BÁSICA");
		m7.setUnidad("BÁSICA");

		m1.setNumeroNivel(1);
		m2.setNumeroNivel(1);
		m3.setNumeroNivel(1);
		m4.setNumeroNivel(1);
		m5.setNumeroNivel(1);
		m6.setNumeroNivel(1);
		m7.setNumeroNivel(2);
		
		List<MateriaPrerrequisito> listRequisitos = new ArrayList<MateriaPrerrequisito>();
		MateriaPrerrequisito mR1 = new MateriaPrerrequisito();
		MateriaPrerrequisito mR2 = new MateriaPrerrequisito();
		
		mR1.setMateriaRequerida(m1);
		mR2.setMateriaRequerida(m2);
		
		m7.setPrerequisitos(listRequisitos);

		try {
			this.materiaON.insert(m1);
			this.materiaON.insert(m2);
			this.materiaON.insert(m3);
			this.materiaON.insert(m4);
			this.materiaON.insert(m5);
			this.materiaON.insert(m6);
			this.materiaON.insert(m7);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("error al crear materia");
		}
		
		Carrera ca1 = new Carrera();
		Carrera ca2 = new Carrera();
		Carrera ca3 = new Carrera();
		Carrera ca4 = new Carrera();
		Carrera ca5 = new Carrera();
		
		ca1.setNombre("COMPUTACIÓN");
		ca2.setNombre("PSICOLOGÍA");
		ca3.setNombre("MEDICINA");
		ca4.setNombre("VETERINARIA");
		ca5.setNombre("ADMINISTRACIÓN DE EMPRESAS");
		
		ca1.setDuracion(4);
		ca2.setDuracion(4);
		ca3.setDuracion(4);
		ca4.setDuracion(4);
		ca5.setDuracion(4);
		
		ca1.setModalidad("Presencial");
		ca2.setModalidad("Virtual");
		ca3.setModalidad("Presencial");
		ca4.setModalidad("Presencial");
		ca5.setModalidad("Virtual");
		
		try {
			this.carreraON.insert(ca1);
			this.carreraON.insert(ca2);
			this.carreraON.insert(ca3);
			this.carreraON.insert(ca4);
			this.carreraON.insert(ca5);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
}
