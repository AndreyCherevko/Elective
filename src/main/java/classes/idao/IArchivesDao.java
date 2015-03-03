/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes.idao;

import classes.entity.Archives;
import java.util.Collection;

/**
 *
 * @author Андрей
 */
public interface IArchivesDao {

    /**
     *
     * @return Collection<Archives> all records from archives;
     */
    public Collection<Archives> findAllArchives() throws Exception;

    /**
     * @param Archives New Archives record, insert record into archive and set
     * value in column users_isArchive true
     */
    public void insertArchive(Archives archive) throws Exception;

    public Archives findWhereCoursesIdEquals(Integer id) throws Exception;

}
