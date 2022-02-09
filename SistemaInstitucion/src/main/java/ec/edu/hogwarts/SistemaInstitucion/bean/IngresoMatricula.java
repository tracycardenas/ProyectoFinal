package ec.edu.hogwarts.SistemaInstitucion.bean;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.model.SelectItem;
import javax.inject.Inject;
import javax.inject.Named;

import ec.edu.hogwarts.SistemaInstitucion.business.MatriculaONLocal;
import ec.edu.hogwarts.SistemaInstitucion.business.NivelON;
import ec.edu.hogwarts.SistemaInstitucion.business.NivelONLocal;
import ec.edu.hogwarts.SistemaInstitucion.business.PersonaONLocal;
import ec.edu.hogwarts.SistemaInstitucion.model.Docente;
import ec.edu.hogwarts.SistemaInstitucion.model.Estudiante;
import ec.edu.hogwarts.SistemaInstitucion.model.Materia;
import ec.edu.hogwarts.SistemaInstitucion.model.Matricula;
import ec.edu.hogwarts.SistemaInstitucion.model.Nivel;
import ec.edu.hogwarts.SistemaInstitucion.model.Persona;

@Named
@RequestScoped
public class IngresoMatricula {
	
	@Inject
	private MatriculaONLocal matriculaON;
	
	@Inject
	private PersonaONLocal personaON;
	
	@Inject
	private NivelONLocal nivelON;
	
	private Matricula matricula = new Matricula();
	private List<Estudiante> estudiantes_seleccionados;
	private Persona estudiante_seleccionado;
	private String cedula;
	private Estudiante est;
	private Nivel nivel;
	private List<Nivel> niveles;
	private int nivel_id;
	private List<Materia>materias_selecionadas;
	
	public List<Nivel> getNiveles() {
		return niveles;
	}
	public void setNiveles(List<Nivel> niveles) {
		this.niveles = niveles;
	}
	public int getNivel_id() {
		return nivel_id;
	}
	public void setNivel_id(int nivel_id) {
		this.nivel_id = nivel_id;
	}
	@PostConstruct
	public void init() {
		
		//this.loadPersonas();
		this.loadCarreras();
		this.loadNiveles();
	}
	public void loadCarreras() {
		
		this.estudiantes_seleccionados=personaON.getEstudiantes();
	}

	public String getCedula() {
		return cedula;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
	}

	public Estudiante getEst() {
		return est;
	}

	public void setEst(Estudiante est) {
		this.est = est;
	}

	public Matricula getMatricula() {
		return matricula;
	}

	public void setMatricula(Matricula matricula) {
		this.matricula = matricula;
	}
	
	public String guardarMatricula() {
		try {
			matriculaON.insert(this.matricula);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public List<Estudiante> getEstudiantes_seleccionados() {
	return estudiantes_seleccionados;
	}
	
	public void setEstudiantes_seleccionados(List<Estudiante> estudiantes_seleccionados) {
		this.estudiantes_seleccionados = estudiantes_seleccionados;
	}
	
	public Persona getEstudiante_seleccionado() {
		return estudiante_seleccionado;
	}
	
	public void setEstudiante_seleccionado(Persona estudiante_seleccionado) {
		this.estudiante_seleccionado = estudiante_seleccionado;
	}
	
	
	
	
	public List<Materia> getMaterias_selecionadas() {
		return materias_selecionadas;
	}
	public void setMaterias_selecionadas(List<Materia> materias_selecionadas) {
		this.materias_selecionadas = materias_selecionadas;
	}
	public Nivel getNivel() {
		return nivel;
	}
	public void setNivel(Nivel nivel) {
		this.nivel = nivel;
	}
	public void loadData() {
		if (cedula==null) 
			return;
			
		Persona p = personaON.getPersona(cedula);
		
		estudiante_seleccionado =p;
		Estudiante estu = personaON.getEstudiante(cedula);
		estudiante_seleccionado = estu;

		
	}
	// BUSCADOR
	
	 
		public List<Persona> autocompletar_Estudiantes(String query) {
	   	 List<Persona> lista_resultado = new ArrayList<Persona>();
	        for (Persona c : estudiantes_seleccionados) {
	            if (c.getCedula().toLowerCase().contains(query.toLowerCase())) {
	                lista_resultado.add(c);
	            }

	        }

	        return lista_resultado;
	   }

	   public Estudiante obtenereEstudiante_codigo(String cedula) {
	       // Aqui se debe implementar una llamda a base de datos.
	   
		   Estudiante curso_ = personaON.getEstudiante(cedula);
	       return curso_;
	   }
	  
	   
	   public List<Persona> detalles_Filtrados_estudiantes(Estudiante c) {

	       List<Persona> resultado_ = new LinkedList<>();
	       if (c != null) {
	           for (Persona ac : estudiantes_seleccionados) {
	               if (ac.getCedula().equalsIgnoreCase(c.getCedula())) {
	                   resultado_.add(ac);
	               }
	           }
	       }

	       return resultado_;
	   }
	   
	   // Nivel
	   public void loadNiveles() {
		   
		   niveles=nivelON.getNiveles();
	   }
	   
	   public List<Nivel> listNiveles(){
		   
		   
		   return nivelON.getNiveles();
	   }
	   
		public List<SelectItem> getSelecItemNiveles() {
			List<SelectItem> selectItems = new ArrayList<SelectItem>();
			List<Nivel> listaNiveles= niveles;

			for (Nivel mater : listaNiveles) {
				SelectItem item = new SelectItem(mater.getNivel());
				selectItems.add(item);

			}
			return selectItems;
		}
	   
		public void actualizarMaterias(AjaxBehaviorEvent e) {

			materias_selecionadas=new ArrayList<Materia>();
			
			nivel=nivelON.getNivel(nivel_id);
			
			materias_selecionadas=nivel.getMaterias();
			
		}	
	   
	   
	   // guardado
	    

}
