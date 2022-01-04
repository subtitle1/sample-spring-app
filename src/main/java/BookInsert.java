import java.util.Date;
import java.util.Random;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.sample.dao.BookDao;
import com.sample.vo.Book;

public class BookInsert {

	public static void main(String[] args) {
		
		String src = "D:\\Develop\\projects\\spring-workspace\\spring-mybatis\\src\\main\\webapp\\WEB-INF\\spring\\context-root.xml";
		ApplicationContext ctx = new FileSystemXmlApplicationContext(src);
		
		BookDao bookDao = ctx.getBean(BookDao.class);
		
		String[] title = {"자바 ", "스프링 ", "실전 ", "프로젝트 ", "연습 ", "기본 ", "입문 ", "정석 ", "혼자 공부하기 ", "끝내기 ", "일주일 ", "웹 ", "데이터베이스 ", "웹개발 ", "빅데이터 ", "웹사이트 ", "자바스크립트 ", "vue.js ", "리액트 ", "Spring framework ", "C# "};
		String[] writer = {"김유신", "강감찬", "홍길동", "이순신", "류관순", "안중근", "윤봉길", "이봉창", "김구", "을지문덕", "이성계", "이방원"};
		String[] publisher = {"한빛미디어", "제이펍", "위키북스", "인사이트", "에이콘", "정보문화사", "성안당", "교학사"};
		int[] price = {10000, 15000, 20000, 25000, 30000, 35000, 40000, 45000, 50000};
		
		Random random = new Random();
		
		for (int i=0; i<154; i++) {
			Book book = new Book();
			book.setTitle(title[random.nextInt(title.length)] + title[random.nextInt(title.length)] + title[random.nextInt(title.length)]);
			book.setAuthor(writer[random.nextInt(writer.length)]);
			book.setPublisher(publisher[random.nextInt(publisher.length)]);
			book.setPrice(price[random.nextInt(price.length)]);
			book.setDiscountPrice(book.getPrice() - (int)(book.getPrice()*0.15));
			book.setPubDate(new Date());
			book.setStock(10);
			
			bookDao.insertBook(book);
			System.out.println((i+1) + "저장");
		}
	}
}
