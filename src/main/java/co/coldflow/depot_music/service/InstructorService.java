package co.coldflow.depot_music.service;

import co.coldflow.depot_music.dto.StudentResponseDto;
import co.coldflow.depot_music.entity.Account;
import co.coldflow.depot_music.entity.EUserRole;
import co.coldflow.depot_music.entity.Instructor;
import co.coldflow.depot_music.entity.Student;
import co.coldflow.depot_music.repository.AccountRepository;
import co.coldflow.depot_music.repository.InstructorRepository;
import co.coldflow.depot_music.repository.StudentRepository;
import co.coldflow.depot_music.dto.InstructorRequestDto;
import co.coldflow.depot_music.dto.InstructorResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.io.FilenameFilter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import java.nio.file.Path;
import java.util.UUID;

@Service
@Transactional
public class InstructorService {
    private final InstructorRepository instructorRepository;
    private final StudentRepository studentRepository;
    private final AccountService accountService;
    private final AccountRepository accountRepository;

    public InstructorService(InstructorRepository instructorRepository, StudentRepository studentRepository, AccountService accountService, AccountRepository accountRepository) {
        this.instructorRepository = instructorRepository;
        this.studentRepository = studentRepository;
        this.accountService = accountService;
        this.accountRepository = accountRepository;
    }

    public Long insertInstructor(InstructorRequestDto instructorRequestDto) {
        Account account = accountService.createAccount(instructorRequestDto.getUsername(), instructorRequestDto.getPassword(), EUserRole.ROLE_INSTRUCTOR);

        Instructor instructorToBeSaved = new Instructor();

        instructorToBeSaved.setAccount(account);
        instructorToBeSaved.setNickName(instructorRequestDto.getNickName());
        instructorToBeSaved.setRealName(instructorRequestDto.getRealName());
        instructorToBeSaved.setTel(instructorRequestDto.getTel());
        instructorToBeSaved.setMemo(instructorRequestDto.getMemo());
        instructorToBeSaved.setProfileInfo(instructorRequestDto.getProfileInfo());

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

    public InstructorResponseDto selectInstructorsProfile() {
        Instructor instructor = getInstructorFromContext();
        return new InstructorResponseDto(instructor);
    }

    private Instructor getInstructorFromContext() {
        User principal = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Account account = accountRepository.findByUsername(principal.getUsername())
                .orElseThrow(() -> new IllegalArgumentException("접속한 사용자의 정보가 없습니다."));

        return instructorRepository.findByAccount(account)
                .orElseThrow(() -> new IllegalArgumentException("접속한 사용자는 강사 정보를 가지고 있지 않습니다."));
    }

    public void updateInstructorsProfile(InstructorRequestDto instructorRequestDto) {
        Instructor instructor = getInstructorFromContext();

        instructor.setNickName(instructorRequestDto.getNickName());
        instructor.setRealName(instructorRequestDto.getRealName());
        instructor.setTel(instructorRequestDto.getTel());
        instructor.setMemo(instructorRequestDto.getMemo());
        instructor.setProfileInfo(instructorRequestDto.getProfileInfo());
    }

    public void changePortrait(MultipartFile portraitFile) throws IOException {
        //TODO 기존 파일정보는 지우기
        String fileNameToBeSaved = getFilenameAsUUID(portraitFile);
        Path targetPath = saveFile(portraitFile, fileNameToBeSaved);

        Instructor instructor = getInstructorFromContext();

        instructor.setFileName(fileNameToBeSaved);
        instructor.setFilePath(targetPath.toString());
    }

    public void changeInstructorsPortrait(MultipartFile portraitFile, long instructorId) {
        //TODO 기존 파일정보는 지우기
        String fileNameToBeSaved = getFilenameAsUUID(portraitFile);
        Path targetPath = saveFile(portraitFile, fileNameToBeSaved);

        Instructor instructor = instructorRepository.findById(instructorId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NO_CONTENT, "요청하신 강사 정보가 존재하지 않습니다. id="+instructorId));

        instructor.setFileName(fileNameToBeSaved);
        instructor.setFilePath(targetPath.toString());
    }

    private String getFilenameAsUUID (MultipartFile portraitFile) {
        String fileName = StringUtils.cleanPath(portraitFile.getOriginalFilename());
        int pos = fileName.lastIndexOf('.');
        String extension = fileName.substring(pos + 1);
        String fileNameToBeSaved = UUID.randomUUID() + "." + extension;

        return fileNameToBeSaved;
    }

    private Path saveFile(MultipartFile file, String fileNameToBeSaved){
        try {
            Path targetPath = null;
            String directoryPath = "portrait";
            Path directory = Paths.get(directoryPath).toAbsolutePath().normalize();
            Files.createDirectories(directory);

            Assert.state(!fileNameToBeSaved.contains(".."), "Name of file cannot contain '..'");
            targetPath = directory.resolve(fileNameToBeSaved).normalize();
            if (!fileNameToBeSaved.isEmpty()) {
                file.transferTo(targetPath);
            }
            return targetPath;
        } catch (Exception e) {
            new ResponseStatusException(HttpStatus.BAD_REQUEST, "파일 형식이 유효하지 않습니다.");
        }
        return null;
    }

    public List<InstructorResponseDto> selectInstructorsProfileOfStudent() {
        User principal = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Account account = accountRepository.findByUsername(principal.getUsername())
                .orElseThrow(() -> new IllegalArgumentException("접속한 사용자의 정보가 없습니다."));

        Student student = studentRepository.findByAccount(account)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NO_CONTENT, "요청하신 사용자의 정보가 없습니다."));

        List<InstructorResponseDto> instructors = new ArrayList<>();
        for(Instructor instructor: student.getInstructors()){
            instructors.add(new InstructorResponseDto(instructor));
        }

        return instructors;
    }
}