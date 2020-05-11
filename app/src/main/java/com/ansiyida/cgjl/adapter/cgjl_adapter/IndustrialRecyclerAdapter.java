package com.ansiyida.cgjl.adapter.cgjl_adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.TextView;

import com.ansiyida.cgjl.R;
import com.ansiyida.cgjl.activity.cgjl_activity.PolicyNewsDetailActivity;
import com.ansiyida.cgjl.bean.cgjl_bean.IndustrialParkDetailBean;
import com.ansiyida.cgjl.bean.cgjl_bean.ParkDetailsListContentBean;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class IndustrialRecyclerAdapter extends RecyclerView.Adapter {

    private String mode = "";
    private Context context;
    private List<IndustrialParkDetailBean.DataBean.ListDetailsBean> listDetailsBeans;
    private List<IndustrialParkDetailBean.DataBean.TextDetailsBean> TextDetailsBean;
    private List<ParkDetailsListContentBean.DataBean.ListBean> ParkDetailList;
    private String[] keywordSplit = new String[]{};

    public IndustrialRecyclerAdapter(Context context) {
        this.context = context;
    }

    public void setListDetailsBeans(List<IndustrialParkDetailBean.DataBean.ListDetailsBean> listDetailsBeans) {
        this.listDetailsBeans = listDetailsBeans;
        mode = "ZCXW";
        notifyDataSetChanged();
    }

    public void setTextDetailsBean(List<IndustrialParkDetailBean.DataBean.TextDetailsBean> TextDetailsBean) {
        this.TextDetailsBean = TextDetailsBean;
        mode = "TextDetail";
        notifyDataSetChanged();
    }

    public void setParkDetailList(List<ParkDetailsListContentBean.DataBean.ListBean> ParkDetailList) {
        this.ParkDetailList = ParkDetailList;
        mode = "parkDetail";
        notifyDataSetChanged();
    }

    public void setKeywordSplit(String[] keywordSplit) {
        this.keywordSplit = keywordSplit;
        mode = "keyword";
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == 0) {
            return new ZCXW_ViewHolder(View.inflate(context, R.layout.industrial_xwdt_layout, null));
        } else if (viewType == 1) {
            return new TextDetail_ViewHolder(View.inflate(context, R.layout.industrial_textdetail_layout, null));
        } else if (viewType == 2) {
            return new ParkDetail_ViewHolder(View.inflate(context, R.layout.policynewsdeatail_recycler_layout, null));
        } else if (viewType == 3) {
            return new KeyWord_ViewHolder(View.inflate(context, R.layout.policynewsdeatail_keyword_layout, null));
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        if (holder instanceof ZCXW_ViewHolder) {
            //政策新闻
            final String title = listDetailsBeans.get(position).getTitle();
            final int id = listDetailsBeans.get(position).getId();
            ((ZCXW_ViewHolder) holder).xwdtTitle.setText(title);
            ((ZCXW_ViewHolder) holder).xwdtTitle.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, PolicyNewsDetailActivity.class);
                    intent.putExtra("Title", title);
                    intent.putExtra("Id", id);
                    context.startActivity(intent);
                }
            });
        } else if (holder instanceof TextDetail_ViewHolder) {
            //产业园详情内容展示
            ((TextDetail_ViewHolder) holder).Title.setText(TextDetailsBean.get(position).getName());
            setWebview(((TextDetail_ViewHolder) holder).webview);
            ((TextDetail_ViewHolder) holder).webview.loadDataWithBaseURL(null, getHtmlP(TextDetailsBean.get(position).getContent()), "text/html", "utf-8", null);
            ((TextDetail_ViewHolder) holder).webview.setFocusable(false);
        } else if (holder instanceof ParkDetail_ViewHolder) {
            setWebview(((ParkDetail_ViewHolder) holder).webview);
            ((ParkDetail_ViewHolder) holder).webview.loadDataWithBaseURL(null, getHtml(ParkDetailList.get(position).getContent()), "text/html", "utf-8", null);
            ((ParkDetail_ViewHolder) holder).recyclerName.setText(ParkDetailList.get(position).getTitle());

        } else if(holder instanceof KeyWord_ViewHolder){
            ((KeyWord_ViewHolder) holder).recyclerShopKeywordOne.setText(keywordSplit[position]);
            if(position == keywordSplit.length-1){
                ((KeyWord_ViewHolder) holder).verticalView.setVisibility(View.GONE);
            }
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (mode.equals("ZCXW")) {
            return 0;
        } else if (mode.equals("TextDetail")) {
            return 1;
        } else if (mode.equals("parkDetail")) {
            return 2;
        } else if (mode.equals("keyword")) {
            return 3;
        }
        return -1;
    }

    @Override
    public int getItemCount() {
        if (mode.equals("ZCXW")) {
            return listDetailsBeans.size();
        } else if (mode.equals("TextDetail")) {
            return TextDetailsBean.size();
        } else if (mode.equals("parkDetail")) {
            return ParkDetailList.size();
        } else if (mode.equals("keyword")) {
            return keywordSplit.length;
        }
        return 0;
    }

    class TextDetail_ViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.ZCYH_title)
        TextView Title;
        @Bind(R.id.webview)
        WebView webview;

        public TextDetail_ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    class ZCXW_ViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.xwdt_title)
        TextView xwdtTitle;

        public ZCXW_ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    class ParkDetail_ViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.recycler_name)
        TextView recyclerName;
        @Bind(R.id.webview)
        WebView webview;

        public ParkDetail_ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    class KeyWord_ViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.recycler_shop_keywordOne)
        TextView recyclerShopKeywordOne;
        @Bind(R.id.vertical_view)
        View verticalView;
        public KeyWord_ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public void setWebview(WebView webview) {
        WebSettings webSettings = webview.getSettings();
        //如果访问的页面中要与Javascript交互，则webview必须设置支持Javascript
        webSettings.setJavaScriptEnabled(true);
        // 若加载的 html 里有JS 在执行动画等操作，会造成资源浪费（CPU、电量）
        // 在 onStop 和 onResume 里分别把 setJavaScriptEnabled() 给设置成 false 和 true 即
        //设置自适应屏幕，两者合用
        webSettings.setUseWideViewPort(true); //将图片调整到适合webview的大小
        webSettings.setLoadWithOverviewMode(true); // 缩放至屏幕的大小
//缩放操作
        webSettings.setSupportZoom(true); //支持缩放，默认为true。是下面那个的前提。
        webSettings.setBuiltInZoomControls(true); //设置内置的缩放控件。若为false，则该WebView不可缩放
        webSettings.setDisplayZoomControls(false); //隐藏原生的缩放控件
////其他细节操作
        webSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK); //关闭webview中缓存
        webSettings.setAllowFileAccess(true); //设置可以访问文件
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true); //支持通过JS打开新窗口
        webSettings.setLoadsImagesAutomatically(true); //支持自动加载图片
        webSettings.setDefaultTextEncodingName("utf-8");//设置编码格式
        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);//
    }

    public String getHtml(String html_body) {
        //字符串html_body==精灵智库内容
        String html = "<!DOCTYPE  html>\n" +
                "<html><head><meta  charset=\"utf-8\">\n" +
                "<title></title>\n" +
                "<meta  content=\"width=device-width,  initial-scale=1.0,  minimum-scale=1.0,  maximum-scale=1.0,  user-scalable=no\"  name=\"viewport\"  />\n" +
                "<meta  http-equiv=\"Content-Type\"  content=\"text/html;  charset=UTF-8\"  />\n" +
                "<meta  http-equiv=\"Pragma\"  content=\"no-cache\"  />\n" +
                "<meta  http-equiv=\"Cache-Control\"  content=\"no-cache\"  />\n" +
                "<meta  http-equiv=\"Expires\"  content=\"0\"  />\n" +
                "<meta  http-equiv=\"Access-Control-Allow-Origin\"  content=\"*\"  />\n" +
                "<meta  name=\"format-detection\"  content=\"telephone=no\"  />\n" +
                "<style>  \n" +
                "body{  \n" +
                "\tpadding:0  0px;\n" +
                "\tfont-size:15px;\n" +
                "\tmargin:0;\n" +
                "\tfont-family:\"Microsoft  YaHei\\\",\\\"Open  Sans\\\",\\\"Helvetica  Neue\\\",Helvetica,Arial,sans-serif\";\n" +
                "\tfont-weight:300;  word-break:  break-all;}\n" +
                "span{color:#FF616161;text-align:left;line-height:30px;font-size:12px !important; }\n" +
                "blockquote  p{margin-top:16px;}\n" +
                "blockquote{padding:16px;    background-color:#f6f6f6;  font-size:15px;  display:  block;  -webkit-margin-before:  1em;  -webkit-margin-after:  0em;-webkit-margin-start:  0px;-webkit-margin-end:  0px;}\n" +
                "img{max-width:100%;  height:auto;}\n" +
                "div{text-align:left;line-height:30px;font-size:12px !important;}\n" +
                "h1{font-size:24px;}\n" +
                "h2{font-size:21px;}\n" +
                "h3{font-size:19px;}\n" +
                "iframe,img  {max-width:  100%;  }\n" +
                "a{color:#4d8ae7;text-decoration: none}\n" +
                "</style>\n" +
                "  <script>\n" +
                "\t\tfunction getImgArray(){\n" +
                "\t\t\tvar arr=document.getElementsByTagName('img');\n" +
                "\t\t\tvar imgArray=[];\n" +
                "\t\t\tfor(var x=0;x<arr.length;x++){\n" +
                "\t\t\t\timgArray[x]=arr[x].src;\n" +
                "\t\t\t}\n" +
                "\t\t\treturn imgArray;\n" +
                "\t\t}\t\n" +
                "  </script>" +
                " <style>\n" +
                "  img {\n" +
                "\twidth:100% !important;\n" +
                "\theight:auto !important;\n" +
                "  }\n" +
                "  </style>" +
                "  </style>" +
                "</head>  \n" +
                "<body>\n" +
                html_body +
                "</body>\n" +
                "</html>";
        html = html.replace("<img", "<img onclick='window.android.showToast(getImgArray(),this.src)'");
        html = html.replace("text-align:center;text-indent:2em;", "text-align:center;");
        return html;
    }
    public String getHtmlP(String html_body) {
        //字符串html_body==精灵智库内容
        String html = "<!DOCTYPE  html>\n" +
                "<html><head><meta  charset=\"utf-8\">\n" +
                "<title></title>\n" +
                "<meta  content=\"width=device-width,  initial-scale=1.0,  minimum-scale=1.0,  maximum-scale=1.0,  user-scalable=no\"  name=\"viewport\"  />\n" +
                "<meta  http-equiv=\"Content-Type\"  content=\"text/html;  charset=UTF-8\"  />\n" +
                "<meta  http-equiv=\"Pragma\"  content=\"no-cache\"  />\n" +
                "<meta  http-equiv=\"Cache-Control\"  content=\"no-cache\"  />\n" +
                "<meta  http-equiv=\"Expires\"  content=\"0\"  />\n" +
                "<meta  http-equiv=\"Access-Control-Allow-Origin\"  content=\"*\"  />\n" +
                "<meta  name=\"format-detection\"  content=\"telephone=no\"  />\n" +
                "<style>  \n" +
                "body{  \n" +
                "\tpadding:0  0px;\n" +
                "\tfont-size:15px;\n" +
                "\tmargin:0;\n" +
                "\tfont-family:\"Microsoft  YaHei\\\",\\\"Open  Sans\\\",\\\"Helvetica  Neue\\\",Helvetica,Arial,sans-serif\";\n" +
                "\tfont-weight:300;  word-break:  break-all;}\n" +
                "p{color:#FF616161;text-align:left;line-height:30px;font-size:12px !important; }\n" +
                "span{color:#FF616161;text-align:left;line-height:30px;font-size:12px !important; }\n" +
                "blockquote  p{margin-top:16px;}\n" +
                "blockquote{padding:16px;    background-color:#f6f6f6;  font-size:15px;  display:  block;  -webkit-margin-before:  1em;  -webkit-margin-after:  0em;-webkit-margin-start:  0px;-webkit-margin-end:  0px;}\n" +
                "img{max-width:100%;  height:auto;}\n" +
                "div{text-align:left;line-height:30px;font-size:12px !important;}\n" +
                "h1{font-size:24px;}\n" +
                "h2{font-size:21px;}\n" +
                "h3{font-size:19px;}\n" +
                "iframe,img  {max-width:  100%;  }\n" +
                "a{color:#4d8ae7;}\n" +
                "</style>\n" +
                "  <script>\n" +
                "\t\tfunction getImgArray(){\n" +
                "\t\t\tvar arr=document.getElementsByTagName('img');\n" +
                "\t\t\tvar imgArray=[];\n" +
                "\t\t\tfor(var x=0;x<arr.length;x++){\n" +
                "\t\t\t\timgArray[x]=arr[x].src;\n" +
                "\t\t\t}\n" +
                "\t\t\treturn imgArray;\n" +
                "\t\t}\t\n" +
                "  </script>" +
                " <style>\n" +
                "  img {\n" +
                "\twidth:100% !important;\n" +
                "\theight:auto !important;\n" +
                "  }\n" +
                "  </style>" +
                "  </style>" +
                "</head>  \n" +
                "<body>\n" +
                html_body +
                "</body>\n" +
                "</html>";
        html = html.replace("<img", "<img onclick='window.android.showToast(getImgArray(),this.src)'");
        html = html.replace("text-align:center;text-indent:2em;", "text-align:center;");
        return html;
    }
}
