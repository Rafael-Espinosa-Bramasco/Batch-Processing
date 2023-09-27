/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package libs;

/**
 *
 * @author rafael
 */
public class FS_Process {
    
    private String ID, OP1, OP2;
    private int MET, RT, ET;
    private char OP;
    
    int ArriveTime;         // When the process is on the Preparated State (The function 'arrive' is called)
    int EndTime;            // When the process is finished (The function 'finish' is called)
    int ReturnTime;         // When the process is finished, calculate the time (inside the function 'finish')
    int AnswerTime;         // Time between states 'new' and 'preparated'
    int WaitTime;           // Blocked Time
    int ServiceTime;        // XD
    
    boolean isArrived;
    boolean isEnded;
    
    public FS_Process(String _ID, int _MET, String _OP1, char _OP, String _OP2){
        // Init. Vars
        ID = _ID;
        MET = _MET;
        RT = _MET;
        OP1 = _OP1;
        OP = _OP;
        OP2 = _OP2;
        ET = 0;
        
        // Init. Time data vars
        AnswerTime = 0;
        WaitTime = 0;
        ServiceTime = 0;
        ReturnTime = 0;
        ArriveTime = 0;
        EndTime = 0;
        
        isArrived = false;
        isEnded = false;
    }
    
    // Methods
    public void arrive(int t){
        if(!isArrived){
            ArriveTime = t;
            isArrived = true;
        }
    }
    
    public void finish(int t){
        if(!isEnded){
            EndTime = t;
            isEnded = true;
            
            ReturnTime = WaitTime + ServiceTime;
        }
    }
    
    public void oneSecPassed(){
        MET--;
        RT--;
        ET++;
    }

    // Getters
    public int getReturnTime(){
        return ReturnTime;
    }
    
    public int getAnswerTime(){
       return AnswerTime;
    }
    
    public int getArriveTime(){
        return ArriveTime;
    }
    
    public int getEndTime(){
        return EndTime;
    }
    
    public String getID(){
        return ID;
    }
    
    public int getMET(){
        return MET;
    }
    
    public int getRT(){
        return RT;
    }
    
    public String getOP1(){
        return OP1;
    }
    
    public char getOP(){
        return OP;
    }
    
    public String getOP2(){
        return OP2;
    }
    
    // Setters
    public void setID(String newID){
        ID = newID;
    }
    
    public void setMET(int newMET){
        MET = newMET;
    }
    
    public void setRT(int newRT){
        RT = newRT;
    }
    
    public void setOP1(String newOP1){
        OP1 = newOP1;
    }
    
    public void setOP(char newOP){
        OP = newOP;
    }
    
    public void setOP2(String newOP2){
        OP2 = newOP2;
    }
    
    // More get/set like Methods
    public void incMET(){
        MET++;
    }
    
    public void incRT(){
        RT++;
    }
    
    public void decMET(){
        MET--;
    }
    
    public void decRT(){
        RT--;
    }
}
