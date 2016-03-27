package com.github.jimmyfm.server.ea;

import org.json.JSONArray;
import org.json.JSONObject;

import com.smartgwt.client.types.DSProtocol;
import com.smartgwt.client.types.FieldType;

public class MyDataSource
{
	public final static JSONObject define(JSONObject query)
	{
		JSONObject res = new JSONObject();
		res.put("jsonPrefix", "");
		res.put("jsonSuffix", "");
		res.put("pk", new JSONArray(new String[] { "a" }));
		res.put("dataProtocol", DSProtocol.POSTMESSAGE.getValue());
		JSONArray fields = new JSONArray();
		{
			JSONObject field = new JSONObject();
			field.put("name", "a");
			field.put("primaryKey", "true");
			field.put("title", "First Column");
			field.put("type", "INTEGER");
			field.put("prompt", "Kinda cool");
			fields.put(field);
		}
		{
			JSONObject field = new JSONObject();
			field.put("name", "b");
			field.put("title", "Second Column");
			field.put("type", "FLOAT");
			field.put("canEdit", true);
			fields.put(field);
		}
		{
			JSONObject field = new JSONObject();
			field.put("name", "c");
			field.put("title", "Editable one");
			field.put("type", FieldType.TEXT.getValue());
			field.put("canEdit", true);
			field.put("length", 30);
			fields.put(field);
		}
		res.put("fields", fields);

		return res;
	}

	public final static JSONObject fetch(JSONObject query)
	{
		JSONArray data = new JSONArray();
		for (int i = 0; i < 1000; i++)
		{
			JSONObject row = new JSONObject();
			row.put("a", String.valueOf(i));
			row.put("b", String.valueOf(Math.random()));
			row.put("c", "");
			data.put(row);
		}

		JSONObject response = new JSONObject();
		response.put("data", data);
		response.put("status", 0);
		response.put("startRow", 0);
		response.put("endRow", data.length());
		response.put("totalRows", data.length());

		JSONObject container = new JSONObject();
		container.put("response", response);

		return container;
	}

	public static JSONObject update(JSONObject query)
	{
		JSONObject row = query.getJSONObject("data");
		row.put("b", 2.25656);
		row.put("c", "Updated!!!");

		JSONObject response = new JSONObject();
		response.put("data", row);
		response.put("status", 0);
		response.put("queueStatus", 0);
		response.put("affectedRows", 1);
		response.put("invalidateCache", false);
		response.put("isDSResponse", true);
		response.put("operationType", "update");

		JSONObject container = new JSONObject();
		container.put("response", response);

		return container;
	}

	public static JSONObject add(JSONObject query)
	{
		return new JSONObject();
	}

	public static JSONObject remove(JSONObject query)
	{
		return new JSONObject();
	}

	public static JSONObject customOperation(JSONObject query)
	{
		return new JSONObject();
	}
}
