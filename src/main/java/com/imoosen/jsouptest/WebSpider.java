package com.imoosen.jsouptest;

/**
 * Created by [mengsen] on 2017/7/20 0020.
 *
 * @Description: [一句话描述该类的功能]
 * @UpdateUser: [mengsen] on 2017/7/20 0020.
 */
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WebSpider {
    public static void main(String[] args) {
        URL url = null;
        URLConnection urlconn = null;
        BufferedReader br = null;
        PrintWriter pw = null;
        String regex = "http://[\\w+\\.?/?]+\\.[A-Za-z]+";
        Pattern p = Pattern.compile(regex);
        try {
            url = new URL("http://51jrq.com/");
            urlconn = url.openConnection();
           // pw = new PrintWriter(new FileWriter("e:/url.txt"), true);//这里我们把收集到的链接存储在了E盘底下的一个叫做url的txt文件中
            br = new BufferedReader(new InputStreamReader(
                    urlconn.getInputStream()));
            String buf = null;
            while ((buf = br.readLine()) != null) {
                Matcher buf_m = p.matcher(buf);
                while (buf_m.find()) {
                    //pw.println(buf_m.group());
                    System.out.println(buf_m.group());
                }
            }
            System.out.println("获取成功！");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            pw.close();
        }
    }
}
