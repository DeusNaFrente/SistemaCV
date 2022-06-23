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
@Table(name = "condpgto", catalog = "milton")
public class Condpgto implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private int id;
    private String condpgt;

    public int getId() {
        return id;
    }

    public String getCondpgt() {
        return condpgt;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCondpgt(String condpgt) {
        this.condpgt = condpgt;
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
        final Condpgto other = (Condpgto) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

}
