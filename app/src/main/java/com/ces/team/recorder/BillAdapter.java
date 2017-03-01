package com.ces.team.recorder;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by Call Me Bear on 2017/2/28.
 */

public class BillAdapter extends BaseAdapter {
    private Context context;
    private Cursor cursor;
    private LinearLayout layout;

    public BillAdapter(Context context, Cursor cursor) {
        this.context = context;
        this.cursor = cursor;
    }

    @Override
    public int getCount() {
        return cursor.getCount();
    }

    @Override
    public Object getItem(int position) {
        return cursor.getPosition();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);
        layout = (LinearLayout) inflater.inflate(R.layout.content_list_bill, null);
        TextView tvType= (TextView) layout.findViewById(R.id.tv_bill_type);
        TextView tvValue= (TextView) layout.findViewById(R.id.tv_bill_value);
        TextView tvTime= (TextView) layout.findViewById(R.id.tv_bill_time);
        cursor.moveToPosition(position);
        String type=cursor.getString(cursor.getColumnIndex("type"));
        String bool=cursor.getString(cursor.getColumnIndex("bool"));
        String value=cursor.getString(cursor.getColumnIndex("value"));
        String time=cursor.getString(cursor.getColumnIndex("time_bill"));
        tvType.setText(type);
        String valueResult;
        if(bool.equals("1")){
            valueResult="+"+value;
            tvType.setBackgroundResource(R.drawable.shape_round_textview_green);
        }else {
            valueResult="-"+value;
            tvType.setBackgroundResource(R.drawable.shape_round_textview_red);
        }
        tvValue.setText(valueResult);
        tvTime.setText(time);
        return layout;
    }
}
