package upeu.edu.pe.rest2018;

public class Usuario {

    private String idusuario;
    private String nombres;
    private String apellidos;
    private String usuario;
    private String clave;
    private String estado;

    public Usuario(){

    }

    public Usuario(String idusuario, String nombres, String apellidos, String usuario, String clave, String estado){
        this.idusuario = idusuario;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.usuario = usuario;
        this.clave = clave;
        this.estado = estado;
    }
    public Usuario( String idusuario, String nombres, String apellidos, String usuario, String clave){
        this.idusuario = idusuario;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.usuario = usuario;
        this.clave = clave;
    }

    public String getIdusuario(){return idusuario;}

    public void  setIdusuario(String idusuario){
        this.idusuario = idusuario;
    }

    public String getNombres(){return nombres;}

    public void setNombres(String nombres){
        this.nombres = nombres;
    }

    public String getApellidos(){return apellidos;}

    public void setApellidos(String apellidos){
        this.apellidos = apellidos;
    }

    public String getUsuario(){return usuario;}

    public void setUsuario(String usuario){
        this.usuario = usuario;
    }

    public String getClave(){return clave;}

    public void setClave(String clave){
        this.clave = clave;
    }

    public String getEstado(){return estado;}

    public void setEstado(String estado){
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "Nombres: "+ nombres + " "+ apellidos + '\n' +
                "Usuario: " +  usuario ;
        /*return "Usuario{" +
                "idusuario=" + idusuario +
                ", nombres='" + nombres + '\'' +
                ", apellidos='" + apellidos + '\'' +
                ", usuario='" + usuario + '\'' +
                ", clave='" + clave + '\'' +
                ", estado='" + estado + '\'' +
                '}';*/
    }
}
