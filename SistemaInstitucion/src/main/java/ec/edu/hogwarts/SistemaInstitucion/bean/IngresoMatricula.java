package ec.edu.hogwarts.SistemaInstitucion.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.model.SelectItem;
import javax.inject.Inject;
import javax.inject.Named;

import ec.edu.hogwarts.SistemaInstitucion.business.CalificacionesONLocal;
import ec.edu.hogwarts.SistemaInstitucion.business.ConfiguracionONLocal;
import ec.edu.hogwarts.SistemaInstitucion.business.GrupoONLocal;
import ec.edu.hogwarts.SistemaInstitucion.business.MatriculaONLocal;
import ec.edu.hogwarts.SistemaInstitucion.business.NivelONLocal;
import ec.edu.hogwarts.SistemaInstitucion.business.PersonaONLocal;
import ec.edu.hogwarts.SistemaInstitucion.model.Calificacion;
import ec.edu.hogwarts.SistemaInstitucion.model.Configuracion;
import ec.edu.hogwarts.SistemaInstitucion.model.Estudiante;
import ec.edu.hogwarts.SistemaInstitucion.model.Grupo;
import ec.edu.hogwarts.SistemaInstitucion.model.Materia;
import ec.edu.hogwarts.SistemaInstitucion.model.Matricula;
import ec.edu.hogwarts.SistemaInstitucion.model.Nivel;
import ec.edu.hogwarts.SistemaInstitucion.model.Persona;

@Named
@SessionScoped
public class IngresoMatricula implements Serializable{
	
	@Inject
	private MatriculaONLocal matriculaON;
	
	@Inject
	private PersonaONLocal personaON;
	
	@Inject
	private NivelONLocal nivelON;
	
	@Inject
	private GrupoONLocal grupoON;
	
	@Inject
	private CalificacionesONLocal calificacionON;
	
	@Inject
	private ConfiguracionONLocal configuracionON;
	
	private Matricula matricula = new Matricula();
	private List<Matricula>listado_matriculas;
	private List<Estudiante> estudiantes_seleccionados;
	private Persona estudiante_seleccionado;
	private String cedula;
	private Estudiante est;
	private Nivel nivel;
	private List<Nivel> niveles;
	private int nivel_id;
	private List<Materia>materias_selecionadas;
	private Grupo grupo;
	private List<Grupo>grupos;
	private List<Grupo>grupos_seleccionados;
	private List<Grupo>grupos_matriculas;
	private Calificacion calificacion;
	private List<Calificacion>lista_calificacioness;
	private Configuracion configuracion;
	
	

