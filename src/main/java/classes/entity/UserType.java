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
 * this class describe entity from user_type
 */

public class UserType implements Serializable {


    private String type;

    public UserType() {
    }

    public UserType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public int hashCode() {
        int hash = 37;
        hash += (type != null ? type.hashCode() : 0) * 7;
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
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UserType)) {
            return false;
        }
        UserType other = (UserType) object;

        return (this.type.equals(other.getType()));
    }

    @Override
    public String toString() {
        return "entity.UserType[ type=" + type + " ]";
    }

}
