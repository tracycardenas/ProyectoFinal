package ec.edu.hogwarts.SistemaInstitucion.bean;

import java.io.Serializable;
import java.util.Date;
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

import ec.edu.hogwarts.SistemaInstitucion.business.EspacioFisicoONLocal;
import ec.edu.hogwarts.SistemaInstitucion.business.GrupoONLocal;
import ec.edu.hogwarts.SistemaInstitucion.business.MateriaONLocal;
import ec.edu.hogwarts.SistemaInstitucion.business.PersonaONLocal;
import ec.edu.hogwarts.SistemaInstitucion.model.Docente;
import ec.edu.hogwarts.SistemaInstitucion.model.EspacioFisico;
import ec.edu.hogwarts.SistemaInstitucion.model.Grupo;
import ec.edu.hogwarts.SistemaInstitucion.model.Materia;

@Named
@ViewScoped
public class GruposOfertadosView implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Inject
	private GrupoONLocal grupoON;
	@Inject
	private MateriaONLocal materiaON;
	@Inject
	private PersonaONLocal docenteON;
	@Inject
	private EspacioFisicoONLocal espacioFisicoON;
	
	private static boolean bandera = true;
	
	private Grupo grupo;
	private Docente docenteSeleccionado;
	private Materia materiaSeleccionada;
	private EspacioFisico espacioFisicoSeleccionado;
	private Grupo grupoActual; 				//Esto es para eliminar alguna selección
	private List<Grupo> grupos;
	private List<Grupo> gruposFiltro;
	private List<Materia> materias;
	private List<Materia> materiasFiltro;
	private List<Docente> docentes;
	private List<Docente> docentesFiltro;
	private List<EspacioFisico> espaciosFisicos;
	private List<EspacioFisico> espaciosFisicosFiltro;
	
	@PostConstruct
	public void init() {
		if(bandera) {
			//this.crearObjetos();
			bandera = false;
		}
		this.cargarGrupos();
		this.cargarMaterias();
		this.cargarDocentes();
		this.cargarEspaciosFisicos();
		this.grupo = new Grupo();
		this.grupoActual = new Grupo();
		this.setGruposFiltro(this.grupos);
		//this.materiaSeleccionada = new Materia();
	}
	/*
	 * 		CRUD's GRUPOS - MATERIAS - DOCENTES - ESPACIOS FÍSICOS
	 * */
	
	public void insertGrupo(Grupo g) {
		try {
			this.grupoON.insert(g);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Error al insertar grupo");
		}
	}
	
	public void borrarGrupo() {
		try {
			grupoON.delete(this.grupoActual.getId());
			grupos.remove(grupoActual);
			this.grupoActual = null;
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Grupo Eliminado"));
			PrimeFaces.current().ajax().update("form:messages","form:dt-grupos");
		} catch (Exception e) {
			// TODO: handle exception
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Error al eliminar"));
		}
	}
	
	public void cargarGrupos() {
		this.grupos = this.grupoON.getGrupos();
	}
	
	public void cargarMaterias() {
		this.materias = materiaON.getMaterias();
	}
	
	public void cargarDocentes() {
		this.docentes = docenteON.getDocentes();
	}
	
	public void cargarEspaciosFisicos() {
		this.espaciosFisicos = espacioFisicoON.getEspacioFisico();
	}
	
	/*
	 * 		FUNCION CREAR GRUPOS
	 * */
	public String guardarGrupo() {
		try {
			this.grupo.setMateria(materiaSeleccionada);
			this.grupo.setDocente(docenteSeleccionado);
			this.grupo.setEspacioFisico(espacioFisicoSeleccionado);
			this.insertGrupo(grupo);
			return "Listar-Grupos?faces-redirect=true";
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
		
	}
	
	
	/*
	 * 			ACTUALIZACIÓN DE TABLAS
	 * */
	public void asignarMateria() {
		PrimeFaces.current().ajax().update("form:messages","form:dt-materias-seleccionada");
	}
	
	public void asignarDocente() {
		PrimeFaces.current().ajax().update("form:messages","form:dt-docente-seleccionado");
	}
	
	public void asignarEspacioFisico() {
		PrimeFaces.current().ajax().update("form:messages","form:dt-espacioFisico-seleccionado");
	}
	/*
	 * 		FUNCIONES DE FILTROS
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
	
	public boolean globalFilterFunctionDocentes(Object value, Object filter, Locale locale) {	//FILTRO DE DOCENTES
        String filterText = (filter == null) ? null : filter.toString().trim().toLowerCase();
        if (LangUtils.isBlank(filterText)) {
            return true;
        }
        Docente d = (Docente) value;
        return d.getCedula().toLowerCase().contains(filterText)
                || d.getNombre().toLowerCase().contains(filterText)
                || d.getApellido().toLowerCase().contains(filterText)
                || d.getEspecialidad().toLowerCase().contains(filterText);
    }
	
	public boolean globalFilterFunctionEspaciosFisicos(Object value, Object filter, Locale locale) {	//FILTRO DE DOCENTES
        String filterText = (filter == null) ? null : filter.toString().trim().toLowerCase();
        if (LangUtils.isBlank(filterText)) {
            return true;
        }
        EspacioFisico e = (EspacioFisico) value;
        return e.getDescripcion().toLowerCase().contains(filterText)
                || e.getUbicacion().toLowerCase().contains(filterText);
    }
	
	public boolean globalFilterFunctionGrupos(Object value, Object filter, Locale locale) { 	//FILTRO DE GRUPOS
        String filterText = (filter == null) ? null : filter.toString().trim().toLowerCase();
        if (LangUtils.isBlank(filterText)) {
            return true;
        }
        int filterInt = getInteger(filterText);
        Grupo o = (Grupo) value;
        return o.getNombre().toLowerCase().contains(filterText)
                || o.getModalidad().toLowerCase().contains(filterText)
                || o.getMateria().getCodigo().contains(filterText)
                || o.getMateria().getNombre().contains(filterText)
                || o.getPeriodo()< filterInt;
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
	 * 		CONSTRUCIÓN DE GRUPOS
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
		
		m1.setNumeroNivel(1);
		m2.setNumeroNivel(1);
		m3.setNumeroNivel(1);
		m4.setNumeroNivel(1);
		m5.setNumeroNivel(1);
		m6.setNumeroNivel(1);
		
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
		}
		
	}
	/*
	 * 		GET'S AND SET'S
	 * */
	public List<Grupo> getGrupos() {
		return grupos;
	}
	public void setGrupos(List<Grupo> grupos) {
		this.grupos = grupos;
	}
	public Grupo getGrupo() {
		return grupo;
	}
	public void setGrupo(Grupo grupo) {
		this.grupo = grupo;
	}
	public Grupo getGrupoActual() {
		return grupoActual;
	}
	public void setGrupoActual(Grupo grupoActual) {
		this.grupoActual = grupoActual;
	}
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
	public List<Grupo> getGruposFiltro() {
		return gruposFiltro;
	}
	public void setGruposFiltro(List<Grupo> gruposFiltro) {
		this.gruposFiltro = gruposFiltro;
	}

	public Docente getDocenteSeleccionado() {
		return docenteSeleccionado;
	}

	public void setDocenteSeleccionado(Docente docenteSeleccionado) {
		this.docenteSeleccionado = docenteSeleccionado;
	}

	public List<Docente> getDocentes() {
		return docentes;
	}

	public void setDocentes(List<Docente> docentes) {
		this.docentes = docentes;
	}

	public List<Materia> getMateriasFiltro() {
		return materiasFiltro;
	}

	public void setMateriasFiltro(List<Materia> materiasFiltro) {
		this.materiasFiltro = materiasFiltro;
	}

	public List<Docente> getDocentesFiltro() {
		return docentesFiltro;
	}

	public void setDocentesFiltro(List<Docente> docentesFiltro) {
		this.docentesFiltro = docentesFiltro;
	}

	public List<EspacioFisico> getEspaciosFisicos() {
		return espaciosFisicos;
	}

	public void setEspaciosFisicos(List<EspacioFisico> espaciosFisicos) {
		this.espaciosFisicos = espaciosFisicos;
	}

	public List<EspacioFisico> getEspaciosFisicosFiltro() {
		return espaciosFisicosFiltro;
	}

	public void setEspaciosFisicosFiltro(List<EspacioFisico> espaciosFisicosFiltro) {
		this.espaciosFisicosFiltro = espaciosFisicosFiltro;
	}

	public EspacioFisico getEspacioFisicoSeleccionado() {
		return espacioFisicoSeleccionado;
	}

	public void setEspacioFisicoSeleccionado(EspacioFisico espacioFisicoSeleccionado) {
		this.espacioFisicoSeleccionado = espacioFisicoSeleccionado;
	}
}
