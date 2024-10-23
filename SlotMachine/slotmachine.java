
package SlotMachine;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;


public class slotmachine extends Thread {
  slotmachineGUI slotmachinegui;
  DisplayWinner W = new DisplayWinner();
  private int number;
   private int number1;
    private int number2;
    private boolean stopSpin;
    
  public void run(){
    try {
        spin();
    } catch (InterruptedException ex) {
        Logger.getLogger(slotmachine.class.getName()).log(Level.SEVERE, null, ex);
    }
  }

    public slotmachine(slotmachineGUI slotmachinegui) {
        this.slotmachinegui = slotmachinegui;
        stopSpin=false;
    }
  
    public int getNumber() {
        return number;
    }

    public void setStopSpin(boolean x) {
        this.stopSpin = x;
    }
    
  
private void spin() throws InterruptedException {
    for (int i = 0; i < 60; i++) {
        this.number = (int) ((Math.random() * 6) + 1);
        this.number1 = (int) ((Math.random() * 6) + 1);
        this.number2 = (int) ((Math.random() * 6) + 1);

        // Incrementar la probabilidad de coincidencia
        if (Math.random() < 0.2) { 
           
            number1 = number;
        } else if (Math.random() < 0.1) { 
            
            number1 = number;
            number2 = number;
        }

        slotmachinegui.mostrar(number, number1, number2);

        if (stopSpin) {
            if (SearchWinner()) {
                slotmachinegui.dispose();
                W.setEnabled(true);
                W.setVisible(true);
            }
            return;
        }

        Thread.sleep(75);
    }

    if (SearchWinner()) {
        slotmachinegui.dispose();
        W.setEnabled(true);
        W.setVisible(true);
    }
}

public boolean SearchWinner(){
    if(number==number1&&number1==number2&&number2==number){
        return true;
    }
    return false;
}
    @Override
    public String toString() {
        return "slotmachine{" + "number=" + number + '}';
    }

    
}
