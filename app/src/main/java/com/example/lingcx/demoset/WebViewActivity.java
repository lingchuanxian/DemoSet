package com.example.lingcx.demoset;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebSettings;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author ling_cx
 * @version 1.0
 * @description
 * @date 2018/7/15 上午11:17
 * @copyright: 2018 www.kind.com.cn Inc. All rights reserved.
 */
public class WebViewActivity extends AppCompatActivity {
    @BindView(R.id.wv_content)
    NoScrollWebView mWvContent;
    private String content = "<div class='post_text' id='endText' style='border-top:1px solid #ddd;'><p class='f_center'><img alt='网友拍婚纱照周杰伦入镜' src='http://cms-bucket.nosdn.127.net/catchpic/8/8f/8fb05cb1c52db80260e44df3908a7682.jpg?imageView&thumbnail=550x0' style='border-width: 0px; border-style: none; border-; vertical-align: middle; display: block; margin: 0px auto; max-width: 640px;' />网友拍婚纱照周杰伦入镜</p><p class='f_center'><img alt='网友拍婚纱照周杰伦入镜' src='http://cms-bucket.nosdn.127.net/catchpic/6/6d/6da3475ff1ee5d8216cbe3003848997f.jpg?imageView&thumbnail=550x0' style='border-width: 0px; border-style: none; border-; vertical-align: middle; display: block; margin: 0px auto; max-width: 640px;' />网友拍婚纱照周杰伦入镜</p><p><!-- 画中画02 -->\n<div class='gg200x300'>\n<div class='at_item right_ad_item' adType='rightAd' requestUrl='https://nex.163.com/q?app=7BE0FC82&c=ent&l=133&site=netease&affiliate=ent&cat=article&type=logo300x250&location=12'></div>\n<a href='javascript:;' target='_self' class='ad_hover_href'></a>\n</div><p><b>网易娱乐7月15日报道</b>&nbsp;据台湾媒体报道，周杰伦和老婆昆凌婚后育有一子一女，一家四口生活幸福，夫妻俩的一举一动也备受各界瞩目。近日，周杰伦粉丝团就分享有网友远赴美国拍摄婚纱，选在纽约中央车站取景的照片，却发现背景中的路人竟是周杰伦、昆凌，还有小周周，意外捕捉到一家三口，也引起粉丝热烈讨论。</p><p>周杰伦官方粉丝团14日PO出一名网友赴美国拍摄的婚纱照，只见照片中，这位新娘站在纽约的中央车站大厅，对着镜头摆出妩媚的姿势。但仔细一看，身后的路人却来头不小，竟然就是周杰伦和老婆昆凌，还有二人的爱女小周周也意外曝光。</p><p>而根据周杰伦和昆凌近日在纽约晒出的照片，二人的穿着都和婚纱照背景中相同，让人更加确定就是夫妻俩，也让粉丝纷纷留言：“太幸运了吧”、“这婚纱照值钱了”、“拍婚纱照顺便追星”、“无比羡慕”。ETtoday/文</p><p></p>";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);
        ButterKnife.bind(this);
        initView();
        initData();
    }

    private void initData() {
        //content.replace("<img", "<img height='250px'; width='100px'");
        mWvContent.loadDataWithBaseURL(null, getNewContent(content),"text/html", "utf-8", null);
    }

    private void initView() {
        //获取webview设置属性
        WebSettings webSettings = mWvContent.getSettings();
        //把html中的内容放大webview等宽的一列中
        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        //支持js
        webSettings.setJavaScriptEnabled(true);
        // 显示放大缩小
        webSettings.setBuiltInZoomControls(true);
        // 可以缩放
        webSettings.setSupportZoom(true);
    }

    /**
     * 将html文本内容中包含img标签的图片，宽度变为屏幕宽度，高度根据宽度比例自适应
     **/
    public static String getNewContent(String htmltext){
        try {
            Document doc= Jsoup.parse(htmltext);
            Elements elements=doc.getElementsByTag("img");
            for (Element element : elements) {
                element.attr("width","100%").attr("height","auto");
            }

            return doc.toString();
        } catch (Exception e) {
            return htmltext;
        }
    }
}
