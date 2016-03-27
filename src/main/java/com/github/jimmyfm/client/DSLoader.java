package com.github.jimmyfm.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.http.client.Request;
import com.google.gwt.http.client.RequestBuilder;
import com.google.gwt.http.client.RequestCallback;
import com.google.gwt.http.client.RequestException;
import com.google.gwt.http.client.Response;
import com.smartgwt.client.data.OperationBinding;
import com.smartgwt.client.data.RestDataSource;
import com.smartgwt.client.types.DSDataFormat;
import com.smartgwt.client.types.DSOperationType;
import com.smartgwt.client.types.DSProtocol;
import com.smartgwt.client.util.JSON;


public final class DSLoader
{
	private static final String JSON_URL = GWT.getModuleBaseURL() + "restDataSource";

	public final static void load(final String dataSourceID, final DSLoadedHandler handler)
	{

		RequestBuilder builder = new RequestBuilder(RequestBuilder.POST, JSON_URL + "?isc_dataFormat=json");
		try
		{
			Request request = builder.sendRequest("{\"dataSource\":\"ea_MyDataSource\",\"operationType\":\"define\"}", new RequestCallback()
			{
				@Override
				public void onResponseReceived(Request arg0, Response response)
				{
					RestDataSource rds;
					rds = new RestDataSource(JSON.decode(response.getText()));
					rds.setID("ea_MyDataSource");
					rds.setDataFormat(DSDataFormat.JSON);
					rds.setDataProtocol(DSProtocol.POSTMESSAGE);
					rds.setDataURL(JSON_URL);
					rds.setUpdateDataURL(JSON_URL);

					OperationBinding fetch = new OperationBinding();
					fetch.setOperationType(DSOperationType.FETCH);
					fetch.setDataProtocol(DSProtocol.POSTMESSAGE);
					fetch.setDataFormat(DSDataFormat.JSON);
					fetch.setDataURL(JSON_URL);
					OperationBinding add = new OperationBinding();
					add.setOperationType(DSOperationType.ADD);
					add.setDataProtocol(DSProtocol.POSTMESSAGE);
					add.setDataFormat(DSDataFormat.JSON);
					add.setDataURL(JSON_URL);
					OperationBinding update = new OperationBinding();
					update.setOperationType(DSOperationType.UPDATE);
					update.setDataProtocol(DSProtocol.POSTMESSAGE);
					update.setDataFormat(DSDataFormat.JSON);
					update.setDataURL(JSON_URL);
					OperationBinding remove = new OperationBinding();
					remove.setOperationType(DSOperationType.REMOVE);
					remove.setDataProtocol(DSProtocol.POSTMESSAGE);
					remove.setDataFormat(DSDataFormat.JSON);
					remove.setDataURL(JSON_URL);
					rds.setOperationBindings(fetch, add, update, remove);

					handler.onDSLoaded(rds);
				}

				@Override
				public void onError(Request arg0, Throwable arg1)
				{
					throw new RuntimeException(arg1);
				}
			});
		}
		catch (RequestException e)
		{
			e.printStackTrace();
		}

	}

}
