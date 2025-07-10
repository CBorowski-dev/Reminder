package de.borowski.reminder;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

@Configuration
public class BatchConfiguration {

    @Bean
    public ToDoReader reader() {
        return new ToDoReader();
    }

    @Bean
    public ToDoProcessor processor() {
        return new ToDoProcessor();
    }

    @Bean
    public EmailWriter writer(DataSource dataSource) {
        return new EmailWriter();
    }

    @Bean
    public Job reminderJob(JobRepository jobRepository, Step step1, JobCompletionNotificationListener listener) {
        return new JobBuilder("reminderJob", jobRepository)
                .listener(listener)
                .start(step1)
                .build();
    }

    @Bean
    public Step step1(JobRepository jobRepository, DataSourceTransactionManager transactionManager,
                      ToDoReader reader, ToDoProcessor processor, EmailWriter writer) {
        return new StepBuilder("step1", jobRepository)
                .<String, String>chunk(3, transactionManager)
                .reader(reader)
                .processor(processor)
                .writer(writer)
                .build();
    }
}