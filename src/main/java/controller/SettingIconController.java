package controller;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import service.DatabaseService;
import utils.Utils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * 设置头像
 * Created by joker on 5/22 0022.
 */
@WebServlet("/setting_icon")
public class SettingIconController extends HttpServlet {

    // 上传文件存储目录
    private static final String UPLOAD_DIRECTORY = "icon";

    // 上传配置
    private static final int MEMORY_THRESHOLD = 1024 * 1024 * 3;  // 3MB
    private static final int MAX_FILE_SIZE = 1024 * 1024 * 40; // 40MB
    private static final int MAX_REQUEST_SIZE = 1024 * 1024 * 50; // 50MB

    int userId;
    String fileName;

    boolean success;

    private DatabaseService databaseService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (!ServletFileUpload.isMultipartContent(req)) {
            // 如果不是则停止
            PrintWriter writer = resp.getWriter();
            writer.println("Error: 表单必须包含 enctype=multipart/form-data");
            writer.flush();
            return;
        }
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
            List<FileItem> formItems = upload.parseRequest(req);

            if (formItems != null && formItems.size() > 0) {
                // 迭代表单数据
                for (FileItem item : formItems) {
                    // 处理不在表单中的字段
                    if (!item.isFormField()) {
                        String fileTempName = new File(item.getName()).getName();
                        String suffix = fileTempName.substring(fileTempName.lastIndexOf(".") + 1);
                        fileName = System.currentTimeMillis() + "." + suffix;
                        String filePath = uploadPath + File.separator + fileName;
                        File storeFile = new File(filePath);
                        System.out.println(filePath);
                        // 保存文件到硬盘
                        item.write(storeFile);
                    } else {
                        String s = item.getFieldName();
                        if (s.equals("user_id")) {
                            userId = Integer.parseInt(item.getString("UTF-8"));
                            System.out.println(userId);
//                        switch (s) {
//                            case "title":
////                                System.out.println(item.getString());
////                                System.out.println(item.getString("UTF-8"));
////                                System.out.println(item.getString("GBK"));
//                                flowerBean.setTitle(item.getString("UTF-8"));
//                                break;
//                            case "introduction":
//                                flowerBean.setIntroduction(item.getString("UTF-8"));
//                                break;
//                            case "price":
//                                flowerBean.setPrice(Double.valueOf(item.getString("UTF-8")));
//                                break;
//                            default:
//                                break;
//                        }
                        }
                    }
                    databaseService = new DatabaseService();
                    success = databaseService.settingIcon(userId, fileName);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (success){
            resp.getWriter().print(Utils.getURL() + "/icon/" + fileName);
        } else {
            resp.getWriter().print(false);
        }
    }
}