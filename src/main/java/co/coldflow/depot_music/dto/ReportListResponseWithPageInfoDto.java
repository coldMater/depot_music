package co.coldflow.depot_music.dto;

import org.springframework.data.domain.Pageable;

import java.util.List;

public class ReportListResponseWithPageInfoDto {
    private int totalPageCount;
    private Pageable pageable;
    private List<ReportResponseDto> reports;
    private PaginationDto pagination;

    public ReportListResponseWithPageInfoDto(int totalPageCount, Pageable pageable, List<ReportResponseDto> reports) {
        this.totalPageCount = totalPageCount;
        this.pageable = pageable;
        this.reports = reports;
        this.pagination = new PaginationDto(pageable.getPageNumber(), totalPageCount, pageable.getPageSize());
    }

    public int getTotalPageCount() {
        return totalPageCount;
    }

    public Pageable getPageable() {
        return pageable;
    }

    public List<ReportResponseDto> getReports() {
        return reports;
    }

    public PaginationDto getPagination() {
        return pagination;
    }
}
