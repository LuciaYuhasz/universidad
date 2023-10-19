/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package universidad.accesoADatos;

import java.sql.*;
import javax.swing.JOptionPane;
import universidad.entidades.Materia;

/**
 *
 * @author norma
 */
public class MateriaData {
     private Connection con = null;

    public MateriaData() {
         con = Conexion.getConexion();
    }
    public void guardarMateria(Materia materia){  //agrego materia sentencia insert
       String sql= "INSERT INTO materia(nombre, año, estado)" 
               + "VALUES(?,?,?)";
        try {
            PreparedStatement ps= con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
            ps.setString(1,materia.getNombre());
            ps.setInt (2, materia.getAnioMateria());
            ps.setBoolean(3, materia.isEstado());
            ps.executeUpdate();
            ResultSet rs= ps.getGeneratedKeys();
            if (rs.next()) {
                materia.setIdMateria(rs.getInt(1));
                JOptionPane.showMessageDialog(null,"materia Agregada exitosamente");
                
            }
            ps.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al acceder a tabla materia"+ex);
        }
   }
   
public Materia buscarMateria(int id){  //lista alumnos con id = 2 sentencia select
        String sql= "SELECT  nombre, año FROM materia WHERE idMateria= ? AND estado = 1";
        Materia materia = null;
         try {
            PreparedStatement ps= con.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            
            if (rs.next()) {
                materia =new Materia();
                materia.setIdMateria(id);
                materia.setNombre(rs.getString("nombre"));
                materia.setAnioMateria(rs.getInt("Año"));
                materia.setEstado(true);
            }else{
                JOptionPane.showMessageDialog(null,"materia No encontrado");
                ps.close();
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al acceder a tabla materia");
        }
        
        return materia;

    }

public void modificarMateria(Materia materia){ //mofifico materia sentencia update
          
      String sql="UPDATE materia SET nombre= ?, año= ? "
              +" WHERE idMateria= ? ";
      
        try {
            PreparedStatement ps= con.prepareStatement(sql);
            ps.setString(1, materia.getNombre());
            ps.setInt(2,materia.getAnioMateria());
            ps.setInt(3, materia.getIdMateria());
            
            int modificar= ps.executeUpdate();
            
            if(modificar==1){
                JOptionPane.showMessageDialog(null,"materia modificado");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla materia"+ex);
        }
  }

 public void eliminarMateria(int id){
      String sql = "UPDATE materia SET estado =0 WHERE idMateria=?";
       try {
            PreparedStatement ps= con.prepareStatement(sql);
            
            ps.setInt(1, id);
           int eliminar= ps.executeUpdate();
           
           if(eliminar==1){
               JOptionPane.showMessageDialog(null,"materia eliminado");
           }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al aceder a la tabla materia");
        }
      
      
  }

}
