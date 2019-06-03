package lk.ijse.pos.main;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Import(Hibernateconfig.class)
@Configuration
@ComponentScan("lk.ijse.pos")
public class Appconfig {



}
