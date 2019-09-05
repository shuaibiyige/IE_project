package com.example.ie_project;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.provider.CalendarContract;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.GregorianCalendar;

public class RecommendActivity extends AppCompatActivity {

    private ListView listView;
    private Context mContext;
    private String[] data = {
            "do exercise, 19:00, 2019-8-16"
    };
    private ArrayAdapter<String> adapter;

    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recommend_activity);
        mContext = this;
        adapter = new ArrayAdapter<String>(RecommendActivity.this, android.R.layout.simple_list_item_1, data);

        listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                String[] event = data[0].split(", ");
                String activity = event[0];
                String time = event[1];
                String[] a = event[2].split("-");
                String year = a[0];
                String month = a[1];
                String day = a[2];
                //open(activity, time, year, month, day);
                String location = "Monash university";
                dialogShow(activity, event[2], location);
            }
        });
    }

    public void open(String activity, String time, String year, String month, String day)
    {
//        Intent intent = new Intent(Intent.ACTION_INSERT);
//        intent.setData(CalendarContract.Events.CONTENT_URI);
//        startActivity(intent);

        Intent intent = new Intent(Intent.ACTION_INSERT);
        intent.setType("vnd.android.cursor.item/event");
        intent.putExtra(CalendarContract.Events.TITLE, activity);
        intent.putExtra(CalendarContract.Events.EVENT_LOCATION, "this is location");
        intent.putExtra(CalendarContract.Events.DESCRIPTION, "do exercises with kid!");

// Setting dates
        GregorianCalendar calDate = new GregorianCalendar(Integer.valueOf(year), Integer.valueOf(month), Integer.valueOf(day));
        intent.putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, calDate.getTimeInMillis());
        intent.putExtra(CalendarContract.EXTRA_EVENT_END_TIME, calDate.getTimeInMillis());
        startActivity(intent);
    }

    private void dialogShow(String act, String ti, String loc)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        LayoutInflater inflater = LayoutInflater.from(mContext);
        final View v = inflater.inflate(R.layout.dialog, null);
        TextView activity = (TextView) v.findViewById(R.id.activity);
        TextView time = (TextView) v.findViewById(R.id.time);
        TextView location = (TextView) v.findViewById(R.id.location);

        Button add = (Button) v.findViewById(R.id.add);
        Button remove = (Button) v.findViewById(R.id.remove);

        activity.setText(act);
        time.setText(ti);
        location.setText(loc);

        final Dialog dialog = builder.create();
        dialog.show();
        dialog.getWindow().setContentView(v);

        add.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                String[] event = data[0].split(", ");
                String activity = event[0];
                String time = event[1];
                String[] a = event[2].split("-");
                String year = a[0];
                String month = a[1];
                String day = a[2];
                open(activity, time, year, month, day);
                dialog.dismiss();
            }
        });

        remove.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                data[0] = "";
                adapter.notifyDataSetChanged();
                listView.setAdapter(adapter);
                dialog.dismiss();
            }
        });

        location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showMap();
            }
        });
    }

    public void showMap()
    {
        String encodedName = Uri.encode("monash university");
        Uri locationUri = Uri.parse("geo:0,0?q="+encodedName);
        //根据经纬度打开地图显示，?z=11表示缩放级别，范围为1-23
//        Uri locationUri = Uri.parse("geo:26.5789070770,106.7170012064?z=11");

        Intent intent = new Intent(Intent.ACTION_VIEW);
        Intent chooser = Intent.createChooser(intent, "请选择地图软件");
        intent.setData(locationUri);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(chooser);
        }
    }
}
