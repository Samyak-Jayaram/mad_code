package com.example.async;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.os.AsyncTask;
public class MainActivity extends AppCompatActivity {
Button start,stop;
TextView tv;
@Override
protected void onCreate(Bundle savedInstanceState) {
super.onCreate(savedInstanceState);
setContentView(R.layout.activity_main);
start=(Button)findViewById(R.id.start);
stop=(Button)findViewById(R.id.stop);
tv=(TextView) findViewById(R.id.res);
LongRunningTask longRunningTask = new LongRunningTask();
start.setOnClickListener(new View.OnClickListener()
{
@Override
public void onClick(View view) {
longRunningTask.doInBackground();
longRunningTask.onProgressUpdate();
}
});
stop.setOnClickListener(new View.OnClickListener()
{
@Override
public void onClick(View view) {
tv.setSelected(false);
longRunningTask.onPostExecute("Async Task Completed");
}
});
}
private class LongRunningTask extends AsyncTask<String,String,String>
{
@Override
protected void onPreExecute()
{
super.onPreExecute();
}
@Override
protected void onProgressUpdate(String... values)
{
super.onProgressUpdate(values);
Toast.makeText(getApplicationContext(),"Banner is moving",Toast.LENGTH_LONG).show();
}
@Override
protected String doInBackground(String... strings)
{
tv.setEllipsize(TextUtils.TruncateAt.MARQUEE);
tv.setSelected(true);
tv.setVisibility(View.VISIBLE);
return null;
}
@Override
protected void onPostExecute(String s)
{
super.onPostExecute(s);
tv.setVisibility(View.INVISIBLE);
Toast.makeText(getApplicationContext(),s,Toast.LENGTH_LONG).show();
}
}
}