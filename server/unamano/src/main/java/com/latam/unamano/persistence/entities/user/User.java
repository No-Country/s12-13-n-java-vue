package com.latam.unamano.persistence.entities.user;

import java.util.Collection;
import java.util.List;

import com.latam.unamano.persistence.entities.addressEntity.Address;
import com.latam.unamano.persistence.entities.task.Task;
import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.latam.unamano.utils.Role;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class User implements UserDetails {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String username;
	private String password;
	private String email;
	private String firstName;
	private String LastName;
	private String phoneNumber;
	@OneToOne(cascade = CascadeType.ALL)
	private Address addresses;
	private String profilePicture;
	@Enumerated(EnumType.STRING)
	private Role role;
	@OneToMany(mappedBy = "client", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Task> requestedTasks;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return List.of(new SimpleGrantedAuthority(role.name()));
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
	
}
