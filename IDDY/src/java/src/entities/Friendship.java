/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src.entities;

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
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Bryan
 */
@Entity
@Table(name = "friendship")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Friendship.findAll", query = "SELECT f FROM Friendship f"),
    @NamedQuery(name = "Friendship.findById", query = "SELECT f FROM Friendship f WHERE f.id = :id"),
    @NamedQuery(name = "Friendship.findByState", query = "SELECT f FROM Friendship f WHERE f.state = :state"),
    @NamedQuery(name = "Friendship.findByBothParts", query = "SELECT f FROM Friendship f WHERE f.fkUser1 = :user1 AND f.fkUser2 = :user2"),
    @NamedQuery(name = "Friendship.findByFollower", query = "SELECT f FROM Friendship f WHERE f.fkUser1 = :other"),
    @NamedQuery(name = "Friendship.findByFollowed", query = "SELECT f FROM Friendship f WHERE f.fkUser2 = :other")})
public class Friendship implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "state")
    private int state;
    @JoinColumn(name = "fk_user1", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Users fkUser1;
    @JoinColumn(name = "fk_user2", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Users fkUser2;

    public Friendship() {
    }

    public Friendship(Integer id) {
        this.id = id;
    }

    public Friendship(Integer id, int state) {
        this.id = id;
        this.state = state;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public Users getFkUser1() {
        return fkUser1;
    }

    public void setFkUser1(Users fkUser1) {
        this.fkUser1 = fkUser1;
    }

    public Users getFkUser2() {
        return fkUser2;
    }

    public void setFkUser2(Users fkUser2) {
        this.fkUser2 = fkUser2;
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
        if (!(object instanceof Friendship)) {
            return false;
        }
        Friendship other = (Friendship) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "src.entities.Friendship[ id=" + id + " ]";
    }
    
}