	@PostConstruct
	public void init() {
		
		this.grupos_matriculas=new ArrayList<Grupo>();
		this.lista_calificacioness=new ArrayList<Calificacion>();
		this.loadCarreras();
		this.loadNiveles();
		this.listado_matriculas=matriculaON.getMatricula();
		
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
		
		int horas_t=0;
		est=personaON.getEstudiante(estudiante_seleccionado.getCedula());
		
		for(Grupo g: grupos_matriculas) {
			
			horas_t=horas_t+g.getMateria().getHorasUnidad();
		}
		
		for (int i = 0; i < grupos_matriculas.size(); i++) {
			
		Calificacion ca = new Calificacion();
		ca.setAporte1(0);
		ca.setAporte2(0);
		ca.setEstado("cursando");
		ca.setEstudiante(est);
		ca.setExamen1(0);
		ca.setExamen2(0);
		ca.setGrupo(grupos_matriculas.get(i));
		ca.setTotal(0);
		
		this.lista_calificacioness.add(calificacion);
		try {
			calificacionON.insert(ca);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
		
		this.matricula.setCostoHora(2.5);
		this.matricula.setCostoMatricula(configuracionON.getConfiguracion(1).getConf_costoMatricula());
		this.matricula.setEstado(false);
		this.matricula.setTotalHoras(horas_t);
		this.matricula.setEstudiante(est);
		
		
		try {
			matriculaON.insert(this.matricula);
		
			System.out.println("Matricula: ");
		
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public String cobrarMatricula() {
		return "CostoMatricula?faces-redirect=true";
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
	
	
	public List<Grupo> getGrupos_seleccionados() {
		return grupos_seleccionados;
	}
	public void setGrupos_seleccionados(List<Grupo> grupos_seleccionados) {
		this.grupos_seleccionados = grupos_seleccionados;
	}
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
	
	
	public List<Grupo> getGrupos_matriculas() {
		return grupos_matriculas;
	}
	public void setGrupos_matriculas(List<Grupo> grupos_matriculas) {
		this.grupos_matriculas = grupos_matriculas;
	}
	
	
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
	
	
	
	
	public Calificacion getCalificacion() {
		return calificacion;
	}
	public void setCalificacion(Calificacion calificacion) {
		this.calificacion = calificacion;
	}
	public List<Calificacion> getLista_calificacioness() {
		return lista_calificacioness;
	}
	public void setLista_calificacioness(List<Calificacion> lista_calificacioness) {
		this.lista_calificacioness = lista_calificacioness;
	}
	public Configuracion getConfiguracion() {
		return configuracion;
	}
	public void setConfiguracion(Configuracion configuracion) {
		this.configuracion = configuracion;
	}
	
	
	public List<Matricula> getListado_matriculas() {
		return listado_matriculas;
	}
	public void setListado_matriculas(List<Matricula> listado_matriculas) {
		this.listado_matriculas = listado_matriculas;
	}
	public void loadData() {
		if (cedula==null) 
			return;
			
		Persona p = personaON.getPersona(cedula);
		
		estudiante_seleccionado =p;
		Estudiante estu = personaON.getEstudiante(cedula);
		estudiante_seleccionado = estu;

		
	}
	 
    //BUSCADOR DE ESTUDIANTE
    
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
			grupos_seleccionados=new ArrayList<Grupo>();
			
			grupos= grupoON.getGrupo();
	
			for (int i = 0; i < grupos.size(); i++) {

				Grupo gru=grupos.get(i);
				
				if(gru.getMateria().getNivel().getId()==nivel_id) {
		
					try {
						grupos_seleccionados.add(gru);
					} catch (Exception e2) {
						// TODO: handle exception
						System.out.println("No guarde");
					}
					
				}
				
			}
			
			
			nivel=nivelON.getNivel(nivel_id);	
			materias_selecionadas=nivel.getMaterias();
			
			
			
		}	
	   

	   
	   // guardado
		
		private boolean value1;
	    private boolean value2;

	    public boolean isValue1() {
	        return value1;
	    }

	    public void setValue1(boolean value1) {
	        this.value1 = value1;
	    }

	    public boolean isValue2() {
	        return value2;
	    }

	    public void setValue2(boolean value2) {
	        this.value2 = value2;
	    }

	    public void addMessage(int gr_id, String nombre) {
	    	int b=0;
	    	String summary ="";
	    	if (value2) {
	    		
	    		if(grupos_matriculas.size()>0) {
	    			
	    			for (int i = 0; i < grupos_matriculas.size(); i++) {
						
	    				Grupo gr=grupos_matriculas.get(i);
	    				if(gr.getId()==gr_id) {
	    					
	    					b=1;
	    					System.out.println("tamanio: "+grupos_matriculas.size());
	    					
	    				}
					}
	    			
	    		}
	    		if(b==0) {
	    		      grupos_matriculas.add(grupoON.getGrupoporCodigo(gr_id)); 
	    		      System.out.println("tamanio: "+grupos_matriculas.size());
	    		}
	    		
	    		
	
		      System.out.println("tamanio: "+grupos_matriculas.size());
	    		
	    		summary ="Grupo seleccionado "+ grupoON.getGrupoporCodigo(gr_id).getNombre()+"de la materia :"+nombre;
	    	}else {
	    		
	    		if(grupos_matriculas.size()>0) {
	    			
	    			for (int i = 0; i < grupos_matriculas.size(); i++) {
						
	    				Grupo gr=grupos_matriculas.get(i);
	    				if(gr.getId()==gr_id) {
	    					
	    					grupos_matriculas.remove(i);
	    					System.out.println("tamanio: "+grupos_matriculas.size());
	    					
	    				}
					}
	    			
	    		}
	    		
	    		summary ="Grupo eliminad "+ grupoON.getGrupoporCodigo(gr_id).getNombre()+" de la materia : "+nombre;
			}
	        
	        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(summary));
	        
	        
	    }
	    
	    // Listar
	    
	    

	    

}