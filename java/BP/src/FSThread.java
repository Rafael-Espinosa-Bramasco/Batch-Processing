
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import libs.FS_Process;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author rafael
 */
public class FSThread extends Thread {
    FStates GUI;
    
    public FSThread(FStates _GUI, ArrayList<FS_Process> IncomingProcesses){
        GUI = _GUI;
        
        this.NewProcesses = IncomingProcesses;
        this.PreparatedProcesses = new ArrayList<>();
        this.BlockedProcesses = new ArrayList<>();
        this.FinishedProcesses = new ArrayList<>();
        
        this.GlobalCounter = 0;
        this.isPaused = false;
    }
    
    // Variables
        // Array Lists
        ArrayList<FS_Process> NewProcesses;
        ArrayList<FS_Process> PreparatedProcesses;
        ArrayList<FS_Process> BlockedProcesses;
        ArrayList<FS_Process> FinishedProcesses;
    
        // Simple Variables
        FS_Process ExecutionProcess;    // Asigned on program execution
        int GlobalCounter;
        boolean isPaused;
        boolean isInterrupted;
        boolean isError;
    
    //@Override
        
        /*
            New Processes -> Unlimited List
            Preparated Processes -> 3 Processes
            Execution Process -> 1 process
            Blocked Processes -> Unlimited List
            Finished Processes -> Unlimited List
        */
        
    public void pauseThread(){
        if(!this.isPaused){
            this.suspend();
            this.isPaused = true;
            this.GUI.setStatus("Paused");
        }
    }
    
    public void continueThread(){
        if(this.isPaused){
            this.resume();
            this.isPaused = false;
            this.GUI.setStatus("On Process");
        }
    }
    
    public void interruptThread(){
        if(!isInterrupted){
            isInterrupted = true;
        }
    }
    
    public void errorThread(){
        if(!isError){
            isError = true;
        }
    }
    
    @Override
    public void run(){
        int TE = 0;
        int TL = 0;
        int GC = 0;
        
        while(this.PreparatedProcesses.size() < 3 && !this.NewProcesses.isEmpty()){
            // Fill the first 3 new processes
            this.PreparatedProcesses.add(this.NewProcesses.get(0));
            this.NewProcesses.remove(0);
        }
        
        this.GUI.updateAll(NewProcesses, PreparatedProcesses, BlockedProcesses, FinishedProcesses);
        
        while(!this.PreparatedProcesses.isEmpty()){
            // assign the actual process
            this.ExecutionProcess = this.PreparatedProcesses.get(0);
            this.PreparatedProcesses.remove(0);
            
            this.ExecutionProcess.arrive(GC);
            
            this.GUI.updateAll(NewProcesses, PreparatedProcesses, BlockedProcesses, FinishedProcesses);
            
            // Take another process if possible
            if(!this.NewProcesses.isEmpty()){
                this.PreparatedProcesses.add(this.NewProcesses.get(0));
                this.NewProcesses.remove(0);
            }
            
            // Fill data of execution process
            this.GUI.setPID(this.ExecutionProcess.getID());
            this.GUI.setOP(this.ExecutionProcess.getFullOperation());
            this.GUI.setMET(String.valueOf(this.ExecutionProcess.getMET()));
            
            TL = this.ExecutionProcess.getRT();
            TE = this.ExecutionProcess.getMET() - TL;
            
            this.GUI.setTE(String.valueOf(TE));
            this.GUI.setTL(String.valueOf(TL));
            
            while(TL > 0){
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(FSThread.class.getName()).log(Level.SEVERE, null, ex);
                }
                TL--;
                TE++;
                GC++;
                this.GUI.setTE(String.valueOf(TE));
                this.GUI.setTL(String.valueOf(TL));
                this.GUI.setGlobalCounter(String.valueOf(GC));
            }
            
            if(TL == 0){
                this.ExecutionProcess.finish(GC);
                this.FinishedProcesses.add(ExecutionProcess);
                this.GUI.updateAll(NewProcesses, PreparatedProcesses, BlockedProcesses, FinishedProcesses);
            }
            
            try {
                Thread.sleep(250);
            } catch (InterruptedException ex) {
                Logger.getLogger(FSThread.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        this.GUI.setStatus("Finished");
        
        JOptionPane.showMessageDialog(GUI, "Processes Finished!");
    }
}
