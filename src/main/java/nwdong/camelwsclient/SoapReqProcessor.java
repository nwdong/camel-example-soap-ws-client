package nwdong.camelwsclient;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.stereotype.Component;

import io.spring.guides.gs_producing_web_service.GetCountryRequest;

@Component
public class SoapReqProcessor implements Processor{

	@Override
	public void process(Exchange exchange) throws Exception {
		
		GetCountryRequest req = new GetCountryRequest();
		
		req.setName("Spain");
		
		exchange.getIn().setBody(req);
	}

}
