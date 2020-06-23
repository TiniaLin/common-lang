import org.apache.commons.lang3.StringUtils;

/**
 * @author tinia
 */
public class ExceptionMessageUtils {
    /**
     * 获取异常信息，如果为空，则返回异常类型
     * @param e 异常
     * @return 异常信息。如果没有异常信息，返回异常类型。
     */
    public static String getMessage(Exception e) {
        String message = e.getMessage();
        if (StringUtils.isEmpty(message)) {
            message = e.getClass().toString();
        }
        return message;
    }
}
