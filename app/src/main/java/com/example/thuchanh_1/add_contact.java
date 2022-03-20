package com.example.thuchanh_1;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.Toast;

import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.normal.TedPermission;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import gun0912.tedbottompicker.TedBottomPicker;

public class add_contact extends AppCompatActivity {

    ImageView imageView;
    String uri =null;
    Button btn_ImageAdd,btn_AddConfirm,btn_Cancle;
    EditText edt_DeviceName,edt_DeviceWattage,edt_ID;
    Switch swtStatusAdd;
    public void Connect_ID()
    {
        imageView=findViewById(R.id.ImageAdd);
        btn_ImageAdd=findViewById(R.id.btn_AddImage);
        edt_DeviceName=findViewById(R.id.edt_DeviceNameAdd);
        edt_DeviceWattage=findViewById(R.id.edt_DeviceWattageAdd);
        btn_AddConfirm=findViewById(R.id.btn_AddConfirm);
        btn_Cancle=findViewById(R.id.btn_Cancle);
        edt_ID=findViewById(R.id.edt_IDAdd);
        swtStatusAdd=findViewById(R.id.swt_StatusAdd);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact);
        Connect_ID();

        btn_AddConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Tạo intent trở về MainActivity
                Intent intent=new Intent();
                //Tạo Bundle để chứa dữ liệu
                Bundle bundle=new Bundle();

                //Bundle hoạt động như 1 javaMap phân biệt thành phần qua key
                //Bundle có các hằng put... trong đó ... là kiểu dữ liệu tương
//
//                bundle.putString("Image",uri);
//                bundle.putInt("ID",Integer.parseInt(edt_ID.getText().toString()));
//                bundle.putString("DeviceName",edt_DeviceName.getText().toString());
//                bundle.putString("DeviceWattage",edt_DeviceWattage.getText().toString());
//                bundle.putBoolean("StatusAdd",swtStatusAdd.isChecked());
                Device device = new Device(Integer.parseInt(edt_ID.getText().toString()),uri, edt_DeviceName.getText().toString(),edt_DeviceWattage.getText().toString(),swtStatusAdd.isChecked());
                bundle.putSerializable("device", (Serializable) device);

                //Đăt bunler lên intent
                intent.putExtras(bundle);

                //Trả dữ liệu cho MainActivity bằng hàm setResult
                //Tham số thứ nhất là resultCode để quản lí phiên
                //Than số thứ 2 là intent chứa dữ liệu
                setResult(200,intent);
                finish();

            }
        });
        btn_ImageAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               Intent intent = new Intent();
               intent.setType("image/*");
               intent.setAction(Intent.ACTION_GET_CONTENT);
               startActivityForResult(Intent.createChooser(intent,"chọn ảnh"),VIDEO_PICK_GALLEY_CODE);
            }
        });
    }
    private static final int VIDEO_PICK_GALLEY_CODE =100;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode== Activity.RESULT_OK)
        {
            if(requestCode==VIDEO_PICK_GALLEY_CODE)
            {
                assert data!=null;
                uri=data.getData().toString();
                imageView.setImageURI(data.getData());
            }
        }
    }
    //    @Override
//    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//        switch (requestCode)
//        {
//            case 1:
//                if(grantResults.length>0&&grantResults[0]==PackageManager.PERMISSION_GRANTED)
//                {
//                    openImagePicker();
//                }
//                else
//                {
//                    Toast.makeText(this,"Bạn chưa cấp quyền truy cập máy ảnh!!!",Toast.LENGTH_LONG).show();
//                }
//                break;
//        }
//    }

//    public  void XinQuyen()
//    {
//        if(ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)
//                != PackageManager.PERMISSION_GRANTED)
//        {
//            //XIN QUYỀN TRUY CẬP MÁY ẢNH
//            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},1);
//        }
//        if(ContextCompat.checkSelfPermission(this,Manifest.permission.CAMERA)
//                !=PackageManager.PERMISSION_GRANTED)
//        {
//            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.CAMERA},2);
//        }
//    }

//    private void requestPermission()
//    {
//        PermissionListener permissionlistener = new PermissionListener() {
//            @Override
//            public void onPermissionGranted() {
//                openImagePicker();
//            }
//            @Override
//            public void onPermissionDenied(List<String> deniedPermissions) {
//                // Toast.makeText(this, "Permission Denied\n" + deniedPermissions.toString(), Toast.LENGTH_SHORT).show();
//
//            }
//        };
//        TedPermission.create()
//                .setPermissionListener(permissionlistener)
//                .setDeniedMessage("If you reject permission,you can not use this service\n\nPlease turn on permissions at [Setting] > [Permission]")
//                .setPermissions(Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE)
//                .check();
//
//    }
//    public void openImagePicker()
//    {
//        TedBottomPicker.OnImageSelectedListener listener=new TedBottomPicker.OnImageSelectedListener() {
//            @Override
//            public void onImageSelected(Uri uri) {
//                try {
//                    Bitmap bitmap= MediaStore.Images.Media.getBitmap(getContentResolver(),uri);
//                    imageView.setImageBitmap(bitmap);
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        };
//        TedBottomPicker tedBottomPicker=new TedBottomPicker.Builder(this)
//                .setOnImageSelectedListener(listener)
//                .create();
//        tedBottomPicker.show(getSupportFragmentManager());
//    }
}