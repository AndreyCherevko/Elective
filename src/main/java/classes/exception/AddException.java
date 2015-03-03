/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package classes.exception;

/**
 *
 * @author Андрей
 * Exception that will used with DAOclasses
 */
public class AddException extends Exception {
    private final String detailMessage;
    public AddException(String detail){
        this.detailMessage=detail;
    }
    public String toString(){
        return "AddCourseException "+detailMessage;
    }
}
