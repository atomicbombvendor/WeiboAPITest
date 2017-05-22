package weibo4j.examples.utils;

import weibo4j.Oauth;
import weibo4j.examples.oauth2.Log;
import weibo4j.http.AccessToken;
import weibo4j.model.WeiboException;
import weibo4j.util.BareBonesBrowserLaunch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by ZZ on 2017/5/22.
 */
public class oAuth {
    public static AccessToken getToken() throws IOException, WeiboException {
        AccessToken token=null;
        Oauth oauth = new Oauth();
        BareBonesBrowserLaunch.openURL(oauth.authorize("code", "", ""));
        System.out.println(oauth.authorize("code", "", ""));
        System.out.print("Hit enter when it's done.[Enter]:");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String code = br.readLine();
        Log.logInfo("code: " + code);
        try {
           token = oauth.getAccessTokenByCode(code);
        } catch (WeiboException e) {
            if (401 == e.getStatusCode()) {
                Log.logInfo("Unable to get the access token.");
            } else {
                e.printStackTrace();
            }
        }
        return token;
    }
}
