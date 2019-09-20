package io.github.mat3e;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "Lang", urlPatterns = {"/api/langs"})
public class LangServlet extends HttpServlet {
    private final Logger logger = LoggerFactory.getLogger(HelloServlet.class);

    private LangRepository repository;
    private ObjectMapper mapper;

    /**
     * Servlet container needs it
     */
    @SuppressWarnings("unsed")
    public LangServlet() {
        this(new LangRepository(), new ObjectMapper());
    }

    private LangServlet(LangRepository repository, ObjectMapper mapper) {
        this.mapper = mapper;
        this.repository = repository;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        logger.info("Got request with parameters: " + req.getParameterMap());
        resp.setContentType("application/json;charset=UTF-8");
        mapper.writeValue(resp.getOutputStream(), repository.findAll());
    }
}
