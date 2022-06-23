package br.com.iris.model;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "comissionado", catalog = "milton")
public class Comissionado implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private int id;
    private String nome;
    private String empresa;
    private String telefone;
    private String contato;
    private String tipopgt;
    private int valor;
    private String status;
    private String regraComissao;
    private String obs;
    private String valorfixo;
     
    
    
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getContato() {
        return contato;
    }

    public void setContato(String contato) {
        this.contato = contato;
    }

    public String getTipopgt() {
        return tipopgt;
    }

    public void setTipopgt(String tipopgt) {
        this.tipopgt = tipopgt;
    }

   

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }

    

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRegraComissao() {
        return regraComissao;
    }

    public void setRegraComissao(String regraComissao) {
        this.regraComissao = regraComissao;
    }

    public String getObs() {
        return obs;
    }

    public void setObs(String obs) {
        this.obs = obs;
    }

    public String getValorfixo() {
        return valorfixo;
    }

    public void setValorfixo(String valorfixo) {
        this.valorfixo = valorfixo;
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
        final Comissionado other = (Comissionado) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

}
