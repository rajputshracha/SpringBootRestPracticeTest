package com.java.polling.PollingWeb.controller;

import com.java.polling.PollingWeb.entity.PollChoices;
import com.java.polling.PollingWeb.entity.PollQuestion;
import com.java.polling.PollingWeb.pojo.QuestionPojo;
import com.java.polling.PollingWeb.pojo.QuestionVO;
import com.java.polling.PollingWeb.repository.PollChoicesRepository;
import com.java.polling.PollingWeb.repository.PollQuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import java.util.*;

@RestController

public class PollServiceResource {
    @Autowired
    private PollQuestionRepository pollQuestionRepository;
    @Autowired
    private PollChoicesRepository pollChoicesRepository;

    public PollServiceResource(PollQuestionRepository pollQuestionRepository, PollChoicesRepository pollChoicesRepository) {
        this.pollQuestionRepository = pollQuestionRepository;
        this.pollChoicesRepository = pollChoicesRepository;
    }

    public PollServiceResource() {
    }

    @GetMapping("/questions")
    public QuestionVO[] retrieveAllStudents() {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
        QuestionVO[] questions = restTemplate.getForObject("http://polls.apiblueprint.org/questions", QuestionVO[].class);

        return questions;
    }

    /*@GetMapping("/getQuestions")
    public QuestionPojo retrieveandSet() {
        QuestionPojo pojo = new QuestionPojo();
        pojo.setChoices(new HashSet<PollChoices>(pollChoicesRepository.findAll()));
        Iterable<PollQuestion> question =pollQuestionRepository.findAll();
        for (PollQuestion ques: question
             ) {
            pojo.setPublished_at(ques.getPublished_at());
            pojo.setQuestion(ques.getQuestion());
        }
        return pojo;
    }*/

    private List<QuestionPojo> fetchResult(){
        List<QuestionPojo> returnResult = new ArrayList<QuestionPojo>();
        QuestionPojo pojo = new QuestionPojo();

        Iterable<PollQuestion> question =pollQuestionRepository.findAll();

        for (PollQuestion ques: question
        ) {
            pojo.setPublished_at(ques.getPublished_at());
            pojo.setQuestion(ques.getQuestion());
            pojo.setChoices(new HashSet<PollChoices>(pollChoicesRepository.findAll()));
            returnResult.add(pojo);
        }

        return returnResult;
    }

    @PostMapping("/createPollingQuestions")
    public List<QuestionPojo> createStudent(@RequestBody PollQuestion question) {
        String choices;
        HashSet<PollChoices> result = (HashSet<PollChoices>)question.getChoices();

        PollQuestion pollingSaved = new PollQuestion();

        for (PollChoices p: result
             ) {
            /*PollChoices item = p;
            String choice = item.getChoice();
            int vote = Collections.frequency(result, p.getChoice());
            System.out.println(item);
            PollChoices choiceSaved = pollChoicesRepository.save(new PollChoices(choice,vote));*/
            question.setPublished_at(new Date());
            pollingSaved  = pollQuestionRepository.save(question);
        }


        return fetchResult();

    }
}
