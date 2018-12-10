package ru.job4j.vacancy;

import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;

public class HTMLParser {
    private static final Logger LOGGER = Logger.getLogger(HTMLParser.class);
    private DataBase dataBase = new DataBase();
    private String[] getVacancyContentAndDate(String vacancyUrl) {
            String[] result = new String[2];
            String text = null;
            String date = null;
        try {
            Document document = Jsoup.connect(vacancyUrl).get();
            text = document.body().select("table.msgTable").first().select("td.msgBody").last().text();
            int index = document.select("td.msgFooter").first().text().indexOf("[");
            date = document.select("td.msgFooter").first().text().substring(0, index);
        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
        }
        result[0] = text;
        result[1] = date;
        return result;
    }

    public ArrayList<Vacancy> searchByPage() throws IOException {
        ArrayList<Vacancy> list = new ArrayList<>();
        String url;
        Document document;
        Elements htmlElements;
        Date lastUpdate = getLastUpdateDate();
        for (int i = 1; i < 40; i++) {
            url = "https://www.sql.ru/forum/job-offers/" + String.valueOf(i);
            document = Jsoup.connect(url).get();
            htmlElements = document.body().select("table.forumTable").first().select("tr");
            for (Element element : htmlElements) {
                String jobTitle = element.select("td.postslisttopic").select("a").text();
                if (jobTitle.toLowerCase().contains("java") && !(jobTitle.toLowerCase().contains("script"))) {
                    String link = element.select("td.postslisttopic").select("a").attr("href");
                    String[] vacancyTextDate = getVacancyContentAndDate(link);
                    Long date = DateParser.dateFormat(vacancyTextDate[1]);
                    Date dateOfPublishVacancy = new Date(date);
                    if (dateOfPublishVacancy.after(lastUpdate)) {
                        list.add(new Vacancy(jobTitle, vacancyTextDate[0], link, dateOfPublishVacancy));
                    } else {
                        break;
                    }

                }
            }

        }
        return list;
    }

    private Date getLastUpdateDate() {
        Date date = dataBase.lastDateInDB();
        if (date == null) {
            date = Date.valueOf("2018-01-01");
        }
        return date;
    }

}
