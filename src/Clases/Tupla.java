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
public class Tupla {
    private int var1;
    private int var2;
    private int var3;

    public Tupla(int var1, int var2) {
        this.var1 = var1;
        this.var2 = var2;
    }

    public Tupla(int var1, int var2, int var3) {
        this.var1 = var1;
        this.var2 = var2;
        this.var3 = var3;
    }

    public int getVar1() {
        return var1;
    }

    public void setVar1(int var1) {
        this.var1 = var1;
    }

    public int getVar2() {
        return var2;
    }

    public void setVar2(int var2) {
        this.var2 = var2;
    }

    public int getVar3() {
        return var3;
    }

    public void setVar3(int var3) {
        this.var3 = var3;
    }

    @Override
    public String toString() {
        return "Tupla{" + "var1=" + var1 + ", var2=" + var2 + ", var3=" + var3 + '}';
    }
    
    
}
