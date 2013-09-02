couchshell
==========

A shell to perform actions efficiently on a CouchDB database

To run:

gradle -q run


Example session (with example completion).

Version: 1.0.0
Welcome to Couch Shell CLI

?:?>server connect --server localhost
Connect to localhost:5984 OK
localhost:5984:?>d

date        dbselect    delete      

localhost:5984:?>dbselect --database icure
icure selected
localhost:5984:icure>

localhost:5984:icure>delete from-view --view 
delete from-view --view 
required --view: The view; no default value
localhost:5984:icure>delete from-view --view 

Contact/all                                                Contact/by_hcparty_form                            
Contact/by_opened_patient_hcparty                          Contact/by_patient_service_tag                     
Dashboard/by_guid                                          Filter/all                                         
Filter/by_name_entity                                      Food/all                                           

localhost:5984:icure>delete from-view --view Filter/all

