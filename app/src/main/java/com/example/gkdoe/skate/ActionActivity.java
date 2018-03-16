package com.example.gkdoe.skate;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;

public class ActionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_action);
        //绑定Layout里面的ListView
        ListView list = (ListView) findViewById(R.id.list_View);

        //生成动态数组，加入数据
        ArrayList<HashMap<String, Object>> listItem = new ArrayList<HashMap<String, Object>>();
        for(int i=0;i<7;i++) {
            if(i==0){
                HashMap<String, Object> map = new HashMap<String, Object>();
                //map.put("ItemImage", R.drawable.checked);//图像资源的ID
                map.put("ItemTitle", "Ollie");
                //map.put("LastImage", R.drawable.lastimage);
                listItem.add(map);
            }else if(i==1){
                HashMap<String, Object> map = new HashMap<String, Object>();
                //map.put("ItemImage", R.drawable.c);//图像资源的ID
                map.put("ItemTitle", "Kickflip");
                //map.put("LastImage", R.drawable.lastimage);
                listItem.add(map);
            }else if(i==2){
                HashMap<String, Object> map = new HashMap<String, Object>();
                // map.put("ItemImage", R.drawable.d);//图像资源的ID
                map.put("ItemTitle", "Power Slide");
                //map.put("LastImage", R.drawable.lastimage);
                listItem.add(map);
            }else if(i==3){
                HashMap<String, Object> map = new HashMap<String, Object>();
                //map.put("ItemImage", R.drawable.d);//图像资源的ID
                map.put("ItemTitle", "Shove it");
                //map.put("LastImage", R.drawable.lastimage);
                listItem.add(map);
            }else if(i==4){
                HashMap<String, Object> map = new HashMap<String, Object>();
                map.put("ItemTitle", "Wall Ride");
                listItem.add(map);
            }else if(i==5){
                HashMap<String, Object> map = new HashMap<String, Object>();
                //map.put("ItemImage", R.drawable.e);//图像资源的ID
                map.put("ItemTitle", "Manual");
                //map.put("LastImage", R.drawable.lastimage);
                listItem.add(map);
            }else if(i==6){
                HashMap<String, Object> map = new HashMap<String, Object>();
                //map.put("ItemImage", R.drawable.e);//图像资源的ID
                map.put("ItemTitle", "返回");
                //map.put("LastImage", R.drawable.lastimage);
                listItem.add(map);
            }

        }
        //生成适配器的Item和动态数组对应的元素
        SimpleAdapter listItemAdapter = new SimpleAdapter(this,listItem,// 数据源
                R.layout.action_items,//ListItem的XML实现
                //动态数组与ImageItem对应的子项
                //new String[] {"ItemImage","ItemTitle", "LastImage"},
                new String[] {"ItemTitle"},
                //ImageItem的XML文件里面的一个ImageView,两个TextView ID
                //new int[] {R.id.ItemImage,R.id.ItemTitle,R.id.last}
                new int[] {R.id.ItemTitle}
        );

        //添加并且显示
        list.setAdapter(listItemAdapter);

        //添加点击
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                    long arg3) {
                setTitle("点击第"+arg2+"个项目");
                if(arg2 == 6){
                    ActionActivity.this.finish();
                }
                else{
                    dialog();
                }
            }
        });

        //添加长按点击
        list.setOnCreateContextMenuListener(new View.OnCreateContextMenuListener() {

            @Override
            public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
                menu.setHeaderTitle("状态");
                menu.add(0, 0, 0, "备注");
                menu.add(0, 1, 0, "删除");
            }

        });
    }

    //长按菜单响应函数
    /*@Override
    public boolean onContextItemSelected(MenuItem item) {
        setTitle("点击了长按菜单里面的第"+item.getItemId()+"个项目");
        return super.onContextItemSelected(item);
    }*/

    protected void dialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(ActionActivity.this);
        builder.setMessage("新技能Get!");
        builder.setTitle("状态");
        builder.setPositiveButton("学习", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.create().show();
    }
}