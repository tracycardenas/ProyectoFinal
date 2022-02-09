package ec.edu.hogwarts.SistemaInstitucion.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;


import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import ec.edu.hogwarts.SistemaInstitucion.business.CarreraONLocal;
import ec.edu.hogwarts.SistemaInstitucion.business.ConfiguracionONLocal;
import ec.edu.hogwarts.SistemaInstitucion.business.InscripcionONLocal;
import ec.edu.hogwarts.SistemaInstitucion.business.PersonaONLocal;
import ec.edu.hogwarts.SistemaInstitucion.model.Carrera;
import ec.edu.hogwarts.SistemaInstitucion.model.Configuracion;
import ec.edu.hogwarts.SistemaInstitucion.model.Estudiante;
import ec.edu.hogwarts.SistemaInstitucion.model.Inscripcion;
import ec.edu.hogwarts.SistemaInstitucion.model.MallaCurricular;
import ec.edu.hogwarts.SistemaInstitucion.model.Persona;



@Named
@SessionScoped
public class BuscadorBean implements Serializable {


	@Inject
	private CarreraONLocal carrerasON;
	
	@Inject
	private InscripcionONLocal inscripcionON;

	@Inject
	private ConfiguracionONLocal configuracionON;
	
	@Inject
	private PersonaONLocal personaON;
	

	private List <Carrera> carreras_seleccionadas;
	private Carrera carrera_seleccionada;
	private List<Estudiante> estudiantes_seleccionados;
	private Persona estudiante_seleccionado;
	private String cedula;
	private Estudiante est;
	private Carrera carrera;
	private MallaCurricular malla;
	private Date fecha;
	private Configuracion conf;
	private Inscripcion inscripcion = new Inscripcion();

	
	@PostConstruct
	public void init() {
		
		this.loadCarreras();
	}
	public void loadCarreras() {
		
		this.carreras_seleccionadas=carrerasON.getCarrera();
		this.estudiantes_seleccionados=personaON.getEstudiantes();
	}
    
	    
   
    public List<Carrera> getCarreras_seleccionadas() {
		return carreras_seleccionadas;
	}



	public void setCarreras_seleccionadas(List<Carrera> carreras_seleccionadas) {
		this.carreras_seleccionadas = carreras_seleccionadas;
	}



	public Carrera getCarrera_seleccionada() {
		return carrera_seleccionada;
	}



	public void setCarrera_seleccionada(Carrera carrera_seleccionada) {
		this.carrera_seleccionada = carrera_seleccionada;
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
	public void setEstudiante_seleccionado(Estudiante estudiante_seleccionado) {
		this.estudiante_seleccionado = estudiante_seleccionado;
	}
	
	

	//BUSCADOR DE CARRERA



	public String getCedula() {
		return cedula;
	}
	public void setCedula(String cedula) {
		this.cedula = cedula;
	}
	public List<Carrera> autocompletar_cursos(String query) {
    	 List<Carrera> lista_resultado = new ArrayList<Carrera>();
         for (Carrera c : carreras_seleccionadas) {
             if (c.getNombre().toLowerCase().contains(query.toLowerCase())) {
                 lista_resultado.add(c);
             }

         }

         return lista_resultado;
    }

    public Carrera obtenerCarrera_codigo(int id_carrera) {
        // Aqui se debe implementar una llamda a base de datos.
    
        Carrera curso_ = carrerasON.getCarrera(id_carrera);
        return curso_;
    }
   
    
    public List<Carrera> detalles_Filtrados(Carrera c) {

        List<Carrera> resultado_ = new LinkedList<>();
        if (c != null) {
            for (Carrera ac : carreras_seleccionadas) {
                if (ac.getNombre().equalsIgnoreCase(c.getNombre())) {
                    resultado_.add(ac);
                }
            }
        }

        return resultado_;
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
    
   public void imprimir () {
	   
	   System.out.println("id Est:"+ estudiante_seleccionado.getCedula());
	   System.out.println(("id car: "+ carrera_seleccionada.getId()));

   }
   
	public void guardarInscripcion() {
		
		
		fecha = new Date();
		
		this.inscripcion.setFecha(fecha);
		this.inscripcion.setEstadoPago(false);
		conf=configuracionON.getConfiguracion(1);
		est=personaON.getEstudiante(estudiante_seleccionado.getCedula());

		inscripcion.setEstudiante(est);
		this.carrera=carrerasON.getCarrera(carrera_seleccionada.getId());
		
		this.inscripcion.setCarrera(carrera);
		
		for (int i = 0; i < carrera_seleccionada.getMallas().size(); i++) {
			MallaCurricular ma=carrera_seleccionada.getMallas().get(i);
			if(ma.getEstado()) {
				malla=ma;
			}
			
		}
		
		this.inscripcion.setMallaCurricular(malla);
		this.inscripcion.setCostoInscripcion(conf.getConf_costoInscripcion());
		try {
			

			inscripcionON.insert(this.inscripcion);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			
			e.printStackTrace();
		}
		
	}
	
	public void loadData() {
		if (cedula==null) 
			return;
			
		Persona p = personaON.getPersona(cedula);
		
		estudiante_seleccionado =p;
		Estudiante estu = personaON.getEstudiante(cedula);
		estudiante_seleccionado = estu;

		
	}
    
    //BUSCADOR DE DOCENTE
	
	
    
    
    //BUSCADOR DE MALLA
	
  
	
	

}
