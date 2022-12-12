package com.example.mika_jony_student_list_android;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mika_jony_student_list_android.Model.Model;
import com.example.mika_jony_student_list_android.Model.Student;

import java.util.List;
public class StudentEditActivity extends AppCompatActivity{
    EditText name, id, phone, address;
    CheckBox cb;
    List<Student> data = Model.instance().getAllStudents();

    Button cancel,save,delete;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_student);

        Intent thisI = getIntent();
        int pos = (int)thisI.getSerializableExtra("pos"); // get the position of the student from the intent

        Student st = data.get(pos);

        name = findViewById(R.id.student_edit_2nd_name);

        id = findViewById(R.id.student_edit_2nd_id);

        phone=findViewById(R.id.student_edit_2nd_phone);

        address=findViewById(R.id.student_edit_2nd_address);

        cb = findViewById(R.id.student_edit_cb);

        cancel=findViewById(R.id.student_edit_cancel_btn);

        delete=findViewById(R.id.student_edit_delete_btn);

        save=findViewById(R.id.student_edit_save_btn);


        this.bindStudentData(st,pos);

        save.setOnClickListener(view -> { // when the save button is clicked
            this.bindDataFromText(pos); // bind the data from the text views to the student
            finish();

        });

        delete.setOnClickListener(view->{ // when the delete button is clicked
            Model.instance().deleteStudent(pos); // delete the student from the model
            Intent deleteIntent = new Intent(this, StudentRecyclerList.class); // create a new intent to the StudentRecyclerList
            startActivity(deleteIntent);

        });
        cancel.setOnClickListener(view -> finish());

    }

    private void bindDataFromText(int pos) {
        data.get(pos).name=name.getText().toString();

        data.get(pos).id=id.getText().toString();

        data.get(pos).phone=phone.getText().toString();

        data.get(pos).address=address.getText().toString();

        data.get(pos).cb=cb.isChecked();
    }

    private void bindStudentData(Student st, int pos) {
        name.setText(st.name); // set the name text view to the student name and exc...

        id.setText(st.id);

        cb.setChecked(st.cb);

        phone.setText(st.phone);

        address.setText(st.address);
    }
}
