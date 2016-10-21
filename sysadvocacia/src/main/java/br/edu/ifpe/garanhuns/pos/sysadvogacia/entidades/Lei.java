/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpe.garanhuns.pos.sysadvogacia.entidades;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Gleydson
 */
@Entity
@Table(name = "lei")
@XmlRootElement
@NamedQueries({
@NamedQuery(name = "Lei.findAll", query = "SELECT l FROM Lei l")})


public class Lei implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codigo", nullable = false)
    private Integer codigo;
    @Size(max = 100)
    @Column(name = "descricao", length = 100)
    private String descricao;
    @Size(max = 10)
    @Column(name = "tipo", length = 10)
    private String tipo;
    @Column(name = "capitulo")
    private Integer capitulo;
    @Column(name = "artigo")
    private Integer artigo;
    @JoinColumn(name = "processo_codigo", referencedColumnName = "codigo", nullable = false)
    @ManyToOne(optional = false)
    private Processo processoCodigo;

    public Lei() {
    }

    public Lei(Integer codigo) {
        this.codigo = codigo;
    }

    
    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {        
        this.tipo = tipo;
    }

    public Integer getCapitulo() {
        return capitulo;
    }

    public void setCapitulo(Integer capitulo) {
        this.capitulo = capitulo;
    }

    public Integer getArtigo() {
        return artigo;
    }

    public void setArtigo(Integer artigo) {
        this.artigo = artigo;
    }
    @XmlTransient 
    public Processo getProcessoCodigo() {
        return processoCodigo;
    }
    
    public void setProcessoCodigo(Processo processoCodigo) {
        this.processoCodigo = processoCodigo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codigo != null ? codigo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Lei)) {
            return false;
        }
        Lei other = (Lei) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.edu.ifpe.pos.sysadvogacia.entidades.Lei[ codigo=" + codigo + " ]";
    }
    
}
