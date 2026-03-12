import com.google.gson.Gson;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.util.List;

@WebServlet("/*")
public class JsonServlet extends HttpServlet {
    private Gson gson = new Gson();

    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");

        String path = req.getPathInfo();
        try {
            if (path == null || path.equals("/")) {
                List<Pokemon> list = DbManager.getInstance().selectAll();
                resp.getWriter().print(gson.toJson(list));
                return;
            }

            String[] parts = path.split("/");
            switch (parts[1]) {
                case "id":
                    int id = Integer.parseInt(parts[2]);
                    Pokemon p1 = DbManager.getInstance().selectById(id);
                    resp.getWriter().print(gson.toJson(p1));
                    break;
                case "name":
                    String name = parts[2];
                    Pokemon p2 = DbManager.getInstance().selectByName(name);
                    resp.getWriter().print(gson.toJson(p2));
                    break;
                default:
                    resp.sendError(HttpServletResponse.SC_NOT_FOUND);
            }
        } catch (Exception e) {
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            e.printStackTrace();
        }
    }
}