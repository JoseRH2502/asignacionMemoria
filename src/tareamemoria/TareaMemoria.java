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
public class TareaMemoria {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        int[] bloqueMemoria = {100, 500, 200, 300, 600};
  
        Proceso p1 = new Proceso(0, 212, 0);
        Proceso p2 = new Proceso(1, 417, 0);
        Proceso p3 = new Proceso(2, 112, 0);
        Proceso p4 = new Proceso(3, 426, 0);
       
    
        Admin_FirstFit ff = new Admin_FirstFit( bloqueMemoria);
        ff.agregarProceso(p1);
        ff.agregarProceso(p2);
        ff.agregarProceso(p3);
        ff.agregarProceso(p4);
        
        HashMap a = ff.getAsignacion();
        System.out.println("Admin FF");
        System.out.println("ID \t\t Pos");
        System.out.println(p1.getId()+ " \t\t "+a.get(p1.getId()).toString());
        System.out.println(p2.getId()+ " \t\t "+a.get(p2.getId()).toString());
        System.out.println(p3.getId()+ " \t\t "+a.get(p3.getId()).toString());
        System.out.println(p4.getId()+ " \t\t "+a.get(p4.getId()).toString());
        System.out.println("\n");
        
        System.out.println("Memoria disponible:");
       // System.out.println(a.get(p2.getId()).toString());
        int m[]= ff.getBloqueMemoria();
        for (int i = 0; i < m.length; i++){
           System.out.println(m[i]);
        }
        System.out.println("\n");
        
        
        Admin_BestFit bf = new Admin_BestFit(bloqueMemoria);
        bf.agregarProceso(p1);
        bf.agregarProceso(p2);
        bf.agregarProceso(p3);
        bf.agregarProceso(p4);
        
        HashMap a1 = bf.getAsignacion();
        System.out.println("Admin BF");
        System.out.println("ID \t\t Pos");
        System.out.println(p1.getId()+ " \t\t "+a1.get(p1.getId()).toString());
        System.out.println(p2.getId()+ " \t\t "+a1.get(p2.getId()).toString());
        System.out.println(p3.getId()+ " \t\t "+a1.get(p3.getId()).toString());
        System.out.println(p4.getId()+ " \t\t "+a1.get(p4.getId()).toString());
        System.out.println("\n");
        
        System.out.println("Memoria disponible:");
        int m1[]= bf.getBloqueMemoria();
        for (int i = 0; i < m1.length; i++){
           System.out.println(m[i]);
        }
        System.out.println("\n");
         
        
        //bf.eliminarProceso(p4);
        //bf.agregarProceso(p4);
       
        
        Admin_WorstFit wf = new Admin_WorstFit(bloqueMemoria);
        wf.agregarProceso(p1);
        wf.agregarProceso(p2);
        wf.agregarProceso(p3);
        wf.agregarProceso(p4);
        
        
        HashMap a2 = wf.getAsignacion();
        System.out.println("Admin WF");
        System.out.println("ID \t\t Pos");
        System.out.println(p1.getId()+ " \t\t "+a2.get(p1.getId()).toString());
        System.out.println(p2.getId()+ " \t\t "+a2.get(p2.getId()).toString());
        System.out.println(p3.getId()+ " \t\t "+a2.get(p3.getId()).toString());
        System.out.println(p4.getId()+ " \t\t "+a2.get(p4.getId()).toString());
        System.out.println("\n");
        
        System.out.println("Memoria disponible:");
        int m2[]= wf.getBloqueMemoria();
        for (int i = 0; i < m2.length; i++){
           System.out.println(m2[i]);
        }
        System.out.println("\n");
        
       
    }
    
}
