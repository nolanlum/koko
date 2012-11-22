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
package com.caa.koko.auth;

import javax.naming.NamingException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ldap.core.DirContextAdapter;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.AuthenticationUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.caa.koko.data.User;

@Service
public class CalNetUserDetailService implements AuthenticationUserDetailsService<Authentication> {
	private final Logger log = LoggerFactory.getLogger(CalNetUserDetailService.class);
	private SessionFactory sessionFactory;
	private LdapTemplate ldapTemplate;

	@Override
	public UserDetails loadUserDetails(Authentication token) throws UsernameNotFoundException {
		String calnetUID = (String) token.getPrincipal();

		try {
			return lookupCalNetUID(Integer.parseInt(calnetUID));
		} catch (NamingException e) {
			throw new UsernameNotFoundException("LDAP query failed!", e);
		}
	}

	private User lookupCalNetUID(int uid) throws NamingException {
		log.debug("Querying info for CalNet UID: {}", uid);

		Session s = sessionFactory.openSession();
		Object u = s.byId(User.class).load(uid);

		if (u == null) {
			log.debug("No existing user for '{}' found; querying LDAP.", uid);

			DirContextAdapter a = (DirContextAdapter) ldapTemplate.lookup("uid=" + uid + ",ou=people");

			String name = (String) a.getAttributes().get("displayName").get();
			String email = "fake@berkeley.edu"; // TODO
			String phone = "0000000000"; // TODO

			User user = new User(uid);
			user.setEmail(email);
			user.setName(name);
			user.setPhone(phone);

			s.save(user);
			s.flush();
			s.close();

			return user;
		} else {
			log.debug("Mapped {} => '{}'.", uid, ((User) u).getName());

			s.close();
			return (User) u;
		}
	}

	public void setSessionFactory(SessionFactory f) {
		this.sessionFactory = f;
	}

	public void setLdapTemplate(LdapTemplate t) {
		this.ldapTemplate = t;
	}

}
