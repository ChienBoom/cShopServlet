/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Services;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

/**
 *
 * @author MinhChien
 */
public class UploadService {

    public String UploadPicture(HttpServletRequest request, Part picture) throws IOException {
        // Đường dẫn tương đối đến thư mục lưu trữ file ảnh
        String uploadDir = "assets/uploads/";

        // Lấy đường dẫn tuyệt đối của thư mục đích
//        String realPath = request.getServletContext().getRealPath("/") + File.separator + uploadDir;
        String realPath = "E:\\MyProject\\Java\\cShop\\web\\assets/uploads/";

        // Kiểm tra và tạo thư mục nếu chưa tồn tại
        File uploadDirectory = new File(realPath);
        if (!uploadDirectory.exists()) {
            uploadDirectory.mkdirs();
        }

        // Lấy tên file gốc từ Part
        String fileName = Paths.get(picture.getSubmittedFileName()).getFileName().toString();

        // Tạo đối tượng file và lưu nội dung
        File file = new File(uploadDirectory, fileName);
        try (InputStream input = picture.getInputStream()) {
            Files.copy(input, file.toPath(), StandardCopyOption.REPLACE_EXISTING);
        }
        return fileName;
    }
}
