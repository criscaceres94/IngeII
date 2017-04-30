/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sys.service;

import com.sys.Vacunas;
import com.sys.VacunasPK;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.PathSegment;

/**
 *
 * @author Cristian
 */
@Stateless
@Path("vacunas")
public class VacunasFacadeREST extends AbstractFacade<Vacunas> {

    @PersistenceContext(unitName = "sysvacPU")
    private EntityManager em;

    private VacunasPK getPrimaryKey(PathSegment pathSegment) {
        /*
         * pathSemgent represents a URI path segment and any associated matrix parameters.
         * URI path part is supposed to be in form of 'somePath;idVacuna=idVacunaValue;idHijo=idHijoValue;idUsuario=idUsuarioValue'.
         * Here 'somePath' is a result of getPath() method invocation and
         * it is ignored in the following code.
         * Matrix parameters are used as field names to build a primary key instance.
         */
        com.sys.VacunasPK key = new com.sys.VacunasPK();
        javax.ws.rs.core.MultivaluedMap<String, String> map = pathSegment.getMatrixParameters();
        java.util.List<String> idVacuna = map.get("idVacuna");
        if (idVacuna != null && !idVacuna.isEmpty()) {
            key.setIdVacuna(new java.lang.Integer(idVacuna.get(0)));
        }
        java.util.List<String> idHijo = map.get("idHijo");
        if (idHijo != null && !idHijo.isEmpty()) {
            key.setIdHijo(new java.lang.Integer(idHijo.get(0)));
        }
        java.util.List<String> idUsuario = map.get("idUsuario");
        if (idUsuario != null && !idUsuario.isEmpty()) {
            key.setIdUsuario(new java.lang.Integer(idUsuario.get(0)));
        }
        return key;
    }

    public VacunasFacadeREST() {
        super(Vacunas.class);
    }

    @POST
    @Override
    @Consumes({MediaType.APPLICATION_JSON})
    public void create(Vacunas entity) {
        super.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") PathSegment id, Vacunas entity) {
        super.edit(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") PathSegment id) {
        com.sys.VacunasPK key = getPrimaryKey(id);
        super.remove(super.find(key));
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public Vacunas find(@PathParam("id") PathSegment id) {
        com.sys.VacunasPK key = getPrimaryKey(id);
        return super.find(key);
    }

    @GET
    @Override
    @Produces({MediaType.APPLICATION_JSON})
    public List<Vacunas> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_JSON})
    public List<Vacunas> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return super.findRange(new int[]{from, to});
    }

    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public String countREST() {
        return String.valueOf(super.count());
    }
   
    @GET
    @Path("where/{id_hijo}")
    @Produces({MediaType.APPLICATION_JSON})
    public List<Vacunas> retornar (@PathParam("id_hijo") Integer id_hijo){
    return super.findAllwhere(id_hijo);
    }
    
    @GET
    @Path("where/usuario/{id_usuario}")
    @Produces({MediaType.APPLICATION_JSON})
    public List<Vacunas> retornars (@PathParam("id_usuario") Integer id_usuario){
    return super.findAllwhere1(id_usuario);
    }
    
    @GET
    @Path("where/{id_hijo}/{aplicada}")
    @Produces({MediaType.APPLICATION_JSON})
    public List<Vacunas> retorna1 (@PathParam("id_hijo") Integer id_hijo,@PathParam("aplicada") Integer aplicada){
    return super.findAllwhereaplicada(id_hijo,aplicada);
    }
    
    @GET
    @Path("where/ordenado/{id_hijo}")
    @Produces({MediaType.APPLICATION_JSON})
    public List<Vacunas> retornaOrdenAlfaa(@PathParam("id_hijo") Integer id_hijo){
    return super.retornaOrdenAlfa(id_hijo);
    }
    
    @Override
    protected EntityManager getEntityManager() {
        em = Persistence.createEntityManagerFactory("sysvacPU").createEntityManager();
        return em;
    }
    
}
