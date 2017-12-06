package com.mti.daemon;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Utils {

	/**
	 * Método que serializa un objeto
	 * @param objeto
	 */
	public static void guardarObjeto(Object objeto) {
		
		FileOutputStream fout = null;
		try {
			fout = new FileOutputStream("save.ser");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ObjectOutputStream oos;
		try {
			oos = new ObjectOutputStream(fout);
			oos.writeObject(objeto);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static Object recuperarObjeto() {
		
		ObjectInputStream objectinputstream = null;
		Object objetoSalida = null;
		try {
		    FileInputStream streamIn = new FileInputStream("save.ser");
		    objectinputstream = new ObjectInputStream(streamIn);
		    
		    objetoSalida = objectinputstream.readObject();
		    
		    
		} catch (Exception e) {
		    e.printStackTrace();
		} finally {
		    if(objectinputstream != null){
		        try {
					objectinputstream .close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		    } 
		}
		
		return objetoSalida;
	}
	
	/*
	
	public static void main(String args[]) {
		Utils utils = new Utils();
		
		 DefaultTableModel model = new DefaultTableModel();
		 //Object[][] datos = { };

		 String[][] datos = new String [][] { 
			 { "X0", "Y0"},
             { "X1", "Y1"},
             { "X2", "Y2"},
             { "X3", "Y3"},
             { "X4", "Y4"} };
	       
            String[] nombreCols= {"X"," Y"}; 
	        model = new DefaultTableModel(datos, nombreCols);
	        
	        JTable jTableOriginal = new JTable(model);
	        jTableOriginal.setPreferredScrollableViewportSize(new Dimension(300, 100));
	        
	        utils.guardarObjeto(jTableOriginal);
	        
	        JTable tablaRecuperada = (JTable) utils.recuperarObjeto();
	        
	        int numeroRenglones = tablaRecuperada.getRowCount();
	        int numeroColumnas = tablaRecuperada.getColumnCount();
	        
	        for (int i = 0; i < numeroRenglones; i++) {
	        	for (int j = 0; j < numeroColumnas; j++) {
	        		System.out.println(tablaRecuperada.getModel().getValueAt(i, j));
	        	}
			}       
	}
	*/
}
