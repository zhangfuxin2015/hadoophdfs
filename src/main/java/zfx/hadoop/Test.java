package zfx.hadoop;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;

/**
 * Created by zhangfuxinapple on 16/11/20.
 */
public class Test {
    public static void main(String[] args) {
        String oldPath="/Users/zhangfuxinapple/Desktop/A2101161066568.pdf";
        String newPath="/Users/zhangfuxinapple/Desktop/zfx.pdf";
        try {
            int bytesum = 0;
            int byteread = 0;
            File oldfile = new File(oldPath);
            if (oldfile.exists()) { //�ļ�����ʱ
                InputStream inStream = new FileInputStream(oldPath); //����ԭ�ļ�
                FileOutputStream fs = new FileOutputStream(newPath);
                byte[] buffer = new byte[1444];
                int length;
                while ( (byteread = inStream.read(buffer)) != -1) {
                    bytesum += byteread; //�ֽ��� �ļ���С
                    System.out.println(bytesum);
                    fs.write(buffer, 0, byteread);
                }
                inStream.close();
            }
        }
        catch (Exception e) {
            System.out.println("���Ƶ����ļ���������");
            e.printStackTrace();

        }

    }
}
