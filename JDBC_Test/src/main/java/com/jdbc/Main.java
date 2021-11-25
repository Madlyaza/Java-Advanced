package com.jdbc;

import com.mysql.cj.protocol.Resultset;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Main
{
    public static void main(String args[])
    {
        getPokemon();
    }

    private static void getPokemon()
    {
        try
        {
            Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/jdbc","root","");
            Statement stmt = conn.createStatement();
            System.out.println(stmt);
            ResultSet rs = stmt.executeQuery("SELECT * FROM pokemon");
            while(rs.next())
            {
                System.out.println("ID: " + rs.getInt(1) + " has the name " + rs.getString(2));
            }
            rs.close();
            stmt.close();
            conn.close();
        }
        catch(Exception ex)
        {
            System.out.println("Exception: " + ex);
        }
    }

    private static void uploadPokemon(String name)
    {
        try
        {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc","root","");
            Statement stmt = conn.createStatement();
            stmt.executeUpdate("INSERT INTO `Pokemon`(`Name`) VALUES ('" + name + "')");
        }
        catch(Exception ex)
        {
            System.out.println("Exception: " + ex);
        }

    }

}
