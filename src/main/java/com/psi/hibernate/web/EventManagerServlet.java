/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.psi.hibernate.web;

import com.psi.hibernate.EventManager;
import com.psi.hibernate.domain.Event;
import com.psi.hibernate.domain.Person;
import com.psi.hibernate.util.HibernateUtil;
import java.io.IOException;
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

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String actionName = req.getParameter("actionparameter");
        if (actionName.equalsIgnoreCase("persontoevent")) {
            EventManager manager = new EventManager();
            List<Person> personList = manager.getPersonList();
            req.setAttribute("personlist", personList);
            List<Event> eventList = manager.getEventList();
            req.setAttribute("eventList", eventList);
            HibernateUtil.getSessionFactory().close();
            req.getRequestDispatcher("/jsp/output.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String actionName = req.getParameter("actionparameter");
        String result;
        boolean personAddedToEvent=false;
        Long id = new Long(1L);
        EventManager manager = new EventManager();
        if (actionName.equalsIgnoreCase("event")) {
            String title =req.getParameter("title");
            id=manager.createAndStoreEvent(title, new Date());
        }
        else if(actionName.equalsIgnoreCase("person")){
            String firstName =req.getParameter("firstname");
            String lastName =req.getParameter("lastname");
            Integer age = Integer.parseInt(req.getParameter("age"));
            id=manager.createAndStorePerson(age, firstName, lastName);
        }
        else{
            Long personId=Long.parseLong(req.getParameter("personlist"));
            Long eventId=Long.parseLong(req.getParameter("eventlist"));
            personAddedToEvent=manager.addPersonToEvent(personId, eventId);
        }
        
        if((id>0) || (personAddedToEvent==true) )
            result="Event successfully added";
        else
            result="Event adidition unsuccessful";

        req.setAttribute("result", result);
        HibernateUtil.getSessionFactory().close();
        req.getRequestDispatcher("/jsp/output.jsp").forward(req, resp);
    }
}
