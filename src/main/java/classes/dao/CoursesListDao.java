/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes.dao;

import classes.connection.ConnectionManager;
import classes.entity.CoursesList;
import classes.entity.Users;
import classes.exception.AddException;
import classes.idao.ICoursesListDao;
import static classes.logger.LogApp.writeLogInFile;
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
public class CoursesListDao implements ICoursesListDao {

    protected final String SQL_SELECT = "SELECT name,users_id FROM courseslist ";
    protected final String SQL_INSERT = "INSERT INTO courseslist(name,users_id) VALUES(?,?) ";
    protected final String COLUMN_NAME = "name";
    protected final String COLUMN_USER = "users_id";

    protected CoursesList createCoursesList(ResultSet rs) throws SQLException {
        CoursesList list = new CoursesList();
        list.setName(rs.getString(COLUMN_NAME));
        list.serUser(rs.getInt(COLUMN_USER));
        return list;
    }

    protected Collection<CoursesList> fetchMultiResults(ResultSet rs) throws SQLException {
        Collection resultList = new ArrayList();
        while (rs.next()) {
            CoursesList dto = createCoursesList(rs);
            resultList.add(dto);
        }

        return resultList;
    }
 //Поиск всех курсов
    @Override
    public Collection<CoursesList> findAllCoursesList() throws Exception {

        // declare variables
        Connection conn = ConnectionManager.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
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

    //Поиск курса по имени
    @Override
    public CoursesList findWhereCoursesNameEquals(String sqlParams) throws Exception {

        // declare variables
        Connection conn = ConnectionManager.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        CoursesList list = new CoursesList();
        final String SQL = SQL_SELECT + "WHERE name=?";
        try {
            stmt = conn.prepareStatement(SQL);
            stmt.setString(1, sqlParams);
            rs = stmt.executeQuery();
            if (!rs.next()) {
                return null;
            }
            list.setName(rs.getString(COLUMN_NAME));
            list.serUser(rs.getInt(COLUMN_USER));
            return list;
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
    public void insertCourselist(CoursesList course) throws Exception {
        Connection conn = ConnectionManager.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            UsersDao usersdao = new UsersDao();
            //Проверка user_id принадлежит учителю или нет?
            Users user = usersdao.findWhereUserIdEquals(course.getUser());

            if (!user.getUserType().equals("teacher")) {
                throw new AddException("Can't chosen teacher, maybe your chose is student");
            }

            final String SQL = SQL_INSERT;
            stmt = conn.prepareStatement(SQL);
            stmt.setObject(1, course.getName());
            stmt.setObject(2, course.getUser());
            stmt.executeUpdate();
            // fetch the results
        } catch (AddException ex) {
            writeLogInFile(ex);
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
