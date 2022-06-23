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
@Table(name = "centrocusto", catalog = "milton")
public class Centrocusto implements Serializable{
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
 
    
   
    private int id;
    private String centrocusto;
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCentrocusto() {
        return centrocusto;
    }

    public void setCentrocusto(String centrocusto) {
        this.centrocusto = centrocusto;
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
        final Centrocusto other = (Centrocusto) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }
    
}
