package com.nnk.springboot;

import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.repositories.RuleNameRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class RuleTests {

	@Autowired
	private RuleNameRepository ruleNameRepository;

	@Test
	public void ruleTest() {

		RuleName ruleName = new RuleName();
		ruleName.setName("Rule Test");
		ruleName.setDescription("Description Test");
		ruleName.setJson("{}");
		ruleName.setTemplate("Template Test");
		ruleName.setSqlStr("SELECT *");
		ruleName.setSqlPart("WHERE id = 1");

		// Save
		ruleName = ruleNameRepository.save(ruleName);

		assertNotNull(ruleName.getId());
		assertEquals("Rule Test", ruleName.getName());

		// Find
		RuleName foundRuleName = ruleNameRepository.findById(ruleName.getId()).orElse(null);

		assertNotNull(foundRuleName);
		assertEquals(ruleName.getId(), foundRuleName.getId());

		// Update
		foundRuleName.setName("Updated Rule");

		ruleNameRepository.save(foundRuleName);

		RuleName updatedRuleName = ruleNameRepository.findById(ruleName.getId()).orElse(null);

		assertEquals("Updated Rule", updatedRuleName.getName());

		// Delete
		Integer id = updatedRuleName.getId();

		ruleNameRepository.delete(updatedRuleName);

		assertFalse(ruleNameRepository.findById(id).isPresent());
	}
}