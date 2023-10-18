
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
        
      // Alumno Juan=new Alumno(1,12323,"Luna"," Juan Pedro",LocalDate.of(1980,4,25),true);
       //AlumnoData alu=new AlumnoData();
     //  alu.guardarAlumno(Juan);
     //alu.modificarAlumno(Juan);
     
     //alu.eliminarAlumno(1);
     //Alumno alumnoEncontrado=alu.buscarAlumnoPorDni(55846254);
     //if(alumnoEncontrado!=null){
       // System.out.println("dni"+alumnoEncontrado.getDni());
        //System.out.println("apellido"+ alumnoEncontrado.getApellido());
        //}
        AlumnoData alu=new AlumnoData();
        for(Alumno alumno:alu.listarAlumnos()){
            System.out.println(alumno.getDni());
            System.out.println(alumno.getApellido());
            System.out.println(alumno.getFechaNac());
        }
    }}

