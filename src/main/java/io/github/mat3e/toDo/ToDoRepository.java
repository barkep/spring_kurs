package io.github.mat3e.toDo;

import io.github.mat3e.HibernateUtil;

import java.util.List;

class ToDoRepository {

    List<ToDo> findAll() {
        var session = HibernateUtil.getSessionFactory().openSession();
        var transaction = session.beginTransaction();
        var result = session.createQuery("from ToDo", ToDo.class).list();
        transaction.commit();
        session.close();
        return result;
    }

    ToDo toggleTodo(Integer id) {
        var session = HibernateUtil.getSessionFactory().openSession();
        var transaction = session.beginTransaction();
        var result = session.get(ToDo.class, id);
        result.setDone(!result.getDone());
        transaction.commit();
        session.close();
        return result;
    }

    ToDo addTodo(ToDo newTodo) {
        var session = HibernateUtil.getSessionFactory().openSession();
        var transaction = session.beginTransaction();
        session.persist(newTodo);
        transaction.commit();
        session.close();
        return newTodo;
    }
}
