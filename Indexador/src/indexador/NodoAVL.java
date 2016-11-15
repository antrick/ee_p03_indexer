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
public class NodoAVL<T extends Comparable<T>> {
	
	T dato;
	NodoAVL<T> izquierda;
        NodoAVL<T> derecha;
	Integer factor;
	
	
	public NodoAVL(T dato) {
		this.dato = dato;
		this.izquierda =null;
                this.derecha = null;
		factor = 0;
	}
	
	
       
	public T getDato() {
		return dato;
	}
	

	public void setDato(T dato) {
		this.dato = dato;
	}
	

	public NodoAVL<T> getIzq() {
		return izquierda;
	}
	

	public void setIzq(NodoAVL<T> izquierda) {
		this.izquierda = izquierda;
	}
	

	public NodoAVL<T> getDer() {
		return derecha;
	}
	

	public void setDer(NodoAVL<T> derecha) {
		this.derecha = derecha;
	}
	

	public Integer getFe() {
		return factor;
	}

	public void setFe(Integer factor) {
		this.factor = factor;
	}	

	@Override
	public String toString(){
		String s = "";
		s += dato;
		return s;
	}
}