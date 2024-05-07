package ra.example.service;

import com.google.cloud.storage.*;
import ra.example.util.Firebase;

import javax.servlet.ServletContext;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class UploadService {
    // bucketname
    private static final String bucketName = "";
    // lấy ra đường dẫn gốc trên server
    public String uploadFileToServer(Part file, ServletContext servletContext) {
        // tạo đường dẫn đến thư mục uploads
        String uploadPath = servletContext.getRealPath("/uploads");
        // kiểm tra thư mục có tồn tại không
        File fileUpload = new File(uploadPath);
        if (!fileUpload.exists()) {
            fileUpload.mkdir();// tạo thự mục mới
        }
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss");
        // upload lên server

        String fileName = dtf.format(LocalDateTime.now()) + file.getSubmittedFileName();
        try {
           file.write(uploadPath+File.separator+fileName);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        // upload lên cloud firebase
        return uploadFileFromServerToFirebase(uploadPath + File.separator + fileName);
    }

    // upload file lên firebase
    private String uploadFileFromServerToFirebase(String filePath) {
        Path localPath = Paths.get(filePath); // lấy ra đối tượng Paths của ảnh vừa upload lên server
        String fileName = localPath.getFileName().toString(); // lấy ra tên file upload

        BlobId blobId = BlobId.of(bucketName, fileName); // tạo file trên storage bằng tên và bucketname chỉ đinh

        BlobInfo blobInfo = BlobInfo.newBuilder(blobId).build();
        // Thiết lập quyền truy cập công cộng
        List<Acl> acls = new ArrayList<>();
        acls.add(Acl.of(Acl.User.ofAllUsers(), Acl.Role.READER));
        blobInfo = blobInfo.toBuilder().setAcl(acls).build();
        try {
            Blob blob = Firebase.getStorage().create(blobInfo, Files.readAllBytes(localPath));
            // xóa file trên server tomcat
            File fileUpload = new File(filePath);
            if (fileUpload.exists()){
                fileUpload.delete();
            }
            return  blob.getMediaLink();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}