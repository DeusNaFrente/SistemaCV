package br.com.iris.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "atcliente", catalog = "milton")
public class Atcliente implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private int id;
    private String empresa;
    private String contato;
    private String telefone;
    private String celular;
    private String email;
    private String conheceu;
    private String tipoatend;
    private String perfilcliente;
    private String direcionar;
    private String motivocont;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmpresa() {
        return empresa;
    }

    public String getContato() {
        return contato;
    }

    public String getTelefone() {
        return telefone;
    }

    public String getCelular() {
        return celular;
    }

    public String getEmail() {
        return email;
    }

    public String getConheceu() {
        return conheceu;
    }

    public String getTipoatend() {
        return tipoatend;
    }

    public String getPerfilcliente() {
        return perfilcliente;
    }

    public String getDirecionar() {
        return direcionar;
    }


    public String getMotivocont() {
        return motivocont;
    }
    
    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    public void setContato(String contato) {
        this.contato = contato;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setConheceu(String conheceu) {
        this.conheceu = conheceu;
    }

    public void setTipoatend(String tipoatend) {
        this.tipoatend = tipoatend;
    }

    public void setPerfilcliente(String perfilcliente) {
        this.perfilcliente = perfilcliente;
    }

    public void setDirecionar(String direcionar) {
        this.direcionar = direcionar;
    }

   
    public void setMotivocont(String motivocont) {
        this.motivocont = motivocont;
    }
    

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + this.id;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Atcliente other = (Atcliente) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

}
