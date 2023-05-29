package control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view.VConsulta;
import view.VModificacion;
import view.VRegistro;
import db.*;
import model.Restaurante;

public class GuiaMichelinListener implements ActionListener{

	private VConsulta vC;
	private VRegistro vR;
	private VModificacion vM;
	private RestaurantesPersistencia rP;
	
	public GuiaMichelinListener(VConsulta vC, VRegistro vR, VModificacion vM) {
		this.vC = vC;
		this.vR = vR;
		this.vM = vM;
		this.rP = new RestaurantesPersistencia(vC);
	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals(VConsulta.CONSULTA)) {
			vC.cambiarVentana(vC, vC);
			vC.cambiarVentana(vC, vC);
			vC.cambiarVentana(vR, vC);
			vC.cambiarVentana(vM, vC);
		} else if(e.getActionCommand().equals(VConsulta.REGISTRO)) {
			vC.cambiarVentana(vC, vR);
			vC.cambiarVentana(vC, vR);
			vC.cambiarVentana(vR, vR);
			vC.cambiarVentana(vM, vR);
		} else if(e.getActionCommand().equals(VConsulta.MODIFICACION)) {
			vC.cambiarVentana(vC, vM);
			vC.cambiarVentana(vC, vM);
			vC.cambiarVentana(vR, vM);
			vC.cambiarVentana(vM, vM);
		} else if(e.getActionCommand().equals(VConsulta.SALIR)) {
			vC.salir(vC);
			vC.salir(vC);
			vC.salir(vR);
			vC.salir(vM);
		} 
		if(e.getActionCommand().equals(VConsulta.CONSULTAR)) {
			vC.cargarTabla(rP.rellenarModelo());
			vC.limpiarCampos();
		}
		if(e.getActionCommand().equals(VConsulta.ELIMINAR)) {
			rP.eliminarSeleccion();
			vC.limpiarCampos();
		}
		if(e.getActionCommand().equals(VRegistro.GUARDAR)) {
			Restaurante rt = vR.guardarRestaurante();
			if(rt != null) {
				rP.registrarRestaurante(rt);
			}
		}
		if(e.getActionCommand().equals(VRegistro.LIMPIAR)) {
			vR.limpiarDatos();
		}
		if(e.getActionCommand().equals(VModificacion.BUSCAR)) {
			if(vM.buscarRestaurante()) {
				vM.habilitarCampos(true);
			}
		}
		if(e.getActionCommand().equals(VModificacion.GUARDAR)) {
			Restaurante rt = vM.modificarRestaurante();
			if(rt != null) {
				rP.modificarRestaurante(rt);
			}
		}
		if(e.getActionCommand().equals(VModificacion.CANCELAR)) {
			vM.cancelar();
		}
	}
}
