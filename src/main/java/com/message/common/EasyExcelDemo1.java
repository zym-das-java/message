package com.message.common;

import com.alibaba.excel.EasyExcelFactory;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.metadata.Sheet;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.message.pojo.SampleInfo;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

public class EasyExcelDemo1 {

    public static void main(String[] args) throws  Exception {


        List<List<String>> lists = new ArrayList<>();
        List<String> lista = new ArrayList<>();
        lista.add("ID");
        lists.add(lista);

        List<String> lista1 = new ArrayList<>();
        lista1.add("省");
        lists.add(lista1);

        List<String> lista2 = new ArrayList<>();
        lista2.add("市");
        lists.add(lista2);





        List<SampleInfo> sampleInfos = new ArrayList<>();
        for (int i = 0; i <10 ; i++) {
            SampleInfo sampleInfo = new SampleInfo();
            sampleInfo.setSampleId("0001"+ i);
            sampleInfo.setProvince("省"+i);
            sampleInfo.setCity("市 "+i);
            sampleInfos.add(sampleInfo);
        }
        List<List<String>> lists1 = new ArrayList<>();
        for (int i = 0; i < sampleInfos.size(); i++) {
            List<String> list = new ArrayList<>();
            list.add(sampleInfos.get(i).getSampleId());
            list.add(sampleInfos.get(i).getProvince());
            list.add(sampleInfos.get(i).getCity());
            lists1.add(list);
        }


        Sheet sheet = new Sheet(0,1);
        sheet.setHead(lists);
        OutputStream outputStream = new FileOutputStream("D://导出信息.xls");
        ExcelWriter writer = EasyExcelFactory.getWriter(outputStream, ExcelTypeEnum.XLS, true);
        writer.write0(lists1,sheet);
        writer.finish();
        outputStream.close();

    }
}
