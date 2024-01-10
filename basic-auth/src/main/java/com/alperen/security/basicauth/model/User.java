package com.alperen.security.basicauth.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Set;

@Data
@Entity
@Table(name = "users")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String username;
    private String password;

    private boolean accountNonExpired;//hesap süresi dolmuş mu
    private boolean isEnabled;//aktif/inaktif
    private boolean accountNonLocked; //hesap kilitli mi
    private boolean credentialsNonExpired;//kimlik bilgileri süresi dolmuş mu


    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    @JoinTable(name = "authorities", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "role", nullable = false)
    @Enumerated(EnumType.STRING)//Role bir enum olduğu için böyle yazdık
    private Set<Role> authorities;
}
