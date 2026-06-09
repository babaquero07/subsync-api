package com.subsync.app.domain.model;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

public class User {
    private final String id;
    private final String email;
    private final String password;  // hashed
    private final String firstName;
    private final String lastName;
    private final Set<Role> roles;
    private final boolean active;
    private final LocalDateTime createdAt;

    // Constructor privado — se usa el builder
    private User(Builder builder) {
        this.id = builder.id;
        this.email = builder.email;
        this.password = builder.password;
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.roles = builder.roles;
        this.active = builder.active;
        this.createdAt = builder.createdAt;
    }

    // Factory method para nuevo registro
    public static User register(String email, String hashedPassword, String firstName, String lastName) {
        return new Builder()
                .id(UUID.randomUUID().toString())
                .email(email)
                .password(hashedPassword)
                .firstName(firstName)
                .lastName(lastName)
                .roles(Set.of(Role.USER))
                .active(true)
                .createdAt(LocalDateTime.now())
                .build();
    }

    // Getters
    public String getId()              { return id; }
    public String getEmail()           { return email; }
    public String getPassword()        { return password; }
    public String getFirstName()       { return firstName; }
    public String getLastName()        { return lastName; }
    public Set<Role> getRoles()        { return roles; }
    public boolean isActive()          { return active; }
    public LocalDateTime getCreatedAt(){ return createdAt; }

    public String getFullName() {
        return firstName + " " + lastName;
    }

    // Builder
    public static class Builder {
        private String id;
        private String email;
        private String password;
        private String firstName;
        private String lastName;
        private Set<Role> roles;
        private boolean active;
        private LocalDateTime createdAt;

        public Builder id(String id)               { this.id = id; return this; }
        public Builder email(String email)         { this.email = email; return this; }
        public Builder password(String password)   { this.password = password; return this; }
        public Builder firstName(String fn)        { this.firstName = fn; return this; }
        public Builder lastName(String ln)         { this.lastName = ln; return this; }
        public Builder roles(Set<Role> roles)      { this.roles = roles; return this; }
        public Builder active(boolean active)      { this.active = active; return this; }
        public Builder createdAt(LocalDateTime dt) { this.createdAt = dt; return this; }

        public User build() { return new User(this); }
    }
}
