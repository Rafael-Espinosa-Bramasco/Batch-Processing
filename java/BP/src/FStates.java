
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

import libs.FS_Process;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */

/**
 *
 * @author rafael
 */
public class FStates extends javax.swing.JFrame {

    /**
     * Creates new form FStates
     */
    public FStates() {
        initComponents();
        
        newModel = (DefaultTableModel) this.NewState.getModel();
        preModel = (DefaultTableModel) this.PreparatedState.getModel();
        bloModel = (DefaultTableModel) this.BlockedState.getModel();
        finModel = (DefaultTableModel) this.FinishedState.getModel();
        
        this.ActualPID = 0;
    }
    
    int ActualPID;
    
    DefaultTableModel newModel;
    DefaultTableModel preModel;
    DefaultTableModel bloModel;
    DefaultTableModel finModel;
    
    FSThread masterThread;
    
    public void setGlobalCounter(String _GC){
        this.GC_Counter.setText(_GC);
    }

    public void setMasterThread(FSThread _masterThread){
        this.masterThread = _masterThread;
    }
    
    public void setPID(String PID){
        this.Program_ID.setText(PID);
    }
    
    public void setOP(String OP){
        this.Operation.setText(OP);
    }
    
    public void setMET(String MET){
        this.MET_Counter.setText(MET);
    }
    
    public void setTE(String TE){
        this.TE_Counter.setText(TE);
    }
    
    public void setTL(String TL){
        this.TL_Counter.setText(TL);
    }
    
    public void setStatus(String status){
        this.status.setText(status);
    }
    
    public void updateAll(ArrayList<FS_Process> _News,ArrayList<FS_Process>  _Prep,ArrayList<FS_Process>  _Block,ArrayList<FS_Process>  _Finish){
        this.updateNews(_News);
        this.updatePrep(_Prep);
        this.updateBloc(_Block);
        this.updateFini(_Finish);
    }
    
    private void randomizeProcesses(){
        
        // Programmer name isnt necessary
        int OP1;
        int OP2;
        char OP = ' ';
        int MET;
        String PID;
        
        FS_Process newProcess;
        
        OP1 = (int) (Math.random() * 100);

        switch((int) (Math.random() *12+1)){
            case 1, 2 -> {OP = '+';}
            case 3, 4 -> {OP = '-';}
            case 5, 6 -> {OP = 'x';}
            case 7, 8 -> {OP = '/';}
            case 9, 10 -> {OP = '%';}
            case 11, 12 -> {OP = 'P';}
        }

        MET = (int) (7 + Math.random() * (18 - 7));

        if(OP == '/' || OP == '%'){
            OP2 = (int) (Math.random() *100+1);
        }else{
            OP2 = (int) (Math.random() *100+0);
        }

        PID = "P".concat(String.valueOf(this.ActualPID)).concat("ID");
        this.ActualPID++;

        newProcess = new FS_Process(PID,MET,String.valueOf(OP1),OP,String.valueOf(OP2));
        this.masterThread.addNewProcess(newProcess);
    }
    
