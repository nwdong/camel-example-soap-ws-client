package nwdong.camelwsclient;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.stereotype.Component;

import io.spring.guides.gs_producing_web_service.GetCountryRequest;
import io.spring.guides.gs_producing_web_service.GetCountryResponse;

@Component
public class SoapRespProcessor implements Processor{

	@Override
	public void process(Exchange exchange) throws Exception {
		
		GetCountryResponse resp = exchange.getIn().getBody(GetCountryResponse.class);
		
		exchange.getIn().setBody("the currency of " + resp.getCountry().getName() + " is " + resp.getCountry().getCurrency());
	}

}
