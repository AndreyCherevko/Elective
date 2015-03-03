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
 * this class describe entity from courseslist
 */

public class CoursesList implements Serializable {
    private String name;
    private Integer user;
    public CoursesList() {
    }

    public CoursesList(String name,Integer user) {
        this.name = name;
        this.user=user;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getUser(){
        return user;
    }
    public void serUser(Integer user){
        this.user=user;
    }
    @Override
    public int hashCode() {
        int hash = 37;
        hash += (name != null ? name.hashCode() : 0)+user.hashCode()*7;
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
        if (!(object instanceof CoursesList)) {
            return false;
        }
        CoursesList other = (CoursesList) object;
        
        return ((this.name.equals(other.getName()))&&(this.user==other.getUser()));
    }

    @Override
    public String toString() {
        return "entity.Courseslist[ name=" + name + "; teacher="+user+" ]";
    }
    
}
