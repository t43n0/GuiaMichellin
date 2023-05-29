package main;

import java.awt.EventQueue;

import control.GuiaMichelinListener;
import view.VConsulta;
import view.VModificacion;
import view.VRegistro;

public class Main {
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				VConsulta vC = new VConsulta();
				VRegistro vR = new VRegistro();
				VModificacion vM = new VModificacion();
				
				GuiaMichelinListener l = new GuiaMichelinListener(vC, vR, vM);
				
				vM.setListener(l);
				vR.setListener(l);
				vC.setListener(l);
				vC.hacerVisible(true);
			}
		});
	}
}
