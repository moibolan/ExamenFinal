package com.progra.productos.rest;

import com.progra.productos.entities.Producto;
import com.progra.productos.services.ProductoService;
import com.progra.productos.services.ProductoServiceImpl;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;

@Path("/productos")
public class ProductResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public ArrayList<Producto> getProducts() {
        ProductoService productoService = new ProductoServiceImpl();
        return productoService.listarProductos();
    }


    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Producto getProductById(@PathParam("id") int id) {
        ProductoService productoService = new ProductoServiceImpl();
        Producto producto = new Producto();
        producto.setId(id);
        return productoService.seleccionarProducto(producto);
    }


}