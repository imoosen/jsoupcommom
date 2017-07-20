package com.imoosen.jsouptest;

import java.io.BufferedReader;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import static sun.plugin.javascript.navig.JSType.Document;

/**
 * Created by [mengsen] on 2017/7/20 0020.
 *
 * @Description: [一句话描述该类的功能]
 * @UpdateUser: [mengsen] on 2017/7/20 0020.
 */

public class DownWebPageUtils {
    public static String getHtmlCode(String url, String encoding) {
        URL uri =null;
        URLConnection urlConnection =null;
        InputStream inputStream =null;
        InputStreamReader inputStreamReader = null;
        BufferedReader bReader =null;
        StringBuffer sBuffer= new StringBuffer();

        try {
            // 建立网络连接
            uri = new URL(url);
            // 打开连接
            urlConnection = uri.openConnection();
            //输入流
            inputStream = urlConnection.getInputStream();
            inputStreamReader = new InputStreamReader(inputStream, encoding);
            bReader = new BufferedReader(inputStreamReader);
            String temp;
            while ((temp = bReader.readLine()) != null) {
                sBuffer.append(temp + "\n");
            }

        } catch (Exception e) {
            e.printStackTrace();

        }finally {
            //关闭资源
            if(bReader!=null){
                try {
                    bReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return sBuffer.toString();
    }
    public static List<HashMap<String, String>> analyzeHtml(String url, String encoding){
        String htmlCode = getHtmlCode(url, encoding);
        Document document = Jsoup.parse(htmlCode);
        Elements elements = document.getElementsByClass("newlist");
        List<HashMap<String, String>> list=new ArrayList();
        for (Element e : elements) {
            HashMap<String, String> map = new HashMap();
            String zwmc = e.getElementsByClass("zwmc").text();
            String gsmc = e.getElementsByClass("gsmc").text();
            String zwyx = e.getElementsByClass("zwyx").text();
            String gzdd = e.getElementsByClass("gzdd").text();
            String gxsj = e.getElementsByClass("gxsj").text();
            map.put("职位名称:", zwmc);
            map.put("公司名称", gsmc);
            map.put("职位月薪", zwyx);
            map.put("工作地点", gzdd);
            map.put("发布日期", gxsj);
            list.add(map);
        }

        return list;
    }
    public static void main(String[] args) {
        List<HashMap<String, String>> resultList = analyzeHtml("http://sou.zhaopin.com/jobs/searchresult.ashx?jl=%E4%B8%8A%E6%B5%B7&kw=java&sm=0&p=1", "UTF-8");
        System.out.println(resultList);
    }
}
