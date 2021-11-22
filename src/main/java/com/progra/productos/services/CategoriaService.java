package com.progra.productos.services;

import com.progra.productos.entities.Categoria;

import java.util.ArrayList;

public interface CategoriaService {

    public Boolean insertarCategoria(Categoria categoria);

    public Boolean actualizarCategoria(Categoria categoria);

    public int insertarCategoriaConRetorno(Categoria categoria);

    public Categoria seleccionarCategoria(Categoria categoria);

    public ArrayList<Categoria> listarCategorias();

}
