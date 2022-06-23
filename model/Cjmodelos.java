package br.com.iris.model;


import br.com.iris.dao.ItemModeloDao;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;



@Entity
@Table(name = "cjmodelos", catalog = "milton")
public class Cjmodelos implements Serializable {
    
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nomedocj;
    private double valor;
    private int qtd;
    
    @OneToOne
    @JoinColumn(name = "idmodelo")
    private Modelo modelo;
    
    @Transient
    private List<ItemModelo> itensMo;

    public List<ItemModelo> getItensMo() {
        if (itensMo == null) {
            if (this.getId() != 0) {
                itensMo = new ItemModeloDao().getByCjmodelos(this);
            } else {
                itensMo = new ArrayList<>();
            }
        }
        
        return itensMo;
    }

    public void setItensMo(List<ItemModelo> itensMo) {
        this.itensMo = itensMo;
    }

    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomedocj() {
        return nomedocj;
    }

    public void setNomedocj(String nomedocj) {
        this.nomedocj = nomedocj;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public int getQtd() {
        return qtd;
    }

    public void setQtd(int qtd) {
        this.qtd = qtd;
    }

    public Modelo getModelo() {
        return modelo;
    }

    public void setModelo(Modelo modelo) {
        this.modelo = modelo;
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
        final Cjmodelos other = (Cjmodelos) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    } 
    
    
    
    
    
    
}
