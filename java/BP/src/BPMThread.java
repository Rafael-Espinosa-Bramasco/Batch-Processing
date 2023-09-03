/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import libs.Batch;
import libs.Process;

/**
 *
 * @author rafael
 */
public class BPMThread extends Thread {

    public BPMThread(ArrayList<Batch> BatchLists, BPMSimulation UI){
        this.PendingBatchs = BatchLists;
        this.UserInterface = UI;
    }
    
    // Variables
    ArrayList<Batch> PendingBatchs;
    BPMSimulation UserInterface;
    String OPformated;
    int TEV;
    int TLV;
    
    @Override
    public void run() {
        this.UserInterface.pendingBatchsCount = this.PendingBatchs.size();
        
        this.UserInterface.setGCounter("0 Seconds");
        
        while(this.UserInterface.pendingBatchsCount > 0){
            // PBC decrements by one
            Batch AB = this.PendingBatchs.get(0);
            this.UserInterface.pendingBatchsCount--;
            // Delete the taken batch
            this.PendingBatchs.remove(0);
            
            this.UserInterface.setBIDNumber(AB.getBatchID());
            this.UserInterface.setBMETNumber(String.valueOf(this.UserInterface.getBMET(AB)).concat(" Seconds"));
            
            this.UserInterface.updatePB();
            
            // Take processes
            while(AB.getNumberOfProcesses() > 0){
                Process AP = AB.GET(0);
                AB.REMOVE(0);
                TEV = 0;
                TLV = AP.getMETime();
                
                try {
                    Thread.sleep(500);
                } catch (InterruptedException ex) {
                    Logger.getLogger(BPMThread.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                this.UserInterface.setPIDNumber(AP.getProgramID());
                this.UserInterface.setPName(AP.getProgrammerName());
                this.UserInterface.setOperation(AP.getNumber1().concat(" ").concat(AP.getOperation()).concat(" ").concat(AP.getNumber2()));
                this.UserInterface.setPMET(String.valueOf(AP.getMETime()));
                this.UserInterface.setTElapsed("0 Seconds");
                this.UserInterface.setTLeft(String.valueOf(TLV).concat(" Seconds"));
                
                while(TLV > 0){
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(BPMThread.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    TLV--;
                    TEV++;
                    this.UserInterface.GC++;
                    this.UserInterface.updateGC();
                    this.UserInterface.setTElapsed(String.valueOf(TEV).concat(" Seconds"));
                    this.UserInterface.setTLeft(String.valueOf(TLV).concat(" Seconds"));
                }
                
                // Update table
                this.OPformated = AP.getNumber1().concat(" ").concat(AP.getOperation()).concat(" ").concat(AP.getNumber2());
                String RES = this.doMath(AP.getNumber1(), AP.getOperation(), AP.getNumber2());
                this.UserInterface.addEndedProcess(AB.getBatchID(), AP.getProgramID(), this.OPformated, RES);
                
                try {
                    Thread.sleep(100);
                } catch (InterruptedException ex) {
                    Logger.getLogger(BPMThread.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        JOptionPane.showMessageDialog(this.UserInterface, "The simulation has ended!");
    }
    
    private String doMath(String OP1, String OPM, String OP2){
        double a = Double.parseDouble(OP1);
        double b = Double.parseDouble(OP2);
        double res;
        
        res = switch (OPM) {
            case "+" -> a + b;
            case "-" -> a - b;
            case "x" -> a * b;
            case "/" -> a / b;
            case "%" -> a % b;
            case "POW" -> java.lang.Math.pow(a, b);
            default -> 0.0;
        };
        
        return String.valueOf(res);
    }
}