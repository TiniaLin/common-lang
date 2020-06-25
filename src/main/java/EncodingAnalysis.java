import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * @author tinia
 */
public class EncodingAnalysis {

    public static final String[] COMMON_ENCODINGS = {
            "UTF-8",
            "GBK",
            "GB2312",
            "ISO-8859-1",
            "BIG5"
    };

    public static String convert(String text, String fromEncoding, String toEncoding) throws UnsupportedEncodingException {
        return new String(text.getBytes(fromEncoding), toEncoding);
    }

    public static String convert(byte[] bytes, String encoding) throws UnsupportedEncodingException {
        return new String(bytes, encoding);
    }

    public static String combinations(String text) throws UnsupportedEncodingException {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < COMMON_ENCODINGS.length; i++) {
            for (int j = 0; j < COMMON_ENCODINGS.length; j++) {
                if (i == j) {
                    continue;
                }
                sb.append("From ").append(COMMON_ENCODINGS[i]).append(" to ").append(COMMON_ENCODINGS[j]).append(": ").append(new String(text.getBytes(COMMON_ENCODINGS[i]), COMMON_ENCODINGS[j])).append("\n");
            }
        }
        return sb.toString();
    }

    public static String URLEncoding(String text, String enc) throws UnsupportedEncodingException {
        String encode = URLEncoder.encode(text, enc).replace("+", "%20");
        return encode;
    }
}
