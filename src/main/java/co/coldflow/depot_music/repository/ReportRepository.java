package co.coldflow.depot_music.repository;

import co.coldflow.depot_music.entity.Instructor;
import co.coldflow.depot_music.entity.Report;
import co.coldflow.depot_music.entity.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReportRepository extends JpaRepository<Report, Long> {
    Page<Report> findAllByOrderByIdDesc(Pageable pageable);
    Page<Report> findAllByInstructorOrderByIdDesc(Instructor instructor, Pageable pageable);

    Page<Report> findAllByStudentOrderByIdDesc(Student student, Pageable pageable);
}
