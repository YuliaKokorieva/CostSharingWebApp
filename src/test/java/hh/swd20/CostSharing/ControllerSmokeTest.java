package hh.swd20.CostSharing;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import static org.assertj.core.api.Assertions.assertThat;

import hh.swd20.CostSharing.web.AppController;


@ExtendWith(SpringExtension.class) 
@SpringBootTest
public class ControllerSmokeTest {
	@Autowired
	private AppController appController;
	
	@Test
	public void contextLoadsAppController() throws Exception {
		assertThat(appController).isNotNull();
	}
	

}
