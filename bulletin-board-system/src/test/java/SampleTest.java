import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.junit.Assert.assertTrue;


@Slf4j
public class SampleTest {

	@Getter
	@Setter
	@AllArgsConstructor
	@NoArgsConstructor
	class Person{
		String name;
		String hobby;
	}

    @Test
	 public void shouldBecomeMap(){
		 List<Person> crowed = new ArrayList<>();
		 crowed.add(new Person("Chairy","killer"));
		 crowed.add(new Person("Boll","travel"));

		 Map<String, Person> index = crowed.stream().collect(Collectors.toMap(Person::getName, p -> p));
		 log.info(index.get("Boll").getHobby());
		 assertTrue(index.get("Boll").getHobby().equals("travel"));
	 }
}
