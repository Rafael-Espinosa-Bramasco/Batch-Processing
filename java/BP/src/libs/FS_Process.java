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
    private int MET, RT, ET, Size,Marcos_needed;
    private char OP;
    double Result;
    
    int ArriveTime;         // When the process is on the Preparated State (The function 'arrive' is called)
    int EndTime;            // When the process is finished (The function 'finish' is called)
    int ReturnTime;         // When the process is finished, calculate the time (inside the function 'finish')
    int AnswerTime;         // Time between states 'new' and 'preparated'
    int WaitTime;           // XD 2
    int ServiceTime;        // XD
    int BlockedTime;        // XD 3
    
    String State;
    
    boolean isArrived;
    boolean isEnded;
    boolean isError;
    
    public void incServiceTime(){
        this.ServiceTime++;
    }
    
    public void incAnswerTime(){
        this.AnswerTime++;
    }
    
    public FS_Process(String _ID, int _MET, String _OP1, char _OP, String _OP2, int size){
        // Init. Vars
        ID = _ID;
        MET = _MET;
        RT = _MET;
        OP1 = _OP1;
        OP = _OP;
        OP2 = _OP2;
        Size = size;
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
        isError = false;
        
        State = "New";
        
        Marcos_needed = Size/5;
        if(Size%5!=0)
            Marcos_needed++;
    }
    
    // Methods
    private void setResult(double a, char op, double b){
        switch(op){
            case '+' -> {
                Result = a + b;
            }
            case '-' -> {
                Result = a - b;
            }
            case 'x' -> {
                Result = a * b;
            }
            case '/' -> {
                Result = a / b;
            }
            case '%' -> {
                Result = a % b;
            }
            case 'P' -> {
                Result = Math.pow(a, b);
            }
        }
    }
    
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
            
            this.setResult(Double.parseDouble(OP1), OP, Double.parseDouble(OP2));
            WaitTime = AnswerTime;
            ReturnTime = WaitTime + ServiceTime;
        }
    }
    
    public void oneSecPassed(){
        RT--;
        ET++;
    }

    // Getters
    public boolean getIsError(){
        return isError;
    }
    
    public double getResult(){
        return Result;
    }
    
    public int getBlockedTime(){
        return BlockedTime;
    }
    
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
    
    public int getWaitTime(){
        return WaitTime;
    }
    
    public int getServiceTime(){
        return ServiceTime;
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
    
    public int getSize(){
        return Size;
    }
    
    public int getMarcos(){
        return Marcos_needed;
    }
    
    public String getFullOperation(){
        return OP1.concat(" ").concat(Character.toString(OP)).concat(" ").concat(OP2);
    }
    
    // Setters
    public void setError(){
        isError = true;
    }
    
    public void setBlockedTime(int t){
        BlockedTime = t;
    }
    
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
    
    public void incBT(){
        BlockedTime++;
    }
    
    public void decBT(){
        BlockedTime--;
    }
    
    public void changeState(String state){
        this.State = state;
    }
    
    public String getState(){
        return State;
    }
}
