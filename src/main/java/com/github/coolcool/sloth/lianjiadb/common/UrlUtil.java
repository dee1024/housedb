package com.github.coolcool.sloth.lianjiadb.common;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 接口或类的说明
 * =========================
 */
public class UrlUtil {

    public static final String ENCODING_UTF8 = "UTF-8";
    static Logger logger = LoggerFactory.getLogger(UrlUtil.class);

    /**
     * 处理URL参数串，把参数名和参数值转化成键值对的形式
     */
    public static Map<String, String> convertParamsString2Map(String paramsString) {
        Map<String, String> paramsMap = new HashMap<String, String>();
        String[] tempParams = paramsString.split("&");
        String name = null;
        String value = null;
        if (tempParams != null && tempParams.length > 0) {
            for (int i = 0; i < tempParams.length; i++) {
                String[] tempArray = tempParams[i].split("=");
                if (tempArray.length == 2) {
                    name = tempArray[0];
                    value = tempArray[1];
                } else {
                    if (tempArray.length != 1)
                        continue;
                    else {
                        name = tempArray[0];
                        value = "";
                    }
                }
                if (!StringUtils.isEmpty(name)) {
                    paramsMap.put(name, value);
                }
            }
        }
        return paramsMap;
    }

    /**
     * 将字符串的URL参数转换成Map形式的键值对 
     * @param urlParameterString 可以是带参数的URL，也可以是纯参数字符串
     * @return 参数Map对象
     * @author zhangxx2
     */
    public static Map<String, String> convertUrlParameterStringToMap(String urlParameterString) {
        Map<String, String> paramMap = new HashMap<String, String>();
        if (urlParameterString == null)
            return paramMap;
        if (urlParameterString.indexOf("?") != -1)
            urlParameterString = urlParameterString.substring(urlParameterString.indexOf("?") + 1);
        String[] userInfoParam = urlParameterString.split("&");
        String[] kv = null;
        for (String _str : userInfoParam) {
            if (StringUtils.isEmpty(_str))
                continue;
            kv = _str.split("=");
            if (kv.length > 2)
                paramMap.put(kv[0], _str.substring(_str.indexOf("=") + 1));
            else if (kv.length == 2)
                paramMap.put(kv[0], kv[1]);
            else if (kv.length == 1 && kv[0] != null)
                paramMap.put(kv[0], "");
            else
                logger.warn(_str);
        }
        return paramMap;
    }

    /**
     * 把Map转成URL的参数串
     * 
     * @param paramMap
     * @return
     */
    public static String convertParamsMap2String(Map<String, String> paramMap) {
        return convertParamsMap2String(paramMap, true);
    }

    public static String convertParamsMap2String(Map<String, String> paramMap, boolean isEncode) {
        StringBuilder sb = new StringBuilder("");
        String value = null;
        if (paramMap != null) {
            for (Map.Entry<String, String> entry : paramMap.entrySet()) {
                if (!StringUtils.isEmpty(sb.toString()))
                    sb.append("&");
                value = entry.getValue() == null ? "" : entry.getValue();
                if (isEncode) {
                    try {
                        sb.append(entry.getKey()).append("=").append(URLEncoder.encode(value, ENCODING_UTF8));
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                        logger.error("",e);
                    }
                } else
                    sb.append(entry.getKey()).append("=").append(value);

            }
        }
        return sb.toString();
    }

    /**
     * 获取URL参数串中指定参数的值
     * 
     * @param paramsString
     * @param paramName
     * @return
     */
    public static String getParamValue(String paramsString, String paramName) {
        return convertParamsString2Map(paramsString).get(paramName);
    }

    /**
     * 从参数串中移除某个参数(不会自动编码解码)
     * 
     * @param paramsString
     * @param paramName
     * @return
     */
    public static String removeParam(String paramsString, String paramName) {
        Map<String, String> map = convertParamsString2Map(paramsString);
        if (!StringUtils.isEmpty(map.get(paramName))) {
            map.remove(paramName);
        }
        return convertParamsMap2String(map, false);
    }

    /**
     * 从URL中移除某个参数
     * 
     * @param paramName
     * @return
     */
    public static String removeParamFromUrl(String url, String paramName) {
        String[] data = url.split("\\?");
        if (data.length < 2) {
            return url;
        }
        String paramsString = removeParam(data[1], paramName);
        if (paramsString == null || "".equals(paramsString)) {
            return data[0];
        } else {
            return data[0] + "?" + paramsString;
        }
    }

