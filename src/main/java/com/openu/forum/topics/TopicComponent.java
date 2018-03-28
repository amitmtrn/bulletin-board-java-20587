package com.openu.forum.topics;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * TopicComponent
 */
@Component
public class TopicComponent {

  @Autowired
  TopicJpaRepository topicRepository;

  @Scheduled(cron="0 0 0 * * ?")
  private void cleanTopics() {
    Calendar cal = Calendar.getInstance(); 
    cal.add(Calendar.MONTH, -1);
    Date date = cal.getTime();
    String dateString = new SimpleDateFormat("dd-MM-yyyy").format(date);

    topicRepository.deleteByDate(dateString);
  }
}