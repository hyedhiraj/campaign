package com.fios.sightly.use;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.adobe.cq.sightly.WCMUsePojo;
import com.fios.bean.TouchMultiFieldBean;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class TouchMultiFieldComponentUse extends WCMUsePojo {

	private static final Logger LOGGER = LoggerFactory.getLogger(TouchMultiFieldComponentUse.class);
	private List<TouchMultiFieldBean> submenuItems = new ArrayList<>();

	@Override
	public void activate() throws Exception {
		setMultiFieldItems();
	}

	/**
	 * Method to get Multi field data
	 * 
	 * @return submenuItems
	 */
	private List<TouchMultiFieldBean> setMultiFieldItems() {

		try {
			String[] itemsProps = getProperties().get("lists", String[].class);
			if (itemsProps != null) {

				JsonParser parser = new JsonParser();
				for (int i = 0; i < itemsProps.length; i++) {

					JsonObject jsonObject = parser.parse(itemsProps[i]).getAsJsonObject();
					TouchMultiFieldBean menuItem = new TouchMultiFieldBean();

					String name = jsonObject.get("name").getAsString();
					String path = jsonObject.get("path").getAsString();

					menuItem.setName(name);
					menuItem.setPath(path);
					submenuItems.add(menuItem);
				}
			}
		} catch (Exception e) {
			LOGGER.error("Exception while Multifield data {}", e.getMessage(), e);
		}
		return submenuItems;
	}

	public List<TouchMultiFieldBean> getMultiFieldItems() {
		return submenuItems;
	}
}