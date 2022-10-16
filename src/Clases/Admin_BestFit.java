/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author Jose
 */
public class Admin_BestFit {
    private int[] memoria;
    private HashMap bloqueMemoria; 
    private ArrayList<Tupla> asignacion;
    private ArrayList<Proceso> procesos;
    private  ArrayList<Proceso> rechazados;

    public Admin_BestFit(int tam) {
        memoria = new int[tam];
        for (int i=0; i<memoria.length; i++){
           memoria[i] = -1;
        }  
        bloqueMemoria = new HashMap();  
        asignacion = new ArrayList<Tupla>();
        procesos = new ArrayList<Proceso>();
        rechazados = new ArrayList<>();
        memoriaLibre();
    }
    
    public void agregarProceso(Proceso proceso){
        bestFit(proceso);
    }
    private void bestFit(Proceso proceso){	
        int bestIdx = -1;
        Iterator iterator = bloqueMemoria.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry entry = (Map.Entry) iterator.next();
            int posMem = Integer.parseInt(entry.getKey().toString());
            int tam =  Integer.parseInt(entry.getValue().toString());
            if (tam >= proceso.getMemoria()){
                if (bestIdx == -1){
                    bestIdx = posMem;
                }else if (Integer.parseInt(bloqueMemoria.get(bestIdx ).toString()) > Integer.parseInt(bloqueMemoria.get(posMem ).toString()) ){
                    bestIdx = posMem;
                }
            }
        }
        
        
        if (bestIdx != -1){   
            asignarMemoria(bestIdx, proceso);
        }else{
            rechazados.add(proceso);
        }
        
        
        
       
    }
    
    public boolean eliminarProceso(Proceso proceso){
        
        for(int i =0; i<asignacion.size(); i++){
            Tupla asig = asignacion.get(i);
            if (asig.getVar1() == proceso.getId()){
                liberarMemoria(asig.getVar2(), asig.getVar3());
                asignacion.remove(asig);
                procesos.remove(proceso);
                return true;
            }
        }
        return false;
    }
    
    
    private void asignarMemoria(int pos, Proceso proceso){
        for (int i=pos; i<(pos + proceso.getMemoria() ); i++){
            memoria[i] = proceso.getId();
        }
        //Proceso p = new Proceso(proceso.getId(), proceso.getMemoria(), proceso.getTiempo());
        procesos.add(proceso);
        Tupla asig = new Tupla(proceso.getId(), pos, proceso.getMemoria());
        asignacion.add(asig);
        bloqueMemoria.clear();
        memoriaLibre();
    }
    
    private void liberarMemoria(int pos, int tam ){
       
        for (int i=pos; i<(pos + tam ); i++){
            memoria[i] = -1;
        }
        bloqueMemoria.clear();
        memoriaLibre();
    }
    
    private void memoriaLibre(){
        boolean isFirst = true;
        int posMemoria = 0;
        int tam = 0;
        for(int i=0; i<memoria.length; i++){
            tam ++;
            if (memoria[i] == -1){
                if (isFirst){
                    posMemoria = i;
                    isFirst = false;
                    bloqueMemoria.put(posMemoria, tam);
                }
                bloqueMemoria.replace(posMemoria, tam);  
            }else{
                tam = 0;
                isFirst = true;
                posMemoria = 0;
            }
        
        }
    }
    
     public void pedirMemoria(Proceso proceso){
        if(procesos.contains(proceso)){
           for(int i = 0; i<asignacion.size(); i++){
                Tupla asig = asignacion.get(i);
                if(asig.getVar1() == proceso.getId()){
                   
                    if (buscarMasMemoriaDer(asig, proceso.getMemoriaNueva())){
                        asignarMasMemoriaDer(asig, proceso.getMemoriaNueva());
                        
                    }else if (buscarMasMemoriaIzq(asig, proceso.getMemoriaNueva())){
                        asignarMasMemoriaIzq(asig, proceso.getMemoriaNueva());
                        break;
                    }else{
                        eliminarProceso(proceso);
                        rechazados.add(proceso);
                    }
                    
                }
            }
        }
        
    }
    
    private boolean buscarMasMemoriaIzq(Tupla tupla, int memoria){
        if (tupla.getVar2() - memoria < 0){
            return false;
        }
        
        for(int i = tupla.getVar2()-memoria; i<tupla.getVar2(); i++){
            if(this.memoria[i] != -1){
                return false;
            }
        }
        return true; 
    }
    
     private boolean buscarMasMemoriaDer(Tupla tupla, int memoria){
        
        if (tupla.getVar2() + memoria + tupla.getVar3() > this.memoria.length){
            return false;
        }
         
        for(int i = tupla.getVar2() + tupla.getVar3(); i<tupla.getVar2() + tupla.getVar3() + memoria; i++){
            
            if(this.memoria[i] != -1){
                return false;
            }
        }
        return true; 
    }
     
     private void asignarMasMemoriaIzq( Tupla tupla, int memoria ){
        for(int i = tupla.getVar2()-memoria; i<tupla.getVar2(); i++){
            if(this.memoria[i] == -1){
               this.memoria[i] = tupla.getVar1();
            }
        }
        tupla.setVar2(tupla.getVar2()- memoria);
        tupla.setVar3(memoria + tupla.getVar3());
    }
    
     private void asignarMasMemoriaDer(Tupla tupla, int memoria){
        for(int i = tupla.getVar2() + tupla.getVar3(); i<tupla.getVar2() + tupla.getVar3() + memoria; i++){
            if(this.memoria[i] == -1){
                 this.memoria[i] = tupla.getVar1();
            }
        }
        tupla.setVar3(memoria + tupla.getVar3());
        
    }
    
    
     public void Descalenderizar(){
        for(int i = 0; i< procesos.size(); i++){
            Proceso proceso = procesos.get(i);
            if (proceso.getTiempo() == 0){
                eliminarProceso(proceso);
            }
        }
            
    }
        
    public ArrayList<Proceso> getProcesos() {
        return procesos;
    }
    
    

    public ArrayList<Tupla> getAsignacion() {
        return asignacion;
    }

    public int[] getMemoria() {
        return memoria;
    }

    public HashMap getBloqueMemoria() {
        return bloqueMemoria;
    }

    public ArrayList<Proceso> getRechazados() {
        return rechazados;
    }
    
    
      
}
