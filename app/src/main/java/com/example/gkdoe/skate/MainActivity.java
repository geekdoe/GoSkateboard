package com.example.gkdoe.skate;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.support.v7.widget.PopupMenu;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends FragmentActivity implements View.OnClickListener, PopupMenu.OnMenuItemClickListener{

    private ViewPager mViewPager;
    private FragmentPagerAdapter mAdapter;
    private List<Fragment> mDatas;

    private TextView mMessageTextView;
    private TextView mDiscoveryTextView;
    private TextView mMeTextView;

    private ImageView mTabline;//当前碎片标签下占宽度3分之1的蓝线
    private int mScreen1_3;//3分之1
    private int mCurrentPageIndex;
    private ImageView imageViewMore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initTabLine();
        initView();

        imageViewMore = (ImageView) findViewById(R.id.actionbar_more_icon);
        imageViewMore.setOnClickListener(this);
        /*Button button2 = (Button) findViewById(R.id.button2);
        Button button3 = (Button) findViewById(R.id.button3);

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Main.this, Article.class);
                startActivity(intent);
            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Main.this, Article.class);
                startActivity(intent);
            }
        });*/

    }

    private  void  initTabLine(){
        //蓝线占宽3分之1
        mTabline = (ImageView) findViewById(R.id.id_iv_tabline);
        Display display = getWindow().getWindowManager().getDefaultDisplay();
        DisplayMetrics outMetrics = new DisplayMetrics();
        display.getMetrics(outMetrics);
        mScreen1_3 = outMetrics.widthPixels/3;
        ViewGroup.LayoutParams lp = mTabline.getLayoutParams();
        lp.width = mScreen1_3;
        mTabline.setLayoutParams(lp);
    }

    private void initView() {
        mViewPager = (ViewPager) findViewById(R.id.id_viewpager);
        mMessageTextView = (TextView) findViewById(R.id.id_tv_message);
        mDiscoveryTextView = (TextView) findViewById(R.id.id_tv_discovery);
        mMeTextView = (TextView) findViewById(R.id.id_tv_me);

        mDatas = new ArrayList<Fragment>();

        MeMainTabFragment tab01 = new MeMainTabFragment();
        DiscoveryMainTabFragment tab02 = new DiscoveryMainTabFragment();
        MessageMainTabFragment tab03 = new MessageMainTabFragment();

        mDatas.add(tab01);
        mDatas.add(tab02);
        mDatas.add(tab03);

        mAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public int getCount() {
                return mDatas.size();
            }

            @Override
            public Fragment getItem(int arg0) {
                return mDatas.get(arg0);
            }
        };
        mViewPager.setAdapter(mAdapter);

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int position) {
                resetTextView();
                switch (position){
                    case 0:
                        mMeTextView.setTextColor(Color.parseColor("#008000"));
                        break;
                    case 1:
                        mDiscoveryTextView.setTextColor(Color.parseColor("#008000"));
                        break;
                    case 2:
                        mMessageTextView.setTextColor(Color.parseColor("#008000"));
                        break;
                }
                mCurrentPageIndex = position;
            }

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPx) {
                LinearLayout.LayoutParams lp = (android.widget.LinearLayout.LayoutParams) mTabline.getLayoutParams();
                if (mCurrentPageIndex == 0 && position == 0) {
                    lp.leftMargin = (int) (positionOffset * mScreen1_3 + mCurrentPageIndex * mScreen1_3);
                } // 0->1
                else if (mCurrentPageIndex == 1 && position == 0) {
                    lp.leftMargin = (int) (mCurrentPageIndex * mScreen1_3 + (positionOffset - 1) * mScreen1_3);
                } // 1->0
                else if (mCurrentPageIndex == 1 && position == 1) {
                    lp.leftMargin = (int) (mCurrentPageIndex * mScreen1_3 + positionOffset * mScreen1_3);
                } // 1->2
                else if (mCurrentPageIndex == 2 && position == 1) {
                    lp.leftMargin = (int) (mCurrentPageIndex * mScreen1_3 + (positionOffset - 1)* mScreen1_3);
                } // 2->1
                mTabline.setLayoutParams(lp);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    protected void resetTextView(){
        mMessageTextView.setTextColor(Color.BLACK);
        mDiscoveryTextView.setTextColor(Color.BLACK);
        mMeTextView.setTextColor(Color.BLACK);
    }

    //点击按钮后，加载弹出式菜单
    @Override
    public void onClick(View v) {
        //创建弹出式菜单对象（最低版本11）
        PopupMenu popup = new PopupMenu(this, v);//第二个参数是绑定的那个view
        //获取菜单填充器
        MenuInflater inflater = popup.getMenuInflater();
        //填充菜单
        inflater.inflate(R.menu.more, popup.getMenu());
        //绑定菜单项的点击事件
        popup.setOnMenuItemClickListener(this);
        popup.show(); //这一行代码不要忘记了
    }

    //弹出式菜单的单击事件处理
    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.setting:
                Toast.makeText(this, "设置", Toast.LENGTH_SHORT).show();
                Intent intentSetting = new Intent();
                intentSetting.setClass(MainActivity.this, SettingActivity.class);
                startActivity(intentSetting);
                break;
            case R.id.about_us:
                Toast.makeText(this, "关于我们", Toast.LENGTH_SHORT).show();
                /* 新建一个Intent对象 */
                Intent intentAboutUs = new Intent();
                /* 指定intent要启动的类 */
                intentAboutUs.setClass(MainActivity.this, AboutUsActivity.class);
                /* 启动一个新的Activity */
                startActivity(intentAboutUs);
                /* 关闭当前的Activity */
                //Activity02.this.finish();
                break;
            default:
                break;
        }
        return false;
    }
}
