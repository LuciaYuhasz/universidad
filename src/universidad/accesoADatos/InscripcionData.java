
package universidad.accesoADatos;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import universidad.entidades.Alumno;
import universidad.entidades.Inscripcion;
import universidad.entidades.Materia;

public class InscripcionData {
    
 private Connection con = null;
 private MateriaData mt= new MateriaData();
 private AlumnoData ad =new AlumnoData();

    public InscripcionData() {
        this.con = Conexion.getConexion();
    }
    
    public void guardarInscripcion(Inscripcion inscp){ //inscribo alumno en materia
        String sql = " INSERT INTO inscripcion (idAlumno, idMateria,nota) VALUES (?,?,?) ";
         try {
             PreparedStatement ps= con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1,inscp.getAlumno().getIdAlumno());
            ps.setInt(2, inscp.getMateria().getIdMateria());
            ps.setDouble(3,inscp.getNota());
            ps.executeUpdate();
             ResultSet rs = ps.getGeneratedKeys();
             if (rs.next()) {
                 inscp.setIdInscripcion(rs.getInt(1));
                 JOptionPane.showMessageDialog(null, "ALUMNO INSCRIPTO CORRECTAMENTE");
             }
            ps.close();
         } catch (SQLException ex) {
             JOptionPane.showMessageDialog(null,"Error al conectar con tabla de inscripcion" + ex.getMessage());
         }
    }
    
    public void modificarNota(int idAlumno, int idMateria, double nota){ //modifico nota
        String sql= " UPDATE inscripcion  SET nota =? "
               + " WHERE idAlumno=? AND idMateria=? ";
         try {
             PreparedStatement ps= con.prepareStatement(sql);
             ps.setDouble(1, nota);
             ps.setInt(2, idAlumno);
             ps.setInt(3, idMateria);
              int modificado=ps.executeUpdate();
           
            if (modificado>0) {
                JOptionPane.showMessageDialog(null,"Nota modificada");
            }
            ps.close();
         } catch (SQLException ex) {
             JOptionPane.showMessageDialog(null,"Error al ingresar a la tabla inscripcion " + ex.getMessage());
         }
    }
    
    public void borrarInscripcion(int idAlumno,int idMateria){ // borro insripcion
        String sql = " DELETE FROM inscripcion WHERE idAlumno=? AND idMateria =?";
         try {
             PreparedStatement ps = con.prepareStatement(sql);
             ps.setInt(1, idAlumno);
             ps.setInt(2, idMateria);
             int borrado = ps.executeUpdate();
             if (borrado!=0) {
                 JOptionPane.showMessageDialog(null,"Inscripcion borrada");
             }
             ps.close();
         } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"Error al ingresar a la tabla inscripcion " + ex.getMessage()); 
         }
    }
    public void actualizarNota(int idAlumno,int idMateria,double nota){
      String sql=" UPDATE inscripcion  SET nota =?"
               + " WHERE idAlumno=? AND idMateria=?";
     try {
         PreparedStatement ps= con.prepareStatement(sql);
         ps.setDouble(1,nota);
         ps.setInt(2, idAlumno);
         ps.setInt(3, idMateria);
         int fila= ps.executeUpdate();
         if (fila>0) {
          JOptionPane.showMessageDialog(null," Nota actualizada ");
         }
         ps.close();
     } catch (SQLException ex) {
        JOptionPane.showMessageDialog(null,"Error al ingresar a la tabla inscripcion " + ex.getMessage());
        }
    }

    public void borrarInscricion(int idAlumno,int idMateria){
        String sql=  " DELETE FROM inscripcion WHERE idAlumno = ? , idMateria = ? ";
     try {
         PreparedStatement ps= con.prepareStatement(sql);
         ps.setInt(1, idAlumno);
         ps.setInt(2, idMateria);
         
         int fila= ps.executeUpdate();
         if (fila>0) {
             JOptionPane.showMessageDialog(null," Incripcion borrada exitosamente");
          
         }
         ps.close();
     } catch (SQLException ex) {
         JOptionPane.showMessageDialog(null,"Error al conectar con tabla de inscripcion" + ex.getMessage());
         }
    }
    
    
    public List<Inscripcion> obtenerInscipciones(){
    
         ArrayList<Inscripcion> listaInscripcion =new ArrayList<>();
         String sql=  " SELEcT * FROM inscripcion ";
          try {
         
         PreparedStatement ps= con.prepareStatement(sql);
         ResultSet rs=ps.executeQuery();
         
         while(rs.next()){
             Inscripcion ins= new Inscripcion();
             ins.setIdInscripcion(rs.getInt("idInscripcion")) ;
             Alumno alu= ad.buscarAlumno(rs.getInt("idAlumno"));
             Materia mat= mt.buscarMateria(rs.getInt("idMateria"));
             ins.setAlumno(alu);
             ins.setMateria(mat);
             ins.setNota(rs.getDouble("nota"));
             listaInscripcion.add(ins);
             
         }
         ps.close();
         
     } catch (SQLException ex) {
             JOptionPane.showMessageDialog(null,"Error al conectar con tabla de inscripcion"+ ex);
  }
     return listaInscripcion ;
    }

}
