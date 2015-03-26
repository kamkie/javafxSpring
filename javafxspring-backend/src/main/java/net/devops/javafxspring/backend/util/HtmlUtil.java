package net.devops.javafxspring.backend.util;

import org.springframework.web.util.HtmlUtils;

public class HtmlUtil {

    public static String htmlEscapeNullSafe(Object o) {
        String str = null;
        if (o != null) {
            str = HtmlUtils.htmlEscape(o.toString());
        }
        return str;
    }

}
