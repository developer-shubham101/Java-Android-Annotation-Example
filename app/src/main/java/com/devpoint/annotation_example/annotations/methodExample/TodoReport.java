/*
 * This Class demonstrates use of annotations using reflection.
 * 
 * @author Yashwant Golecha (ygolecha@gmail.com)
 * @version 1.0
 * 
 */

package com.devpoint.annotation_example.annotations.methodExample;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class TodoReport {
    public TodoReport() {
        super();
    }

  /*  public static void main(String[] args) {
        getTodoReportForBusinessLogic();
    }*/

    /**
     * This method iterates through all messages of BusinessLogic class and fetches annotations defined on each of them.
     * After that it displays the information from annotation accordingly.
     */
    public static void getTodoReportForBusinessLogic(Object obj) throws InvocationTargetException, IllegalAccessException {
        
        Class businessLogicClass = BusinessLogic.class;
        for(Method method : businessLogicClass.getMethods()) {
            Todo todoAnnotation = method.getAnnotation(Todo.class);
            if(todoAnnotation != null) {
                method.invoke(obj);
                System.out.println(" Method Name : " + method.getName());
//                System.out.println(" Author : " + todoAnnotation.author());
                System.out.println(" Priority : " + todoAnnotation.priority());
                System.out.println(" Status : " + todoAnnotation.status());
                System.out.println(" --------------------------- ");
            }
        }
    }
}
