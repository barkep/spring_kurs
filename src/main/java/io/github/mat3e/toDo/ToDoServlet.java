package io.github.mat3e.toDo;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.mat3e.hello.HelloServlet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ToDo", urlPatterns = {"/api/todos/*"})
public class ToDoServlet extends HttpServlet {
    private final Logger logger = LoggerFactory.getLogger(HelloServlet.class);

    private ToDoRepository repository;
    private ObjectMapper mapper;

    /**
     * Servlet container needs it
     */
    @SuppressWarnings("unsed")
    public ToDoServlet() {
        this(new ToDoRepository(), new ObjectMapper());
    }

    private ToDoServlet(ToDoRepository repository, ObjectMapper mapper) {
        this.mapper = mapper;
        this.repository = repository;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        logger.info("Got request with parameters: " + req.getParameterMap());
        resp.setContentType("application/json;charset=UTF-8");
        mapper.writeValue(resp.getOutputStream(), repository.findAll());
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        logger.info("Put for : " + req.getPathInfo());
        try {
            Integer todoId = Integer.valueOf(req.getPathInfo().replace("/", ""));
            ToDo todo = repository.toggleTodo(todoId);
            mapper.writeValue(resp.getOutputStream(), todo);
        } catch (NumberFormatException e) {
            logger.warn("Wrong path " + req.getPathInfo());
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        var newTodo = mapper.readValue(req.getInputStream(), ToDo.class);
        newTodo.setDone(false);
        resp.setContentType("application/json;charset=UTF-8");
        mapper.writeValue(resp.getOutputStream(), repository.addTodo(newTodo));
    }
}
