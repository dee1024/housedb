package com.github.coolcool.sloth.lianjiadb.common;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.httpclient.*;
import org.apache.commons.httpclient.cookie.CookiePolicy;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.commons.httpclient.util.EncodingUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.client.params.ClientPNames;

/**
 * HTTP请求工具
 */
public abstract class NetUtil {

    static HttpClient httpClient = new HttpClient();
    static{
        httpClient.getParams().setParameter(ClientPNames.COOKIE_POLICY,
                CookiePolicy.BROWSER_COMPATIBILITY);
    }

    static Matcher matcher = null;
    static Pattern tokenPattern = Pattern.compile("<input name=\"authenticity_token\" type=\"hidden\" value=\"(.*?)\" />");
    static Pattern indexStartTokenPattern = Pattern.compile("<form.*?class=\"starred\".*?name=\"authenticity_token\" type=\"hidden\" value=\"(.*?)\"");



    private static Log logger = LogFactory.getLog(NetUtil.class);

    /**
     * 异常码：签名不正确
     */
    public static final String ERROR_INVALID_SIGN = "90";
    /**
     * 异常码：服务不可用
     */
    public static final String ERROR_SERVICE_UNAVAILABLE = "91";
    /**
     * 异常码：无效请求
     */
    public static final String ERROR_INVALID_REQUEST = "92";
    /**
     * 异常码：服务器内部错误
     */
    public static final String ERROR_INTERNAL_SERVER_ERROR = "93";
    /**
     * 异常码：应用不存在
     */
    public static final String ERROR_APPLICATION_IS_NOT_EXISTED = "94";
    /**
     * 异常码：服务不存在
     */
    public static final String ERROR_SERVICE_IS_NOT_FOUND = "95";
    /**
     * 异常码：非法请求参数
     */
    public static final String ERROR_ILLEGAL_REQUEST_PARAMETER = "96";

    /**
     * 异常信息容器
     */
    private static Map<String, String> errMsgContainer = new HashMap<String, String>();

    /*
     * ==========================================================================
     * =======================
     */

    /**
     * 根据具体的的KEY来获取U点续费错误信息
     * @param key
     * @return
     */
    public static String getErrorMessageInfo(String key) {
        if (errMsgContainer.isEmpty()) {
            errMsgContainer.put(ERROR_INVALID_SIGN, "签名不正确");
            errMsgContainer.put(ERROR_SERVICE_UNAVAILABLE, "服务不可用");
            errMsgContainer.put(ERROR_INVALID_REQUEST, "无效请求");
            errMsgContainer.put(ERROR_INTERNAL_SERVER_ERROR, "服务器内部错误");
            errMsgContainer.put(ERROR_APPLICATION_IS_NOT_EXISTED, "应用不存在");
            errMsgContainer.put(ERROR_SERVICE_IS_NOT_FOUND, "服务不存在");
            errMsgContainer.put(ERROR_ILLEGAL_REQUEST_PARAMETER, "非法请求参数");
        }
        return errMsgContainer.get(key);
    }

    /**
     * 发送POST请求
     */
    public static String postHttpRequest(String paramStr, String postUri, int expireSeconds)  {
        return postHttpRequest(paramStr, postUri, expireSeconds, null);
    }

    /**
     * 发送GET请求
     * @param paramStr 字符串形式的参数
     */
    public static String getHttpRequest(String paramStr, String getUri, int expireSeconds) {
        return getHttpRequest(paramStr, getUri, expireSeconds, null);
    }

    /**
     * 发送POST请求
     * @param paramStr 字符串形式的参数
     * @param postUrl POST请求的URL
     * @param expireSeconds 请求超时时间，单位为秒
     * @param charSet 字符集
     */
    public static String postHttpRequest(String paramStr, String postUrl, int expireSeconds, String charSet) {
        if (paramStr == null)
            paramStr = "";
        return postHttpRequest(UrlUtil.convertUrlParameterStringToMap(paramStr), postUrl, expireSeconds, charSet);
    }

    /**
     * 发送GET请求
     * @param paramStr 字符串形式的参数
     * @param getUrl GET请求的URL
     * @param expireSeconds 请求超时时间，单位为秒]
     * @param charSet 字符集
     */
    public static String getHttpRequest(String paramStr, String getUrl, int expireSeconds, String charSet) {
        if (paramStr == null)
            paramStr = "";
        return getHttpRequest(UrlUtil.convertUrlParameterStringToMap(paramStr), getUrl, expireSeconds, charSet);
    }

    /**
     * 发送POST请求
     * @param uParamsMap Map形式的�数

     */
    public static String postHttpRequest(Map<String, String> uParamsMap, String postUri, int expireSeconds) {
        return postHttpRequest(uParamsMap, postUri, expireSeconds, null);
    }

    /**
     * 发送POST请求
     * @param uParamsMap Map形式的参数
     */
    public static String getHttpRequest(Map<String, String> uParamsMap, String getUri, int expireSeconds) {
        return getHttpRequest(uParamsMap, getUri, expireSeconds, null);
    }

