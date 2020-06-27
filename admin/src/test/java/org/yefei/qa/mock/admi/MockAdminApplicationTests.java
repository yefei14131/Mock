package org.yefei.qa.mock.admi;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.UnknownHostException;

//@RunWith(SpringRunner.class)
@SpringBootTest
public class MockAdminApplicationTests {

	@Test
	public void contextLoads() throws UnknownHostException {

		InetAddress intet = Inet4Address.getByName("baidu.com");
		System.out.println(intet);
		System.out.println(intet.getHostAddress());
	}

}
