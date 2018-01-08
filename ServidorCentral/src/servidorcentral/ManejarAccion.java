package servidorcentral;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Leonardo
 */
public class ManejarAccion {
    
    private static Connection _conn = null;
    private static ResultSet _resultSet;
    String _query = "";
    
    /**
     * Constructor de manejador de acciones
     */
    public ManejarAccion(){
        
        try
        {
            
            Class.forName( Registro.POSTGRE_BD_CLASS_FOR_NAME );
            _conn = DriverManager.getConnection( Registro.POSTGRE_BD_URL,
                    Registro.POSTGRE_BD_USER, Registro.POSTGRE_BD_PASSWORD );
            
        }
        catch (ClassNotFoundException e)
        {
            
            System.out.println("Clase de Registro no encontrada:"+e.getMessage());
            
        }
        catch (SQLException e)
        {
            
            System.out.println("Error de Conexion BD: "+e.getMessage());
            
        }
        
    }
    
    public String accionNumeroDeDescargasXVideo(){
        
        String _listaVideos = "";
        
        try {
            
            _query = "";
            
            
            Statement _st = _conn.createStatement();
            _resultSet = _st.executeQuery(_query);
            
            while(_resultSet.next()){
                
                _listaVideos = _listaVideos +"@";

            }
            
            return _listaVideos;
            
        } catch (Exception e) {
            
            System.out.println("Error al consultar numero de descargas de cliente");
            return null;
            
        }
        
    }
    
    /**
     * Metodo para consultar los 10 clientes con mas descargas de videos
     * @return lista de los 10 clientes con mas descargas
     */
    public String accionNumeroClientesDescargas(){
        
        String _listaClientes = "";
        
        try {
            
            _query = "select cli_nombre, cli_num_descargas from cliente "
                    + "order by (cli_num_descargas, cli_nombre) desc limit 10";
            
            
            Statement _st = _conn.createStatement();
            _resultSet = _st.executeQuery(_query);
            
            while(_resultSet.next()){
                
                _listaClientes = _listaClientes + _resultSet.getString("cli_nombre")
                        +":"+_resultSet.getString("cli_num_descargas")+"@";

            }
            
            return _listaClientes;
            
        } catch (Exception e) {
            
            System.out.println("Error al consultar numero de descargas de cliente");
            return null;
            
        }
        
        
        
    }
    
    /**
     * Metodo para obtener los 10 videos mas descargados
     * @return Lista de los 10videos mas descargados
     */
    public String accionNumeroVideosDescargados(){
        
        String _listaVideos = "";
        
        try {
            
            _query = "select vid_nombre, vid_num_descargados,"
                    + "(select cli_nombre from cliente where cli_id=fk_usuario) as cliente "
                    + "from video "
                    + "order by (vid_num_descargados,vid_nombre) desc limit 10";
            
            Statement _st = _conn.createStatement();
            _resultSet = _st.executeQuery(_query);
            
            while(_resultSet.next()){
                
                _listaVideos = _listaVideos +_resultSet.getString("vid_nombre")
                        +":"+_resultSet.getString("vid_num_descargados")+":"+
                        _resultSet.getString("cliente")+"@";

            }
            return _listaVideos;
            
        } catch (SQLException e) {
            
            System.out.println("Error en al consulta  a BD: "+e.getMessage());
            return null;
            
        }
        
        
        
    }
    
    /**
     * Actualiza el contador de descargas hechas a un video al ser este descargado
     * @param id Nombre del video
     */
    public void contadorDescargasVideo(String id){
        
        try {
            
            _query = "update video set vid_num_descargados = vid_num_descargados +1 "
                + "where vid_id="+id;
            
            Statement _st = _conn.createStatement();
            _resultSet = _st.executeQuery(_query);
            
        } catch (Exception e) {
            
            System.out.println("SQL EXCEPTION");    
            
        }
        
    }
    
    /**
     * Actualiza el contador de descargas hechas al cliente al ser un video 
     * descargado
     * @param idDueño  id del Dueño del Video
     */
    public void contadorDescargasCliente(int idDueño){
        
        try {
            
            _query = "update cliente set cli_num_descargas = cli_num_descargas +1 "
                    + "where cli_id ="+idDueño;
            
            Statement _st = _conn.createStatement();
            _resultSet = _st.executeQuery(_query);
            
        } catch (SQLException e) {
            System.out.println("SQL EXCEPTION");
            
        }
        
    }
    
    /**
     * Metodo para descargar un video
     * @param id id del video
     * @return 
     */
    public String accionDescargar(String id){
        
        Video _video = new Video();
        
        try {
            
            _query = "select vid_nombre, vid_ruta, vid_tamano, fk_usuario from video where "
                    + "vid_id="+id;
            
            Statement _st = _conn.createStatement();
            _resultSet = _st.executeQuery(_query);
            
            while(_resultSet.next()){
                
                _video.setNombre(_resultSet.getString("vid_nombre"));
                _video.setRuta(_resultSet.getString("vid_ruta"));
                _video.setTamaño(_resultSet.getString("vid_tamano"));
                _video.setDueño(_resultSet.getInt("fk_usuario"));
                contadorDescargasVideo(id);
                contadorDescargasCliente(_video.getDueño());
                
            }
            
            System.out.println("ENVIADNO ARCHIVO "+_video.getNombre());
            
            String _respuesta = _video.getNombre()+":"+_video.getRuta()+":"+_video.getTamaño()+
                    devolverInfoUsuario(String.valueOf(_video.getDueño()));
            
            System.out.println(_respuesta);
            
            return _respuesta;
            
        } catch (SQLException e) {
            System.out.println("SQL EXCEPTION"+e.getMessage()+" "+e.getSQLState());
        }
        return null;
    }
    
