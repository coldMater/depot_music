package co.coldflow.depot_music.runner;

import co.coldflow.depot_music.entity.Instructor;
import co.coldflow.depot_music.entity.Parent;
import co.coldflow.depot_music.repository.InstructorRepository;
import co.coldflow.depot_music.repository.ParentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;

@Component
public class DepotRunner implements ApplicationRunner{
    @Autowired
    DataSource dataSource;
    @Autowired
    InstructorRepository instructorRepository;
    @Autowired
    ParentRepository parentRepository;

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
    }
}
