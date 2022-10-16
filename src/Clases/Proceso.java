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
    private int memoriaInicial; 
    private int memoriaSolicitada; 
    private int tiempo; 

    public Proceso(int id, int memoria, int tiempo) {
        this.id = id;
        this.memoriaInicial = memoria;
        this.memoriaSolicitada =  0;
        this.tiempo = tiempo;
    }

    public int getMemoria() {
        return memoriaInicial;
    }
    
    public int getMemoriaNueva() {
        return memoriaSolicitada;
    }

    public void setMemoria(int memoria) {
        this.memoriaInicial = memoria;
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
    
    public void pedirMemoria(int num){
        memoriaSolicitada += num;
    }

    @Override
    public String toString() {
        return "Proceso{" + "id=" + id + ", memoria=" + memoriaInicial + ", tiempo=" + tiempo + '}';
    } 
    
}
