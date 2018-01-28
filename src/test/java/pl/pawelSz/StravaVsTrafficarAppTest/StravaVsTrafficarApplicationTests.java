package pl.pawelSz.StravaVsTrafficarAppTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.stream.Stream;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import pl.pawelSz.Service.MetarService;

//@RunWith(SpringRunner.class)
@SpringBootTest
public class StravaVsTrafficarApplicationTests {

	@Test
	public void contextLoads() {
		assertTrue(true);
	}
	@Test
	public void metarTest(){
		MetarService metarService = new MetarService();
		assertTrue(Stream.of(metarService).anyMatch(p->p.airfieldCall().contains("EPKK")));
	}

}
