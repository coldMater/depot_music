package co.coldflow.depot_music.service;

import co.coldflow.depot_music.entity.EStudentType;
import co.coldflow.depot_music.entity.Parent;
import co.coldflow.depot_music.entity.Student;
import co.coldflow.depot_music.repository.ParentRepository;
import co.coldflow.depot_music.repository.StudentRepository;
import co.coldflow.depot_music.web.dto.StudentRequestDto;
import co.coldflow.depot_music.web.dto.StudentResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;

@Service
@Transactional
public class StudentService {
    @Autowired
    StudentRepository studentRepository;

    @Autowired
    ParentRepository parentRepository;

    public Long insertStudent(StudentRequestDto studentRequestDto){
        Student student = new Student();

        student.setAddress(studentRequestDto.getAddress());
        student.setBirthDate(LocalDate.parse(studentRequestDto.getBirthDate()));
        student.setEmail(studentRequestDto.getEmail());
        student.setName(studentRequestDto.getName());
        student.setStudentType(studentRequestDto.getStudentType());
        student.setTel(studentRequestDto.getTel());

        if(studentRequestDto.getStudentType() == EStudentType.CHILDREN && studentRequestDto.getParentId() != null){
            //수강생의 타입이 "자녀-Children"인 경우, 부모 정보 추가
            Parent parent = parentRepository.findById(studentRequestDto.getParentId())
                    .orElseThrow(() -> new IllegalArgumentException("입력하신 부모의 ID가 존재하지 않습니다. id=" + studentRequestDto.getParentId()));

            student.setParent(parent);
        }

        studentRepository.save(student);

        return student.getId();
    }

    public StudentResponseDto selectStudent(long id) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("입력하신 학생의 ID가 존재하지 않습니다. id=" + id));

        return new StudentResponseDto(
            student.getId(),
            student.getName(),
            student.getBirthDate().toString(),
            student.getTel(),
            student.getEmail(),
            student.getAddress(),
            student.getStudentType()
        );
    }
}
