package com.ansiyida.cgjl.bean;

import java.util.List;

/**
 * Created by ansiyida on 2018/6/13.
 */
public class VisitedDYBean {

    /**
     * status : 0001
     * message : 成功
     * data : [{"id":"NONE724","title":"Smiling Could Help You Get More Out of Your Running Workouts","translateTitle":"","content":"<p>\n\tFor athletes of all levels, endurance \u2013 how long they can keep going at their chosen sport \u2013 is made up of physiological and psychological factors.\n<\/p>\n<p>\n\tPhysiological factors include cardiovascular fitness, and how efficient an athlete is at using energy (their \"movement economy\"). A critical psychological factor, on the other hand, is perceived effort, or how hard we feel we are working during an activity.\n<\/p>\n<p>\n\tThe lower our perceived effort, the easier we feel that an activity is.\n<\/p>\n<p>\n\tCrucially, any strategy that reduces how much an athlete perceives it to be an effort generally has a positive effect on endurance performance. One of the more surprising approaches could be to deliberately manipulate one's facial expression.\n<\/p>\n<p>\n\tAs peculiar as it may seem, many top athletes, including Olympic marathon gold medallist Eliud Kipchoge, strategically use periodic smiling during performance to relax and cope.\n<\/p>\n<p>\n\tIn addition, research has also suggested that intentional smiling may reduce effort perception during physical activity in comparison with frowning.\n<\/p>\n<p>\n\tHowever, until we began our latest investigation, no study had looked into the actual effects of facial expressions on movement economy or perceived effort during endurance activity that has a longer duration.\n<\/p>\n<p>\n\tRunner research\n<\/p>\n<p>\n\tWe asked 24 club-level runners to complete four six minute running blocks on a treadmill. Each six minute run was performed during a single session, with a two minute rest between each bout.\n<\/p>\n<p>\n\tDuring each run, participants either smiled (specifically a real or \"Duchenne\" smile, and not a fake smile), frowned (runners mimicked their own facial expression during intense running), attempted to consciously relax their hands and upper-body (by imagining they were holding a crisp but trying not to break it), or adopted their normal focus of attention during running.\n<\/p>\n<p>\n\tEach participant also wore a breathing mask that allowed us to measure how much oxygen they consumed while running. By measuring the oxygen, we could work out how much energy the runner had used.\n<\/p>\n<p>\n\tAfter each run, we asked participants to report on a number of perceptual responses, including their perceived effort during the preceding six minutes.\n<\/p>\n<p>\n\tOur key finding was that participants were most economical (they used less energy) while smiling.\n<\/p>\n<p>\n\tRemarkably, participants were 2.8 percent more economical when smiling than frowning, and 2.2 percent more economical in comparison with the normal thoughts condition. These reductions would be enough to expect a meaningful improvement in performance in race conditions.\n<\/p>\n<p>\n\tParticipants also reported a higher perceived effort when frowning than smiling or when attempting to relax their hands and upper body.\n<\/p>\n<p>\n\tCollectively, these results suggest that smiling may be a beneficial strategy to improve running economy, and to reduce perception of effort in comparison with frowning.\n<\/p>\n<p>\n\tIn contrast, not only does frowning reflect effort during physical activity, but may actually, in turn, increase our perception of effort.\n<\/p>\n<p>\n\tBut why exactly did facial expression impact the runners' economy and perceived effort?\n<\/p>\n<p>\n\tInterestingly, our findings are supported by the concept of embodied emotion \u2013 the idea that adopting a facial expression can influence how emotions are experienced.\n<\/p>\n<p>\n\tWe also know that relaxation strategies can improve running economy. So smiling may increase relaxation among runners, while frowning may increase tension.\n<\/p>\n<p>\n\tMore deliberate relaxation techniques may need some practice to be effective, however, perhaps explaining why the conscious relaxation cues did not improve running economy in our study.\n<\/p>\n<p>\n\tImproving your performance\n<\/p>\n<p>\n\tSo what are the practical implications of this study? And how can you use this research to improve your own running performance?\n<\/p>\n<p>\n\tOne implication is that smiling may be a useful strategy to improve economy and to make you feel more relaxed during running. In contrast, frowning may increase tension and make your run feel harder.\n<\/p>\n<p>\n\tThere are many questions we still need to answer, however.\n<\/p>\n<p>\n\tFirstly, how long should you smile for? Like Kipchoge, are periodic (30 second), bouts of smiling sufficient, or do we need to smile continuously like the runners in our study did?\n<\/p>\n<p>\n\tSecondly, does smiling also work during other endurance activities, like cycling or rowing?\n<\/p>\n<p>\n\tFinally, can a simple relaxation cue \u2013 to imagine delicately holding a crisp between your fingers \u2013 improve running economy with practice?\n<\/p>\n<p>\n\tA longer training study might answer these questions but, for now, our recommendation is to pay some attention to your facial expression and to smile as much as you can during your run.\n<\/p>\n<p>\n\tEven when the miles seem gruelling, try to focus on pleasant memories, beam and say hello to people as you run past, grin at cameras on the sidelines, or even a small smile to yourself when you complete each mile will work too.\n<\/p>\n<p>\n\tNoel Brick, Lecturer in Sport and Exercise Psychology, Ulster University and Richard Metcalfe, Lecturer in Sport and Exercise Science, Swansea University.\n<\/p>\n<p>\n\tThis article was originally published by&nbsp;The Conversation. Read the original article.\n<\/p>","remarks":"","source":"Sciencealert","isusetime":"2018-02-19 22:53:34","pkey":724,"type":"P","imgpath":"http://pyspider-crawl-image-v01.oss-cn-qingdao.aliyuncs.com/sciencealert.com/2018/01/b5be06e5a576632d4d3dbe90773761be0.jpg?x-oss-process=image/resize,m_mfit,h_150,w_200","iskey":"N","selftype":"P","description":"For athletes of all levels, endurance \u2013 how long they can keep going at their chosen sport \u2013 is made up of physiological and psychological factors.","headImg":"","userid":0,"comnum":0,"vtime":"","imgArray":[],"thumbsUpNum":0,"shareNum":0,"collectionNum":0}]
     */

