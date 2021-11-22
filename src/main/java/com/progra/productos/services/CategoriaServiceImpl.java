package com.progra.productos.services;

import com.progra.productos.dao.CategoriaDao;
import com.progra.productos.entities.Categoria;

import java.util.ArrayList;

public class CategoriaServiceImpl implements CategoriaService{

    private CategoriaDao categoriaDao;
    public CategoriaServiceImpl(){
        categoriaDao = new CategoriaDao();
    }


    @Override
    public Boolean insertarCategoria(Categoria categoria) {
        return categoriaDao.insertarCategoria(categoria);
    }

    @Override
    public Boolean actualizarCategoria(Categoria categoria) {
        return categoriaDao.actualizarCategoria(categoria);
    }

    @Override
    public int insertarCategoriaConRetorno(Categoria categoria) {
        return categoriaDao.insertarCategoriaConRetornoId(categoria);
    }

    @Override
    public Categoria seleccionarCategoria(Categoria categoria) {
        return categoriaDao.seleccionarCategoria(categoria);
    }

    @Override
    public ArrayList<Categoria> listarCategorias() {
        return categoriaDao.mostrarTodos();
    }
}
