/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.psi.hibernate.web;

import com.psi.hibernate.SearchManager;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author chetans
 */
public class SearchServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String searchThis = request.getParameter("searchbox");
        if (searchThis == null || searchThis.trim().equals("")) {
            String result = "Please enter something in the \"Search\" to search.";
            request.setAttribute("result", result);
            request.getRequestDispatcher("jsp/searchFailure.jsp").forward(request, response);
            return;
        } else {
            SearchManager manager = new SearchManager();
            List searchResult = manager.search(searchThis.trim());
            if (searchResult.size() < 1) {
                String result = "No results found for \""+searchThis.trim()+"\"";
                request.setAttribute("result", result);
                request.getRequestDispatcher("jsp/searchFailure.jsp").forward(request, response);
                return;
            }
            request.setAttribute("searchResult", searchResult);
            request.getRequestDispatcher("jsp/searchSuccess.jsp").forward(request, response);
            return;
        }
    }
}
