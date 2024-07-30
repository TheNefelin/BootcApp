package cl.praxis.bootcapp.services.imp;

import cl.praxis.bootcapp.entities.Note;
import cl.praxis.bootcapp.entities.NoteDTO;
import cl.praxis.bootcapp.entities.Subject;
import cl.praxis.bootcapp.entities.User;
import cl.praxis.bootcapp.repositories.INoteRepository;
import cl.praxis.bootcapp.repositories.ISubjectRepository;
import cl.praxis.bootcapp.repositories.IUserRepository;
import cl.praxis.bootcapp.services.INoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class NoteService implements INoteService {
    @Autowired
    private INoteRepository repoNote;

    @Autowired
    private ISubjectRepository repoSubject;

    @Autowired
    private IUserRepository repoUser;

    @Override
    public List<NoteDTO> getNoteByIdSubject(Long idSubject) {
        List<Note> notes = repoNote.findByIdSubject(idSubject);
        return toDTO(notes);
    }

    @Override
    public List<NoteDTO> getNoteByIdTeacher(Long idTeacher) {
        List<Note> notes = repoNote.findByIdTeacher(idTeacher);
        return toDTO(notes);
    }

    @Override
    public List<NoteDTO> getNoteByIdStudent(Long idStudent) {
        List<Note> notes = repoNote.findByIdStudent(idStudent);
        return toDTO(notes);
    }

    private List<NoteDTO> toDTO(List<Note> notes) {
        Set<Long> idSubjectSet = notes.stream().map(Note::getIdSubject).collect(Collectors.toSet());
        Set<Long> idTeacherSet = notes.stream().map(Note::getIdTeacher).collect(Collectors.toSet());
        Set<Long> idStudentsSet = notes.stream().map(Note::getIdStudent).collect(Collectors.toSet());

        Map<Long, Subject> subjectList = repoSubject.findAllById(idSubjectSet).stream().collect(Collectors.toMap(Subject::getId, subject -> subject));
        Map<Long, User> teacherList = repoUser.findAllById(idTeacherSet).stream().collect(Collectors.toMap(User::getId, user -> user));
        Map<Long, User> studentList = repoUser.findAllById(idStudentsSet).stream().collect(Collectors.toMap(User::getId, user -> user));

        return notes.stream().map(note -> {
            NoteDTO noteDTO = new NoteDTO();
            noteDTO.setId(note.getId());
            noteDTO.setNote(note.getNote());
            noteDTO.setTeacher(teacherList.get(note.getIdTeacher()));
            noteDTO.setEstudent(studentList.get(note.getIdStudent()));
            noteDTO.setSubject(subjectList.get(note.getIdSubject()));
            return noteDTO;
        }).collect(Collectors.toList());
    }

}
