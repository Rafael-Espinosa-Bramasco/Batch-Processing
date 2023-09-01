/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package libs;

import java.util.ArrayList;

/**
 *
 * @author rafael
 */
public class Batch {
    String BatchID;
    ArrayList<Process> BatchProcesses = new ArrayList();
    
    public Batch(String B_ID){
        this.BatchID = B_ID;
    }
    
    public void REMOVE(int index){
        this.BatchProcesses.remove(index);
    }
    public Process GET(int index){
        return (this.BatchProcesses.size() >0) ? this.BatchProcesses.get(index) : null;
    }
    public void addProcess(Process p){
        this.BatchProcesses.add(p);
    }
    public Process getActualProcess(){
        return (this.BatchProcesses.size() > 0) ? this.BatchProcesses.get(this.BatchProcesses.size() - 1) : null;
    }
    public int getNumberOfProcesses(){
        return this.BatchProcesses.size();
    }
    public String getBatchID(){
        return this.BatchID;
    }
}
