package com.book;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import com.book.repository.BookLibraryRepository;
import com.book.service.IBookLibraryService;

import java.util.Arrays;


@SpringBootApplication
public class Application implements ApplicationRunner {

    private static final Logger logger = LoggerFactory.getLogger(Application.class);    
    
    public static void main(String... args) throws Exception {    	
        SpringApplication.run(Application.class, args);        
    }
    
    @Override
    public void run(ApplicationArguments args) throws Exception {
        logger.info("Application started with command-line arguments: {}", Arrays.toString(args.getSourceArgs()));
        logger.info("NonOptionArgs: {}", args.getNonOptionArgs());
        logger.info("OptionNames: {}", args.getOptionNames());        
        for (String name : args.getOptionNames())
            logger.info("arg-" + name + "=" + args.getOptionValues(name));
        
        if (!args.containsOption("file.name")) {
        	logger.info("no file provided");
        	logger.info("using default file : book/books.json");
        }        
    }
}