    /**
     * 发送POST请求
     * @param uParamsMap Map形式的�数
     * @param postUrl POST请求的URL
     * @param expireSeconds 请求超时时间，单位为秒
     * @param charSet 字符集
     */
    public static String postHttpRequest(Map<String, String> uParamsMap, String postUrl, int expireSeconds, String charSet)  {
        //logger.info("以HTTP Client的方式，发送POST请求.");
        NameValuePair[] nameValuPairArray = convertParamsMap2NameValuePair(uParamsMap);
        PostMethod postMethod = new PostMethod(postUrl);
        if (charSet == null) {
            postMethod.setQueryString(nameValuPairArray);
        } else {
            String queryString = EncodingUtil.formUrlEncode(nameValuPairArray, charSet);
            postMethod.setQueryString(queryString);
        }
        String result = httpRequest(postMethod, expireSeconds, postUrl, charSet);
        //logger.info("POST请求发送完毕. ");
        return result;
    }

    /**
     * 发送POST请求
     * @param uParamsMap Map形式的参数
     * @param getUrl GET请求的URL
     * @param expireSeconds 请求超时时间，单位为秒
     * @param charSet 字符集
     */
    public static String getHttpRequest(Map<String, String> uParamsMap, String getUrl, int expireSeconds, String charSet)  {
        //logger.info("以HTTP Client的方式，发送GET请求.");
        NameValuePair[] nameValuPairArray = convertParamsMap2NameValuePair(uParamsMap);
        GetMethod getMethod = new GetMethod(getUrl);
        if (charSet == null) {
            getMethod.setQueryString(nameValuPairArray);
        } else {
            String queryString = EncodingUtil.formUrlEncode(nameValuPairArray, charSet);
            getMethod.setQueryString(queryString);
        }
        String result = httpRequest(getMethod, expireSeconds, getUrl, charSet);
        //logger.info("GET请求发送完毕. ");
        return result;
    }

    /**
     * 转换Map的参数成NameValuePair
     * @param uParamsMap
     * @return
     */
    public static NameValuePair[] convertParamsMap2NameValuePair(Map<String, String> uParamsMap) {
        if (uParamsMap == null)
            uParamsMap = new HashMap<String, String>();
        List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
        for (Map.Entry<String, String> entry : uParamsMap.entrySet()) {
            nameValuePairs.add(new NameValuePair(entry.getKey(), entry.getValue()));
        }
        NameValuePair[] nameValuPairArray = nameValuePairs.toArray(new NameValuePair[nameValuePairs.size()]);
        return nameValuPairArray;
    }

    /**
     * 发送POST请求

     */
    public static String httpRequest(HttpMethodBase postMethod, int expireSeconds, String requestUri)  {
        return httpRequest(postMethod, expireSeconds, requestUri, null);
    }

    /**
     * 发送POST请求
     */
    public static String httpRequest(HttpMethodBase postMethod, int expireSeconds, String requestUri, String charSet)  {


        postMethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, new DefaultHttpMethodRetryHandler());
        httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(expireSeconds > 0 ? expireSeconds * 1000 : 5000);
        if (logger.isDebugEnabled())
            logger.debug("http Request QueryString: " + postMethod.getQueryString());

