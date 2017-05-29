/**Copyright(C) 2017
 *CaseInsensitivePathMatcher.java, May 11, 2017 [Nguyễn Hưng Thuận]
 */
package manageproject.utils;

import java.util.Map;

import org.springframework.util.AntPathMatcher;

/**
 * @author DELL
 *
 */
public class CaseInsensitivePathMatcher extends AntPathMatcher {
	@Override
    protected boolean doMatch(String pattern, String path, boolean fullMatch, Map<String, String> uriTemplateVariables) {
        System.err.println(pattern + " -- " + path);
        return super.doMatch(pattern.toLowerCase(), path.toLowerCase(), fullMatch, uriTemplateVariables);
    }
}
