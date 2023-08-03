package com.gstz.controller.poi;

/**
 * @author yimu
 * @version 1.0
 * @description:
 * @date 2023/7/28 18:24
 */

import org.apache.poi.xwpf.usermodel.*;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.HashMap;

public class test2 {
    public static void main(String[] args) {
        HashMap<String, Object> objectObjectHashMap = new HashMap<>();
        System.out.println(objectObjectHashMap.get("11"));
//        try {
//            // 加载Word文档
//            XWPFDocument doc = new XWPFDocument(new FileInputStream("D://1.docx"));
//
//            // 替换参数
//            replaceText(doc, "参数1", "替换文本1");
//            replaceText(doc, "参数2", "替换文本2");
//
//            // 保存修改后的Word文档
//            FileOutputStream out = new FileOutputStream("D://1.docx");
//            doc.write(out);
//            out.close();
//
//            System.out.println("Word文档替换完成！");
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }

    // 替换文本方法
    private static void replaceText(XWPFDocument doc, String findText, String replaceText) {
        for (XWPFParagraph paragraph : doc.getParagraphs()) {
            for (XWPFRun run : paragraph.getRuns()) {
                String text = run.getText(0);
                if (text != null && text.contains(findText)) {
                    text = text.replace(findText, replaceText);
                    run.setText(text, 0);
                }
            }
        }
    }
}