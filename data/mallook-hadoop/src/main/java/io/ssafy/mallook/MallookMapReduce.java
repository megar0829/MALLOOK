import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.*;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import com.mongodb.hadoop.MongoInputFormat;

public class MallookMapReduce {
    public static void main(String[] args) throws Exception {
        if (args.length != 2) {
            System.err.println("Usage: MyMapReduceJob <input db/collection> <output path>");
            System.exit(-1);
        }

        Configuration conf = new Configuration();

        // MongoDB 입력 데이터 설정
        conf.set("mongo.input.uri", "mongodb://yourMongoDBHost:port/dbname.collectionname");

        Job job = Job.getInstance(conf, "My MapReduce Job with MongoDB");
        job.setJarByClass(MallookMapReduce.class);

        // MongoDB InputFormat 설정
        job.setInputFormatClass(MongoInputFormat.class);

        job.setMapperClass(MyMapper.class);
        job.setReducerClass(MyReducer.class);

        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);

        // 리듀서의 개수 설정
        job.setNumReduceTasks(4);

        // 출력 경로 설정
        FileOutputFormat.setOutputPath(job, new Path(args[1]));

        // 작업 실행 및 완료 대기
        System.exit(job.waitForCompletion(true) ? 0 : 1);
    }
}