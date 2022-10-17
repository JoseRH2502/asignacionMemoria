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
import Clases.Admin_BuddySystem.Arbol;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

public class Controlador extends Thread{
    Admin_BestFit bestFit;
    Admin_FirstFit firstFit;
    Admin_WorstFit worstFit;
    Arbol buddySystem;
    ArrayList<Proceso> procesos;
    int idProceso;
    int tamMemoria = 64;

    public Controlador() {
        bestFit = new Admin_BestFit(tamMemoria);
        firstFit = new Admin_FirstFit(tamMemoria);
        worstFit = new Admin_WorstFit(tamMemoria);
        buddySystem = new Arbol(tamMemoria);
        procesos = new ArrayList<Proceso>();
        idProceso = 0;
        
    }
    
    public void agregarProceso(){
      
       int memoriaInicial = (int) Math.floor(Math.random()*(1-8+1)+8); 
       int tiempo = (int) Math.floor(Math.random()*(10-60+1)+60); 
      
        Proceso  proceso = new Proceso(idProceso, memoriaInicial, tiempo);
        procesos.add(proceso);
        bestFit.agregarProceso(proceso);
        firstFit.agregarProceso(proceso);
        worstFit.agregarProceso(proceso);
        buddySystem.agregarProceso(proceso);
       
        idProceso ++;
    }
    
    public void eliminarProceso(){
      for(Proceso proceso: procesos)  {
          proceso.setTiempo(proceso.getTiempo() - 1);
      }
      bestFit.Descalenderizar();
      worstFit.Descalenderizar();
      firstFit.Descalenderizar();
      buddySystem.Descalenderizar();
    }
    
    public void pedirMemoria(){
        int index = (int) Math.floor(Math.random()*(0 - (firstFit.getProcesos().size()-1))+ (firstFit.getProcesos().size()-1)); 
        int memoriaNueva = (int) Math.floor(Math.random()*(1-4+1)+4); 
        firstFit.getProcesos().get(index).pedirMemoria(memoriaNueva);
        firstFit.pedirMemoria(firstFit.getProcesos().get(index));
        bestFit.pedirMemoria(firstFit.getProcesos().get(index));
        worstFit.pedirMemoria(firstFit.getProcesos().get(index));
        buddySystem.pedirMemoria(firstFit.getProcesos().get(index));   
    }
    
  
    @Override
    public void run(){
        try {
        int pedirMem = 0;
        int agregarProceso = 0;
        while(idProceso <= 100){
            pedirMem ++;
            agregarProceso ++;
            TimeUnit.SECONDS.sleep(1);
           
            eliminarProceso();
            // QUITAR IMPRIMIR
            //imprimir();
            
            if (agregarProceso== 2){
                agregarProceso();
                agregarProceso = 0;
            }
            
            if (pedirMem == 30){
                pedirMemoria();
                pedirMem = 0;
            }
        }
        System.out.println("SIMULACIÓN TERMINADA");
       }catch(Exception e) {
            System.out.println(e);
        }
    }

    public Admin_BestFit getBestFit() {
        return bestFit;
    }

    public Admin_FirstFit getFirstFit() {
        return firstFit;
    }

    public Admin_WorstFit getWorstFit() {
        return worstFit;
    }

    public Arbol getBuddySystem() {
        return buddySystem;
    }
    
    
    
    
    
    public void imprimir(){
        int[] memBF = bestFit.getMemoria();
        int[] memFF = firstFit.getMemoria();
        int[] memWF = worstFit.getMemoria();
        int[] memBS = buddySystem.getMemoria();
        
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
        System.out.println("MemoriaBS");
        for(int  i= 0; i< memBS.length; i++ ){
             System.out.println(memBS[i]);
        }
    }
    
    }


    

    
    
    
    
    
   
    
    
    
    

