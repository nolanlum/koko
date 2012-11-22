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
package com.caa.koko;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.caa.koko.data.User;

@Controller
public class SimplePageController {

	@RequestMapping(value="/")
	public String home(Model model) {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String uid = "somepony";
		String name = "Hickory Dickory Dock Diques";

		if (principal instanceof User) {
			User u = (User) principal;
			uid = u.getUsername();
			name = u.getName();
		}

		model.addAttribute("uid", uid);
		model.addAttribute("name", name);
		return "home";
	}

	@RequestMapping(value = "/auth/loggedout")
	public String loggedout() {
		return "auth/loggedout";
	}

	@RequestMapping(value = "/auth/failed")
	public String failed() {
		return "auth/failed";
	}
}
