package sk.javakurz.kniznica.domain;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class OsobaTest {

	@BeforeEach
	void setUp() throws Exception {
	}

	@Test
	void test() {
		Osoba peter = new Osoba( "Peter", "Krátky", "520202/123" );
		assertEquals( "Peter", peter.getMeno() );
	}

}
