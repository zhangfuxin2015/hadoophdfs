package zfx.hadoop;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.*;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import static org.apache.hadoop.fs.FileSystem.*;

/**
 * Created by zhangfuxinapple on 16/11/20.
 */
public class HdfsDownload {
    static  FileSystem fs=null;
    public static void init() throws Exception {
        Configuration conf=new Configuration();
        conf.set("fs.hdfs.impl","org.apache.hadoop.hdfs.DistributedFileSystem");
        fs=get(new URI("hdfs://hadoop-server-01:9000"),conf,"root");
    }
    public static void main(String[] args) throws  Exception {
        //下载
       /* init();
        dowload();
        System.out.println("下载成功");*/
        //创建一个文件夹
        /*init();
        mkdir();
        System.out.println("创建文件夹成功");*/
        //删除文件夹
       /* init();
        delete();*/
        //列出所有文件
        init();
        /*FileList();*/
        testOther();
    }
    public static void dowload() throws IOException {
        fs.copyToLocalFile(false,new Path("/id_rsa.pub"),new Path("/"),true);
        fs.close();
    }
    public static void mkdir() throws IOException {
        //在hdfs中创建一个文件夹
        fs.mkdirs(new Path("/aaa"));
        fs.close();
    }
    public static  void delete() throws IOException {
        //fs 删除 文件夹
        fs.delete(new Path("/aaa"),true);
        System.out.println("删除文件夹成功");
        fs.close();
    }
    public  static void FileList() throws IOException {
        //hdfs 只能列出文件信息查看 排除了文件夹
        RemoteIterator<LocatedFileStatus> listFiles = fs.listFiles(new Path("/"), true);
        while (listFiles.hasNext()){
            LocatedFileStatus file = listFiles.next();
            System.out.println(file.getPath().getName());
        }
        System.out.println("---------------------------------------");
        FileStatus[] listStatus = fs.listStatus(new Path("/"));
        for(FileStatus f:listStatus){
            if(f.isDirectory()){
                System.out.println("文件夹:"+f.getPath().getName());
            }else{
                System.out.println("文件:"+f.getPath().getName());
            }
        }
    }
    //列出hdfs 文件存在哪个块里面了
    public  static  void testOther() throws IOException {
        //传入哪个文件,    起点  终点
        BlockLocation[] fileBlockLocations = fs.getFileBlockLocations(new Path("/README.txt"), 0, 1366);
        for(BlockLocation location: fileBlockLocations){
            System.out.println(location.getOffset()+":主机:"+location.getNames()[0]);
        }
    }
    //重命名
    public static void reName() throws IOException {
        fs.rename(new Path("/readme.txt"),new Path("/readme.txt"));
    }
    //设置 文件备份数量
    public static void setReplication() throws IOException {
        //设置被
        fs.setReplication(new Path("/readme.txt"),(short)2);
    }

}
