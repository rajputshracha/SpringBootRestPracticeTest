package com.java.polling.PollingWeb;

import com.java.polling.PollingWeb.controller.PollServiceResource;
import com.java.polling.PollingWeb.entity.PollChoices;
import com.java.polling.PollingWeb.entity.PollQuestion;
import com.java.polling.PollingWeb.repository.PollChoicesRepository;
import com.java.polling.PollingWeb.repository.PollQuestionRepository;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@WebMvcTest(value = PollingWebApplication.class, secure = false)
public class PollingTest {

    @Autowired
    private MockMvc mvc;

    String exampleCourseJson = "{\\r\\n            \\\"question\\\": \\\"Favourite Book?\\\",\\r\\n            \\\"choices\\\": [\\r\\n                \\\"Love\\\",\\r\\n                \\\"Life\\\"\\r\\n                \\r\\n            ]\\r\\n        }\\r\\n";

    @MockBean
    private RestTemplate template;


    @Ignore
    public void createQuestion() throws Exception {
        PollChoices choice1 = new PollChoices();
        choice1.setChoice("Brisbane");
        choice1.setVotes(10);

        PollChoices choice2 = new PollChoices();
        choice2.setChoice("Sydney");
        choice2.setVotes(5);

        Set<PollChoices> choice = new HashSet<PollChoices>();

        PollQuestion mockCourse = new PollQuestion();
        mockCourse.setQuestion("Favourite City");
        mockCourse.setPublished_at(new Date());
        mockCourse.setChoices(choice);


       RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/createPollingQuestions")
                .accept(MediaType.APPLICATION_JSON).content(exampleCourseJson)
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mvc.perform(requestBuilder).andReturn();

        MockHttpServletResponse response = result.getResponse();

        assertEquals(HttpStatus.CREATED.value(), response.getStatus());

        assertEquals("http://localhost:8080/createPollingQuestions",
                response.getHeader(HttpHeaders.LOCATION));

    }

    @Test
    public void getList() throws Exception {
        String uri = "/questions";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();

        assertTrue(content.length() > 0);
    }

}
