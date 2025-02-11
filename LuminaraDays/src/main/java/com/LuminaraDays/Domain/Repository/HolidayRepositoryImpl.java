package com.LuminaraDays.Domain.Repository;

import com.LuminaraDays.Controller.Dto.HolidayDTO;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class HolidayRepositoryImpl implements HolidayRepository {

    private static final Logger logger = LoggerFactory.getLogger(HolidayRepositoryImpl.class);

    @Override
    public List<HolidayDTO> fetchHolidays(int year) {
        String url = "https://www.timeanddate.com/holidays/brazil/" + year;
        List<HolidayDTO> holidays = new ArrayList<>();

        try (WebClient webClient = new WebClient()) {
            webClient.getOptions().setJavaScriptEnabled(false);
            webClient.getOptions().setCssEnabled(false);
            webClient.getOptions().setThrowExceptionOnScriptError(false);
            webClient.getOptions().setTimeout(10000);
            webClient.addRequestHeader("User-Agent", "Mozilla/5.0");

            HtmlPage page = webClient.getPage(url);
            List<HtmlElement> rows = page.getByXPath("//table[contains(@class, 'table--left')]/tbody/tr");

            for (HtmlElement row : rows) {
                List<HtmlElement> cells = row.getElementsByTagName("td");
                List<HtmlElement> headers = row.getElementsByTagName("th");

                List<String> columnValues = new ArrayList<>();
                for (HtmlElement header : headers) {
                    String value = header.asNormalizedText().trim();
                    columnValues.add(value.isEmpty() ? "-" : value);
                }
                for (HtmlElement cell : cells) {
                    String value = cell.asNormalizedText().trim();
                    columnValues.add(value.isEmpty() ? "-" : value);
                }

                if (columnValues.size() >= 4) {
                    String date = columnValues.get(0);
                    String dayOfWeek = columnValues.get(1);
                    String holidayName = columnValues.get(2);
                    String holidayType = columnValues.get(3);

                    holidays.add(new HolidayDTO(date + " (" + dayOfWeek + ")", holidayName, holidayType));
                }
            }
        } catch (IOException e) {
            logger.error("Error fetching holiday data: {}", e.getMessage(), e);
        }

        return holidays;
    }
}

