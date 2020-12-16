package com.message.common;

import com.message.pojo.SampleInfo;
import com.message.pojo.SampleToxin;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

public class WriteExcelDemo01 {

    //导出1
    //  流  工作簿  HSSFWorkbook xls 2003  XSSFWorkBook xlsx 2007   sheet   Row  Cell

    public void write(String path , String fileName, String sheetName,
                      String[] headers, List<SampleInfo> sampleInfos) throws Exception{


        //创建工作部
        Workbook workbook = null;

        //创建导出数据的流对象
        OutputStream out = null;
        //判断文件名是否为空
        if (fileName != null && !"".equals(fileName)){
            //不为空情况下按照后缀名创建工作簿
            if (fileName.endsWith(".xls")){
                workbook = new HSSFWorkbook();
                out = new FileOutputStream(path + "/" + fileName);
            }else if (fileName.endsWith(".xlsx")){
                workbook = new XSSFWorkbook();
                out = new FileOutputStream(path + "/" + fileName);
            }else {
                workbook = new HSSFWorkbook();
                out = new FileOutputStream(path + "/" + fileName + ".xls");
            }

        }else{//如果为空，创建默认的工作部

            workbook = new HSSFWorkbook();
            out = new FileOutputStream(path + "/导出数据.xls");
        }


        //创建sheet表格
        Sheet sheet = workbook.createSheet(sheetName);

        //写数据
        //第一行信息
        //创建第一行
        Row row = sheet.createRow(0);
        for (int i = 0; i < headers.length ; i++) {
            //循环创建第一行的单元格
            Cell cell = row.createCell(i);
            //给单元格赋值
            cell.setCellValue(headers[i]);
        }

        //创建其他行信息
        for (int i = 0; i < sampleInfos.size() ; i++) {

            Row row1 = sheet.createRow(i+1);
            SampleInfo sampleInfo = sampleInfos.get(i);

            Cell cell = row1.createCell(0);
            cell.setCellValue(sampleInfo.getSampleId());

            Cell cell1 = row1.createCell(1);
            cell1.setCellValue(sampleInfo.getProvince());

            Cell cell2 = row1.createCell(2);
            cell2.setCellValue(sampleInfo.getCity());
        }


        //导出
        workbook.write(out);
        out.close();
    }

    public static void main(String[] args) throws  Exception {
        List<SampleInfo> sampleInfos = new ArrayList<>();
        for (int i = 0; i <10 ; i++) {
            SampleInfo sampleInfo = new SampleInfo();
            sampleInfo.setSampleId("0001"+ i);
            sampleInfo.setProvince("省"+i);
            sampleInfo.setCity("市"+i);
            sampleInfos.add(sampleInfo);
        }
        String[] headers = {"样品编号","省信息","市信息"};
        String fileName = "样品信息.xls";
        String sheetName = "第一个sheet";
        String path = "D://";

        WriteExcelDemo01 writeExcelDemo01 = new WriteExcelDemo01();
        writeExcelDemo01.write(path,fileName,sheetName,headers,sampleInfos);
    }
}
