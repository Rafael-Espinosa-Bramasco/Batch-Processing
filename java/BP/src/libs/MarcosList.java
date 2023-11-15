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
public class MarcosList {
    
    ArrayList<FS_Process> processesList;
    
    public MarcosList(){
        this.processesList = new ArrayList<>();
        this.initPList();
    }
    
    private void initPList(){
        for(int i = 0 ; i < 36; i++){
            this.processesList.add(null);
        }
        this.processesList.trimToSize();
    }
    
    public void addProcess(FS_Process p, int index){
        this.processesList.set(index, p);
    }
    
    public void remProcess(int index){
        this.processesList.set(index, null);
    }
    
    public int getFreeSpaces(){
        int count = 0;
        for(int i = 0 ; i < 36 ; i++){
            if(this.processesList.get(i) == null){
                count++;
            }
        }
        return count;
    }
}
