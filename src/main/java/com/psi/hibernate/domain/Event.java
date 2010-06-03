/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.psi.hibernate.domain;

import java.util.HashSet;
import java.util.Set;
import org.hibernate.search.annotations.DocumentId;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Index;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.Store;

/**
 *
 * @author chetans
 */
@Indexed
public class Event {

    private Long id;

    private String title;
    private String date;
    private Set participants = new HashSet();
    private Person person;

    public Event() {}

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    @DocumentId
    public Long getId() {
        return id;
    }

    private void setId(Long id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Field(index=Index.TOKENIZED,store=Store.YES)
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Set getParticipants() {
        return participants;
    }

    public void setParticipants(Set participants) {
        this.participants = participants;
    }

    @Override
    public String toString() {
        return title+" "+date;
    }


}
