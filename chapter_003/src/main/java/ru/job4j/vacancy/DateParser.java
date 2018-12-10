package ru.job4j.vacancy;

import org.apache.log4j.Logger;

import java.text.DateFormat;
import java.text.DateFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateParser {
    private static final Logger LOGGER = Logger.getLogger(DateParser.class);
    public static long dateFormat(String date) {
        Calendar cal = Calendar.getInstance();
        Calendar parsedate = Calendar.getInstance();
        DateFormat formatter;
        Date dt;
        try {
            if (date.contains("сегодня")) {
                formatter = new SimpleDateFormat("сегодня, HH:mm");
                dt = formatter.parse(date);
                parsedate.setTime(dt);
                cal.set(Calendar.HOUR_OF_DAY, parsedate.get(Calendar.HOUR_OF_DAY));
                cal.set(Calendar.MINUTE, parsedate.get(Calendar.MINUTE));
            } else if (date.contains("вчера")) {
                formatter = new SimpleDateFormat("вчера, HH:mm");
                dt = formatter.parse(date);
                parsedate.setTime(dt);
                cal.set(Calendar.HOUR_OF_DAY, parsedate.get(Calendar.HOUR_OF_DAY));
                cal.set(Calendar.MINUTE, parsedate.get(Calendar.MINUTE));
                cal.add(Calendar.DAY_OF_MONTH, -1);
            } else {
                formatter = new SimpleDateFormat("d MMM y, HH:mm", myDateFormatSymbols);
                cal.setTime(formatter.parse(date));
            }
            return cal.getTimeInMillis();
        } catch (ParseException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return 0;
    }
    private static DateFormatSymbols myDateFormatSymbols = new DateFormatSymbols() {

        @Override
        public String[] getMonths() {
            return new String[]{"янв", "фев", "мар", "апр", "май", "июн",
                    "июл", "авг", "сен", "окт", "ноя", "дек"};
        }
    };
}
