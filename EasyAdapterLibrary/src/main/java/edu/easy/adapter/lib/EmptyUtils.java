package edu.easy.adapter.lib;

import java.util.List;
import java.util.Map;

/**
 * 对象为空检测类
 *
 * @author Single
 * @version 1.1
 * @category 对象为空检测类
 */
public class EmptyUtils {

    /**
     * 检测字符串是否为空
     *
     * @param params Type:String
     * @return true  params == null || params.length == 0
     */
    public static boolean emptyOfString(String params) {
        if (params != null && params.length() > 0) {
            return false;
        }
        return true;
    }

    /**
     * 检测List是否为空
     *
     * @param params Type:List<?>
     * @return true params == null || params.size()==0
     */
    public static boolean emptyOfList(List<?> params) {
        if (params != null && params.size() > 0) {
            return false;
        }
        return true;
    }

    /**
     * 检测数组是否为空
     *
     * @param params Type:Object[]
     * @return true params[] == null
     */
    public static boolean emptyOfArray(Object params[]) {
        if (params != null && params.length > 0) {
            return false;
        }
        return true;
    }

    /**
     * 检测对象是否为空
     *
     * @param params Type:Object
     * @return true params == null
     */
    public static boolean emptyOfObject(Object params) {
        if (params != null) {
            return false;
        }
        return true;
    }

    /**
     * 判断一个Map集合是否为空
     *
     * @param map
     * @return true map == null || map.size() == 0
     */
    public static <K, V> boolean emptyOfMap(Map<K, V> map) {
        if (map != null && map.size() > 0) {
            return false;
        }
        return true;
    }

    /**
     * 判断CharSequence是否为空
     *
     * @param mParams
     * @return
     */
    public static boolean emptyOfCharSequence(CharSequence mParams) {
        if (mParams != null && mParams.length() > 0) {
            return false;
        }
        return true;
    }
}
