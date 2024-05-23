package practicaficheros;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.TreeSet;
import java.util.Scanner;

public class App {

    public TreeSet<Usuario> usuarios = new TreeSet<>();

    public File archivo = new File("./fichero.txt");
    public ObjectInputStream reader;
    public ObjectOutputStream writer;

    public void createUser(String name, String apellidos, int edad, String email) {
        Usuario usuario = new Usuario(name, apellidos, edad, email);
        boolean anade = usuarios.add(usuario);
        if (anade) {
            System.out.println("Usuario anadido");
        } else {
            System.out.println("El usuario ya existe");
        }
    }

    public void userCreation(Scanner teclado) {
        System.out.println("Introduzca nombre");
        String name = teclado.nextLine();
        System.out.println("Introduzca apellidos");
        String apellidos = teclado.nextLine();
        System.out.println("Introduzca edad");
        int edad = teclado.nextInt();
        teclado.nextLine(); // Consumir el salto de línea
        System.out.println("Introduzca correo electronico");
        String email = teclado.nextLine();
        createUser(name, apellidos, edad, email);
    }

    public void removeUser(String name) {
        usuarios.removeIf(user -> user.getName().equals(name));
        return;
    }

    public void showUsers() {
        for (Usuario usuario : usuarios) {
            System.out.println(usuario);
        }
    }

    public void saveChanges() {
        try {
            if (usuarios != null) {
                writer = new ObjectOutputStream(new FileOutputStream(archivo));
                writer.writeObject(usuarios);
                writer.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void verifyFiles() {
        try {
            if (!archivo.exists()) {
                archivo.createNewFile();
            }
            reader = new ObjectInputStream(new FileInputStream(archivo));
            Object objectToCast = reader.readObject();
            if (objectToCast instanceof TreeSet) {
                usuarios = (TreeSet<Usuario>) objectToCast;
            } else {
                throw new IOException("Data file is not TreeSet");
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public int getOption(Scanner teclado) {
        int option = -1;
        while (true) {
            if (teclado.hasNextInt()) {
                option = teclado.nextInt();
                teclado.nextLine(); // Consumir el salto de línea
                if (option >= 1 && option <= 4) {
                    break;
                } else {
                    System.out.println("Valor incorrecto, por favor introduce un número entre 1 y 4.");
                }
            } else {
                System.out.println("Valor incorrecto, por favor introduce un número.");
                teclado.next(); // Consumir la entrada no válida
            }
        }
        return option;
    }

    public void showOptions() {
        System.out.println("1. Añadir usuario");
        System.out.println("2. Eliminar usuario");
        System.out.println("3. Listar Usuarios");
        System.out.println("4. Salir");
    }

    public static void main(String[] args) {
        App menu = new App();
        menu.verifyFiles();
        Scanner teclado = new Scanner(System.in);
        int exitDoor = 0;
        while (exitDoor == 0) {
            menu.showOptions();
            int option = menu.getOption(teclado);
            switch (option) {
                case 1:
                    menu.userCreation(teclado);
                    break;
                case 2:
                    System.out.println("Introduzca el nombre del usuario a eliminar:");
                    String nameToRemove = teclado.nextLine();
                    menu.removeUser(nameToRemove);
                    break;
                case 3:
                    menu.showUsers();
                    break;
                case 4:
                    exitDoor = 1;
                    break;
                default:
                    System.out.println("No ha podido ser");
                    break;
            }
            menu.saveChanges();
        }
        teclado.close();
    }
}
