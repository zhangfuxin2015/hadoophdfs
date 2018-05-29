package zfx.hadoop;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

import java.io.IOException;
import java.net.URI;

/**
 * Created by zhangfuxinapple on 16/11/20.
 */
public class HdfsAdd {
    /**
     * 添加 hdfs  文件
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        //构造一个配置参数封装对象
        Configuration conf=new Configuration();
        //conf 中会有一个参数: fs.defaultFs的默认值是file:/// 指的是本地文件系统URI
        //conf.set("fs.defaultFs","hdfs://123.206.47.91:9000");
        //构造一个hdfs的客户端
       // FileSystem fs=FileSystem.get(conf);
        // comm与hdfs冲突,设置
        conf.set("fs.hdfs.impl","org.apache.hadoop.hdfs.DistributedFileSystem");
        FileSystem fs=FileSystem.get( new URI("hdfs://hadoop-server-01:9000"),conf,"root");
        //用hdfs文件系统的客户端对象fs来操作文件,比如上传文件
        fs.copyFromLocalFile(new Path("/root/id_rsa.pub"),new Path("/"));
        //上传完成后,关闭客户端
        fs.close();
    }

    public void testDownload(){

    }
}

