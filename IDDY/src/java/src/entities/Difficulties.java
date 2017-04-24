/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src.entities;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Bryan
 */
@Entity
@Table(name = "difficulties")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Difficulties.findAll", query = "SELECT d FROM Difficulties d"),
    @NamedQuery(name = "Difficulties.findById", query = "SELECT d FROM Difficulties d WHERE d.id = :id"),
    @NamedQuery(name = "Difficulties.findByTitle", query = "SELECT d FROM Difficulties d WHERE d.title = :title"),
    @NamedQuery(name = "Difficulties.findByPoints", query = "SELECT d FROM Difficulties d WHERE d.points = :points")})
public class Difficulties implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 25)
    @Column(name = "title")
    private String title;
    @Basic(optional = false)
    @NotNull
    @Column(name = "points")
    private int points;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fkDiff")
    private Collection<Challenges> challengesCollection;

    public Difficulties() {
    }

    public Difficulties(Integer id) {
        this.id = id;
    }

    public Difficulties(Integer id, String title, int points) {
        this.id = id;
        this.title = title;
        this.points = points;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    @XmlTransient
    public Collection<Challenges> getChallengesCollection() {
        return challengesCollection;
    }

    public void setChallengesCollection(Collection<Challenges> challengesCollection) {
        this.challengesCollection = challengesCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Difficulties)) {
            return false;
        }
        Difficulties other = (Difficulties) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return ""+title;
    }
    
}
