package com.admin.utils;

import java.time.LocalDateTime;
import java.time.LocalDate;
import java.time.Month;

public class TimePeriodHelper {

    // Phương thức trả về thời gian bắt đầu cho một khoảng thời gian (ngày, tháng, năm)
    public static LocalDateTime getStartDateForPeriod(String period) {
        LocalDateTime startDate = null;

        switch (period.toLowerCase()) {
            case "day":
                startDate = LocalDate.now().atStartOfDay();  // Thời gian bắt đầu của ngày hiện tại
                break;
            case "month":
                startDate = LocalDate.of(LocalDate.now().getYear(), LocalDate.now().getMonth(), 1).atStartOfDay();  // Thời gian bắt đầu của tháng hiện tại
                break;
            case "year":
                startDate = LocalDate.of(LocalDate.now().getYear(), Month.JANUARY, 1).atStartOfDay();  // Thời gian bắt đầu của năm hiện tại
                break;
            default:
                throw new IllegalArgumentException("Invalid time period specified: " + period);
        }

        return startDate;
    }

    // Phương thức trả về thời gian kết thúc cho một khoảng thời gian (ngày, tháng, năm)
    public static LocalDateTime getEndDateForPeriod(String period) {
        LocalDateTime endDate = null;

        switch (period.toLowerCase()) {
            case "day":
                endDate = LocalDate.now().atTime(23, 59, 59);  // Thời gian kết thúc của ngày hiện tại
                break;
            case "month":
                endDate = LocalDate.now().withDayOfMonth(LocalDate.now().lengthOfMonth()).atTime(23, 59, 59);  // Thời gian kết thúc của tháng hiện tại
                break;
            case "year":
                endDate = LocalDate.of(LocalDate.now().getYear(), Month.DECEMBER, 31).atTime(23, 59, 59);  // Thời gian kết thúc của năm hiện tại
                break;
            default:
                throw new IllegalArgumentException("Invalid time period specified: " + period);
        }

        return endDate;
    }
}
