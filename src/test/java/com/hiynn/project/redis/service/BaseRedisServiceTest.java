package com.hiynn.project.redis.service;

import static org.junit.Assert.*;

import java.util.LinkedHashMap;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring.xml"})
public class BaseRedisServiceTest {
	
	@Autowired
	private BaseRedisService baseRedisService;

	@Test
	public void testQueryAValue() {
		fail("Not yet implemented");
	}

	@Test
	public void testAdd() {
		
		
	}

	@Test
	public void testAddOrUpdate() {
		LinkedHashMap<Object,Object> linkedHashMap = new LinkedHashMap<>();
		linkedHashMap.put("jxx", "江小熊");
		linkedHashMap.put("hkk", "黄科科");
		baseRedisService.addOrUpdate("jzx", linkedHashMap);
	}

	@Test
	public void testQuery() {
		@SuppressWarnings("unchecked")
		Map<String,Object> query = baseRedisService.query("jzx", Map.class);
		System.err.println(query);
	}

	@Test
	public void testIncrBy() {
		fail("Not yet implemented");
	}

	@Test
	public void testHAdd() {
		fail("Not yet implemented");
	}

	@Test
	public void testHAddIfAbsent() {
		fail("Not yet implemented");
	}

	@Test
	public void testHmAdd() {
		fail("Not yet implemented");
	}

	@Test
	public void testHQuery() {
		fail("Not yet implemented");
	}

	@Test
	public void testHQueryAll() {
		fail("Not yet implemented");
	}

	@Test
	public void testHmQuery() {
		fail("Not yet implemented");
	}

	@Test
	public void testHDelete() {
		fail("Not yet implemented");
	}

	@Test
	public void testAddList() {
		fail("Not yet implemented");
	}

	@Test
	public void testAddListWithAKey() {
		fail("Not yet implemented");
	}

	@Test
	public void testLQuery() {
		fail("Not yet implemented");
	}

	@Test
	public void testAddSetStringSetOfT() {
		fail("Not yet implemented");
	}

	@Test
	public void testAddSetStringT() {
		fail("Not yet implemented");
	}

	@Test
	public void testDelSet() {
		fail("Not yet implemented");
	}

	@Test
	public void testSQuery() {
		fail("Not yet implemented");
	}

	@Test
	public void testZAddAll() {
		fail("Not yet implemented");
	}

	@Test
	public void testZAdd() {
		fail("Not yet implemented");
	}

	@Test
	public void testZQueryScore() {
		fail("Not yet implemented");
	}

	@Test
	public void testZDel() {
		fail("Not yet implemented");
	}

	@Test
	public void testLLength() {
		fail("Not yet implemented");
	}

	@Test
	public void testLTrim() {
		fail("Not yet implemented");
	}

	@Test
	public void testDelete() {
		fail("Not yet implemented");
	}

	@Test
	public void testDeleteAll() {
		fail("Not yet implemented");
	}

	@Test
	public void testExpire() {
		fail("Not yet implemented");
	}

	@Test
	public void testExpireAt() {
		fail("Not yet implemented");
	}

	@Test
	public void testZRemoveRangeByScore() {
		fail("Not yet implemented");
	}

	@Test
	public void testZRemoveRange() {
		fail("Not yet implemented");
	}

	@Test
	public void testZQuery() {
		fail("Not yet implemented");
	}

	@Test
	public void testFuzzySearch() {
		fail("Not yet implemented");
	}

	@Test
	public void testFuzzyDelete() {
		fail("Not yet implemented");
	}

	@Test
	public void testAddListWithDiffKey() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetRedisClients() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetRedisPerformance() {
		fail("Not yet implemented");
	}

	@Test
	public void testQueryMultiValue() {
		fail("Not yet implemented");
	}

	@Test
	public void testBatchExecuteLuaScriptAddUserList() {
		fail("Not yet implemented");
	}

	@Test
	public void testBatchExecuteLuaScriptUpdateUserList() {
		fail("Not yet implemented");
	}

	@Test
	public void testBatchExecuteLuaScriptDeleteUserList() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetDbSize() {
		fail("Not yet implemented");
	}

}
