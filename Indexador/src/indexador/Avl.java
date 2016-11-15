/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package indexador;

import javax.swing.JFrame;
import javax.swing.UIManager;

/**
 *
 * @author Anita
 */
public class Avl {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        try
        {
            //Cambiamos el Look&Feel
            JFrame.setDefaultLookAndFeelDecorated( true );
                UIManager.setLookAndFeel( UIManager.getCrossPlatformLookAndFeelClassName() );
        }catch( Exception e ){}
        new AbrirArchivoTexto();
       

/*
        arb.visualizar();
        System.out.println();
        System.out.println(arb);
        */
    }
    
}

/*
        arb.alta("pedro");
        arb.alta("juan");
        arb.alta("miguel");
        arb.alta("andres");
        arb.alta("sergio");
        arb.alta("felipe");
        arb.alta("ernesto");
        arb.alta("roman");
        arb.alta("felix");

*/

