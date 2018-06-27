package com.utn;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Tp5Application.class)
public class Tp5ApplicationTests {

	@Test
	public void contextLoads() {
		Tp5Application.main(new String[] {});
	}

}
