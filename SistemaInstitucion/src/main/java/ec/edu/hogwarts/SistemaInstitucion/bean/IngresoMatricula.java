package ec.edu.hogwarts.SistemaInstitucion.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
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
import ec.edu.hogwarts.SistemaInstitucion.business.InscripcionONLocal;
import ec.edu.hogwarts.SistemaInstitucion.business.MallaCurricularONLocal;
import ec.edu.hogwarts.SistemaInstitucion.business.MateriaONLocal;
import ec.edu.hogwarts.SistemaInstitucion.business.MateriaPrerrequisitoONLocal;
import ec.edu.hogwarts.SistemaInstitucion.business.MatriculaONLocal;
import ec.edu.hogwarts.SistemaInstitucion.business.NivelONLocal;
import ec.edu.hogwarts.SistemaInstitucion.business.PersonaONLocal;
import ec.edu.hogwarts.SistemaInstitucion.model.Calificacion;
import ec.edu.hogwarts.SistemaInstitucion.model.Configuracion;
import ec.edu.hogwarts.SistemaInstitucion.model.Estudiante;
import ec.edu.hogwarts.SistemaInstitucion.model.Grupo;
import ec.edu.hogwarts.SistemaInstitucion.model.Inscripcion;
import ec.edu.hogwarts.SistemaInstitucion.model.MallaCurricular;
import ec.edu.hogwarts.SistemaInstitucion.model.Materia;
import ec.edu.hogwarts.SistemaInstitucion.model.MateriaPrerrequisito;
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
	
	@Inject
	private MallaCurricularONLocal mallaON;
	
	@Inject
	private InscripcionONLocal inscripcionON;
	
	@Inject
	private MateriaONLocal materiaON;
	
	@Inject
	private MateriaPrerrequisitoONLocal prereON; 
	
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
	private List<Materia>materias;
	private Grupo grupo;
	private List<Grupo>grupos;
	private List<Grupo>grupos_seleccionados;
	private List<Grupo>grupos_matriculas;
	private Calificacion calificacion;
	private List<Calificacion>lista_calificacioness;
	private List<Calificacion>calificaciones;
	private Configuracion configuracion;
	private MallaCurricular malla;
	private Inscripcion inscripcion;
	private boolean estado=false;
	

	@PostConstruct
	public void init() {
		
		this.grupos_matriculas=new ArrayList<Grupo>();
		this.lista_calificacioness=new ArrayList<Calificacion>();
		this.loadCarreras();
		this.loadNiveles();
		this.listado_matriculas=matriculaON.getMatricula();
		this.niveles= new ArrayList<Nivel>();
		
		
	}
	
	
	public List<Calificacion> getCalificaciones() {
		return calificaciones;
	}


	public void setCalificaciones(List<Calificacion> calificaciones) {
		this.calificaciones = calificaciones;
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
		this.grupos_matriculas=new ArrayList<Grupo>();
		this.lista_calificacioness=new ArrayList<Calificacion>();
		this.loadCarreras();
		this.loadNiveles();
		this.listado_matriculas=matriculaON.getMatricula();
		this.niveles= new ArrayList<Nivel>();

		return "CostoMatricula?faces-redirect=true";
	}
	
	public void loadPer() {
		
		estudiantes_seleccionados=personaON.getEstudiantes();
	}
	
	
	
	
	
	
	
	public List<Materia> getMaterias() {
		return materias;
	}
	public void setMaterias(List<Materia> materias) {
		this.materias = materias;
	}
	public MallaCurricular getMalla() {
		return malla;
	}
	public void setMalla(MallaCurricular malla) {
		this.malla = malla;
	}
	public Inscripcion getInscripcion() {
		return inscripcion;
	}
	public void setInscripcion(Inscripcion inscripcion) {
		this.inscripcion = inscripcion;
	}
	public boolean isEstado() {
		return estado;
	}
	public void setEstado(boolean estado) {
		this.estado = estado;
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
		listado_matriculas=matriculaON.getMatricula();

		
	}
	 
	public void getMatriculas() {
		
		listado_matriculas=matriculaON.getMatricula();
	}
	
	public List<Matricula> obtenerMatriculas(){
		listado_matriculas=matriculaON.getMatricula();
		return  listado_matriculas;
	}
    //BUSCADOR DE ESTUDIANTE
    
	public List<Persona> autocompletar_Estudiantes(String query) {
		this.estado=false;
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
	   

	   
		public List<Grupo> actualizarMaterias() {

			if(estudiante_seleccionado!=null) {
				
			
				grupos= grupoON.getGrupos();
				
				inscripcion=inscripcionON.getInscripcionEstudiante(estudiante_seleccionado.getCedula());

				malla=mallaON.getMalla(inscripcion.getMallaCurricular().getId());
				
				niveles=malla.getNiveles();
				calificaciones=new ArrayList<Calificacion>();
				materias=new ArrayList<Materia>();
			
				int mayor=0;
				materias_selecionadas=new ArrayList<Materia>();
				grupos_seleccionados=new ArrayList<Grupo>();
				
				//Verifica si el estudiante tiene registrada alguna calificacion
				
				
				if(calificacionON.getCalificacion().size()>0) {
				
				for(Calificacion cali : calificacionON.getCalificacion()) {
		
					
					if(estudiante_seleccionado.getCedula().equalsIgnoreCase(cali.getEstudiante().getCedula())) {
					
						if(mayor<cali.getGrupo().getMateria().getNumeroNivel()) {
						mayor=cali.getGrupo().getMateria().getNumeroNivel();
						}
						calificaciones.add(cali);
						
						estado=true;
					}
				}}
				
				// Cargar Informacion de : grupos, inscripcion,malla y niveles;
				
	
				
				// Cargar grupos seleccionados
	
				if(estado) {
					
					//Mayor: esta variable contiene el nivel mas alto que a cursado el estudiante
					//A continuacion se obtendra todas las materias del nivel "mayor" e inferiores para 
					//verificar que todas esten aprobadas 
					
					for (Nivel niv: niveles) {
						
						for(Calificacion cal: calificaciones) {
							
							if(niv.getNivel() <= mayor &&  niv.getMateria().getId()==cal.getGrupo().getMateria().getId() && cal.getEstado().equalsIgnoreCase("Reprobado")) {
								
								materias.add(niv.getMateria());
								for(Grupo g: grupoON.getGrupos()) {
									
									if(g.getMateria().getId()==niv.getMateria().getId()) {
										
										boolean b=true;
										for(Grupo grup: grupos_seleccionados) {
											
											if(grup.getMateria().getId()==g.getMateria().getId()) {
												b=false;
											}
										}
										
										if(b) {
										grupos_seleccionados.add(g);
										}
										
										
									}
								}
								
							}
							
						}
	
					}
					
					for(Nivel niv : niveles) {
						
						if (niv.getNivel()==mayor+1) {
						
							for(Grupo g: grupoON.getGrupos()) {
								
								if(g.getMateria().getId()==niv.getMateria().getId() ) {
									
									grupos_seleccionados.add(g);
									
								}
							}
							
					}
						
					}
					
					
					
				}else {
					
					
					
					for(Nivel niv: niveles) {
						
						if(niv.getNivel()==1) {
							
							for(Grupo g: grupoON.getGrupos()) {
								
								if(g.getMateria().getId()==niv.getMateria().getId()) {
									
									grupos_seleccionados.add(g);
								}
								
							}
				
						}
					}
	
					
				}
				
				
				
				}
				
				return grupos_seleccionados;
				
			}	
	public boolean Estado(int mat_cod) {
				
				boolean estado_materia=false;
				
				System.out.println("Cod: "+mat_cod);
				
				if(materiaON.getMateriaporCodigo(mat_cod).getNumeroNivel()!=1) {
				
				if(estado) {
					
					List<MateriaPrerrequisito>pre=new ArrayList<MateriaPrerrequisito>();

					pre=materiaON.getMateriaporCodigo(mat_cod).getPrerequisitos();
						
						
			
				
				 for(MateriaPrerrequisito mp :pre) {
					 
					 for(Materia m : materias) {
						 m.setPrerequisitos(pre);
						 
						 if(mp.getMateriaRequerida().getId()==m.getId()) {
							 
							 System.out.println("mp: "+mp.getMateriaRequerida().getId());
							 System.out.println("m: "+m.getId());
							 estado_materia=true;
						 }
					 }
					 
				 }
				

			}}
			
			
			
			return estado_materia;
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
	    
	   public void construirObjetos() {
		   
		   
		   
	   }
	    
	    

	    

}