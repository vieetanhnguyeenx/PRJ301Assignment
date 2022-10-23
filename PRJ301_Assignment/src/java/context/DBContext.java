/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package context;

import java.sql.Connection;
import java.sql.DriverManager;



/**
 *
 * @author Admin
 */
public class DBContext {
    public Connection getConnection() throws Exception {
        String url = "jdbc:sqlserver://" + serverName + ":" + portNumber + "\\" + intance + ";databaseName=" + dbName;
        if (intance == null || intance.trim().isEmpty()) {
            url = "jdbc:sqlserver://" + serverName + ":" + portNumber + ";databaseName=" + dbName;
        }
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        return DriverManager.getConnection(url, userID, password);
                
        //jdbc:sqlserver://localhost\SQLEXPRESS01:1433;databaseName=master [sa on db_accessadmin]
        //jdbc:sqlserver://localhost\SQLEXPRESS01:1433;databaseName=Student_CV [sa on Default schema]
    }
    private final String serverName = "localhost\\SQLEXPRESS01";
    private final String dbName = "PRJ301_FALL2022_Assignment";
    private final String portNumber = "1433";
    private final String intance = "";
    private final String userID = "sa";
    private final String password = "sa";
    
    
    public static void main(String[] args) {
        System.out.println("hello");
        try {
            DBContext d = new DBContext();
            if (d.getConnection() != null) {
                System.out.println("ok");
            } else {
                System.out.println("non");
            }
        } catch (Exception e) {
        }
    }
}
