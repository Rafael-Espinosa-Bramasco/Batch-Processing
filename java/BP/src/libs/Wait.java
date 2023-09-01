/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package libs;

/**
 *
 * @author rafael
 */
public class Wait extends Thread {
    
  private int numero = 0;
 
  public Wait(int x) {
    this.numero = x;
  }
   
  private void waits(){
      try {
        //Se duerme 2 segundos
        Thread.sleep(this.numero);
      } catch (InterruptedException e) {
      }
  }
  
  @Override
  public void run() {
      this.waits();
  }
}
