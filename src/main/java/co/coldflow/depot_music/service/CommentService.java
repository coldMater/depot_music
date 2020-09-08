package co.coldflow.depot_music.service;

import co.coldflow.depot_music.entity.Comment;
import co.coldflow.depot_music.entity.Report;
import co.coldflow.depot_music.repository.CommentRepository;
import co.coldflow.depot_music.repository.ReportRepository;
import co.coldflow.depot_music.dto.CommentRequestDto;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class CommentService {
    private final CommentRepository commentRepository;
    private final ReportRepository reportRepository;

    public CommentService(CommentRepository commentRepository, ReportRepository reportRepository) {
        this.commentRepository = commentRepository;
        this.reportRepository = reportRepository;
    }

    public void deleteComment(long commentId) {
        commentRepository.deleteById(commentId);
    }

    public long insertComment(CommentRequestDto commentRequestDto) {
        Report report = reportRepository.findById(commentRequestDto.getReportId())
            .orElseThrow(() -> new IllegalArgumentException("요청하신 요청의 수강일지 ID가 존재하지 않습니다. id="+commentRequestDto.getReportId()));

        Comment commentToBeInserted = new Comment();

        User principal = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        commentToBeInserted.setReport(report);
        commentToBeInserted.setContent(commentRequestDto.getContent());
        commentToBeInserted.setAuthorName(commentRequestDto.getAuthorName());
        commentToBeInserted.setUsername(principal.getUsername());

        commentRepository.save(commentToBeInserted);

        return commentToBeInserted.getId();
    }
}
