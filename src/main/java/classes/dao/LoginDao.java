/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes.dao;

import classes.connection.ConnectionManager;
import classes.entity.Login;
import classes.entity.Users;
import classes.exception.AddException;
import classes.idao.ILoginDao;
import static classes.logger.LogApp.writeLogInFile;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Андрей
 */
public class LoginDao implements ILoginDao {

    protected final String SQL_SELECT = "SELECT login,password,users_id,isWorking FROM login where login=? and password=? ";
    protected final String SQL_INSERT = "INSERT INTO login VALUES(?,?,?,?) ";
    protected final String SQL_SET_WORKING = "UPDATE login SET isWorking=true WHERE users_id=?";
    protected final String COLUMN_LOGIN = "login";
    protected final String COLUMN_PASSWORD = "password";
    protected final String COLUMN_ID = "users_id";
    protected final String COLUMN_IS_WORKING = "isWorking";

    protected Login createUser(ResultSet rs) throws SQLException {
        Login user = new Login();
        user.setLogin(rs.getString(COLUMN_LOGIN));
        user.setPassword(rs.getString(COLUMN_PASSWORD));
        user.setId(rs.getInt(COLUMN_ID));
        user.setWorking(rs.getBoolean(COLUMN_IS_WORKING));
        return user;
    }

    protected Collection<Login> fetchMultiResults(ResultSet rs) throws SQLException {
        Collection resultList = new ArrayList();
        while (rs.next()) {
            Login dto = createUser(rs);
            resultList.add(dto);
        }
        return resultList;
    }

    //Поиск записи с заданными login и password
    public Login findByDynamicSelect(String login, String password) throws Exception {

        // declare variables
        Connection conn = ConnectionManager.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            // get the user-specified connection or get a connection from the ResourceManager
            // !      conn = isConnSupplied ? userConn : connPool.getConnection();

            // construct the SQL statement
            final String SQL = SQL_SELECT;

            //System.out.println("Executing " + SQL);
            // prepare statement
            stmt = conn.prepareStatement(SQL);

            // bind parameters
            stmt.setObject(1, login);
            stmt.setObject(2, password);

            rs = stmt.executeQuery();

            // fetch the results
            if (rs.next()) {
                Login user = createUser(rs);
                return user;
            } else {
                return null;
            }

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

    public void insertUser(Login login, Users user, Connection conn) throws Exception {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            //проверка на корректность пароля
            Pattern pat = Pattern.compile("([0-9]|[a-zA-Z])+");
            Matcher mat = pat.matcher(login.getPassword());
            if (!mat.matches()) {
                throw new AddException("incorrect password, must consist only from a-z,0-9");
            }

            final String SQL = SQL_INSERT;

            stmt = conn.prepareStatement(SQL);

            // bind parameters
            stmt.setObject(1, login.getLogin());
            stmt.setObject(2, login.getPassword());
            stmt.setObject(3, user.hashCode());
            if (user.getUserType().equals("student")) {
                stmt.setObject(4, true);
            } else {
                stmt.setObject(4, false);
            }
            stmt.executeUpdate();

            // fetch the results
        } finally {
            if (stmt != null) {
                stmt.close();
            }
            if (rs != null) {
                rs.close();
            }

        }

    }

    //Подтверждение заявки на регистрацию
    public void setWorking(Users user) throws Exception {
        Connection conn = ConnectionManager.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {

            final String SQL = SQL_SET_WORKING;
            stmt = conn.prepareStatement(SQL);
            stmt.setObject(1, user.hashCode());
            stmt.executeUpdate();
            // fetch the results

        } catch (SQLException ex) {
            throw new Exception(ex);
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

    //Поиск всех не подтвержденных заявок
    public Collection<Login> findAllNotWorking() throws Exception {

        // declare variables
        Connection conn = ConnectionManager.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            // get the user-specified connection or get a connection from the ResourceManager
            // !      conn = isConnSupplied ? userConn : connPool.getConnection();

            // construct the SQL statement
            final String SQL = "SELECT login,password,users_id,isWorking FROM login WHERE isWorking=false";

            //System.out.println("Executing " + SQL);
            // prepare statement
            stmt = conn.prepareStatement(SQL);

            // bind parameters
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

    public boolean checkLogin(String login) throws Exception {
        String SQL = "SELECT * FROM login WHERE LOGIN = ? ";
        Connection cn = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        //проверка логина и пароля

        try {
            cn = ConnectionManager.getConnection();

            st = cn.prepareStatement(SQL);
            st.setString(1, login);

            rs = st.executeQuery();
            /*проверка, существует ли пользователь с указанным логином и паролем*/
            return rs.next();

        } finally {
            if (rs != null) {
                rs.close();
            }
            if (st != null) {
                st.close();
            }

            if (cn != null) {
                cn.close();
            }
        }

    }

    public boolean checkLogin(String login, String password) throws Exception {
        //проверка логина и пароля
        Connection cn = null;
        PreparedStatement st = null;
        ResultSet rs = null;

        cn = ConnectionManager.getConnection();

        st = cn.prepareStatement(SQL_SELECT);
        st.setString(1, login);
        st.setString(2, password);

        try {
            rs = st.executeQuery();
            if (rs.next() && rs.getBoolean("isWorking")) /*проверка, существует ли пользователь с указанным логином и паролем и подтвержденным аккаунтом*/ {
                return true;
            } else {
                return false;
            }
        } finally {
            if (rs != null) {
                rs.close();
            }

            if (st != null) {
                st.close();
            }

            if (cn != null) {
                cn.close();
            }
        }
    }
}
