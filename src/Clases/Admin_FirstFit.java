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
public class Admin_FirstFit {   
    private int[] memoria;
    private HashMap bloqueMemoria; 
    private ArrayList<Tupla> asignacion;
    private ArrayList<Proceso> procesos;
    
    public Admin_FirstFit(int tam) {
        memoria = new int[tam];
        for (int i=0; i<memoria.length; i++){
           memoria[i] = -1;
        }  
        bloqueMemoria = new HashMap();  
        asignacion = new ArrayList<Tupla>();
        procesos = new ArrayList<Proceso>();
        memoriaLibre(); 
    }
    
    public void agregarProceso(Proceso proceso){
        firstFit( proceso);
    }
    
    private void firstFit(Proceso proceso){	
        Iterator iterator = bloqueMemoria.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry entry = (Map.Entry) iterator.next();
            int posMem = Integer.parseInt(entry.getKey().toString());
            int tam =  Integer.parseInt(entry.getValue().toString());
            if (tam >=  proceso.getMemoria()){
                asignarMemoria(posMem, proceso);
                break;
            }   
        }
        
    }
    
    public boolean eliminarProceso(Proceso proceso){
        boolean borrado = false;
        for(Tupla asig: asignacion){
            if (asig.getVar1() == proceso.getId()){
                liberarMemoria(asig.getVar2(), asig.getVar3());
                asignacion.remove(asig);
                borrado = true;
                break;
            }
        }
        return borrado;
    }
    
    private void asignarMemoria(int pos, Proceso proceso){
        for (int i=pos; i<(pos + proceso.getMemoria() ); i++){
            memoria[i] = proceso.getId();
        }
        Proceso p = new Proceso(proceso.getId(), proceso.getMemoria(), proceso.getTiempo());
        procesos.add(p);
        Tupla asig = new Tupla(proceso.getId(), pos, proceso.getMemoria());
        asignacion.add(asig);
        bloqueMemoria.clear();
        memoriaLibre();
    }
    
    private void liberarMemoria(int pos, int tam){
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
    
     public void disminuirTiempo(){
        for(Proceso proceso : procesos){
            int tiempo = proceso.getTiempo() -1;
            proceso.setTiempo(tiempo);
            if (tiempo < 0){
                eliminarProceso(proceso);
            }
        }
            
    }

    public ArrayList<Proceso> getProcesos() {
        return procesos;
    }
    

    public int[] getMemoria() {
        return memoria;
    }

    public HashMap getBloqueMemoria() {
        return bloqueMemoria;
    }

    public ArrayList<Tupla> getAsignacion() {
        return asignacion;
    }
    
}

