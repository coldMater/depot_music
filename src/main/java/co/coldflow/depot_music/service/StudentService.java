package co.coldflow.depot_music.service;

import co.coldflow.depot_music.entity.EStudentType;
import co.coldflow.depot_music.entity.Parent;
import co.coldflow.depot_music.entity.Student;
import co.coldflow.depot_music.repository.ParentRepository;
import co.coldflow.depot_music.repository.StudentRepository;
import co.coldflow.depot_music.web.dto.ParentResponseDto;
import co.coldflow.depot_music.web.dto.StudentRequestDto;
import co.coldflow.depot_music.web.dto.StudentResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
        student.setBirthDate(studentRequestDto.getBirthDate());
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

        Parent parent = student.getParent();

        return new StudentResponseDto(
            student.getId(),
            student.getName(),
            student.getBirthDate().toString(),
            student.getTel(),
            student.getEmail(),
            student.getAddress(),
            student.getStudentType(),
            parent == null ? null : new ParentResponseDto(student.getParent().getId(), student.getParent().getName(), student.getParent().getTel())
        );
    }

    public void updateStudent(long id, StudentRequestDto studentRequestDto) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("입력하신 학생의 ID가 존재하지 않습니다. id=" + id));

        Optional<Parent> parent = studentRequestDto.getParentId() != null ? parentRepository.findById(studentRequestDto.getParentId()) : Optional.empty();

        student.setName(studentRequestDto.getName());
        student.setBirthDate(studentRequestDto.getBirthDate());
        student.setTel(studentRequestDto.getTel());
        student.setEmail(studentRequestDto.getEmail());
        student.setAddress(studentRequestDto.getAddress());
        student.setStudentType(studentRequestDto.getStudentType());
        student.setParent(parent.isPresent() ? parent.get() : null);
    }

    public List<StudentResponseDto> selectStudentList() {
        List<Student> existingStudentList = studentRepository.findAll();

        ArrayList<StudentResponseDto> studentResponseDtoArrayList = new ArrayList<>();

        for(Student student: existingStudentList){
            studentResponseDtoArrayList.add(new StudentResponseDto(student));
        }

        return studentResponseDtoArrayList;
    }
}
