/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 *
 * @author Андрей
 */
public class CodeFilter implements Filter {

    // The filter configuration object we are associated with.  If
    // this value is null, this filter instance is not currently
    // configured. 
    private FilterConfig filterConfig = null;

    public CodeFilter() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {
        String encoding = request.getCharacterEncoding();
        System.out.println(encoding);
// установка UTF-8, если не установлена
        if (!"UTF-8".equalsIgnoreCase(encoding)) {
            response.setCharacterEncoding("UTF-8");
        }
        chain.doFilter(request, response);
    }



@Override
        public void destroy() {
    }

    /**
     * Init method for this filter
     */
    @Override
        public void init(FilterConfig filterConfig) {
        this.filterConfig = filterConfig;

    }

}
