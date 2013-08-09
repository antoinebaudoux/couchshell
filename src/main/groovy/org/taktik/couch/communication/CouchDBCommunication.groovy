package org.taktik.couch.communication

import groovy.json.JsonSlurper
import groovyx.net.http.ContentType
import groovyx.net.http.HTTPBuilder
import groovyx.net.http.Method
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import org.taktik.couch.state.ShellState

/** Created by aduchate on 09/08/13, 15:30 */
@Component
class CouchDBCommunication {
	@Autowired
	ShellState shellState

	def slurper = new JsonSlurper()

	List<String> listAllViews() {
		List<String> viewNames = []
		new URL("http://${shellState.serverAddress}/${shellState.selectedDatabase}/_all_docs?startkey=\"_design/\"&endkey=\"_design0\"&include_docs=true").withReader {
			def result = slurper.parse(it)

			result.rows.each { d->
				def doc = d.doc._id.replaceAll('_design/','')
				((Map)d.doc.views).keySet().each {k-> viewNames << "${doc}/${k}"}}
		}

		return viewNames
	}

	List<List<String>> getIds(String view) {
		def comps = view.split('/')
		def result = null
		new URL("http://${shellState.serverAddress}/${shellState.selectedDatabase}/_design/${comps[0]}/_view/${comps[1]}?include_docs=true").withReader {
			def rows = slurper.parse(it).rows
			result = rows.collect {[it.doc._id, it.doc._rev]}
		}
		return result
	}

	String postBulk(def json) {
		def returnString
		def http = new HTTPBuilder( "http://${shellState.serverAddress}/${shellState.selectedDatabase}/_bulk_docs" )
		http.request( Method.POST, ContentType.JSON ) { req ->
			body = json
			response.success = { resp, j ->
				returnString = j as String
			}
		}
		return returnString
	}
}
