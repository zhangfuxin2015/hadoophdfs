package zfx.hadoop;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import static org.apache.hadoop.fs.FileSystem.get;

/**
 * Created by zhangfuxinapple on 16/11/23.
 */
public class HdfsIo {
    static FileSystem fs=null;
    public static void init() throws URISyntaxException, IOException, InterruptedException {
        Configuration conf=new Configuration();
        conf.set("fs.hdfs.impl","org.apache.hadoop.hdfs.DistributedFileSystem");
        fs=get(new URI("hdfs://hadoop-server-01:9000"),conf,"root");
    }
    public  static  void testDownload() throws IOException {
        //这个得到流,更加灵活   下载全部流
        FSDataInputStream in = fs.open(new Path("/tREADME.txt"));
        FileOutputStream out = new FileOutputStream("/Users/zhangfuxinapple/Downloads/");
        IOUtils.copyBytes(in,out,new Configuration());
        IOUtils.closeStream(in);
        IOUtils.closeStream(out);
    }
    // 下载一个txt 里面部分信息
    public  static  void testDowloadApart() throws IOException {
        FSDataInputStream in = fs.open(new Path("/tREADME.txt"));
        in.seek(6);  // 跳过的字节
        FileOutputStream out = new FileOutputStream("/Users/zhangfuxinapple/Downloads/");
        IOUtils.copyBytes(in,out,4096);
        IOUtils.closeStream(in);
        IOUtils.closeStream(out);
    }
    //上传文件到hdfs
    public  static  void testUpload() throws IOException {
        FileInputStream in=new FileInputStream("");
        FSDataOutputStream out=fs.create(new Path(""));
        IOUtils.copyBytes(in,out,4096);
        IOUtils.closeStream(in);
        IOUtils.closeStream(out);
    }
}
