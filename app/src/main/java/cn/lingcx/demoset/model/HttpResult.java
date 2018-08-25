package cn.lingcx.demoset.model;
import java.io.Serializable;

/**
 * @author ling_cx
 * @version 1.0
 * @description Api返回结果的格式统一封装
 * @date 2018/8/16 下午10:00
 * @copyright: 2018 www.kind.com.cn Inc. All rights reserved.
 */
public class HttpResult<T> implements Serializable {
    private int ReturnCode;
    private String Description;
    private T DetailInfo;

    public int getReturnCode() {
        return ReturnCode;
    }

    public void setReturnCode(int returnCode) {
        ReturnCode = returnCode;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public T getDetailInfo() {
        return DetailInfo;
    }

    public void setDetailInfo(T detailInfo) {
        DetailInfo = detailInfo;
    }

    @Override
    public String toString() {
        return "HttpResult{" +
                "ReturnCode=" + ReturnCode +
                ", Description='" + Description + '\'' +
                ", DetailInfo=" + DetailInfo +
                '}';
    }
}
