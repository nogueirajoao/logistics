package trucklogistics.logistics.truckers;

import lombok.Data;

import java.time.LocalDate;
import java.util.Map;

@Data
public class ReportResponse {

    public ReportResponse(Map<LocalDate, Long> dayReport, Map<String, Long> monthReport, Map<Integer, Long> yearReport) {
        this.dayReport = dayReport;
        this.monthReport = monthReport;
        this.yearReport = yearReport;
    }

    Map<LocalDate, Long> dayReport;
    Map<String, Long> monthReport;
    Map<Integer, Long> yearReport;

}
