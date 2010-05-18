/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.psi.hibernate.domain;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author chetans
 */
public class Event {

    private Long id;

    private String title;
    private Date date;
    private Set participants = new HashSet();
    private Person person;

    public Event() {}

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public Long getId() {
        return id;
    }

    private void setId(Long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

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
}
