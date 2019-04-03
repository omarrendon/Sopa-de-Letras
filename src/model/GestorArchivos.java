/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author omarcr
 */
public class GestorArchivos {
    
    private SopaLetras sopa;
     private File outArchivo;
    private File inputArchivo;   
    private FileReader lector;
    private FileWriter escritor;
    private Integer pr;
    private String[] word;
    
    
    public GestorArchivos(String ruta) throws FileNotFoundException, IOException {
        inputArchivo = new File(ruta);
        outArchivo = new File("output.txt");
        lector = new FileReader(inputArchivo);
        escritor = new FileWriter(outArchivo);
        sopa = this.enmarcar();
        pr = 0;
    }
    
    private char[][] leerArchivo() throws IOException {
        int c = 0;
        int aux=0;
        int numFilas = 0, numColumnas = 0, numPalabras = 0;
        String str = "";
        this.word = null;
        int pos = 0;
        boolean flag = true;
        char [][] matriz = null;
        while (lector.ready()) {
            c = lector.read();
            if (c == 32 || c == 10 || c == 13){
                if (aux==0) {
                    numColumnas = Integer.valueOf(str);
                }
                else if (aux==1) {
                    numFilas = Integer.valueOf(str);
                }
                else if (aux==2) {
                    numPalabras = Integer.valueOf(str);
                }
                else {
                    if(flag) {
                        if(this.word==null) {
                        this.word = new String [numPalabras];
                        }
                        if (!str.equals("")){
                            this.word[pos]=str;
                            pos++;
                        }
                        if(pos==numPalabras){
                            flag=false;
                        }
                    } else {
                        if(matriz == null) {
                            matriz = new char[numFilas][numColumnas];
                        }
                        matriz[0][0] = (char)lector.read();
                        for (int k = 1; k < matriz[0].length; k++) {
                            matriz[0][k] = (char)lector.read();
//                            System.out.println((char)lector.read());
//                            System.out.println(matriz[0][k]);
                        }
                        lector.skip(2);
                        for (int k = 1; k < matriz.length; k++) {
                            for (int l = 0; l < matriz[k].length; l++) {
                                matriz[k][l] = (char)lector.read();
                            }
                            lector.skip(2);
                        }
                    }
                }
                str="";
                aux++;
            } else {
                str = str+(char)c;
            }
        }
        lector.close();
        return matriz;
    }
    
    private SopaLetras enmarcar() throws IOException {
        char [][] matriz = leerArchivo();
        Character [][] matrizResultado = new Character [(matriz.length+2)][(matriz[0].length+2)];
        for (int i = 0; i < matrizResultado.length; i++) {
            for (int j = 0; j < matrizResultado[i].length; j++) {
                if (i == 0 || i == (matrizResultado.length-1) || j == 0 || j == (matrizResultado[i].length-1)) {
                    matrizResultado[i][j] = '0';
                } else {
                    matrizResultado[i][j] = matriz[i-1][j-1];
                }
            }
        }
        SopaLetras sopa = new SopaLetras(matrizResultado);
        return sopa;
    }
    
    public void escribirArchivo() throws IOException {
        String[] resultado = this.sopa.buscarPalabra(word);
        this.escritor.write(this.sopa.getErrores().toString());
        this.escritor.write("\r\n");
        for (int i = 0; i < resultado.length; i++) {
            this.escritor.write(resultado[i]);
            this.escritor.write("\r\n");
            this.pr++;
        }
        escritor.close();
    }

    public SopaLetras getSopa() {
        return sopa;
    }

    public File getiArchivo() {
        return inputArchivo;
    }

    public File getoArchivo() {
        return outArchivo;
    }

    public FileReader getLector() {
        return lector;
    }

    public FileWriter getEscritor() {
        return escritor;
    }

    public String[] getPalabras() {
        return word;
    }

    public synchronized Integer getProgress() {
        return pr;
    }

    
    
}
