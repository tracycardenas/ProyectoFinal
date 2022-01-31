package ec.edu.hogwarts.SistemaInstitucion.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_planes_analitico")
public class PlanAnalitico implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "plan_id")
	private int id;
	@Column(name = "plan_intencion")
	private String intencion;
	@Column(name = "plan_obj_general")
	private String objetivoGeneral;
	@Column(name = "plan_temas")
	private String temas; //Esto debería ser un listado
	@Column(name = "plan_politica_evaluacion")
	private String politicaEvaluacion;
	@Column(name = "plan_bibliografia")
	private String bibliografia; //Esto debería ser un listado
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getIntencion() {
		return intencion;
	}
	public void setIntencion(String intencion) {
		this.intencion = intencion;
	}
	public String getObjetivoGeneral() {
		return objetivoGeneral;
	}
	public void setObjetivoGeneral(String objetivoGeneral) {
		this.objetivoGeneral = objetivoGeneral;
	}
	public String getTemas() {
		return temas;
	}
	public void setTemas(String temas) {
		this.temas = temas;
	}
	public String getPoliticaEvaluacion() {
		return politicaEvaluacion;
	}
	public void setPoliticaEvaluacion(String politicaEvaluacion) {
		this.politicaEvaluacion = politicaEvaluacion;
	}
	public String getBibliografia() {
		return bibliografia;
	}
	public void setBibliografia(String bibliografia) {
		this.bibliografia = bibliografia;
	}
	
}
