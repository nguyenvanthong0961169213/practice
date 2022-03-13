package com.example.thuchanh_1;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText edt_Device;
    ListView lsp_Device;
    Button btn_Add, btn_Delete;
    Device device =null;
    ArrayList<Device> arrDevice;
    ArrayList<Device> arrDelete;
    My_Adapter Adapter;
    ActivityResultLauncher<Intent> activityResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == RESULT_OK) {
                        //Intent intent = result.getData();
                         device = (Device) getIntent().getExtras().get("device");

                    }
                }
            }
    );

    public void Connect_ID() {
        edt_Device = findViewById(R.id.edt_Device);
        lsp_Device = findViewById(R.id.lsp_Device);
        btn_Add = findViewById(R.id.btn_ADD);
        btn_Delete = findViewById(R.id.btn_Delete);
    }

    public void Update_data_ArrDevice() {
        arrDevice = new ArrayList<>();
//       arrDevice.add(new Device(1,R.drawable.sao,"Bóng Điện","220V",true));
//       arrDevice.add(new Device(2,R.drawable.sao,"Bình Nóng","Công suất 1000W",false));
//       arrDevice.add(new Device(2,R.drawable.sao,"Điện Thoại","Công suất 1000W",false));
//       arrDevice.add(new Device(2,R.drawable.sao,"Bình Nóng","Công suất 1000W",false));
//       arrDevice.add(new Device(2,R.drawable.sao,"Điện Thoại","Công suất 1000W",false));
//       arrDevice.add(new Device(2,R.drawable.sao,"Bình Nóng","Công suất 1000W",false));
//       arrDevice.add(new Device(2,R.drawable.sao,"Điện Thoại","Công suất 1000W",false));
//       arrDevice.add(new Device(2,R.drawable.sao,"Bình Nóng","Công suất 1000W",false));
//       arrDevice.add(new Device(2,R.drawable.sao,"Điện Thoại","Công suất 1000W",false));
    }

    public void Update_data_Adapter() {
        Adapter = new My_Adapter(MainActivity.this, arrDevice);
        lsp_Device.setAdapter(Adapter);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Connect_ID();
        Update_data_ArrDevice();
        Update_data_Adapter();
        btn_Delete.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Confirm!");
                builder.setMessage("Bạn có muốn xóa các thiết bị đang bật không?");
                builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        for (int k = 0; k < arrDevice.size(); k++) {
                            if (arrDevice.get(k).isStatus() == false) {
                                arrDevice.remove(arrDevice.get(k));
                                k--;
                                Adapter.notifyDataSetChanged();
                            }
                        }
                    }
                });
                builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                builder.create();
                builder.show();
            }
        });
        edt_Device.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                Adapter.getFilter().filter(charSequence.toString());
                Adapter.notifyDataSetChanged();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        btn_Add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, add_contact.class);
              //  activityResultLauncher.launch(intent);
                startActivityForResult(intent, 100);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //Lấy dữ liệu từ newContact trả về
//        Bundle bundle=data.getExtras();
//        Device device = (Device) bundle.get("device");
//        int ID=bundle.getInt("ID");
//        String DeviceName=bundle.getString("DeviceName");
//        String IMG =bundle.getString("Image");
//
//        String DeviceWattage=bundle.getString("DeviceWattage");
//        Boolean Status=bundle.getBoolean("StatusAdd");
//        if(requestCode==100&&resultCode==200)
//        {
//            arrDevice.add(device);
//            Adapter.notifyDataSetChanged();
//        }
//    }
        arrDevice.add(device);
        Adapter.notifyDataSetChanged();
        lsp_Device.setAdapter(Adapter);
    }
}