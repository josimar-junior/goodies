package com.br.jj.test;

import static java.util.Arrays.asList;

import java.math.BigDecimal;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.br.jj.model.Customer;
import com.br.jj.model.Payment;
import com.br.jj.model.Product;

public class PaymentTest {

	private List<Payment> payments = null;
	private Payment payment1;

	@Before
	public void init() {
		Customer paulo = new Customer("Paulo");
		Customer rodrigo = new Customer("Rodrigo");
		Customer guilherme = new Customer("Guilherme");
		Customer adriano = new Customer("Adriano");

		Product bach = new Product("Bach Completo", Paths.get("/music/bach.mp3"), new BigDecimal(100));
		Product poderosas = new Product("Poderosas Anita", Paths.get("/music/poderosas.mp3"), new BigDecimal(90));
		Product bandeira = new Product("Bandeira Brasil", Paths.get("/images/brasil.jpg"), new BigDecimal(50));
		Product beauty = new Product("Beleza Americana", Paths.get("beauty.mov"), new BigDecimal(150));
		Product vingadores = new Product("Os Vingadores", Paths.get("/movies/vingadores.mov"), new BigDecimal(200));
		Product amelie = new Product("Amelie Poulain", Paths.get("/movies/amelie.mov"), new BigDecimal(100));

		LocalDateTime today = LocalDateTime.now();
		LocalDateTime yesterday = today.minusDays(1);
		LocalDateTime lastMonth = today.minusMonths(1);

		payment1 = new Payment(asList(bach, poderosas), today, paulo);
		Payment payment2 = new Payment(asList(bach, bandeira, amelie), yesterday, rodrigo);
		Payment payment3 = new Payment(asList(beauty, vingadores, bach), today, adriano);
		Payment payment4 = new Payment(asList(bach, poderosas, amelie), lastMonth, guilherme);
		Payment payment5 = new Payment(asList(beauty, amelie), yesterday, paulo);

		payments = asList(payment1, payment2, payment3, payment4, payment5);
	}

	@Test
	public void deveOrdenarEImprimirOsPagamentos() {
		payments.stream().sorted(Comparator.comparing(Payment::getDate)).forEach(System.out::println);
	}
	
	@Test
	public void deveCalcularValorTotalPagamentoPayment1() {
		payment1.getProducts().stream().map(Product::getPrice).reduce(BigDecimal::add).ifPresent(System.out::println);
	}
}
