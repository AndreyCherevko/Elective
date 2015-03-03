/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes.entity;

import java.io.Serializable;


/**
 *
 * @author Андрей
 * this class describe entity from courses
 */
public class Courses implements Serializable {


    private Integer id;

    private String name;

    private Integer users;

    private Boolean isArchive;

    private Boolean isConfirm;
    public Courses() {
    }

    public Courses(Integer id) {
        this.id = id;
    }

    public Courses(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getUsers() {
        return users;
    }

    public Boolean getConfirm(){
        return isConfirm;
    }
    public void setConfirm(Boolean conf){
        this.isConfirm=conf;
    }
    public void setUsers(Integer users) {
        this.users = users;
    }
    public void setArchive(Boolean archive) {
        this.isArchive = archive;
    }
    public Boolean getArchive() {
        return isArchive;
    }
    @Override
    public int hashCode() {
        int hash = 37;
        hash += (id != null ? id.hashCode() : 0) + this.name.hashCode() + this.users.hashCode()+this.isArchive.hashCode()*this.isConfirm.hashCode() * 7;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null) {
            return false;
        }
        if (!(object instanceof Courses)) {
            return false;
        }
        Courses other = (Courses) object;

        return ((this.name.equals(other.getName())) && (this.users == other.getUsers())&&(this.isArchive==other.getArchive())&&(this.isConfirm==other.getConfirm()));
    }

    @Override
    public String toString() {
        return "entity.Courses[ id=" + id +"; name="+name+"; user="+users +" ]";
    }

}
