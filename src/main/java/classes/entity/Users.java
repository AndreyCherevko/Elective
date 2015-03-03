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
 * this class describe entity from users
 */

public class Users implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer usersId;

    private String name;

    private String surname;

    private String middlename;

    private String phone;

    private String userType;

    public Users() {
    }

  

    public Users(Integer usersId, String name, String surname, String middlename, String phone, String userType) {
        this.usersId = usersId;
        this.name = name;
        this.surname = surname;
        this.middlename = middlename;
        this.phone = phone;
        this.userType=userType;
    }

    public Integer getId() {
        return usersId;
    }

    public void setId(Integer usersId) {
        this.usersId = usersId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getMiddlename() {
        return middlename;
    }

    public void setMiddlename(String middlename) {
        this.middlename = middlename;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

  

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }
  
    @Override
    public int hashCode() {
        int hash = 17;
        hash += this.name.hashCode() + this.middlename.hashCode() + this.name.hashCode()+this.phone.hashCode()+this.userType.hashCode();
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
        if (!(object instanceof Users)) {
            return false;
        }
        Users other = (Users) object;

        return ((this.name.equals(other.getName())) && (this.surname.equals(other.getSurname())) && (this.middlename.equals(other.getMiddlename()))
                && (this.phone.equals(other.getPhone()))  && (this.userType.equals(other.getUserType())));
    }

    @Override
    public String toString() {
        return "Users{" + "usersId=" + usersId + ", name=" + name + ", surname=" + surname + ", middlename=" + middlename + ", phone=" + phone + ", userType=" + userType + '}';
    }

   

}
