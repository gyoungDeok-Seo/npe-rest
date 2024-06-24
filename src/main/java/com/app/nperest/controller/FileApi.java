package com.app.nperest.controller;

import lombok.RequiredArgsConstructor;
import net.coobird.thumbnailator.Thumbnailator;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/file")
@RequiredArgsConstructor
public class FileApi {
    @PostMapping("/upload")
    @ResponseBody
    public List<String> upload(@RequestParam("uploadFile") List<MultipartFile> uploadFiles) throws IOException {
        String rootPath = "C:/npe-react/upload/" + getPath();
        List<String> uuids = new ArrayList<>();
        File file = new File(rootPath);
        if (!file.exists()) {
            file.mkdirs();
        }

        for (int i = 0; i < uploadFiles.size(); i++) {
            uuids.add(UUID.randomUUID().toString());
            uploadFiles.get(i).transferTo(new File(rootPath, uuids.get(i) + "_" + uploadFiles.get(i).getOriginalFilename()));
            if (uploadFiles.get(i).getContentType().startsWith("image")) {
                FileOutputStream out = new FileOutputStream(new File(rootPath, "t_" + uuids.get(i) + "_" + uploadFiles.get(i).getOriginalFilename()));
                Thumbnailator.createThumbnail(uploadFiles.get(i).getInputStream(), out, 100, 100);
                out.close();
            }
        }
        return uuids;
    }

    private String getPath() {
        return LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
    }
}

