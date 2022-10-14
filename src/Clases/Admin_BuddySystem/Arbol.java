/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases.Admin_BuddySystem;

import Clases.Proceso;
import java.util.ArrayList;

/**
 *
 * @author Jose
 */
public class Arbol {
    private ArrayList<Node> arbol;
    private int[] memoria;
    private int idNode; 
    private Node nodoActual;

    public Arbol(int tam) {
        idNode = 0;
        arbol = new ArrayList<>();
        memoria= new int[tam];
        Node root = new Node(0,-2,tam);
        this.arbol.add(root);  
        nodoActual = root;
        for (int i=0; i < tam ; i++){
            memoria[i] = nodoActual.getId();
        }
        
    }
     public void agregarProceso(Proceso proceso){
         if(arbol.size() == 1){
             subArbolIzquierdo(proceso);
             nodoActual.setProceso(proceso);
         }else{
            hojaLibre(proceso);
            subArbolIzquierdo(proceso);
            nodoActual.setProceso(proceso);
         }
         
     }
    
    
    public void subArbolIzquierdo(Proceso proceso){
        //System.out.println(nodoActual.getMemoria()/2 >= proceso.getMemoria());
        while (nodoActual.getMemoria()/2 >= proceso.getMemoria()){
            idNode ++;
            Node izquierdo = new Node(idNode,nodoActual.getId(), nodoActual.getMemoria()/2);
            idNode ++;
            Node derecho = new Node(idNode,nodoActual.getId(), nodoActual.getMemoria()/2);
            arbol.add(izquierdo);
            arbol.add(derecho);
            nodoActual.setHijoIzquierdo(izquierdo.getId());
            nodoActual.setHijoDerecho(derecho.getId());
            dividirMemoria();
            nodoActual = izquierdo;
        }

    }
    
    private void dividirMemoria(){
        for (int i=0; i< memoria.length; i++){
            if(nodoActual.getId() == memoria[i]){
                dividirMemoriaAux(i,nodoActual.getMemoria()/2, nodoActual.getHijoIzquierdo());
                //System.out.println( nodoActual.getHijoDerecho() );
                dividirMemoriaAux(i + nodoActual.getMemoria()/2 ,nodoActual.getMemoria()/2, nodoActual.getHijoDerecho());
                break;
            }
           
        }
    }
    
    private void dividirMemoriaAux(int pos, int tam, int valor){
        //System.out.println(pos + " " + tam + " "+ valor);
        for (int i=pos; i < pos + tam ; i++){
            memoria[i] = valor;
        }
       
        
    }
    
    private void hojaLibre(Proceso proceso){
        for (int i=0; i< memoria.length; i++){
            Node nodo = recuperarNodo(memoria[i]);
            
            if(nodo.getProceso() == null){
               if(nodo.getMemoria() >= proceso.getMemoria()){
                   //System.out.println("Nodo elegido:" +nodo.getId());
                   nodoActual = nodo;
                   break;
               }
            }
        }
        
    }
    
    private Node recuperarNodo(int idNode){
        for(Node nodo: arbol){
            if (nodo.getId() == idNode){
                return nodo;
            }
        }
        return null;
    }
    
    public void eliminarProceso(Proceso proceso){
        nodoActual = buscarNodo(proceso);
        nodoActual.setProceso(null);
        Boolean nodoBorrable = true;
        
        while(nodoBorrable){
            if (nodoActual.getPadre() == -2 ){
                break;
            }
            Node padre = recuperarNodo(nodoActual.getPadre());
            Node der = recuperarNodo(padre.getHijoDerecho());
            Node izq = recuperarNodo(padre.getHijoIzquierdo());
            if((der.getProceso() == null  && izq.getProceso() == null) && (esHoja(der.getId())) && esHoja(izq.getId()) ){
                liberarMemoria(izq.getId(), der.getId(), padre.getId());
                padre.setHijoDerecho(-1);
                padre.setHijoIzquierdo(-1);
                arbol.remove(izq);
                arbol.remove(der);
                nodoActual = padre;
            }else{
                nodoBorrable  = false;
            }
        }
    }
    
    private boolean esHoja(int IDnodo){
       for (int i=0; i < memoria.length ; i++){
           if(IDnodo == memoria[i]){
               return true;
           }
       }
       return false; 
    }
    
    private void liberarMemoria(int IDizq, int IDder, int IDPadre){
        //System.out.println("IZQ " + IDizq + " DER " + IDder + " Padre "+IDPadre);
        for (int i=0; i < memoria.length ; i++){
            if (memoria[i] == IDizq || memoria[i] == IDder){
                memoria[i] = IDPadre;
            }
             
        }
    }
    
    
    
    public Node buscarNodo(Proceso proceso){
    for (int i=0; i< memoria.length; i++){
            Node nodo = recuperarNodo(memoria[i]);
            if(nodo.getProceso() == proceso){
                return nodo;
            }
        }
        return null;
    }
    
    private int[] memoriaTraducida(){
        int[] memoriaTraducida = new int[memoria.length];
        for (int i=0; i< memoria.length; i++){
            Node nodoTemp = recuperarNodo(memoria[i]);
            if (nodoTemp.getProceso() == null)
            memoriaTraducida[i] = -1;
            else{
                memoriaTraducida[i] = nodoTemp.getProceso().getId();
            }
        }
        
        return memoriaTraducida;
        
    }
    
    public ArrayList<Node> getArbol() {
        return arbol;
    }
    
    public int[] getMemoria() {
        return memoriaTraducida();
    }
  
}
