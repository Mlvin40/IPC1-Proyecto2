/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Frontend;

import Backend.archivos.ArchivosPrograma;
import Backend.Laberinto;
import Backend.laberintos.ConstructorDeLaberintos;
import Backend.listas.ListaException;
import Backend.listas.ListaGenerica;
import Frontend.editor.VentanaEditorLaberintos;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author Phoenix
 */
public class VentanaMenuSeleccionTablero extends javax.swing.JFrame {

    static JFrame ventanaAnterior;

    ArchivosPrograma archivoTableros = new ArchivosPrograma();
    ListaGenerica<Laberinto> listaLaberintos;
    ConstructorDeLaberintos constructorDeLaberintos = new ConstructorDeLaberintos();
    private int selected;

    public VentanaMenuSeleccionTablero(JFrame ventanaAnterior) {
        initComponents();
        setLocationRelativeTo(null);
        this.ventanaAnterior = ventanaAnterior;
        this.listaLaberintos = archivoTableros.getListaLaberintos();

        try {
            mostrarLaberintosExistentes();
        } catch (ListaException ex) {
            Logger.getLogger(VentanaEditorLaberintos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        txtLaberintosExistentes = new javax.swing.JTextArea();
        jLabel1 = new javax.swing.JLabel();
        btnJugar = new javax.swing.JButton();
        txtLaberintoSeleccionado = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        btnRegresar = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(0, 0));
        setPreferredSize(new java.awt.Dimension(644, 555));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtLaberintosExistentes.setColumns(20);
        txtLaberintosExistentes.setRows(5);
        jScrollPane1.setViewportView(txtLaberintosExistentes);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 100, 220, 203));

        jLabel1.setBackground(new java.awt.Color(255, 51, 0));
        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 102));
        jLabel1.setText("Seleccione un Laberinto");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 60, -1, -1));

        btnJugar.setBackground(new java.awt.Color(204, 255, 204));
        btnJugar.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnJugar.setForeground(new java.awt.Color(0, 0, 102));
        btnJugar.setText("Jugar");
        btnJugar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnJugarActionPerformed(evt);
            }
        });
        getContentPane().add(btnJugar, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 160, 86, -1));

        txtLaberintoSeleccionado.setBackground(new java.awt.Color(0, 204, 204));
        getContentPane().add(txtLaberintoSeleccionado, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 130, 86, -1));

        jButton1.setBackground(new java.awt.Color(255, 255, 204));
        jButton1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton1.setForeground(new java.awt.Color(0, 0, 102));
        jButton1.setText("Visualizar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 130, -1, -1));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 0));
        jLabel2.setText("Laberintos Existentes");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 70, -1, -1));

        btnRegresar.setBackground(new java.awt.Color(204, 255, 204));
        btnRegresar.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnRegresar.setForeground(new java.awt.Color(0, 0, 102));
        btnRegresar.setText("Regresar");
        btnRegresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegresarActionPerformed(evt);
            }
        });
        getContentPane().add(btnRegresar, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 470, -1, -1));

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgFondos/menuseleccion.jpg"))); // NOI18N
        jLabel3.setText("jLabel3");
        jLabel3.setToolTipText("");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 640, 530));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void mostrarLaberintosExistentes() throws ListaException {
        txtLaberintosExistentes.setText(constructorDeLaberintos.mostrarLaberintosExistentes(listaLaberintos));
    }
    private void btnJugarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnJugarActionPerformed

        //Si el usuario dejo el campo vacio se le notifica y finaliza  el evento del boton
        if (txtLaberintoSeleccionado.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "El campo no puede estar vacio");
            return;
        }
        int sizeLista;
        try {
            sizeLista = archivoTableros.getListaLaberintos().obtenerSize();
            selected = Integer.parseInt(txtLaberintoSeleccionado.getText());

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(rootPane, "Ingrese un numero de la lista");
            return;
        }
        if (selected >= 0 && selected < sizeLista) {
            //se oculta esta ventana
            this.setVisible(false);
            try {

                Laberinto elejido = listaLaberintos.obtenerValor(selected);
                VentanaJuego ventanaJuego = new VentanaJuego(elejido, this);
                ventanaJuego.setVisible(true);
            } catch (ListaException ex) {
                Logger.getLogger(VentanaMenuSeleccionTablero.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            JOptionPane.showMessageDialog(rootPane, "Seleccione un tablero existente");
        }
    }//GEN-LAST:event_btnJugarActionPerformed

    private void btnRegresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegresarActionPerformed
        this.dispose();
        ventanaAnterior.setVisible(true);
    }//GEN-LAST:event_btnRegresarActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        //Si el usuario dejo el campo vacio se le notifica y finaliza  el evento del boton
        if (txtLaberintoSeleccionado.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "El campo no puede estar vacio");
            return;
        }
        int sizeLista;
        try {
            sizeLista = archivoTableros.getListaLaberintos().obtenerSize();
            selected = Integer.parseInt(txtLaberintoSeleccionado.getText());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(rootPane, "Seleccione un numero de la lista");
            return;
        }

        if (selected >= 0 && selected < sizeLista) {
            //se oculta esta ventana

            try {
                VentanaVisualizarLaberinto ventanaVisualizarLaberinto = new VentanaVisualizarLaberinto(this, rootPaneCheckingEnabled, listaLaberintos.obtenerValor(selected));
                ventanaVisualizarLaberinto.setVisible(true);
            } catch (ListaException ex) {
                Logger.getLogger(VentanaMenuSeleccionTablero.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else {
            JOptionPane.showMessageDialog(rootPane, "Seleccione un tablero existente");
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(VentanaMenuSeleccionTablero.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VentanaMenuSeleccionTablero.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VentanaMenuSeleccionTablero.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VentanaMenuSeleccionTablero.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VentanaMenuSeleccionTablero(ventanaAnterior).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnJugar;
    private javax.swing.JButton btnRegresar;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField txtLaberintoSeleccionado;
    private javax.swing.JTextArea txtLaberintosExistentes;
    // End of variables declaration//GEN-END:variables
}
