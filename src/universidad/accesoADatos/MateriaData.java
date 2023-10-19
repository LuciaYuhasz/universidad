
package universidad.accesoADatos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import universidad.entidades.Materia;


public class MateriaData {
    
private Connection con = null;
   public MateriaData(){
       con = Conexion.getConexion();
   }
   public void agregarMateria(Materia materia){ //agrego materia
        try {
            String sql = "INSERT INTO materia (nombre, año,estado) VALUES (?,?,?)";
            PreparedStatement ps = con.prepareStatement( sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1,materia.getNombre());
            ps.setInt(2,materia.getAnioMateria());
            ps.setBoolean(3, true);
             ps.executeUpdate();
            ResultSet rs= ps.getGeneratedKeys();
            if (rs.next()) {
                materia.getIdMateria();
                JOptionPane.showMessageDialog(null,"Materia Agregado exitosamente");
            }
            ps.close();
        
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"Error al conectar con la tabla inscripcion ");
        }

}
   public void modificarMateria(int idMateria,int año){ // modifico materia
       String sql= "UPDATE materia SET año =?"
               + "WHERE idMateria = ? ";
        try {
            PreparedStatement ps= con.prepareStatement(sql);
            ps.setInt(2, año);
            ps.setInt(1, idMateria);
    
            int modificado=ps.executeUpdate();
           
            if (modificado>0) {
                JOptionPane.showMessageDialog(null,"Materia modificada");
            }
            ps.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al acceder a tabla materia" + ex.getMessage());
        }
   }
   
   public void eliminarMateria(int id){ // elimino materia
       String sql= "UPDATE materia SET estado = 0 WHERE idMateria=?";
        try {
            PreparedStatement ps= con.prepareStatement(sql);
            
            ps.setInt(1, id);
            int modificado=ps.executeUpdate();
           
            if (modificado == 1) {
                JOptionPane.showMessageDialog(null,"Materia eliminada");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al acceder a tabla materia");
        }
   }
   
   public Materia buscarMateria(int id){  //busco materia por id
     String sql= "SELECT  nombre,año  FROM materia WHERE idMateria= ? AND estado = 1";
        Materia materia = null;
         try {
            PreparedStatement ps= con.prepareStatement(sql);
            ps.setInt(1,id);
            ResultSet rs = ps.executeQuery();
            
            if (rs.next()) {
                materia = new Materia();
                materia.setIdMateria(id);
                materia.setNombre(rs.getString("nombre"));
                materia.setAnioMateria(rs.getInt("año"));
                materia.setEstado(true);
            }else{
                JOptionPane.showMessageDialog(null,"Materia No encontrada");
                ps.close();
            }
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al acceder a tabla materia");
        }
       return materia;
       
   }
   
   
   public List<Materia>listarMaterias(){
        
            String sql = "SELECT idMateria,nombre,año FROM materia WHERE estado = 1";
            ArrayList<Materia>materias=new ArrayList<>();
            try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs=ps.executeQuery();
            while (rs.next()) {
                Materia materia = new Materia();
                materia.setIdMateria(rs.getInt("idMateria"));
                materia.setNombre(rs.getString("nombre"));
                materia.setAnioMateria(rs.getInt("año"));
                materias.add(materia);
                
            }
        } catch (SQLException ex) {
           JOptionPane.showMessageDialog(null,"Error en la tabla materia");
        }
     return materias;
}
}
