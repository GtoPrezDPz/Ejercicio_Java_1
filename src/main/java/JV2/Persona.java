package JV2;

import jdk.swing.interop.SwingInterOpUtils;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
@Data @NoArgsConstructor @AllArgsConstructor

public class Persona {
    private Optional<String> nombre;
    private Optional<String> poblacion;
    private Optional<Integer> edad;

    public static void main(String[] args) throws IOException {
        ArrayList<Persona> Personas = new ArrayList<Persona>();
        File file = new File("C:/Users/gustavo.perez/Desktop/Projects/Ejercicio_Java_1/src/main/java/Documentos/personas.txt");
        BufferedReader br = new BufferedReader(new FileReader(file));
        String line;
        while ((line = br.readLine()) != null) {
            String[] datos =  line.split(":");
            Persona persona;
            if( datos.length >= 3){
                persona = new Persona(Optional.ofNullable(datos[0]), Optional.ofNullable(datos[1]), Optional.of(Integer.parseInt(datos[2])));
            }else{
                persona = new Persona(Optional.ofNullable(datos[0]), Optional.ofNullable(datos[1]), Optional.of(0));
            }
            Personas.add(persona);
        }
        br.close();
        AtomicInteger count = new AtomicInteger(1);
        Personas.stream().filter(p -> p.getEdad().isPresent())
                .filter(p -> p.getEdad().get() < 25)
                .filter(p -> p.getEdad().get() > 0).toList()
                .forEach( p -> System.out.println("Linea "+ count.getAndIncrement() + " Nombre: "+p.getNombre().orElse("Desconocido")+ " Ciudad "+ p.getPoblacion().orElse("Desconocido") + " Edad "+p.getEdad().orElse(null)));
    }
}



