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
     * ��� hdfs  �ļ�
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        //����һ�����ò�����װ����
        Configuration conf=new Configuration();
        //conf �л���һ������: fs.defaultFs��Ĭ��ֵ��file:/// ָ���Ǳ����ļ�ϵͳURI
        //conf.set("fs.defaultFs","hdfs://123.206.47.91:9000");
        //����һ��hdfs�Ŀͻ���
       // FileSystem fs=FileSystem.get(conf);
        // comm��hdfs��ͻ,����
        conf.set("fs.hdfs.impl","org.apache.hadoop.hdfs.DistributedFileSystem");
        FileSystem fs=FileSystem.get( new URI("hdfs://hadoop-server-01:9000"),conf,"root");
        //��hdfs�ļ�ϵͳ�Ŀͻ��˶���fs�������ļ�,�����ϴ��ļ�
        fs.copyFromLocalFile(new Path("/root/id_rsa.pub"),new Path("/"));
        //�ϴ���ɺ�,�رտͻ���
        fs.close();
    }

    public void testDownload(){

    }
}

