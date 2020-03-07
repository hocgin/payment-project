package in.hocg.payment.spring.boot.sample.utils;

import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.util.Arrays;

/**
 * @author 李华业
 * @date 2020年03月04日 12:04:03
 */
public class IpUtils {

  private static final String[] IP_HEADER_NAMES = {
    "X-Forwarded-For",
    "Proxy-Client-IP",
    "WL-Proxy-Client-IP",
    "HTTP_X_FORWARDED_FOR",
    "HTTP_X_FORWARDED",
    "HTTP_X_CLUSTER_CLIENT_IP",
    "HTTP_CLIENT_IP",
    "HTTP_FORWARDED_FOR",
    "HTTP_FORWARDED",
    "HTTP_VIA",
    "REMOTE_ADDR"
  };

  public static String getRemoteIP()
  {
    RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
    if (requestAttributes == null)
    {
      return "0.0.0.0";
    }

    HttpServletRequest request = ((ServletRequestAttributes) requestAttributes).getRequest();
    for (String header: IP_HEADER_NAMES) {
      String ipList = request.getHeader(header);
      if (ipList != null && ipList.length() != 0 && !"unknown".equalsIgnoreCase(ipList)) {
        return  ipList.split(",")[0];
      }
    }
    return request.getRemoteAddr();
  }
}
