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
		String v = view.trim()
		couchDBCommunication.postBulk([docs:couchDBCommunication.getIds(v).collect {[_id:it[0],_rev:it[1],_deleted:true]}])
	}

	@CliCommand(value = "delete ids", help = "Delete documents with ids")
	public String deleteIds(@CliOption(key = ["","ids"], mandatory = true, help = "The ids as [id1:rev1,id2:rev2]", optionContext = "couch-ids") final String ids) {
		String i = ids.trim()
		if (i.startsWith('[') && i.endsWith(']')) {
			couchDBCommunication.postBulk([docs:i[1..-2].split(/,/).collect {[_id:it.split(/:/)[0],_rev:it.split(/:/)[1],_deleted:true]}])
		}
	}

	@CliCommand(value = "list ids", help = "List ids from view")
	public String listIdsFromViews(@CliOption(key = ["","view"], mandatory = true, help = "The view", optionContext = "couch-view") final String view) {
		String v = view.trim()
		return couchDBCommunication.getIds(v).collect {"${it[0]}:${it[1]}"}.join(",")
	}
}