    /**
     * Devuelve el puerto e ip del cliente del cual va a ser descargado el video
     * @param id
     * @return ip y puerto en formato especial separado por ":"
     */
    public String devolverInfoUsuario(String id){
        
        try {
            
            _query = "select cli_puerto, cli_ip from cliente where cli_id ="+id;
            
            Statement _st = _conn.createStatement();
            _resultSet = _st.executeQuery(_query);
            String _ip = "";
            String _puerto = "";
            while(_resultSet.next()){
                
                _puerto = _resultSet.getString("cli_puerto");
                _ip = _resultSet.getString("cli_ip");
                
            }
           
            return ":"+_puerto+":"+_ip;
           
        } catch (SQLException e) {
            System.out.println("SQL EXCEPTION");
        }
        
        return null;
    }
    
    /**
     * Registrar un nuevo video en la base de datos
     * @param nombreCliente Nombre del dueño del video
     * @param tamano Tamaño del video en bytes
     * @param nombre Nombre del Video
     * @param ruta Ruta donde permanecera el video en el cliente
     * @return Respuesta de la base de datos
     */
    public String accionVideo(String nombreCliente, String tamano, String nombre, String ruta){
        
        try {
                
                _query = "insert into video (vid_nombre, "
                        + "vid_ruta, vid_tamano, vid_num_descargados, fk_usuario) values ("
                        + "'"+nombre+"','"+ruta+"','"+tamano+"',0,(select cli_id"
                        + " from cliente where cli_nombre='"+nombreCliente+"'))";
                Statement _st = _conn.createStatement();
                _resultSet = _st.executeQuery(_query);
                return "REGISTRADO";
                
            } catch (Exception e) {
                
                return "NO REGISTRADO";
                
            }
        
    }
    
    /**
     * Metodo apra devolver el id del usuario
     * @param nombre Nombre del usuario
     * @return Valor del Id del cliente
     */
    public int obtenerId(String nombre){
        
        int _id;
        
        try {
            
            _query = "SELECT cli_id FROM cliente where cli_nombre = '"+nombre+"'";
            Statement _st = _conn.createStatement();
            _resultSet = _st.executeQuery(_query);
            _id = 0;
            while(_resultSet.next()){
                
                _id = Integer.parseInt(_resultSet.getString("cli_id"));
                
            }
            return _id;
            
        } catch (SQLException e) {
            
            System.out.println("Error en al conexion a la BD: "+e.getMessage());
            
        }
        
        return 0;
        
    }
    
    /**
     * Registra un nuevo usuario en la base de datos
     * @param nombre Nombre dle cliente
     * @param ip Ip dle cliente
     * @param puerto Puerto de escucha del cliente
     * @return Respuesta de la base de datos
     */
    public String accionInsn (String nombre,String ip , int puerto){
        
            try {
                
                _query = "insert into cliente (cli_nombre, "
                        + "cli_puerto, cli_ip, cli_num_descargas) values ("
                        + "'"+nombre+"',"+puerto+",'"+ip+"',0)";
                Statement _st = _conn.createStatement();
                _resultSet = _st.executeQuery(_query);
                return "INSERTADO";
                
            } catch (SQLException e) {
                
                return "NO INSERTADO";
                
            }
        
    }
    
    /**
     * Metodo para consultar la lista de videos disponibles por los usuarios
     * registrardos
     * @return Devuelve una lsita con los videos disponibles
     */
    public String accionDir (){

        String _listaVideos = "";
        
        try {
            
            _query = "select vid_id, vid_nombre, vid_tamano from video";
            Statement _st = _conn.createStatement();
            _resultSet = _st.executeQuery(_query);
            while(_resultSet.next()){
                
                _listaVideos = _listaVideos+_resultSet.getString("vid_id")+":"
                        + _resultSet.getString("vid_nombre")+":"+
                        _resultSet.getString("vid_tamano")+"@";
                System.out.println(_listaVideos);
                
            }
            
            return _listaVideos;
            
        } catch (Exception e) {
           
            System.out.println("Error en consulta: "+e.getMessage());
            return null;
            
        }
        
        
        
    }
    
    /**
     * Metodo para cerrar la conexion con la base de datos
     * @param conn 
     */
    public void cerrarConexion( Connection conn ){
        
        try {
            
            conn.close();
            
        } catch (SQLException ex) {
            
            System.out.println("Error de conexion a BD: "+ex.getMessage());
            
        }
        
    }
    
}
