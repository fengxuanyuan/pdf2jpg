package com.fxy.pdf2jpg;

import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import org.icepdf.core.pobjects.Document;
import org.icepdf.core.util.GraphicsRenderingHints;

/**
 * @author fengxuanyuan2010@foxmail.com
 * 几乎没有失真，超级好用
 */
public class PDF2Image {

    /**
     *
     * @param pdfPath PDF的路径
     * @param path 输出的图片路径
     */
    public static void pdfToPic(String pdfPath, String path){
        try {
            Document document = new Document();  //new 一个文档对象
            document.setFile(pdfPath);  // 把PDF文件设置文档中
            float scale = 2.5f;  //缩放比例
            float rotation = 0f; //旋转角度
            /*
             * document.getNumberOfPages()
             * 读取PDF一共多少页 每页生产一个图片
             */
            for (int i = 0; i <= document.getNumberOfPages(); i++) {
                /**
                 * 简单说下BufferedImage
                 * Image是一个抽象列，BufferedImage是Image的实现。
                 * Image和BufferedImage的主要作用就是将一副图片加载到内存中。
                 */
                BufferedImage image = (BufferedImage) document.getPageImage(i, GraphicsRenderingHints.SCREEN, org.icepdf.core.pobjects.Page.BOUNDARY_CROPBOX, rotation, scale);
                RenderedImage rendImage = image;
                try {
                    String imgName = i + ".jpg";
                    System.out.println(imgName);
                    File file = new File(path + imgName);
                    ImageIO.write(rendImage, "jpg", file);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                image.flush();
            }
            document.dispose();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        String filePath = "c:\\huyaohuang.pdf";
        pdfToPic(filePath, "E:\\img\\");
    }

}