package controller;

import model.FlowerBean;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.stereotype.Controller;
import service.DatabaseService;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.DatatypeConverter;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.util.List;

/**
 * 发布商品的 Controller
 * Created by joker on 5/3 0003.
 */
@WebServlet("/create_flower")
@Controller
public class CreateFlowerController extends HttpServlet {

    // 上传文件存储目录
    private static final String UPLOAD_DIRECTORY = "flower";

    private DatabaseService databaseService = new DatabaseService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/view/create_flower.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        String base64 = req.getParameter("base64");
        String base64Image = base64.split(",")[1];
        byte[] imageBytes = DatatypeConverter.parseBase64Binary(base64Image);
        ByteArrayInputStream bis = new ByteArrayInputStream(imageBytes);
        BufferedImage image = ImageIO.read(bis);
        bis.close();

        // 构造路径来存储上传的文件
        String path = getServletContext().getRealPath(".") + File.separator + UPLOAD_DIRECTORY;
        String fileName = System.currentTimeMillis()+".jpg";

        File file = new File(path,fileName);
        ImageIO.write(image, "jpg", file);

        System.out.println(file);

        FlowerBean flowerBean = new FlowerBean();
        flowerBean.setTitle(req.getParameter("title"));
        flowerBean.setIntroduction(req.getParameter("introduction"));
        flowerBean.setPrice(Double.parseDouble(req.getParameter("price")));
        flowerBean.setImageURL(fileName);

        if (databaseService.insertFlower(flowerBean)){
            req.setAttribute("message", "上传成功!");
//            resp.sendRedirect(req.getContextPath()+"/.html");
        } else {
            req.setAttribute("message", "插入数据失败");
        }

        // 跳转到 message.jsp
//        req.getRequestDispatcher("/message.jsp").forward(req, resp);
    }
}
