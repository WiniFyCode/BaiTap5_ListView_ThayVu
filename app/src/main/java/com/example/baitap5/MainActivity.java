package com.example.baitap5;

import static java.util.Collections.addAll;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Filter;
import android.widget.GridView;
import android.widget.Spinner;
import android.widget.TextView;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.widget.TimePicker;

import java.util.ArrayList;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    private Spinner spinnerChonTinh;
    private AutoCompleteTextView autoChonQuan;
    private GridView gridView;
    private TextView tvDatePicker, tvTimePicker;
    private Button btnDatePicker, btnTimePicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spinnerChonTinh = findViewById(R.id.spinnerChonTinh);
        autoChonQuan = findViewById(R.id.autoChonQuan);
        gridView = findViewById(R.id.gridView);
        tvDatePicker = findViewById(R.id.tvDatePicker);
        tvTimePicker = findViewById(R.id.tvTimePicker);
        btnDatePicker = findViewById(R.id.btnDatePicker);
        btnTimePicker = findViewById(R.id.btnTimePicker);

        // Thiết lập Spinner cho tỉnh thành
        ArrayAdapter<String> tinhAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, Data.TINH_THANH);
        tinhAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinnerChonTinh.setAdapter(tinhAdapter);
        spinnerChonTinh.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // Cập nhật ArrayAdapter cho AutoCompleteTextView
                ArrayAdapter<String> quanHuyenAdapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_dropdown_item_1line, Data.QUAN_HUYEN[position]);
                autoChonQuan.setAdapter(quanHuyenAdapter);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Không làm gì
            }
        });

        // Thiết lập AutoCompleteTextView cho quận/huyện
        ArrayAdapter<String> quanHuyenAdapter = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, Data.QUAN_HUYEN[0]);
        autoChonQuan.setAdapter(quanHuyenAdapter);

        // Thiết lập GridView
        int[] imageResources = {R.drawable.anuong, R.drawable.dienthoai, R.drawable.nha, R.drawable.oto, R.drawable.noithat, R.drawable.thethao, R.drawable.thoitrang, R.drawable.tuyendung, R.drawable.vietnammap,R.drawable.anuong, R.drawable.dienthoai, R.drawable.nha, R.drawable.oto, R.drawable.noithat, R.drawable.thethao, R.drawable.thoitrang, R.drawable.tuyendung, R.drawable.vietnammap,R.drawable.anuong, R.drawable.dienthoai, R.drawable.nha, R.drawable.oto, R.drawable.noithat, R.drawable.thethao, R.drawable.thoitrang, R.drawable.tuyendung, R.drawable.vietnammap,R.drawable.anuong, R.drawable.dienthoai, R.drawable.nha, R.drawable.oto, R.drawable.noithat, R.drawable.thethao, R.drawable.thoitrang, R.drawable.tuyendung, R.drawable.vietnammap,R.drawable.anuong, R.drawable.dienthoai, R.drawable.nha, R.drawable.oto, R.drawable.noithat, R.drawable.thethao, R.drawable.thoitrang, R.drawable.tuyendung, R.drawable.vietnammap,R.drawable.anuong, R.drawable.dienthoai, R.drawable.nha, R.drawable.oto, R.drawable.noithat, R.drawable.thethao, R.drawable.thoitrang, R.drawable.tuyendung, R.drawable.vietnammap,R.drawable.anuong, R.drawable.dienthoai, R.drawable.nha, R.drawable.oto, R.drawable.noithat, R.drawable.thethao, R.drawable.thoitrang, R.drawable.tuyendung, R.drawable.vietnammap,R.drawable.anuong, R.drawable.dienthoai, R.drawable.nha, R.drawable.oto, R.drawable.noithat, R.drawable.thethao, R.drawable.thoitrang, R.drawable.tuyendung, R.drawable.vietnammap,R.drawable.anuong, R.drawable.dienthoai, R.drawable.nha, R.drawable.oto, R.drawable.noithat, R.drawable.thethao, R.drawable.thoitrang, R.drawable.tuyendung, R.drawable.vietnammap}; // Thay thế bằng ID hình ảnh của bạn

        // Tạo đối tượng ImageAdapter
        ImageAdapter imageAdapter = new ImageAdapter(this, imageResources);
        gridView.setAdapter(imageAdapter);

        // Thiết lập DatePicker
        btnDatePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Lấy ngày hiện tại
                final Calendar c = Calendar.getInstance();
                int year = c.get(Calendar.YEAR);
                int month = c.get(Calendar.MONTH);
                int day = c.get(Calendar.DAY_OF_MONTH);

                // Tạo DatePickerDialog
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        MainActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                // Cập nhật TextView khi người dùng chọn ngày
                                tvDatePicker.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                            }
                        },
                        year, month, day);
                datePickerDialog.show();
            }
        });

        // Thiết lập TimePicker
        btnTimePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Lấy giờ hiện tại
                final Calendar c = Calendar.getInstance();
                int hour = c.get(Calendar.HOUR_OF_DAY);
                int minute = c.get(Calendar.MINUTE);

                // Tạo TimePickerDialog
                TimePickerDialog timePickerDialog = new TimePickerDialog(
                        MainActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        // Cập nhật TextView khi người dùng chọn giờ
                        tvTimePicker.setText(hourOfDay + ":" + minute);
                    }
                },
                        hour, minute, true); // true: sử dụng định dạng 24 giờ
                timePickerDialog.show();
            }
        });
    }
}