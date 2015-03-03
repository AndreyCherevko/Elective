/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package classes.exception;

/**
 *
 * @author Андрей
 */
public class DaoFactoryException extends Exception{
    private String detail;
    public DaoFactoryException(String detail){
        this.detail=detail;
    }
    @Override
    public String toString(){
        return "Don't have the chosen databaseFactory "+detail; 
    }
}
