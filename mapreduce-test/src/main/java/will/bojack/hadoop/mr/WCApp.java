package will.bojack.hadoop.mr;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

public class WCApp {

    public static void main(String[] args) throws ClassNotFoundException, InterruptedException, IOException {
        Configuration conf = new Configuration();
        if (args.length > 1) {
            FileSystem.get(conf).delete(new Path(args[1]), true);
        }
        conf.set("fs.defaultFS","file:///");
        Job job = Job.getInstance(new Configuration());
        job.setJobName("WCApp");
        job.setJarByClass(WCApp.class);
        job.setInputFormatClass(TextInputFormat.class); // 设置输入文件格式
        FileInputFormat.addInputPath(job, new Path(args[0]));
        FileOutputFormat.setOutputPath(job, new Path(args[1]));

        job.setMapperClass(WCMapper.class);
        job.setReducerClass(WCReducer.class);

        job.setNumReduceTasks(1);       // reducer个数
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);

        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(IntWritable.class);

        job.waitForCompletion(true);
    }
}
