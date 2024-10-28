
package com.examen1;

   //2) Modfique la clase Nomina  SIN MODIFICAR el método doNomina
   
   public class Nomina implements Runnable {  //+1
   
    void doNomina() {
   
    System.out.println("Comienzo calculo de Nomina");
   
    try {
   
    Thread.sleep(1000);
   
   
    } catch (InterruptedException e) {
   
    e.printStackTrace();
   
    }
   
    System.out.println("Fin Calculo de Nomina");
   
    }
   
    public void run() {

        try {
   
            this.doNomina();  //+1

        } catch (Exception e) {
            System.err.println(" Error: " + e.getMessage());
            e.printStackTrace();
   
    }
   
   }
}