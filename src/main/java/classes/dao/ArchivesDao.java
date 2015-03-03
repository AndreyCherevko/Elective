/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes.dao;

import classes.connection.ConnectionManager;
import classes.entity.Archives;
import classes.idao.IArchivesDao;
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
public class ArchivesDao implements IArchivesDao {

    protected final String SQL_SELECT = "SELECT id,courses_id,mark FROM archives ";
    protected final String SQL_INSERT = "INSERT INTO archives(courses_id,mark) VALUES(?,?) ";
    protected final String COLUMN_ID = "id";
    protected final String COLUMN_COURSE = "courses_id";
    protected final String COLUMN_MARK = "mark";

    protected Archives createArchive(ResultSet rs) throws SQLException {
        Archives archive = new Archives();
        rs.next();
        archive.setId(rs.getInt(COLUMN_ID));
        archive.setCourse(rs.getInt(COLUMN_COURSE));
        archive.setMark(rs.getInt(COLUMN_MARK));
        return archive;
    }

    protected Collection<Archives> fetchMultiResults(ResultSet rs) throws SQLException {
        Collection resultList = new ArrayList();
        while (rs.next()) {
            Archives dto = createArchive(rs);
            resultList.add(dto);
        }

        return resultList;
    }
//Создание коллекции со всеми архивными записями
    @Override
    public Collection<Archives> findAllArchives() throws Exception {

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

    // Поиск архивной записи по courses_id
    @Override
    public Archives findWhereCoursesIdEquals(Integer id) throws Exception {

        // declare variables
        Connection conn = ConnectionManager.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        final String SQL = SQL_SELECT + " WHERE courses_id=?";
        try {
            stmt = conn.prepareStatement(SQL);
            stmt.setObject(1, id);
            rs = stmt.executeQuery();
            // fetch the results
            return createArchive(rs);
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

    //Вставка архива
    @Override
    public void insertArchive(Archives archive) throws Exception {
        Connection conn = ConnectionManager.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        CoursesDao courses = new CoursesDao();

        try {
          
            final String SQL = SQL_INSERT;
            stmt = conn.prepareStatement(SQL);
            stmt.setObject(1, archive.getCourse());
            stmt.setObject(2, archive.getMark());
            //Добавление новой архивной записи
            stmt.executeUpdate();
            //текущая запись курса устанавливаеться архивной
            courses.setArchive(archive.getCourse());

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