    private String status;
    private String message;
    private List<DataBean> data;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : NONE724
         * title : Smiling Could Help You Get More Out of Your Running Workouts
         * translateTitle :
         * content : <p>
         For athletes of all levels, endurance – how long they can keep going at their chosen sport – is made up of physiological and psychological factors.
         </p>
         <p>
         Physiological factors include cardiovascular fitness, and how efficient an athlete is at using energy (their "movement economy"). A critical psychological factor, on the other hand, is perceived effort, or how hard we feel we are working during an activity.
         </p>
         <p>
         The lower our perceived effort, the easier we feel that an activity is.
         </p>
         <p>
         Crucially, any strategy that reduces how much an athlete perceives it to be an effort generally has a positive effect on endurance performance. One of the more surprising approaches could be to deliberately manipulate one's facial expression.
         </p>
         <p>
         As peculiar as it may seem, many top athletes, including Olympic marathon gold medallist Eliud Kipchoge, strategically use periodic smiling during performance to relax and cope.
         </p>
         <p>
         In addition, research has also suggested that intentional smiling may reduce effort perception during physical activity in comparison with frowning.
         </p>
         <p>
         However, until we began our latest investigation, no study had looked into the actual effects of facial expressions on movement economy or perceived effort during endurance activity that has a longer duration.
         </p>
         <p>
         Runner research
         </p>
         <p>
         We asked 24 club-level runners to complete four six minute running blocks on a treadmill. Each six minute run was performed during a single session, with a two minute rest between each bout.
         </p>
         <p>
         During each run, participants either smiled (specifically a real or "Duchenne" smile, and not a fake smile), frowned (runners mimicked their own facial expression during intense running), attempted to consciously relax their hands and upper-body (by imagining they were holding a crisp but trying not to break it), or adopted their normal focus of attention during running.
         </p>
         <p>
         Each participant also wore a breathing mask that allowed us to measure how much oxygen they consumed while running. By measuring the oxygen, we could work out how much energy the runner had used.
         </p>
         <p>
         After each run, we asked participants to report on a number of perceptual responses, including their perceived effort during the preceding six minutes.
         </p>
         <p>
         Our key finding was that participants were most economical (they used less energy) while smiling.
         </p>
         <p>
         Remarkably, participants were 2.8 percent more economical when smiling than frowning, and 2.2 percent more economical in comparison with the normal thoughts condition. These reductions would be enough to expect a meaningful improvement in performance in race conditions.
         </p>
         <p>
         Participants also reported a higher perceived effort when frowning than smiling or when attempting to relax their hands and upper body.
         </p>
         <p>
         Collectively, these results suggest that smiling may be a beneficial strategy to improve running economy, and to reduce perception of effort in comparison with frowning.
         </p>
         <p>
         In contrast, not only does frowning reflect effort during physical activity, but may actually, in turn, increase our perception of effort.
         </p>
         <p>
         But why exactly did facial expression impact the runners' economy and perceived effort?
         </p>
         <p>
         Interestingly, our findings are supported by the concept of embodied emotion – the idea that adopting a facial expression can influence how emotions are experienced.
         </p>
         <p>
         We also know that relaxation strategies can improve running economy. So smiling may increase relaxation among runners, while frowning may increase tension.
         </p>
         <p>
         More deliberate relaxation techniques may need some practice to be effective, however, perhaps explaining why the conscious relaxation cues did not improve running economy in our study.
         </p>
         <p>
         Improving your performance
         </p>
         <p>
         So what are the practical implications of this study? And how can you use this research to improve your own running performance?
         </p>
         <p>
         One implication is that smiling may be a useful strategy to improve economy and to make you feel more relaxed during running. In contrast, frowning may increase tension and make your run feel harder.
         </p>
         <p>
         There are many questions we still need to answer, however.
         </p>
         <p>
         Firstly, how long should you smile for? Like Kipchoge, are periodic (30 second), bouts of smiling sufficient, or do we need to smile continuously like the runners in our study did?
         </p>
         <p>
         Secondly, does smiling also work during other endurance activities, like cycling or rowing?
         </p>
         <p>
         Finally, can a simple relaxation cue – to imagine delicately holding a crisp between your fingers – improve running economy with practice?
         </p>
         <p>
         A longer training study might answer these questions but, for now, our recommendation is to pay some attention to your facial expression and to smile as much as you can during your run.
         </p>
         <p>
         Even when the miles seem gruelling, try to focus on pleasant memories, beam and say hello to people as you run past, grin at cameras on the sidelines, or even a small smile to yourself when you complete each mile will work too.
         </p>
         <p>
         Noel Brick, Lecturer in Sport and Exercise Psychology, Ulster University and Richard Metcalfe, Lecturer in Sport and Exercise Science, Swansea University.
         </p>
         <p>
         This article was originally published by&nbsp;The Conversation. Read the original article.
         </p>
         * remarks :
         * source : Sciencealert
         * isusetime : 2018-02-19 22:53:34
         * pkey : 724
         * type : P
         * imgpath : http://pyspider-crawl-image-v01.oss-cn-qingdao.aliyuncs.com/sciencealert.com/2018/01/b5be06e5a576632d4d3dbe90773761be0.jpg?x-oss-process=image/resize,m_mfit,h_150,w_200
         * iskey : N
         * selftype : P
         * description : For athletes of all levels, endurance – how long they can keep going at their chosen sport – is made up of physiological and psychological factors.
         * headImg :
         * userid : 0
         * comnum : 0
         * vtime :
         * imgArray : []
         * thumbsUpNum : 0
         * shareNum : 0
         * collectionNum : 0
         */

