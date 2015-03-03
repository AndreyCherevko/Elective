/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes.dao;

import classes.connection.ConnectionManager;
import classes.entity.UserType;
import classes.idao.IUserTypeDao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

/**
 *
 * @author Андрей
 */
public class UserTypeDao implements IUserTypeDao {

    protected final String SQL_SELECT = "SELECT type FROM user_type ";
    protected final String SQL_INSERT = "INSERT INTO user_type(type) VALUES(?) ";
    protected final String COLUMN_TYPE = "type";

    /**
     * Create new UserType object
     */
    protected UserType createUserType(ResultSet rs) throws SQLException {
        UserType usertype = new UserType();
        usertype.setType(rs.getString(COLUMN_TYPE));
        return usertype;
    }

    /**
     *
     * @param rs
     * @return
     * @throws java.sql.SQLException Convert ResultSet into Collection
     */
    protected Collection<UserType> fetchMultiResults(ResultSet rs) throws SQLException {
        Collection resultList = new ArrayList();
        while (rs.next()) {
            UserType dto = createUserType(rs);
            resultList.add(dto);
        }
        return resultList;
    }

    @Override
    public UserType findWhereUserTypeEquals(String sqlParams) throws Exception {

        // declare variables
        Connection conn = ConnectionManager.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        UserType type = new UserType();
        final String SQL = SQL_SELECT + "WHERE type=?";
        try {
            stmt = conn.prepareStatement(SQL);
            stmt.setString(1, sqlParams);
            rs = stmt.executeQuery();
            if (!rs.next()) {
                return null;
            }

            type.setType(rs.getString(COLUMN_TYPE));
            // fetch the results
            return type;
        } catch (Exception ex) {
            throw new Exception("Exception: " + ex.getMessage(), ex);
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
    public Collection<UserType> findAllUserType() throws Exception {

        // declare variables
        Connection conn = ConnectionManager.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        UserType type = new UserType();
        final String SQL = SQL_SELECT;
        try {
            stmt = conn.prepareStatement(SQL);

            rs = stmt.executeQuery();
            // fetch the results
            return fetchMultiResults(rs);
        } catch (Exception ex) {
            throw new Exception("Exception: " + ex.getMessage(), ex);
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
    public void insertUserType(UserType type) throws Exception {
        Connection conn = ConnectionManager.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {

            final String SQL = SQL_INSERT;

            stmt = conn.prepareStatement(SQL);
            stmt.setObject(1, type.getType());
            stmt.executeUpdate();

            // fetch the results
        } catch (Exception ex) {
            throw new Exception("Exception: " + ex.getMessage(), ex);
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

}
