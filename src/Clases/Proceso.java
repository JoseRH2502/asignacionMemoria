/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

/**
 *
 * @author Jose
 */
public class Proceso {
    private int id;
    private int memoria; 
    private int tiempo; 

    public Proceso(int id, int memoria, int tiempo) {
        this.id = id;
        this.memoria = memoria;
        this.tiempo = tiempo;
    }

    public int getMemoria() {
        return memoria;
    }

    public void setMemoria(int memoria) {
        this.memoria = memoria;
    }

    public int getTiempo() {
        return tiempo;
    }

    public void setTiempo(int tiempo) {
        this.tiempo = tiempo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Proceso{" + "id=" + id + ", memoria=" + memoria + ", tiempo=" + tiempo + '}';
    }

   
    

   
    
    
}
