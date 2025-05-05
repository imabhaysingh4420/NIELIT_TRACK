package co.in.nielit.nielittrack.views;

import android.os.Bundle;
import android.view.Menu;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import co.in.nielit.nielittrack.R;
import co.in.nielit.nielittrack.context.AppContext;
import co.in.nielit.nielittrack.controllers.StudentAttendanceAdapter;
import co.in.nielit.nielittrack.models.Student;

public class AddAttendanceActivity extends AppCompatActivity {

    ArrayList<Student> studentBeanList;
    int sessionId = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycler_view_layout);

        sessionId = getIntent().getExtras().getInt("sessionId");

        TextView titleTextView = findViewById(R.id.textView_title);
        titleTextView.setText(R.string.ADD_ATTENDANCE);
        RecyclerView recyclerView = findViewById(R.id.main_recycler_view);
        studentBeanList = ((AppContext) AddAttendanceActivity.this
                .getApplicationContext()).getStudentList();

        StudentAttendanceAdapter adapter = new StudentAttendanceAdapter(studentBeanList, sessionId);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }


}