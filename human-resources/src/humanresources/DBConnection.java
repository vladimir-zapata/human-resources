package humanresources;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DBConnection {

    String strConexionDB = "JDBC:sqlite:C:/Users/Jade0/OneDrive/Documentos/DB/nomina.s3db";
    Connection con = null;

    public DBConnection() {
        
        try {
        Class.forName("org.sqlite.JDBC");
        con = DriverManager.getConnection(strConexionDB);
        
        System.out.println("Conexion establecida...");
        
        } catch (Exception e)
        {  
        
            System.out.println("Error al conectar..." + e);
           
        }
    
    }
    
    public int ejecutarSententeciaSQL (String strSentenciaSQL){
    
        try {
        
            PreparedStatement pstm = con.prepareStatement(strSentenciaSQL);
            pstm.execute();
            return 1;
            
        } catch (SQLException e) {
            System.out.println(e);
            return 0;
            
        }
    }
    
    public ResultSet ConsultarRegistros(String strSentenciaSQL){
    try {

       PreparedStatement pstm = con.prepareStatement(strSentenciaSQL);
       ResultSet respuesta = pstm.executeQuery();
       return respuesta;
       
      } catch (Exception e){
          System.out.println(e);
          return null;
      }
}

    public PreparedStatement prepareStatement(String insert_into_Empleados_IDNombreApellidoDir) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public Connection getConn() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}