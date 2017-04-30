/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sys;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Cristian
 */
@Embeddable
public class VacunasPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "id_vacuna")
    private int idVacuna;
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_hijo")
    private int idHijo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_usuario")
    private int idUsuario;

    public VacunasPK() {
    }

    public VacunasPK(int idVacuna, int idHijo, int idUsuario) {
        this.idVacuna = idVacuna;
        this.idHijo = idHijo;
        this.idUsuario = idUsuario;
    }

    public int getIdVacuna() {
        return idVacuna;
    }

    public void setIdVacuna(int idVacuna) {
        this.idVacuna = idVacuna;
    }

    public int getIdHijo() {
        return idHijo;
    }

    public void setIdHijo(int idHijo) {
        this.idHijo = idHijo;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idVacuna;
        hash += (int) idHijo;
        hash += (int) idUsuario;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof VacunasPK)) {
            return false;
        }
        VacunasPK other = (VacunasPK) object;
        if (this.idVacuna != other.idVacuna) {
            return false;
        }
        if (this.idHijo != other.idHijo) {
            return false;
        }
        if (this.idUsuario != other.idUsuario) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sys.VacunasPK[ idVacuna=" + idVacuna + ", idHijo=" + idHijo + ", idUsuario=" + idUsuario + " ]";
    }
    
}
