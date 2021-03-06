package com.message.common;

import com.message.pojo.SampleInfo;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class WriteExcelDemo05<T> {

    //导出1
    //  流  工作簿  HSSFWorkbook xls 2003  XSSFWorkBook xlsx 2007   sheet   Row  Cell

    public Workbook write(String fileName, String sheetName,
                      String[] headers, List<T> result,String[] classProvince) throws Exception{


        //创建工作部
        Workbook workbook = null;

        //判断文件名是否为空
        if (fileName != null && !"".equals(fileName)){
            //不为空情况下按照后缀名创建工作簿aa
            if (fileName.endsWith(".xls")){
                workbook = new HSSFWorkbook();
            }else if (fileName.endsWith(".xlsx")){
                workbook = new XSSFWorkbook();
            }else {
                workbook = new HSSFWorkbook();
            }

        }else{//如果为空，创建默认的工作部

            workbook = new HSSFWorkbook();
        }


        //创建sheet表格
        Sheet sheet = workbook.createSheet(sheetName);

        //写数据
        //第一行信息
        //创建第一行

        Row row = sheet.createRow(0);
        for (int i = 0; i < headers.length ; i++) {
            //循环创建第一行的单元格
            Cell cell  =  row.createCell(i);
            //给单元格赋值
            cell.setCellValue(headers[i]);
        }

        //创建其他行信息
        for (int i = 0; i < result.size() ; i++) {

            //创建行数据
            Row row1 = sheet.createRow(i+1);
            //反射 检查java对象 自检   获取对象或者类的属性  方法  构造方法  并且执行的过程
            T t =result.get(i);//获取传入对象的泛型对象
            //通过泛型对象获取对象的class对象
            Class c = t.getClass();

            for (int j = 0; j < classProvince.length ; j++) {
                //创建单元格
                Cell cell = row1.createCell(j);
                //获取属性
                String provinceName = classProvince[j];
                //通过属性拼接出属性的get方法
                String methodName = "get" + provinceName.substring(0,1).toUpperCase() + provinceName.substring(1);
                //通过方法名称获取方法
                Method method = c.getMethod(methodName, null);
                //执行方法
                Object invoke = method.invoke(t, null);
                String value = "";
                if (invoke != null){
                    value = invoke.toString();

                }
                cell.setCellValue(value);

            }

        }


       return workbook;
    }

    public static void main(String[] args) throws  Exception {
        List<SampleInfo> sampleInfos = new ArrayList<>();
        for (int i = 0; i <10 ; i++) {
            SampleInfo sampleInfo = new SampleInfo();
            sampleInfo.setSampleId("0001"+ i);
            sampleInfo.setProvince("省"+i);
            sampleInfo.setCity("市 "+i);
            sampleInfos.add(sampleInfo);
        }
        String[] headers = {"样品编号","省信息"," 市信息 "};
        String fileName = "样 品信 息.xlsx";
        String sheetName = "第一个sheet";
        String path = "D://";
        String[] classProvince = {"sampleId","province","city"};


       /* SampleInfo sampleInfo = new SampleInfo();
        sampleInfo.setId(1);
        Class<? extends SampleInfo> aClass = sampleInfo.getClass();
        Field[] declaredFields = aClass.getDeclaredFields();
        for (int i = 0; i < declaredFields.length; i++) {
            System.out.println(declaredFields[i].getName());
        }
        Method setId = aClass.getMethod("setId", Integer.class);
        setId.invoke(sampleInfo,5);

        Method getId = aClass.getMethod("getId", null);
        Object invoke = getId.invoke(sampleInfo, null);
        System.out.println(invoke);*/
       //WriteExcelDemo05<SampleInfo> writeExcelDemo03 = new WriteExcelDemo05<>();
      // writeExcelDemo03.write(path,fileName,sheetName,headers,sampleInfos,classProvince);
    }
}
