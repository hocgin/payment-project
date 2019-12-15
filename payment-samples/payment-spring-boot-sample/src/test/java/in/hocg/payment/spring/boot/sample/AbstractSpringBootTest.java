package in.hocg.payment.spring.boot.sample;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;

@ActiveProfiles("test")
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(
        classes = TestsConfiguration.class,
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
@AutoConfigureMockMvc
public abstract class AbstractSpringBootTest {
    
    @Autowired
    protected MockMvc mockMvc;
    
    @Autowired
    private HttpMessageConverters httpMessageConverters;
    
    protected RestTemplate restTemplate = (new TestRestTemplate()).getRestTemplate();
    
    @PostConstruct
    private void postConstruct() {
        restTemplate.setErrorHandler(new DefaultResponseErrorHandler());
        restTemplate.setMessageConverters(httpMessageConverters.getConverters());
    }
    
//    @Value("${server.port:8080}")
    Integer port = 8080;
    
    protected String url() {
        return "http://localhost:" + port;
    }
}
