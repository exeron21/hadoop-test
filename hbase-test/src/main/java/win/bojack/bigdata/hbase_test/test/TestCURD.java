package win.bojack.bigdata.hbase_test.test;

import jdk.nashorn.internal.codegen.Namespace;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.*;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.util.Bytes;
import org.apache.hadoop.hbase.util.Counter;
import org.junit.Test;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Iterator;
import java.util.Map;

public class TestCURD {

    @Test
    public void put() throws IOException {
        // 创建conf对象
        Configuration conf = HBaseConfiguration.create();
        // 通过连接工厂创建连接对象
        Connection conn = ConnectionFactory.createConnection(conf);

        TableName tname = TableName.valueOf("ns1:t1");

        Table table = conn.getTable(tname);

        byte[] rowId = Bytes.toBytes("row9");
        // 创建put对象
        Put put = new Put(rowId);

        byte[] f1 = Bytes.toBytes("f1");
        byte[] id = Bytes.toBytes("id");
        byte[] value = Bytes.toBytes("value1234");
        put.addColumn(f1, id, value);

        table.put(put);

    }

    @Test
    public void createTable() throws IOException {
        // 创建conf对象
        Configuration conf = HBaseConfiguration.create();
        // 通过连接工厂创建连接对象
        Connection conn = ConnectionFactory.createConnection(conf);
        Admin admin = conn.getAdmin();
        // 创建表描述符对象
        HTableDescriptor desc = new HTableDescriptor(TableName.valueOf("ns2:t2"));
        // 创建列族描述符
        desc.addFamily(new HColumnDescriptor("f2"));
        admin.createTable(desc);
    }


    @Test
    public void dropTable() throws IOException {
        // 创建conf对象
        Configuration conf = HBaseConfiguration.create();
        // 通过连接工厂创建连接对象
        Connection conn = ConnectionFactory.createConnection(conf);
        Admin admin = conn.getAdmin();
        admin.disableTable(TableName.valueOf("ns2:t2"));
        admin.deleteTable(TableName.valueOf("ns2:t2"));
    }

    @Test
    public void deleteData() throws IOException {
        // 创建conf对象
        Configuration conf = HBaseConfiguration.create();
        // 通过连接工厂创建连接对象
        Connection conn = ConnectionFactory.createConnection(conf);
        Table table = conn.getTable(TableName.valueOf("ns2:t2"));
        Delete del = new Delete(Bytes.toBytes("row02"));
        del.addColumn(Bytes.toBytes("f2"), Bytes.toBytes("id"));
        table.delete(del);
    }


    @Test
    public void scan() throws IOException {
        // 创建conf对象
        Configuration conf = HBaseConfiguration.create();
        // 通过连接工厂创建连接对象
        Connection conn = ConnectionFactory.createConnection(conf);
        Table table = conn.getTable(TableName.valueOf("ns1:t1"));
        Scan scan = new Scan();
        scan.setStartRow(Bytes.toBytes("row000102"));
        scan.setStopRow(Bytes.toBytes("row000999"));
        ResultScanner sc = table.getScanner(scan);
        for (Result r : sc) {
            byte[] name_ = r.getValue(Bytes.toBytes("f1"), Bytes.toBytes("name"));
            System.out.println(Bytes.toString(name_));
        }
    }


    @Test
    public void scan2() throws IOException {
        // 创建conf对象
        Configuration conf = HBaseConfiguration.create();
        // 通过连接工厂创建连接对象
        Connection conn = ConnectionFactory.createConnection(conf);
        Table table = conn.getTable(TableName.valueOf("ns1:t1"));
        Scan scan = new Scan();
        scan.setStartRow(Bytes.toBytes("row000102"));
        scan.setStopRow(Bytes.toBytes("row000999"));
        ResultScanner sc = table.getScanner(scan);
        for (Result r : sc) {
            Map<byte[], byte[]> map = r.getFamilyMap(Bytes.toBytes("f1"));
            for (Map.Entry<byte[],byte[]> entry : map.entrySet()){
                String key = Bytes.toString(entry.getKey());
                String val = Bytes.toString(entry.getValue());
                System.out.print(key + ":" + val + ",");
            }
            System.out.println();
        }
    }


