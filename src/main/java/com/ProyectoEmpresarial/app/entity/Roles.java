package com.ProyectoEmpresarial.app.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Entity
@Table(name="authorities")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Roles implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	@Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name="authority")
	private String concepto;

	@JsonBackReference(value="UsuarioRoles")
@ManyToOne(fetch = FetchType.LAZY)
@JoinColumn(name ="user_id")
private Usuario user;









}
