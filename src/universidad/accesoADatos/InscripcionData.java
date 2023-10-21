
package universidad.accesoADatos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
 private MateriaData md= new MateriaData();
 private AlumnoData ad=new AlumnoData();

    public InscripcionData() {
        this.con = Conexion.getConexion();
    }
    
    public void guardarInscripcion(Inscripcion inscp){ //inscribo alumno en materia
        String sql = " INSERT INTO inscripcion (idAlumno, idMateria,nota) VALUES (?,?,?)";
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
        String sql= "UPDATE inscripcion  SET nota =?"
               + "WHERE idAlumno=? AND idMateria=?";
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
        String sql = "DELETE FROM inscripcion WHERE idAlumno=? AND idMateria =?";
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
 
// public List<Materia> obtenerMateriasCursadas( int idAlumno){
//     ArrayList<Materia> materias= new ArrayList<>();
//     String sql + "SELECT inscripcion.idMateria, año FROM inscripcionmateria Where inscripcion.idMateria = materia.idMateria AND inscripcion.idAlumno = ? ";
//     
// } ME QUEDA TERMINAR ESTE CODIGO 
// 
 

