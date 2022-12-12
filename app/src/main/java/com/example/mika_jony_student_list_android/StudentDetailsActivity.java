package com.example.mika_jony_student_list_android;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mika_jony_student_list_android.Model.Model;
import com.example.mika_jony_student_list_android.Model.Student;

import java.util.List;

public class StudentDetailsActivity extends AppCompatActivity {

    CheckBox cb;
    TextView name, id, phone, address;
    List<Student> data = Model.instance().getAllStudents();
    Student student;

    Button back,edit;

    Intent thisI;
    int pos;


    public Intent editIntent;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_details);

        setContentView(R.layout.activity_student_details);


        thisI = getIntent();
        pos = (int)thisI.getSerializableExtra("pos");
        student = data.get(pos);
        name = findViewById(R.id.student_details_tv_2nd_name);
        id = findViewById(R.id.student_details_tv_2nd_ID);
        phone=findViewById(R.id.student_details_tv_2nd_phone);
        address=findViewById(R.id.student_details_tv_2nd_address);
        cb = findViewById(R.id.student_details_cb);
        back=findViewById(R.id.student_details_back_btn);
        edit=findViewById(R.id.student_details_edit_btn);

        this.bind(student,pos); // bind the student data to the text views

        edit.setOnClickListener(view -> { // when the edit button is clicked
            editIntent = new Intent(this, StudentEditActivity.class); // create a new intent to the StudentEditActivity
            editIntent.putExtra("pos",pos); // put the position of the student in the intent
            startActivity(editIntent); // start the activity

        });



        back.setOnClickListener(view -> finish());
    }

    private void bind(Student st, int pos) {
        name.setText(st.name);
        id.setText(st.id);
        phone.setText(st.phone);
        address.setText(st.address);
        cb.setChecked(st.cb);
    }
    @Override
    protected void onStart() {
        super.onStart();
        bind(student,pos);
    }
}
