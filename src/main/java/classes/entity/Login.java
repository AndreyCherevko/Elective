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
 */

public class Login implements Serializable {

    private String login;

    private String password;

    private Integer id;

    private Boolean isWorking;
    public Login() {
    }

  
    public Login(String login, String password, Integer id) {
        this.login = login;
        this.password = password;
        this.id=id;
        
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setId(Integer id){
        this.id=id;
    }
    public Integer getId(){
        return id;
    }
      public Boolean getWorking(){
        return isWorking;
    }
      public void setWorking(Boolean isWorking){
        this.isWorking=isWorking;
    }
    @Override
    public int hashCode() {
        int hash = 37;
        hash += (login != null ? login.hashCode() : 0)+password.hashCode()+this.isWorking.hashCode()*7;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if(object==this) return true;
        if(object==null) return false;
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Login)) {
            return false;
        }
        Login other = (Login) object;
       
        return ((this.login.equals(other.getLogin()))&&this.password.equals(other.getPassword())&&(this.id==other.getId())&&(this.isWorking==other.getWorking()));
    }

    @Override
    public String toString() {
        return "DBLogic.entity.Login[ login=" + login + "; password="+password+" ]";
    }
    
}
