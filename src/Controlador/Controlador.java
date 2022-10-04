/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

/**
 *
 * @author Jose
 */
import Clases.Admin_FirstFit;
import Clases.Proceso;
import Clases.Admin_WorstFit;
import Clases.Admin_BestFit;
import java.util.ArrayList;
import java.util.HashMap;

public class Controlador {
    Admin_BestFit bestFit;
    Admin_FirstFit firstFit;
    Admin_WorstFit worstFit;
    ArrayList<Proceso> procesos;
    int idProceso;
    int[] memoria = {100, 500, 200, 300, 600};

    public Controlador() {
        bestFit = new Admin_BestFit(memoria);
        firstFit = new Admin_FirstFit(memoria);
        worstFit = new Admin_WorstFit(memoria);
        procesos = new ArrayList<Proceso>();
        idProceso = 0;
        
    }
    
    public void agregarProceso(){
       int memoriaInicial = (int) Math.floor(Math.random()*100);
       int tiempo = (int) Math.floor(Math.random()*(29-301+1)+301); 
       Proceso  proceso = new Proceso(idProceso, memoriaInicial, tiempo);
       procesos.add(proceso);
       idProceso ++;
    }
    
    public void calenderizarProceso(Proceso proceso){
        bestFit.agregarProceso(proceso);
        firstFit.agregarProceso(proceso);
        worstFit.agregarProceso(proceso);
    }
    
    public void descalenderizarProceso(Proceso proceso){
        bestFit.eliminarProceso(proceso);
        firstFit.eliminarProceso(proceso);
        worstFit.eliminarProceso(proceso);
        procesos.remove(proceso);
    } 
    
    public int[] obtenerMemoriaBestFit(){
        return bestFit.getBloqueMemoria();
    }
    
    public int[] obtenerMemoriaFirstFit(){
        return firstFit.getBloqueMemoria();
    }
    
    public int[] obtenerMemoriaWorstFit(){
        return worstFit.getBloqueMemoria();
    }
    
    public HashMap obtenerAsignacionMemoriaBestFit(){
        return bestFit.getAsignacion();
    }
    
    public HashMap obtenerAsignacionMemoriaFirstFit(){
        return firstFit.getAsignacion();
    }
    
    public HashMap obtenerAsignacionMemoriaWorstFit(){
        return worstFit.getAsignacion();
    }

    public ArrayList<Proceso> getProcesos() {
        return procesos;
    }

    public int[] getMemoria() {
        return memoria;
    }
    
    
    
    
   
    
    
    
    
}
