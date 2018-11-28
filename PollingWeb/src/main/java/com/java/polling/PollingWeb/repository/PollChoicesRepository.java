package com.java.polling.PollingWeb.repository;

import com.java.polling.PollingWeb.entity.PollChoices;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PollChoicesRepository extends JpaRepository<PollChoices,Long> {
}
