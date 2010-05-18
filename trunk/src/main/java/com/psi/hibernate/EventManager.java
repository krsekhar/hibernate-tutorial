/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.psi.hibernate;


import org.hibernate.Session;

import java.util.*;

import com.psi.hibernate.domain.Event;
import com.psi.hibernate.domain.Person;
import com.psi.hibernate.util.HibernateUtil;

public class EventManager {

    public static void main(String[] args) {
        EventManager mgr = new EventManager();

        if (args[0].equals("store")) {
            mgr.createAndStoreEvent("My Event", new Date());
        }
        else if (args[0].equals("list")) {
            List events = mgr.listEvents();
            for (int i = 0; i < events.size(); i++) {
                Event theEvent = (Event) events.get(i);
                System.out.println(
                        "Event: " + theEvent.getTitle() + " Time: " + theEvent.getDate()
                );
            }
        }
        else if (args[0].equals("addpersontoevent")) {
            Long eventId = mgr.createAndStoreEvent("My Event", new Date());
            Long personId = mgr.createAndStorePerson((int)Math.random()%50,"Foo", "Bar");
            mgr.addPersonToEvent(personId, eventId);
            //System.out.println("Added person " + personId + " to event " + eventId);
        }

        HibernateUtil.getSessionFactory().close();
    }

    private Long createAndStoreEvent(String title, Date theDate) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();

        Event theEvent = new Event();
        theEvent.setTitle(title);
        theEvent.setDate(theDate);
        session.save(theEvent);

        //Fetch auto generated Id
        Long id=(Long)session.createQuery("select e.id from Event e where e.title= :title and e.date= :theDate")
                .setParameter("title", title)
                .setTimestamp("theDate", theDate)
                .uniqueResult();
        session.getTransaction().commit();
        return id;
    }

    private Long createAndStorePerson(int age,String firstName, String lastName) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();

        Person thePerson = new Person();
        thePerson.setAge(age);
        thePerson.setFirstname(firstName);
        thePerson.setLastname(lastName);
        session.save(thePerson);

        //Fetch auto generated Id
        Long id=(Long)session.createQuery("select p.id from Person p where p.age= :age")
                .setParameter("age", age)
                .uniqueResult();
        session.getTransaction().commit();
        return id;
    }

    private List listEvents() {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        List result = session.createQuery("from Event").list();
        session.getTransaction().commit();
        return result;
    }

    private void addPersonToEvent(Long personId, Long eventId) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();

        Person aPerson = (Person) session.load(Person.class, personId);
        Event anEvent = (Event) session.load(Event.class, eventId);
        aPerson.getEvents().add(anEvent);

        session.getTransaction().commit();
    }

}
