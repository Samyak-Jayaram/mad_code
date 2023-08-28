package com.example.calculator;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.annotation.SuppressLint;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
public class MainActivity extends AppCompatActivity {
Button b1,b2,b3,b4,b5,b6,b7,b8,b9,b0,bplus,bsubtract,bmultiply,bdivision,b_equals;
EditText ET_Result;
boolean add,subtract,multiply,divide;
float v1,v2;
@SuppressLint("MissingInflatedId")
@Override
protected void onCreate(Bundle savedInstanceState) {
super.onCreate(savedInstanceState);
setContentView(R.layout.activity_main);
b1=(Button)findViewById(R.id.button_1);
b2=(Button)findViewById(R.id.button_2);
b3=(Button)findViewById(R.id.button_3);
b4=(Button)findViewById(R.id.button_4);
b5=(Button)findViewById(R.id.button_5);
b6=(Button)findViewById(R.id.button_6);
b7=(Button)findViewById(R.id.button_7);
b8=(Button)findViewById(R.id.button_8);
b9=(Button)findViewById(R.id.button_9);
b0=(Button)findViewById(R.id.button_0);
bplus=(Button)findViewById(R.id.plus);
bsubtract=(Button)findViewById(R.id.subtract);
bmultiply=(Button)findViewById(R.id.multiply);
bdivision=(Button)findViewById(R.id.division);
b_equals=(Button)findViewById(R.id.equals);
ET_Result=(EditText)findViewById(R.id.result);
b1.setOnClickListener(new View.OnClickListener() {
@Override
public void onClick(View view) {
ET_Result.setText(ET_Result.getText()+"1");
}
});
b2.setOnClickListener(new View.OnClickListener() {
@Override
public void onClick(View view) {
ET_Result.setText(ET_Result.getText()+"2");
}
});
b3.setOnClickListener(new View.OnClickListener() {
@Override
public void onClick(View view) {
ET_Result.setText(ET_Result.getText()+"3");
}
});
b4.setOnClickListener(new View.OnClickListener() {
@Override
public void onClick(View view) {
ET_Result.setText(ET_Result.getText()+"4");
}
});
b5.setOnClickListener(new View.OnClickListener() {
@Override
public void onClick(View view) {
ET_Result.setText(ET_Result.getText()+"5");
}
});
b6.setOnClickListener(new View.OnClickListener() {
@Override
public void onClick(View view) {
ET_Result.setText(ET_Result.getText()+"6");
}
});
b7.setOnClickListener(new View.OnClickListener() {
@Override
public void onClick(View view) {
ET_Result.setText(ET_Result.getText()+"7");
}
});
b8.setOnClickListener(new View.OnClickListener() {
@Override
public void onClick(View view) {
ET_Result.setText(ET_Result.getText()+"8");
}
});
b9.setOnClickListener(new View.OnClickListener() {
@Override
public void onClick(View view) {
ET_Result.setText(ET_Result.getText()+"9");
}
});
b0.setOnClickListener(new View.OnClickListener() {
@Override
public void onClick(View view) {
ET_Result.setText(ET_Result.getText()+"0");
}
});
bplus.setOnClickListener(new View.OnClickListener() {
@Override
public void onClick(View view) {
v1=Float.parseFloat(ET_Result.getText()+"");
add=true;
ET_Result.setText(null);
}
});
bsubtract.setOnClickListener(new View.OnClickListener() {
@Override
public void onClick(View view) {
v1=Float.parseFloat(ET_Result.getText()+"");
subtract=true;
ET_Result.setText(null);
}
});
bmultiply.setOnClickListener(new View.OnClickListener() {
@Override
public void onClick(View view) {
v1=Float.parseFloat(ET_Result.getText()+"");
multiply=true;
ET_Result.setText(null);
}
});
bdivision.setOnClickListener(new View.OnClickListener() {
@Override
public void onClick(View view) {
v1=Float.parseFloat(ET_Result.getText()+"");
divide=true;
ET_Result.setText(null);
}
});
b_equals.setOnClickListener(new View.OnClickListener() {
@Override
public void onClick(View view)
{
v2=Float.parseFloat(ET_Result.getText()+"");
if(add)
{
ET_Result.setText(v1 + v2 + "");
add=false;
}
if(subtract)
{
ET_Result.setText(v1 - v2 + "");
subtract=false;
}
if(multiply)
{
ET_Result.setText(v1 *v2 + "");
multiply=false;
}
if(divide)
{
ET_Result.setText(v1 /v2 + "");
divide=false;
}
}
});
}
}