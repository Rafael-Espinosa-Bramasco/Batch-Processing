
import java.util.ArrayList;
import java.util.Collections;
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
    
    public FSThread(FStates _GUI, ArrayList<FS_Process> IncomingProcesses, int Q){
        GUI = _GUI;
        
        this.NewProcesses = IncomingProcesses;
        this.PreparatedProcesses = new ArrayList<>();
        this.BlockedProcesses = new ArrayList<>();
        this.FinishedProcesses = new ArrayList<>();
        this.AllProcesses = new ArrayList<>();
        
        this.Marcos = new ArrayList<>();
        
        this.isPaused = false;
        
        this.isQuantum = Q >= 1;
        this.Quantum = Q;
    }
    
    // Variables
        // Array Lists
        ArrayList<FS_Process> NewProcesses;
        ArrayList<FS_Process> PreparatedProcesses;
        ArrayList<FS_Process> BlockedProcesses;
        ArrayList<FS_Process> FinishedProcesses;
        ArrayList<FS_Process> AllProcesses;
        
        ArrayList<String> Marcos;
    
        // Simple Variables
        FS_Process ExecutionProcess;    // Asigned on program execution
        boolean isPaused;
        boolean isInterrupted;
        boolean isError;
        boolean isQuantum;
        int Quantum;
    
    //@Override
        
        /*
            New Processes -> Unlimited List
            Preparated Processes -> 3 Processes
            Execution Process -> 1 process
            Blocked Processes -> Unlimited List
            Finished Processes -> Unlimited List
        */
        
    public void addNewProcess(FS_Process _newProcess){
        this.NewProcesses.add(_newProcess);
    }
        
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
    
    public void showBCPTable() {
        
        this.AllProcesses.clear();
        this.AllProcesses.addAll(NewProcesses);
        this.AllProcesses.addAll(PreparatedProcesses);
        this.AllProcesses.add(ExecutionProcess);
        this.AllProcesses.addAll(BlockedProcesses);
        this.AllProcesses.addAll(FinishedProcesses);
        
        
        BCP bcp = new BCP();
        bcp.setData(AllProcesses);
        bcp.setMT(this);
        bcp.setVisible(true);
        
        this.pauseThread();
        
    }
    
    public void showPageTable() {
        MarcosTable marcos = new MarcosTable();
        marcos.setData(this.Marcos);
        marcos.setVisible(true);
        
        this.pauseThread();
        
    }
    
    public void updateNewProcesses(){
        this.GUI.updateNews(this.NewProcesses);
    }
    
    private void checkBlocked(){
        // Decrement blocked time
        for(int i = 0; i < this.BlockedProcesses.size() ; i++){
            this.BlockedProcesses.get(i).decBT();
            this.BlockedProcesses.get(i).incAnswerTime();
        }
        
        // Chek if some process has finished
        for(int i = 0; i < this.BlockedProcesses.size() ; i++){
            if(this.BlockedProcesses.get(i).getBlockedTime() < 0){
                this.PreparatedProcesses.add(this.BlockedProcesses.get(i));
                this.BlockedProcesses.get(i).changeState("Prepared");
                this.BlockedProcesses.remove(i);
                i--;
            }
        }
    }
    
    private void checkNews(){
        for(int i = 0 ; i < this.NewProcesses.size() ; i++){
            this.NewProcesses.get(i).incAnswerTime();
        }
    }
    
    @Override
    public void run(){
        int TE = 0;
        int TL = 0;
        int GC = 0;
        int ProcessesInMemory = 0;
        this.Marcos.add("SO");
        this.Marcos.add("SO");
        int volatileQuantum;
        
        while(ProcessesInMemory<34 && !this.NewProcesses.isEmpty()){
            // Fill the first 3 new processes
            boolean acomodado=false;
            for(int i=0;i<NewProcesses.size();i++)
            {
                FS_Process actual = this.NewProcesses.get(i);
                if((ProcessesInMemory + actual.getMarcos())<34)
                {
                    this.PreparatedProcesses.add(actual);
                    actual.changeState("Prepared");
                    this.NewProcesses.remove(i);
                    ProcessesInMemory+=actual.getMarcos();
                    for(int j=0;j<actual.getMarcos();j++)
                        this.Marcos.add(actual.getID());
                    acomodado = true;
                    break;
                }
            }
            if(!acomodado)
                break;
        }
        
        this.GUI.updateNews(NewProcesses);
        this.GUI.updatePrep(PreparatedProcesses);
        
        while(!this.PreparatedProcesses.isEmpty() || !this.NewProcesses.isEmpty() || !this.BlockedProcesses.isEmpty()){
            // assign the actual process
            if(ProcessesInMemory < 34){
                //Take another process if possible
                for(int i=0;i<NewProcesses.size();i++)
                {
                    FS_Process actual = this.NewProcesses.get(i);
                    if((ProcessesInMemory + actual.getMarcos())<34)
                    {
                        this.PreparatedProcesses.add(actual);
                        actual.changeState("Prepared");
                        this.NewProcesses.remove(i);
                        ProcessesInMemory+=actual.getMarcos();
                        for(int j=0;j<actual.getMarcos();j++)
                            this.Marcos.add(actual.getID());
                        this.GUI.updateNews(NewProcesses);
                        break;
                    }
                }
            }
            this.GUI.updatePrep(PreparatedProcesses);
            if(this.ExecutionProcess == null && !this.PreparatedProcesses.isEmpty())
            {
                this.ExecutionProcess = this.PreparatedProcesses.get(0);
                this.PreparatedProcesses.remove(0);

                this.ExecutionProcess.arrive(GC);
                this.ExecutionProcess.changeState("Executed");
            }
            
            
            // Fill data of execution process
            this.GUI.setPID(this.ExecutionProcess.getID());
            this.GUI.setOP(this.ExecutionProcess.getFullOperation());
            this.GUI.setMET(String.valueOf(this.ExecutionProcess.getMET()));
            
            TL = this.ExecutionProcess.getRT();
            TE = this.ExecutionProcess.getMET() - TL;
            volatileQuantum = this.Quantum;
            
            this.GUI.setTE(String.valueOf(TE));
            this.GUI.setTL(String.valueOf(TL));
            
            while(TL > 0){
                
                if(isInterrupted){
                    isInterrupted = false;
                    
                    this.ExecutionProcess.setRT(++TL);
                    this.ExecutionProcess.setBlockedTime(9);
                    this.ExecutionProcess.changeState("Blocked");
                    
                    this.BlockedProcesses.add(ExecutionProcess);
                    
                    this.GUI.updateBloc(BlockedProcesses);
                    
                    this.ExecutionProcess = null;
                    
                    break;
                }
                if(isError){
                    isError = false;
                    
                    this.ExecutionProcess.setError();
                    this.ExecutionProcess.finish(GC);
                    this.ExecutionProcess.changeState("Finished");
                    
                    this.FinishedProcesses.add(ExecutionProcess);
                    
                    ProcessesInMemory-=this.ExecutionProcess.getMarcos();
                    for(int j=0;j<this.ExecutionProcess.getMarcos();j++)
                        this.Marcos.remove(this.ExecutionProcess.getID());
                    
                    this.GUI.updateFini(FinishedProcesses);
                    
                    this.ExecutionProcess = null;
                    
                    break;
                }
                
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(FSThread.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                this.checkNews();
                this.checkBlocked();
                this.GUI.updateBloc(BlockedProcesses);
                this.GUI.updatePrep(PreparatedProcesses);
                
                TL--;
                TE++;
                GC++;
                volatileQuantum--;
                
                this.ExecutionProcess.setRT(TL);
                
                this.ExecutionProcess.incServiceTime();
                
                this.GUI.setTE(String.valueOf(TE));
                this.GUI.setTL(String.valueOf(TL));
                this.GUI.setGlobalCounter(String.valueOf(GC));
                
                if(this.isQuantum && volatileQuantum == 0){
                    break;
                }
                
                if(isInterrupted){
                    isInterrupted = false;
                    
                    this.ExecutionProcess.setRT(TL);
                    this.ExecutionProcess.setBlockedTime(9);
                    this.ExecutionProcess.changeState("Blocked");
                    
                    this.BlockedProcesses.add(ExecutionProcess);
                    
                    this.GUI.updateBloc(BlockedProcesses);
                    
                    this.ExecutionProcess = null;
                    
                    break;
                }
                if(isError){
                    isError = false;
                    
                    this.ExecutionProcess.setError();
                    this.ExecutionProcess.finish(GC);
                    this.ExecutionProcess.changeState("Finished");
                    
                    this.FinishedProcesses.add(ExecutionProcess);
                    
                    ProcessesInMemory-=ExecutionProcess.getMarcos();
                    for(int j=0;j<this.ExecutionProcess.getMarcos();j++)
                        this.Marcos.remove(this.ExecutionProcess.getID());
                    
                    this.GUI.updateFini(FinishedProcesses);
                    
                    this.ExecutionProcess = null;
                    
                    break;
                }
                this.ExecutionProcess.decRT();
            }
            
//            while(!this.BlockedProcesses.isEmpty() && this.PreparatedProcesses.isEmpty() && ProcessesInMemory == 3 && this.ExecutionProcess == null){
//                try {
//                    Thread.sleep(1000);
//                } catch (InterruptedException ex) {
//                    Logger.getLogger(FSThread.class.getName()).log(Level.SEVERE, null, ex);
//                }
//                
//                this.checkBlocked();
//
//                this.GUI.updateBloc(BlockedProcesses);
//                
//                this.GUI.setGlobalCounter(String.valueOf(++GC));
//                
//                this.GUI.setPID("-.-");
//                this.GUI.setOP("-.-");
//                this.GUI.setMET("-.-");
//                this.GUI.setTL("-.-");
//                this.GUI.setTE("-.-");
//            }

            if(TL == 0 && this.ExecutionProcess != null  && !this.ExecutionProcess.getIsError()){
                this.ExecutionProcess.finish(GC);
                this.ExecutionProcess.changeState("Finished");
                this.FinishedProcesses.add(ExecutionProcess);
                ProcessesInMemory-=this.ExecutionProcess.getMarcos();
                for(int j=0;j<this.ExecutionProcess.getMarcos();j++)
                        this.Marcos.remove(this.ExecutionProcess.getID());
                this.ExecutionProcess = null;
                this.GUI.updateFini(FinishedProcesses);
            }else if(volatileQuantum == 0 && this.ExecutionProcess != null  && !this.ExecutionProcess.getIsError()){
                this.ExecutionProcess.setRT(TL);
                this.ExecutionProcess.changeState("Prepared");
                this.PreparatedProcesses.add(this.ExecutionProcess);
                this.ExecutionProcess = null;
            }
            
            try {
                Thread.sleep(150);
            } catch (InterruptedException ex) {
                Logger.getLogger(FSThread.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        this.GUI.setPID("-.-");
        this.GUI.setOP("-.-");
        this.GUI.setMET("-.-");
        this.GUI.setTL("-.-");
        this.GUI.setTE("-.-");
        
        this.GUI.updateAll(NewProcesses, PreparatedProcesses, BlockedProcesses, FinishedProcesses);
        
        this.GUI.setStatus("Finished");
        
        FinalTable ft = new FinalTable();
        ft.setData(FinishedProcesses);
        ft.setVisible(true);
        
        JOptionPane.showMessageDialog(ft, "Processes Finished!");
    }
}
