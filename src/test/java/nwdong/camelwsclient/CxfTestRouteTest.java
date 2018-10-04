package nwdong.camelwsclient;

import static org.junit.Assert.*;

import org.apache.camel.CamelContext;
import org.apache.camel.EndpointInject;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.AdviceWithRouteBuilder;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.test.spring.CamelSpringBootRunner;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@RunWith(CamelSpringBootRunner.class)
@SpringBootTest
public class CxfTestRouteTest {
	
	@Autowired
	private CamelContext camelContext;
	
	@Autowired
	private ProducerTemplate producerTemplate;
	
    // Creates a mock Camel endpoint to put on the end of the route.
    @EndpointInject(uri = "mock:Result")
    private MockEndpoint mockResultEndpoint;
    
    @Before
    public void setup() throws Exception {
		camelContext.getRouteDefinition("CxfTestRoute")
		.adviceWith(camelContext, new AdviceWithRouteBuilder() {

			@Override
			public void configure() throws Exception {
				// append mock:result to the end of direct:startCurveDownloader
				weaveAddLast().to(mockResultEndpoint);
			}
		});
    }

	@Test
	public void test() throws Exception {
		mockResultEndpoint.expectedMessageCount(1);
		producerTemplate.sendBody("direct:starCxfTestRoute", "this is test info");
		mockResultEndpoint.assertIsSatisfied();
	}
}
