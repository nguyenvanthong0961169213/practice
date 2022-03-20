package com.example.thuchanh_1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Debug;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class myDB extends SQLiteOpenHelper {
    //Tên bảng
    public static String TableName="DeviceTable";
    //Tên các cột trong bảng
    public  static final String ID="ID";
    public  static final String Img="Image";
    public  static final String Name="Name";
    public  static final String Wattage="Wattage";
    public  static final String Status="Status";
    public  static  boolean checkStatus;
    public myDB(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
//        String sqlCreate=" Create table if not exists "+ TableName + " ("
//                + ID +"Integer Primary key, "+Img+" Text,"+Name+" Text,"
//                + Wattage+ " Text,"+Status+" Int )";

        String sqlCreate =" Create table if not exists " + TableName + " ("
                + ID + " Integer Primary key," +Img+" Text," +Name+" Text,"
                +Wattage+" Text,"+Status+" Integer)";
        //Chạy câu truy vấn để tạo bảng
        sqLiteDatabase.execSQL(sqlCreate);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        //Xóa bảng TableContact đã có
        sqLiteDatabase.execSQL("Drop table if exists "+TableName);
        //Tạo lại
        onCreate(sqLiteDatabase);
    }
   public  static final String TAG=MainActivity.class.getSimpleName();
    public  void AddDevice(Device device)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(ID,device.getID());
        values.put(Img,device.getImage());
        values.put(Name,device.getName());
        values.put(Wattage,device.getWattage());
        values.put(Status, device.isStatus()?1:0);
        Log.e(TAG,"WATTAGE");
        db.insert(TableName,null,values);
        db.close();
    }
    public void deteteContact(int id)
    {
        SQLiteDatabase db=getWritableDatabase();
        String sql="Delete From "+TableName+" Where ID="+id;
        db.execSQL(sql);
    }
    public ArrayList<Device> getAllDevice()
    {
        ArrayList<Device> list=new ArrayList<>();
        //Câu truy vấn
        String sql=" Select * from "+TableName;
        //Lấy đối tượng csal sqlite
        SQLiteDatabase db=this.getReadableDatabase();
        //Chạy câu truy vấn trả về dạng Cursor
        Cursor cursor=db.rawQuery(sql,null);
        //tạo ArrayList<Contact> đã trả về
        if(cursor!=null)
        {
            while (cursor.moveToNext())
            {
                if(cursor.getInt(4)==0)
                {
                    checkStatus=false;
                }
                else
                {
                    checkStatus=true;
                }
                Device contact=new Device(
                        cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        checkStatus
                        );
                list.add(contact);
            }
        }
        return list;
    }
}
