package mod.logic;

import mod.beans.Usuario;
import mod.Reg.UsuarioReg;


/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

public class Usuario_logic {
    private static final UsuarioReg usuarioreg = new UsuarioReg();

    public static UsuarioReg getUsuarioReg() {
        return usuarioreg;
    }
    
    
    public static boolean autentificar(String Usuario, String Contraseña){
        if (obtener(Usuario)!=null){
            Usuario usuarioConsulta = obtener(Usuario);
            return usuarioConsulta.getUsuario().equals(Usuario)&&usuarioConsulta.getContraseña().equals(Contraseña);
        }else{
            return false;
        }
    }
    
    public static boolean insertar(Usuario usuario){
        return usuarioreg.insertar(usuario);
    }
    public static boolean modificar(Usuario usuario){
         return usuarioreg.modificar(usuario);
    }
    public static boolean eliminar(String usuario){
         return usuarioreg.eliminar(usuario);
    }
    public static Usuario obtener(String usuario){
         return usuarioreg.obtener(usuario);
    }
    
    
}
