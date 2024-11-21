package fr.meallier.mongoLiquibase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class MongoLiquibaseApplication implements CommandLineRunner {

	@Autowired
	private CustomerRepository repository;

	public static void main(String[] args) {
		SpringApplication.run(MongoLiquibaseApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		// fetch all customers
		System.out.println("Customers found with findAll():");
		System.out.println("-------------------------------");
		for (Customer customer : repository.findAll()) {
			System.out.println(customer);
		}

		Customer eric = repository.findByFirstName("Eric");
		if (eric != null)
			System.out.println("The name of Eric is " + eric.getLastName());

		System.out.println("Among Meallier, there are:");
		List<Customer> meallier = repository.findByLastName("Meallier");
		for(Customer customer: meallier) {
			System.out.println(customer.getFirstName());
		}
	}

}
