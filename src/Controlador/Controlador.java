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
import java.util.concurrent.TimeUnit;

public class Controlador extends Thread{
    Admin_BestFit bestFit;
    Admin_FirstFit firstFit;
    Admin_WorstFit worstFit;
    ArrayList<Proceso> procesos;
    int idProceso;
    int tamMemoria = 32;

    public Controlador() {
        bestFit = new Admin_BestFit(tamMemoria);
        firstFit = new Admin_FirstFit(tamMemoria);
        worstFit = new Admin_WorstFit(tamMemoria);
        procesos = new ArrayList<Proceso>();
        idProceso = 0;
        
    }
    
    public void agregarProceso(){
       int memoriaInicial = (int) Math.floor(Math.random()*5);
       //int tiempo = (int) Math.floor(Math.random()*(29-301+1)+301); 
        int tiempo = (int) Math.floor(Math.random()*(10-60+1)+60); 
       Proceso  proceso = new Proceso(idProceso, memoriaInicial, tiempo);
       procesos.add(proceso);
       idProceso ++;
       
       bestFit.agregarProceso(proceso);
       firstFit.agregarProceso(proceso);
       worstFit.agregarProceso(proceso);
    }
    
    public Admin_BestFit getBestFit() {
        return bestFit;
    }

    public void setBestFit(Admin_BestFit bestFit) {
        this.bestFit = bestFit;
    }

    public Admin_FirstFit getFirstFit() {
        return firstFit;
    }

    public void setFirstFit(Admin_FirstFit firstFit) {
        this.firstFit = firstFit;
    }

    public Admin_WorstFit getWorstFit() {
        return worstFit;
    }

    public void setWorstFit(Admin_WorstFit worstFit) {
        this.worstFit = worstFit;
    }

    public ArrayList<Proceso> getProcesos() {
        return procesos;
    }

    public void setProcesos(ArrayList<Proceso> procesos) {
        this.procesos = procesos;
    }

    public int getIdProceso() {
        return idProceso;
    }

    public void setIdProceso(int idProceso) {
        this.idProceso = idProceso;
    }

    public int getTamMemoria() {
        return tamMemoria;
    }

    public void setTamMemoria(int tamMemoria) {
        this.tamMemoria = tamMemoria;
    }
    
    @Override
    public void run(){
        try {
        int timAgregar = 0;
        while(true){
            TimeUnit.SECONDS.sleep(1);
            timAgregar ++;
            if (timAgregar == 5 ){
                agregarProceso();
                timAgregar = 0;
            }
            if (timAgregar == 3 ){
                imprimir();
            }
            
        }
        }catch(Exception e) {
            System.out.println(e);
        }
    }
    
    public void imprimir(){
        int[] memBF = bestFit.getMemoria();
        int[] memFF = firstFit.getMemoria();
        int[] memWF = worstFit.getMemoria();
        System.out.println("\n");
        System.out.println("\n");
        System.out.println("\n");
        System.out.println("MemoriaBF");
        for(int  i= 0; i< memBF.length; i++ ){
             System.out.println(memBF[i]);
        }
        System.out.println("MemoriaFF");
        for(int  i= 0; i< memFF.length; i++ ){
             System.out.println(memFF[i]);
        }
        System.out.println("MemoriaWF");
        for(int  i= 0; i< memWF.length; i++ ){
             System.out.println(memWF[i]);
        }
    }
    
    }
    

    
    
    
    
    
   
    
    
    
    