        private String id;
        private String title;
        private String translateTitle;
        private String content;
        private String remarks;
        private String source;
        private String isusetime;
        private int pkey;
        private String type;
        private String imgpath;
        private String iskey;
        private String selftype;
        private String description;
        private String headImg;
        private int userid;
        private int comnum;
        private String vtime;
        private int thumbsUpNum;
        private int shareNum;
        private int collectionNum;
        private List<?> imgArray;
        private String logo;

        public String getLogo() {
            return logo;
        }

        public void setLogo(String logo) {
            this.logo = logo;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getTranslateTitle() {
            return translateTitle;
        }

        public void setTranslateTitle(String translateTitle) {
            this.translateTitle = translateTitle;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getRemarks() {
            return remarks;
        }

        public void setRemarks(String remarks) {
            this.remarks = remarks;
        }

        public String getSource() {
            return source;
        }

        public void setSource(String source) {
            this.source = source;
        }

        public String getIsusetime() {
            return isusetime;
        }

        public void setIsusetime(String isusetime) {
            this.isusetime = isusetime;
        }

        public int getPkey() {
            return pkey;
        }

        public void setPkey(int pkey) {
            this.pkey = pkey;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getImgpath() {
            return imgpath;
        }

        public void setImgpath(String imgpath) {
            this.imgpath = imgpath;
        }

        public String getIskey() {
            return iskey;
        }

        public void setIskey(String iskey) {
            this.iskey = iskey;
        }

        public String getSelftype() {
            return selftype;
        }

        public void setSelftype(String selftype) {
            this.selftype = selftype;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getHeadImg() {
            return headImg;
        }

        public void setHeadImg(String headImg) {
            this.headImg = headImg;
        }

        public int getUserid() {
            return userid;
        }

        public void setUserid(int userid) {
            this.userid = userid;
        }

        public int getComnum() {
            return comnum;
        }

        public void setComnum(int comnum) {
            this.comnum = comnum;
        }

        public String getVtime() {
            return vtime;
        }

        public void setVtime(String vtime) {
            this.vtime = vtime;
        }

        public int getThumbsUpNum() {
            return thumbsUpNum;
        }

        public void setThumbsUpNum(int thumbsUpNum) {
            this.thumbsUpNum = thumbsUpNum;
        }

        public int getShareNum() {
            return shareNum;
        }

        public void setShareNum(int shareNum) {
            this.shareNum = shareNum;
        }

        public int getCollectionNum() {
            return collectionNum;
        }

        public void setCollectionNum(int collectionNum) {
            this.collectionNum = collectionNum;
        }

        public List<?> getImgArray() {
            return imgArray;
        }

        public void setImgArray(List<?> imgArray) {
            this.imgArray = imgArray;
        }
    }
}
