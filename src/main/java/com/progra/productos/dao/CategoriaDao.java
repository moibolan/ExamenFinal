package com.progra.productos.dao;

import com.progra.productos.entities.Categoria;
import com.progra.productos.utils.Conexion;

import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CategoriaDao {


    public final static Connection connection = Conexion.getConnection();

    //metodo para insertar

    public boolean insertarCategoria(Categoria categoria){
        try {
            String consulta = "insert into categoria(descripcion) values (?)";
            PreparedStatement stm1 = connection.prepareStatement(consulta);
            stm1.setString(1, categoria.getDescripcion());

            stm1.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(CategoriaDao.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Error:" + ex.fillInStackTrace());
            return false;
        }

    }

    //metodo para insertar
    public int insertarCategoriaConRetornoId(Categoria categoria){
        int codigo = 0;
        try {

            String consulta = "insert into categoria(descripcion) values (?)";
            PreparedStatement stm1 = connection.prepareStatement(consulta, Statement.RETURN_GENERATED_KEYS);
            stm1.setString(1, categoria.getDescripcion());
            stm1.executeUpdate();
            ResultSet rs = stm1.getGeneratedKeys();
            if(rs.next()){
                codigo = rs.getInt(1);
            }
            return codigo;
        } catch (SQLException ex) {
            Logger.getLogger(CategoriaDao.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Error:" + ex.fillInStackTrace());
            return -1;
        }

    }

    //metodo para buscar
    public Categoria seleccionarCategoria(Categoria categoria){
        try {
            String consulta = "select * from categoria where id_categoria = ? ";
            PreparedStatement stm1 = connection.prepareStatement(consulta);
            stm1.setInt(1, categoria.getId());
            ResultSet rs = stm1.executeQuery();
            Categoria categoriaNueva = null;
            while(rs.next()){
                categoriaNueva = new Categoria();
                categoriaNueva.setId(rs.getInt("id_categoria"));
                categoriaNueva.setDescripcion(rs.getString("descripcion"));
            }
            return categoriaNueva;
        } catch (SQLException ex) {
            Logger.getLogger(CategoriaDao.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Error:" + ex.fillInStackTrace());
            return null;
        }

    }


    //metodo para traer todos los registros
    public ArrayList<Categoria> mostrarTodos(){
        try {
            String consulta = "select * from categoria";
            PreparedStatement stm1 = connection.prepareStatement(consulta);
            ResultSet rs = stm1.executeQuery();
            ArrayList<Categoria> listaCategorias = new ArrayList();
            while(rs.next()){
                Categoria categoria = new Categoria();
                categoria.setId(rs.getInt("id_categoria"));
                categoria.setDescripcion(rs.getString("descripcion"));

                listaCategorias.add(categoria);
            }
            return listaCategorias;
        } catch (SQLException ex) {
            Logger.getLogger(CategoriaDao.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Error:" + ex.fillInStackTrace());
            return null;
        }

    }

    //metodo para actualizar
    public boolean actualizarCategoria(Categoria categoria){
        try {
            String consulta = "update categoria " +
                    " set descripcion=? where id_categoria=? ";
            PreparedStatement stm1 = connection.prepareStatement(consulta);
            stm1.setString(1, categoria.getDescripcion());
            stm1.setInt(2, categoria.getId());
            stm1.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(CategoriaDao.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Error:" + ex.fillInStackTrace());
            return false;
        }

    }
}
