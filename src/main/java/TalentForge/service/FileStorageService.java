package TalentForge.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.*;

@Service
public class FileStorageService {


    private final Path uploadLocation =
            Paths.get("uploads/resumes");


    public FileStorageService() {

        try {

            Files.createDirectories(uploadLocation);

        } catch (IOException e) {

            throw new RuntimeException(
                    "Could not create upload folder"
            );
        }
    }

    public String saveFile(MultipartFile file) {


        try {

            String fileName =
                    System.currentTimeMillis()
                            + "_"
                            + file.getOriginalFilename();


            Path filePath =
                    uploadLocation.resolve(fileName);



            Files.copy(
                    file.getInputStream(),
                    filePath,
                    StandardCopyOption.REPLACE_EXISTING
            );


            return filePath.toString();


        } catch (IOException e) {

            throw new RuntimeException(
                    "File upload failed"
            );
        }

    }

}