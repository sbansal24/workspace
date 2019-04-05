package com.test.jpa.entity;

import javax.persistence.Embeddable;

@Embeddable
public class Contact {
    private int contactId;
    private String name;

    public int getContactId() {
        return contactId;
    }

    public void setContactId(int contactId) {
        this.contactId = contactId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
