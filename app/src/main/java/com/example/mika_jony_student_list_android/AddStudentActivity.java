package com.example.mika_jony_student_list_android;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mika_jony_student_list_android.Model.Model;
import com.example.mika_jony_student_list_android.Model.Student;

import java.util.List;

public class AddStudentActivity extends AppCompatActivity {
    CheckBox cb;
    Button cancel,save;
    List<Student> data = Model.instance().getAllStudents();
    EditText name2nd, id2nd, phone2nd, address2nd;

    Intent addI;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student);

        name2nd = (EditText)findViewById(R.id.student_add_st_2nd_name); // find the student_add_st_2nd_name EditText and exc....
        id2nd = findViewById(R.id.student_add_st_2nd_id);
        phone2nd=findViewById(R.id.student_add_st_2nd_phone);
        address2nd=findViewById(R.id.student_add_st_2nd_address);
        cb = findViewById(R.id.student_add_st_cb);
        cancel=findViewById(R.id.student_add_st_cancel_btn);
        save=findViewById(R.id.student_add_st_save_btn);

        cancel.setOnClickListener(view -> finish());
        save.setOnClickListener((view)->{
            Student st=new Student(name2nd.getText().toString(),id2nd.getText().toString(),phone2nd.getText().toString(),address2nd.getText().toString(),"",cb.isChecked());
            data.add(st);
            finish();
        });
    }
}
