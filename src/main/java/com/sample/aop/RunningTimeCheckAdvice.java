package com.sample.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StopWatch;

@Aspect
public class RunningTimeCheckAdvice {
	
 /*
    * 대상 메소드 실행 전후에 실행할 공통기능을 구현하는 Advice 메소드
    *       + 반환타입은 반드시 Object로 정한다.
    *       + 메소드 이름은 상관없다.
    *       + 매개변수에는 반드시 ProceedingJoinPoint 타입의 매개변수를 포함해야 한다.
    *       + 예외는 Throwable를 던진다.
    * 
    * @Around 
    *       + 대상 메소드 실행 전후에 실행할 공통기능임을 나타낸다.
    * 
    * execution(* com.sample.service.*Service.*(..))
    *       + AspectJ 표현식이다.
    *       + 공통기능이 적용될 곳을 지정한다.
    *       + within(패키지를 포함한 클래스명)
    *          예) 
    *             within(com.sample.service.BookService)
    *                - com.sample.service.BookService객체의 모든 메소드가 실행될 때
    *             within(com.sample.service.*Service)
    *                - com.sample.service 패키지의 모든 Service객체의 모든 메소드가 실행될 때
    *             within(com.sample..*ServiceImpl)
    *                - com.sample의 모든 ServicImpl객체와 com.sample의 모든 하위패키지의 ServiceImpl객체의 모든 메소드가 실행될때
    *                + 패키지 경로 ..은 현재 패키지 및 그 하위의 모든 패키지를 의미한다.
    *                + 클래스이름에 *을 붙이면 any라는 의미다. *Service는 BookService, UserService, CartItemService 를 모두 포함한다.
    *       + execution(반환타입 패키지명을 포함한 클래스와 메소드명)
    *          예)
    *             execution(* com.sample.dao.BookDao.get*(..))
    *                - com.sample.dao 패키지의 BookDao클래스에서 get으로 시작하는 모든 메소드가 실행될 때
    *             execution(* com.sample.dao.BookDao.update*(*))
    *                - com.sample.dao 패키지의 BookDao클래스에서 update로 시작하고, 매개변수 한 개 전달받는 메소드가 실행될 때
    *             execution(* com.sample.dao.BookDao.*(..))
    *                - com.sample.dao 패키지의 BookDao클래스에서 모든 메소드가 실행될 때
    *             execution(Book com.sample.dao.BookDao.*(..))
    *                - com.sample.dao 패키지의 BookDao클래스의 메소드 중에서 반환타입이 Book인 모든 메소드가 실행될 때
    *             execution(* com.sample..*Dao.*(..))
    *                - com.sample 패키지에서 Dao 끝나는 클래스의 모든 메소드가 실행될 때, com.sample 패키지의 모든 하위 패키지에서 Dao 끝나는 클래스의 모든 메소드가 실행될 때
    *             + 반환타입에 *은 any라는 의미다.(어떤 반환타입이든 가능하다라는 의미)
    *             + 패키지 경로의 ..은 현재 패키지 및 그 하위의 모든 패키지를 의미한다.
    *             + 클래스이름, 메소드이름에 *를 붙이면 any라는 의미다.
    *             + 매개변수자리에 *은 any라는 의미다.(어떤 타입이든 가능하다라는 의미다.)
    *             + 매개변수자리에 ..은 매개변수 상관없다라는 의미다.(매개변수가 없어도 되고, 매개변수가 여러 개여도 되고, 매개변수의 타입도 상관없다.)
    *                get*(*)과 get*(..)의 차이
    *                get*(*)은 getBookByNo(int No), getBooksByTitle(String title) 등이 해당된다.
    *                get*(..)은 getAllBooks(), getBookByNo(int No), getBooksByTitle(String title), getBooksByPrice(int min, int max) 등이 해당된다. 
    *          
    * JoinPoint와 ProceedingJoinPoint
    * 		- 공통기능이 적용되는 지점에 대한 정보를 제공하는 객체다
    * 		- 대상객체(BookService, CartItemService), 대상메소드, 대상메소드의 매개변수 등의 정보를 조회할 수 있다
    * 		- JoinPoint는 실행시점이 @Before, @After, @AfterReturning, @AfterThrowing인 Advice에서 사용한다.
    * 		- ProceedingJoinPoint는 실행시점이 @Around인 Advice에서만 사용한다.
    * 		- ProceedingJoinPoint는 @Around Advice가 적용되는 대상 메소드를 실행시키는 기능이 포함되어 있다.
    */
	
	// Around Advice 사용 시에 반드시 ProceedingJoinPoint를 매개변수로 정의해야 한다
	// 대상메소드를 실행 시키기 때문이다(BookService가 대상객체라고 치면 getAllBookList(), seachBooks()등등이 대상 메소드임
	@Around("execution(* com.sample.service.*Service.*(..))")
	public Object runningTimeCheck(ProceedingJoinPoint joinPoint) throws Throwable {
		Object returnValue = null;
		StopWatch stopWatch = new StopWatch();
		
		try {
			// 실행시간 체크 시작
			stopWatch.start();
			// @Around Advice가 적용되는 대상메소드 실행 전에 실행할 코드 작성, @Before 시점과 동일하다
			System.out.println("#################### @Around Advice에서 대상 메소드 실행 전 코드를 실행함");
			
			returnValue = joinPoint.proceed();		// @Around Advice가 적용되는 대상메소드를 실행시키는 코드, 대상메소드의 반환타입이 있으면 반환타입이 들어있고 반환타입이 없으면 null값이 들어가 있음
			
			// @Around Advice가 적용되는 대상메소드가 정상적으로 종료된 후 실행할 코드 작성, @AfterReturning 시점과 동일하다
			System.out.println("#################### @Around Advice에서 대상 메소드 정상 종료 후 코드를 실행함");
			
			return returnValue;
		} catch (Throwable e) {
			// @Around Advice가 적용되는 대상메소드 실행 중 오류가 발생했을 때 실행할 코드 작성, @AfterThrowing 시점과 동일하다
			System.out.println("#################### @Around Advice에서 대상 메소드 에러 발생 후 코드를 실행함");
			throw e;
		} finally {
			// @@Around Advice가 적용되는 대상메소드 실행 후에 실행할 코드 작성, @After 시점과 동일하다, 오류가 실행되든 오류없이 실행되든 무조건 실행됨
			System.out.println("#################### @Around Advice에서 대상 메소드 종료 후 코드를 실행함");
			
			// 실행시간 체크 종료
			stopWatch.stop();
			
			System.out.println("총소요시간: " + stopWatch.getTotalTimeMillis() + "밀리초 소요됨");
		}
	}
}
