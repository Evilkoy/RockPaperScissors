package com.makson.rockPaperScissors.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @JoinColumn(name = "username")
    private String name;
    private String password;
    private Integer total;
    private Integer wins;
    private Integer ties;
    private Integer loses;
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "users_roles",
            joinColumns = {@JoinColumn(name = "USER_ID", referencedColumnName = "ID")},
            inverseJoinColumns = {@JoinColumn(name = "ROLE_ID", referencedColumnName = "ID")})
    private List<Role> roles;

    public User(String name, String password, Integer total, Integer wins, Integer ties, Integer loses, List<Role> roles) {
        this.name = name;
        this.password = password;
        this.total = total;
        this.wins = wins;
        this.ties = ties;
        this.loses = loses;
        this.roles = roles;
    }
}
