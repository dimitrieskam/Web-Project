package org.example.DataHolder;

import jakarta.annotation.PostConstruct;
import org.example.model.Professor;
import org.example.model.Student;
import org.example.model.Subject;
import org.example.model.Topic;
import org.example.repository.ProfessorRepository;
import org.example.repository.StudentRepository;
import org.example.repository.SubjectRepository;
import org.example.repository.TopicRepository;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

//@Component
//public class DataInitializer {
//
//    private final ProfessorRepository professorRepository;
//    private final StudentRepository studentRepository;
//    private final SubjectRepository subjectRepository;
//    private final TopicRepository topicRepository;
//
//    public DataInitializer(ProfessorRepository professorRepository, StudentRepository studentRepository, SubjectRepository subjectRepository, TopicRepository topicRepository) {
//        this.professorRepository = professorRepository;
//        this.studentRepository = studentRepository;
//        this.subjectRepository = subjectRepository;
//        this.topicRepository = topicRepository;
//    }
//
//    //@PostConstruct
//    public void initializeData() throws Exception {
//        Professor professor1 = new Professor("Marija", "Dimitrieska", "marija@d.com");
//        Professor professor2 = new Professor("Ivan", "Pupinoski", "ivan@p.com");
//        Professor professor3 = new Professor("Angela", "Ivanova", "angela@i.com");
//
//        this.professorRepository.save(professor1);
//        this.professorRepository.save(professor2);
//        this.professorRepository.save(professor3);
//
//        Student student1 = new Student("Marija", "Dimitrieska", 111111, "marija@d.com");
//        Student student2 = new Student("Ivan", "Pupinoski", 222222, "ivan@p.com");
//        Student student3 = new Student("Angela", "Ivanova", 333333, "angela@i.com");
//
//        this.studentRepository.save(student1);
//        this.studentRepository.save(student2);
//        this.studentRepository.save(student3);
//
//        List<Student> students1 = new ArrayList<>();
//        students1.add(student1);
//        List<Student> students2 = new ArrayList<>();
//        students2.add(student2);
//        List<Student> students3 = new ArrayList<>();
//        students3.add(student3);
//
//        List<Professor> professors1 = new ArrayList<>();
//        professors1.add(professor1);
//        List<Professor> professors2 = new ArrayList<>();
//        professors2.add(professor2);
//        List<Professor> professors3 = new ArrayList<>();
//        professors3.add(professor3);
//
//        Subject subject1 = new Subject("Web Programming", "111", students1, professors1);
//        Subject subject2 = new Subject("Electronic And Mobile Commerce", "222", students2, professors2);
//        Subject subject3 = new Subject("Operative Systems", "333", students3, professors3);
//
//        this.subjectRepository.save(subject1);
//        this.subjectRepository.save(subject2);
//        this.subjectRepository.save(subject3);
//
//        Topic topic1 = new Topic("Topic1", LocalDate.of(2025, 5, 15), LocalDate.of(2025, 6, 15), 3, 5, subject1);
//        Topic topic2 = new Topic("Topic2", LocalDate.of(2025, 6, 15), LocalDate.of(2025, 7, 15), 2, 3, subject2);
//        Topic topic3 = new Topic("Topic3", LocalDate.of(2025, 7, 15), LocalDate.of(2025, 8, 15), 1, 1, subject3);
//
//        this.topicRepository.save(topic1);
//        this.topicRepository.save(topic2);
//        this.topicRepository.save(topic3);
//    }

/*<<<<<<< HEAD
    private final ProfessorRepository professorRepository;
    private final StudentRepository studentRepository;
    private final SubjectRepository subjectRepository;
    private final TopicRepository topicRepository;

    public DataInitializer(ProfessorRepository professorRepository, StudentRepository studentRepository, SubjectRepository subjectRepository, TopicRepository topicRepository) {
        this.professorRepository = professorRepository;
        this.studentRepository = studentRepository;
        this.subjectRepository = subjectRepository;
        this.topicRepository = topicRepository;
    }

    // @PostConstruct
    public void initializeData() throws Exception {
        Professor professor1 = new Professor("Marija", "Dimitrieska", "marija@d.com");
        Professor professor2 = new Professor("Ivan", "Pupinoski", "ivan@p.com");
        Professor professor3 = new Professor("Angela", "Ivanova", "angela@i.com");

        this.professorRepository.save(professor1);
        this.professorRepository.save(professor2);
        this.professorRepository.save(professor3);

        Student student1 = new Student("Marija", "Dimitrieska", 111111, "marija@d.com");
        Student student2 = new Student("Ivan", "Pupinoski", 222222, "ivan@p.com");
        Student student3 = new Student("Angela", "Ivanova", 333333, "angela@i.com");

        this.studentRepository.save(student1);
        this.studentRepository.save(student2);
        this.studentRepository.save(student3);

        List<Student> students1 = new ArrayList<>();
        students1.add(student1);
        List<Student> students2 = new ArrayList<>();
        students2.add(student2);
        List<Student> students3 = new ArrayList<>();
        students3.add(student3);

        List<Professor> professors1 = new ArrayList<>();
        professors1.add(professor1);
        List<Professor> professors2 = new ArrayList<>();
        professors2.add(professor2);
        List<Professor> professors3 = new ArrayList<>();
        professors3.add(professor3);

        Subject subject1 = new Subject("Web Programming", "111", students1, professors1);
        Subject subject2 = new Subject("Electronic And Mobile Commerce", "222", students2, professors2);
        Subject subject3 = new Subject("Operative Systems", "333", students3, professors3);

        this.subjectRepository.save(subject1);
        this.subjectRepository.save(subject2);
        this.subjectRepository.save(subject3);

        Topic topic1 = new Topic("Topic1", LocalDate.of(2025, 5, 15), LocalDate.of(2025, 6, 15), 3, 5, subject1);
        Topic topic2 = new Topic("Topic2", LocalDate.of(2025, 6, 15), LocalDate.of(2025, 7, 15), 2, 3, subject2);
        Topic topic3 = new Topic("Topic3", LocalDate.of(2025, 7, 15), LocalDate.of(2025, 8, 15), 1, 1, subject3);

        this.topicRepository.save(topic1);
        this.topicRepository.save(topic2);
        this.topicRepository.save(topic3);
    }

}
=======
//}
>>>>>>> newDb */