package employee_payroll;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;

public class Login extends javax.swing.JFrame {

    static String username;
    static int userid;
    

    public Login() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        usernameTf = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        passwordTf = new javax.swing.JPasswordField();
        loginAdminBtn = new javax.swing.JButton();
        exitAdminBtn = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        useridTf = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("User Login");

        jLabel2.setText("User Name");

        jLabel3.setText("Password");

        passwordTf.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                passwordTfActionPerformed(evt);
            }
        });

        loginAdminBtn.setText("Login");
        loginAdminBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loginAdminBtnActionPerformed(evt);
            }
        });

        exitAdminBtn.setText("Exit");
        exitAdminBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitAdminBtnActionPerformed(evt);
            }
        });

        jLabel4.setText("User Id");

        useridTf.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                useridTfActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(114, 114, 114)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(44, 44, 44)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(48, 48, 48)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(usernameTf)
                            .addComponent(passwordTf, javax.swing.GroupLayout.DEFAULT_SIZE, 177, Short.MAX_VALUE)
                            .addComponent(useridTf)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(84, 84, 84)
                        .addComponent(loginAdminBtn)
                        .addGap(50, 50, 50)
                        .addComponent(exitAdminBtn)))
                .addContainerGap(35, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(useridTf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(usernameTf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(33, 33, 33)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(passwordTf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(43, 43, 43)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(loginAdminBtn)
                    .addComponent(exitAdminBtn))
                .addContainerGap(102, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void loginAdminBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loginAdminBtnActionPerformed
        String usernameStr = usernameTf.getText().trim();
        String passwordStr = new String(passwordTf.getPassword()).trim();
        String uid= useridTf.getText();
        if (usernameStr.equals("") || passwordStr.equals("") || uid.equals("")) {
            JOptionPane.showMessageDialog(null, "Enter userid,username and password");
        } else {
            try {
                Class.forName("com.mysql.jdbc.Driver"); // To check if library added or not
                Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/payroll", "root", "root");
                Statement stm = conn.createStatement(); // Intialises stm object
                String query = "select * from user where  userid= '" + uid + "'and username='" + usernameStr + " 'and password ='" + passwordStr +"'";
                ResultSet rs = stm.executeQuery(query);
                if (rs.next()) {
                    if (rs.getString("status").equalsIgnoreCase("Blocked")) {
                        JOptionPane.showMessageDialog(null, "Your account is blocked");
                        useridTf.setText(" ");
                    passwordTf.setText(" ");
                    usernameTf.setText(" ");
                        
                        return;
                    }
                    username= usernameStr;
                  
                     JOptionPane.showMessageDialog(null, "You have logged in successfully!!");
                    if (rs.getString("usertype").equalsIgnoreCase("Admin")) {
                        
                        PayrollHome obj = new PayrollHome();
             
                        obj.setVisible(true);
                        this.setVisible(false);
                    } else {
                        Home obj = new Home();

                        obj.setVisible(true);
                        this.setVisible(false);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid username and password");
                    useridTf.setText(" ");
                    passwordTf.setText(" ");
                    usernameTf.setText(" ");
                }
                stm.close();
                rs.close();
                conn.close();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }//GEN-LAST:event_loginAdminBtnActionPerformed

    private void exitAdminBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitAdminBtnActionPerformed
        System.exit(0);
    }//GEN-LAST:event_exitAdminBtnActionPerformed

    private void passwordTfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_passwordTfActionPerformed
        
    }//GEN-LAST:event_passwordTfActionPerformed

    private void useridTfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_useridTfActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_useridTfActionPerformed

    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Login().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton exitAdminBtn;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JButton loginAdminBtn;
    private javax.swing.JPasswordField passwordTf;
    private javax.swing.JTextField useridTf;
    public static javax.swing.JTextField usernameTf;
    // End of variables declaration//GEN-END:variables
}
