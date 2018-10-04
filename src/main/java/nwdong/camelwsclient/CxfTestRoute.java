package nwdong.camelwsclient;

import javax.jws.WebService;

import org.apache.camel.CamelContext;
import org.apache.camel.component.cxf.CxfEndpoint;
import org.apache.camel.spring.SpringRouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CxfTestRoute extends SpringRouteBuilder{
	
	@Autowired
	private CamelContext camelContext;

	@Autowired
	SoapReqProcessor soapReqProcessor;
	
	@Autowired
	SoapRespProcessor soapRespProcessor;
	
	@Override
	public void configure() throws Exception {
		
		CxfEndpoint cxfEndpoint = getEndpoint();
		
		from("direct:starCxfTestRoute").routeId("CxfTestRoute")
			.process(soapReqProcessor)
			.to(cxfEndpoint)
			.process(soapRespProcessor)
			.log("${body}");
	}
	
	public CxfEndpoint getEndpoint() throws ClassNotFoundException{
		
//		setSSL(); // enable it if HTTPs required
		
		CxfEndpoint cxfEndpoint = new CxfEndpoint();
		// WSDL URL: http://localhost:8080/ws/countries.wsdl
		cxfEndpoint.setAddress("http://localhost:8080/ws/countries"); 
		// CountriesPort is declared with @WebService
		cxfEndpoint.setServiceClass("io.spring.guides.gs_producing_web_service.CountriesPort");
		cxfEndpoint.setCamelContext(camelContext);
		
		return cxfEndpoint;
		
	}	
	
	// tried to use cxf.xml to config http:conduit
	// the following issues stop using cxf.xml (refer to doc\cxf endpoint memo)
	// 1. only cxf.xml in classpath can be loaded by cxf component
	// 2. placeholder not working in cxf.xml
	private void setSSL() {
		
		String keystoreFullpath = "path to keystore file";
		
		// load SSL config
		System.setProperty("javax.net.ssl.trustStore", keystoreFullpath);
		System.setProperty("javax.net.ssl.trustStorePassword", "trust keystore password");
		System.setProperty("javax.net.ssl.keyStore", keystoreFullpath);
		System.setProperty("javax.net.ssl.keyStorePassword", "keystore password");

	}

}
