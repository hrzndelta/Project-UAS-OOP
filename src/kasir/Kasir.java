/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kasir;

import kasir.view.login;

public class Kasir {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Theme.applyLookAndFeel();
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new login().setVisible(true);
            }
        });
    }
    
}
