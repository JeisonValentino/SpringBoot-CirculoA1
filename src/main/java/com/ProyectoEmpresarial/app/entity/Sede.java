/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ProyectoEmpresarial.app.entity;

import javax.persistence.Table;

import com.ProyectoEmpresarial.app.Excepciones.EntityIdResolver;
import com.ProyectoEmpresarial.app.entity.Estudiantes.Estudiante;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;
import lombok.extern.jackson.Jacksonized;

import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.OneToMany;

/**
 *
 * @author Stewen Jordi Mateo Villanueva <smateovj@gmail.com>
 */
@Entity
@Table(name = "sede")
@JsonIgnoreProperties({"empleadoList","estudianteList","hibernateLazyInitializer"})
@Data
@NoArgsConstructor
@AllArgsConstructor
@Jacksonized
@Builder
@Getter
@Setter
public class Sede  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "ruc")
    private String ruc;

    public String getRuc() {
        return ruc;
    }

    public void setRuc(String ruc) {
        this.ruc = ruc;
    }

    @Column(name = "direccion")
    private String direccion;

    @Column(name = "telefono")
    private String telefono;


    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idSede")
    private List<Estudiante> estudianteList;


    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idSede")
    private List<Empleado> empleadoList;



}
