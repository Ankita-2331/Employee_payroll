package employee_payroll;

import java.awt.HeadlessException;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class PayrollHome extends javax.swing.JFrame {

    Connection conn;
    ResultSet rs;
    Statement stm;

    public PayrollHome() {
        initComponents();
        try {
            Class.forName("com.mysql.jdbc.Driver"); // To check if library added or not
            conn = DriverManager.getConnection("jdbc:mysql://localhost/payroll", "root", "root");
            stm = conn.createStatement(); // Intialises stm object
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e.getMessage());
        }
        getUserData();
        getEmployeeData();
        getLeaveData();
        getSalaryData();
    }

    void getLeaveData() {
        try {
            String searchStr = empIdSearchTf.getText().trim();
            String query = "Select * from leaves where employeeId>0 ";
            if (!searchStr.equals("")) {
                query += " and employeeId like '" + searchStr + "%'";
            }
            // System.out.println(query);
            rs = stm.executeQuery(query);

            // get table data from JTable(userTable)
            DefaultTableModel tableModel = (DefaultTableModel) leaveTable.getModel();

            // get total rows in  table (JTable(userTable))
            int rowCount = tableModel.getRowCount();

            // empty Jtable records
            for (int i = 0; i < rowCount; i++) {
                tableModel.removeRow(0);
            }
            // executes till records are available in mysql table and add records in userTable(JTable)
            while (rs.next()) {
                tableModel.addRow(new Object[]{
                    rs.getInt("leaveid"),
                    rs.getString("reason"),
                    rs.getInt("employeeId"),
                    rs.getString("leavedate")
                });
                /*sets first record of the mysql table in the respective textfields */
                if (rs.isFirst()) {
                    leaveIdLbl.setText(rs.getString("leaveid"));
                    reasonTf.setText(rs.getString("reason"));
                    leaveEmpIdTf.setText(rs.getString("employeeId"));
                    leavedateLbl.setText(rs.getString("leavedate"));
                }
                // rs.getString("name of mysql table column")

            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    void getUserData() {
        try {
            String userIdStr = userIdSearchTf.getText().trim();
            
            
           
            String query = "Select * from user where userid>0";
            
            if (!userIdStr.equals("")) {
                query += " and userid like'" + userIdStr + "%'";
            }
            rs = stm.executeQuery(query);

            // get table data from JTable(userTable)
            DefaultTableModel tableModel = (DefaultTableModel) userTable.getModel();

            // get total rows in  table (JTable(userTable))
            int rowCount = tableModel.getRowCount();

            // empty Jtable records
            for (int i = 0; i < rowCount; i++) {
                tableModel.removeRow(0);
            }
            // executes till records are available in mysql table and add records in userTable(JTable)
            while (rs.next()) {
                tableModel.addRow(new Object[]{
                    rs.getInt("userid"),
                    rs.getString("username"),
                    rs.getInt("password"),
                    rs.getString("status"),
                    rs.getString("usertype")
                });
                /*sets first record of the mysql table in the respective textfields */
                if (rs.isFirst()) {
                    userIdTf.setText(rs.getString("userid"));
                    userNameTf.setText(rs.getString("username"));
                    userPasswordTf.setText(rs.getString("password"));
                    userstatusCmb.setSelectedItem(rs.getString("status"));
                    usertypeCmb.setSelectedItem(rs.getString("usertype"));
                }
                // rs.getString("name of mysql table column")

            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    void getEmployeeData() {
        try {
            String empNameStr = empNameSearchTf.getText().trim();
            String query = "Select * from employee where employeeId>0";
            if (!empNameStr.equals("")) {
                query += " and employeename like'" + empNameStr + "%'";
            }
            rs = stm.executeQuery(query);

            // get table data from JTable(userTable)
            DefaultTableModel tableModel = (DefaultTableModel) empTable.getModel();

            // get total rows in  table (JTable(userTable))
            int rowCount = tableModel.getRowCount();

            // empty Jtable records
            for (int i = 0; i < rowCount; i++) {
                tableModel.removeRow(0);
            }
            // executes till records are available in mysql table and add records in userTable(JTable)
            while (rs.next()) {
                tableModel.addRow(new Object[]{
                    rs.getInt("userid"),
                    rs.getString("employeename") != null ? rs.getString("employeename") : "",
                    rs.getInt("employeeid"),
                    rs.getString("designation") != null ? rs.getString("designation") : "",
                    rs.getString("contactnumber") != null ? rs.getString("contactnumber") : "",
                    rs.getString("password"),
                    rs.getString("status"),
                    rs.getInt("salary")
                    });
                /*sets first record of the mysql table in the respective textfields */
                if (rs.isFirst()) {
                    euseridTf.setText(rs.getString("userid"));
                    empNameTf.setText(rs.getString("employeename"));
                    empUserIdTf.setText(rs.getString("employeeid"));
                    empDesignationTf.setText(rs.getString("designation"));
                    empContactNumTf.setText(rs.getString("contactnumber"));
                    empPasswordTf.setText(rs.getString("password"));
                    empStatus.setSelectedItem(rs.getString("status"));
                    empBasicSalaryTf.setText(rs.getString("salary"));
                }
                // rs.getString("name of mysql table column")

            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    void getSalaryData() {
        try {
            String salaryEmpIdStr = salaryEmpIdSearchTf.getText().trim();
            String query = "Select * from salary where salaryid>0";
            if (!salaryEmpIdStr.equals("")) {
                query += " and employeeid like'" + salaryEmpIdStr + "%'";
            }
            rs = stm.executeQuery(query);

            // get table data from JTable(userTable)
            DefaultTableModel tableModel = (DefaultTableModel) salaryTable.getModel();

            // get total rows in  table (JTable(userTable))
            int rowCount = tableModel.getRowCount();

            // empty Jtable records
            for (int i = 0; i < rowCount; i++) {
                tableModel.removeRow(0);
            }
            // executes till records are available in mysql table and add records in userTable(JTable)
            while (rs.next()) {
                tableModel.addRow(new Object[]{
                    rs.getInt("salaryid"),
                    rs.getInt("employeeid"),
                    rs.getString("month"),
                    rs.getDouble("incentive"),
                    rs.getInt("leavecount"),
                    rs.getDouble("salary"),
                    rs.getDouble("deduction"),
                    rs.getDouble("netsalary")
                });
                /*sets first record of the mysql table in the respective textfields */
                if (rs.isFirst()) {
                    salaryIdLbl.setText(rs.getString("salaryId"));
                    salaryEmpIdTf.setText(rs.getString("employeeid"));
                    salaryMonthTf.setText(rs.getString("month"));
                    incentiveTf.setText(rs.getString("incentive"));
                    leaveCountTf.setText(rs.getString("leavecount"));
                    salaryTf.setText(rs.getString("salary"));
                    deductionLbl.setText(rs.getString("deduction"));
                    netSalaryTf.setText(rs.getString("netsalary"));
                }
                // rs.getString("name of mysql table column")

            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        userIdSearchTf = new javax.swing.JTextField();
        userIdSearchBtn = new javax.swing.JButton();
        label = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        userTable = new javax.swing.JTable();
        jPanel7 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        userstatusCmb = new javax.swing.JComboBox();
        userIdTf = new javax.swing.JTextField();
        userNameTf = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        usertypeCmb = new javax.swing.JComboBox();
        userPasswordTf = new javax.swing.JPasswordField();
        jPanel8 = new javax.swing.JPanel();
        addUserBtn = new javax.swing.JButton();
        updateUserBtn = new javax.swing.JButton();
        deleteUserBtn = new javax.swing.JButton();
        clearUserBtn = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jPanel10 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        empNameTf = new javax.swing.JTextField();
        empDesignationTf = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        empContactNumTf = new javax.swing.JTextField();
        empUserIdTf = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        empStatus = new javax.swing.JComboBox();
        empPasswordTf = new javax.swing.JPasswordField();
        empBasicSalaryTf = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        euseridTf = new javax.swing.JTextField();
        jPanel11 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        empNameSearchTf = new javax.swing.JTextField();
        empSearchBtn = new javax.swing.JButton();
        jPanel12 = new javax.swing.JPanel();
        addEmpBtn = new javax.swing.JButton();
        updateEmpBtn = new javax.swing.JButton();
        deleteEmpBtn = new javax.swing.JButton();
        clearEmpBtn = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        empTable = new javax.swing.JTable();
        leaveTab = new javax.swing.JPanel();
        jLabel30 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        leaveEmpIdTf = new javax.swing.JTextField();
        jLabel32 = new javax.swing.JLabel();
        jPanel17 = new javax.swing.JPanel();
        jPanel18 = new javax.swing.JPanel();
        leaveBtn = new javax.swing.JButton();
        leaveClearBtn = new javax.swing.JButton();
        jPanel19 = new javax.swing.JPanel();
        jLabel34 = new javax.swing.JLabel();
        empIdSearchTf = new javax.swing.JTextField();
        leaveSearchBtn = new javax.swing.JButton();
        jScrollPane6 = new javax.swing.JScrollPane();
        leaveTable = new javax.swing.JTable();
        jLabel35 = new javax.swing.JLabel();
        leaveIdLbl = new javax.swing.JTextField();
        jScrollPane4 = new javax.swing.JScrollPane();
        reasonTf = new javax.swing.JTextArea();
        leavedateLbl = new javax.swing.JFormattedTextField();
        salaryTab = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();
        salaryEmpIdTf = new javax.swing.JTextField();
        jLabel25 = new javax.swing.JLabel();
        salaryMonthTf = new javax.swing.JTextField();
        jLabel26 = new javax.swing.JLabel();
        incentiveTf = new javax.swing.JTextField();
        jLabel27 = new javax.swing.JLabel();
        leaveCountTf = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        addSalaryBtn = new javax.swing.JButton();
        updateSalaryBtn = new javax.swing.JButton();
        clearSalaryBtn = new javax.swing.JButton();
        calculateSalaryBtn = new javax.swing.JButton();
        jPanel16 = new javax.swing.JPanel();
        jLabel29 = new javax.swing.JLabel();
        salaryEmpIdSearchTf = new javax.swing.JTextField();
        salarySearchBtn = new javax.swing.JButton();
        jScrollPane5 = new javax.swing.JScrollPane();
        salaryTable = new javax.swing.JTable();
        jLabel33 = new javax.swing.JLabel();
        netSalaryTf = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        deductionLbl = new javax.swing.JLabel();
        salaryIdLbl = new javax.swing.JTextField();
        salaryTf = new javax.swing.JTextField();
        logoutBtn = new javax.swing.JButton();
        jLabel20 = new javax.swing.JLabel();
        viewSalBtn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel9.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel5.setText("Enter User's Id");

        userIdSearchTf.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                userIdSearchTfActionPerformed(evt);
            }
        });

        userIdSearchBtn.setText("Search");
        userIdSearchBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                userIdSearchBtnActionPerformed(evt);
            }
        });

        label.setToolTipText("");

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel5)
                .addGap(45, 45, 45)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(label, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(userIdSearchTf, javax.swing.GroupLayout.DEFAULT_SIZE, 183, Short.MAX_VALUE))
                .addGap(37, 37, 37)
                .addComponent(userIdSearchBtn)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(userIdSearchTf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(userIdSearchBtn))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(label, javax.swing.GroupLayout.DEFAULT_SIZE, 17, Short.MAX_VALUE)
                .addContainerGap())
        );

        userTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "User Id", "User Name", "Password", "Status", "User Type"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        userTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                userTableMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(userTable);
        if (userTable.getColumnModel().getColumnCount() > 0) {
            userTable.getColumnModel().getColumn(0).setPreferredWidth(5);
            userTable.getColumnModel().getColumn(1).setPreferredWidth(15);
            userTable.getColumnModel().getColumn(2).setPreferredWidth(6);
            userTable.getColumnModel().getColumn(3).setPreferredWidth(6);
            userTable.getColumnModel().getColumn(4).setPreferredWidth(5);
        }

        jPanel7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel1.setText("User id");

        jLabel2.setText("User name");

        jLabel3.setText("Password");

        jLabel4.setText("Status");

        userstatusCmb.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "ACTIVE", "BLOCKED" }));
        userstatusCmb.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        userIdTf.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                userIdTfActionPerformed(evt);
            }
        });

        jLabel6.setText("User type");

        usertypeCmb.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "ADMIN", "EMPLOYEE" }));
        usertypeCmb.setMinimumSize(new java.awt.Dimension(70, 22));
        usertypeCmb.setPreferredSize(new java.awt.Dimension(70, 22));

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jLabel6)
                        .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGap(47, 47, 47)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(userstatusCmb, 0, 170, Short.MAX_VALUE)
                    .addComponent(userIdTf)
                    .addComponent(userNameTf)
                    .addComponent(usertypeCmb, 0, 170, Short.MAX_VALUE)
                    .addComponent(userPasswordTf))
                .addContainerGap(111, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(userIdTf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(userNameTf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(userPasswordTf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(userstatusCmb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(usertypeCmb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel8.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        addUserBtn.setText("Add");
        addUserBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addUserBtnActionPerformed(evt);
            }
        });

        updateUserBtn.setText("Update");
        updateUserBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateUserBtnActionPerformed(evt);
            }
        });

        deleteUserBtn.setText("Delete");
        deleteUserBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteUserBtnActionPerformed(evt);
            }
        });

        clearUserBtn.setText("Clear");
        clearUserBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearUserBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(addUserBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(updateUserBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(deleteUserBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(clearUserBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(0, 6, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(addUserBtn)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(updateUserBtn)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(deleteUserBtn)
                .addGap(18, 18, 18)
                .addComponent(clearUserBtn)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(99, 99, 99)
                        .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel9, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING))
                .addContainerGap(99, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(35, 35, 35)
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 281, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(209, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Users", jPanel1);

        jPanel10.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel7.setText("Basic Salary");

        jLabel8.setText("Employee Name");

        jLabel9.setText("Designation");

        jLabel10.setText("Contact Number");

        jLabel11.setText("Employee  Id");

        empUserIdTf.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                empUserIdTfActionPerformed(evt);
            }
        });

        jLabel14.setText("Password");

        jLabel15.setText("Status");

        empStatus.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "ACTIVE", "BLOCKED" }));
        empStatus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                empStatusActionPerformed(evt);
            }
        });

        jLabel13.setText("User Id");

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE))
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 95, Short.MAX_VALUE)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(empDesignationTf)
                                .addComponent(empContactNumTf)
                                .addComponent(empStatus, 0, 170, Short.MAX_VALUE))
                            .addComponent(empUserIdTf, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(empBasicSalaryTf, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(euseridTf, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(empNameTf, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(empPasswordTf, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(76, Short.MAX_VALUE))))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(euseridTf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(empNameTf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(empUserIdTf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(empDesignationTf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9))
                .addGap(18, 18, 18)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(empContactNumTf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(empPasswordTf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(empStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(empBasicSalaryTf, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(28, Short.MAX_VALUE))
        );

        jPanel11.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel12.setText("Enter Employee Name");

        empSearchBtn.setText("Search");
        empSearchBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                empSearchBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel12)
                .addGap(45, 45, 45)
                .addComponent(empNameSearchTf, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(37, 37, 37)
                .addComponent(empSearchBtn)
                .addContainerGap(70, Short.MAX_VALUE))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(empNameSearchTf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(empSearchBtn))
                .addContainerGap(37, Short.MAX_VALUE))
        );

        jPanel12.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        addEmpBtn.setText("Add");
        addEmpBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addEmpBtnActionPerformed(evt);
            }
        });

        updateEmpBtn.setText("Update");
        updateEmpBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateEmpBtnActionPerformed(evt);
            }
        });

        deleteEmpBtn.setText("Delete");
        deleteEmpBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteEmpBtnActionPerformed(evt);
            }
        });

        clearEmpBtn.setText("Clear");
        clearEmpBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearEmpBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(addEmpBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(updateEmpBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(deleteEmpBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(clearEmpBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(0, 6, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(addEmpBtn)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(updateEmpBtn)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(deleteEmpBtn)
                .addGap(18, 18, 18)
                .addComponent(clearEmpBtn)
                .addContainerGap(47, Short.MAX_VALUE))
        );

        empTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "User Id", "Employee Name", "Employee Id", "Designation", "Contact Number", "Password", "Status", "Basic Salary"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        empTable.getTableHeader().setReorderingAllowed(false);
        empTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                empTableMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(empTable);
        if (empTable.getColumnModel().getColumnCount() > 0) {
            empTable.getColumnModel().getColumn(0).setPreferredWidth(5);
            empTable.getColumnModel().getColumn(1).setPreferredWidth(35);
            empTable.getColumnModel().getColumn(2).setPreferredWidth(28);
            empTable.getColumnModel().getColumn(3).setPreferredWidth(30);
            empTable.getColumnModel().getColumn(4).setPreferredWidth(30);
            empTable.getColumnModel().getColumn(5).setPreferredWidth(20);
            empTable.getColumnModel().getColumn(6).setPreferredWidth(10);
            empTable.getColumnModel().getColumn(7).setPreferredWidth(25);
        }

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(47, 47, 47)
                        .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 730, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(66, 66, 66)
                        .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(28, 28, 28)
                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 389, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Employee", jPanel2);

        jLabel30.setText("Reason");

        jLabel31.setText("Employee Id");

        leaveEmpIdTf.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                leaveEmpIdTfActionPerformed(evt);
            }
        });

        jLabel32.setText("Leave Date");

        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 170, Short.MAX_VALUE)
        );

        jPanel18.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        leaveBtn.setText("Add");
        leaveBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                leaveBtnActionPerformed(evt);
            }
        });

        leaveClearBtn.setText("Clear");
        leaveClearBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                leaveClearBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel18Layout = new javax.swing.GroupLayout(jPanel18);
        jPanel18.setLayout(jPanel18Layout);
        jPanel18Layout.setHorizontalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(leaveBtn)
                    .addComponent(leaveClearBtn))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel18Layout.setVerticalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(leaveBtn)
                .addGap(34, 34, 34)
                .addComponent(leaveClearBtn)
                .addContainerGap(72, Short.MAX_VALUE))
        );

        jPanel19.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel34.setText("Enter Employee Id ");

        empIdSearchTf.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                empIdSearchTfActionPerformed(evt);
            }
        });

        leaveSearchBtn.setText("Search");
        leaveSearchBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                leaveSearchBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel19Layout = new javax.swing.GroupLayout(jPanel19);
        jPanel19.setLayout(jPanel19Layout);
        jPanel19Layout.setHorizontalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel34)
                .addGap(45, 45, 45)
                .addComponent(empIdSearchTf, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(37, 37, 37)
                .addComponent(leaveSearchBtn)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel19Layout.setVerticalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel34)
                    .addComponent(empIdSearchTf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(leaveSearchBtn))
                .addContainerGap(37, Short.MAX_VALUE))
        );

        leaveTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Employee Id", "Leave Id", "Leave date", "Reason"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        leaveTable.getTableHeader().setReorderingAllowed(false);
        leaveTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                leaveTableMouseClicked(evt);
            }
        });
        jScrollPane6.setViewportView(leaveTable);
        if (leaveTable.getColumnModel().getColumnCount() > 0) {
            leaveTable.getColumnModel().getColumn(0).setPreferredWidth(8);
            leaveTable.getColumnModel().getColumn(1).setPreferredWidth(8);
            leaveTable.getColumnModel().getColumn(2).setPreferredWidth(20);
            leaveTable.getColumnModel().getColumn(3).setPreferredWidth(30);
        }

        jLabel35.setText("Leave Id:");

        leaveIdLbl.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                leaveIdLblActionPerformed(evt);
            }
        });

        reasonTf.setColumns(20);
        reasonTf.setRows(5);
        jScrollPane4.setViewportView(reasonTf);

        leavedateLbl.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter(new java.text.SimpleDateFormat("YYYY-MM-DD"))));
        leavedateLbl.setToolTipText("Enter Date in format 2017-05-10");

        javax.swing.GroupLayout leaveTabLayout = new javax.swing.GroupLayout(leaveTab);
        leaveTab.setLayout(leaveTabLayout);
        leaveTabLayout.setHorizontalGroup(
            leaveTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(leaveTabLayout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(leaveTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(leaveTabLayout.createSequentialGroup()
                        .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 553, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(135, 135, 135))
            .addGroup(leaveTabLayout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addGroup(leaveTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(leaveTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel30)
                        .addComponent(jLabel31, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel32, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jLabel35, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(leaveTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(leaveIdLbl)
                    .addComponent(jScrollPane4)
                    .addComponent(leaveEmpIdTf, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(leavedateLbl))
                .addGap(30, 30, 30)
                .addComponent(jPanel18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(258, 258, 258))
        );
        leaveTabLayout.setVerticalGroup(
            leaveTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(leaveTabLayout.createSequentialGroup()
                .addGroup(leaveTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(leaveTabLayout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addGroup(leaveTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(leaveIdLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel35, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(23, 23, 23)
                        .addGroup(leaveTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel30)
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                        .addGroup(leaveTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(leaveTabLayout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(jLabel31, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel32, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(leaveTabLayout.createSequentialGroup()
                                .addGap(15, 15, 15)
                                .addComponent(leaveEmpIdTf, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(leavedateLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(leaveTabLayout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addComponent(jPanel18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(leaveTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(leaveTabLayout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(jPanel17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(leaveTabLayout.createSequentialGroup()
                        .addGap(65, 65, 65)
                        .addComponent(jPanel19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 427, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(74, 74, 74))
        );

        jTabbedPane1.addTab("Leave", leaveTab);

        jLabel18.setText("Employee Id");

        jLabel25.setText("LWP Month");

        jLabel26.setText("Incentive");

        jLabel27.setText("Leave Count");

        jPanel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        addSalaryBtn.setText("Add");
        addSalaryBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addSalaryBtnActionPerformed(evt);
            }
        });

        updateSalaryBtn.setText("Update");
        updateSalaryBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateSalaryBtnActionPerformed(evt);
            }
        });

        clearSalaryBtn.setText("Clear");
        clearSalaryBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearSalaryBtnActionPerformed(evt);
            }
        });

        calculateSalaryBtn.setText("Calculate");
        calculateSalaryBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                calculateSalaryBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(addSalaryBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(updateSalaryBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(jPanel4Layout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(clearSalaryBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel4Layout.createSequentialGroup()
                            .addGap(38, 38, 38)
                            .addComponent(calculateSalaryBtn))))
                .addContainerGap(32, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(addSalaryBtn)
                .addGap(18, 18, 18)
                .addComponent(updateSalaryBtn)
                .addGap(18, 18, 18)
                .addComponent(clearSalaryBtn)
                .addGap(18, 18, 18)
                .addComponent(calculateSalaryBtn)
                .addContainerGap(36, Short.MAX_VALUE))
        );

        jPanel16.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel29.setText("Enter Employee Id");

        salarySearchBtn.setText("Search");
        salarySearchBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                salarySearchBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel29)
                .addGap(45, 45, 45)
                .addComponent(salaryEmpIdSearchTf, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(37, 37, 37)
                .addComponent(salarySearchBtn)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel29)
                    .addComponent(salaryEmpIdSearchTf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(salarySearchBtn))
                .addContainerGap(37, Short.MAX_VALUE))
        );

        salaryTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Salary Id", "Employee Id", "Month", "Incentives", "leavecount", "Salary ", "Deduction", "Net Salary"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Integer.class, java.lang.String.class, java.lang.Double.class, java.lang.Integer.class, java.lang.Double.class, java.lang.Double.class, java.lang.Double.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        salaryTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                salaryTableMouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(salaryTable);

        jLabel33.setText("Net Salary");

        jLabel16.setText("Salary");

        jLabel17.setText("Salary Id");

        jLabel19.setText("Deduction");

        javax.swing.GroupLayout salaryTabLayout = new javax.swing.GroupLayout(salaryTab);
        salaryTab.setLayout(salaryTabLayout);
        salaryTabLayout.setHorizontalGroup(
            salaryTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(salaryTabLayout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addGroup(salaryTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel17)
                    .addGroup(salaryTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jPanel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 679, Short.MAX_VALUE)
                        .addGroup(salaryTabLayout.createSequentialGroup()
                            .addGroup(salaryTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel18)
                                .addComponent(jLabel25)
                                .addComponent(jLabel26)
                                .addComponent(jLabel19)
                                .addComponent(jLabel33, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(salaryTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jLabel16, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel27, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGap(18, 18, 18)
                            .addGroup(salaryTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(netSalaryTf, javax.swing.GroupLayout.DEFAULT_SIZE, 193, Short.MAX_VALUE)
                                .addComponent(salaryEmpIdTf, javax.swing.GroupLayout.DEFAULT_SIZE, 193, Short.MAX_VALUE)
                                .addComponent(salaryMonthTf)
                                .addComponent(incentiveTf)
                                .addComponent(salaryIdLbl)
                                .addComponent(leaveCountTf)
                                .addComponent(salaryTf)
                                .addComponent(deductionLbl, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGap(168, 168, 168)
                            .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(28, Short.MAX_VALUE))
        );
        salaryTabLayout.setVerticalGroup(
            salaryTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(salaryTabLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(salaryTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel17)
                    .addComponent(salaryIdLbl, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(salaryTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(salaryTabLayout.createSequentialGroup()
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(14, 14, 14))
                    .addGroup(salaryTabLayout.createSequentialGroup()
                        .addGroup(salaryTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel18)
                            .addComponent(salaryEmpIdTf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(23, 23, 23)
                        .addGroup(salaryTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel25)
                            .addComponent(salaryMonthTf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(24, 24, 24)
                        .addGroup(salaryTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel26)
                            .addComponent(incentiveTf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(29, 29, 29)
                        .addGroup(salaryTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel27)
                            .addComponent(leaveCountTf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(salaryTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(salaryTabLayout.createSequentialGroup()
                                .addComponent(salaryTf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(10, 10, 10))
                            .addGroup(salaryTabLayout.createSequentialGroup()
                                .addComponent(jLabel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                        .addGroup(salaryTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(deductionLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGroup(salaryTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(netSalaryTf, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel33, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 427, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33))
        );

        jTabbedPane1.addTab("Salary", salaryTab);

        logoutBtn.setText("Logout");
        logoutBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logoutBtnActionPerformed(evt);
            }
        });

        jLabel20.setBackground(new java.awt.Color(51, 102, 255));
        jLabel20.setFont(new java.awt.Font("Times New Roman", 3, 24)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(51, 0, 153));
        jLabel20.setText("Year-2017");
        jLabel20.setBorder(new javax.swing.border.MatteBorder(null));

        viewSalBtn.setText("View Salary");
        viewSalBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                viewSalBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel20)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(viewSalBtn)
                        .addGap(50, 50, 50)
                        .addComponent(logoutBtn)
                        .addGap(58, 58, 58))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 749, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(27, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(logoutBtn)
                    .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(viewSalBtn))
                .addGap(18, 18, 18)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 915, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void logoutBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logoutBtnActionPerformed
        // TODO add your handling code here:
        Login obj = new Login();
        obj.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_logoutBtnActionPerformed

    private void salaryTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_salaryTableMouseClicked
        // TODO add your handling code here:
        int selectedRow = salaryTable.getSelectedRow();
        if (selectedRow >= 0) {
            salaryIdLbl.setText(salaryTable.getValueAt(selectedRow, 0).toString());
            salaryEmpIdTf.setText(salaryTable.getValueAt(selectedRow, 1).toString());
            salaryMonthTf.setText(salaryTable.getValueAt(selectedRow, 2).toString());
            incentiveTf.setText(salaryTable.getValueAt(selectedRow, 3).toString());
            salaryTf.setText(salaryTable.getValueAt(selectedRow, 5).toString());
            leaveCountTf.setText(salaryTable.getValueAt(selectedRow, 4).toString());
            deductionLbl.setText(salaryTable.getValueAt(selectedRow, 6).toString());
            netSalaryTf.setText(salaryTable.getValueAt(selectedRow, 7).toString());

        } else {
            getSalaryData();
        }
    }//GEN-LAST:event_salaryTableMouseClicked

    private void salarySearchBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_salarySearchBtnActionPerformed
        // TODO add your handling code here:
        getSalaryData();
    }//GEN-LAST:event_salarySearchBtnActionPerformed

    private void clearSalaryBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearSalaryBtnActionPerformed
        // TODO add your handling code here:
        salaryIdLbl.setText("");
        salaryEmpIdTf.setText("");
        salaryMonthTf.setText("");
        incentiveTf.setText("");
        salaryEmpIdSearchTf.setText("");
        leaveCountTf.setText("");
        netSalaryTf.setText("");
        salaryTf.setText("");
        deductionLbl.setText("");
    }//GEN-LAST:event_clearSalaryBtnActionPerformed

    private void updateSalaryBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateSalaryBtnActionPerformed
        String salaryEmpIdStr = salaryEmpIdTf.getText().trim();
        String salaryMonthStr = salaryMonthTf.getText().trim();
        String incentiveStr = incentiveTf.getText().trim();
        String salaryIdStr=salaryIdLbl.getText().trim();
        String leavecountStr=leaveCountTf.getText().trim();
        String salaryStr=salaryTf.getText().trim();
        

        if (salaryEmpIdStr.equals("") || incentiveStr.equals("")|| salaryIdStr.equals("") ||salaryStr.equals("")) {
            JOptionPane.showMessageDialog(null, "Enter all the values.");
            return;
        }
        int empid=0,lc=0,salid=0; 
                double salary=0, netSalary=0,incentive=0 ;

        try {
            salid=Integer.parseInt(salaryIdStr);
            empid = Integer.parseInt(salaryEmpIdStr);
            lc=Integer.parseInt(leavecountStr);
            incentive = Double.parseDouble(incentiveStr);
            salary=Double.parseDouble(salaryStr);

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Enter numeric values for employee Id,leave count,incentive and salary.");
            return;
        }
        try {
           

            String query = "select * from employee where employeeid=" + empid;
            rs = stm.executeQuery(query);
            if (!rs.next()) {
                JOptionPane.showMessageDialog(null, "Employee record does not exist!!");
                return;
            } else {
                salary = rs.getDouble("salary");
            }

            
            double deduction = 1000*lc;
            netSalary = salary - deduction + incentive;

            // query to insert record
            query = "update salary  set employeeid='" + empid + "', month='" + salaryMonthStr + "', incentive='" + incentive + "', leavecount='" + lc + "', salary='" + salary  + "' , deduction='" + deduction +  "' , netsalary='" + netSalary +  "'where salaryid=" + salid;

            // executes insert query in mysql and returns number of rows inserted
            int rowsAff = stm.executeUpdate(query);

            if (rowsAff > 0) {
                //if record inserted
                JOptionPane.showMessageDialog(null, " Salary Record updated successfully!!");
            } else {
                // no rows inserted
                JOptionPane.showMessageDialog(null, "Unable to update  Salary Record!!");
            }
            getSalaryData();
        } catch (HeadlessException | SQLException e) {
            System.out.println(e.getMessage());
        }
    }//GEN-LAST:event_updateSalaryBtnActionPerformed

    private void addSalaryBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addSalaryBtnActionPerformed
        // TODO add your handling code here:
        String salaryEmpIdStr = salaryEmpIdTf.getText().trim();
        String salaryMonthStr = salaryMonthTf.getText().trim();
        String incentiveStr = incentiveTf.getText().trim();
        String salaryIdStr=salaryIdLbl.getText().trim();
        String leavecountStr=leaveCountTf.getText().trim();
        String salaryStr=salaryTf.getText().trim();
        

        if (salaryEmpIdStr.equals("") || salaryMonthStr.equals("") || incentiveStr.equals("")|| salaryIdStr.equals("") || salaryStr.equals("")) {
            JOptionPane.showMessageDialog(null, "Enter all the values.");
            return;
        }
        int empid=0,leavecount=0,salid=0; 
                double salary=0, netSalary=0,incentive;

        try {
            salid=Integer.parseInt(salaryIdStr);
            empid = Integer.parseInt(salaryEmpIdStr);
            leavecount=Integer.parseInt(leavecountStr);
            incentive = Double.parseDouble(incentiveStr);
            salary=Double.parseDouble(salaryStr);

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Enter numeric values for employee Id,leave count,incentive and salary.");
            return;
        }
        try {
            String query = "select * from salary where salaryid=" + salid ;
            ResultSet rs = stm.executeQuery(query);
            if (rs.next()) {
                JOptionPane.showMessageDialog(null, "Salary record already exists!!");
                return;
            }

            query = "select * from employee where employeeid=" + empid;
            rs = stm.executeQuery(query);
            if (!rs.next()) {
                JOptionPane.showMessageDialog(null, "Employee record does not exist!!");
                return;
            } else {
                salary = rs.getDouble("salary");
            }

            query = "select * from leaves where employeeid=" + empid;
            rs = stm.executeQuery(query);
            if (!rs.next()) {
                JOptionPane.showMessageDialog(null, "Employee's Leave Record does not exist!!");
                return;
            } else {
                leavecount = rs.getInt("leavecount");
            }
            double deduction = leavecount *1000.00;
            netSalary = salary - deduction + incentive;

            // query to insert record
            query = "insert into salary (salaryid,employeeid,month,incentive,leavecount,salary,deduction,netsalary) values ('" + salid+ "','" + empid + "','" + salaryMonthStr +" ',' " + incentive + " ',' " + leavecount + "','" + salary + "','" + deduction + "','" + netSalary +"')";

            // executes insert query in mysql and returns number of rows inserted
            int rowsAff = stm.executeUpdate(query);

            if (rowsAff > 0) {
                //if record inserted
                JOptionPane.showMessageDialog(null, " Salary Record added successfully!!");
            } else {
                // no rows inserted
                JOptionPane.showMessageDialog(null, "Unable to add  Salary Record!!");
            }
            getSalaryData();
        } catch (HeadlessException | SQLException e) {
            System.out.println(e.getMessage());
        }
    }//GEN-LAST:event_addSalaryBtnActionPerformed

    private void empTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_empTableMouseClicked
        int selectedRow = empTable.getSelectedRow();
        if (selectedRow >= 0) {
            empBasicSalaryTf.setText(empTable.getValueAt(selectedRow,7 ).toString());
            empNameTf.setText(empTable.getValueAt(selectedRow, 1).toString());
            empDesignationTf.setText(empTable.getValueAt(selectedRow,3 ).toString());
            empContactNumTf.setText(empTable.getValueAt(selectedRow,4 ).toString());
            euseridTf.setText(empTable.getValueAt(selectedRow,0).toString());
            empPasswordTf.setText(empTable.getValueAt(selectedRow,5 ).toString());
            empStatus.setSelectedItem(empTable.getValueAt(selectedRow,6 ).toString());

            empUserIdTf.setText(empTable.getValueAt(selectedRow,2).toString());
        } else {
            getEmployeeData();
        }
    }//GEN-LAST:event_empTableMouseClicked

    private void clearEmpBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearEmpBtnActionPerformed

        empBasicSalaryTf.setText("");
        empNameTf.setText((""));
        empDesignationTf.setText("");
        empNameSearchTf.setText("");
        empPasswordTf.setText("");
        empContactNumTf.setText("");
        empUserIdTf.setText("");
        euseridTf.setText("");
    }//GEN-LAST:event_clearEmpBtnActionPerformed

    private void deleteEmpBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteEmpBtnActionPerformed
        // TODO add your handling code here:
        String userIdStr = empUserIdTf.getText().trim();

        if (userIdStr.equals("")) {
            JOptionPane.showMessageDialog(null, "Enter the employee id or select the employee record for deletion.");
            return;
        }
        int usersId;

        try {
            usersId = Integer.parseInt(userIdStr);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Enter numeric values in Employee Id.");
            return;
        }

        try {

            // query to update record
            String query = "Update employee set status='Blocked' where employeeid=" + usersId;

            // executes delete query in mysql and returns number of rows updated
            int rowsAff = stm.executeUpdate(query);

            if (rowsAff > 0) {
                
               
                //if record deleted
                JOptionPane.showMessageDialog(null, " Employee Record Blocked successfully");
            } else {
                // no record deleted
                JOptionPane.showMessageDialog(null, "Unable to Block Record");
            }
           
            getEmployeeData();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }//GEN-LAST:event_deleteEmpBtnActionPerformed

    private void updateEmpBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateEmpBtnActionPerformed
        // TODO add your handling code here:
        String empBasicSalaryStr = empBasicSalaryTf.getText().trim();
        String empnameStr = empNameTf.getText().trim();
        String empDesignationStr = empDesignationTf.getText().trim();
        String empContactStr = empContactNumTf.getText().trim();
        String euseridStr=euseridTf.getText().trim();
        String empPasswordStr = empPasswordTf.getText().trim();
        String empStatusStr = empStatus.getSelectedItem().toString();
        String empUserIdStr = empUserIdTf.getText().trim();

        if (empUserIdStr.equals("") || euseridStr.equals("") || empBasicSalaryStr.equals("") || empnameStr.equals("") || empDesignationStr.equals("") || empContactStr.equals("") || empPasswordStr.equals("")) {
        
            JOptionPane.showMessageDialog(null, "Enter the values");
            return;
        }
        int password,euser,empid; 
                double empSalary= Double.parseDouble(empBasicSalaryStr);;

        try {euser=Integer.parseInt(euseridStr);
             empid=Integer.parseInt(empUserIdStr);
            password = Integer.parseInt(empPasswordStr);
            
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Enter numeric value for password and salary");
            return;
        }
        try {

            
                //if record updated
                 String query = "update employee  set employeename='" + empnameStr + "', designation='" + empDesignationStr + "', contactnumber='" + empContactStr + "' , salary='" + empSalary + "', userid='" + euser  + "' , password='" + password +  "' , status='" + empStatusStr +  "'where employeeid=" + empid;
                // executes update query in mysql and returns number of rows updated
                int rowsAff = stm.executeUpdate(query);
                if (rowsAff > 0) {
                    JOptionPane.showMessageDialog(null, "Employee Record updated successfully");
                } else {
                    // no record updated
                    JOptionPane.showMessageDialog(null, "Unable to update Employee Record");
                }
           
            getEmployeeData();
            
        } catch (HeadlessException | SQLException e) {
            System.out.println(e.getMessage());
        }
    }//GEN-LAST:event_updateEmpBtnActionPerformed

    private void addEmpBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addEmpBtnActionPerformed
        // TODO add your handling code here:

        String empBasicSalaryStr = empBasicSalaryTf.getText().trim();
        String empnameStr = empNameTf.getText().trim();
        String empDesignationStr = empDesignationTf.getText().trim();
        String empContactStr = empContactNumTf.getText().trim();
        String euserStr=euseridTf.getText().trim();
        String empIdStr=empUserIdTf.getText().trim();
        String empPasswordStr =empPasswordTf.getText().trim();
        String empStatusStr = empStatus.getSelectedItem().toString();

        if (empBasicSalaryStr.equals("") || empIdStr.equals("") || euserStr.equals("") || empnameStr.equals("") || empDesignationStr.equals("") || empContactStr.equals("") || empPasswordStr.equals("")) {
            JOptionPane.showMessageDialog(null, "Enter the values.");
            return;
        }
        int password,euser,empid;
        double empSalary;

        try {
            euser=Integer.parseInt(euserStr);
            empid=Integer.parseInt(empIdStr);
            password = Integer.parseInt(empPasswordStr);
            empSalary = Double.parseDouble(empBasicSalaryStr);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Enter numeric values for password,employee id, salary");
            return;
        }
        try {
            String query = "select * from employee where employeeid=" + empid;
            ResultSet rs = stm.executeQuery(query);
            if (rs.next()) {
                JOptionPane.showMessageDialog(null, "Account already exists !!!");
                return;
            }
             query=" Insert into user(userid,username,password,status,usertype) values('"+euser+" ',' "+empnameStr+" ',' "+password+" ',' "+empStatusStr+" ',' "+"EMPLOYEE"+"')";

            int rowsAff = stm.executeUpdate(query);

            if (rowsAff > 0){
            query = " Insert into employee(employeename,employeeId,salary,designation,contactnumber,userid,status,password) values('"+empnameStr+" ',' "+empid+" ',' "+empSalary+" ',' "+empDesignationStr+" ',' "+empContactStr+" ',' "+euser+" ',' "+password+" ',' "+empStatusStr+"')";

            // executes insert query in mysql and returns number of rows inserted
            rowsAff = stm.executeUpdate(query, Statement.RETURN_GENERATED_KEYS);  
            


            if (rowsAff<=0) {
          
                int employeeid = 0;
                rs = stm.getGeneratedKeys();
                if (rs.next()) {
                    employeeid = rs.getInt(1);
               }
                //if record inserted
              
                JOptionPane.showMessageDialog(null, "Record added successfully!!");
            } else {
                // no rows inserted
                JOptionPane.showMessageDialog(null, "Unable to add Record!!");
            }  
            }else {
                // no record updated
                JOptionPane.showMessageDialog(null, "Unable to add Record.");
            }
            getUserData();
            getEmployeeData();
        } catch (HeadlessException | SQLException e) {
            System.out.println(e.getMessage());
        }
    }//GEN-LAST:event_addEmpBtnActionPerformed

    private void empSearchBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_empSearchBtnActionPerformed
        getEmployeeData();
    }//GEN-LAST:event_empSearchBtnActionPerformed

    private void clearUserBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearUserBtnActionPerformed
        userIdTf.setText("");
        userNameTf.setText("");
        userPasswordTf.setText("");
        userIdSearchTf.setText("");
    }//GEN-LAST:event_clearUserBtnActionPerformed

    private void deleteUserBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteUserBtnActionPerformed
        String userIdStr = userIdTf.getText().trim();

        if (userIdStr.equals("")) {
            JOptionPane.showMessageDialog(null, "Enter the User id or select the user record for deletion");
            return;
        }
        int usersId;

        try {
            usersId = Integer.parseInt(userIdStr);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Enter numeric values in usersId");
            return;
        }

        try {

            // query to update record
            String query = "Update user set status='Blocked' where userid=" + usersId;

            // executes delete query in mysql and returns number of rows updated
            int rowsAff = stm.executeUpdate(query);

            if (rowsAff > 0) {
                //if record deleted
                JOptionPane.showMessageDialog(null, " Users Record Blocked successfully");
            } else {
                // no record deleted
                JOptionPane.showMessageDialog(null, "Unable to Block Users Record");
            }
            getUserData();
            getEmployeeData();

        } catch(Exception e) {
            System.out.println(e.getMessage());
        }
    }//GEN-LAST:event_deleteUserBtnActionPerformed

    @SuppressWarnings("empty-statement")
    private void updateUserBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateUserBtnActionPerformed
        String userIdStr = userIdTf.getText().trim();
        String userNameStr = userNameTf.getText().trim();
        String userPasswordStr = userPasswordTf.getText().trim();
        String userstatusStr = userstatusCmb.getSelectedItem().toString();
        String usertypeStr = usertypeCmb.getSelectedItem().toString();

        if (userNameStr.equals("") || userPasswordStr.equals("")) {
            JOptionPane.showMessageDialog(null, "Enter the values");
            return;
        }

        int password, usersid;

        try {
            password = Integer.parseInt(userPasswordStr);
            usersid = Integer.parseInt(userIdStr);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Enter numeric value for password");
            return;
        }
        try {

            // query to update record
            String query = "update user  set username='" + userNameStr + "',password=" + password + ",status='" + userstatusStr + "',usertype='" + usertypeStr + "' where userid=" + usersid;

            // executes update query in mysql and returns number of rows updated
            int rowsAff = stm.executeUpdate(query);

            if (rowsAff > 0) {
                //if record updated
                JOptionPane.showMessageDialog(null, "Users Record updated successfully");
            } else {
                // no record updated
                JOptionPane.showMessageDialog(null, "Unable to update Users Record");
            }
            getUserData();
        } catch (HeadlessException | SQLException e) {
            System.out.println(e.getMessage());
        }
    }//GEN-LAST:event_updateUserBtnActionPerformed

    private void addUserBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addUserBtnActionPerformed
        String userIdStr = userIdTf.getText().trim();
        String userNameStr = userNameTf.getText().trim();
        String userPasswordStr = userPasswordTf.getText().trim();
        String userstatusStr = userstatusCmb.getSelectedItem().toString();
        String usertypeStr = usertypeCmb.getSelectedItem().toString();

        if (userNameStr.equals("") || userPasswordStr.equals("")) {
            JOptionPane.showMessageDialog(null, "Enter the values");
            return;
        }
        int password,uid;

        try {
            password = Integer.parseInt(userPasswordStr);
            uid=Integer.parseInt(userIdStr);
        } catch(NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Enter numeric value for password and userid.");
            return;
        }
        
        try {
             String query = "select * from user where userid=" + uid;
            ResultSet rs = stm.executeQuery(query);
            if (rs.next()) {
                JOptionPane.showMessageDialog(null, "Account already exists !!!");
                return;
            }
            
            query = " Insert into user(userid,username,password,status,usertype) values('"+uid+" ',' "+userNameStr+" ',' "+userPasswordStr+" ',' "+userstatusStr+" ',' "+usertypeStr+"')";

            // executes insert query in mysql and returns number of rows inserted
            int rowsAff = stm.executeUpdate(query, Statement.RETURN_GENERATED_KEYS);  
            


            if (rowsAff<=0) {
          
                int userid = 0;
                 rs = stm.getGeneratedKeys();
                if (rs.next()) {
                    userid = rs.getInt(1);
               }
                //if record inserted
              
                JOptionPane.showMessageDialog(null, " User Record added successfully");
            } else {
                // no rows inserted
                JOptionPane.showMessageDialog(null, "Unable to add  User Record");
            }  
            getUserData();
        } catch (HeadlessException | SQLException e) {
            System.out.println(e.getMessage());
        }
    }//GEN-LAST:event_addUserBtnActionPerformed

    private void userTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_userTableMouseClicked
        int selectedRow = userTable.getSelectedRow();
        if (selectedRow >= 0) {
            userIdTf.setText(userTable.getValueAt(selectedRow, 0).toString());
            userNameTf.setText(userTable.getValueAt(selectedRow, 1).toString());
            userPasswordTf.setText(userTable.getValueAt(selectedRow, 2).toString());
            userstatusCmb.setSelectedItem(userTable.getValueAt(selectedRow, 3).toString());
            usertypeCmb.setSelectedItem(userTable.getValueAt(selectedRow, 4).toString());
        } else {
            getUserData();
        }
    }//GEN-LAST:event_userTableMouseClicked

    private void userIdSearchBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_userIdSearchBtnActionPerformed
         getUserData();
    }//GEN-LAST:event_userIdSearchBtnActionPerformed

    private void leaveTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_leaveTableMouseClicked
        // TODO add your handling code here:
        int selectedRow = leaveTable.getSelectedRow();
        if (selectedRow >= 0) {
            leaveIdLbl.setText(leaveTable.getValueAt(selectedRow, 1).toString());
            reasonTf.setText(leaveTable.getValueAt(selectedRow, 3).toString());
            leaveEmpIdTf.setText(leaveTable.getValueAt(selectedRow,0 ).toString());
            leavedateLbl.setText(leaveTable.getValueAt(selectedRow,2 ).toString());
        } else {
            getLeaveData();
        }
    }//GEN-LAST:event_leaveTableMouseClicked

    private void leaveSearchBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_leaveSearchBtnActionPerformed
        // TODO add your handling code here:
        getLeaveData();
    }//GEN-LAST:event_leaveSearchBtnActionPerformed

    private void leaveClearBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_leaveClearBtnActionPerformed
        // TODO add your handling code here:
        reasonTf.setText("");
        leaveIdLbl.setText("");
        leavedateLbl.setText("");
        leaveEmpIdTf.setText("");
        empIdSearchTf.setText("");
    }//GEN-LAST:event_leaveClearBtnActionPerformed

    private void leaveBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_leaveBtnActionPerformed
        // TODO add your handling code here:
        String reasonStr = reasonTf.getText().trim();
        String useridStr = leaveEmpIdTf.getText().trim();
        String leaveidStr = leaveIdLbl.getText().trim();
        String leaveDateStr = leavedateLbl.getText().trim();

        if (reasonStr.equals("") || useridStr.equals("") ||leaveidStr.equals("")|| leaveDateStr.equals("")) {
            JOptionPane.showMessageDialog(null, "Enter all the values");
            return;
        }
        int userid,leaveid;
        

        try {
            userid = Integer.parseInt(useridStr);
            leaveid=Integer.parseInt(leaveidStr);
            

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Enter numeric value for leave id and employee id");
            return;
        }
        try {
            String query = "select * from leaves where employeeid=" + userid ;
            ResultSet rs = stm.executeQuery(query);
            if (rs.next()) {
                JOptionPane.showMessageDialog(null, "Leave already added");
                return;

            }

            query = "select * from employee where employeeid=" + userid;
            rs = stm.executeQuery(query);
            if (rs.next()) {
            } else {
                JOptionPane.showMessageDialog(null, "Employee does not exists!!");
                return;
            }
            // query to insert record
            query = "insert into leaves (employeeid,leaveid,reason,leavedate) values ('" + userid + "','" + leaveid + "','" + reasonStr + "','" + leaveDateStr+"')";

            // executes insert query in mysql and returns number of rows inserted
            int rowsAff = stm.executeUpdate(query);

            if (rowsAff > 0) {
                //if record inserted
                JOptionPane.showMessageDialog(null, " Leave Record added successfully!!");
            } else {
                // no rows inserted
                JOptionPane.showMessageDialog(null, "Unable to add  leave Record!!");
            }
            getLeaveData();
        } catch (HeadlessException | SQLException e) {
            System.out.println(e.getMessage());
        }
    }//GEN-LAST:event_leaveBtnActionPerformed

    private void calculateSalaryBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_calculateSalaryBtnActionPerformed
        // TODO add your handling code here:
        String salaryEmpIdStr = salaryEmpIdTf.getText().trim();
        String salaryMonthStr = salaryMonthTf.getText().trim();
        String incentiveStr = incentiveTf.getText().trim();
        String salaryStr=salaryTf.getText().trim();
        String leaveCountStr=leaveCountTf.getText().trim();

        if (salaryEmpIdStr.equals("") || salaryMonthStr.equals("") || incentiveStr.equals("") || salaryStr.equals("")) {
            JOptionPane.showMessageDialog(null, "Enter  all the values.");
            return;
        }
        int lc,empid;
                double salary,netSalary,incentive;

        try {
           empid=Integer.parseInt(salaryEmpIdStr);
           salary=Double.parseDouble(salaryStr);
            incentive = Double.parseDouble(incentiveStr);
            lc=Integer.parseInt(leaveCountStr);

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Enter numeric values for salary and incentive");
            return;
        }
        try {
            /*
             String query = "select * from salary where empid=" + salaryEmpIdStr + " and salmonth=" + salaryMonthStr + " and salYear=YEAR(CURDATE())";
             ResultSet rs = stm.executeQuery(query);
             if (rs.next()) {
             JOptionPane.showMessageDialog(null, "salary record already exists");
             return;
             }
             */
            String query = "select * from employee where employeeid=" + empid;
            rs = stm.executeQuery(query);
            if (!rs.next()) {
                JOptionPane.showMessageDialog(null, "Employee does not exist!!");
                return;
            } else {
                salary = rs.getDouble("salary");
            }
              leaveCountTf.setText("" + lc);
              salaryTf.setText("" + salary);
            double deduction= lc *1000.00;
            netSalary = salary +(incentive-deduction);
            netSalaryTf.setText("" + netSalary);
            deductionLbl.setText("" + deduction);
          
            
          
        } catch (HeadlessException | SQLException e) {
            System.out.println(e.getMessage());
        }
    }//GEN-LAST:event_calculateSalaryBtnActionPerformed

    private void leaveIdLblActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_leaveIdLblActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_leaveIdLblActionPerformed

    private void leaveEmpIdTfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_leaveEmpIdTfActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_leaveEmpIdTfActionPerformed

    private void userIdSearchTfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_userIdSearchTfActionPerformed
        userIdSearchTf.addKeyListener(new KeyAdapter() {
         @Override
         public void keyPressed(KeyEvent ke) {
            String value = userIdSearchTf.getText();
            int l = value.length();
            if (ke.getKeyChar() >='0' && ke.getKeyChar() <= '9'){
               ke.consume();
              } else {
                label.setText("*Enter numeric digits only");
              userIdSearchTf.setText("");
             
            }
         }
      });
        setVisible(true);
      
    }//GEN-LAST:event_userIdSearchTfActionPerformed

    private void userIdTfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_userIdTfActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_userIdTfActionPerformed

    private void empStatusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_empStatusActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_empStatusActionPerformed

    private void empUserIdTfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_empUserIdTfActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_empUserIdTfActionPerformed

    private void empIdSearchTfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_empIdSearchTfActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_empIdSearchTfActionPerformed

    private void viewSalBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_viewSalBtnActionPerformed
      Home obj = new Home();
        obj.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_viewSalBtnActionPerformed

    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PayrollHome().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addEmpBtn;
    private javax.swing.JButton addSalaryBtn;
    private javax.swing.JButton addUserBtn;
    private javax.swing.JButton calculateSalaryBtn;
    private javax.swing.JButton clearEmpBtn;
    private javax.swing.JButton clearSalaryBtn;
    private javax.swing.JButton clearUserBtn;
    private javax.swing.JLabel deductionLbl;
    private javax.swing.JButton deleteEmpBtn;
    private javax.swing.JButton deleteUserBtn;
    private javax.swing.JTextField empBasicSalaryTf;
    private javax.swing.JTextField empContactNumTf;
    private javax.swing.JTextField empDesignationTf;
    private javax.swing.JTextField empIdSearchTf;
    private javax.swing.JTextField empNameSearchTf;
    private javax.swing.JTextField empNameTf;
    private javax.swing.JPasswordField empPasswordTf;
    private javax.swing.JButton empSearchBtn;
    private javax.swing.JComboBox empStatus;
    private javax.swing.JTable empTable;
    private javax.swing.JTextField empUserIdTf;
    private javax.swing.JTextField euseridTf;
    private javax.swing.JTextField incentiveTf;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel label;
    private javax.swing.JButton leaveBtn;
    private javax.swing.JButton leaveClearBtn;
    private javax.swing.JTextField leaveCountTf;
    private javax.swing.JTextField leaveEmpIdTf;
    private javax.swing.JTextField leaveIdLbl;
    private javax.swing.JButton leaveSearchBtn;
    private javax.swing.JPanel leaveTab;
    private javax.swing.JTable leaveTable;
    private javax.swing.JFormattedTextField leavedateLbl;
    private javax.swing.JButton logoutBtn;
    private javax.swing.JLabel netSalaryTf;
    private javax.swing.JTextArea reasonTf;
    private javax.swing.JTextField salaryEmpIdSearchTf;
    private javax.swing.JTextField salaryEmpIdTf;
    private javax.swing.JTextField salaryIdLbl;
    private javax.swing.JTextField salaryMonthTf;
    private javax.swing.JButton salarySearchBtn;
    private javax.swing.JPanel salaryTab;
    private javax.swing.JTable salaryTable;
    private javax.swing.JTextField salaryTf;
    private javax.swing.JButton updateEmpBtn;
    private javax.swing.JButton updateSalaryBtn;
    private javax.swing.JButton updateUserBtn;
    private javax.swing.JButton userIdSearchBtn;
    private javax.swing.JTextField userIdSearchTf;
    private javax.swing.JTextField userIdTf;
    private javax.swing.JTextField userNameTf;
    private javax.swing.JPasswordField userPasswordTf;
    private javax.swing.JTable userTable;
    private javax.swing.JComboBox userstatusCmb;
    private javax.swing.JComboBox usertypeCmb;
    private javax.swing.JButton viewSalBtn;
    // End of variables declaration//GEN-END:variables
}
