/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package indexador;

/**
 *
 * @author andy
 */


public class ArbolAVL<T extends Comparable<T>>{
	
    NodoAVL<T> raiz;
    
   
    public ArbolAVL() {
    	this.raiz = null;
	}
    
    public NodoAVL<T> getRaiz() {
		return raiz;
	}

	public void setRaiz(NodoAVL<T> raiz) {
		this.raiz = raiz;
	}

	//revisa izquierda
	public void revisarI(NodoAVL<T> nodo, Integer direccion){
        NodoAVL<T> nodo1, nodo2, nodo3;
        if (direccion ==-1){
          nodo1=(NodoAVL<T>) nodo.getIzq();
          nodo2=(NodoAVL<T>) nodo1.getIzq();
        }else{
          nodo1=(NodoAVL<T>) nodo.getDer();
          nodo2=(NodoAVL<T>) nodo1.getIzq();    
        }
        if(nodo2.getFe() == -1){// Rotacion II
            nodo1.setIzq(nodo2.getDer());
            nodo2.setDer(nodo1);
            nodo1.setFe(0);
            if (direccion == -1){
                nodo.setIzq(nodo2);
            }else{
                nodo.setDer(nodo2); 
            }
        }else{ // Rotacion ID
            nodo3 = (NodoAVL<T>) nodo2.getDer();
            nodo1.setIzq(nodo3.getDer());
            nodo3.setDer(nodo1);
            nodo2.setDer(nodo3.getIzq());
            nodo3.setIzq(nodo2);
            if(nodo3.getFe() == -1){
                nodo1.setFe(1);
            }else{
                nodo1.setFe(0);
            }
            if (nodo3.getFe() == 1){
                nodo2.setFe(-1);
            }else{
                nodo2.setFe(0);
            }
            if (direccion == -1){
                nodo.setIzq(nodo3);
            }else{
                nodo.setDer(nodo3); 
            }
            
        }
        nodo1.setFe(0);
    }
    
	/**
	 * revisa derecha
	
	 */
    public void revisarD(NodoAVL<T> nodo, Integer direccion){
        NodoAVL<T> nodo1, nodo2, nodo3;
        if (direccion ==-1){
            nodo1=  nodo.getIzq();
            nodo2=  nodo1.getDer();
        }else{
            nodo1=  nodo.getDer();
            nodo2=  nodo1.getDer();
        }
        if(nodo2.getFe() == 1){// Rotacion DD
            nodo1.setDer(nodo2.getIzq());
            nodo2.setIzq(nodo1);
            nodo1.setFe(0);
            if (direccion == -1){
                nodo.setIzq(nodo2);
            }else{
                nodo.setDer(nodo2); 
            }
            
            
        }else{ // Rotacion DI
            nodo3 = (NodoAVL<T>) nodo2.getIzq();
            nodo1.setDer(nodo3.getIzq());
            nodo3.setIzq(nodo1);
            nodo2.setIzq(nodo3.getDer());
            nodo3.setDer(nodo2);
            if(nodo3.getFe() == 1){
                nodo1.setFe(-1);
            }else{
                nodo1.setFe(0);
            }
            if (nodo3.getFe() == -1){
                nodo2.setFe(1);
            }else{
                nodo2.setFe(0);
            }
            if (direccion == -1){
                nodo.setIzq(nodo3);
            }else{
                nodo.setDer(nodo3); 
            }
            
        }
        nodo1.setFe(0);
    }
    
    /**
     * Método auxiliar de inserción.
     
     */
    private Integer _insertar(NodoAVL<T> nodo, T dato){
        Integer resultado = 0;
        if (dato.compareTo(nodo.getDato()) < 0){
            if (nodo.getIzq() == null){
                nodo.setIzq(new NodoAVL<T>(dato));
                switch ( (nodo).getFe()){
                case 1: // El arbol se balanceó
                     (nodo).setFe(0);
                    resultado = 0;
                    break;
                case 0: // Se cargó del lado izquierdo
                     (nodo).setFe(-1);
                    resultado = 1;
                    break;              
                }
                //resultado = 1;
                
            }else{
                switch (_insertar(nodo.getIzq(),dato)){
                case 1: // Se insrtó un dato nuevo, revisar.
                    switch ( (nodo).getFe()){
                    case 1: // El arbol se balanceó
                         (nodo).setFe(0);
                        resultado = 0;
                        break;
                    case 0: // Se cargó del lado izquierdo
                         (nodo).setFe(-1);
                        resultado = 1;
                        break;  
                    case -1: // Reestructuracion del arbol
                        //if (nodo == raiz){
                            resultado = -1;
                        //}else{                        
                            
                            
                            
                        //}
                        break;
                    }
                    break;
                case -1:
                    revisarI( (nodo),-1);
                    break;
                case -2:
                    revisarD( (nodo),-1);
                    break;
                    
            
                    
                }
                
                
            }
        }else{
            if (dato.compareTo(nodo.getDato()) > 0){
                if (nodo.getDer() == null){
                    nodo.setDer(new NodoAVL<T>(dato));
                    switch ( (nodo).getFe()){
                    case -1: // El arbol se balanceó
                         (nodo).setFe(0);
                        resultado = 0;
                        break;
                    case 0: // Se cargó del lado izquierdo
                         (nodo).setFe(1);
                        resultado = 1;
                        break;              
                    }
                }else{
                    switch (_insertar(nodo.getDer(),dato)){
                    case 1: // Se insrtó un dato nuevo, revisar.
                        switch ( (nodo).getFe()){
                        case -1: // El arbol se balanceó
                             (nodo).setFe(0);
                            resultado = 0;
                            break;
                        case 0: // Se cargó del lado der
                             (nodo).setFe(1);
                            resultado = 1;
                            break;  
                        case 1: // Reestructuracion del arbol
                                resultado = -2;
                            }
                            break;
                    case -1:
                        revisarI( (nodo),1);
                        break;
                    case -2:
                        revisarD( (nodo),1);
                    break;
                    
                        }                       
                        
                    }
                }
            }
        
        
        return resultado;
     
   }
    //metodo insertar
  
