package cn.lingcx.demoset.db;

import android.content.Context;

import org.greenrobot.greendao.query.QueryBuilder;
import org.greenrobot.greendao.query.WhereCondition;

import java.util.List;

import cn.lingcx.demoset.dao.ChatMessageRecordDao;
import cn.lingcx.demoset.model.ChatMessageRecord;

/**
 * @author ling_cx
 * @version 1.0
 * @description
 * @date 2018/5/24 下午7:16
 * @copyright: 2018 www.kind.com.cn Inc. All rights reserved.
 */
public class ChatMessageRecordDaoOpen {
    /**
     * 添加数据至数据库
     *
     * @param context
     * @param record
     */
    public static void insertData(Context context, ChatMessageRecord record) {
        DbManager.getDaoSession(context).getChatMessageRecordDao().insert(record);
    }


    /**
     * 将数据实体通过事务添加至数据库
     *
     * @param context
     * @param list
     */
    public static void insertData(Context context, List<ChatMessageRecord> list) {
        if (null == list || list.size() <= 0) {
            return;
        }
        DbManager.getDaoSession(context).getChatMessageRecordDao().insertInTx(list);
    }

    /**
     * 添加数据至数据库，如果存在，将原来的数据覆盖
     * 内部代码判断了如果存在就update(entity);不存在就insert(entity)；
     *
     * @param context
     * @param student
     */
    public static void saveData(Context context, ChatMessageRecord student) {
        DbManager.getDaoSession(context).getChatMessageRecordDao().save(student);
    }

    /**
     * 删除数据至数据库
     *
     * @param context
     * @param student 删除具体内容
     */
    public static void deleteData(Context context, ChatMessageRecord student) {
        DbManager.getDaoSession(context).getChatMessageRecordDao().delete(student);
    }

    /**
     * 根据id删除数据至数据库
     *
     * @param context
     * @param id      删除具体内容
     */
    public static void deleteByKeyData(Context context, long id) {
        DbManager.getDaoSession(context).getChatMessageRecordDao().deleteByKey(id);
    }

    /**
     * 删除全部数据
     *
     * @param context
     */
    public static void deleteAllData(Context context) {
        DbManager.getDaoSession(context).getChatMessageRecordDao().deleteAll();
    }

    /**
     * 更新数据库
     *
     * @param context
     * @param record
     */
    public static void updateData(Context context, ChatMessageRecord record) {
        DbManager.getDaoSession(context).getChatMessageRecordDao().update(record);
    }


    /**
     * 查询所有数据
     *
     * @param context
     * @return
     */
    public static List<ChatMessageRecord> queryAll(Context context) {
        QueryBuilder<ChatMessageRecord> builder = DbManager.getDaoSession(context).getChatMessageRecordDao().queryBuilder();

        return builder.build().list();
    }

    /**
     *  分页加载
     * @param context
     * @param pageSize 当前第几页(程序中动态修改pageSize的值即可)
     * @param pageNum  每页显示多少个
     * @return
     */
    public static List<ChatMessageRecord> queryPaging( int pageSize, int pageNum,Context context){
        ChatMessageRecordDao ChatMessageRecordDao = DbManager.getDaoSession(context).getChatMessageRecordDao();
        List<ChatMessageRecord> listMsg = ChatMessageRecordDao.queryBuilder()
                .offset(pageSize * pageNum).limit(pageNum).list();
        return listMsg;
    }

    public static List<ChatMessageRecord> queryCondition( String speakId,String audienceId, Context context){
        ChatMessageRecordDao ChatMessageRecordDao = DbManager.getDaoSession(context).getChatMessageRecordDao();
        QueryBuilder<ChatMessageRecord> builder = ChatMessageRecordDao.queryBuilder();
        WhereCondition condition1 = builder.and(cn.lingcx.demoset.dao.ChatMessageRecordDao.Properties.SpeakerId.eq(speakId), cn.lingcx.demoset.dao.ChatMessageRecordDao.Properties.AudienceId.eq(audienceId));
        WhereCondition condition2 = builder.and(cn.lingcx.demoset.dao.ChatMessageRecordDao.Properties.SpeakerId.eq(audienceId), cn.lingcx.demoset.dao.ChatMessageRecordDao.Properties.AudienceId.eq(speakId));
        WhereCondition condition3 = builder.or(condition1,condition2);
        List<ChatMessageRecord> listMsg = builder
                .where(condition3)
                .list();
        return listMsg;
    }
}
