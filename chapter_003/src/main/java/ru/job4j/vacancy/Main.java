package ru.job4j.vacancy;
import org.apache.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.util.ArrayList;


public class Main implements Job {
    private static final Logger LOGGER = Logger.getLogger(Main.class);
    @Override
    public void execute(JobExecutionContext jobExecutionContext) {
        DataBase dataBase = new DataBase();
        dataBase.createTable();
        HTMLParser htmlParser = new HTMLParser();
        ArrayList<Vacancy> listOfVacancies = new ArrayList<>();
        try {
            listOfVacancies = htmlParser.searchByPage();
        } catch (IOException e) {
           LOGGER.error(e.getMessage(), e);
        }
        dataBase.insertVacancy(listOfVacancies);
        dataBase.close();
        System.out.println(listOfVacancies.size() + " Vacancies is added");


    }
}
