package co.coldflow.depot_music.runner;

import co.coldflow.depot_music.entity.Instructor;
import co.coldflow.depot_music.entity.Parent;
import co.coldflow.depot_music.entity.Student;
import co.coldflow.depot_music.repository.InstructorRepository;
import co.coldflow.depot_music.repository.ParentRepository;
import co.coldflow.depot_music.repository.StudentRepository;
import co.coldflow.depot_music.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.time.LocalDate;

@Component
public class DepotRunner implements ApplicationRunner{
    @Autowired
    DataSource dataSource;
    @Autowired
    InstructorRepository instructorRepository;
    @Autowired
    ParentRepository parentRepository;
    @Autowired
    StudentRepository studentRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        Connection connection = dataSource.getConnection();

        System.out.println(connection.getMetaData().getURL());
        System.out.println(connection.getMetaData().getUserName());

        for(int i = 0; i < 30; i++){
            Instructor instructor = new Instructor();
            instructor.setUsername("test"+i);
            instructor.setRealName("test_name"+i);
            instructor.setTel("test_tel"+i);
            instructorRepository.save(instructor);
        }

        for(int i = 0; i < 3; i++){
            Parent parent = new Parent();
            parent.setName("tp"+i);
            parent.setTel("010-1234-123"+i);
            parentRepository.save(parent);
        }

        for(int i = 0; i < 5; i++){
            Student student = new Student();
            student.setName("stu_"+i);
            student.setTel("010-0000-000"+i);
            student.setBirthDate(LocalDate.now().minusYears(15));
            studentRepository.save(student);
        }
    }
}
