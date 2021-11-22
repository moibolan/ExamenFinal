package com.progra.productos.services;

import com.progra.productos.dao.ProductoDao;
import com.progra.productos.entities.Producto;

import java.util.ArrayList;

public class ProductoServiceImpl implements ProductoService {

    private ProductoDao productoDao;

    public ProductoServiceImpl() {
        productoDao = new ProductoDao();
    }


    @Override
    public Boolean insertarProducto(Producto producto) {
        return productoDao.insertarProducto(producto);
    }

    @Override
    public Boolean actualizarProducto(Producto producto) {
        return productoDao.actualizarProducto(producto);
    }

    @Override
    public int insertarProductoConRetorno(Producto producto) {
        return productoDao.insertarProductoConRetornoId(producto);
    }

    @Override
    public Producto seleccionarProducto(Producto producto) {
        return productoDao.seleccionarProducto(producto);
    }

    @Override
    public ArrayList<Producto> listarProductos() {
        return productoDao.mostrarTodos();
    }
}
