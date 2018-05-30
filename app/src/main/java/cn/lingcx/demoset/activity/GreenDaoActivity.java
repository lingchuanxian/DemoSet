package cn.lingcx.demoset.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lingcx.demoset.R;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.lingcx.demoset.db.ChatMessageRecordDaoOpen;
import cn.lingcx.demoset.model.ChatMessageRecord;

/**
 * @author ling_cx
 * @version 1.0
 * @description
 * @date 2018/5/24 下午7:20
 * @copyright: 2018 www.kind.com.cn Inc. All rights reserved.
 */
public class GreenDaoActivity extends AppCompatActivity {
    @BindView(R.id.button)
    Button mButton;
    @BindView(R.id.button2)
    Button mButton2;
    @BindView(R.id.button3)
    Button mButton3;
    @BindView(R.id.button4)
    Button mButton4;
    @BindView(R.id.btn_query_all)
    Button mBtnQueryAll;
    @BindView(R.id.button5)
    Button mButton5;
    @BindView(R.id.tv_content)
    TextView mTvContent;

    private List<ChatMessageRecord> mRecordList = new ArrayList<>();

    private int page;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_record);
        ButterKnife.bind(this);
        initView();
        initData();
    }

    private void initView() {

    }

    /**
     * 初始化数据
     */
    private void initData() {
        for (int i = 0; i < 100; i++) {
            ChatMessageRecord record = new ChatMessageRecord("lingcx","sqfang",new Date(),"content"+i,0,i);
            mRecordList.add(record);
        }
    }

    @OnClick({R.id.button,R.id.button1, R.id.button2, R.id.button3, R.id.button4, R.id.button5, R.id.btn_query_all,R.id.btn_query_condition})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.button:
                ChatMessageRecord recordss = new ChatMessageRecord("cfn","cqq",new Date(),"content",0,1);
                ChatMessageRecordDaoOpen.insertData(this, recordss);
                break;
            case R.id.button1:
                ChatMessageRecord recordsss = new ChatMessageRecord("cqq","cfn",new Date(),"content",0,1);
                ChatMessageRecordDaoOpen.insertData(this, recordsss);
                break;
            case R.id.button2:
                ChatMessageRecord record = new ChatMessageRecord("lingcx","sqfang",new Date(),"content"+1,0);
                /**
                 * 根据特定的对象删除
                 */
                ChatMessageRecordDaoOpen.deleteData(this, record);
                /**
                 * 根据主键删除
                 */
                ChatMessageRecordDaoOpen.deleteByKeyData(this, 7);
                ChatMessageRecordDaoOpen.deleteAllData(this);
                break;
            case R.id.button3:
                ChatMessageRecord updateRecord = new ChatMessageRecord("sqfang","lingcx",new Date(),"content"+1,0);
                ChatMessageRecordDaoOpen.updateData(this, updateRecord);
                break;
            case R.id.button4:
                List<ChatMessageRecord> records = ChatMessageRecordDaoOpen.queryAll(this);
                mTvContent.setText(records.toString());
                for (int i = 0; i < records.size(); i++) {
                    Log.i("Log", records.get(i).getContent());
                }

                break;
            case R.id.button5:
                ChatMessageRecordDaoOpen.deleteAllData(this);
                break;
            case R.id.btn_query_all:
                List<ChatMessageRecord> students2 = ChatMessageRecordDaoOpen.queryPaging(page, 2, this);

                if (students2.size() == 0) {
                    Toast.makeText(this, "没有更多数据了", Toast.LENGTH_SHORT).show();
                }
                for (ChatMessageRecord st : students2) {
                    Log.e("TAG", "onViewClicked: ==" + st);
                    Log.e("TAG", "onViewClicked: == num = " + st.getAudienceId());
                }
                page++;
                mTvContent.setText(students2.toString());
                break;
            case R.id.btn_query_condition:
                List<ChatMessageRecord> students3 = ChatMessageRecordDaoOpen.queryCondition("lingcx","sqfang",this);

                mTvContent.setText(students3.toString());
                break;
            default:
                    break;
        }
    }
}
