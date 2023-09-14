package com.silverlink.Utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Datasource {

    public static String connURL = "jdbc:sqlserver://192.168.1.2\\SQLS19;" +
            "database=TEST;" +
            "user=sa;" +
            "password=;" + //Añadir contraseña para loguearse
            "encrypt=true;" +
            "trustServerCertificate=true;";

//    public static String connURL = "jdbc:sqlserver://SILVERLINK\\SQLEXPRESS;" +
//            "database=TEST;" +
//            "user=ninorokr;" +
//            "password=;" + //Añadir contraseña para loguearse vcmk
//            "encrypt=true;" +
//            "trustServerCertificate=true;";

    public static Connection conn;

    public static void open() {
        try {
            conn = DriverManager.getConnection(connURL);
            System.out.println("Conectado");
        } catch (SQLException sqle) {
            System.out.println("No se pudo conectar a la BD");
            sqle.printStackTrace();
        }
    }

//    public void close() {
//        try {
//            if (conn != null) {
//                conn.close();
//            }
//        } catch (SQLException e) {
//            System.out.println("No se pudo cerrar la conexión :?");
//        }
//    }

}
