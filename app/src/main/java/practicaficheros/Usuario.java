/*
*
* Usuario.java
*
* Creada el 23 may 2024, 0:33:22
*
* Desarrollada por Bluesadsilk en l'empresa Abastos el dia 23 may 2024
*
* Email de contacto: bluesadsilk@proton.me
*
*
* @autor Bluesadsilk
* @date 23 may 2024
*/
package practicaficheros;

import java.io.Serializable;
import java.util.Objects;

/**
 * Usuario
 */
public class Usuario implements Comparable<Usuario>, Serializable {
    private String name;
    private String apellidos;
    private int edad;
    private String emaill;

    public Usuario() {
    }

    public Usuario(String name, String apellidos, int edad, String emaill) {
        this.name = name;
        this.apellidos = apellidos;
        this.edad = edad;
        this.emaill = emaill;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getApellidos() {
        return this.apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public int getEdad() {
        return this.edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getEmaill() {
        return this.emaill;
    }

    public void setEmaill(String emaill) {
        this.emaill = emaill;
    }

    public Usuario name(String name) {
        setName(name);
        return this;
    }

    public Usuario apellidos(String apellidos) {
        setApellidos(apellidos);
        return this;
    }

    public Usuario edad(int edad) {
        setEdad(edad);
        return this;
    }

    public Usuario emaill(String emaill) {
        setEmaill(emaill);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Usuario)) {
            return false;
        }
        Usuario usuario = (Usuario) o;
        return Objects.equals(name, usuario.name) && Objects.equals(apellidos, usuario.apellidos)
                && edad == usuario.edad && Objects.equals(emaill, usuario.emaill);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, apellidos, edad, emaill);
    }

    @Override
    public String toString() {
        return "{" +
                " name='" + getName() + "'" +
                ", apellidos='" + getApellidos() + "'" +
                ", edad='" + getEdad() + "'" +
                ", emaill='" + getEmaill() + "'" +
                "}";
    }

    @Override
    public int compareTo(Usuario otroUsuario) {
        int result = Integer.compare(this.edad, otroUsuario.edad);

        if (result == 0) {
            result = this.name.compareTo(otroUsuario.name);
        }
        return result;
    }
}