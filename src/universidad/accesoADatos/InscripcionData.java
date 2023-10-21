
package universidad.accesoADatos;

<<<<<<< HEAD
import java.sql.*;
=======
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
>>>>>>> 8211c0566627befeca2c1147ca95842e800cedf1
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
<<<<<<< HEAD
 private MateriaData mt= new MateriaData();
 private AlumnoData ad =new AlumnoData();
=======
 private MateriaData md= new MateriaData();
 private AlumnoData ad=new AlumnoData();
>>>>>>> 8211c0566627befeca2c1147ca95842e800cedf1

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
    
<<<<<<< HEAD
    
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

=======
    public List<Inscripcion>obtenerInscripciones(){
     ArrayList<Inscripcion> cursadas= new ArrayList<>();
     String sql="SELECT * FROM inscripcion";
     try {
         PreparedStatement ps=con.prepareStatement(sql);
         
         ResultSet rs= ps.executeQuery();
         while(rs.next()){
             
             
             Inscripcion insc= new Inscripcion();
             insc.setIdInscripcion(rs.getInt("idInscripcion"));
             
             Alumno alu= ad.buscarAlumno(rs.getInt("idAlumno"));
             Materia mat= md.buscarMateria(rs.getInt("idMateria"));
             insc.setAlumno(alu);
             insc.setMateria(mat);
             insc.setNota(rs.getDouble("nota"));
             cursadas.add(insc);  
             
         }
         
         ps.close();
     } catch (SQLException ex) {
         JOptionPane.showMessageDialog(null,"Error al acceder a la tabla inscripcion");
     }
     
     return cursadas;
     
      
 }  
    
 
    public List<Inscripcion>obtenerInscripcionesPorAlumno(int idAlumno){
     ArrayList<Inscripcion> cursadas= new ArrayList<>();
     String sql="SELECT * FROM inscripcion WHERE idAlumno = ?";
     try {
         PreparedStatement ps=con.prepareStatement(sql);
         ps.setInt(1, idAlumno);
         ResultSet rs= ps.executeQuery();
         while(rs.next()){
             
             
             Inscripcion insc= new Inscripcion();
             insc.setIdInscripcion(rs.getInt("idInscripcion"));
             
             Alumno alu= ad.buscarAlumno(rs.getInt("idAlumno"));
             Materia mat= md.buscarMateria(rs.getInt("idMateria"));
             insc.setAlumno(alu);
             insc.setMateria(mat);
             insc.setNota(rs.getDouble("nota"));
             cursadas.add(insc);  
             
         }
         
         ps.close();
     } catch (SQLException ex) {
         JOptionPane.showMessageDialog(null,"Error al acceder a la tabla inscripcion");
     }
     
     return cursadas;
 }
 
     public List<Materia> obtenerMateriasCursadas( int idAlumno){
         
     ArrayList<Materia> materias= new ArrayList<>();
     
     String sql = "SELECT inscripcion.idMateria, año FROM inscripcion,"
    +"materia WHERE inscripcion.idMateria = materia.idMateria AND inscripcion.idAlumno = ?; ";
     try {
         PreparedStatement ps= con.prepareStatement(sql);
         ps.setInt(1, idAlumno);
         ResultSet rs=ps.executeQuery();
         while(rs.next()){
         Materia materia=new Materia ();
         materia.setIdMateria(rs.getInt("idMateria"));
         materia.setNombre(rs.getString("nombre"));
         materia.setAnioMateria(rs.getInt("año"));
         
         materias.add(materia);
     }
         ps.close();
         
         
         
     } catch (SQLException ex) {
        JOptionPane.showMessageDialog(null,"RError al acceder a la tabla Inscripcion");
     }
        return materias;

}

    public List<Materia>obtenerMateriasNOCursadas(int idAlumno){
    
    ArrayList<Materia> materias=new ArrayList<>();
    
    String sql = "SELECT * FROM materia WHERE estado = 1 AND idMateria not in "
            + "(SELECT idMateria FORM inscripcion WHERE idAlumno =?) ";
    
            
     try {
         
     
         PreparedStatement ps = con.prepareStatement(sql);
    
            ps.setInt(1,idAlumno);
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                
                Materia materia= new Materia();
                materia.setIdMateria(rs.getInt("idMateria"));
                materia.setNombre(rs.getString("nombre"));
                materia.setAnioMateria(rs.getInt("año"));
                materias.add(materia);
                
            }
            ps.close();
             } catch (SQLException ex) {
       JOptionPane.showMessageDialog(null, "Error ");
     }
     return materias;
}

    public List<Alumno> obtenerAlumnosXMateria( int idMateria){
        ArrayList<Alumno> alumnosMateria= new ArrayList<>();
        String sql = "SELECT a.idAlumno, dni, nombre, apellido, fechaNacimiento, estado"
                +"FROM inscripcion i, alumno a WHERE i.idAlumno = a.idAlumno AND idMateria = ? AND a.estado = 1";
     try {
         PreparedStatement ps=con.prepareCall(sql);
         
         ps.setInt(1, idMateria);
         
         ResultSet rs=ps.executeQuery();
         while(rs.next()){
             Alumno alumno= new Alumno();
             alumno.setIdAlumno(rs.getInt("idAlumno"));
             alumno.setApellido(rs.getString("idApellido"));
             alumno.setNombre(rs.getString("nombre"));
             alumno.setFechaNac(rs.getDate("fechaNacimiento").toLocalDate());
             alumno.setEstado(rs.getBoolean("estado"));
             alumnosMateria.add(alumno);
             
             
         }
         
         ps.close();
     } catch (SQLException ex) {
       JOptionPane.showMessageDialog(null,"Error al acceder a la tabla inscripcion");
     }
     return alumnosMateria;
    }



>>>>>>> 8211c0566627befeca2c1147ca95842e800cedf1
}
