/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sys;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Cristian
 */
@Entity
@Table(name = "vacunas")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Vacunas.findAll", query = "SELECT v FROM Vacunas v")
    , @NamedQuery(name = "Vacunas.findByIdVacuna", query = "SELECT v FROM Vacunas v WHERE v.vacunasPK.idVacuna = :idVacuna")
    , @NamedQuery(name = "Vacunas.findByNombre", query = "SELECT v FROM Vacunas v WHERE v.nombre = :nombre")
    , @NamedQuery(name = "Vacunas.findByDosis", query = "SELECT v FROM Vacunas v WHERE v.dosis = :dosis")
    , @NamedQuery(name = "Vacunas.findByEdad", query = "SELECT v FROM Vacunas v WHERE v.edad = :edad")
    , @NamedQuery(name = "Vacunas.findByFecha", query = "SELECT v FROM Vacunas v WHERE v.fecha = :fecha")
    , @NamedQuery(name = "Vacunas.findByLote", query = "SELECT v FROM Vacunas v WHERE v.lote = :lote")
    , @NamedQuery(name = "Vacunas.findByNombreMedico", query = "SELECT v FROM Vacunas v WHERE v.nombreMedico = :nombreMedico")
    , @NamedQuery(name = "Vacunas.findByDescripcion", query = "SELECT v FROM Vacunas v WHERE v.descripcion = :descripcion")
    , @NamedQuery(name = "Vacunas.findByIdHijo", query = "SELECT v FROM Vacunas v WHERE v.vacunasPK.idHijo = :idHijo")
    , @NamedQuery(name = "Vacunas.findByAplicada", query = "SELECT v FROM Vacunas v WHERE v.aplicada = :aplicada")
    , @NamedQuery(name = "Vacunas.findByMesAplicacion", query = "SELECT v FROM Vacunas v WHERE v.mesAplicacion = :mesAplicacion")
    , @NamedQuery(name = "Vacunas.findByIdUsuario", query = "SELECT v FROM Vacunas v WHERE v.vacunasPK.idUsuario = :idUsuario")})
public class Vacunas implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected VacunasPK vacunasPK;
    @Size(max = 2147483647)
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "dosis")
    private Integer dosis;
    @Column(name = "edad")
    private Integer edad;
    @Size(max = 2147483647)
    @Column(name = "fecha")
    private String fecha;
    @Size(max = 2147483647)
    @Column(name = "lote")
    private String lote;
    @Size(max = 2147483647)
    @Column(name = "nombre_medico")
    private String nombreMedico;
    @Size(max = 2147483647)
    @Column(name = "descripcion")
    private String descripcion;
    @Column(name = "aplicada")
    private Integer aplicada;
    @Column(name = "mes_aplicacion")
    private Integer mesAplicacion;
    
    
    /*@JoinColumn(name = "id_hijo", referencedColumnName = "cedula", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Datos datos;
    @JoinColumn(name = "id_usuario", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Usuarios usuarios;*/

    public Vacunas() {
    }

    public Vacunas(VacunasPK vacunasPK) {
        this.vacunasPK = vacunasPK;
    }

    public Vacunas(int idVacuna, int idHijo, int idUsuario) {
        this.vacunasPK = new VacunasPK(idVacuna, idHijo, idUsuario);
    }

    public VacunasPK getVacunasPK() {
        return vacunasPK;
    }

    public void setVacunasPK(VacunasPK vacunasPK) {
        this.vacunasPK = vacunasPK;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getDosis() {
        return dosis;
    }

    public void setDosis(Integer dosis) {
        this.dosis = dosis;
    }

    public Integer getEdad() {
        return edad;
    }

    public void setEdad(Integer edad) {
        this.edad = edad;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getLote() {
        return lote;
    }

    public void setLote(String lote) {
        this.lote = lote;
    }

    public String getNombreMedico() {
        return nombreMedico;
    }

    public void setNombreMedico(String nombreMedico) {
        this.nombreMedico = nombreMedico;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Integer getAplicada() {
        return aplicada;
    }

    public void setAplicada(Integer aplicada) {
        this.aplicada = aplicada;
    }

    public Integer getMesAplicacion() {
        return mesAplicacion;
    }

    public void setMesAplicacion(Integer mesAplicacion) {
        this.mesAplicacion = mesAplicacion;
    }

    /*public Datos getDatos() {
        return datos;
    }

    public void setDatos(Datos datos) {
        this.datos = datos;
    }

    public Usuarios getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(Usuarios usuarios) {
        this.usuarios = usuarios;
    }*/

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (vacunasPK != null ? vacunasPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Vacunas)) {
            return false;
        }
        Vacunas other = (Vacunas) object;
        if ((this.vacunasPK == null && other.vacunasPK != null) || (this.vacunasPK != null && !this.vacunasPK.equals(other.vacunasPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sys.Vacunas[ vacunasPK=" + vacunasPK + " ]";
    }
    
}
