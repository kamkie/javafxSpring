package net.devops.javafxspring.backend.util

import groovy.transform.CompileStatic
import org.springframework.web.util.HtmlUtils

@CompileStatic
class HtmlUtil {

    static String htmlEscapeNullSafe(Object o) {
        return HtmlUtils.htmlEscape(o?.toString() ?: "{null}");
    }

}
