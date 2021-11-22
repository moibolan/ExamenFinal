package com.progra.productos.utils;

import java.sql.Connection;
import java.sql.DriverManager;

public class Conexion {

    public static Connection getConnection(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.
                    getConnection("jdbc:mysql://localhost:3306/examen-final", "root", "1234");
            return con;
        }catch(Exception ex){
            System.out.println(ex);
            return null;
        }
    }
}
