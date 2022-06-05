package ru.kata.spring.boot_security.demo.models;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;

@Entity

@Table(name = "users")
@Data
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name ="name")
    private String name;
    @Column(name ="last_name")
    private String last_name;
    @Column(name ="age")
    private Byte age;
    @Column(name ="email")
    private String email;
    @Column(name ="username")
    private String username;
    @Column(name ="password")
    private String password;

    @ManyToMany //(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinTable (name = "users_roles", joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Collection<Role> roles;


    public User(String name, String last_name, Byte age, String email) {
        this.name = name;
        this.last_name = last_name;
        this.age = age;
        this.email = email;
    }


    public User() {
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", last_name='" + last_name + '\'' +
                ", age=" + age + '\'' +
                ", email=" + email +
                '}'
                ;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}
