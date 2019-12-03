/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import civitas.Jugador;
import civitas.TituloPropiedad;
import java.util.ArrayList;
/**
 *
 * @author jesus
 */
public class JugadorPanel extends javax.swing.JPanel {

    /**
     * Creates new form JugadorPanel
     */
    public JugadorPanel() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabelNombre = new javax.swing.JLabel();
        jLabelSaldo = new javax.swing.JLabel();
        jLabelEncarcelado = new javax.swing.JLabel();
        jLabelEspeculador = new javax.swing.JLabel();
        jTextFieldNombre = new javax.swing.JTextField();
        jTextFieldSaldo = new javax.swing.JTextField();
        jTextFieldEncarcelado = new javax.swing.JTextField();
        propiedades = new javax.swing.JPanel();
        jTextFieldEspeculador = new javax.swing.JTextField();

        jLabelNombre.setFont(new java.awt.Font("Laksaman", 0, 15)); // NOI18N
        jLabelNombre.setText("Nombre");
        jLabelNombre.setEnabled(false);

        jLabelSaldo.setText("Saldo");
        jLabelSaldo.setEnabled(false);

        jLabelEncarcelado.setFont(new java.awt.Font("Laksaman", 0, 15)); // NOI18N
        jLabelEncarcelado.setText("Encarcelado");
        jLabelEncarcelado.setEnabled(false);

        jLabelEspeculador.setFont(new java.awt.Font("Laksaman", 0, 15)); // NOI18N
        jLabelEspeculador.setText("Especulador");
        jLabelEspeculador.setEnabled(false);

        jTextFieldNombre.setFont(new java.awt.Font("Laksaman", 0, 15)); // NOI18N
        jTextFieldNombre.setText("jTextField1");
        jTextFieldNombre.setEnabled(false);

        jTextFieldSaldo.setFont(new java.awt.Font("Laksaman", 0, 15)); // NOI18N
        jTextFieldSaldo.setText("jTextField2");
        jTextFieldSaldo.setEnabled(false);

        jTextFieldEncarcelado.setFont(new java.awt.Font("Laksaman", 0, 15)); // NOI18N
        jTextFieldEncarcelado.setText("jTextField3");
        jTextFieldEncarcelado.setEnabled(false);
        jTextFieldEncarcelado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldEncarceladoActionPerformed(evt);
            }
        });

        propiedades.setEnabled(false);

        jTextFieldEspeculador.setText("jTextField4");
        jTextFieldEspeculador.setEnabled(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelNombre)
                    .addComponent(jLabelSaldo)
                    .addComponent(jLabelEncarcelado)
                    .addComponent(jLabelEspeculador))
                .addGap(7, 7, 7)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextFieldNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldSaldo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldEncarcelado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldEspeculador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(32, 32, 32)
                .addComponent(propiedades, javax.swing.GroupLayout.PREFERRED_SIZE, 450, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(propiedades, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(37, 37, 37)
                                .addComponent(jLabelSaldo))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabelNombre)
                                    .addComponent(jTextFieldNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextFieldSaldo, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(7, 7, 7)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabelEncarcelado)
                            .addComponent(jTextFieldEncarcelado, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabelEspeculador)
                            .addComponent(jTextFieldEspeculador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(12, 12, 12))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jTextFieldEncarceladoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldEncarceladoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldEncarceladoActionPerformed


    //Métodos
    void setJugador(Jugador jugador)
    {
        this.jugador = jugador;
        
        //Editar campos
        String encarcelado,especulador;
        encarcelado= (jugador.isEncarcelado())? "SI" : "NO";
        especulador= (jugador.isEspeculador())? "SI" : "NO";
        
        this.jTextFieldNombre.setText(jugador.getNombre());
        this.jTextFieldSaldo.setText(Float.toString(jugador.getSSaldo()));
        this.jTextFieldEncarcelado.setText(encarcelado);
        this.jTextFieldEspeculador.setText(especulador);
        
        rellenaPropiedades(jugador.getPropiedades());
    }
    
    private void rellenaPropiedades (ArrayList<TituloPropiedad> lista) {
        
        // Se elimina la información antigua
        propiedades.removeAll();
        
        // Se recorre la lista de propiedades para ir
        //creando sus vistas individuales y añadirlas al panel
        for (TituloPropiedad t : lista) {
            PropiedadPanel vistaPropiedad = new PropiedadPanel();
            vistaPropiedad.setPropiedad(t);
            propiedades.add(vistaPropiedad);
            vistaPropiedad.setVisible(true);
        }
        // Se fuerza la actualización visual del panel propiedades y 
        //del panel del jugador
        repaint();
        revalidate();
    }
    
    
    //-----------------------------------------------------
    //Atributos
    private Jugador jugador;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabelEncarcelado;
    private javax.swing.JLabel jLabelEspeculador;
    private javax.swing.JLabel jLabelNombre;
    private javax.swing.JLabel jLabelSaldo;
    private javax.swing.JTextField jTextFieldEncarcelado;
    private javax.swing.JTextField jTextFieldEspeculador;
    private javax.swing.JTextField jTextFieldNombre;
    private javax.swing.JTextField jTextFieldSaldo;
    private javax.swing.JPanel propiedades;
    // End of variables declaration//GEN-END:variables
}
