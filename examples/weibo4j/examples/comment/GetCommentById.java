package weibo4j.examples.comment;

import weibo4j.Comments;
import weibo4j.examples.oauth2.Log;
import weibo4j.model.Comment;
import weibo4j.model.CommentWapper;
import weibo4j.model.Paging;
import weibo4j.model.WeiboException;

public class GetCommentById {

	public static void main(String[] args) {
		getCommentByPaging();
	}

	public static void getCommentDefault() {
		String access_token = "2.007EB_ODbVATNEea9b7d49970sNnxs";
		String id = "4109947069411711";
		Comments cm = new Comments();
		cm.client.setToken(access_token);
		try {
			CommentWapper comment = cm.getCommentById(id);
			System.out.println("Size: " + comment.getComments().size());
			for (Comment c : comment.getComments()) {
				Log.logInfo(c.toString());
			}
			System.out.println("Total Number: " + comment.getTotalNumber());
			System.out.println(comment.getNextCursor());
			System.out.println(comment.getHasvisible());
		} catch (WeiboException e) {
			e.printStackTrace();
		}
	}

	public static void getCommentByPaging(){
		String access_token = "2.007EB_ODbVATNEea9b7d49970sNnxs";
		String id = "4109526507094777";//
		Comments cm = new Comments();
		cm.client.setToken(access_token);
		try {
			int count=0;
			long totalAmount = cm.getCommentById(id).getTotalNumber();
			Log.logInfo("comment's Id is "+id);
			Log.logInfo("comments total amount is " + totalAmount);

			Paging page = new Paging();
			page.setCount(100);
			long pageNum = (totalAmount/100)+2;
			Log.logInfo("Page amount is "+pageNum);
			for (int i=1; i<=pageNum; i++) {
				Log.logInfo("Current page num is "+i);
				CommentWapper comment = cm.getCommentById(id, page, 0);
				for (Comment c : comment.getComments()) {
					Log.logInfo("count>> "+(++count)+" "+c.toString());
				}
			}
			Log.logInfo("All amount are "+totalAmount+"||Print Comments Count are "+count);
		} catch (WeiboException e) {
			e.printStackTrace();
		}
	}
}
