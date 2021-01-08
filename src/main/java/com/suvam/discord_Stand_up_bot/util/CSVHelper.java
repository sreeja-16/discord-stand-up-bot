package com.suvam.discord_Stand_up_bot.util;

import com.suvam.discord_Stand_up_bot.entity.UserResponse;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.QuoteMode;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;

public class CSVHelper {

    public static ByteArrayInputStream responsesToCSV(List<UserResponse> responses) {
        final CSVFormat format =
                CSVFormat.DEFAULT.withHeader("id","response","user","Answered_at").withQuoteMode(QuoteMode.MINIMAL);

        try (ByteArrayOutputStream out = new ByteArrayOutputStream();
             CSVPrinter csvPrinter = new CSVPrinter(new PrintWriter(out), format);) {
            for (UserResponse response : responses) {
                List<String> data = Arrays.asList(
                        String.valueOf(response.getId()),
                        response.getResponse(),
                        response.getUser().getName(),
                        String.valueOf(response.getAnsweredAt())
                );

                csvPrinter.printRecord(data);
            }

            csvPrinter.flush();
            return new ByteArrayInputStream(out.toByteArray());
        } catch (IOException e) {
            throw new RuntimeException("fail to import data to CSV file: " + e.getMessage());
        }
    }
}