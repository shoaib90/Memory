package com.shoaibkhan.memory;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class Ff extends android.support.v4.app.Fragment {
    private RadioGroup radioGroup;
    private Button b;
    public EditText ed1, ed2, ed3, ed4, ed5, ed6, ed7, ed8, ed9, ed10;
    int p[] = new int[10];
    int bl[] = new int[10];
    public RadioButton f1,f2,f3;
    private TextView tv;

    public Ff() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ((MainActivity)getActivity()).setActionBarTitle("Fixed Memory Allocation");
        // Inflate the layout for this fragment
        View v=inflater.inflate(R.layout.fragment_ff, container, false);
        ed1 = (EditText) v.findViewById(R.id.p1);
        ed2 = (EditText) v.findViewById(R.id.p2);
        ed3 = (EditText) v.findViewById(R.id.p3);
        ed4 = (EditText) v.findViewById(R.id.p4);
        ed5 = (EditText) v.findViewById(R.id.p5);
        ed6 = (EditText) v.findViewById(R.id.b1);
        ed7 = (EditText) v.findViewById(R.id.b2);
        ed8 = (EditText) v.findViewById(R.id.b3);
        ed9 = (EditText) v.findViewById(R.id.b4);
        ed10 = (EditText) v.findViewById(R.id.b5);
        tv = (TextView) v.findViewById(R.id.txt1);
        f1=(RadioButton)v.findViewById(R.id.f1);
        f2=(RadioButton)v.findViewById(R.id.f2);
        f3=(RadioButton)v.findViewById(R.id.f3);
        b = (Button) v.findViewById(R.id.submit);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                p[0] = Integer.parseInt(ed1.getText().toString());
                p[1] = Integer.parseInt(ed2.getText().toString());
                p[2] = Integer.parseInt(ed3.getText().toString());
                p[3] = Integer.parseInt(ed4.getText().toString());
                p[4] = Integer.parseInt(ed5.getText().toString());
                bl[0] = Integer.parseInt(ed6.getText().toString());
                bl[1] = Integer.parseInt(ed7.getText().toString());
                bl[2] = Integer.parseInt(ed8.getText().toString());
                bl[3] = Integer.parseInt(ed9.getText().toString());
                bl[4] = Integer.parseInt(ed10.getText().toString());
                if (f1.isChecked() == true) {
                    int allocation[] = new int[5];
                    int If=0;
                    // Initially no block is assigned to any process
                    for (int i = 0; i < allocation.length; i++)
                        allocation[i] = -1;
                    // pick each process and find suitable blocks
                    // according to its size ad assign to it
                    for (int i = 0; i < 5; i++) {
                        for (int j = 0; j < 5; j++) {
                            if (bl[j] >= p[i]) {
                                // allocate block j to p[i] process
                                allocation[i] = j;
                                If+=bl[j]-p[i];
                                bl[j]=0;

                                break;
                            }
                        }
                    }
                    Intent in = new Intent(getActivity(), Display.class); //fragmnet to activity
                    in.putExtra("ps",p);//process size
                    in.putExtra("If",If);//Internal fragmentation
                    in.putExtra("bno",allocation); //block number
                    getActivity().startActivity(in);
                } else if (f2.isChecked() == true) {
                    int allocation[] = new int[5];
                    int If=0;
                    for (int i = 0; i < allocation.length; i++)
                        allocation[i] = -1;
                    for (int i=0; i<5; i++)
                    {
                        int wstIdx = -1;
                        for (int j=0; j<5; j++)
                        {
                            if (bl[j] >= p[i])
                            {
                                if (wstIdx == -1)
                                    wstIdx = j;
                                else if (bl[wstIdx] < bl[j])
                                    wstIdx = j;
                            }
                        }
                        if (wstIdx != -1)
                        {
                            // allocate block j to p[i] process
                            allocation[i] = wstIdx;
                            If+=bl[wstIdx]-p[i];
                            bl[wstIdx]=0;
                        }
                    }
                    Intent in = new Intent(getActivity(), Display.class);
                    in.putExtra("ps",p);
                    in.putExtra("bno",allocation);
                    in.putExtra("If",If);
                    getActivity().startActivity(in);
                }
                else if (f3.isChecked() == true) {
                    int allocation[] = new int[5];;
                    int If=0;
                    for (int i = 0; i < allocation.length; i++)
                        allocation[i] = -1;
                    for (int i=0; i<5; i++)
                    {
                        int bestIdx = -1;
                        for (int j=0; j<5; j++)
                        {
                            if (bl[j] >= p[i])
                            {
                                if (bestIdx == -1)
                                    bestIdx = j;
                                else if (bl[bestIdx] > bl[j])
                                    bestIdx = j;
                            }
                        }
                        if (bestIdx != -1)
                        {
                            allocation[i] = bestIdx;
                            If+=bl[bestIdx]-p[i];
                            bl[bestIdx]=0;
                        }
                    }
                    Intent in = new Intent(getActivity(), Display.class);
                    in.putExtra("ps",p);
                    in.putExtra("bno",allocation);
                    in.putExtra("If",If);
                    getActivity().startActivity(in);
                }

            }


        });
return v;
    }
}
