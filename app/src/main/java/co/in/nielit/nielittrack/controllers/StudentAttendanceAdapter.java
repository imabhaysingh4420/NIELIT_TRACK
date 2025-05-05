package co.in.nielit.nielittrack.controllers;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import co.in.nielit.nielittrack.R;
import co.in.nielit.nielittrack.models.Attendance;
import co.in.nielit.nielittrack.models.Student;

public class StudentAttendanceAdapter extends RecyclerView.Adapter<StudentAttendanceAdapter.ViewHolder> {

    private final List<Student> studentList;
    private final int sessionId;
    private final Map<String, String> attendanceMap = new HashMap<>();
    private Context context;

    public StudentAttendanceAdapter(List<Student> studentList, int sessionId) {
        this.studentList = studentList;
        this.sessionId = sessionId;
    }

    @NonNull
    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View customView = inflater.inflate(R.layout.add_student_attendance, parent, false);
        return new ViewHolder(customView);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ViewHolder holder, int position) {
        Student student = studentList.get(position);

        holder.rollTextView.setText(student.getStudent_roll());
        holder.nameTextView.setText(student.getStudent_firstname() + " " + student.getStudent_lastname());

        String status = attendanceMap.get(student.getStudent_roll());
        updateButtonUI(holder, status);

        holder.presentButton.setOnClickListener(v -> {
            attendanceMap.put(student.getStudent_roll(), "P");
            updateButtonUI(holder, "P");
        });

        holder.absentButton.setOnClickListener(v -> {
            attendanceMap.put(student.getStudent_roll(), "A");
            updateButtonUI(holder, "A");
        });

        // Handle Submit Attendance Button
        if (position == getItemCount() - 1) {
            holder.submitAttendanceButton.setVisibility(View.VISIBLE);
            holder.submitAttendanceButton.setOnClickListener(v -> {
                saveAllAttendance();
            });
        } else {
            holder.submitAttendanceButton.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return studentList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView nameTextView;
        public TextView rollTextView;
        public Button presentButton;
        public Button absentButton;
        public Button submitAttendanceButton;

        public ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            rollTextView = itemView.findViewById(R.id.label_roll);
            nameTextView = itemView.findViewById(R.id.label_student);
            presentButton = itemView.findViewById(R.id.button_present);
            absentButton = itemView.findViewById(R.id.button_absent);
            submitAttendanceButton = itemView.findViewById(R.id.button_submit_attendance);
        }
    }

    // Save all marked attendance into database
    public void saveAllAttendance() {
        DBHandler handler = new DBHandler(context);
        boolean anySaved = false;

        for (Student student : studentList) {
            String roll = student.getStudent_roll();
            String status = attendanceMap.get(roll);

            if (status != null) {
                Attendance attendance = new Attendance();
                attendance.setAttendance_session_id(sessionId);
                attendance.setAttendance_student_roll(roll);
                attendance.setAttendance_status(status);

                handler.addNewAttendance(attendance);
                anySaved = true;
            }
        }

        if (anySaved) {
            Toast.makeText(context, "Attendance Successfully Saved!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Please mark attendance first!", Toast.LENGTH_SHORT).show();
        }
    }

    // Update button UI based on selection
    private void updateButtonUI(ViewHolder holder, String selectedStatus) {
        if ("P".equals(selectedStatus)) {
            holder.presentButton.setAlpha(1.0f);
            holder.absentButton.setAlpha(0.5f);
        } else if ("A".equals(selectedStatus)) {
            holder.absentButton.setAlpha(1.0f);
            holder.presentButton.setAlpha(0.5f);
        } else {
            holder.presentButton.setAlpha(0.5f);
            holder.absentButton.setAlpha(0.5f);
        }
    }
}
