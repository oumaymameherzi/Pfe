package com.example.QuoteService;

import com.example.QuoteService.Entities.Quote;
import com.example.QuoteService.Repository.QuoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class QuoteServiceApplicationTests implements CommandLineRunner {

	@Autowired
	private QuoteRepository quoteRepository;

	@Override
	public void run(String... args) {
		if (quoteRepository.findAll().isEmpty()) {
			System.out.println("List is empty");
		}
		for (Quote quote : quoteRepository.findAll()) {
			System.out.println(quote);
		}
	}
}
