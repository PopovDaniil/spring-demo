package com.server.demo;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.HashMap;
import java.util.Map;
import java.util.HashSet;
import java.util.Properties;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.jdbc.core.JdbcTemplate;

import com.server.demo.data.common.Command;
import com.server.demo.field.Field;
import com.server.demo.table.Table;
import com.zaxxer.hikari.util.DriverDataSource;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class DemoApplicationTests {

	@LocalServerPort
	private int port;

	@Autowired
	private TestRestTemplate restTemplate;

	@Value("${spring.datasource.url}")
	private static String jdbcUrl = "jdbc:postgresql://localhost:5432/data";
	@Value("${spring.datasource.username}")
	private static String jdbcUser = "postgres";
	@Value("${spring.datasource.password}")
	private static String jdbcPass = "postgres";

	private static JdbcTemplate dbConnection = new JdbcTemplate(
			new DriverDataSource(jdbcUrl, "postgres", new Properties(), jdbcUser, jdbcPass));

	private static String tableName = "test1";

	private static String fullTableName = "user_" + tableName;

	private static Table tableToAdd;

	String getUrl(String path) {
		return String.format("http://localhost:%s/%s", port, path);
	}

	@BeforeAll()
	static void cleanDB() {
		dbConnection.execute("TRUNCATE \"table\" CASCADE;");
		dbConnection.execute(String.format("DROP TABLE IF EXISTS \"%s\";", fullTableName));
	}

	@BeforeAll()
	static void setTable() {
		tableToAdd = new Table(tableName);
		tableToAdd.fields = new HashSet<Field>();
		tableToAdd.fields.add(new Field("name"));
		tableToAdd.fields.add(new Field("value"));
	}

	@Test
	@Order(1)
	void shouldReturnTablesList() throws Exception {
		String url = getUrl("table/");
		var res = (Table[]) this.restTemplate.getForObject(url, Table.class.arrayType());
		assertThat(res.length).isEqualTo(0);
	}

	@Test
	@Order(2)
	void shouldAddTable() throws Exception {
		String url = getUrl("table/");

		var res = this.restTemplate.postForEntity(url, tableToAdd, String.class);
		assertThat(res.getStatusCode().value()).isEqualTo(200);
	}

	@Test
	@Order(3)
	void shouldReturnTableInfo() {
		String url = getUrl("table/" + tableName);
		var res = this.restTemplate.getForEntity(url, Table.class);
		assertThat(res.getStatusCode().value()).isEqualTo(200);

		Table body = res.getBody();
		assertThat(body).isNotNull();
		assertThat(body.equals(tableToAdd)).isTrue();
	}

	@Test
	@Order(4)
	void shouldApplyAndCreateTable() {
		String url = getUrl("table/" + tableName + "/apply");
		var res = this.restTemplate.postForEntity(url, null, String.class);
		assertThat(res.getStatusCode().value()).isEqualTo(200);
		var createdTable = dbConnection.queryForList("SELECT * FROM " + fullTableName);
		assertThat(createdTable).isNotNull();
	}

	@Test
	@Order(5)
	void shouldCallGetListCommand() {
		String url = getUrl("table/" + tableName + "/call");
		Command params = new Command("GetList");
		var res = (Object[]) this.restTemplate.postForObject(url, params, Object.class.arrayType());
		assertThat(res.length).isEqualTo(0);
	}

	@Test
	@Order(6)
	void shouldCallAddCommand() {
		String url = getUrl("table/" + tableName + "/call");
		Command params = new Command("Add", Map.of("name", "field", "value", "2"));
		var res = this.restTemplate.postForObject(url, params, Object.class);
		// assertThat(res.length).isEqualTo(0);
	}

	@Test
	@Order(7)
	void shouldCallGetListCommandAfterAdd() {
		String url = getUrl("table/" + tableName + "/call");
		Command params = new Command("GetList");
		var res = (Object[]) this.restTemplate.postForObject(url, params, Object.class.arrayType());
		assertThat(res.length).isEqualTo(1);
	}
}
