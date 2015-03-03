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
 * this class describe entity from archives
 */

public class Archives implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;

    private Integer mark;

    private Integer course;

    public Archives() {
    }

    public Archives(Integer id) {
        this.id = id;
    }

    public Archives(Integer id, Integer mark) {
        this.id = id;
        this.mark = mark;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getMark() {
        return mark;
    }

    public void setMark(Integer mark) {
        this.mark = mark;
    }

    public Integer getCourse() {
        return course;
    }

    public void setCourse(Integer users) {
        this.course = users;
    }

    @Override
    public int hashCode() {
        int hash = 37;
        hash += (id != null ? id.hashCode() : 0)+this.mark.hashCode()+this.course.hashCode();
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
        if (!(object instanceof Archives)) {
            return false;
        }
        Archives other = (Archives) object;
        
        return ((this.id==other.getId())&&(this.mark==other.getMark())&&(this.course==other.getCourse()));
    }

    @Override
    public String toString() {
        return "entity.Archives[ id=" + id + "; course="+course+"mark="+mark+ "]";
    }
    
}
