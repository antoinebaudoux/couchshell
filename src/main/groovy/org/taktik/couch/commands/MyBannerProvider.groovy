/*
 * Copyright 2011-2012 the original author or authors.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.taktik.couch.commands;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.shell.core.CommandMarker;
import org.springframework.shell.core.annotation.CliCommand;
import org.springframework.shell.plugin.support.DefaultBannerProvider;
import org.springframework.stereotype.Component;

/**
 * @author Jarred Li
 *
 */
@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class MyBannerProvider extends DefaultBannerProvider 
				implements CommandMarker {

	@CliCommand(value = ["version"], help = "Displays current CLI version")
	public String getBanner() {
		return '''  _____              __     ______       ____
 / ___/__  __ ______/ /    / __/ /  ___ / / /
/ /__/ _ \\/ // / __/ _ \\  _\\ \\/ _ \\/ -_) / /
\\___/\\___/\\_,_/\\__/_//_/ /___/_//_/\\__/_/_/

'''+"Version: ${version}";

	}

	public String getVersion() {
		return "1.0.0";
	}

	public String getWelcomeMessage() {
		return "Welcome to Couch Shell CLI";
	}
	
	@Override
	public String name() {
		return "CouchShell";
	}
}