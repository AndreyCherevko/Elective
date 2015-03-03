/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes.idao;

import classes.entity.Login;
import classes.entity.Users;
import java.util.Collection;

/**
 *
 * @author Андрей
 */
public interface IUsersDao {

    /**
     * @return Users with chosen id, if user not exists return null
     * @throws Exception
     */
    public Users findWhereUserIdEquals(Integer id) throws Exception;

    /**
     * @return Collection<Users> all users
     * @throws Exception
     */
    public Collection<Users> findAllUser(String usertype) throws Exception;

    /**
     * @param Users user New Users record, * insert user, if chosen UserType is
     * invalid throw AddException
     * @throws Exception
     */
    public void insertUser(Users user, Login newuser) throws Exception;


}
