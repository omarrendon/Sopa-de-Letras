/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.GestorArchivos;

/**
 *
 * @author omarcr
 */
public class Controlador {
    private GestorArchivos gestorArchivos;
    
    public void abrirArchivo(File archivo) throws IOException {
        this.gestorArchivos = new GestorArchivos(archivo.getPath());   
    }
    
    public void generarArchivoDeSalida() throws IOException {
        this.gestorArchivos.escribirArchivo();
    }

    public GestorArchivos getGestorArchivos() {
        return gestorArchivos;
    }
    
}
