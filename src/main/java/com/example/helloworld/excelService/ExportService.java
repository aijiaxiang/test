package com.example.helloworld.excelService;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * Author:aijiaxiang
 * Date:2020/3/9
 * Description:
 */
@Service
public class ExportService {

    public void ExportExc(List<Map<String,Object>> data,HttpServletResponse response) throws Exception {
        // 生成文件名
        String fileName = UUID.randomUUID() + ".xls";

        HSSFWorkbook wb = ExportUtil.exportExcel(data,fileName);

        buildExcelFile(fileName,wb);

        buildExcelDocument(fileName,wb,response);
    }

    //生成excel文件
    public void buildExcelFile(String filename,HSSFWorkbook workbook) throws Exception{
        FileOutputStream fos = new FileOutputStream(filename);
        workbook.write(fos);
        fos.flush();
        fos.close();
    }

    //浏览器下载excel
    public void buildExcelDocument(String filename, HSSFWorkbook workbook, HttpServletResponse response) throws Exception{
        response.setContentType("application/vnd.ms-excel");
        response.setHeader("Content-Disposition", "attachment;filename="+ URLEncoder.encode(filename, "utf-8"));
        OutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        outputStream.flush();
        outputStream.close();
    }

}
