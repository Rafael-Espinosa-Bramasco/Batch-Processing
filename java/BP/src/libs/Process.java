/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package libs;

/**
 *
 * @author rafael
 */
public class Process {
    
    String ProgrammerName, Operation, ProgramID, Number1, Number2;
    int METime;
    
    public Process(String ProgName, String num1, String num2, String Op, int time, String PID){
        this.ProgrammerName = ProgName;
        this.Number1 = num1;
        this.Number2 = num2;
        this.Operation = Op;
        this.METime = time;
        this.ProgramID = PID;
    }
    
    public String getNumber1(){
        return this.Number1;
    }
    public void setNumber1(String x){
        this.Number1 = x;
    }
    
    public String getNumber2(){
        return this.Number2;
    }
    public void setNumber2(String x){
        this.Number2 = x;
    }
    
    public int getMETime(){
        return this.METime;
    }
    public void setMETime(int x){
        this.METime = x;
    }
    
    public String getProgrammerName(){
        return this.ProgrammerName;
    }
    public void setProgrammerName(String x){
        this.ProgrammerName = x;
    }
    
    public String getProgramID(){
        return this.ProgramID;
    }
    public void setProgramID(String x){
        this.ProgramID = x;
    }
    
    public String getOperation(){
        return this.Operation;
    }
    public void setOperation(String x){
        this.Operation = x;
    }
}
