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


public class Vf extends Fragment {
    private RadioGroup radioGroup;
    private Button b;
    public EditText ed1, ed2, ed3, ed4, ed5, ed6, ed7, ed8, ed9, ed10;
    int p[] = new int[5];
    int bl[] = new int[5];
    private TextView tv;
    public RadioButton r1,r2,r3;


    public Vf() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ((MainActivity)getActivity()).setActionBarTitle("Variable Memory Allocation");
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_vf, container, false);
        ed1 = (EditText) v.findViewById(R.id.po1);
        ed2 = (EditText) v.findViewById(R.id.po2);
        ed3 = (EditText) v.findViewById(R.id.po3);
        ed4 = (EditText) v.findViewById(R.id.po4);
        ed5 = (EditText) v.findViewById(R.id.po5);
        ed6 = (EditText) v.findViewById(R.id.bl1);
        ed7 = (EditText) v.findViewById(R.id.bl2);
        ed8 = (EditText) v.findViewById(R.id.bl3);
        ed9 = (EditText) v.findViewById(R.id.bl4);
        ed10 = (EditText) v.findViewById(R.id.bl5);
        b = (Button) v.findViewById(R.id.sub);
        r1=(RadioButton)v.findViewById(R.id.v1);
        r2=(RadioButton)v.findViewById(R.id.v2);
        r3=(RadioButton)v.findViewById(R.id.v3);
        tv=(TextView)v.findViewById(R.id.text1);
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
                if (r1.isChecked() == true) {
                    int allocation[] = new int[5];
                    int t[] = new int[5];
                    int If=0;
                    // Initially no block is assigned to any process
                    for (int i = 0; i < allocation.length; i++)
                        allocation[i] = -1;

                    for(int i=0;i<5;i++)
                    {
                        t[i]=bl[i];
                    }
                    // pick each process and find suitable blocks
                    // according to its size ad assign to it
                    for (int i = 0; i < 5; i++) {
                        for (int j = 0; j < 5; j++) {
                            if (bl[j] >= p[i]) {
                                // allocate block j to p[i] process
                                allocation[i] = j;

                                // Reduce available memory in this block.
                                bl[j] -= p[i];

                                break;
                            }
                        }
                    }
                    for(int i=0;i<5;i++ )
                    {
                        if(t[i]!=bl[i])
                        {
                            If+=bl[i];
                        }
                    }
                    Intent in = new Intent(getActivity(), Display.class);
                        in.putExtra("ps",p);
                        in.putExtra("If",If);
                        in.putExtra("bno",allocation);
                        getActivity().startActivity(in);
                } else if (r2.isChecked() == true) {
                    int allocation[] = new int[5];
                    int t[] = new int[5];
                    int If=0;
                    for (int i = 0; i < allocation.length; i++)
                        allocation[i] = -1;
                    for(int i=0;i<5;i++)
                    {
                        t[i]=bl[i];
                    }
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

                            // Reduce available memory in this block.
                            bl[wstIdx] -= p[i];
                        }
                    }
                    for(int i=0;i<5;i++ )
                    {
                        if(t[i]!=bl[i])
                        {
                            If+=bl[i];
                        }
                    }
                    Intent in = new Intent(getActivity(), Display.class);
                    in.putExtra("ps",p);
                    in.putExtra("bno",allocation);
                    in.putExtra("If",If);
                    getActivity().startActivity(in);
                }
                else if (r3.isChecked() == true) {
                    int allocation[] = new int[5];
                    int t[] = new int[5];
                    int If=0;
                    for (int i = 0; i < allocation.length; i++)
                        allocation[i] = -1;
                    for(int i=0;i<5;i++)
                    {
                        t[i]=bl[i];
                    }
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
                            bl[bestIdx] -= p[i];
                        }
                    }
                    for(int i=0;i<5;i++ )
                    {
                        if(t[i]!=bl[i])
                        {
                            If+=bl[i];
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
