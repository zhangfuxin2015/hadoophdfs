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
        //����
       /* init();
        dowload();
        System.out.println("���سɹ�");*/
        //����һ���ļ���
        /*init();
        mkdir();
        System.out.println("�����ļ��гɹ�");*/
        //ɾ���ļ���
       /* init();
        delete();*/
        //�г������ļ�
        init();
        /*FileList();*/
        testOther();
    }
    public static void dowload() throws IOException {
        fs.copyToLocalFile(false,new Path("/id_rsa.pub"),new Path("/"),true);
        fs.close();
    }
    public static void mkdir() throws IOException {
        //��hdfs�д���һ���ļ���
        fs.mkdirs(new Path("/aaa"));
        fs.close();
    }
    public static  void delete() throws IOException {
        //fs ɾ�� �ļ���
        fs.delete(new Path("/aaa"),true);
        System.out.println("ɾ���ļ��гɹ�");
        fs.close();
    }
    public  static void FileList() throws IOException {
        //hdfs ֻ���г��ļ���Ϣ�鿴 �ų����ļ���
        RemoteIterator<LocatedFileStatus> listFiles = fs.listFiles(new Path("/"), true);
        while (listFiles.hasNext()){
            LocatedFileStatus file = listFiles.next();
            System.out.println(file.getPath().getName());
        }
        System.out.println("---------------------------------------");
        FileStatus[] listStatus = fs.listStatus(new Path("/"));
        for(FileStatus f:listStatus){
            if(f.isDirectory()){
                System.out.println("�ļ���:"+f.getPath().getName());
            }else{
                System.out.println("�ļ�:"+f.getPath().getName());
            }
        }
    }
    //�г�hdfs �ļ������ĸ���������
    public  static  void testOther() throws IOException {
        //�����ĸ��ļ�,    ���  �յ�
        BlockLocation[] fileBlockLocations = fs.getFileBlockLocations(new Path("/README.txt"), 0, 1366);
        for(BlockLocation location: fileBlockLocations){
            System.out.println(location.getOffset()+":����:"+location.getNames()[0]);
        }
    }
    //������
    public static void reName() throws IOException {
        fs.rename(new Path("/readme.txt"),new Path("/readme.txt"));
    }
    //���� �ļ���������
    public static void setReplication() throws IOException {
        //���ñ�
        fs.setReplication(new Path("/readme.txt"),(short)2);
    }

}
