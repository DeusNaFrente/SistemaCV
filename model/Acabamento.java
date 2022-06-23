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
@Table(name = "acabamento", catalog = "milton")
public class Acabamento implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    @OneToOne
    @JoinColumn(name = "idmatprima")
    private Materiaprima materiaprima;
    
  
    private String nome;
    private String descricao;
    private String medida;
    private int qtd;
    private String acao;
    private double altura;
    private double largura;
    private int ajustes;
    private int texec;
    private double markup;
    private double precof;
    private String unidcob;
    private int aberturaos;
    private int artefinal;
    private int marcenaria;
    private int eletrica;
    private int expedicao;
    private int revconteudo;
    private int impressaorec;
    private int lcaixa;
    private int acabamentos;
    private int contqualid;
    private int instalacao;
    private int contratacao;
    private int faturamento;
    private int criacao;
    private int aprovacli;
    private int usinagem;
    private int serralheria;
    private int pintura;
            
            

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getMedida() {
        return medida;
    }

    public Materiaprima getMateriaprima() {
        return materiaprima;
    }

    public int getQtd() {
        return qtd;
    }

    public String getAcao() {
        return acao;
    }

    public double getAltura() {
        return altura;
    }

    public double getLargura() {
        return largura;
    }

    public int getAjustes() {
        return ajustes;
    }

    public int getTexec() {
        return texec;
    }

    public double getMarkup() {
        return markup;
    }

    public double getPrecof() {
        return precof;
    }

    public String getUnidcob() {
        return unidcob;
    }
    
    

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setMedida(String medida) {
        this.medida = medida;
    }

    public void setMateriaprima(Materiaprima materiaprima) {
        this.materiaprima = materiaprima;
    }

    public void setQtd(int qtd) {
        this.qtd = qtd;
    }

    public void setAcao(String acao) {
        this.acao = acao;
    }

    public void setAltura(double altura) {
        this.altura = altura;
    }

    public void setLargura(double largura) {
        this.largura = largura;
    }

    public void setAjustes(int ajustes) {
        this.ajustes = ajustes;
    }

    public void setTexec(int texec) {
        this.texec = texec;
    }

    public void setMarkup(double markup) {
        this.markup = markup;
    }

    public void setPrecof(double precof) {
        this.precof = precof;
    }

    public void setUnidcob(String unidcob) {
        this.unidcob = unidcob;
    }

    public int getAberturaos() {
        return aberturaos;
    }

    public void setAberturaos(int aberturaos) {
        this.aberturaos = aberturaos;
    }

    public int getArtefinal() {
        return artefinal;
    }

    public void setArtefinal(int artefinal) {
        this.artefinal = artefinal;
    }

    public int getMarcenaria() {
        return marcenaria;
    }

    public void setMarcenaria(int marcenaria) {
        this.marcenaria = marcenaria;
    }

    public int getEletrica() {
        return eletrica;
    }

    public void setEletrica(int eletrica) {
        this.eletrica = eletrica;
    }

    public int getExpedicao() {
        return expedicao;
    }

    public void setExpedicao(int expedicao) {
        this.expedicao = expedicao;
    }

    public int getRevconteudo() {
        return revconteudo;
    }

    public void setRevconteudo(int revconteudo) {
        this.revconteudo = revconteudo;
    }

    public int getImpressaorec() {
        return impressaorec;
    }

    public void setImpressaorec(int impressaorec) {
        this.impressaorec = impressaorec;
    }

    public int getLcaixa() {
        return lcaixa;
    }

    public void setLcaixa(int lcaixa) {
        this.lcaixa = lcaixa;
    }

    public int getAcabamentos() {
        return acabamentos;
    }

    public void setAcabamentos(int acabamentos) {
        this.acabamentos = acabamentos;
    }

    public int getContqualid() {
        return contqualid;
    }

    public void setContqualid(int contqualid) {
        this.contqualid = contqualid;
    }

    public int getInstalacao() {
        return instalacao;
    }

    public void setInstalacao(int instalacao) {
        this.instalacao = instalacao;
    }

    public int getContratacao() {
        return contratacao;
    }

    public void setContratacao(int contratacao) {
        this.contratacao = contratacao;
    }

    public int getFaturamento() {
        return faturamento;
    }

    public void setFaturamento(int faturamento) {
        this.faturamento = faturamento;
    }

    public int getCriacao() {
        return criacao;
    }

    public void setCriacao(int criacao) {
        this.criacao = criacao;
    }

    public int getAprovacli() {
        return aprovacli;
    }

    public void setAprovacli(int aprovacli) {
        this.aprovacli = aprovacli;
    }

    public int getUsinagem() {
        return usinagem;
    }

    public void setUsinagem(int usinagem) {
        this.usinagem = usinagem;
    }

    public int getSerralheria() {
        return serralheria;
    }

    public void setSerralheria(int serralheria) {
        this.serralheria = serralheria;
    }

    public int getPintura() {
        return pintura;
    }

    public void setPintura(int pintura) {
        this.pintura = pintura;
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
        final Acabamento other = (Acabamento) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

}
