package ec.edu.hogwarts.SistemaInstitucion.convertidor;

import javax.el.ValueExpression;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;
import javax.inject.Named;

import ec.edu.hogwarts.SistemaInstitucion.bean.BuscadorBean;
import ec.edu.hogwarts.SistemaInstitucion.model.Carrera;


@Named
@FacesConverter(value="cursoConverter", managed = true)
public class CursoConverter implements Converter{

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
    	

        if (context == null) {
            throw new NullPointerException("context");
        }
        if (component == null) {
            throw new NullPointerException("component");
        }
        FacesContext ctx = FacesContext.getCurrentInstance();
        ValueExpression vex = ctx.getApplication().getExpressionFactory().createValueExpression(ctx.getELContext(), "#{buscadorBean}", BuscadorBean.class);
        BuscadorBean bean = (BuscadorBean) vex.getValue(ctx.getELContext());
        Carrera curso_;
      
        try {
        	System.out.println("Value ....."+value);
            curso_ = bean.obtenerCarrera_codigo(Integer.parseInt(value));
        } catch (NumberFormatException e) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Valor Desconocido", "Error en Codigo");
            throw new ConverterException(message);
        }
        if (curso_ == null) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Valor Desconocido", "Error en Objeto");
            throw new ConverterException(message);
        }
        return curso_;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {

        if (context == null) {
            throw new NullPointerException("context");
        }
        if (component == null) {
            throw new NullPointerException("component");
        }
        
        return String.valueOf(((Carrera) value).getId());
    }

}
