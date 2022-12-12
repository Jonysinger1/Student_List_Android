package com.example.mika_jony_student_list_android.Model;

import java.util.LinkedList;
import java.util.List;

public class Model {
    private static final Model _instance = new Model();

    public static Model instance(){
        return _instance;
    }
    private Model(){
        for(int i=0;i<20;i++){
            addStudent(new Student("Student " + i,"id "+i,"phone +972 "+i,"address "+i, "avatar", false));
        }
    }
    public void deleteStudent(int pos){ // delete student at position pos
        data.remove(pos);

    }
    List<Student> data = new LinkedList<>(); // data is a list of students
    public List<Student> getAllStudents(){ // return all students
        return data;
    }

    public void addStudent(Student st){ // add a student to the list
        data.add(st);
    }
}
