package org.zun.codegen.interceptor;

import cn.hutool.core.text.AntPathMatcher;
import cn.hutool.core.util.StrUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.zun.codegen.service.DynamicDataSourceService;

import javax.sql.DataSource;
import java.util.Enumeration;

@Slf4j
@Component
@RequiredArgsConstructor
public class AppInterceptor implements HandlerInterceptor {

    private final JdbcTemplate jdbcTemplate;
    private final DynamicDataSourceService dataSourceService;
    private final AntPathMatcher antPathMatcher = new AntPathMatcher();

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String requestURI = request.getRequestURI();
        if (isWhiteListUrl(requestURI)) {
            return HandlerInterceptor.super.preHandle(request, response, handler);
        }
        // 获取数据源地址
        String host = request.getHeader("x-datasource-host");
        String port = request.getHeader("x-datasource-port");
        String username = request.getHeader("x-datasource-username");
        String password = request.getHeader("x-datasource-password");
        if (!StrUtil.isAllNotBlank(host, port, username, password)) {
            throw new RuntimeException("数据源配置缺失");
        }
        var key = "%s_%s_%s_%s".formatted(host, port, username, password);

        // 如果有这个数据源了直接通过
        if (dataSourceService.has(key)) {
            return HandlerInterceptor.super.preHandle(request, response, handler);
        }

        // 设置数据源
        var url = "jdbc:mysql://%s:%s".formatted(host, port);
        DataSource dataSource = dataSourceService.create(url, username, password);
        jdbcTemplate.setDataSource(dataSource);
        dataSourceService.add(key, dataSource);

        return HandlerInterceptor.super.preHandle(request, response, handler);
    }

    private boolean isWhiteListUrl(String path) {
        return path.endsWith(".css") || path.endsWith(".js") || path.endsWith(".jpg")
                || path.endsWith(".png") || path.endsWith(".gif")
                || path.endsWith(".ico") || path.endsWith(".svg")
                || path.endsWith(".woff") || path.endsWith(".woff2")
                || path.endsWith(".ttf") || path.endsWith(".eot")
                || path.endsWith(".html") || path.equals("/") || path.equals("/v3/api-docs/swagger-config")
                || path.equals("/v3/api-docs");
    }
}
