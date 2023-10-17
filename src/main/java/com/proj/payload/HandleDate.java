package com.proj.payload;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class HandleDate {
    public String getChangedDate(String inputDateStr, int daysToAdd) {

        LocalDateTime currentDateTime = LocalDateTime.parse(inputDateStr, DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss"));

        // Calculate the new date and time based on the daysToAdd value
        LocalDateTime newDateTime = currentDateTime.plusDays(daysToAdd);

        // Format the new date and time back into a string
        String modifiedDateStr = newDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss"));

        return modifiedDateStr;
    }
    public static void main(String[] args) {
        LocalDateTime currentDateTime = LocalDateTime.parse("2023-10-15T15:30:00", DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss"));
        System.out.println(currentDateTime.plusDays(2));

    }
}