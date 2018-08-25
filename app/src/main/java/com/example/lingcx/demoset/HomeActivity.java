package com.example.lingcx.demoset;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.CustomListener;
import com.bigkoo.pickerview.listener.OnOptionsSelectChangeListener;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.bigkoo.pickerview.view.TimePickerView;
import com.blankj.utilcode.util.ToastUtils;
import com.google.gson.Gson;
import com.idescout.sql.SqlScoutServer;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import cn.lingcx.demoset.activity.GreenDaoActivity;

/**
 * @author ling_cx
 * @version 1.0
 * @description
 * @date 2018/4/30 上午8:52
 * @copyright: 2018 www.kind.com.cn Inc. All rights reserved.
 */
public class HomeActivity extends AppCompatActivity implements View.OnClickListener {
    private TimePickerView  pvCustomTime;
    private OptionsPickerView pvOptions;
    private List<IntervalModel> options1Items = new ArrayList<>();
    private Gson gson = new Gson();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        SqlScoutServer.create(this, getPackageName());
        initPickerView();
        options1Items.add(new IntervalModel(0, "上午"));
        options1Items.add(new IntervalModel(1, "下午"));
        initOptionPicker();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_bd:
                Intent intentBd = new Intent(this,BaiduMapActivity.class);
                startActivity(intentBd);
                break;
            case R.id.btn_gd:
                Intent intentGd = new Intent(this,GaodeMapActivity.class);
                startActivity(intentGd);
                break;
            case R.id.btn_fz:
                Intent intentFz = new Intent(this,GroupActivity.class);
                startActivity(intentFz);
                break;
            case R.id.btn_greendao:
                Intent intentGreenDao = new Intent(this,GreenDaoActivity.class);
                startActivity(intentGreenDao);
                break;
            case R.id.btn_switch:
                Intent intentSwitch = new Intent(this,SwitchActivity.class);
                startActivity(intentSwitch);
                break;
            case R.id.btn_XhsEmoticonsKeyboard:
                Intent intentEmotionKeyboard = new Intent(this,EmoticonsKeyboardActivity.class);
                startActivity(intentEmotionKeyboard);
                break;
            case R.id.btn_notification:
                Intent intentNotification = new Intent(this,NotificationActivity.class);
                startActivity(intentNotification);
                break;
            case R.id.btn_coordinatorLayout:
                Intent intentCoordinatorLayout = new Intent(this,CoordinatorLayoutActivity.class);
                startActivity(intentCoordinatorLayout);
                break;
            case R.id.btn_webview:
                Intent intentWebView = new Intent(this,WebViewActivity.class);
                startActivity(intentWebView);
                break;
            case R.id.btn_zxing:
                Intent intentZxing = new Intent(this,ZxingActivity.class);
                startActivity(intentZxing);
                break;
            case R.id.btn_calendar:
                Intent intentCalendar = new Intent(this,CalendarViewActivity.class);
                startActivity(intentCalendar);
                break;
            case R.id.btn_picker:
                pvCustomTime.show();
                break;
            default:
                break;
        }
    }

    private void initPickerView(){
        /**
         * @description
         *
         * 注意事项：
         * 1.自定义布局中，id为 optionspicker 或者 timepicker 的布局以及其子控件必须要有，否则会报空指针.
         * 具体可参考demo 里面的两个自定义layout布局。
         * 2.因为系统Calendar的月份是从0-11的,所以如果是调用Calendar的set方法来设置时间,月份的范围也要是从0-11
         * setRangDate方法控制起始终止时间(如果不设置范围，则使用默认时间1900-2100年，此段代码可注释)
         */
        Calendar selectedDate = Calendar.getInstance();//系统当前时间
        Calendar startDate = Calendar.getInstance();
        startDate.set(2014, 1, 23);
        Calendar endDate = Calendar.getInstance();
        endDate.set(2027, 2, 28);
        //时间选择器 ，自定义布局
        pvCustomTime = new TimePickerBuilder(this, new OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {//选中事件回调
                ToastUtils.showShort(date.toString());
            }
        })
                /*.setType(TimePickerView.Type.ALL)//default is all
                .setCancelText("Cancel")
                .setSubmitText("Sure")
                .setContentTextSize(18)
                .setTitleSize(20)
                .setTitleText("Title")
                .setTitleColor(Color.BLACK)
               /*.setDividerColor(Color.WHITE)//设置分割线的颜色
                .setTextColorCenter(Color.LTGRAY)//设置选中项的颜色
                .setLineSpacingMultiplier(1.6f)//设置两横线之间的间隔倍数
                .setTitleBgColor(Color.DKGRAY)//标题背景颜色 Night mode
                .setBgColor(Color.BLACK)//滚轮背景颜色 Night mode
                .setSubmitColor(Color.WHITE)
                .setCancelColor(Color.WHITE)*/
                /*.animGravity(Gravity.RIGHT)// default is center*/
                .setDate(selectedDate)
                .setRangDate(startDate, endDate)
                .setLayoutRes(R.layout.pickerview_custom_time, new CustomListener() {

                    @Override
                    public void customLayout(View v) {
                        final TextView tvSubmit = (TextView) v.findViewById(R.id.tv_finish);
                        TextView ivCancel = (TextView) v.findViewById(R.id.iv_cancel);
                        tvSubmit.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                pvCustomTime.returnData();
                                pvCustomTime.dismiss();
                                pvOptions.show();
                            }
                        });
                        ivCancel.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                pvCustomTime.dismiss();
                            }
                        });
                    }
                })
                .setContentTextSize(18)
                .setType(new boolean[]{ true, true, true,false, false, false})
                .setLabel("年", "月", "日", "时", "分", "秒")
                .setLineSpacingMultiplier(1.2f)
                .setTextXOffset(0, 0, 0, 40, 0, -40)
                .isCenterLabel(false) //是否只显示中间选中项的label文字，false则每项item全部都带有label。
                .setDividerColor(0xFF24AD9D)
                .build();
    }

    private void initOptionPicker() {//条件选择器初始化

        /**
         * 注意 ：如果是三级联动的数据(省市区等)，请参照 JsonDataActivity 类里面的写法。
         */

        pvOptions = new OptionsPickerBuilder(this, new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {

            }
        })
                /*.setTitleText("城市选择")
                .setContentTextSize(20)//设置滚轮文字大小
                .setDividerColor(Color.LTGRAY)//设置分割线的颜色
                .setSelectOptions(0, 1)//默认选中项
                .setBgColor(Color.WHITE)
                .setTitleBgColor(Color.DKGRAY)
                .setTitleColor(Color.LTGRAY)
                .setCancelColor(Color.YELLOW)
                .setSubmitColor(Color.YELLOW)
                .setTextColorCenter(Color.LTGRAY)
                .isRestoreItem(true)//切换时是否还原，设置默认选中第一项。
                .isCenterLabel(false) //是否只显示中间选中项的label文字，false则每项item全部都带有label。
                //.setBackgroundId(0x00000000) //设置外部遮罩颜色*/
                .setOptionsSelectChangeListener(new OnOptionsSelectChangeListener() {
                    @Override
                    public void onOptionsSelectChanged(int options1, int options2, int options3) {
                        String str = "options1: " + options1 + "\noptions2: " + options2 + "\noptions3: " + options3;
                        Toast.makeText(HomeActivity.this, str, Toast.LENGTH_SHORT).show();
                    }
                })
                .build();

//        pvOptions.setSelectOptions(1,1);
        pvOptions.setPicker(options1Items);//一级选择器
        //pvOptions.setPicker(options1Items, options2Items);//二级选择器
        /*pvOptions.setPicker(options1Items, options2Items,options3Items);//三级选择器*/
    }
}
