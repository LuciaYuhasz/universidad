
package universidad;
import java.sql.Connection;
import java.time.LocalDate;
import java.time.Month;
import universidad.accesoADatos.AlumnoData;
import universidad.accesoADatos.Conexion;

import universidad.entidades.Alumno;
public class Universidad {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Alumno Ian=new Alumno(12323,"Luna", "Juan Pedro",LocalDate.of(1980,4,25),false); 
      // Alumno Juan=new Alumno(9,12323,"Luna", "Juan Pedro",LocalDate.of(1980,4,25),true);
       AlumnoData alu=new AlumnoData();
       alu.guardarAlumno(Ian);
   // alu.modificarAlumno(Juan);
     
    // alu.eliminarAlumno(8);
    
        }
        
    }

