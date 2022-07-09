/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package com.mycompany.claseconexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;


public class ClaseConexion {

     Connection conectar;
    
    String Usuario="root";
    String Contraseña=" ";//Agregar contraseña
    String bd="login";
    String ip="127.0.0.1";
    String puerto="3306";
    
    
    String cadena ="jdbc:mysql://"+ip+":"+puerto+"/"+bd;
    
    
    public Connection estableceConexion(){
        try{
        
                Class.forName("com.mysql.jdbc.Driver");
                conectar = DriverManager.getConnection(cadena,Usuario,Contraseña);
               JOptionPane.showMessageDialog(null, "Se conecto correctamente a la BASE de DATOS");//comentar esta linea despues de haber realizado la primera conexion
        
        } catch (ClassNotFoundException | SQLException e){
            JOptionPane.showMessageDialog(null, "Problemas en la conexion"+e.toString());
        }//muestra error de coneccion
    return conectar;
    }
}
