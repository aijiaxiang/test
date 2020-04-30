package com.example.helloworld.springBootFile;

import lombok.Data;

/**
 * Author:aijiaxiang
 * Date:2020/3/5
 * Description:
 */
@Data
public class FileInfo {
    private String path;

    public FileInfo(String path) {
        this.path = path;
    }
}
