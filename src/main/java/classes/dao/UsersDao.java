/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes.dao;

import classes.connection.ConnectionManager;
import classes.entity.Login;
import classes.entity.UserType;
import classes.entity.Users;
import classes.exception.AddException;
import classes.idao.ILoginDao;
import classes.idao.IUsersDao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Андрей
 */
public class UsersDao implements IUsersDao {

    protected final String SQL_SELECT = "SELECT id,name,surname,middlename,phone,user_type_type FROM users ";
    protected final String SQL_INSERT = "INSERT INTO users(id,name,surname,middlename,phone,user_type_type) VALUES(?,?,?,?,?,?) ";
    //Запрос используеться при сохранение студента в архиве
    protected final String COLUMN_ID = "id";
    protected final String COLUMN_NAME = "name";
    protected final String COLUMN_SURNAME = "surname";
    protected final String COLUMN_MIDDLENAME = "middlename";
    protected final String COLUMN_PHONE = "phone";
    protected final String COLUMN_TYPE = "user_type_type";

    protected Users createUser(ResultSet rs) throws SQLException {
        Users user = new Users();
        user.setId(rs.getInt(COLUMN_ID));
        user.setName(rs.getString(COLUMN_NAME));
        user.setSurname(rs.getString(COLUMN_SURNAME));
        user.setMiddlename(rs.getString(COLUMN_MIDDLENAME));
        user.setPhone(rs.getString(COLUMN_PHONE));
        user.setUserType(rs.getString(COLUMN_TYPE));
        return user;
    }

    protected Collection<Users> fetchMultiResults(ResultSet rs) throws SQLException {
        Collection resultList = new ArrayList();
        while (rs.next()) {
            Users dto = createUser(rs);
            resultList.add(dto);
        }

        return resultList;
    }

    protected Collection<Users> findByDynamicSelect(String sql, Object[] sqlParams) throws Exception {

        // declare variables
        Connection conn = ConnectionManager.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            // get the user-specified connection or get a connection from the ResourceManager
            // !      conn = isConnSupplied ? userConn : connPool.getConnection();

            // construct the SQL statement
            final String SQL = sql;

            //System.out.println("Executing " + SQL);
            // prepare statement
            stmt = conn.prepareStatement(SQL);

            // bind parameters
            for (int i = 0; sqlParams != null && i < sqlParams.length; i++) {
                stmt.setObject(i + 1, sqlParams[i]);
            }

            rs = stmt.executeQuery();

            // fetch the results
            return fetchMultiResults(rs);

        } finally {
            if (stmt != null) {
                stmt.close();
            }
            if (rs != null) {
                rs.close();
            }
            if (conn != null) {
                conn.close();
            }
        }

    }

    @Override
    public void insertUser(Users user, Login newlogin) throws Exception {
        Connection conn = ConnectionManager.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        ILoginDao logindao = new LoginDao();
        UserTypeDao typedao = new UserTypeDao();
        CoursesDao coursesdao = new CoursesDao();
        conn.setAutoCommit(false);
        try {

            //проверка на корректность номера телефона
            Pattern pat = Pattern.compile("([0-9]|\\-)+");
            Matcher mat = pat.matcher(user.getPhone());
            if (!mat.matches()) {
                throw new AddException("incorrect phone");
            }

            //Проверка на соответсвие поля type с полем UserType
            final String SQL = SQL_INSERT;
            UserType types = typedao.findWhereUserTypeEquals(user.getUserType());

            if (types == null) {
                throw new AddException("you chosen invalid usertype");
            }

            stmt = conn.prepareStatement(SQL);

            // bind parameters
            stmt.setObject(1, user.hashCode());
            stmt.setObject(2, user.getName());
            stmt.setObject(3, user.getSurname());
            stmt.setObject(4, user.getMiddlename());
            stmt.setObject(5, user.getPhone());
            stmt.setObject(6, user.getUserType());

            stmt.executeUpdate();
            newlogin.setId(user.hashCode());
            logindao.insertUser(newlogin, user,conn);
            conn.commit();
            conn.setAutoCommit(true);
        } catch (Exception ex) {
            conn.rollback();
            conn.setAutoCommit(true);
            throw new Exception(ex);
        } finally {
            if (stmt != null) {

                stmt.close();

                if (rs != null) {

                    rs.close();

                }
                if (conn != null) {

                    conn.close();

                }
            }

        }
    }

    @Override
    public Users findWhereUserIdEquals(Integer id) throws Exception {
        Users user = null;
        Collection<Users> users = findByDynamicSelect(SQL_SELECT + "WHERE id=?", new Object[]{id});
        Iterator<Users> it = users.iterator();
            if (it.hasNext()) {
            user = it.next();
        } else {
            return null;
        }
        return user;
    }

    @Override
    public Collection<Users> findAllUser(String usertype) throws Exception {
        return findByDynamicSelect(SQL_SELECT + " where user_type_type=?", new Object[]{usertype});
    }

}
