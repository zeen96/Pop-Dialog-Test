package com.personal.demo_popdialog;

import android.app.Dialog;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener{

    private Dialog mDialog;
    private View mDialogView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDialog = new Dialog(this,R.style.Theme_Light_Dialog);
        mDialogView = LayoutInflater.from(this).inflate(R.layout.dialog_my,null);

        ListView dialogList = (ListView) mDialogView.findViewById(R.id.lv_dialog);
        dialogList.setAdapter(new DialogAdapter());
        dialogList.setOnItemClickListener(this);
        //获得dialog的window窗口
        Window window = mDialog.getWindow();
        //设置dialog在屏幕底部
        window.setGravity(Gravity.BOTTOM);
        //设置dialog弹出时的动画效果，从屏幕底部向上弹出
        window.setWindowAnimations(R.style.dialogStyle);
        window.getDecorView().setPadding(0, 0, 0, 0);
        //获得window窗口的属性
        WindowManager.LayoutParams lp = window.getAttributes();
        //设置窗口宽度为充满全屏
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        //设置窗口高度为包裹内容
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        //将设置好的属性set回去
        window.setAttributes(lp);
        //将自定义布局加载到dialog上
        mDialog.setContentView(mDialogView);
    }

    public void click(View v) {
        mDialog.show();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch (position) {
            case 0:
                Toast.makeText(this,"详细信息",Toast.LENGTH_LONG).show();
                break;
            case 1:
                Toast.makeText(this,"删除",Toast.LENGTH_LONG).show();
                break;
        }
    }

    private class DialogAdapter extends BaseAdapter {
        private String[] item = {"详细信息", "删除"};
        private int[] icon = {R.mipmap.detail, R.mipmap.delete};

        @Override
        public int getCount() {
            return item.length;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view;
            if(convertView == null) {
                view = View.inflate(getApplicationContext(),R.layout.item_dialog,null);
            }else {
                view = convertView;
            }

            ImageView imageView = (ImageView) view.findViewById(R.id.iv_icon);
            TextView textView = (TextView) view.findViewById(R.id.tv_item);

            imageView.setImageBitmap(BitmapFactory.decodeResource(getResources(),icon[position]));
            textView.setText(item[position]);

            return view;
        }
    }

}
