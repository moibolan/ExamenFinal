package com.progra.productos.dao;

import com.progra.productos.entities.Producto;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import static com.progra.productos.dao.CategoriaDao.connection;

public class ProductoDao {

    public boolean insertarProducto(Producto producto){
        try {
            String consulta = "insert into producto(nombre, descripcion, url, precio,cantidad, id_categorias) values (?, ?, ?, ?, ?, ?)";
            PreparedStatement stm1 = connection.prepareStatement(consulta);
            stm1.setString(1, producto.getNombre());
            stm1.setString(2, producto.getDescripcion());
            stm1.setString(3, producto.getUrl());
            stm1.setDouble(4, producto.getPrecio());
            stm1.setInt(5, producto.getCantidad());
            stm1.setInt(6, producto.getIdCategoria());
            stm1.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ProductoDao.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Error:" + ex.fillInStackTrace());
            return false;
        }

    }

    //metodo para insertar
    public int insertarProductoConRetornoId(Producto producto){
        int codigo = 0;
        try {
            String consulta = "insert into producto(nombre, descripcion, url, precio,cantidad, id_categorias) values (?, ?, ?, ?, ?, ?)";
            PreparedStatement stm1 = connection.prepareStatement(consulta, Statement.RETURN_GENERATED_KEYS);

            stm1.setString(1, producto.getNombre());
            stm1.setString(2, producto.getDescripcion());
            stm1.setString(3, producto.getUrl());
            stm1.setDouble(4, producto.getPrecio());
            stm1.setInt(5, producto.getCantidad());
            stm1.setInt(6, producto.getIdCategoria());

            stm1.executeUpdate();
            ResultSet rs = stm1.getGeneratedKeys();
            if(rs.next()){
                codigo = rs.getInt(1);
            }
            return codigo;
        } catch (SQLException ex) {
            Logger.getLogger(ProductoDao.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Error:" + ex.fillInStackTrace());
            return -1;
        }

    }


    //metodo para buscar
    public Producto seleccionarProducto(Producto producto){
        try {
            String consulta = "select * from producto where id_productos = ? ";
            PreparedStatement stm1 = connection.prepareStatement(consulta);
            stm1.setInt(1, producto.getId());
            ResultSet rs = stm1.executeQuery();
            Producto productoNuevo = null;
            while(rs.next()){

                productoNuevo = new Producto();
                productoNuevo.setId(rs.getInt("id_productos"));
                productoNuevo.setNombre(rs.getString("nombre"));
                productoNuevo.setDescripcion(rs.getString("descripcion"));
                productoNuevo.setUrl(rs.getString("url"));
                productoNuevo.setPrecio(rs.getDouble("precio"));
                productoNuevo.setCantidad(rs.getInt("cantidad"));
                productoNuevo.setIdCategoria(rs.getInt("id_categorias"));

            }
            return productoNuevo;
        } catch (SQLException ex) {
            Logger.getLogger(ProductoDao.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Error:" + ex.fillInStackTrace());
            return null;
        }

    }

    //metodo para traer todos los registros
    public ArrayList<Producto> mostrarTodos(){
        try {
            String consulta = "select * from producto";
            PreparedStatement stm1 = connection.prepareStatement(consulta);
            ResultSet rs = stm1.executeQuery();
            ArrayList<Producto> listaProductos = new ArrayList();
            while(rs.next()){
                Producto producto = new Producto();

                producto.setId(rs.getInt("id_productos"));
                producto.setNombre(rs.getString("nombre"));
                producto.setDescripcion(rs.getString("descripcion"));
                producto.setUrl(rs.getString("url"));
                producto.setPrecio(rs.getDouble("precio"));
                producto.setCantidad(rs.getInt("cantidad"));
                producto.setIdCategoria(rs.getInt("id_categorias"));

                listaProductos.add(producto);
            }
            return listaProductos;
        } catch (SQLException ex) {
            Logger.getLogger(ProductoDao.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Error:" + ex.fillInStackTrace());
            return null;
        }

    }

    //metodo para actualizar
    public boolean actualizarProducto(Producto producto){
        try {

            String consulta = "update producto " +
                    " set nombre = ?, descripcion=?, url=?, precio=?, cantidad=?, id_categorias=? where id_productos=? ";
            PreparedStatement stm1 = connection.prepareStatement(consulta);

            stm1.setString(1, producto.getNombre());
            stm1.setString(2, producto.getDescripcion());
            stm1.setString(3, producto.getUrl());
            stm1.setDouble(4, producto.getPrecio());
            stm1.setInt(5, producto.getCantidad());
            stm1.setInt(6, producto.getIdCategoria());

            stm1.setInt(5, producto.getId());
            stm1.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ProductoDao.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Error:" + ex.fillInStackTrace());
            return false;
        }

    }







}
