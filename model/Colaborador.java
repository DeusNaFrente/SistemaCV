//aqui conversamos com o banco de dados
package br.com.iris.model;

import java.io.Serializable;
import java.util.Date;
//import java.util.Date;
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
@Table(name = "colaborador", catalog = "milton")
public class Colaborador implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String colaborador;
    private int cpf;
    private int rg;
    private String endereco;
    private int cnh;
    private String categoria;
    
    @Temporal(TemporalType.DATE)
    private Date expedicao;

    @Temporal(TemporalType.DATE)
    private Date vencimento;

    private String celular;
    private String telefone;
    private String bairro;
    private String cep;
    private String cidade;
    private String estado;
    private String carteiratrabalho;
    private String pis;
    private String sexo;
    private String estadocivil;
    private String naturalidade;

    @Temporal(TemporalType.DATE)
    private Date nascimento;

    private String favorecido;
    private int cpffavorecido;
    private int agencia;
    private String banco;
    private int numerobanco;
    private int conta;
    private String profissao;
    private double salariobase;
    private double bonus;
    private String tipocontratacao;
    private double vt;
    private double vr;
    private String obs;
    private double contsindical;
    private double outrosbenef;
    private double valorhsutil;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCpf() {
        return cpf;
    }

    public void setCpf(int cpf) {
        this.cpf = cpf;
    }

    public int getRg() {
        return rg;
    }

    public void setRg(int rg) {
        this.rg = rg;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public int getCnh() {
        return cnh;
    }

    public void setCnh(int cnh) {
        this.cnh = cnh;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public Date getExpedicao() {
        return expedicao;
    }

    public void setExpedicao(Date expedicao) {
        this.expedicao = expedicao;
    }

    public Date getVencimento() {
        return vencimento;
    }

    public void setVencimento(Date vencimento) {
        this.vencimento = vencimento;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getCarteiratrabalho() {
        return carteiratrabalho;
    }

    public void setCarteiratrabalho(String carteiratrabalho) {
        this.carteiratrabalho = carteiratrabalho;
    }

    public String getPis() {
        return pis;
    }

    public void setPis(String pis) {
        this.pis = pis;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getEstadocivil() {
        return estadocivil;
    }

    public void setEstadocivil(String estadocivil) {
        this.estadocivil = estadocivil;
    }

    public String getNaturalidade() {
        return naturalidade;
    }

    public void setNaturalidade(String naturalidade) {
        this.naturalidade = naturalidade;
    }

    public Date getNascimento() {
        return nascimento;
    }

    public void setNascimento(Date nascimento) {
        this.nascimento = nascimento;
    }

    public String getFavorecido() {
        return favorecido;
    }

    public void setFavorecido(String favorecido) {
        this.favorecido = favorecido;
    }

    public int getCpffavorecido() {
        return cpffavorecido;
    }

    public void setCpffavorecido(int cpffavorecido) {
        this.cpffavorecido = cpffavorecido;
    }

    public int getAgencia() {
        return agencia;
    }

    public void setAgencia(int agencia) {
        this.agencia = agencia;
    }

    public String getBanco() {
        return banco;
    }

    public void setBanco(String banco) {
        this.banco = banco;
    }

    public int getNumerobanco() {
        return numerobanco;
    }

    public void setNumerobanco(int numerobanco) {
        this.numerobanco = numerobanco;
    }

    public int getConta() {
        return conta;
    }

    public void setConta(int conta) {
        this.conta = conta;
    }

    public String getProfissao() {
        return profissao;
    }

    public void setProfissao(String profissao) {
        this.profissao = profissao;
    }

    public double getSalariobase() {
        return salariobase;
    }

    public void setSalariobase(double salariobase) {
        this.salariobase = salariobase;
    }

    public double getBonus() {
        return bonus;
    }

    public void setBonus(double bonus) {
        this.bonus = bonus;
    }

    public String getTipocontratacao() {
        return tipocontratacao;
    }

    public void setTipocontratacao(String tipocontratacao) {
        this.tipocontratacao = tipocontratacao;
    }

    public String getColaborador() {
        return colaborador;
    }

    public void setColaborador(String colaborador) {
        this.colaborador = colaborador;
    }

    public double getVt() {
        return vt;
    }

    public void setVt(double vt) {
        this.vt = vt;
    }

    public double getVr() {
        return vr;
    }

    public void setVr(double vr) {
        this.vr = vr;
    }

    public String getObs() {
        return obs;
    }

    public void setObs(String obs) {
        this.obs = obs;
    }

    public double getContsindical() {
        return contsindical;
    }

    public void setContsindical(double contsindical) {
        this.contsindical = contsindical;
    }

    public double getOutrosbenef() {
        return outrosbenef;
    }

    public void setOutrosbenef(double outrosbenef) {
        this.outrosbenef = outrosbenef;
    }

    public double getValorhsutil() {
        
         return  (int) (getSalariobase() / 140);
        
        
    }

    public void setValorhsutil(double valorhsutil) {
        this.valorhsutil = valorhsutil;
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
        final Colaborador other = (Colaborador) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

}
