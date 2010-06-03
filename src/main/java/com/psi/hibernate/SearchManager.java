/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.psi.hibernate;

import com.psi.hibernate.domain.Event;
import com.psi.hibernate.domain.Person;
import com.psi.hibernate.util.HibernateUtil;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.queryParser.MultiFieldQueryParser;
import org.apache.lucene.queryParser.ParseException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.search.FullTextSession;
import org.hibernate.search.Search;

/**
 *
 * @author chetans
 */
public class SearchManager {

    public List search(String searchThis) {
        List result = null;
        try {
            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            session.beginTransaction();
            FullTextSession fullTextSession = Search.getFullTextSession(session);
            Transaction tx = fullTextSession.beginTransaction();
            List<Person> personList = session.createQuery("from Person").list();
            for (Person person : personList) {
                fullTextSession.index(person);
            }
            List<Event> eventList = session.createQuery("from Event").list();
            for (Event event : eventList) {
                fullTextSession.index(event);
            }
            String[] fields={"firstName","lastName","title"};
            MultiFieldQueryParser parser = new MultiFieldQueryParser(fields, new StandardAnalyzer());
            org.apache.lucene.search.Query query = parser.parse(searchThis+"*");
            org.hibernate.Query hibQuery = fullTextSession.createFullTextQuery(query, Person.class,Event.class);
            result = hibQuery.list();
            tx.commit();
//            session.getTransaction().commit();
        } catch (ParseException ex) {
            Logger.getLogger(SearchManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    private void indexExistingData() {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        FullTextSession fullTextSession = Search.getFullTextSession(session);
        Transaction tx = fullTextSession.beginTransaction();
        List<Person> personList = session.createQuery("from Person").list();
        for (Person person : personList) {
            fullTextSession.index(person);
        }
        tx.commit();
        //session.getTransaction().commit();
    }
}
