package com.example.myapplication;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

public class SQLite_Activity extends AppCompatActivity implements View.OnClickListener {
    private Button generateBtn,addBtn,deleteBtn,updateBtn,selectBtn;
    private EditText ed_username,ed_password,ed_selection;
    private TextView showInfo;
    private MyDbHelper myDbHelper;
    private SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sqlite);
        myDbHelper=new MyDbHelper(SQLite_Activity.this,"MyDataBase.db",null,666);
        init();
        generateBtn.setOnClickListener(this);
        addBtn.setOnClickListener(this);
        deleteBtn.setOnClickListener(this);
        updateBtn.setOnClickListener(this);
        selectBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            /*case R.id.generate://创建数据库和表
                //设置数据库的相关参数，初始化数据库
                myDbHelper=new MyDbHelper(SQLite_Activity.this,"MyDataBase.db",null,666);
                //通过帮助类获取数据库对象
                db=myDbHelper.getWritableDatabase();
                break;*/
            case R.id.add:
                db=myDbHelper.getWritableDatabase();
                String username=ed_username.getText().toString();
                String password=ed_password.getText().toString();
                //创建一个ContentValues对象用来存储记录的字段，以键值对的方式存储，键对应的是字段名，值对应的是某个字段具体的值
                /*ContentValues contentValues=new ContentValues();
                contentValues.put("username",username);
                contentValues.put("password",password);
                db.insert("user",null,contentValues);*/
                db.execSQL("insert into user(username,password) values(?,?)",new Object[]{username,password});
                db.close();
                Toast.makeText(SQLite_Activity.this,"数据添加成功",Toast.LENGTH_SHORT).show();
                break;
                //查询的方法：查询所有数据
            case R.id.select:
                db=myDbHelper.getWritableDatabase();
                //Cursor接口类，接口集中会有游标，游标会指向接口集中的某一条记录，游标指向那一条记录，我们获取的就是那一条记录，初始时会指向第一条记录
                Cursor cursor=db.query("user",new String[]{"username","password"},null,null,null,null,null,null);
                cursor.moveToNext();
                showInfo.setText("用户名："+cursor.getString(0)+"密码："+cursor.getString(1));
                while (cursor.moveToNext()){
                    showInfo.append("\n用户名："+cursor.getString(0)+"密码："+cursor.getString(1));
                }
                cursor.close();
                db.close();
                Toast.makeText(SQLite_Activity.this,"数据查询成功",Toast.LENGTH_SHORT).show();
                break;
                //高级查询
            case R.id.generate:
                db=myDbHelper.getWritableDatabase();
                String selection=ed_selection.getText().toString();
                Cursor cursor1=db.query("user",new String[]{"username","password"},"username=?",new String[]{selection},null,null,null,null);
                showInfo.setText("查询结果如下");
                while (cursor1.moveToNext()){
                    showInfo.append("\n用户名："+cursor1.getString(0)+"密码："+cursor1.getString(1));
                }
                cursor1.close();
                db.close();
                Toast.makeText(SQLite_Activity.this,"数据查询成功",Toast.LENGTH_SHORT).show();
                break;
                //删除操作
            case R.id.delete:
                db=myDbHelper.getWritableDatabase();
                String selection1=ed_selection.getText().toString();
                int i=db.delete("username","username=?",new String[]{selection1});
                if(i>0){
                    Toast.makeText(SQLite_Activity.this,"删除成功，删除了"+i+"条",Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(SQLite_Activity.this,"删除失败",Toast.LENGTH_SHORT).show();
                }
                db.close();
                break;
                //修改操作
            case R.id.update:
                db=myDbHelper.getWritableDatabase();
                String username1=ed_username.getText().toString();
                String password1=ed_password.getText().toString();
                String selection2=ed_selection.getText().toString();
                ContentValues contentValues=new ContentValues();
                contentValues.put("username",username1);
                contentValues.put("password",password1);
                db.update("user",contentValues,"username=?",new String[]{selection2});
                db.close();
                Toast.makeText(SQLite_Activity.this,"修改成功",Toast.LENGTH_SHORT).show();
                break;
        }
    }

    //初始化控件对象
    private void init(){
        generateBtn=findViewById(R.id.generate);
        addBtn=findViewById(R.id.add);
        deleteBtn=findViewById(R.id.delete);
        updateBtn=findViewById(R.id.update);
        selectBtn=findViewById(R.id.select);
        ed_username=findViewById(R.id.User_Name);
        ed_password=findViewById(R.id.Password);
        showInfo=findViewById(R.id.showInfo);
        ed_selection=findViewById(R.id.selection);
    }

    //数据库帮助类
    class MyDbHelper extends SQLiteOpenHelper {
        //构造器的作用，参数的含义，上下文，数据库文件的名称，结果集工厂，版本号
        public MyDbHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
            super(context, name, factory, version);
        }

        //数据库初始化的时候，用于创建表或者视图文件
        @Override
        public void onCreate(SQLiteDatabase sqLiteDatabase) {
            sqLiteDatabase.execSQL("create table user(userid integer primary key autoincrement,username varchar(16),password varchar(16))");
        }

        //升级方法
        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        }
    }
}