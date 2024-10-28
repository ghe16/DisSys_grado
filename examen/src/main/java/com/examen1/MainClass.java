package com.examen1;


 //1) Explique cuántos threads va a crear. Escriba de nuevo el método main (+2)
//Vamos un thread para el cálculo de la nómina y otro thread para el cálculo del stock.

public class MainClass { 
    public static void main(String args[]) {

        Nomina nomina = new Nomina();
    
        Stock stock = new Stock();
    
        Thread threadNomina = new Thread(nomina); // (+0,5)
    
        Thread threadStock = new Thread(stock); //  (+0,5)
    
        threadNomina.start(); // (+0,5)
    
        threadStock.start();  // (+0,5)
    
        }
    }