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
    
    /**
     * Metodo para consultar los 10 clientes con mas descargas de videos
     * @return lista de los 10 clientes con mas descargas
     */
    public ArrayList<Cliente> accionNumeroClientesDescargas(){
        
        ArrayList<Cliente> _listaClientes = new ArrayList<>();
        Cliente _cliente;
        
        try {
            
            _query = "select cli_nombre, cli_num_descargas from cliente "
                    + "order by (cli_num_descargas, cli_nombre) desc limit 10";
            
            
            Statement _st = _conn.createStatement();
            _resultSet = _st.executeQuery(_query);
            
            while(_resultSet.next()){
                
                _cliente = new Cliente();
                _cliente.setNombre(_resultSet.getString("cli_nombre"));
                _cliente.setNumeroVideosDescargados(
                        _resultSet.getInt("cli_num_descargas"));
                _listaClientes.add(_cliente);
                
            }
            
            return _listaClientes;
            
        } catch (Exception e) {
            
            System.out.println("Error al consultar numero de descargas de cliente");
            
        }
        
        return null;
        
    }
    
    /**
     * Metodo para obtener los 10 videos mas descargados
     * @return Lista de los 10videos mas descargados
     */
    public ArrayList<Video> accionNumeroVideosDescargados(){
        
        ArrayList<Video> _listaVideos = new ArrayList<>();
        Video _video;
        
        try {
            
            _query = "select vid_nombre, vid_num_descargados from video "
                    + "order by (vid_num_descargados,vid_nombre) desc limit 10";
            
            Statement _st = _conn.createStatement();
            _resultSet = _st.executeQuery(_query);
            
            while(_resultSet.next()){
                
                _video = new Video();
                _video.setNombre(_resultSet.getString("vid_nombre"));
                _video.setNumDescargado(Integer.parseInt(
                        _resultSet.getString("vid_num_descargados")));
                _listaVideos.add(_video);
                
            }
            
            return _listaVideos;
            
        } catch (Exception e) {
            
            System.out.println("Error en al consulta  a BD: "+e.getMessage());
            
        }
        
        return null;
        
    }
    
    /**
     * Actualiza el contador de descargas hechas a un video al ser este descargado
     * @param nombre Nombre del video
     */
    public void contadorDescargasVideo(String nombre){
        
        try {
            
            _query = "update video set vid_num_descargados = vid_num_descargados +1 "
                + "where vid_nombre='"+nombre+"'";
            
            Statement _st = _conn.createStatement();
            _resultSet = _st.executeQuery(_query);
            
        } catch (Exception e) {
            

            
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
            
        } catch (Exception e) {

            
        }
        
    }
    
    /**
     * Metodo para descargar un video
     * @param nombre Nombre del video
     */
    public void accionDescargar(String nombre){
        
        Video _video = null;
        
        try {
            
            _query = "select vid_nombre, vid_ruta, vid_tamano, fk_usuario from video where "
                    + "vid_nombre='"+nombre+"' limit 1";
            
            Statement _st = _conn.createStatement();
            _resultSet = _st.executeQuery(_query);
            
            while(_resultSet.next()){
                
                _video = new Video();
                _video.setNombre(_resultSet.getString("vid_nombre"));
                _video.setRuta(_resultSet.getString("vid_ruta"));
                _video.setTamaño(_resultSet.getString("vid_tamano"));
                _video.setDueño(_resultSet.getInt("fk_usuario"));
                contadorDescargasVideo(nombre);
                contadorDescargasCliente(_video.getDueño());
                
            }
            
            System.out.println("ENVIADNO ARCHIVO "+_video.getNombre());
            /*
            
            ENVIAR ARCHIVO
            
            */
            
        } catch (Exception e) {
        }
        
    }
    
    /**
     * Registrar un nuevo video en la base de datos
     * @param nombreCliente Nombre del dueño del video
     * @param tamano Tamaño del video en bytes
     * @param nombre Nombre del Video
     * @param ruta Ruta donde permanecera el video en el cliente
     * @return Respuesta de la base de datos
     */
    public boolean accionVideo(String nombreCliente, String tamano, String nombre, String ruta){
        
        try {
                
                _query = "insert into video (vid_nombre, "
                        + "vid_ruta, vid_tamano, vid_num_descargados, fk_usuario) values ("
                        + "'"+nombre+"','"+ruta+"','"+tamano+"',0,(select cli_id"
                        + " from cliente where cli_nombre='"+nombreCliente+"'))";
                Statement _st = _conn.createStatement();
                _resultSet = _st.executeQuery(_query);
                return false;
                
            } catch (Exception e) {
                
                return true;
                
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
    public boolean accionInsn (String nombre,String ip , int puerto){
        
            try {
                
                _query = "insert into cliente (cli_nombre, "
                        + "cli_puerto, cli_ip, cli_num_descargas) values ("
                        + "'"+nombre+"',"+puerto+",'"+ip+"',0)";
                Statement _st = _conn.createStatement();
                _resultSet = _st.executeQuery(_query);
                return false;
                
            } catch (Exception e) {
                
                return true;
                
            }
        
    }
    
    /**
     * Metodo para consultar la lista de videos disponibles por los usuarios
     * registrardos
     * @return Devuelve una lsita con los videos disponibles
     */
    public ArrayList<Video> accionDir (){
        
        Video _video;
        ArrayList<Video> _listaVideos = new ArrayList<>();
        
        try {
            
            _query = "select vid_id, vid_nombre, vid_tamano from video";
            Statement _st = _conn.createStatement();
            _resultSet = _st.executeQuery(_query);
            while(_resultSet.next()){
                _video = new Video();
                _video.setId(_resultSet.getInt("vid_id"));
                _video.setNombre(_resultSet.getString("vid_nombre"));
                _video.setTamaño(_resultSet.getString("vid_tamano"));
                System.out.println(_video.getNombre());
                _listaVideos.add(_video);
                
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
