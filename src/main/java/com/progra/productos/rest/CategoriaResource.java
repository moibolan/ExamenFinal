package com.progra.productos.rest;

import com.progra.productos.entities.Categoria;
import com.progra.productos.entities.Producto;
import com.progra.productos.services.CategoriaService;
import com.progra.productos.services.CategoriaServiceImpl;
import com.progra.productos.services.ProductoService;
import com.progra.productos.services.ProductoServiceImpl;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;

@Path("/categorias")
public class CategoriaResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public ArrayList<Categoria> getCategorias() {
        CategoriaService categoriaService = new CategoriaServiceImpl();
        return categoriaService.listarCategorias();
    }


    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getProductById(@PathParam("id") int id) {
        try {
            CategoriaService categoriaService = new CategoriaServiceImpl();
            Categoria categoria = new Categoria();
            categoria.setId(id);
            categoria = categoriaService.seleccionarCategoria(categoria);
            if (categoria != null)
                return Response.ok(categoria, MediaType.APPLICATION_JSON).build();
            return Response.status(404).entity("Error la categoria no fue encontrada.").build();
        }catch(Exception e) {
        throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
        }
    }
}