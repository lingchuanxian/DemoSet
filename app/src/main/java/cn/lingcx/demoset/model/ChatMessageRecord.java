package cn.lingcx.demoset.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

import java.util.Date;

/**
 * @author ling_cx
 * @version 1.0
 * @description
 * @date 2018/5/24 下午7:05
 * @copyright: 2018 www.kind.com.cn Inc. All rights reserved.
 */
@Entity
public class ChatMessageRecord {
    @Id(autoincrement = true)
    private Long id;
    private String speakerId;
    private String audienceId;
    private Date occureTime;
    private String content;
    private int isGroupChat;
    private int test;
    @Generated(hash = 552197992)
    public ChatMessageRecord(Long id, String speakerId, String audienceId, Date occureTime, String content, int isGroupChat,
            int test) {
        this.id = id;
        this.speakerId = speakerId;
        this.audienceId = audienceId;
        this.occureTime = occureTime;
        this.content = content;
        this.isGroupChat = isGroupChat;
        this.test = test;
    }

    public ChatMessageRecord(String speakerId, String audienceId, Date occureTime, String content, int isGroupChat) {
        this.speakerId = speakerId;
        this.audienceId = audienceId;
        this.occureTime = occureTime;
        this.content = content;
        this.isGroupChat = isGroupChat;
    }

    public ChatMessageRecord(String speakerId, String audienceId, Date occureTime, String content, int isGroupChat, int test) {
        this.speakerId = speakerId;
        this.audienceId = audienceId;
        this.occureTime = occureTime;
        this.content = content;
        this.isGroupChat = isGroupChat;
        this.test = test;
    }

    @Generated(hash = 8122656)
    public ChatMessageRecord() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getSpeakerId() {
        return this.speakerId;
    }
    public void setSpeakerId(String speakerId) {
        this.speakerId = speakerId;
    }
    public String getAudienceId() {
        return this.audienceId;
    }
    public void setAudienceId(String audienceId) {
        this.audienceId = audienceId;
    }
    public Date getOccureTime() {
        return this.occureTime;
    }
    public void setOccureTime(Date occureTime) {
        this.occureTime = occureTime;
    }
    public String getContent() {
        return this.content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public int getIsGroupChat() {
        return this.isGroupChat;
    }
    public void setIsGroupChat(int isGroupChat) {
        this.isGroupChat = isGroupChat;
    }

    @Override
    public String toString() {
        return "ChatMessageRecord{" +
                "id=" + id +
                ", speakerId='" + speakerId + '\'' +
                ", audienceId='" + audienceId + '\'' +
                ", occureTime=" + occureTime +
                ", content='" + content + '\'' +
                ", isGroupChat=" + isGroupChat +
                ", test=" + test +
                '}';
    }

    public int getTest() {
        return this.test;
    }

    public void setTest(int test) {
        this.test = test;
    }
}
