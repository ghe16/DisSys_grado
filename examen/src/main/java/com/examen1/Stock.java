package com.examen1;


   
   //3) Reescriba la clase  Stock  SIN MODIFICAR el método doStock 
   
   public class Stock implements Runnable { //+1
   
    void doStock() {
   
    System.out.println("Comienzo calculo de stock");
   
    try {
   
    Thread.sleep(60*1000); //simulamos 1 minuto del cálculo del stock
   
    } catch (InterruptedException e) {
   
    e.printStackTrace();
   
    }
   
    System.out.println("Fin calculo de stock");
   
    }
   
    public void run() {
   try{
    this.doStock(); //+1
   
   } catch (Exception e) {
      System.err.println(" Error: " + e.getMessage());
      e.printStackTrace();
   
   }
}}
   
   