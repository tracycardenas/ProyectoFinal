package ec.edu.hogwarts.SistemaInstitucion.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.model.SelectItem;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import ec.edu.hogwarts.SistemaInstitucion.business.CalificacionesONLocal;
import ec.edu.hogwarts.SistemaInstitucion.business.LibroDiarioONLocal;
import ec.edu.hogwarts.SistemaInstitucion.business.MovimientoON;
import ec.edu.hogwarts.SistemaInstitucion.business.MovimientoONLocal;
import ec.edu.hogwarts.SistemaInstitucion.business.PersonaONLocal;
import ec.edu.hogwarts.SistemaInstitucion.model.Calificacion;
import ec.edu.hogwarts.SistemaInstitucion.model.Estudiante;
import ec.edu.hogwarts.SistemaInstitucion.model.Grupo;
import ec.edu.hogwarts.SistemaInstitucion.model.LibroDiario;
import ec.edu.hogwarts.SistemaInstitucion.model.Materia;
import ec.edu.hogwarts.SistemaInstitucion.model.Movimiento;
import ec.edu.hogwarts.SistemaInstitucion.model.Nivel;
import ec.edu.hogwarts.SistemaInstitucion.model.Persona;

@Named
@ViewScoped
public class IngresoLibroDiarioBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	private LibroDiarioONLocal libroDiarioON;
	
	@Inject
	private MovimientoONLocal movimientoON;
	
	List<Movimiento> movimientos = new ArrayList<Movimiento>();
	
	private Date fechaSeleccionada;
	
	private int idlibrodiario;
	
	@PostConstruct
	public void init() {
		this.cargarMovimientos();
	}
	
	public int getIdlibrodiario() {
		return idlibrodiario;
	}

	public void setIdlibrodiario(int idlibrodiario) {
		this.idlibrodiario = idlibrodiario;
	}

	public List<Movimiento> getMovimientos() {
		return movimientos;
	}

	public void setMovimientos(List<Movimiento> movimientos) {
		this.movimientos = movimientos;
	}

	public Date getFechaSeleccionada() {
		return fechaSeleccionada;
	}

	public void setFechaSeleccionada(Date fechaSeleccionada) {
		this.fechaSeleccionada = fechaSeleccionada;
	}

	private LibroDiario libroDiario = new LibroDiario();

	public LibroDiario getLibroDiario() {
		return libroDiario;
	}

	public void setLibroDiario(LibroDiario libroDiario) {
		this.libroDiario = libroDiario;
	}
	
	public String guardarLibroDiario() {
		try {
			libroDiarioON.insert(this.libroDiario);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public void cargarMovimientos() {
		System.out.println("ENTRE A CARGAR MOVIMIENTOS");
		System.out.println("mensaje de prueba");
		movimientos = movimientoON.getMovimiento();
		
		
	}
	
	public void actualizarMovimientos(AjaxBehaviorEvent e) {
		System.out.println("entre a actualizar movimientos");
		System.out.println("ID SELECCIONADO "+idlibrodiario);
		movimientos = new ArrayList<Movimiento>();
		if (idlibrodiario!=0) {
			
			
			for (int i = 0; i < movimientoON.getMovimiento().size(); i++) {
				if (idlibrodiario== movimientoON.getMovimiento().get(i).getLibroDiario().getId()) {
					movimientos.add(movimientoON.getMovimiento().get(i));
				}
			}

			

		}
		
		if (idlibrodiario==0) {
			movimientos = movimientoON.getMovimiento();
		}
	}
	
	public List<SelectItem> getSelecItemID() {
		List<SelectItem> selectItems = new ArrayList<SelectItem>();
		List<LibroDiario> listaLibrosDiarios = libroDiarioON.getLibroDiario();
		
		for (LibroDiario libroDiario : listaLibrosDiarios) {
			SelectItem item = new SelectItem(libroDiario.getId(), libroDiario.getFecha()+"");
			selectItems.add(item);
		}
		
		
		return selectItems;
	}
	
	

}
