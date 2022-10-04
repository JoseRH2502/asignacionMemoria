/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import java.util.HashMap;

/**
 *
 * @author Jose
 */
public class Admin_WorstFit {
    private int[] bloqueMemoria; 
    private HashMap asignacion;

    public Admin_WorstFit(int[] bloqueMemoria) {
        this.bloqueMemoria = new int[bloqueMemoria.length];
        for (int i=0; i<bloqueMemoria.length; i++){
           this.bloqueMemoria[i] = bloqueMemoria[i];
         }
        asignacion = new HashMap();  
    }
    
    public void agregarProceso(Proceso proceso){
        worstFit( proceso);
    }
    
    private void worstFit(Proceso proceso){
        int wstIdx = -1;
        for (int j=0; j<bloqueMemoria.length; j++){
            if (bloqueMemoria[j] >= proceso.getMemoria()){
                    if (wstIdx == -1){
                        wstIdx = j;
                    }else if (bloqueMemoria[wstIdx] <bloqueMemoria[j]){
                        wstIdx = j;
                    }
                }
            }
       
            if (wstIdx != -1){
                asignacion.put(proceso.getId(), wstIdx);
                bloqueMemoria[wstIdx] -= proceso.getMemoria();
            }
            
            if (!asignacion.containsKey(proceso.getId())){
            asignacion.put(proceso.getId(), -1);
        }
    }
    
    public boolean eliminarProceso(Proceso proceso){
        if (Integer.parseInt(asignacion.get(proceso.getId()).toString()) != -1 ){
          int index = Integer.parseInt(asignacion.get(proceso.getId()).toString()) ;
          bloqueMemoria[index] += proceso.getMemoria();
          asignacion.remove(proceso.getId());
          return true;
        }else{
            return false;
        }
    }
    
    
    public int[] getBloqueMemoria() {
        return bloqueMemoria;
    }

    public HashMap getAsignacion() {
        return asignacion;
    }
    
    
}
