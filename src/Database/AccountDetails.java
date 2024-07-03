/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Database;

import java.sql.*;

/**
 *
 * @author Gaurab
 */
public class AccountDetails {

    Statement stmt;
    Connection conn;

    public static void main(String[] Args) {

    }

    public void makeConnection() {
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/", "root", "9848688463"); Statement stmt = conn.createStatement();) {
            stmt.executeUpdate("create database if not exists accountDetails;");
            stmt.executeUpdate("use accountDetails;");
            stmt.executeUpdate("create table if not exists Data(email varchar(255),username varchar(255),password varchar(255));");

        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public boolean insertCredentials(String email, String user, String password) {
        boolean taken = false;
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/", "root", "9848688463");
            stmt = conn.createStatement();
            stmt.executeUpdate("use accountDetails");
            ResultSet rs = stmt.executeQuery("select * from Data where email = \'" + email + "';");
            if (rs.next()) {
                taken = true;
            } else {
                stmt.executeUpdate("insert into Data values(\'" + email + "',\'" + user + "',\'" + password + "');");
            }

        } catch (SQLException e) {
            System.out.println(e);
        }
        return taken;
    }

    public int checkCredentials(String email, String password) {
        boolean emailValidity = false;
        boolean passValidity = false;

        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/", "root", "9848688463");
            stmt = conn.createStatement();
            stmt.executeUpdate("use accountDetails");
            ResultSet validEmail = stmt.executeQuery("select * from Data where email = \'" + email + "'");
            emailValidity = validEmail.next();
            ResultSet pass = stmt.executeQuery("select * from Data where password = \'" + password + "'");
            passValidity = pass.next();
        } catch (SQLException e) {
            System.out.println(e);
        }
        if (!emailValidity) {
            return 0;
        } else if (!passValidity) {
            return -1;
        } else {
            return 1;
        }

    }
}
