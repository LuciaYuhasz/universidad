
package universidad;
import java.sql.Connection;
import java.time.LocalDate;
import java.time.Month;
import universidad.accesoADatos.AlumnoData;
import universidad.accesoADatos.Conexion;
import universidad.accesoADatos.InscripcionData;
import universidad.accesoADatos.MateriaData;

import universidad.entidades.Alumno;
import universidad.entidades.Inscripcion;
import universidad.entidades.Materia;
public class Universidad {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        

      /* Alumno Juan=new Alumno(1,12323,"Luna"," Juan Pedro",LocalDate.of(1980,4,25),true);
       AlumnoData alu=new AlumnoData();
     //  alu.guardarAlumno(Juan);
     //alu.modificarAlumno(Juan);
     
     alu.eliminarAlumno(1);
     
     Alumno alumnoEncontrado = alumn.buscarAlumno(2);
        if (alumnoEncontrado != null) {
            System.out.println("dni : " + alumnoEncontrado.getDni());
            System.out.println("apelido :" + alumnoEncontrado.getApellido());
            System.out.println("nombre  :" + alumnoEncontrado.getNombre());
            System.out.println("fecha de nacimiento : " + alumnoEncontrado.getFechaNac());
        }*/
      
       /* AlumnoData alumn = new AlumnoData();
        for (Alumno alumno :alumn.listarAlumnos()) {
            System.out.println(" Id de alumno : " + alumno.getIdAlumno());
            System.out.println(" DNI alumno : " + alumno.getDni());
            System.out.println(" Apellido del alumno : " + alumno.getApellido());
            System.out.println(" Nombre del alumno  : " + alumno.getNombre());
            System.out.println(" Fecha de nacimiento del alumno : " + alumno.getFechaNac());
            System.out.println("-------------------------------------------------------------------");
        }*/

//trabajamos con la clase materia

           /* Materia mat = new Materia(1, "lengua", 1, true);
            MateriaData desarrollo = new MateriaData(); // me mofifica a nombre de la materia
            desarrollo.agregarMateria(mat);
            System.out.println("Se agrego la materia : " + mat.getNombre());
            System.out.println("Se agrego la materia : " + mat.getAnioMateria());
            System.out.println("Se agrego la materia : " + mat.getIdMateria());
            desarrollo.modificarMateria(1, 1);
            System.out.println("  Id Materia modificada : "  + mat.getIdMateria());
            System.out.println(" Materia modificada : "  + mat.getNombre());
            System.out.println(" Año modificado : "  + mat.getAnioMateria());
            
            desarrollo.eliminarMateria(1);*/
           
         /*   MateriaData mat = new MateriaData();
        for (Materia materia :mat.listarMaterias()) {
            System.out.println(" Id de materia : " + materia.getIdMateria());
            System.out.println(" Nombre de la Materia : " + materia.getNombre());
            System.out.println("Año de la materia : " + materia.getAnioMateria());
            System.out.println("-------------------------------------------------------------------");
        }*/
         
         /*MateriaData mat =new MateriaData();
          Materia materiaEncontrada = mat.buscarMateria(3);
         System.out.println("nombre :" + materiaEncontrada.getNombre());
         System.out.println("año : " + materiaEncontrada.getAnioMateria());*/
             
// trabajamos con la clase inscripcion
//
//          AlumnoData alum = new AlumnoData();
//           MateriaData mat = new MateriaData();
//           InscripcionData ins = new InscripcionData();
//           Alumno Victoria = alum.buscarAlumno(2);
//           Materia lab = mat.buscarMateria(2);
//           Inscripcion inscp= new Inscripcion(9, Victoria , lab);
////           ins.guardarInscripcion(inscp);
////          ins.modificarNota(6, 5, 4);
//           ins.borrarInscripcion(2, 2);
////     
           AlumnoData ad= new AlumnoData();
           MateriaData md=new MateriaData();
           InscripcionData id= new InscripcionData();
           
           Alumno Mario=ad.buscarAlumno(8);
           Materia mate=md.buscarMateria(2);
           Inscripcion insc=new Inscripcion(8,Mario, mate);
           //        id.guardarInscripcion(insc);

            id.modificarNota(9,8,2);


            //  id.borrarInscripcion(8,2);

    }   
    
}
      

