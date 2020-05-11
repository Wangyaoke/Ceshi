package com.ansiyida.cgjl.util;

import com.andview.refreshview.utils.Utils;
import com.ansiyida.cgjl.bean.WXPayBean;

import java.security.MessageDigest;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

import okhttp3.internal.http.HttpMethod;
import retrofit2.HttpException;

public class WXUtil {
    /**
     * 获取sign签名
     *
     * @return
     */
    public static void genPayReq( WXPayBean request ) {
       // PayBean pb = new PayBean();
        request.setAppid("wx8855ca7656c29232");// appid;
        request.setBody("weixing");
        request.setMach_id("1525551981");// 商户id
        request.setNonce_str(DeviceUtils.gennocestr());// 随机字符串
        request.setNotify_url("http://www.peita.net");
        request.setOut_trade_no("20150806125348");// 订单编号
        request.setTotal_fee(1);// 总金额
        request.setTrade_type("APP");// 支付类型
        request.setSpbill_create_ip(DeviceUtils.getLocalIPAddress() );// ip


        //微信签名需要（https://pay.weixin.qq.com/wiki/doc/api/app/app.php?        chapter=4_3）
        SortedMap<Object, Object> param = new TreeMap<Object, Object>();// 可以根据键升序排列
        param.put("appid",request.getappid());
        param.put("body",request.getbody());// 商品描述
        param.put("mch_id",request.getmch_id());
        param.put("nonce_str",request.getnonce_str());//随机字符串
        param.put("notify_url",request.getnotify_url());// 这个url必须能直接访问
        param.put("out_trade_no",request.getput_trade_no());// 不能重复
        param.put("total_fee",request.gettotal_fee());// 商品总金额
        param.put("trade_type",request.gettrade_type());// 交易类型
        param.put("spbill_create_ip",request.getspbill_create_ip());// 用户实际ip
        // 调用获取微信签名的方法，
        String  sign = CreateSign("UTF-8", param, "552f6344d6b424a35dfae9ee94b0fdac");
        param.put("sign", sign);
      xmlString(param);
        // 进行网络请求，我使用的是xutils
       // goRequestion(xmlStr);
    }
    public static void xmlString (SortedMap<Object, Object> param){
        StringBuilder sb = new StringBuilder();
        sb.append("<xml>");
        Set es = param.entrySet();
        Iterator it = es.iterator();
        while (it.hasNext()){

            Map.Entry entry = (Map.Entry) it.next();
            String k = (String) entry.getKey();
            Object v = entry.getValue();
            sb.append("<").append(k).append(">");
            sb.append(v);
            sb.append("</").append(k).append(">");
        }
        sb.append("</xml>");
try
{
    new GetPrepayId(new String(sb.toString().getBytes(),"ISO8859-1")).execute();
}
catch (Exception e)
{
    e.printStackTrace();
}
       // return sb.toString();
    }

    public static  void goRequestion(String str){

    }

    /**
     * 微信支付签名算法sign
     * @param characterEncoding
     * @param parameters
     * @return
     */
    public static  String CreateSign(String charactEncoding, SortedMap<Object, Object> param, String key){
        StringBuilder sb = new StringBuilder();
        Set es = param.entrySet();
        Iterator it = es.iterator();
        while(it.hasNext()) {
            Map.Entry entry = (Map.Entry)it.next();
            String k = (String)entry.getKey();
            Object v = entry.getValue();
            if(null != v && !"".equals(v)
                    && !"sign".equals(k) && !"key".equals(k)) {
                sb.append(k + "=" + v + "&");
            }
        }
        sb.append("key="+key);

        String sign = MD5Encode(sb.toString(), charactEncoding).toUpperCase();

        return sign;
    }


    private static String byteArrayToHexString(byte b[]) {
        StringBuffer resultSb = new StringBuffer();
        for (int i = 0; i < b.length; i++)
            resultSb.append(byteToHexString(b[i]));

        return resultSb.toString();
    }

    private static String byteToHexString(byte b) {
        int n = b;
        if (n < 0)
            n += 256;
        int d1 = n / 16;
        int d2 = n % 16;
        return hexDigits[d1] + hexDigits[d2];
    }

    private static String MD5Encode(String origin, String charsetname) {
        String resultString = null;
        try {
            resultString = new String(origin);
            MessageDigest md = MessageDigest.getInstance("MD5");
            if (charsetname == null || "".equals(charsetname))
                resultString = byteArrayToHexString(md.digest(resultString
                        .getBytes()));
            else
                resultString = byteArrayToHexString(md.digest(resultString
                        .getBytes(charsetname)));
        } catch (Exception exception) {
        }
        return resultString;
    }

    private static final String hexDigits[] = { "0", "1", "2", "3", "4", "5",
            "6", "7", "8", "9", "a", "b", "c", "d", "e", "f" };
}
