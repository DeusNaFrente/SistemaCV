
//aqui conversamos com o banco de dados
package br.com.iris.model;

import java.io.Serializable;
import javax.persistence.Column;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;


@Entity
@Table(name = "cliente", catalog = "milton")
public class Cliente implements Serializable{
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;
   
    
    
    @OneToOne
    @JoinColumn(name="idvendedor")
    private Vendedor vendedor;
    
    
    @OneToOne
    @JoinColumn(name="idcomissionado")
    private Comissionado comissionado;
    
    
    
    
     

    
    
    private String empresa;
    private String endereco;
    private String telefone;
    private String telefone2;
    private int    pontuacao;
    private String contato;
    private String celular;
    private String email;
    private String departamento;
    private String tabela;
    private String tipoatend;
    private String classificacao;
    private String origem;
    private String entidade;
    private int    numero;
    private String complemento;
    private String estado;
    private String cidade;
    private String bairro;
    private String cep;
    private String cpf;
    private String cnpj;
    private String insc;
    private String inscmun;
    private String rg;
    private String indie;
    private String site;
    private String emailemp;
    private String obs;
    
    private String rsocial;

    public int getId() {
        return id;
    }



    public Vendedor getVendedor() {
        return vendedor;
    }
    
     public void setVendedor(Vendedor vendedor) {
        this.vendedor = vendedor;
    }
    
    
    


    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

  
    
   

    public String getEndereco() {
        return endereco;
    }

    public String getTelefone() {
        return telefone;
    }

    public String getTelefone2() {
        return telefone2;
    }

    public int getPontuacao() {
        return pontuacao;
    }

    public String getContato() {
        return contato;
    }

    public String getCelular() {
        return celular;
    }

    public String getEmail() {
        return email;
    }

    public String getDepartamento() {
        return departamento;
    }

   

    public String getTabela() {
        return tabela;
    }

    public String getTipoatend() {
        return tipoatend;
    }

    public String getClassificacao() {
        return classificacao;
    }

    public String getOrigem() {
        return origem;
    }

    public String getEntidade() {
        return entidade;
    }

    public int getNumero() {
        return numero;
    }

    public String getComplemento() {
        return complemento;
    }

    public String getEstado() {
        return estado;
    }

    public String getCidade() {
        return cidade;
    }

    public String getBairro() {
        return bairro;
    }
    
    public String getCep() {
        return cep;
    }

    public String getCpf() {
        return cpf;
    }

    public String getCnpj() {
        return cnpj;
    }

    public String getInsc() {
        return insc;
    }

    public String getInscmun() {
        return inscmun;
    }

    public String getRg() {
        return rg;
    }

    public String getIndie() {
        return indie;
    }

    public String getSite() {
        return site;
    }

    public String getEmailemp() {
        return emailemp;
    }

    public String getObs() {
        return obs;
    }

    public Comissionado getComissionado() {
        return comissionado;
    }

    public String getRsocial() {
        return rsocial;
    }
    
    
    
    
    
    
  
    
    
    
    
    
    

    public void setId(int id) {
        this.id = id;
    }

   
  
    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public void setTelefone2(String telefone2) {
        this.telefone2 = telefone2;
    }

    public void setPontuacao(int pontuacao) {
        this.pontuacao = pontuacao;
    }

    public void setContato(String contato) {
        this.contato = contato;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    

    public void setTabela(String tabela) {
        this.tabela = tabela;
    }

    public void setTipoatend(String tipoatend) {
        this.tipoatend = tipoatend;
    }

    public void setClassificacao(String classificacao) {
        this.classificacao = classificacao;
    }

    public void setOrigem(String origem) {
        this.origem = origem;
    }

    public void setEntidade(String entidade) {
        this.entidade = entidade;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }
    
      public void setCep(String cep) {
        this.cep = cep;
    }
    

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public void setInsc(String insc) {
        this.insc = insc;
    }

    public void setInscmun(String inscmun) {
        this.inscmun = inscmun;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public void setIndie(String indie) {
        this.indie = indie;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public void setEmailemp(String emailemp) {
        this.emailemp = emailemp;
    }

    public void setObs(String obs) {
        this.obs = obs;
    }

    public void setComissionado(Comissionado comissionado) {
        this.comissionado = comissionado;
    }

    public void setRsocial(String rsocial) {
        this.rsocial = rsocial;
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
        final Cliente other = (Cliente) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }
    
}