        StringBuffer returnBodyStr = new StringBuffer();
        InputStream in = null;
        BufferedReader bufferedreader = null;
        try {
            int status = httpClient.executeMethod(postMethod);
            in = postMethod.getResponseBodyAsStream();
            if (charSet == null)
                charSet = postMethod.getResponseCharSet();
            bufferedreader = new BufferedReader(new InputStreamReader(in, charSet));
            for (String sl = bufferedreader.readLine(); sl != null; sl = bufferedreader.readLine()) {
                returnBodyStr.append(sl);
            }
            if (HttpServletResponse.SC_OK == status) {
                //logger.info("POST请求发送完毕. ");
                return returnBodyStr.toString();
            } else if (HttpServletResponse.SC_INTERNAL_SERVER_ERROR == status) {
                logger.error(postMethod.getURI() + " 远程服务调用时出现内部服务错误. " + ("".equals(returnBodyStr.toString()) ? "" : (". Response body: " + returnBodyStr.toString())));
            } else if (HttpServletResponse.SC_NOT_FOUND == status) {
                logger.error(postMethod.getURI() + " 找不到对应的服务. " + ("".equals(returnBodyStr.toString()) ? "" : (". Response body: " + returnBodyStr.toString())));
            }else  if(status==HttpServletResponse.SC_MOVED_PERMANENTLY||status==HttpServletResponse.SC_MOVED_TEMPORARILY){
                Header locationHeader = postMethod.getResponseHeader("location");
                String location = null;
                if(locationHeader != null){
                    location = locationHeader.getValue();
                    return getHttpRequest("",location,3);
                }
            }
            else {
                String errMsg = "Encounter exception. Exception code: " + status + ("".equals(returnBodyStr.toString()) ? "" : (". Response body: " + returnBodyStr.toString()));
                logger.error(errMsg);
            }
        } catch (HttpException e) {
            logger.error(requestUri + " 远程服务调用时发生HttpException，异常信息: " + e.getMessage());
            logger.error("",e);
        } catch (IOException e) {
            logger.error(requestUri + " 远程服务调用时发生IOException，异常信息: " + e.getMessage());
            logger.error("",e);
        } finally {
            postMethod.releaseConnection();
            if (bufferedreader != null) {
                try {
                    bufferedreader.close();
                } catch (IOException e) {
                    logger.error("",e);
                }
            }
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    logger.error("",e);
                }
            }
        }
        return "";
    }




    public static void main(String[] args) throws IOException {

        String username = "CarnegieEunice";
        String email = username.concat("@gmail.com");
        String password = "123abcd";

        String url = "https://www.github.com/join";
        String joinPageHtml = getHttpRequest("",url,8);
        Matcher matcher = tokenPattern.matcher(joinPageHtml);
        String token = "";
        while (matcher.find()) {
            token = matcher.group(1);
        }
        //check username
//        String checkUsernameUrl = "https://github.com/signup_check/username";
//        Map<String,String> checkUsernameParams = new HashMap<>();
//        checkUsernameParams.put("value",username);
//        checkUsernameParams.put("authenticity_token",token);
//        String checkUsernameResult = postHttpRequest(checkUsernameParams, checkUsernameUrl, 8);
//        if(!StringUtils.isBlank(checkUsernameResult)){
//            logger.error("username ".concat(username).concat(" is error : ").concat(checkUsernameResult));
//            return;
//        }
//
//        //check email
//        String checkEmailUrl = "https://github.com/signup_check/email";
//        Map<String,String> checkEmailParams = new HashMap<>();
//        checkEmailParams.put("value",email);
//        checkEmailParams.put("authenticity_token",token);
//        String checkEmailResult = postHttpRequest(checkEmailParams, checkEmailUrl, 8);
//        if(!StringUtils.isBlank(checkEmailResult)) {
//            logger.error("email ".concat(email).concat(" is error : ").concat(checkEmailResult));
//            return ;
//        }
//
//        //check password
//        String checkPasswordUrl = "https://github.com/signup_check/password";
//        Map<String,String> checkPasswordParams = new HashMap<>();
//        checkPasswordParams.put("value",password);
//        checkPasswordParams.put("authenticity_token",token);
//        String checkPasswordResult = postHttpRequest(checkPasswordParams, checkPasswordUrl, 8);
//        if(!StringUtils.isBlank(checkPasswordResult)) {
//            logger.error("password is error : " + checkPasswordResult);
//        }


        //join
//        String joinUrl = "https://github.com/join";
//        Map<String,String> joinParams = new HashMap<>();
//        joinParams.put("utf8","✓");
//        joinParams.put("authenticity_token",token);
//        joinParams.put("user[login]",username);
//        joinParams.put("user[email]",email);
//        joinParams.put("user[password]",password);
//        joinParams.put("source","form-join");
//        String joinResult = postHttpRequest(joinParams,joinUrl,3);
//        if(joinResult.indexOf("Welcome to GitHub")>-1){
//            logger.info("User "+username+" join github successfully .");
//        }
//
//        //visit
//        String indexUrl = "https://github.com/coolcooldee/sloth";
//        String indexPageHtml = getHttpRequest("",indexUrl,8);
//        String reg = ">\\s+([^\\s<]*)\\s+<";
//        indexPageHtml = indexPageHtml.replaceAll(reg, ">$1<");
//        Matcher matcher2 = indexStartTokenPattern.matcher(indexPageHtml);
//        String token2 = "";
//        while (matcher2.find()) {
//            token2 = matcher2.group(1);
//            System.out.println(token2);
//        }
//
//        //star
//        //coolcooldee sloth
//        String slotStarUrl = "https://github.com/coolcooldee/sloth/star";
//        Map<String,String> statParams = new HashMap<>();
//        statParams.put("utf8","✓");
//        statParams.put("authenticity_token",token2);
//        String statResult = postHttpRequest(statParams,slotStarUrl,3);
//        logger.info(statResult);

        //login
        String loginUrl = "https://github.com/login";
        String loginPageHtml = getHttpRequest("",loginUrl,3);
        String reg = ">\\s+([^\\s<]*)\\s+<";
        loginPageHtml = loginPageHtml.replaceAll(reg, ">$1<");
        Matcher matcher3 = tokenPattern.matcher(loginPageHtml);
        String token3 = "";
        while (matcher3.find()) {
            token3= matcher3.group(1);
        }


        String sessionUrl = "https://github.com/session";
        Map<String,String> sessionParam = new HashMap<>();
        sessionParam.put("commit","Sign in");
        sessionParam.put("utf8","✓");
        sessionParam.put("authenticity_token",token3);
        sessionParam.put("login",username);
        sessionParam.put("password",password);
        String sessionResult = postHttpRequest(sessionParam,sessionUrl,3);
        if(sessionResult.indexOf("Start a project")>-1){
            logger.info("User "+username+" login successfully.");
        }



    }


}
