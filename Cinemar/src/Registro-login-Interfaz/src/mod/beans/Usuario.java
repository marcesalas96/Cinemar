/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mod.beans;


public class Usuario {

    public static String getText() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    //Atributos
    private String Usuario;
    private String Contraseña;
    private String nombre;
    private String apellido;
    private String correo;
    private int Edad;
    
    //constructor

    public Usuario(String Usuario, String Contraseña,String nombre, String apellido, String correo, int Edad) {
        this.Usuario = Usuario;
        this.Contraseña = Contraseña;
        this.nombre = nombre;
        this.apellido = apellido;
        this.correo = correo;
        this.Edad = Edad;
    }

    //getter and setter
    public String getUsuario() {
        return Usuario;
    }

    public void setUsuario(String Usuario) {
        this.Usuario = Usuario;
    }

    public String getContraseña() {
        return Contraseña;
    }

    public void setContraseña(String Contraseña) {
        this.Contraseña = Contraseña;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public int getEdad() {
        return Edad;
    }

    public void setEdad(int Edad) {
        this.Edad = Edad;
    }
  
    
    
}
