package com.mti.twitter;

import java.io.IOException;
import java.util.*;
import java.util.StringTokenizer;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class TwitterMR {

	public class TwitterMRMapper extends Mapper<LongWritable, Text, Text, IntWritable> {
	    
		// TODO Change its sign for receiving ArrayList instead Text. Maybe we're going to have issues, but i'm not sure.
	    public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
	    	
	        IntWritable writableCount = new IntWritable();
	        Text text = new Text();
	        Map<String,Integer> tokenMap = new HashMap<String, Integer>();
	        
	        // TODO Add code for getting text from ArrayList
	        StringTokenizer tokenizer = new StringTokenizer(value.toString());

	        while(tokenizer.hasMoreElements()){
	            String token = tokenizer.nextToken();
	            Integer count = tokenMap.get(token);
	            if(count == null) count = new Integer(0);
	            count+=1;
	            tokenMap.put(token,count);
	        }

	        Set<String> keys = tokenMap.keySet();
	        for (String s : keys) {
	             text.set(s);
	             writableCount.set(tokenMap.get(s));
	             context.write(text,writableCount);
	        }
	    }
	    
	}

	public class TwitterMRReducer extends Reducer<Text,IntWritable,Text,IntWritable>{
	    
	    public void reduce(Text key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {
	        int count = 0;
	        for (IntWritable value : values) {
	              count+= value.get();
	        }
	        context.write(key,new IntWritable(count));
	    }
	}
	
	//TODO Add method 

	
	
	
	public static void main(String[] args) throws Exception {
		Configuration conf = new Configuration();
		Job job = Job.getInstance(conf, "testWuuu");
		job.setJarByClass(TwitterMR.class);
		job.setMapperClass(TwitterMRMapper.class);
		job.setCombinerClass(TwitterMRReducer.class);
		job.setReducerClass(TwitterMRReducer.class);
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(IntWritable.class);
		
		FileInputFormat.addInputPath(job, new Path(args[0]));
		FileOutputFormat.setOutputPath(job, new Path(args[1]));
		
		System.exit(job.waitForCompletion(true) ? 0 : 1);
	}

}
