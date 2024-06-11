package com.example.technomakers.springbatch.exercice;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.configuration.ListableJobLocator;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import javax.annotation.PostConstruct;

@Configuration
@ImportResource("classpath:job.xml")
public class BatchConfig {

    @Autowired
    JobLauncher jobLauncher;

    @Autowired
    Job studentJob;


    public void runJob() throws Exception {
        jobLauncher.run(studentJob, new JobParametersBuilder().toJobParameters());
    }
    
}