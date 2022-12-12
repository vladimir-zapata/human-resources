package Controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import Vistas.vistas;
        



/**
 *
 * @author QualityControl
 */
public class Controlador {
    protected float sueldo=0;
    protected double seguromedico = 0;
    protected double pension = 0;
    protected double sueldoneto = 0;
    protected double horasextra = 0;
    protected int horastrabajadas = 0;
    protected double calculoars = 0;
    protected double calculoafp = 0;
    protected double sueldobruto = 0;
    
    
   // Nomina c = new Nomina();
   // Vista vista = new Vista();
    
    
    
        //public Controlador(Vista v) {
       // this.vista = v;
       // this.vista.btncalculos.addActionListener(this);


    

        //Limpiar formulario y Listar contactos
        //limpiarCampos(v);

    
    public void Calcular_sueldobruto (){
  
       sueldobruto=sueldo;
    }
    
    public void Calcularafp(){
        calculoafp = ((2.87 * sueldo)/100);
        pension = sueldo - calculoafp;

}
    
    public void Calcularseguro(){
        calculoars = ((3.04 * sueldo)/100);
        seguromedico = sueldo - calculoars;
    }
    
    public void Calcularhorasextra(){
        horasextra = horastrabajadas * 50;
       
    }
    
    public void Calcularsueldoneto(){
        sueldoneto = (pension+seguromedico+horasextra)-sueldo;
       
    }
            
}
