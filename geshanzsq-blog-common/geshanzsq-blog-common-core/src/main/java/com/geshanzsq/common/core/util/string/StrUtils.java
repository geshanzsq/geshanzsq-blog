package com.geshanzsq.common.core.util.string;

import com.geshanzsq.common.core.constant.CommonConstant;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.CollectionUtils;

import java.util.Collection;
import java.util.List;

/**
 * 字符串工具类
 *
 * @author geshanzsq
 * @date 2022/3/19
 */
public class StrUtils extends StringUtils {

    /**
     * 左括号
     */
    private final static String LEFT_BRACKET = "{";

    /**
     * 右括号
     */
    private final static String RIGHT_BRACKET = "}";

    /**
     * 转换文本, {} 表示占位符，例：
     * format("欢迎访问{blogName}博客，网站地址：{siteUrl}", "格姗知识圈", "http://geshanzsq.com")
     * format("欢迎访问{}博客，网站地址：{}", "格姗知识圈", "http://geshanzsq.com")
     * @param str 需要转换的字符串
     * @param params 参数
     */
    public static String format(String str, Object... params) {
        if (isBlank(str) || params == null || params.length == 0) {
            return str;
        }
        StringBuilder result = new StringBuilder();
        // 数组下标
        int index = 0;
        while (str.contains(LEFT_BRACKET) && str.contains(RIGHT_BRACKET)) {
            int start = str.indexOf(LEFT_BRACKET);
            int end = str.indexOf(RIGHT_BRACKET);
            // 如果 index 大于 params 数组，说明后续无需替换
            if (index > (params.length - 1)) {
                result.append(str);
                break;
            }
            // 如果右 } 大于左 }，直接取出 {，跳过当前，示例：欢迎访问{blogName}博客，}{网站地址：{siteUrl}
            if (end < start || index > (params.length - 1)) {
                result.append(str.substring(0, start + 1));
                str = str.substring(start + 1);
                continue;
            }
            result.append(str.substring(0, start));
            result.append(params[index]);
            str = str.substring(end + 1);
            index++;
        }
        result.append(str);
        return result.toString();
    }

    /**
     * 是否为http(s)://开头
     *
     * @param link 链接
     * @return 结果
     */
    public static boolean isHttp(String link) {
        return StringUtils.startsWithAny(link, CommonConstant.HTTP, CommonConstant.HTTPS);
    }

    /**
     * 判断是否为空，包含字符串、集合
     *
     * @param value 值
     * @return 是否为空
     */
    public static boolean isNullBlank(Object value) {
        return value == null
                || (value instanceof String && isBlank(String.valueOf(value)))
                || (value instanceof Collection && CollectionUtils.isEmpty((Collection) value));
    }

    /**
     * 判断是否不为空，包含字符串
     * @param value 值
     * @return 是否不为空
     */
    public static boolean isNotNullBlank(Object value) {
        return !isNullBlank(value);
    }

    /**
     * 查找指定字符串是否匹配指定字符串列表中的任意一个字符串
     *
     * @param str     指定字符串
     * @param strList 需要检查的字符串数组
     * @return 是否匹配
     */
    public static boolean matches(String str, List<String> strList) {
        if (CollectionUtils.isEmpty(strList)) {
            return false;
        }
        for (String pattern : strList) {
            if (isMatch(pattern, str)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 判断url是否与规则配置:
     * ? 表示单个字符;
     * * 表示一层路径内的任意字符串，不可跨层级;
     * ** 表示任意层路径;
     *
     * @param pattern 匹配规则
     * @param url     需要匹配的url
     * @return
     */
    public static boolean isMatch(String pattern, String url) {
        AntPathMatcher matcher = new AntPathMatcher();
        return matcher.match(pattern, url);
    }

}
