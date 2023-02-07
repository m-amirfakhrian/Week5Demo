
package servlets;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Majid
 */
public class AverageServlet extends HttpServlet {

   

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        
        String action = request.getParameter("action");
        if (action != null && action.equals("reset")) {
            session.invalidate();
            session = request.getSession();
        }
        
        ArrayList<Integer> numbers = (ArrayList<Integer>)session.getAttribute("numbers");
        if (numbers == null)
            numbers = new ArrayList<>();
        
         if (request.getParameter("number") != null) {
            int number = Integer.parseInt(request.getParameter("number"));
            numbers.add(number);
            session.setAttribute("numbers", numbers);
        }
        
        // Calculate Average
        double average = 0.0;
        for(int number : numbers) {
            average += number;
        }
        
        if (numbers.size() > 0) {
            average /= numbers.size();
        }
        
        request.setAttribute("average",average);
        
     getServletContext().getRequestDispatcher("/WEB-INF/average.jsp").forward(request, response);
    }

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }

    
}
