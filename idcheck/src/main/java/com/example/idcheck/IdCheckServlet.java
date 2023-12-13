package com.example.idcheck;


import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "idCheckServlet", urlPatterns = "/id-check")
public class IdCheckServlet extends HttpServlet {

    ObjectMapper objectMapper;

    @Override
    public void init() throws ServletException {
        objectMapper = new ObjectMapper();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // /id-check?id=seungjo
        String id = req.getParameter("id");

        // content type
        resp.setContentType("application/json");

        // service 호출


        IdCheckResponse idCheckResponse = new IdCheckResponse(1);

        PrintWriter writer = resp.getWriter();
        String json = objectMapper.writeValueAsString(idCheckResponse);
        writer.write(json);

        objectMapper.writeValue(writer, idCheckResponse);

        /**
         * {
         *   "result": 1
         * }
         */

    }
}
