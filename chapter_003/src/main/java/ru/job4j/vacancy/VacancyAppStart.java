package ru.job4j.vacancy;

import org.apache.log4j.Logger;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


public class VacancyAppStart {
    private static final Logger LOGGER = Logger.getLogger(VacancyAppStart.class);
    public static void main(String[] args) {

        //Loading from propertyfile
        Properties config = new Properties();
        try (InputStream in = ConnectorDB.class.getClassLoader().getResourceAsStream("vacancy.properties")) {
            config.load(in);
        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
        }

        try {
            SchedulerFactory sf = new StdSchedulerFactory();
            Scheduler scheduler = sf.getScheduler();

            JobDetail job = JobBuilder.newJob(Main.class)
                    .withIdentity("Vacancy", "group1").build();

            // run every day infinite loop
            CronTrigger crontrigger = TriggerBuilder
                    .newTrigger()
                    .withIdentity("croneTrigger", "group1")
                    .withSchedule(CronScheduleBuilder.cronSchedule(config.getProperty("cron")))
                    .build();

            scheduler.start();
            scheduler.scheduleJob(job, crontrigger);

        } catch (SchedulerException e) {
            LOGGER.error(e.getMessage(), e);
        }

    }
}
