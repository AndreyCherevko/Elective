/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package classes.idao;

import classes.entity.UserType;
import java.util.Collection;

/**
 *
 * @author Андрей
 */
public interface IUserTypeDao {
    /**
     *@return UserType where type.equals("sqlParams"), if record not exists return null
     * @throws Exception
     */
    public UserType findWhereUserTypeEquals(String sqlParams) throws Exception;
    /**
     *@return Collection<UserType> All UserType from user_type
     * @throws Exception
     */
    public Collection<UserType> findAllUserType() throws Exception;
    /**
     * @param UserType user New UserType record,
     * insert new type in user_type_type
     * @throws  Exception
          */
    public void insertUserType(UserType type) throws Exception;
    
}
