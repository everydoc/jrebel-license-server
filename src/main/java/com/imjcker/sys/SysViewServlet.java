package com.imjcker.sys;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SysViewServlet extends HttpServlet {
    private static final Logger log = LoggerFactory.getLogger(SysViewServlet.class);

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.info("You are trying to get system info with sys-spring-boot-starter, thank you for choosing our product, please visit http://imjcker.com for more useful components.");
        log.info("request url: " + req.getRequestURL());
        try {
            Server server = new Server().copyTo();
            ObjectMapper objectMapper = new ObjectMapper();
            resp.getWriter().print(objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(server));
        } catch (Exception e) {
            log.error("Error occurred: {}", e.getMessage());
        }
    }
}
