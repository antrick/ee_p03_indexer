/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package indexador;

import java.awt.BorderLayout;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
 
/**
 *
 * @author Anita
 */
public class AbrirArchivoTexto extends JFrame implements ActionListener {
    JTextPane txp;
    ButtonGroup bgIndice;
    JRadioButton [] rbtnIndice;
    JFileChooser abrirArchivo;
    File f;
    JPanel pnlBuscar;
    JLabel lblBuscar;
    JTextField txtBuscar;
    ArbolAVL arb;
    
    public AbrirArchivoTexto(){
        //Para poder cerrar la ventana
        setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
 
        //Se agrega un layout
        getContentPane().setLayout( new BoxLayout(getContentPane(), BoxLayout.PAGE_AXIS) );
 
        //Se crea el editor de texto y se agrega a un scroll
        //txp = new JTextPane();
        
        pnlBuscar = new JPanel();
        pnlBuscar .setLayout( new FlowLayout() );
        lblBuscar = new JLabel();
        txtBuscar = new JTextField(20);
        JButton btnBuscar = new JButton("Buscar");
        btnBuscar.addActionListener( this );
        pnlBuscar.add(lblBuscar);
        pnlBuscar.add(txtBuscar);
        pnlBuscar.add(btnBuscar);
        
        //JScrollPane jsp = new JScrollPane();
        //jsp.setViewportView( txp );
 
        //add( jsp, BorderLayout.CENTER );
 
        //Se crea un boton para abrir el archivo
        JButton btn = new JButton( "Abrir" );
        btn.addActionListener( this );
        //btn.setSize(100, 20);
        //btn.setIcon( new ImageIcon( getClass().getResource( "Abrir.png" ) ) );
 
        add( btn );
 
        //Tama√±o de la ventana
        setSize( 500, 500 );
 
        //Esto sirve para centrar la ventana
        setLocationRelativeTo( null );
        
        //Hacemos visible la ventana
        setVisible( true );
    }
 
    //------------------------------Action Performed-------------------------------//
    public void actionPerformed( ActionEvent e ){
        String [] campos;
        JButton btn = (JButton)e.getSource();
        if( btn.getText().equals( "Abrir" ) )
        {
            if( abrirArchivo == null ) abrirArchivo = new JFileChooser();
            //Con esto solamente podamos abrir archivos
            abrirArchivo.setFileSelectionMode( JFileChooser.FILES_ONLY );
 
            int seleccion = abrirArchivo.showOpenDialog( this );
 
            if( seleccion == JFileChooser.APPROVE_OPTION )
            {
                f = abrirArchivo.getSelectedFile();
                try
                {
                    String nombre = f.getName();
                    String path = f.getAbsolutePath();
                    String contenido = getEncabezado( path ); //getArchivo( path );
                    campos = contenido.split("\\|");
                    contenido = "";
                    rbtnIndice = new JRadioButton[campos.length];
                    bgIndice = new ButtonGroup();
                    for(int i = getContentPane().getComponentCount() - 1; i > 0; i--)
                    	getContentPane().remove(i);
                    add(new JLabel("Datos"));
                    for(int i = 0; i < campos.length; i++){
                        contenido += campos[i] + "\n";
                        rbtnIndice[i] = new JRadioButton(campos[i]);
                        add(rbtnIndice[i]);
                        bgIndice.add(rbtnIndice[i]);
                    }
                    if(campos.length > 0)
                    	rbtnIndice[0].setSelected(true);
                    
                    JButton btnAceptar = new JButton( "Aceptar" );
                    btnAceptar.addActionListener( this );
                    add( btnAceptar );
                    paintAll(getGraphics());
                    //Colocamos en el titulo de la aplicacion el 
                    //nombre del archivo
                    this.setTitle( nombre );
 
                    //En el editor de texto colocamos su contenido
//                    txp.setText( contenido );
 
                }catch( Exception exp){}
            }
        }
        else if( btn.getText().equals( "Aceptar" ) ){
        	//JOptionPane.showMessageDialog(null, "");
        	getArchivo( f.getAbsolutePath() ); 
                
        }
        else if( btn.getText().equals( "Buscar" ) ){
        	NodoAVL resultado = arb.buscar(txtBuscar.getText(),arb.getRaiz());
        	String busq;
        	
        	if(resultado == null)
        		busq  = "No se encontro el dato";
                
        	else
        		busq = resultado.toString();
        	JOptionPane.showMessageDialog(null, busq); 
                
        }
    }
    //-----------------------------------------------------------------------------//
 
    //-------------------------Se obtiene el contenido del Archivo----------------//
    public String getArchivo( String ruta ){
        FileReader fr = null;
        BufferedReader br = null;
        String [] campos;
        //Cadena de texto donde se guardara el contenido del archivo
        String contenido = "";
        try
        {
            //ruta puede ser de tipo String o tipo File
            fr = new FileReader( ruta );
            br = new BufferedReader( fr );
 
            String linea;
            //Obtenemos el contenido del archivo linea por linea
            if(br.readLine() != null){
                arb = new ArbolAVL();
            	int seleccionado = 0;
            	for(int i = 0; i < bgIndice.getButtonCount(); i++){
            		if(rbtnIndice[i].isSelected())
            			seleccionado = i;
            		rbtnIndice[i].setEnabled(false);
            	}
            	while( ( linea = br.readLine() ) != null ){ 
            		contenido += linea + "\n";
                    campos = linea.split("\\|");
                    for(int i = 0; i < campos.length; i++){
                        arb.insertar(campos[seleccionado]);
                    }
            	}
            	lblBuscar.setText(rbtnIndice[seleccionado].getText());
            	add(pnlBuscar);
                paintAll(getGraphics());
                
                System.out.println("");
                arb.preOrden();
//                System.out.println("");
//                arb.imprime();
            }
 
        }catch( Exception e ){  }
        //finally se utiliza para que si todo ocurre correctamente o si ocurre 
        //algun error se cierre el archivo que anteriormente abrimos
        finally
        {
            try{
                br.close();
            }catch( Exception ex ){}
        }
        
        return contenido;
    }
    //-----------------------------------------------------------------------------//
    //-------------------------Se obtiene el encabezado del Archivo----------------//
    public String getEncabezado( String ruta ){
        
        FileReader fr = null;
        BufferedReader br = null;
        //Cadena de texto donde se guardara el contenido del archivo
        String contenido = "";
        try
        {
            //ruta puede ser de tipo String o tipo File
            fr = new FileReader( ruta );
            br = new BufferedReader( fr );
 
            String linea;
            //Obtenemos el contenido de la primera l√¨nea del archivo
            if( ( linea = br.readLine() ) != null ){ 
                contenido += linea + "\n";
            }
 
        }catch( Exception e ){  }
        //finally se utiliza para que si todo ocurre correctamente o si ocurre 
        //algun error se cierre el archivo que anteriormente abrimos
        finally
        {
            try{
                br.close();
             
            }catch( Exception ex ){}
        }
        
        return contenido;
    }
    public ArbolAVL getArbol(){
        return arb;
    }
}
