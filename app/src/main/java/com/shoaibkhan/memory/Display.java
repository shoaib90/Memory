package com.shoaibkhan.memory;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Display extends AppCompatActivity {
    TextView t;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);
        t=(TextView)findViewById(R.id.txt1);
        int ef=0;
        Bundle b=getIntent().getExtras();
        int[] p=b.getIntArray("ps");//process size
         int[] allocation=b.getIntArray("bno");
         int If=b.getInt("If");
        t.append("\n|Process No.|\tProcess Size|\tBlock no.\t\t|\n");
        for (int i = 0; i < 5; i++) {
            t.append(" |\t\t\t\t" + (i + 1) + "\t\t\t\t|\t\t\t\t\t\t" + p[i] + "\t\t\t\t\t|\t\t\t\t\t\t");
            if (allocation[i] != -1) {
                t.append(String.valueOf((allocation[i] + 1)+"\t\t\t\t\t|"));
            } else {
                t.append("Not Allocated|");
                ef+=p[i];
            }t.append("\n");
        }
        t.append("\n Internal Fragmentation"+"\t"+If);
        t.append("\n External Fragmentation"+"\t"+ef);
    }
}
