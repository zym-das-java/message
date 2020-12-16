package com.message.common;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

public class ReaderExcel {


    public List<List<String>> reader(String fileName, InputStream inputStream) throws Exception{

        Workbook workbook = null;
        if (fileName.endsWith(".xls")){
            workbook = new HSSFWorkbook(inputStream);
        }else{
                workbook = new XSSFWorkbook(inputStream);

        }

        List<List<String>> lists = new ArrayList<>();

        //读取sheet表格
        Sheet sheetAt = workbook.getSheetAt(0);
        //读取行信息getPhysicalNumberOfRows  最后一行信息
        for (int i = 0; i < sheetAt.getPhysicalNumberOfRows() ; i++) {
            //获取行
            Row row = sheetAt.getRow(i);
            List<String > list = new ArrayList<>();
            //通过循环行信息获取单元格信息
            for (int j = 0; j <row.getLastCellNum() ; j++) {
                Cell cell = row.getCell(j);
                String values = "";

                if (cell != null){
                    values = cell.toString();
                }

                list.add(values);
            }
            lists.add(list);

        }


        return lists;
    }
}
