/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tareamemoria;

import java.util.HashMap;

/**
 *
 * @author Jose
 */
public class Admin_BestFit {
    private int[] bloqueMemoria; 
    private HashMap asignacion;

    public Admin_BestFit(int[] bloqueMemoria) {
        this.bloqueMemoria = new int[bloqueMemoria.length];
        for (int i=0; i<bloqueMemoria.length; i++){
           this.bloqueMemoria[i] = bloqueMemoria[i];
        }
   
        asignacion = new HashMap();  
    }
    
    public void agregarProceso(Proceso proceso){
        bestFit(proceso);
    }
    private void bestFit(Proceso proceso){	
        int bestIdx = -1;
        
        for (int j=0; j<bloqueMemoria.length; j++)
        {
            if (bloqueMemoria[j] >= proceso.getMemoria())
            {
                if (bestIdx == -1){
                    bestIdx = j;
                }else if (bloqueMemoria[bestIdx] > bloqueMemoria[j]){
                    bestIdx = j;
                }
            }
        }
        
        
        if (bestIdx != -1)
        {   
            asignacion.put(proceso.getId(),bestIdx);
            bloqueMemoria[bestIdx] -= proceso.getMemoria();
        }
        
        if (!asignacion.containsKey(proceso.getId())){
            asignacion.put(proceso.getId(), -1);
        }
    }
    
    public boolean eliminarProceso(Proceso proceso){
        if (asignacion.containsKey(proceso.getId())){
          int index = Integer.parseInt(asignacion.get(proceso.getId()).toString()) ;
          bloqueMemoria[index] += proceso.getMemoria();
          asignacion.remove(proceso.getId());
          return true;
        }else{
            return false;
        }
    }
    
    public HashMap getAsignacion() {
        return asignacion;
    }
    
    public int[] getBloqueMemoria() {
        return bloqueMemoria;
    }   
}