    /**
     * 返回已经排好序（按照参数名的英文字母升序）的参数名字的列表
     * 
     * @param paramsString
     * @return
     */
    public static List<String> getSortParamsName(String paramsString) {
        List<String> paramsNameList = new ArrayList<String>();
        String[] tempParams = paramsString.split("&");
        if (tempParams != null && tempParams.length > 0) {
            for (int i = 0; i < tempParams.length; i++) {
                if (!StringUtils.isEmpty(tempParams[i].split("=")[0])) {
                    paramsNameList.add(tempParams[i].split("=")[0]);
                }
            }
            Collections.sort(paramsNameList);// key排序
        }
        return paramsNameList;
    }

    /**
     * 处理URL参数串，对参数的顺序进行重新排序，按照参数名的英文字母升序
     * 
     * @param paramsString
     *            格式："b=XX & d=XX & c=XX & a=XX" (乱序)
     * @return sortParamsString 格式："a=XX & b=XX & c=XX & d=XX" (升序)
     */
    public static String getSortParamsString(String paramsString) {
        return getSortParamsString(paramsString, true);
    }

    public static String getSortParamsString(String paramsString, boolean isDecode) {
        StringBuilder sortParamsString = new StringBuilder("");
        List<String> sortParamsNameList = getSortParamsName(paramsString);
        Map<String, String> paramsMap = convertParamsString2Map(paramsString);
        for (String paramName : sortParamsNameList) {
            if (!StringUtils.isEmpty(paramName)) {
                if (!StringUtils.isEmpty(sortParamsString.toString()))
                    sortParamsString.append("&");
                sortParamsString.append(paramName + "=" + paramsMap.get(paramName));
            }
        }

        String result = null;
        if (isDecode == false)
            return sortParamsString.toString();
        try {
            result = URLDecoder.decode(sortParamsString.toString(), ENCODING_UTF8);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            logger.error("",e);
        }

        return result;
    }

    /**
     * 在URL后面添加参数
     * @param url
     * @param params
     * @return
     */
    public static String appendParams(String url, String params) {
        String result = null;
        if (url != null && !"".equals(url.trim())) {
            result = url;
            if (params != null && !"".equals(params.trim())) {
                if (url.indexOf("?") > -1) {// URL中含有?，即已经有其他的参数了
                    return url + "&" + params;
                } else {
                    return url + "?" + params;
                }
            }
        }
        return result;
    }

    /**
     * 采用UTF-8字符集的URLEncoder方法
     * @param arg
     * @return
     * @author zhangxx2
     */
    public static String encode(String arg) {
        if (arg == null)
            return null;
        try {
            return URLEncoder.encode(arg, ENCODING_UTF8);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            logger.error("",e);
            return arg;
        }
    }

    /**
     * 采用UTF-8字符集的URLDecoder方法
     * @param arg
     * @return
     * @author zhangxx2
     */
    public static String decode(String arg) {
        if (arg == null)
            return null;
        try {
            return URLDecoder.decode(arg, ENCODING_UTF8);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            logger.error("",e);
            return arg;
        }
    }

    /**
     * 获取排序后的参数字符串
     * @param paramsMap 参数Map对象
     * @return
     * @author zhangxx2
     */
    public static String getSortParamsString(Map<String, String> paramsMap) {
        return getSortParamsString(paramsMap, false);
    }

    /**
     * 获取排序后的参数字符串
     * @param paramsMap 参数Map对象
     * @param isEncode 是否进行URLEncoder编码
     * @return
     * @author zhangxx2
     */
    public static String getSortParamsString(Map<String, String> paramsMap, boolean isEncode) {
        if (paramsMap == null || paramsMap.isEmpty())
            return null;

        List<String> sortParamsNameList = new ArrayList<String>(paramsMap.keySet());
        Collections.sort(sortParamsNameList);

        StringBuilder sortParamsString = new StringBuilder();
        String tmpValue = null;
        for (String paramName : sortParamsNameList) {
            if (StringUtils.isEmpty(paramName))
                continue;
            tmpValue = paramsMap.get(paramName) == null ? "" : paramsMap.get(paramName);
            sortParamsString.append(paramName).append("=").append(isEncode ? encode(tmpValue) : tmpValue).append("&");
            ;
        }

        if (sortParamsString.length() > 1) {
            int length = sortParamsString.length();
            sortParamsString.delete(length - 1, length);
        }

        return sortParamsString.toString();
    }

}
