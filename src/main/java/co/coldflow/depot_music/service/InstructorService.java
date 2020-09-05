package co.coldflow.depot_music.service;

import co.coldflow.depot_music.entity.Account;
import co.coldflow.depot_music.entity.EUserRole;
import co.coldflow.depot_music.entity.Instructor;
import co.coldflow.depot_music.entity.Student;
import co.coldflow.depot_music.repository.AccountRepository;
import co.coldflow.depot_music.repository.InstructorRepository;
import co.coldflow.depot_music.repository.StudentRepository;
import co.coldflow.depot_music.web.dto.InstructorRequestDto;
import co.coldflow.depot_music.web.dto.InstructorResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

import java.nio.file.Path;

@Service
@Transactional
public class InstructorService {
    private final InstructorRepository instructorRepository;
    private final StudentRepository studentRepository;
    private final AccountService accountService;

    public InstructorService(InstructorRepository instructorRepository, StudentRepository studentRepository, AccountService accountService) {
        this.instructorRepository = instructorRepository;
        this.studentRepository = studentRepository;
        this.accountService = accountService;
    }

    public Long insertInstructor(InstructorRequestDto instructorRequestDto, Path filePath) {
        Account account = accountService.createAccount(instructorRequestDto.getUsername(), instructorRequestDto.getPassword(), EUserRole.ROLE_INSTRUCTOR);

        Instructor instructorToBeSaved = new Instructor();

        instructorToBeSaved.setAccount(account);
        instructorToBeSaved.setNickName(instructorRequestDto.getNickName());
        instructorToBeSaved.setRealName(instructorRequestDto.getRealName());
        instructorToBeSaved.setTel(instructorRequestDto.getTel());
        instructorToBeSaved.setMemo(instructorRequestDto.getMemo());
        instructorToBeSaved.setProfileInfo(instructorRequestDto.getProfileInfo());
        instructorToBeSaved.setFileName(instructorRequestDto.getPortrait() != null ? instructorRequestDto.getPortrait().getOriginalFilename():"");
        instructorToBeSaved.setFilePath(instructorRequestDto.getPortrait() != null ? filePath.toString(): "");


        Instructor instructorSaved = instructorRepository.save(instructorToBeSaved);

        return instructorSaved.getId();
    }

    public InstructorResponseDto selectInstructor(long id) {
        Instructor existingInstructor = instructorRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다. id="+id));
        return new InstructorResponseDto(existingInstructor);
    }

    public List<InstructorResponseDto> selectInstructorList() {
        List<Instructor> existingInstructorList = instructorRepository.findAll();

        ArrayList<InstructorResponseDto> instructorResponseDtoArrayList = new ArrayList<>();
        for(Instructor instructor: existingInstructorList){
            instructorResponseDtoArrayList.add(new InstructorResponseDto(instructor));
        }

        return instructorResponseDtoArrayList;
    }

    public void updateInstructor(Long id, InstructorRequestDto instructorRequestDto) {
        Instructor instructorToBeEdited = instructorRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다. id=" + id));

        instructorToBeEdited.setNickName(instructorRequestDto.getNickName());
        instructorToBeEdited.setRealName(instructorRequestDto.getRealName());
        instructorToBeEdited.setTel(instructorRequestDto.getTel());
        instructorToBeEdited.setMemo(instructorRequestDto.getMemo());
        instructorToBeEdited.setProfileInfo(instructorRequestDto.getProfileInfo());
    }

    public String getImagePath(long id) {
        Instructor instructor = instructorRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다. id="+id));
        return instructor.getFilePath();
    }

    public void linkStudent(long instructorId, long studentId) {
        Instructor instructor = instructorRepository.findById(instructorId)
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다. id="+instructorId));

        Student student = studentRepository.findById(studentId)
                .orElseThrow(()-> new IllegalArgumentException("해당 ID 를 가진 수강생이 존재하지 않습니다. id="+studentId));

        for(Student s : instructor.getStudents()){
            if(s.equals(student)){
                throw new IllegalArgumentException("선택한 수강생은 해당 강사에게 이미 등록되어있습니다.");
            }
        }

        instructor.getStudents().add(student);
    }

    public void unlinkStudent(long instructorId, long studentId) {
        Instructor instructor = instructorRepository.findById(instructorId)
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다. id="+instructorId));

        Student student = studentRepository.findById(studentId)
                .orElseThrow(()-> new IllegalArgumentException("해당 ID 를 가진 수강생이 존재하지 않습니다. id="+studentId));

        instructor.getStudents().remove(student);
    }
}