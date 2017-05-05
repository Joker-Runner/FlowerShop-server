package controller;

import model.FlowerBean;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.stereotype.Controller;
import service.DatabaseService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * 发布商品的 Controller
 * Created by joker on 5/3 0003.
 */
@WebServlet("/create_flower")
@Controller
public class CreateFlowerController extends HttpServlet {

//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        super.doGet(req, resp);
//    }
//
//    @Override
//    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
////        String s = req.getParameter("intro");
//        File file = new File(req.getParameter("file"));
//        String title = req.getParameter("title");
//
//    }

    private static final long serialVersionUID = 1L;

    // 上传文件存储目录
    private static final String UPLOAD_DIRECTORY = "flower";

    // 上传配置
    private static final int MEMORY_THRESHOLD = 1024 * 1024 * 3;  // 3MB
    private static final int MAX_FILE_SIZE = 1024 * 1024 * 40; // 40MB
    private static final int MAX_REQUEST_SIZE = 1024 * 1024 * 50; // 50MB

    /**
     * 上传数据及保存文件
     */
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {
        FlowerBean flowerBean = new FlowerBean();
        DatabaseService databaseService = new DatabaseService();

        // 检测是否为多媒体上传
        if (!ServletFileUpload.isMultipartContent(request)) {
            // 如果不是则停止
            PrintWriter writer = response.getWriter();
            writer.println("Error: 表单必须包含 enctype=multipart/form-data");
            writer.flush();
            return;
        }
        System.out.println(request.getCharacterEncoding());
        request.setCharacterEncoding("UTF-8");

        // 配置上传参数
        DiskFileItemFactory factory = new DiskFileItemFactory();
        // 设置内存临界值 - 超过后将产生临时文件并存储于临时目录中
        factory.setSizeThreshold(MEMORY_THRESHOLD);
        // 设置临时存储目录
        factory.setRepository(new File(System.getProperty("java.io.tmpdir")));

        ServletFileUpload upload = new ServletFileUpload(factory);

        // 设置最大文件上传值
        upload.setFileSizeMax(MAX_FILE_SIZE);

        // 设置最大请求值 (包含文件和表单数据)
        upload.setSizeMax(MAX_REQUEST_SIZE);

        // 中文处理
        System.out.println(upload.getHeaderEncoding());
        upload.setHeaderEncoding("UTF-8");

        // 构造路径来存储上传的文件
        String uploadPath = getServletContext().getRealPath(".") + File.separator + UPLOAD_DIRECTORY;

        // 如果目录不存在则创建
        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) {
            uploadDir.mkdir();
        }

        try {
            // 解析请求的内容提取文件数据
            @SuppressWarnings("unchecked")
            List<FileItem> formItems = upload.parseRequest(request);

            if (formItems != null && formItems.size() > 0) {
                // 迭代表单数据
                for (FileItem item : formItems) {
                    // 处理不在表单中的字段
                    if (!item.isFormField()) {
//                        String fileName = new File(item.getName()).getName();
//                        String filePath = uploadPath + File.separator + fileName;
                        String fileTempName = new File(item.getName()).getName();
                        String suffix = fileTempName.substring(fileTempName.lastIndexOf(".") + 1);
                        String fileName = System.currentTimeMillis() + "." + suffix;
                        String filePath = uploadPath + File.separator + fileName;
                        File storeFile = new File(filePath);
                        // 在控制台输出文件的上传路径
                        System.out.println(uploadPath);
                        System.out.println(filePath);
                        // 保存文件到硬盘
                        item.write(storeFile);

                        flowerBean.setImageURL(fileName);
//                        flowerBean.setTitle(request.getParameter("title"));
//                        flowerBean.setIntroduction(request.getParameter("introduction"));
//                        flowerBean.setPrice(Double.parseDouble(request.getParameter("price")));
//                        databaseService.insertFlower(flowerBean);
//                        request.setAttribute("message", "上传成功!");
                    } else {
                        String s = item.getFieldName();
                        switch (s) {
                            case "title":
//                                System.out.println(item.getString());
//                                System.out.println(item.getString("UTF-8"));
//                                System.out.println(item.getString("GBK"));
                                flowerBean.setTitle(item.getString("UTF-8"));
                                break;
                            case "introduction":
                                flowerBean.setIntroduction(item.getString("UTF-8"));
                                break;
                            case "price":
                                flowerBean.setPrice(Double.valueOf(item.getString("UTF-8")));
                                break;
                            default:
                                break;
                        }
                    }
                }
                if (databaseService.insertFlower(flowerBean)) {
                    request.setAttribute("message", "上传成功!");
                } else {
                    request.setAttribute("message", "插入数据失败");
                }
            }
        } catch (Exception ex) {
            request.setAttribute("message", "错误信息: " + ex.getMessage());
        }
        // 跳转到 message.jsp
        getServletContext().getRequestDispatcher("/message.jsp").forward(
                request, response);
    }
}
