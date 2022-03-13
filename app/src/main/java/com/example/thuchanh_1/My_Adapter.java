package com.example.thuchanh_1;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CompoundButton;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class My_Adapter extends BaseAdapter implements Filterable {
    //Attributes
    //Khai báo các phần tử thành phần của lớp My_Adapter
    //1.Lớp ngữ cảnh ứng dụng Contaxt là lớp cha của Activity
    private Activity activity;
    //2.Nguồn dữ liệu cho My_Adapter
    private List<Device> data;
    private  List<Device> databackup;
    //3.Bộ Phân tích Layour
    private LayoutInflater inflater;

    //Contructors

    public My_Adapter(Activity activity, List<Device> data) {
        this.activity = activity;
        this.data = data;
        this.inflater=(LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    //Getters and Setter
    //Hàm trả về số phần tử của ListView
    @Override
    public int getCount() {
        return data.size();
    }
    //Hàm trả về phần tử ở vị trí Position
    @Override
    public Object getItem(int i) {
        return data.get(i);
    }
    //Hàm trả về ID của vị trí position
    @Override
    public long getItemId(int i) {
        return data.get(i).getID();
    }
    //Hàm phân tích View và trả data trên ListView
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if(view==null)
        {
            view=inflater.inflate(R.layout.list_thietbi,null);
        }
        Device object=(Device) getItem(i);
        if(object!=null)
        {
            ImageView image=view.findViewById(R.id.image);
            TextView txt_Name=view.findViewById(R.id.txt_DeviceName);
            TextView txt_Wattage=view.findViewById(R.id.txt_DeviceWattage);
            Switch swt_Status=view.findViewById(R.id.swt_Status);

            image.setImageURI(Uri.parse(object.getImage()));
            txt_Name.setText(object.getName());
            txt_Wattage.setText(object.getWattage());
            swt_Status.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                    object.setStatus(b);
                }
            });
            swt_Status.setChecked(object.isStatus());
        }
        return view;
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                FilterResults filterResults=new FilterResults();
                if(databackup==null)
                {
                    databackup=new ArrayList<>(data);
                }
                if(charSequence.length()==0||charSequence==null)
                {
                    filterResults.count=databackup.size();
                    filterResults.values=databackup;
                }
               else
                {
                    ArrayList<Device> newData=new ArrayList<>();
                    for (Device item:databackup)
                    {
                        if(item.getName().toLowerCase().contains(charSequence.toString().toLowerCase()))
                        {
                            newData.add(item);
                        }
                    }
                    filterResults.count=newData.size();
                    filterResults.values=newData;
                }
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                  data=new ArrayList<>();
                  ArrayList<Device> tmp=(ArrayList<Device>) filterResults.values;
                  for(Device item:tmp)
                  {
                      data.add(item);
                  }
                  notifyDataSetChanged();;
            }
        };
    }
}
