/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.psi.hibernate.web;

import com.psi.hibernate.EventManager;
import com.psi.hibernate.domain.Event;
import com.psi.hibernate.domain.Person;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Insect
 */
public class EventManagerServlet extends HttpServlet {

//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        String actionName = req.getParameter("actionparameter");
//        if (actionName.equalsIgnoreCase("persontoevent")) {
//            EventManager manager = new EventManager();
//            List<Person> personList = manager.getPersonList();
//            req.setAttribute("personlist", personList);
//            List<Event> eventList = manager.getEventList();
//            req.setAttribute("eventList", eventList);
//            req.getRequestDispatcher("/jsp/output.jsp").forward(req, resp);
//            //This is commented becasue if it's not commited the app won't wor; :D \m/
//            //https://forum.hibernate.org/viewtopic.php?f=1&t=996962&start=0
//            //TODO: Read more. Find out why.
//
//            //HibernateUtil.getSessionFactory().close();
//        }
//    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String actionName = req.getParameter("actionparameter");
        if(actionName==null){
            actionName=getServletConfig().getInitParameter("actionparameter");
        }
        String result="";
        boolean personAddedToEvent=false;
        Long id = new Long(1L);
        EventManager manager = new EventManager();

        try{
            if (actionName.equalsIgnoreCase("event")) {
            String title =req.getParameter("title");
            DateFormat dateFormat = new SimpleDateFormat("mm:DD:yyyy");
            id=manager.createAndStoreEvent(title, dateFormat.format(new Date()));
            if(id>0)
                result=title+" successfully added";
            else
                result="Duplicate User."+title+" adidition unsuccessful.";
        }
        else if(actionName.equalsIgnoreCase("person")){
            String firstName =req.getParameter("firstname");
            String lastName =req.getParameter("lastname");
            Integer age = Integer.parseInt(req.getParameter("age"));
            id=manager.createAndStorePerson(age, firstName, lastName);
            if(id>0)
                result=firstName+" successfully added";
            else
                result="Duplicate User."+firstName+" adidition unsuccessful.";
        }
        else if(actionName.equalsIgnoreCase("index")){
            List<Person> personList=manager.getPersonList();
            List<Event> eventList=manager.getEventList();
            req.setAttribute("personlist", personList);
            req.setAttribute("eventlist", eventList);
            req.getRequestDispatcher("/jsp/addpersontoevent.jsp").forward(req, resp);
            return;
        }
        else{
            Long personId=Long.parseLong(req.getParameter("personlist"));
            Long eventId=Long.parseLong(req.getParameter("eventlist"));
            personAddedToEvent=manager.addPersonToEvent(personId, eventId);
            if((personAddedToEvent==true) )
                result=manager.getPerson(personId)+" has been successfully added to "+manager.getEventTitle(eventId);
            else
                result=manager.getPerson(personId)+" COULD NOT be added to "+manager.getEventTitle(eventId);
        }
        }catch(Exception e){
            System.out.println("Exception: "+e.getMessage());
            e.printStackTrace();
            result="Operation Unsuccessful.";
        }

        req.setAttribute("result", result);
        req.getRequestDispatcher("/jsp/output.jsp").forward(req, resp);
        return;
        //This is commented becasue if it's not commited the app won't wor; :D \m/ 
        //https://forum.hibernate.org/viewtopic.php?f=1&t=996962&start=0
        //TODO: Read more. Find out why.

        //HibernateUtil.getSessionFactory().close();
    }
}
