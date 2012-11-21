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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.AuthenticationUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class CalNetUserDetailService implements AuthenticationUserDetailsService<Authentication> {
	Logger log = LoggerFactory.getLogger(CalNetUserDetailService.class);

	@Override
	public UserDetails loadUserDetails(Authentication token) throws UsernameNotFoundException {
		String calnetUID = token.getName();

		return lookupCalNetUID(calnetUID);
	}

	private CalNetUserDetails lookupCalNetUID(String uid) {
		// TODO this should actually do something.
		log.debug("Querying info for CalNet UID: {}", uid);

		// TODO figure out Spring LDAP and flesh out CalNetUserDetails.
		// In the Near Future, LDAP should only be queried if our ORM doesn't know
		// anything about the user.

		return new CalNetUserDetails(uid);
	}

}
