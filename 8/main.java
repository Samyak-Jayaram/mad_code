MainActivity.java
package com.example.meetinginfo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.example.meetinginfo.ViewPagerFragmentAdapter;


public class MainActivity extends AppCompatActivity {
    ViewPagerFragmentAdapter viewPagerFragmentAdapter;
    TabLayout tabLayout;
    ViewPager2 viewPager2;
    private String[] titles= new String[]{"SCHEDULE MEETING", "GET MEETING INFO"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewPager2=findViewById(R.id.view_pager);
        tabLayout=findViewById(R.id.tab_layout);
        viewPagerFragmentAdapter=new ViewPagerFragmentAdapter(this);
        viewPager2.setAdapter(viewPagerFragmentAdapter);
        new TabLayoutMediator(tabLayout,viewPager2,(((tab, position) -> tab.setText(titles[position])))).attach();

    }

}

Fragment1.java
package com.example.meetinginfo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import static androidx.core.content.ContextCompat.getSystemService;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Fragment1#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Fragment1 extends Fragment {
    EditText date,time,agenda;
    DataBaseConn dbc;
    CalendarView calendarView;
    Button btn;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Fragment1() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Fragment1.
     */
    // TODO: Rename and change types and number of parameters

    public static Fragment1 newInstance(String param1, String param2) {
        Fragment1 fragment = new Fragment1();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_1 ,container,false);
        date=view.findViewById(R.id.txtDate);
        time=view.findViewById(R.id.txtTime);
        agenda=view.findViewById(R.id.txtAgenda);
        btn=view.findViewById(R.id.btn1);
        calendarView=view.findViewById(R.id.mCal);
        dbc=new DataBaseConn(getActivity()); //need to initialize here only
        calendarView.setVisibility(View.INVISIBLE);
        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                closeKeyBoard();
                calendarView.setVisibility(View.VISIBLE);
                calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
                    @Override
                    public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                        String d=dayOfMonth+"/"+(month+1)+"/"+year;
                        date.setText(d);
                        calendarView.setVisibility(View.INVISIBLE);
                    }
                });
            }
        });
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mdate,mTime,mAgenda;
                mdate=date.getText().toString();
                mTime=time.getText().toString();
                mAgenda=agenda.getText().toString();
                Boolean insert=dbc.insertvalue(mdate,mTime,mAgenda);
                if(insert==true){
                    Toast.makeText(getActivity(),"Data Inserted",Toast.LENGTH_SHORT).show();
                }
                else
                    Toast.makeText(getActivity(),"Data NOT Inserted",Toast.LENGTH_SHORT).show();
//txt.setText("NOT INSERTED");
            }
        });
        return view;
    }
    private void closeKeyBoard() {
        View view = getActivity().getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager)
                    getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }
}

Fragment2.java
package com.example.meetinginfo;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Fragment2#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Fragment2 extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    EditText date;
    CalendarView cal;
    Button btn1;
    DataBaseConn dbc;
    TextView t;

    public Fragment2() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Fragment2.
     */
    // TODO: Rename and change types and number of parameters
    public static Fragment2 newInstance(String param1, String param2) {
        Fragment2 fragment = new Fragment2();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_2,container,false);
        date=view.findViewById(R.id.editTextDate);
        cal=view.findViewById(R.id.calendarView);
        btn1=view.findViewById(R.id.btn2);
        dbc=new DataBaseConn(getActivity());
        // t=()
        cal.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                String d=dayOfMonth+"/"+(month+1)+"/"+year;
                date.setText(d);
            }
        });
        btn1.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("Range")
            @Override
            public void onClick(View v) {
                String d1=date.getText().toString();
                StringBuffer res=new StringBuffer();
                Cursor c=dbc.fetch(d1);
                int count=c.getCount();
                c.moveToFirst();
                if(count>0) {
                    do {

                        res.append(c.getString(c.getColumnIndex("agenda"))+"\t"+"at"+"\t"+c.getString(c.getColumnIndex("time")));
                        res.append("\n");
                        //med =(String.valueOf(c.getString(c.getColumnIndex("agenda"))));
                        //med1 =(String.valueOf(c.getString(c.getColumnIndex("time"))));
                    }while (c.moveToNext());
                    Toast.makeText(getActivity(), res,
                            Toast.LENGTH_LONG).show();
                }
                else
                {
                    Toast.makeText(getActivity(), "No Meeting on This Day....", Toast.LENGTH_LONG).show();
                }
            }
        });
        return view;
    }
}

ViewPagerFragmentAdapter.java
package com.example.meetinginfo;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import com.example.meetinginfo.Fragment1;
import com.example.meetinginfo.Fragment2;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class ViewPagerFragmentAdapter extends FragmentStateAdapter {
    private String[] titles= new String[]{"SCHEDULE MEETING", "GET MEETING INFO"};

    public ViewPagerFragmentAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
           }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        Fragment fragment=null;
        switch(position)
        {
            case 0:
                fragment= new Fragment1();
                break;
            case 1:
                fragment= new Fragment2();
                break;
        }
        return fragment;
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}

DataBaseConn.java
package com.example.meetinginfo;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;
public class DataBaseConn extends SQLiteOpenHelper {
    public DataBaseConn(Context context) {
        super(context,"MeetingDB.db",null,1);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create Table meetingTbl(date TEXT,time TEXT, agenda TEXT)");
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int
            newVersion) {
        db.execSQL("drop Table if exists meetingTbl");
    }
    public boolean insertvalue(String d, String t, String agd){
        SQLiteDatabase DB=this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("date",d);
        cv.put("time",t);
        cv.put("agenda",agd);
        long res=DB.insert("meetingTbl",null,cv); //query to insert
        if(res==-1){
            return false;
        }
        else
            return true;
    }
    public Cursor fetch(String d){
        SQLiteDatabase DB=this.getReadableDatabase();
        // String sqlquery="select name from MDTbl where date='19/3/21' AND time='Afternoon'";
        // Cursor c = DB.rawQuery(sqlquery,null);
        Cursor c = DB.rawQuery("Select time,agenda from meetingTbl where date='"+d+"' ",null);
        return c;
    }
}


DBMS.java
package com.example.meetinginfo;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;

public class DBMS extends SQLiteOpenHelper {
    public DBMS(@Nullable Context context, @Nullable String name,
                @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int
            newVersion) {
    }
}