    public Integer insertar(T dato) {
        if (raiz == null){
            raiz = new NodoAVL<T>(dato);
        }else{
            Integer result = _insertar(raiz, dato);
            if (result == -1){
            
                // Reestructuración del arbol
                    NodoAVL<T> nodo1, nodo2;
                    nodo1=  raiz.getIzq();
                    
                    if(nodo1.getFe() == -1){// Rotacion II
                        raiz.setIzq(nodo1.getDer());
                        nodo1.setDer(raiz);
                         (raiz).setFe(0);
                        raiz = nodo1;
                    }else{ // Rotacion ID
                        nodo2 = (NodoAVL<T>) nodo1.getDer();
                        raiz.setIzq(nodo2.getDer());
                        nodo2.setDer(raiz);
                        nodo1.setDer(nodo2.getIzq());
                        nodo2.setIzq(nodo1);
                        if(nodo2.getFe() == -1){
                             (raiz).setFe(1);
                        }else{
                             (raiz).setFe(0);
                        }
                        if (nodo2.getFe() == 1){
                            nodo1.setFe(-1);
                        }else{
                            nodo1.setFe(0);
                        }
                        raiz = nodo2;
                    }
                     (raiz).setFe(0);
            
                
                         
            }else if (result == -2){
                // Reestructuración del arbol
                NodoAVL<T> nodo1, nodo2;
                nodo1=  raiz.getDer();
                
                if(nodo1.getFe() == 1){// Rotacion DD
                    raiz.setDer(nodo1.getIzq());
                    nodo1.setIzq(raiz);
                     (raiz).setFe(0);
                    raiz = nodo1;
                }else{ // Rotacion DI
                    nodo2 = (NodoAVL<T>) nodo1.getIzq();
                    raiz.setDer(nodo2.getIzq());
                    nodo2.setIzq(raiz);
                    nodo1.setIzq(nodo2.getDer());
                    nodo2.setDer(nodo1);
                    if(nodo2.getFe() == 1){
                         (raiz).setFe(-1);
                    }else{
                         (raiz).setFe(0);
                    }
                    if (nodo2.getFe() == -1){
                        nodo1.setFe(1);
                    }else{
                        nodo1.setFe(0);
                    }
                    raiz = nodo2;
                    
                }
                
                 (raiz).setFe(0);
        
            }
        }
        return 0;
    }
	
    
	@Override
	public String toString() {
		String s = "";
		s += raiz.getDato();
		return s;
	}
	
	
	public NodoAVL<T> buscar(T dato,NodoAVL<T> r){
        long start = System.currentTimeMillis(); //nos permite iniciar un conteo del tiempo de ejecucion del metodo
        if(r==null){
            return null;
        }else if(r.getDato().compareTo(dato)==0){
            long end = System.currentTimeMillis();
  long res = end - start;
  System.out.println("Milisegundos "+res+" Segundos: "+res/1000);
            return r;
        }else if(r.getDato().compareTo(dato)<0){
            return buscar(dato,r.getDer());
        }else if(r.getDato().compareTo(dato)>0){
            return buscar(dato,r.getIzq());
        }else{
            long end = System.currentTimeMillis();
  long res = end - start;
  System.out.println("Milisegundos "+res+" Segundos: "+res/1000);
            return null;
        }
         
    }
        public void preOrden(){
        ayudantePreOrden(raiz);
    }

    private void ayudantePreOrden(NodoAVL aux){
        int i = 1;
        if(aux!=null){
            System.out.println(aux.getDato());
            ayudantePreOrden(aux.getIzq());
            ayudantePreOrden(aux.getDer());
        }
    }

     
        public static void main(String [] args){
            ArbolAVL a=new ArbolAVL();
            a.insertar(6);
            a.insertar(4);
            a.insertar(7);
            a.insertar(8);
            a.insertar(9);
            a.insertar(10);
            BTreePrinter.printNode(a.getRaiz());
        }
}
