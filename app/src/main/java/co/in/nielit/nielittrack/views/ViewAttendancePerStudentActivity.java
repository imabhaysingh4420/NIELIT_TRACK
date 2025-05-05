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
import co.in.nielit.nielittrack.controllers.AttendanceCountAdapter;
import co.in.nielit.nielittrack.models.Attendance;

public class ViewAttendancePerStudentActivity extends AppCompatActivity {

    ArrayList<Attendance> attendanceBeanList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.recycler_view_layout);

        TextView titleTextView = findViewById(R.id.textView_title);
        titleTextView.setText(R.string.ATTENDANCE_REPORT);
        RecyclerView recyclerView = findViewById(R.id.main_recycler_view);

        attendanceBeanList = ((AppContext) this.getApplicationContext()).getAttendanceList();

        AttendanceCountAdapter adapter = new AttendanceCountAdapter(attendanceBeanList);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }

   
}