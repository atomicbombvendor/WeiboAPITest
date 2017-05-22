package weibo4j.examples.utils;

import weibo4j.Comments;
import weibo4j.examples.oauth2.Log;
import weibo4j.model.CommentWapper;
import weibo4j.model.Paging;
import weibo4j.model.WeiboException;

/**
 * Created by ZZ on 2017/5/22.
 */
public class Comment {
    private static String accessToken;
    private static int amount = 200;
    public static void main(String[] args) {
        String targetId = "4103078477115286";//目标
        int page1 = 0; //结束时间2017-05-22 21:56:19
        int page2 = 60;//应该开始的时间 2017-05-22 22:56:19
        getCommentByPaging(targetId,page2);
    }
    public static void getCommentByPaging(String id, int... pageIndex){
        String access_token = "2.007EB_ODbVATNEea9b7d49970sNnxs";
        int startPage = pageIndex[0];
        Comments cm = new Comments();
        cm.client.setToken(access_token);
        try {
            int count=0;
            long totalAmount = cm.getCommentById(id).getTotalNumber();
            Log.logInfo("comment's Id is "+id);
            Log.logInfo("comments total amount is " + totalAmount);

            Paging page = new Paging();
            page.setCount(amount);
            int pageNum = (int)(totalAmount%amount==0?totalAmount%amount:((totalAmount%amount)+1));
            Log.logInfo("Page amount is "+pageNum+" ,every page have "+amount+" comments");
            for (int i=pageNum; i>=startPage; i--) {//逆序的得到评论
                Log.logInfo("Current page num is "+i);
                page.setPage(i);//设置页码
                CommentWapper comment = cm.getCommentById(id, page, 0);
                for (weibo4j.model.Comment c : comment.getComments()) {
                    Log.logInfo("count>> "+(++count)+" "+c.toString());
                }
            }
            Log.logInfo("All amount are "+totalAmount+"||Print Comments Count are "+count);
        } catch (WeiboException e) {
            e.printStackTrace();
        }
    }
}
