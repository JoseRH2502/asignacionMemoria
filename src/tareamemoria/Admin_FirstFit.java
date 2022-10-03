/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tareamemoria;

import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author Jose
 */

class Admin_FirstFit
{   
    private int[] bloqueMemoria; 
    private HashMap asignacion;
    
    public Admin_FirstFit(int[] bloqueMemoria) {
        this.bloqueMemoria = new int[bloqueMemoria.length];
        for (int i=0; i<bloqueMemoria.length; i++){
           this.bloqueMemoria[i] = bloqueMemoria[i];
         }
        asignacion = new HashMap();   
    }
    
    public void agregarProceso(Proceso proceso){
        firstFit( proceso);
    }
    
    private void firstFit(Proceso proceso){	
        for (int i = 0; i < bloqueMemoria.length; i++){
            if (bloqueMemoria[i] >=  proceso.getMemoria()){
                asignacion.put(proceso.getId(), i);
                bloqueMemoria[i] -=  proceso.getMemoria();
                break;
            }   
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

    public HashMap getAsignacion() {
        return asignacion;
    }
    
    public int[] getBloqueMemoria() {
        return bloqueMemoria;
    }   
					
}
