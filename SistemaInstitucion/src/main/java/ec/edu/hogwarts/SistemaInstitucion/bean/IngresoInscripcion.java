package ec.edu.hogwarts.SistemaInstitucion.bean;

import java.util.Date;

import javax.enterprise.context.RequestScoped;
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

@Named
@RequestScoped
public class IngresoInscripcion {
	
	@Inject
	private InscripcionONLocal inscripcionON;
	@Inject
	private ConfiguracionONLocal configuracionON;
	@Inject
	private PersonaONLocal personaON;
	@Inject
	private CarreraONLocal carreraOn;
	
	
	
	private Estudiante est;
	private Carrera carrera;
	private MallaCurricular malla;
	private Date fecha;
	private Configuracion conf;
	private String cedula;
	private int carrera_id;
	private int malla_id;
	
	
	private Inscripcion inscripcion = new Inscripcion();

	public Inscripcion getInscripcion() {
		return inscripcion;
	}

	public void setInscripcion(Inscripcion inscripcion) {
		this.inscripcion = inscripcion;
	}
	
	public String guardarInscripcion(int id, String ced) {
		
		fecha = new Date();
		
		System.out.println("fehca: "+fecha);
		System.out.println(id+ "    "+ced);
		
		this.inscripcion.setFecha(fecha);
		this.inscripcion.setEstadoPago(false);
		conf=configuracionON.getConfiguracion(1);
		est=personaON.getEstudiante(ced);
		System.out.println("cedual: "+ est.getCedula());
		inscripcion.setEstudiante(est);
		carrera=carreraOn.getCarrera(id);
		
		System.out.println("Carrera_ID : "+carrera.getId());
		inscripcion.setCarrera(carrera);
		malla=(MallaCurricular) carreraOn.getCarrera(carrera_id).getMallas();
		
		System.out.println("Malla: "+ malla.getId());
		this.inscripcion.setMallaCurricular(malla);
		this.inscripcion.setCostoInscripcion(conf.getConf_costoInscripcion());
		try {
			

			inscripcionON.insert(this.inscripcion);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			
			System.out.println("INGRESE AQUI");
			e.printStackTrace();
		}
		return "CostInscripcion?faces-redirect=true";
	}

	public Estudiante getEst() {
		return est;
	}

	public void setEst(Estudiante est) {
		this.est = est;
	}

	public Carrera getCarrera() {
		return carrera;
	}

	public void setCarrera(Carrera carrera) {
		this.carrera = carrera;
	}

	public MallaCurricular getMalla() {
		return malla;
	}

	public void setMalla(MallaCurricular malla) {
		this.malla = malla;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Configuracion getConf() {
		return conf;
	}

	public void setConf(Configuracion conf) {
		this.conf = conf;
	}

	public String getCedula() {
		return cedula;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
	}

	public int getCarrera_id() {
		return carrera_id;
	}

	public void setCarrera_id(int carrera_id) {
		this.carrera_id = carrera_id;
	}

	public int getMalla_id() {
		return malla_id;
	}

	public void setMalla_id(int malla_id) {
		this.malla_id = malla_id;
	}
	
	
	
	
	

}
