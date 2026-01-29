package com.billing_service.mail;

public class CustomerCreatedEvent {

    private final String email;
    private final String name;

    public CustomerCreatedEvent(String email, String name) {
        this.email = email;
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }
}
