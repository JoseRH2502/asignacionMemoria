/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tareamemoria;

import Clases.Admin_FirstFit;
import Clases.Proceso;
import Clases.Admin_WorstFit;
import Clases.Admin_BestFit;
import java.util.HashMap;
import Controlador.Controlador;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

/**
 *
 * @author Jose
 */
public class TareaMemoria {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {   
        Controlador c = new Controlador();
        c.run();
        /*
        Admin_BestFit BF = c.getBestFit();
        Admin_FirstFit FF = c.getFirstFit();
        Admin_WorstFit WF = c.getWorstFit();
        
        //HashMap a = BF.getBloqueMemoria();
        int[] memBF = BF.getMemoria();
        int[] memFF = FF.getMemoria();
        int[] memWF = WF.getMemoria();
        /*for(int  i= 0; i< BF.getMemoria().length; i++ ){
             System.out.println(mem[i]);
        }
        Proceso p = new Proceso(0, 3, 0);
        Proceso p2 = new Proceso(1, 5, 0);
        Proceso p3 = new Proceso(2, 2, 0);
        
        
       
        
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
        }*/
        

    }
}
    

