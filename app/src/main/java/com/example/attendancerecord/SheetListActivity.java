package com.example.attendancerecord;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class SheetListActivity extends AppCompatActivity {
    private ListView sheetList;
    private ArrayAdapter adapter;
    private ArrayList<String> listItems=new ArrayList();
    private long cid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sheet_list);
        setToolbar();
        cid=getIntent().getLongExtra("cid",-1);
        loadListItems();
        sheetList=findViewById(R.id.sheetList);
        adapter=new ArrayAdapter(this,R.layout.sheet_list,R.id.date_list_item,listItems);
        sheetList.setAdapter(adapter);

        sheetList.setOnItemClickListener((parent, view, position, id) -> openSheetActivity(position));
    }

    private void setToolbar() {
        ImageButton back=findViewById(R.id.back);
        TextView title=findViewById(R.id.title_toolbar);
        back.setOnClickListener(view -> onBackPressed());
        title.setText("Month Divided Attendance");
    }

    private void openSheetActivity(int position) {
        long[] idArray=getIntent().getLongArrayExtra("idArray");
        int[] rollArray=getIntent().getIntArrayExtra("rollArray");
        String[] nameArray=getIntent().getStringArrayExtra("nameArray");
        Intent ii =new Intent(this,SheetActivity.class);
        ii.putExtra("idArray",idArray);
        ii.putExtra("rollArray",rollArray);
        ii.putExtra("nameArray",nameArray);
        ii.putExtra("month",listItems.get(position));

        startActivity(ii);
    }

    private void loadListItems() {
        Cursor cursor=new DbHelper(this).getDistinctMonths(cid);

        while (cursor.moveToNext()){
            String date=cursor.getString(cursor.getColumnIndex(DbHelper.DATE_KEY));//01.04.2021
            listItems.add(date.substring(3));
        }
    }
}