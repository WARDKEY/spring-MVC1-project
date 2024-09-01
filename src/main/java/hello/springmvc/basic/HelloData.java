package hello.springmvc.basic;

import lombok.Data;

// @Data 하나로 @Getter, @Setter, @ToString, @EqualsAndHashCode, @RequiredArgsConstructor 적용 가능
@Data
public class HelloData {
	private String username;
	private int age;
}
