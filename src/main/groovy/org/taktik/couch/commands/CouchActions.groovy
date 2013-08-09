package org.taktik.couch.commands

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.shell.core.annotation.CliAvailabilityIndicator
import org.springframework.shell.core.annotation.CliCommand
import org.springframework.shell.core.annotation.CliOption
import org.springframework.stereotype.Component
import org.taktik.couch.communication.CouchDBCommunication
/** Created by aduchate on 09/08/13, 15:00 */
@Component
class CouchActions extends CouchBase {
	@Autowired
	CouchDBCommunication couchDBCommunication

	@CliAvailabilityIndicator(["delete"])
	public boolean isDeleteAvailable() {
		return shellState.serverAddress && shellState.selectedDatabase;
	}

	@CliCommand(value = "delete from-view", help = "Delete documents from view")
	public String deleteView(@CliOption(key = ["","view"], mandatory = true, help = "The view", optionContext = "couch-view") final String view) {
		couchDBCommunication.postBulk([docs:couchDBCommunication.getIds(view).collect {[_id:it[0],_rev:it[1],_deleted:true]}])
	}

}
