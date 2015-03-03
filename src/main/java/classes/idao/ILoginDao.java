/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package classes.idao;

import classes.entity.Login;
import classes.entity.Users;
import java.sql.Connection;
import java.util.Collection;

/**
 *
 * @author Андрей
 */
public interface ILoginDao {
     public Login findByDynamicSelect(String login, String password) throws Exception;
     public void insertUser(Login login, Users user, Connection conn) throws Exception;
       public Collection<Login> findAllNotWorking() throws Exception ;
        public void setWorking(Users user) throws Exception;
         public boolean checkLogin(String login, String password) throws Exception;
          public boolean checkLogin(String login) throws Exception;
}
