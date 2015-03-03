/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes.idao;

import classes.entity.Courses;
import java.util.Collection;

/**
 *
 * @author Андрей
 */
public interface ICoursesDao {

    /**
     *
     * @param Integer users_id
     * @return Courses return Courses where id=users_id
     * @throws Exception
     */
    public Collection<Courses> findStudentWhereUsersIdEquals(Integer sqlParams) throws Exception;

    /**
     * @return Collection<Courses>, all records from course
     * @throws Exception
     */
    public Collection<Courses> findAllCourses() throws Exception;

    /**
     * @param Courses New Courses record insert Course object in course table
     * @throws Exception, if !UserType.equals("student")
     */
    public void insertCourse(Courses course) throws Exception;
    

    public Collection<Courses> findStudentRequestWhereIdEquals(Integer sqlParams, Boolean confirm) throws Exception;

    public void setConfirm(Integer id) throws Exception;

    public Collection<Courses> findAllNotConfirmCourses() throws Exception;
}
