package org.taktik.couch.commands

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.shell.core.CommandMarker
import org.taktik.couch.state.ShellState

/** Created by aduchate on 09/08/13, 15:02 */
class CouchBase implements CommandMarker {
	ShellState shellState

	@Autowired
	void setShellState(ShellState shellState) {
		this.shellState = shellState
	}
}
