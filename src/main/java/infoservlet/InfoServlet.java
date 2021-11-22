package infoservlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/")
public class InfoServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter writer = resp.getWriter();
        writer.append(LocalDateTime.now() + "\n");
        writer.append("Attributes:\n");
        Collections.list(req.getAttributeNames()).stream().forEach(a -> {
            writer.append(a);
            writer.append(":");
            Object attribute = req.getAttribute(a);
            if (attribute instanceof Object[])
                writer.append(Arrays.toString((Object[]) attribute));
            else if (attribute instanceof byte[])
                writer.append(Arrays.toString((byte[]) attribute));
            else
                writer.append(attribute.toString());
            writer.append("\n");
        });
        writer.append("\n");
        writer.append("Headers:\n");
        Collections.list(req.getHeaderNames()).stream().forEach(a -> {
            writer.append(a);
            writer.append(":");
            writer.append(req.getHeader(a).toString());
            writer.append("\n");
        });
        writer.flush();
    }
}
