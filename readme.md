# how to run
firstly, start springboot-example-soap-ws-producer 

then, run unit test CxfTestRouteTest

should see below in log
	CxfTestRoute                             : the currency of Spain is EUR

# Stub code from WSDL
1. to generate stub code from wsdl
mvn install
2. to add the generated src folder to project, so IDE is aware of it