import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.lang.reflect.Method;

/**
 * @author tinia
 */
public class BeanUtils {

    private static final String GET_PREFIX = "get";
    private static final String SET_PREFIX = "set";

    /**
     * 复制Bean属性
     *
     * @param from 被复制对象
     * @param to   复制后对象的类型
     * @param <T> 被复制对象类型
     * @param <S> 复制后对象的类型
     * @return 复制后的对象
     */
    public static <T, S> S copyProperties(T from, Class<S> to) {
        if (from == null) {
            return null;
        }
        S s;
        try {
            s = to.newInstance();
            Method[] tMethods = from.getClass().getMethods();
            for (Method tMethod : tMethods) {
                String tMethodName = tMethod.getName();
                if (tMethodName.startsWith(GET_PREFIX)) {
                    String sSetterName = tMethodName.replaceFirst(GET_PREFIX, SET_PREFIX);
                    Method sSetter = to.getMethod(sSetterName, tMethod.getReturnType());
                    if (sSetter != null) {
                        sSetter.invoke(s, tMethod.invoke(from));
                    }
                }
            }
        } catch (Exception e) {
            s = JSONObject.parseObject(JSON.toJSONString(from), to);
        }
        return s;
    }

}
