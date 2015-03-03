/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes.dao;

import classes.connection.ConnectionManager;
import classes.daoFactory.DaoFactory;
import classes.entity.Courses;
import classes.entity.Users;
import classes.exception.AddException;
import classes.idao.ICoursesDao;
import classes.idao.IUsersDao;
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
public class CoursesDao implements ICoursesDao {

    protected final String SQL_SELECT = "SELECT id,name,users_id,isArchive,isConfirm FROM courses ";
    protected final String SQL_INSERT = "INSERT INTO courses(name,users_id) VALUES(?,?) ";
    protected final String SQL_SET_ARCHIVE = "UPDATE courses SET isArchive=true WHERE id=?";
    protected final String SQL_SET_CONFIRM = "UPDATE courses SET isConfirm=true WHERE id=?";
    protected final String COLUMN_ID = "id";
    protected final String COLUMN_NAME = "name";
    protected final String COLUMN_USER = "users_id";
    protected final String COLUMN_ARCHIVE = "isArchive";
    protected final String COLUMN_CONFIRM = "isConfirm";

    protected Courses createCourse(ResultSet rs) throws SQLException {
        Courses course = new Courses();
        course.setId(rs.getInt(COLUMN_ID));
        course.setName(rs.getString(COLUMN_NAME));
        course.setUsers(rs.getInt(COLUMN_USER));
        course.setArchive(rs.getBoolean(COLUMN_ARCHIVE));
        course.setConfirm(rs.getBoolean(COLUMN_CONFIRM));
        return course;
    }

    protected Collection<Courses> fetchMultiResults(ResultSet rs) throws SQLException {
        Collection resultList = new ArrayList();
        while (rs.next()) {
              Courses dto = createCourse(rs);
            resultList.add(dto);
        }
          return resultList;
    }
   //Поиск курса исходя из users_id
    @Override
    public Collection<Courses> findStudentWhereUsersIdEquals(Integer sqlParams) throws Exception {

        // declare variables
        Connection conn = ConnectionManager.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        final String SQL = SQL_SELECT + "WHERE users_id=?";
        try {
            
            stmt = conn.prepareStatement(SQL);
            stmt.setInt(1, sqlParams);
            rs = stmt.executeQuery();
               
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

    public Courses findWhereIdEquals(Integer sqlParams) throws Exception {

        // declare variables
        Connection conn = ConnectionManager.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Courses course = new Courses();
        DaoFactory factory = null;
        IUsersDao usersdao = null;
        Users user = null;
        final String SQL = SQL_SELECT + "WHERE id=?";
        try{
            stmt = conn.prepareStatement(SQL);
            stmt.setInt(1, sqlParams);
            rs = stmt.executeQuery();
            return createCourse(rs);
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
    //Поиск всех курсов, что отвечают users_id
      public Collection<Courses> findStudentRequestWhereIdEquals(Integer sqlParams,Boolean confirm) throws Exception {

        // declare variables
        Connection conn = ConnectionManager.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
      
        final String SQL = SQL_SELECT + " WHERE users_id=? and isConfirm=?";
        try{
            stmt = conn.prepareStatement(SQL);
            stmt.setInt(1, sqlParams);
            stmt.setBoolean(2, confirm);
            rs = stmt.executeQuery();
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
    public Collection<Courses> findAllCourses() throws Exception {

        // declare variables
        Connection conn = ConnectionManager.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        final String SQL = SQL_SELECT+" WHERE isArchive=false and isConfirm=true";
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
    //Поиск не подтвержденных курсов
    @Override
    public Collection<Courses> findAllNotConfirmCourses() throws Exception {

        // declare variables
        Connection conn = ConnectionManager.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        final String SQL = SQL_SELECT+" WHERE isArchive=false and isConfirm=false";
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
    public void insertCourse(Courses course) throws Exception {
        Connection conn = ConnectionManager.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            UsersDao usersdao = new UsersDao();
            //Проверка user_id принадлежит учителю или нет?
            Users user = usersdao.findWhereUserIdEquals(course.getUsers());

            if (!user.getUserType().equals("student")) {
                throw new AddException("Can't chosen student, maybe your chose is teacher");
            }

            Collection<Courses> courses = findStudentWhereUsersIdEquals(course.getUsers());
            for (Courses cr : courses) {
                if (cr.getName().equals(course.getName())) {
                    System.out.println(course.getName());
                    System.out.println(courses.size());
                    throw new AddException("You just chose this course");
                }
            }

            final String SQL = SQL_INSERT;
            stmt = conn.prepareStatement(SQL);
            stmt.setObject(1, course.getName());
            stmt.setObject(2, course.getUsers());
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
    
    //Дальнейший код помечает запись как архивную
    public void setArchive(Integer id) throws Exception{
        Connection conn = ConnectionManager.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
           

            final String SQL = SQL_SET_ARCHIVE;
            stmt = conn.prepareStatement(SQL);
            stmt.setObject(1,id);
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
    //Подтверждение заявки
     public void setConfirm(Integer id) throws Exception{
        Connection conn = ConnectionManager.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
           

            final String SQL = SQL_SET_CONFIRM;
            stmt = conn.prepareStatement(SQL);
            stmt.setObject(1,id);
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
}
