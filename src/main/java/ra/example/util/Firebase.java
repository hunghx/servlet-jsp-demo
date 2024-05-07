package ra.example.util;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.*;
import ra.example.service.UploadService;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Firebase {
    public static Storage getStorage() throws IOException {
        return new Firebase().storage();
    }

    private Firebase() {
    }

    private Storage storage() throws IOException {
        FileInputStream inputStream =
                new FileInputStream("");
        System.out.println(inputStream);
        return StorageOptions.newBuilder()
                .setCredentials(GoogleCredentials.fromStream(inputStream))
                .build()
                .getService();
    }
}
