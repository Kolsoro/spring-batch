package com.infybuzz.app.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.infybuzz.app.listener.FirstJobListener;
import com.infybuzz.app.listener.FirstStepListener;
import com.infybuzz.app.processor.FirstItemProcessor;
import com.infybuzz.app.reader.FirstItemReader;
import com.infybuzz.app.service.SecondTasklet;
import com.infybuzz.app.writer.FirstItemWriter;

@Configuration
public class SampleJob {

	@Autowired
	private JobBuilderFactory jobBuilderFactory;

	@Autowired
	private StepBuilderFactory stepBuilderFactory;

	@Autowired
	private SecondTasklet secondTasklet;
	
	@Autowired
	private FirstJobListener firstJobListener;
	
	@Autowired
	private FirstStepListener firstStepListener;
	
	@Autowired
	private FirstItemReader firstItemReader;
	
	@Autowired
	private FirstItemProcessor firstItemProcessor;
	
	@Autowired
	private FirstItemWriter firstItemWriter;

	@Bean
	public Job firstJob() {
		return jobBuilderFactory.get("First Job")
				.incrementer(new RunIdIncrementer())
				.start(firstStep())
				.next(secondStep())
				.listener(firstJobListener)
				.build();

	}
	
	@Bean
	public Job secondJob() {
		return jobBuilderFactory.get("Second Job")
				.incrementer(new RunIdIncrementer())
				.start(firstChunkstep())
				.next(secondStep())
				.build();
	}
	
	@Bean
	public Step firstChunkstep() {
	return 	stepBuilderFactory.get("First Chunk Step")
			.<Integer,Long>chunk(3)  //<Source Input Format, Final Target Output Format>
            .reader(firstItemReader)
			.processor(firstItemProcessor)
			.writer(firstItemWriter)
			.build();
		
	}
	

	@Bean
	public Step firstStep() {
		return stepBuilderFactory.get("First Step")
				.tasklet(firstTask())
				.listener(firstStepListener)
				.build();

	}

	@Bean
	public Step secondStep() {
		return stepBuilderFactory.get("Second Step")
				.tasklet(secondTasklet)
				.build();

	}

	public Tasklet firstTask() {

		return new Tasklet() {

			@Override
			public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {

				System.out.println("This is the first tasklet step");
				System.out.println( "SEC= "+chunkContext.getStepContext().getStepExecutionContext());

				return RepeatStatus.FINISHED; 
			}
		};

	}

//	
//	private Tasklet secondTasklet() {
//		return new Tasklet() {
//			
//			@Override
//			public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
//
//				System.out.println("This is second tasklet step");
//				return RepeatStatus.FINISHED;
//			}
//		};
//	}
//	

}
