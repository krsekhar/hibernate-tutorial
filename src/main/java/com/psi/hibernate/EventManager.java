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
import org.apache.log4j.Logger;
import org.hibernate.NonUniqueResultException;

public class EventManager {
    private Logger log = Logger.getLogger(EventManager.class);
/*
    public static void main(String[] args) {
        EventManager mgr = new EventManager();

        if (args[0].equals("store")) {
            mgr.createAndStoreEvent("My Event", new Date());
        }
        else if (args[0].equals("list")) {
            List events = mgr.getEventList();
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
        }
        else if (args[0].equals("addEmailToPerson")) {
            Long personId = mgr.createAndStorePerson((int)Math.random()%50,"Foo", "Bar");
                mgr.addEmailToPerson(personId, "myeamail@host.com");
        }

        HibernateUtil.getSessionFactory().close();
    }
*/
    public Long createAndStoreEvent(String title, String theDate) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();

        if(isDuplicateEvent(session,title,theDate)){
            return new Long (-1L);
        }
        else{
            Event theEvent = new Event();
            theEvent.setTitle(title);
            theEvent.setDate(theDate);
            session.save(theEvent);
        }
        //Fetch auto generated Id
        Long id=(Long)session.createQuery("select e.id from Event e where e.title= :title and e.date= :theDate")
                .setParameter("title", title)
                .setParameter("theDate", theDate)
                .uniqueResult();
        session.getTransaction().commit();
        return id;
    }

    public Long createAndStorePerson(int age,String firstName, String lastName) throws Exception{
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();

        if(isDuplicatePerson(session,age,firstName,lastName)){
            return new Long(-1L);
        }
        else{
            Person thePerson = new Person();
            thePerson.setAge(age);
            thePerson.setFirstName(firstName);
            thePerson.setLastName(lastName);
            Long id=(Long)session.save(thePerson);
            return id;
        }
        //Fetch auto generated Id
//        Long id=(Long)session.createQuery("select p.id from Person p where p.age= :age and " +
//                "p.firstName=:firstName and p.lastName=:lastName")
//                .setParameter("age", age)
//                .setParameter("lastName", lastName)
//                .setParameter("firstName", firstName)
//                .uniqueResult();
//        session.getTransaction().commit();
//        return id;
    }

    public List getEventList() {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        List result = session.createQuery("from Event").list();
        session.getTransaction().commit();
        return result;
    }

    public boolean addPersonToEvent(Long personId, Long eventId) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();

        Person aPerson = (Person) session.load(Person.class, personId);
        Event anEvent = (Event) session.load(Event.class, eventId);
        boolean result=aPerson.getEvents().add(anEvent);

        session.getTransaction().commit();
        return result;
    }

    private void addEmailToPerson(Long personId, String emailAddress) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();

        Person aPerson = (Person) session.load(Person.class, personId);
        // adding to the emailAddress collection might trigger a lazy load of the collection
        aPerson.getEmailAddresses().add(emailAddress);

        session.getTransaction().commit();
    }

    public List<Person> getPersonList() {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        List result = session.createQuery("from Person").list();
        session.getTransaction().commit();
        return result;
    }

    private boolean isDuplicatePerson(Session session, int age, String firstName, String lastName) {
        try{
            session.createQuery("select p from Person p where p.firstName=:firstName and " +
                    "p.lastName=:lastName")
                    .setParameter("firstName", firstName)
                    .setParameter("lastName", lastName)
                    .uniqueResult();
        }catch(NonUniqueResultException NURE){
            log.error("No Unique result available"+NURE.getMessage());
            NURE.printStackTrace();
            return true;
        }
        return false;
    }

    private boolean isDuplicateEvent(Session session, String title, String date) {
        try{
            session.createQuery("select e from Event e where e.title=:title and e.date=:date")
                    .setParameter("title", title)
                    .setParameter("date", date)
                    .uniqueResult();
        }catch(NonUniqueResultException NURE){
            log.error("No Unique result available"+NURE.getMessage());
            NURE.printStackTrace();
            return true;
        }
        return false;
    }

    public String getEventTitle(Long eventId) {
        Session session=HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Event event=(Event) session.load(Event.class, eventId);
        return event.getTitle();
    }

    public String getPerson(Long personId) {
        Session session=HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Person person=(Person) session.load(Person.class, personId);
        return person.getFirstName()+" "+person.getLastName();
    }

}
