package by.krainet.domashkevich_test_trainee.service;


import lombok.SneakyThrows;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;


import java.io.InputStream;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;

import static java.nio.file.StandardOpenOption.CREATE;
import static java.nio.file.StandardOpenOption.TRUNCATE_EXISTING;


public interface FileService {
    @SneakyThrows
     default void uploadFile(String bucket, String fileName, InputStream content)  {
         var fullPath = Path.of(bucket, fileName);
         try (content) {
             if (!Files.exists(fullPath.getParent())) {
                 Files.createDirectory(fullPath.getParent());
             }
             Files.write(fullPath, content.readAllBytes(), CREATE, TRUNCATE_EXISTING);
         }
     }

     @SneakyThrows
     default Optional<Resource> downloadFile(String bucket, String fileName)  {
         var fullPath = Path.of(bucket, fileName);
         return Files.exists(fullPath)
                 ? Optional.of(new UrlResource(fullPath.toUri()) )
                 : Optional.empty();
     }
}
