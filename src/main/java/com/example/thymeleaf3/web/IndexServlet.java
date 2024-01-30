package com.example.thymeleaf3.web;

import com.example.thymeleaf3.config.TemplateEngineUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

@WebServlet("/")
public class IndexServlet extends HttpServlet {

    static class Employee {
        String firstName;

        public Employee(String name) {
            this.firstName = name;
        }

        public String getFirstName() {
            return firstName;
        }

        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<Employee> employeeList = new ArrayList<>();
        employeeList.add(new Employee("TEst1"));
        employeeList.add(new Employee("TEst2"));

        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(request.getServletContext());

        WebContext context = new WebContext(request, response, request.getServletContext());

        context.setVariable("employees", employeeList);
        engine.process("index.html", context, response.getWriter());
    }

}
