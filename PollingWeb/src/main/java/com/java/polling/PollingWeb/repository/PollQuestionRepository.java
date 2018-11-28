package com.java.polling.PollingWeb.repository;

import com.java.polling.PollingWeb.entity.PollQuestion;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PollQuestionRepository extends JpaRepository<PollQuestion,Long> {

}
