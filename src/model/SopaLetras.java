/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.IOException;

/**
 *
 * @author omarcr
 */
public class SopaLetras {
    Character [][] sopa;
    private Integer errores;
    
    public SopaLetras(Character [][] sopa){
        this.sopa = sopa;
        this.errores = 0;
    }
    
    public String buscarPalabra(String word){
        char i1 = word.charAt(0);
        char i2 = word.charAt(1);
        String resultado = String.format("[%s] (0,0) ERROR", word);
        for (int i = 0; i < sopa.length; i++) {
            for (int j = 0; j < sopa[i].length; j++) {
                if(sopa[i][j]==i1){
                    if(sopa[i][j+1]==i2){
                        if(buscarDerecha(word,i,j)){
                            return String.format("[%s] (%d,%d) Horizontal_Derecha", word,j,i);
                        }
                    }
                    if(sopa[i][j-1]==i2){
                        if(buscarIzquierda(word,i,j)){
                            return String.format("[%s] (%d,%d) Horizontal_Izquierda", word,j,i);
                        }
                    }
                    if(sopa[i+1][j]==i2){
                        if(buscarAbajo(word,i,j)){
                            return String.format("[%s] (%d,%d) Vertical_Abajo", word,j,i);
                        }
                        
                    }
                    if(sopa[i-1][j]==i2){
                        if(buscarArriba(word,i,j)){
                            return String.format("[%s] (%d,%d) Vertical_Arriba", word,j,i);
                        }
                    }
                    
                    if(sopa[i-1][j+1]==i2){
                        if(buscarDerechaArriba(word,i,j)){
                            return String.format("[%s] (%d,%d) Diagonal_Derecha-ARRIBA", word,j,i);
                        }
                    }
                    
                    if(sopa[i+1][j+1]==i2){
                        if(buscarDerechaAbajo(word,i,j)){
                            return String.format("%s (%d,%d) Diagonal_Derecha-ABAJO", word,j,i);
                        }
                    }
                    if(sopa[i-1][j-1]==i2){
                        if(buscarIzquierdaArriba(word,i,j)){
                            return String.format("%s (%d,%d) Diagonal_Izquierda-ARRIBA", word,j,i);
                        }
                    }
                    if(sopa[i+1][j-1]==i2){
                        if(buscarIzquierdaAbajo(word,i,j)){
                            return String.format("%s (%d,%d) Diagonal_Izquierda-ABAJO", word,j,i);
                        }
                    }
                }
            }
        }
        return resultado;
    }
    
    
    private boolean buscarArriba(String p, int fila, int columna){
        int posicion=2;
        char letra = ' ';
        for (int i = fila-2; i >= 0; i--) {
            letra=p.charAt(posicion);
            if(!(letra==sopa[i][columna])){
                return false;
            }
            posicion++;
            if(posicion==p.length()){
                break;
            }
        }
        return true;
    }
    
     private boolean buscarIzquierdaAbajo (String palabra, int fila, int columna) {
        int posicion=2;
        char letra = ' ';
        int j = columna-2;
        for (int i = fila+2; i < sopa.length; i++,j--) {
            letra=palabra.charAt(posicion);
            if(!(letra==sopa[i][j])){
                return false;
            }
            posicion++;
            if(posicion==palabra.length()){
                break;
            }
        }
        return true;
    }
     private boolean buscarDerecha(String p, int fila, int columna){
        int ps=2;
        char letra = ' ';
        for (int i = columna+2; i < sopa[fila].length ; i++) {
            letra=p.charAt(ps);
            if (letra==sopa[fila][i]) {
                if(ps==p.length()-1){
                    break;
                }
                ps++;
            }
            else {
                return false;
            }
        }
        return true;
    }
    
    private boolean buscarIzquierda(String p, int fila, int columna){
        int posicion=2;
        char letra = ' ';
        for (int i = columna-2; i >= 0 ; i--) {
            letra=p.charAt(posicion);
            if (letra==sopa[fila][i]) {
                if(posicion==p.length()-1){
                    break;
                }
                posicion++;
            }
            else {
                return false;
            }
        }
        return true;
    }
    
    private boolean buscarAbajo(String p, int fila, int columna){
        int posicion=2;
        char letra = ' ';
        for (int i = fila+2; i < sopa.length; i++) {
            letra=p.charAt(posicion);
            if(!(letra==sopa[i][columna])){
                return false;
            }
            posicion++;
            if(posicion==p.length()){
                break;
            }
        }
        return true;
    }
    
    
    private boolean buscarDerechaAbajo(String p, int fila, int columna){
        int posicion=2;
        char letra = ' ';
        int j = columna+2;
        for (int i = fila+2; i < sopa.length; i++,j++) {
            letra=p.charAt(posicion);
            if(!(letra==sopa[i][j])){
                return false;
            }
            posicion++;
            if(posicion==p.length()){
                break;
            }
        }
        return true;
    }
    
    private boolean buscarDerechaArriba (String p, int fila, int columna) {
        int posicion=2;
        char letra = ' ';
        int j = columna+2;
        for (int i = fila-2; i >= 0; i--,j++) {
            letra=p.charAt(posicion);
            if(!(letra==sopa[i][j])){
                return false;
            }
            posicion++;
            if(posicion==p.length()){
                break;
            }
        }
        return true;
    }
    
    private boolean buscarIzquierdaArriba (String palabra, int fila, int columna) {
        int posicion=2;
        char letra = ' ';
        int j = columna-2;
        for (int i = fila-2; i >= 0; i--,j--) {
            letra=palabra.charAt(posicion);
            if(!(letra==sopa[i][j])){
                return false;
            }
            posicion++;
            if(posicion==palabra.length()){
                break;
            }
        }
        return true;
    }
    public String[] buscarPalabra(String[] p) {
        String[] r = new String[p.length];
        String error= null;
        for (int i = 0; i < p.length; i++) {
            error = String.format("[%s] (0,0) ERROR", p[i]);
            r[i] = this.buscarPalabra(p[i]);
            if (r[i].compareTo(error)==0) {
                this.errores++;
            }
        }
        return r;
    }
    
   

    public Integer getErrores() {
        return errores;
    }
    
}

class prueba {
    public static void main(String[] args) {
        Character [][] matriz= {
            {'0','0','0','0','0','0','0'},
            {'0','0','0','0','0','0','0'},
            {'0','0','0','0','0','0','0'},
            {'0','0','0','0','0','0','0'},
            {'0','0','0','0','0','0','0'},
            {'0','0','0','0','0','0','0'},
            {'0','0','0','0','0','0','0'}
    };
  
        try {
            GestorArchivos gestor = new GestorArchivos("input.txt");
            gestor.escribirArchivo();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}