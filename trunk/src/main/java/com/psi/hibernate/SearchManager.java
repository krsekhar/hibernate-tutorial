/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.psi.hibernate;

import com.psi.hibernate.domain.Person;
import com.psi.hibernate.util.HibernateUtil;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.queryParser.QueryParser;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.search.FullTextSession;
import org.hibernate.search.Search;

/**
 *
 * @author chetans
 */
public class SearchManager {
    public List<String> search(String searchThis){
        List<String> result = null;
        try {
            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            session.beginTransaction();
            FullTextSession fullTextSession = Search.getFullTextSession(session);
            Transaction tx = fullTextSession.beginTransaction();
            QueryParser parser = new QueryParser("firstName", new StandardAnalyzer());
            org.apache.lucene.search.Query query = parser.parse(searchThis);
            org.hibernate.Query hibQuery = fullTextSession.createFullTextQuery(query, Person.class);
            result = hibQuery.list();
            tx.commit();
            session.getTransaction().commit();
        } catch (ParseException ex) {
            Logger.getLogger(SearchManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

}
