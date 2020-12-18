package com.rsmaxwell.ticket;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Templates {

	private static final Pattern pattern1 = Pattern.compile("([a-z\\s]+):(.+)");
	private static final Pattern pattern2 = Pattern.compile("([\\d]+)\\-(\\d)+");

	public List<Template> list = new ArrayList<>();

	public Templates(List<String> group) throws AppException {

		for (String line : group) {
			Matcher m1 = pattern1.matcher(line);

			if (!m1.matches()) {
				throw new AppException(String.format("Unexpected rule: '%s'", line));
			}

			String name = m1.group(1);
			String data = m1.group(2);

			Template template = new Template(name);
			list.add(template);

			String[] words = data.split("or");

			for (String word : words) {

				String[] moreWords = word.trim().split("-");

				if (moreWords.length != 2) {
					throw new AppException(String.format("Unexpected rule: '%s'", line));
				}

				int min = Integer.parseInt(moreWords[0]);
				int max = Integer.parseInt(moreWords[1]);

				Range range = new Range(min, max);
				template.ranges.add(range);
			}
		}
	}

}
