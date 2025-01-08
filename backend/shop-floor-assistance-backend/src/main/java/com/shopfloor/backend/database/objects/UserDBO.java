package com.shopfloor.backend.database.objects;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Entity representing a user in the database.
 * Implements UserDetails for Spring Security integration.
 * @author David Todorov (https://github.com/david-todorov)
 */
@Entity
@Table(name = "users")
@Getter
@Setter
public class UserDBO implements UserDetails {

    /**
     * Unique identifier for the user.
     * Auto-generated by the database.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Long id;

    /**
     * Username of the user.
     * Must be unique and cannot be null.
     */
    @Column(unique = true, length = 100, nullable = false)
    private String username;

    /**
     * Password of the user.
     * Cannot be null.
     */
    @Column(nullable = false)
    private String password;

    /**
     * Roles associated with the user.
     * Eagerly fetched and stored in a join table.
     */
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
    private Set<RoleDBO> roles = new HashSet<>();

    /**
     * Default constructor initializing roles as an empty set.
     */
    public UserDBO() {
        this.roles = new HashSet<>();
    }

    /**
     * Constructs a UserDBO with the specified username and password.
     * @param username the username of the user
     * @param password the password of the user
     */
    public UserDBO(String username, String password) {
        this.username = username;
        this.password = password;
    }

    /**
     * Returns the authorities granted to the user.
     * @return a collection of granted authorities
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles.stream()
                .map(role -> new SimpleGrantedAuthority("ROLE_" + role.getName().name())) // Ensure prefix
                .collect(Collectors.toSet());
    }

    /**
     * Returns the password of the user.
     * @return the password
     */
    @Override
    public String getPassword() {
        return password;
    }

    /**
     * Returns the username of the user.
     * @return the username
     */
    @Override
    public String getUsername() {
        return username;
    }

    /**
     * Indicates whether the user's account has expired.
     * @return true if the account is non-expired, false otherwise
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * Indicates whether the user is locked or unlocked.
     * @return true if the account is non-locked, false otherwise
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     * Indicates whether the user's credentials (password) has expired.
     * @return true if the credentials are non-expired, false otherwise
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * Indicates whether the user is enabled or disabled.
     * @return true if the user is enabled, false otherwise
     */
    @Override
    public boolean isEnabled() {
        return true;
    }
}
