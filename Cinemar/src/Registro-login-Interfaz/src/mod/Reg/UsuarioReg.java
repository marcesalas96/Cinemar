/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mod.Reg;

import java.util.ArrayList;
import java.util.List;
import mod.beans.Usuario;

/**
 *buscar, insetar, modificar y eliminar
 */
public class UsuarioReg {
    private final List<Usuario> usuarios;
    //constructor

    public UsuarioReg() {
        usuarios = new ArrayList<>();
    }
    
    public int buscar(String Usuario){
        int n = -1;
        for(int i=0; i< usuarios.size(); i++){
            if(usuarios.get(i).getUsuario().equals(Usuario)){
                n=i;
                break;
            }
        }
        return n;
    }
    
    public boolean insertar(Usuario usuario){
        if(buscar(usuario.getUsuario())==-1){
            usuarios.add(usuario);//agregando usuario
            return true;
        }else{
            return false;
        }
    }
    
    public boolean modificar(Usuario usuario){
        if(buscar(usuario.getUsuario()) !=-1){
            Usuario usuarioaux= obtener(usuario.getUsuario());
            
            usuarioaux.setContraseña(usuario.getContraseña());
            usuarioaux.setNombre(usuario.getNombre());
            usuarioaux.setApellido(usuario.getApellido());
            usuarioaux.setCorreo(usuario.getCorreo());
            usuarioaux.setEdad(usuario.getEdad());
            
            return true;
        }else{
            return false;
        }
    }
    
    public boolean eliminar(String usuario){
         if(buscar(usuario) !=-1){
            usuarios.remove(buscar(usuario));
            return true;
        }else{
            return false;
        }
    }
    
    public Usuario obtener(String usuario){
    //busca en toda la lista disponible ve la posicion y luego devuelve
        if(buscar(usuario)!=-1){
            return usuarios.get(buscar(usuario));
        }else{
            return null;
        }
    
    }
    
    
}



