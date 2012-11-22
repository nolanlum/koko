/**
 * Copyright (c) 2012 Nolan Lum <nol888@gmail.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.caa.koko.data;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
@Table
public class User implements UserDetails {
	private static final long serialVersionUID = 7412888268173756613L;
	private static final Collection<GrantedAuthority> USER_AUTHORITIES = AuthorityUtils.createAuthorityList("ROLE_USER");
	private static final Collection<GrantedAuthority> ADMIN_AUTHORITIES = AuthorityUtils.createAuthorityList("ROLE_SUPERVISOR");

	private int uid;
	private String email, name, phone;
	private boolean isAdmin;

	public User(int calnetUid) {
		this.uid = calnetUid;
	}

	@Id
	@Column(unique = true, nullable = false)
	public int getUserId() {
		return this.uid;
	}

	public void setUserId(int uid) {
		this.uid = uid;
	}

	@Column(nullable = true)
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(nullable = false)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(nullable = true)
	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Column(name = "admin", nullable = false)
	public boolean isAdmin() {
		return this.isAdmin;
	}

	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}

	@Override
	@Transient
	public String getPassword() {
		// We don't handle passwords.
		return "";
	}

	@Override
	@Transient
	public String getUsername() {
		return "" + uid;
	}

	@Override
	@Transient
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return isAdmin ? ADMIN_AUTHORITIES : USER_AUTHORITIES;
	}

	@Override
	@Transient
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	@Transient
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	@Transient
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	@Transient
	public boolean isEnabled() {
		return true;
	}

}
