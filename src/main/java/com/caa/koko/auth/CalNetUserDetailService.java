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

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

	@Override
	public UserDetails loadUserDetails(Authentication token) throws UsernameNotFoundException {
		String calnetUID = (String) token.getPrincipal();

		return lookupCalNetUID(Integer.parseInt(calnetUID));
	}

	private User lookupCalNetUID(int uid) {
		log.debug("Querying info for CalNet UID: {}", uid);

		Session s = sessionFactory.openSession();
		Object u = s.byId(User.class).load(uid);

		if (u == null) {
			log.debug("No existing user for '{}' found; querying LDAP.", uid);

			// TODO figure out Spring LDAP and flesh out CalNetUserDetails.
			// In the Near Future, LDAP should only be queried if our ORM doesn't know
			// anything about the user.

			User user = new User(uid);
			user.setEmail("fake@berkeley.edu");
			user.setName("Hurfy Durfy");
			user.setPhone("732-2-DIQUES");

			s.save(user);
			s.close();

			return user;
		} else {
			log.debug("Found '{}' locally, returning cached data.", uid);

			s.close();
			return (User) u;
		}
	}

	public void setSessionFactory(SessionFactory f) {
		this.sessionFactory = f;
	}

}
