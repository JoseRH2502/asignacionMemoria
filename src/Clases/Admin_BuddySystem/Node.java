/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases.Admin_BuddySystem;

import Clases.Proceso;

/**
 *
 * @author Jose
 */
public class Node {
    private int id;
    private Proceso proceso;
    private int padre;
    private int hijoDerecho;
    private int hijoIzquierdo;
    private int memoria;
    
    public Node(int id, int padre, int memoria){
        this.id = id;
        this. padre = padre;
        this.memoria = memoria;
        this.hijoDerecho = -1;
        this.hijoIzquierdo = -1;
        this.proceso = null;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Proceso getProceso() {
        return proceso;
    }

    public void setProceso(Proceso proceso) {
        this.proceso = proceso;
    }

    public int getPadre() {
        return padre;
    }

    public void setPadre(int padre) {
        this.padre = padre;
    }

    public int getHijoDerecho() {
        return hijoDerecho;
    }

    public void setHijoDerecho(int hijoDerecho) {
        this.hijoDerecho = hijoDerecho;
    }

    public int getHijoIzquierdo() {
        return hijoIzquierdo;
    }

    public void setHijoIzquierdo(int hijoIzquierdo) {
        this.hijoIzquierdo = hijoIzquierdo;
    }

    public int getMemoria() {
        return memoria;
    }

    public void setMemoria(int memoria) {
        this.memoria = memoria;
    }
    
    
}
