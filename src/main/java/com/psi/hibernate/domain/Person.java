/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.psi.hibernate.domain;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Id;
import org.hibernate.search.annotations.DocumentId;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Index;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.Store;

@Indexed
public class Person {

    private Long id;
    private int age;
    private String firstName;
    private String lastName;
    private Set events = new HashSet();
    private Set emailAddresses = new HashSet();

    public Set getEmailAddresses() {
        return emailAddresses;
    }

    public void setEmailAddresses(Set emailAddresses) {
        this.emailAddresses = emailAddresses;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Field(index = Index.TOKENIZED, store = Store.YES)
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstname) {
        this.firstName = firstname;
    }

    @DocumentId
    public Long getId() {
        return id;
    }

    private void setId(Long id) {
        this.id = id;
    }

    @Field(index = Index.TOKENIZED, store = Store.YES)
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastname) {
        this.lastName = lastname;
    }

    public Set getEvents() {
        return events;
    }

    public void setEvents(Set events) {
        this.events = events;
    }

    public void addToEvent(Event event) {
        this.getEvents().add(event);
        event.getParticipants().add(this);
    }

    public void removeFromEvent(Event event) {
        this.getEvents().remove(event);
        event.getParticipants().remove(this);
    }

    public Person() {}

    @Override
    public String toString() {
        return firstName+" "+lastName;
    }


}