    public void clearTable(char table){
        switch(table){
            case 'N' -> {
                while(this.newModel.getRowCount() > 0){
                    this.newModel.removeRow(0);
                }
            }
            case 'P' -> {
                while(this.preModel.getRowCount() > 0){
                    this.preModel.removeRow(0);
                }
            }
            case 'B' -> {
                while(this.bloModel.getRowCount() > 0){
                    this.bloModel.removeRow(0);
                }
            }
            case 'F' -> {
                if(this.finModel.getRowCount() > 0){
                    while(this.finModel.getRowCount() > 0){
                        this.finModel.removeRow(0);
                    }
                }
            }
        }
    }
    public void updateNews(ArrayList<FS_Process> _News){
        clearTable('N');
        
        for(int i = 0; i < _News.size() ; i++){
            this.newModel.addRow(new Object[]{_News.get(i).getID(), _News.get(i).getFullOperation(), _News.get(i).getMET()});
        }
    }
    public void updatePrep(ArrayList<FS_Process> _Prep){
        clearTable('P');
        
        for(int i = 0; i < _Prep.size() ; i++){
            this.preModel.addRow(new Object[]{_Prep.get(i).getID(), _Prep.get(i).getMET(), _Prep.get(i).getRT()});
        }
    }
    public void updateBloc(ArrayList<FS_Process> _Block){
        clearTable('B');
        
        for(int i = 0; i < _Block.size() ; i++){
            this.bloModel.addRow(new Object[]{_Block.get(i).getID(), _Block.get(i).getBlockedTime()});
        }
    }
    public void updateFini(ArrayList<FS_Process> _Finish){
        clearTable('F');
        
        for(int i = 0; i < _Finish.size() ; i++){
            this.finModel.addRow(new Object[]{_Finish.get(i).getID(), _Finish.get(i).getFullOperation(), (_Finish.get(i).getIsError()) ? "<ERROR>" : _Finish.get(i).getResult()});
        }
    }
        
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Table = new javax.swing.JScrollPane();
        FinishedState = new javax.swing.JTable();
        Table1 = new javax.swing.JScrollPane();
        NewState = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        Table2 = new javax.swing.JScrollPane();
        PreparatedState = new javax.swing.JTable();
        Table3 = new javax.swing.JScrollPane();
        BlockedState = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        Program_ID = new javax.swing.JLabel();
        Operation = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        TE_Counter = new javax.swing.JLabel();
        TL_Counter = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        GC_Counter = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        MET_Counter = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        status = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Five States Simulation");
        setResizable(false);
        addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                formKeyTyped(evt);
            }
        });

        FinishedState.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
            },
            new String [] {
                "Program ID", "Operation", "Result"
            }
        ));
        FinishedState.setFocusable(false);
        FinishedState.setShowGrid(true);
        Table.setViewportView(FinishedState);

        NewState.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
            },
            new String [] {
                "Program ID", "Operation", "MET"
            }
        ));
        NewState.setFocusable(false);
        NewState.setShowGrid(true);
        Table1.setViewportView(NewState);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel1.setText("New");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel2.setText("Finished");

        PreparatedState.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
            },
            new String [] {
                "Program ID", "MET", "RT"
            }
        ));
        PreparatedState.setFocusable(false);
        PreparatedState.setShowGrid(true);
        Table2.setViewportView(PreparatedState);

        BlockedState.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
            },
            new String [] {
                "Program ID", "Blocked Elapsed Time"
            }
        ));
        BlockedState.setFocusable(false);
        BlockedState.setShowGrid(true);
        Table3.setViewportView(BlockedState);

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel3.setText("Preparated");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel4.setText("Blocked");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel5.setText("Ejecución");

        jLabel6.setText("ID: ");

        jLabel7.setText("Operation:");

        jLabel8.setText("MET: ");

        jLabel9.setText("Time elapsed:");

        jLabel10.setText("Time left:");

        Program_ID.setText("0");

        Operation.setText("Operation");

        jLabel13.setText("Seconds");

        TE_Counter.setText("0");

        TL_Counter.setText("0");

        jLabel16.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel16.setText("Global Time: ");

        GC_Counter.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        GC_Counter.setText("0");

        jLabel18.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel18.setText("Seconds");

        jLabel17.setText("Seconds");

        jLabel19.setText("Seconds");

        MET_Counter.setText("0");

        jLabel20.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel20.setText("Status: ");

        status.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        status.setText("On Process");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(Table1, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(TL_Counter)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel19))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                            .addGap(35, 35, 35)
                                            .addComponent(jLabel5))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(layout.createSequentialGroup()
                                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                        .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                                            .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                            .addComponent(MET_Counter)
                                                            .addGap(15, 15, 15))
                                                        .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.LEADING))
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                        .addComponent(Operation, javax.swing.GroupLayout.DEFAULT_SIZE, 72, Short.MAX_VALUE)
                                                        .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(Program_ID, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                                .addGroup(layout.createSequentialGroup()
                                                    .addComponent(jLabel9)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                    .addComponent(TE_Counter)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                    .addComponent(jLabel17)))
                                            .addGap(48, 48, 48)))
                                    .addComponent(Table2, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(Table3, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Table, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel16)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(GC_Counter)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel18)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel20)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(status)))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(148, 148, 148)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(191, 191, 191)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(133, 133, 133)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addGap(130, 130, 130))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(GC_Counter)
                    .addComponent(jLabel18)
                    .addComponent(jLabel20)
                    .addComponent(status))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(jLabel2)
                    .addComponent(jLabel1))
                .addGap(2, 2, 2)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Table, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Table1, javax.swing.GroupLayout.PREFERRED_SIZE, 396, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(Table2, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(Table3, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel5)
                                .addGap(11, 11, 11)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel6)
                                    .addComponent(Program_ID))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel7)
                                    .addComponent(Operation))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel8)
                                    .addComponent(jLabel13)
                                    .addComponent(MET_Counter))
                                .addGap(26, 26, 26)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel9)
                                    .addComponent(TE_Counter)
                                    .addComponent(jLabel17))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel10)
                                    .addComponent(TL_Counter)
                                    .addComponent(jLabel19))))
                        .addGap(3, 3, 3)))
                .addGap(7, 7, 7))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void formKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyTyped
        // TODO add your handling code here:
        switch(evt.getKeyChar()){
            case 'e', 'E' -> {
                this.masterThread.interruptThread();
            }
            case 'w', 'W' -> {
                this.masterThread.errorThread();
            }
            case 'p', 'P' -> {
                this.masterThread.pauseThread();
            }
            case 'c', 'C' -> {
                this.masterThread.continueThread();
            }
            case 'n','N' -> {
                this.randomizeProcesses();
                this.masterThread.updateNewProcesses();
            }
            case 'b','B' -> {
                this.masterThread.showBCPTable();
            }
        }
    }//GEN-LAST:event_formKeyTyped

    public void setAutomaticationInfo(int _ActualPID){
        this.ActualPID = _ActualPID;
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FStates.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FStates.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FStates.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FStates.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FStates().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable BlockedState;
    private javax.swing.JTable FinishedState;
    private javax.swing.JLabel GC_Counter;
    private javax.swing.JLabel MET_Counter;
    private javax.swing.JTable NewState;
    private javax.swing.JLabel Operation;
    private javax.swing.JTable PreparatedState;
    private javax.swing.JLabel Program_ID;
    private javax.swing.JLabel TE_Counter;
    private javax.swing.JLabel TL_Counter;
    private javax.swing.JScrollPane Table;
    private javax.swing.JScrollPane Table1;
    private javax.swing.JScrollPane Table2;
    private javax.swing.JScrollPane Table3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel status;
    // End of variables declaration//GEN-END:variables
}
