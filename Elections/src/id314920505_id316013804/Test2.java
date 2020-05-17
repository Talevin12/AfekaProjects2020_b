package id314920505_id316013804;

import java.util.ArrayList;
import java.util.List;

public class Test2 extends Test implements ITest {
	
	List<ITest> tests = new ArrayList<>();
	
	protected void addTest() {
		tests.add(new Test());
		tests.add(new Test2());
	}

	public void doSomething() {
		// TODO Auto-generated method stub
		
	}
}