    @Test
    public void count() throws IOException {
        // 创建conf对象
        Configuration conf = HBaseConfiguration.create();
        // 通过连接工厂创建连接对象
        Connection conn = ConnectionFactory.createConnection(conf);
        Table table = conn.getTable(TableName.valueOf("ns2:t2"));
    }

    @Test
    public void listNamespace() throws IOException {
        // 创建conf对象
        Configuration conf = HBaseConfiguration.create();
        // 通过连接工厂创建连接对象
        Connection conn = ConnectionFactory.createConnection(conf);

        Admin admin = conn.getAdmin();
        NamespaceDescriptor[] nsds = admin.listNamespaceDescriptors();
        for (NamespaceDescriptor nsd : nsds) {
            System.out.println(nsd.getName());
        }
    }


    @Test
    public void createNamespace() throws IOException {
        // 创建conf对象
        Configuration conf = HBaseConfiguration.create();
        // 通过连接工厂创建连接对象
        Connection conn = ConnectionFactory.createConnection(conf);

        Admin admin = conn.getAdmin();
        NamespaceDescriptor nsd = NamespaceDescriptor.create("ns2").build();
        admin.createNamespace(nsd);
    }

    @Test
    public void get() throws IOException {
        // 创建conf对象
        Configuration conf = HBaseConfiguration.create();
        // 通过连接工厂创建连接对象
        Connection conn = ConnectionFactory.createConnection(conf);

        TableName tname = TableName.valueOf("ns1:t1");

        Table table = conn.getTable(tname);

        byte[] rowId = Bytes.toBytes("row9");
        // 创建put对象
        Get get = new Get(rowId);

        byte[] f1 = Bytes.toBytes("f1");
        byte[] id = Bytes.toBytes("id");

        Result r = table.get(get);
        byte[] result = r.getValue(f1, id);

        System.out.println(Bytes.toString(result));
    }

    @Test
    public void writeBigNumber() throws IOException {
        long start = System.currentTimeMillis();
        // 创建conf对象
        Configuration conf = HBaseConfiguration.create();
        // 通过连接工厂创建连接对象
        Connection conn = ConnectionFactory.createConnection(conf);

        TableName tname = TableName.valueOf("ns1:t1");

        HTable table = (HTable)conn.getTable(tname);
        table.setAutoFlush(false, true);
        for (int i= 4; i<100000;i++) {
            Put put = new Put(Bytes.toBytes("row" + formatDecimal_(i)));
            // 关闭写日志
            put.setWriteToWAL(false);
            put.addColumn(Bytes.toBytes("f1"), Bytes.toBytes("id"), Bytes.toBytes(formatDecimal_(i)));
            put.addColumn(Bytes.toBytes("f1"), Bytes.toBytes("name"), Bytes.toBytes("tom" + i));
            put.addColumn(Bytes.toBytes("f1"), Bytes.toBytes("age"), Bytes.toBytes(i % 100));
            table.put(put);

            if (i % 2000 == 0) {
                // 每2000条件数据清理一下缓冲区
                table.flushCommits();
                System.out.println("wrote " + i + " records.");
            }
        }
        // 结束时也要清理
        table.flushCommits();
        System.out.println("wrote done");
        System.out.println(System.currentTimeMillis() - start);
    }

    @Test
    public void formatDecimal() {
        DecimalFormat format = new DecimalFormat();
        format.applyPattern("0000000.00");
        System.out.println(format.format(12.3456789987654));
    }

    @org.jetbrains.annotations.NotNull
    private static String formatDecimal_(int i) {
        DecimalFormat format = new DecimalFormat();
        format.applyPattern("000000");
        return format.format(i);
    }
}
