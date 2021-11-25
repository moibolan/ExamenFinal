package com.progra.productos.rest;

import com.progra.productos.entities.Consulta;
import com.progra.productos.entities.Producto;
import com.progra.productos.services.ProductoService;
import com.progra.productos.services.ProductoServiceImpl;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.ws.http.HTTPException;
import java.util.ArrayList;
import java.util.Objects;
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
    public Response getProductById(@PathParam("id") int id) {
        try{
                ProductoService productoService = new ProductoServiceImpl();
                Producto producto = new Producto();
                producto.setId(id);
                producto = productoService.seleccionarProducto(producto);
                if (producto != null)
                    return Response.ok(producto, MediaType.APPLICATION_JSON).build();
                return Response.status(404).entity("Error el producto no fue encontrado.").build();
            }catch(Exception e) {
            throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
        }
    }
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response saveProduct(Producto producto) {
        ProductoService productoService = new ProductoServiceImpl();
        // if(productoService.insertarProducto(producto)){
        //si descomentas este codigo, podras obtener el objeto con el nuevo id, desde la BD
        int codigo = productoService.insertarProductoConRetorno(producto);
        if(codigo != -1) {
            producto.setId(codigo);
            Producto producto1 = productoService.seleccionarProducto(producto);
            return Response.ok(producto1, MediaType.APPLICATION_JSON).build();
        }else{
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }
    }

    @PUT
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateProduct(@PathParam("id") int id, Producto producto) {
        try {
            ProductoService productoService = new ProductoServiceImpl();
            producto.setId(id);
            Producto productoAux = new Producto();
            productoAux.setId(id);
            if(productoService.seleccionarProducto(productoAux) != null) {
                productoService.actualizarProducto(producto);
                return Response.ok(producto, MediaType.APPLICATION_JSON).build();
            }
            return Response.status(404).entity("Error el producto no fue encontrado.").build();
        }catch(Exception ex){
            throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
        }
    }

    @PATCH
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateProductPatch(@PathParam("id") int id, Consulta consulta) {
        try {
            if (consulta.getCantidad() < 0) {
                throw new WebApplicationException(Response.Status.BAD_REQUEST);
            } else {
                ProductoService productoService = new ProductoServiceImpl();
                System.out.println(consulta.getOperacion());
                Producto producto = new Producto();
                producto.setId(id);
                producto = productoService.seleccionarProducto(producto);
                if (producto != null) {
                    if (Objects.equals(consulta.getOperacion(), "agregar")) {
                        int agregar = producto.getCantidad() + consulta.getCantidad();
                        producto.setCantidad(agregar);
                    }
                    if (Objects.equals(consulta.getOperacion(), "modificar")) {
                        producto.setCantidad(consulta.getCantidad());
                    }
                    if (Objects.equals(consulta.getOperacion(), "restar")) {
                        if(producto.getCantidad() - consulta.getCantidad() < 0) {
                            throw new WebApplicationException(Response.status(422).entity("Error la cantidad a restar es mayor a la cantidad.").build());
                        }
                        int restar = producto.getCantidad() - consulta.getCantidad();
                        producto.setCantidad(restar);
                    }
                    productoService.actualizarProducto(producto);
                    return Response.ok(producto, MediaType.APPLICATION_JSON).build();
                } else {
                    return Response.status(404).entity("Error el producto no fue encontrado.").build();
                }
            }
        }catch (Exception e){
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }
}
}