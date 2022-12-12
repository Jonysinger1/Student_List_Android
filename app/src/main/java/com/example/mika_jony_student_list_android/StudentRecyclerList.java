package com.example.mika_jony_student_list_android;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mika_jony_student_list_android.Model.Model;
import com.example.mika_jony_student_list_android.Model.Student;

import java.util.List;

public class StudentRecyclerList extends AppCompatActivity{
    List<Student> data;
    ImageButton add;
    RecyclerView list;
    StudentRecyclerAdapter adapter;


    @SuppressLint({"MissingInflatedId", "WrongViewCast"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_recycler_list);

        data = Model.instance().getAllStudents(); // get the data from the model

        list = findViewById(R.id.studentrecycler_list); // find the studentrecycler_list RecyclerView

        list.setHasFixedSize(true); // set the list to have a fixed size

        list.setLayoutManager(new LinearLayoutManager(this)); //define the recycler view to be a list

        adapter = new StudentRecyclerAdapter(); // create a new adapter

        list.setAdapter(adapter); // set the adapter to the recycler view

        add=findViewById(R.id.studentRecycler_add_btn2); // find the studentRecycler_add_btn2 ImageButton

        add.setOnClickListener(view->{
            Intent addStudentIntent = new Intent(this,AddStudentActivity.class); // create a new intent to the AddStudentActivity
            startActivity(addStudentIntent);

        });

        adapter.setOnItemClickListener((int pos)-> {

                    //Log.d("TAG", "Row was clicked " + pos);
                    Intent intent = new Intent(this,StudentDetailsActivity.class); // create a new intent to the StudentDetailsActivity

                    intent.putExtra("pos",pos); // put the position of the student in the intent

                    startActivity(intent);
                }
        );
    }

    @Override
    protected void onStart() {
        super.onStart();
        list.setAdapter(adapter);
    }


    class StudentViewHolder extends RecyclerView.ViewHolder{ // create a new class that extends RecyclerView.ViewHolder
        TextView name;
        TextView id;
        CheckBox cb;
        public StudentViewHolder(@NonNull View itemView, OnItemClickListener listener) { // create a constructor that takes a View and an OnItemClickListener
            super(itemView);
            name = itemView.findViewById(R.id.student_list_row_name_tv); // find the student_list_row_name_tv TextView

            id = itemView.findViewById(R.id.student_list_row_id_tv);

            cb = itemView.findViewById(R.id.student_list_row_cb);

            cb.setOnClickListener(new View.OnClickListener() { // set an onClickListener to the CheckBox
                @Override
                public void onClick(View view) {
                    int pos = (int)cb.getTag(); // get the position of the student from the CheckBox tag
                    Student st = data.get(pos); // get the student from the data list
                    st.cb = cb.isChecked();
                    data.set(pos,st); //update the data

                }
            });
            itemView.setOnClickListener(new View.OnClickListener() { // set an onClickListener to the itemView
                @Override
                public void onClick(View view) {
                    int pos = getAdapterPosition(); // get the position of the student from the itemView
                    listener.onItemClick(pos); // call the onItemClick method from the OnItemClickListener
                }
            });
        }

        public void bind(Student st, int pos) { // create a bind method that takes a Student and an int
            name.setText(st.name);

            id.setText(st.id);

            cb.setChecked(st.cb);

            cb.setTag(pos);
        }
    }


    public interface OnItemClickListener{
        void onItemClick(int pos);
    }



    class StudentRecyclerAdapter extends RecyclerView.Adapter<StudentViewHolder>{
        OnItemClickListener listener;
        void setOnItemClickListener(OnItemClickListener listener){
            this.listener = listener;
        }
        @NonNull
        @Override
        public StudentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = getLayoutInflater().inflate(R.layout.student_list_row,parent,false);
            return new StudentViewHolder(view,listener);
        }

        @Override
        public void onBindViewHolder(@NonNull StudentViewHolder holder, int position) {
            Student st = data.get(position);
            holder.bind(st,position);
        }

        @Override
        public int getItemCount() {
            return data.size();
        }
    }

}
