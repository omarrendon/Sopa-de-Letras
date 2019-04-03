/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import controller.Controlador;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author omarcr
 */
public class MainFrame extends JFrame {
     public JPanel panel = new JPanel();
    private Controlador controlador;
    
    public MainFrame() {
        ventana();
    }
    public void ventana() {
        super.setTitle("Sopa de letras");
        this.controlador = new Controlador();
        super.setSize(400, 400);
        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        super.setLayout(new BorderLayout());
        super.setLocationRelativeTo(null);
        super.add(this.crearPanelBotones(),BorderLayout.CENTER);
        super.setVisible(true);
    }

    private JPanel crearPanelBotones() {
        JPanel panel = new JPanel();
        JButton btnChoose = new JButton("Seleccionar Archivo");
        JButton btnSolve = new JButton("Resolver");
        JButton btnAyuda = new JButton("Ayuda!");
        
        btnAyuda.addActionListener( new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(MainFrame.this, "SE GENERARÁ UN ARCHIVO output.txt con el ejercicio resuelto dentro de la carpeta del archivo");
            }
        });
        
        JFileChooser fileChooser = new JFileChooser();
        panel.setLayout(new FlowLayout());
        btnSolve.setEnabled(false);
        btnChoose.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                int returnVal = fileChooser.showOpenDialog(panel);

            if (returnVal == JFileChooser.APPROVE_OPTION) {
                    try {
                        controlador.abrirArchivo(fileChooser.getSelectedFile());
                        btnSolve.setEnabled(true);
                    } catch (IOException ex) {
                        JOptionPane.showMessageDialog(MainFrame.this,"Ha habido un error");
                    }
            }
            }
        });
        btnSolve.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                try {
                    controlador.generarArchivoDeSalida();
                    JOptionPane.showMessageDialog(MainFrame.this,String.format("Archivo generado con exito  [%s]",controlador.getGestorArchivos().getoArchivo().getPath()));
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(MainFrame.this,"Ha habido un error");
                }
            }
        });
        
        
        
        panel.add(btnChoose);
        panel.add(btnSolve);
        panel.add(btnAyuda);
        panel.setBackground(Color.decode("#CEF6F5"));
        
        return panel;
    }
}
