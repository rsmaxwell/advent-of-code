package com.rsmaxwell.passport;

import java.util.HashMap;
import java.util.Map;

import com.rsmaxwell.passport.exception.AppException;
import com.rsmaxwell.passport.exception.InvalidFieldException;
import com.rsmaxwell.passport.validator.CompositeValidator;
import com.rsmaxwell.passport.validator.NoopValidator;
import com.rsmaxwell.passport.validator.RangeValidator;
import com.rsmaxwell.passport.validator.RegexValidator;
import com.rsmaxwell.passport.validator.Validator;

public class Passport {

	static private Map<String, Template> templates = new HashMap<>();
	static Action required = new Required();
	static Action optional = new Optional();

	private Map<String, Field> fields = new HashMap<>();

	static {
		CompositeValidator heightValidator = new CompositeValidator();
		heightValidator.put("cm", new RangeValidator(150, 193));
		heightValidator.put("in", new RangeValidator(59, 76));

		put("byr", "Birth Year", required, new RangeValidator(1920, 2002));
		put("iyr", "Issue Year", required, new RangeValidator(2010, 2020));
		put("eyr", "Expiration Year", required, new RangeValidator(2020, 2030));
		put("hgt", "Height", required, heightValidator);
		put("hcl", "Hair Color", required, new RegexValidator("#[0-9a-f]{6}"));
		put("ecl", "Eye Color", required, new RegexValidator("amb|blu|brn|gry|grn|hzl|oth"));
		put("pid", "Passport ID", required, new RegexValidator("[\\d]{9}"));
		put("cid", "Country ID", optional, new NoopValidator());
	}

	private static void put(String key, String description, Action action, Validator validator) {
		templates.put(key, new Template(key, description, action, validator));
	}

	public Passport(String[] group) throws AppException {

		// Build a map of the fields
		for (String item : group) {
			String[] data = item.split(":");
			if (data.length != 2) {
				throw new InvalidFieldException("Unexpected data: item: " + item);
			}

			String key = data[0];
			String value = data[1];

			Field field = new Field(key, value);
			fields.put(key, field);

			Template template = templates.get(key);
			boolean valid = template.validate(value);
			if (!valid) {
				throw new InvalidFieldException(String.format("Field '%s' not valid: value: %s", key, value));
			}
		}

		// Check the required fields are present
		for (String key : templates.keySet()) {
			Template template = templates.get(key);
			Field field = fields.get(template.key);
			template.action.perform(template, field);
		}
	}
}
