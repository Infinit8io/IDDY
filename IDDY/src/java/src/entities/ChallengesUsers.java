/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src.entities;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Bryan
 */
@Entity
@Table(name = "challenges_users")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ChallengesUsers.findAll", query = "SELECT c FROM ChallengesUsers c"),
    @NamedQuery(name = "ChallengesUsers.findById", query = "SELECT c FROM ChallengesUsers c WHERE c.id = :id"),
    @NamedQuery(name = "ChallengesUsers.findByState", query = "SELECT c FROM ChallengesUsers c WHERE c.state = :state"),
    @NamedQuery(name = "ChallengesUsers.findByDatetimeCreate", query = "SELECT c FROM ChallengesUsers c WHERE c.datetimeCreate = :datetimeCreate"),
    @NamedQuery(name = "ChallengesUsers.findByDatetimeDone", query = "SELECT c FROM ChallengesUsers c WHERE c.datetimeDone = :datetimeDone"),
    @NamedQuery(name = "ChallengesUsers.findByStateAndUser", query = "SELECT c FROM ChallengesUsers c WHERE c.fkGetter = :user AND c.state = :state")})
public class ChallengesUsers implements Serializable {

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
    @Basic(optional = true)
    @Column(name = "datetime_create")
    @Temporal(TemporalType.TIMESTAMP)
    private Date datetimeCreate;
    @Column(name = "datetime_done")
    @Temporal(TemporalType.TIMESTAMP)
    private Date datetimeDone;
    @JoinColumn(name = "fk_giver", referencedColumnName = "id")
    @ManyToOne(optional = true)
    private Users fkGiver;
    @JoinColumn(name = "fk_getter", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Users fkGetter;
    @JoinColumn(name = "fk_chall", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Challenges fkChall;
    
    public ChallengesUsers() {
    }

    public ChallengesUsers(Integer id) {
        this.id = id;
    }

    public ChallengesUsers(Integer id, int state, Date datetimeCreate) {
        this.id = id;
        this.state = state;
        this.datetimeCreate = datetimeCreate;
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

    public Date getDatetimeCreate() {
        return datetimeCreate;
    }

    public void setDatetimeCreate(Date datetimeCreate) {
        this.datetimeCreate = datetimeCreate;
    }

    public Date getDatetimeDone() {
        return datetimeDone;
    }

    public void setDatetimeDone(Date datetimeDone) {
        this.datetimeDone = datetimeDone;
    }

    public Users getFkGiver() {
        return fkGiver;
    }

    public void setFkGiver(Users fkGiver) {
        this.fkGiver = fkGiver;
    }

    public Users getFkGetter() {
        return fkGetter;
    }

    public void setFkGetter(Users fkGetter) {
        this.fkGetter = fkGetter;
    }

    public Challenges getFkChall() {
        return fkChall;
    }

    public void setFkChall(Challenges fkChall) {
        this.fkChall = fkChall;
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
        if (!(object instanceof ChallengesUsers)) {
            return false;
        }
        ChallengesUsers other = (ChallengesUsers) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "src.entities.ChallengesUsers[ id=" + id + " ]";
    }
    
}
