/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes.idao;

import classes.entity.CoursesList;
import java.util.Collection;

/**
 *
 * @author Андрей
 */
public interface ICoursesListDao {
    /**
     * 
     * @return Collection<CoursesList> all records from CoursesList
     * @throws Exception 
     */
    public Collection<CoursesList> findAllCoursesList() throws Exception;
    /**
     * 
     * @param String sqlParams name
     * @return CoursesList where name.equals("name"), if record not exist return null;
     * @throws Exception 
     */
    public CoursesList findWhereCoursesNameEquals(String sqlParams) throws Exception;
    /**
     * 
     * @param CoursesList course New Course record,
     * insert new Course record
     * @throws Exception 
     */
    public void insertCourselist(CoursesList course) throws Exception;

}
