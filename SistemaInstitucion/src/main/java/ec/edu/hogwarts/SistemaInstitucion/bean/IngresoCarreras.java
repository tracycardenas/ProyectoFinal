package ec.edu.hogwarts.SistemaInstitucion.bean;

import java.util.Iterator;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import ec.edu.hogwarts.SistemaInstitucion.business.CarreraONLocal;
import ec.edu.hogwarts.SistemaInstitucion.model.Carrera;
import ec.edu.hogwarts.SistemaInstitucion.model.Docente;
import ec.edu.hogwarts.SistemaInstitucion.model.Estudiante;
import ec.edu.hogwarts.SistemaInstitucion.model.MallaCurricular;
import ec.edu.hogwarts.SistemaInstitucion.model.Persona;

@Named
@RequestScoped
public class IngresoCarreras {
	
	@Inject
	private CarreraONLocal carreraON;
	private List<Carrera>carreras;
	private int id=0;
	private String mallaD;
	private Carrera carrera = new Carrera();
	

	@PostConstruct
	public void init() {
		
		this.loadCarreras();
	}

	public Carrera getCarrera() {
		return carrera;
	}

	public void setCarrera(Carrera carrera) {
		this.carrera = carrera;
	}
	
	
	public List<Carrera> getCarreras() {
		return carreras;
	}

	public void setCarreras(List<Carrera> carreras) {
		this.carreras = carreras;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	

	public String getMallaD() {
		return mallaD;
	}

	public void setMallaD(String mallaD) {
		this.mallaD = mallaD;
	}

	public String guardarCarreras() {
		
		try {
			carreraON.insert(this.carrera);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "InsertarCarreras?faces-redirect=true";
	}
	
	public void loadCarreras() {
		
		this.carreras=carreraON.getCarrera();
	}
	
	public String eliminar (int id) {
		
		try {
			
			carreraON.delete(id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "InsertarCarreras?faces-redirect=true";
	}
	
	public String editar(int id) {
		
		
		return "InsertarCarreras?faces-redirect=true&id="+id;
	}
	
	public void loadData() {
		if (id==0) 
			return;
			

		carrera = carreraON.getCarrera(id);
		for (int i = 0; i < carrera.getMallas().size(); i++) {
			
			if (carrera.getMallas().get(i).getEstado())
				mallaD=carrera.getMallas().get(i).getDescripcion();
			
		}
		
	}
	
	public String update () {
	
		try {
			
			this.carrera.setId(id);
			carreraON.update(this.carrera);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "InsertarCarreras?faces-redirect=true";
	}
	
	public String malla() {

	String desc="";


		
		return desc;
	}
	

}
