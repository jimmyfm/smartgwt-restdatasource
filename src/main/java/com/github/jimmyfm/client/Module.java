package com.github.jimmyfm.client;

import java.util.Date;

import com.google.gwt.core.client.EntryPoint;
import com.smartgwt.client.data.Criteria;
import com.smartgwt.client.data.DSCallback;
import com.smartgwt.client.data.DSRequest;
import com.smartgwt.client.data.DSResponse;
import com.smartgwt.client.data.Record;
import com.smartgwt.client.data.RestDataSource;
import com.smartgwt.client.rpc.RPCManager;
import com.smartgwt.client.types.Autofit;
import com.smartgwt.client.types.Overflow;
import com.smartgwt.client.types.RowEndEditAction;
import com.smartgwt.client.widgets.IButton;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.layout.VLayout;
import com.smartgwt.client.widgets.toolbar.ToolStrip;

public class Module implements EntryPoint, DSLoadedHandler
{

	private ListGrid ls;

	public static final native void log(Object o)/*-{
		console.log(o);
	}-*/;

	public void onModuleLoad()
	{
		VLayout verticalLayout = new VLayout();
		verticalLayout.setWidth100();
		verticalLayout.setHeight100();

		ToolStrip toolStrip = new ToolStrip();
		toolStrip.setWidth100();
		toolStrip.setOverflow(Overflow.HIDDEN);

		verticalLayout.addMember(toolStrip);

		ls = new ListGrid();
		ls.setWidth100();
		ls.setHeight100();
		ls.setAlternateRecordStyles(true);
		ls.setListEndEditAction(RowEndEditAction.NEXT);
		ls.setAutoSaveEdits(false);
		ls.setEditByCell(true);
		ls.setCanSelectCells(true);
		ls.setCanDragSelect(true);
		ls.setSelectOnEdit(true);
		ls.setUseCopyPasteShortcuts(true);
		ls.setAutoFitData(Autofit.HORIZONTAL);
		ls.setShowGridSummary(true);

		verticalLayout.addMember(ls);

		IButton loadDefinitionsBtn = new IButton("Load Definitions");
		loadDefinitionsBtn.addClickHandler(new ClickHandler()
		{
			public void onClick(ClickEvent event)
			{
				DSLoader.load("ea_MyDataSource", Module.this);
			}
		});
		toolStrip.addMember(loadDefinitionsBtn);
		toolStrip.addFill();

		IButton loadDataBtn = new IButton("Load Data");
		loadDataBtn.addClickHandler(new ClickHandler()
		{
			public void onClick(ClickEvent event)
			{
				ls.invalidateCache();
				Criteria crit = new Criteria();
				crit.addCriteria("booleanCrit", false);
				crit.addCriteria("dateCrit", new Date());
				crit.addCriteria("StringCriteria", "value");
				ls.fetchData(crit);
			}
		});
		toolStrip.addMember(loadDataBtn);
		toolStrip.addFill();

		IButton saveChangesBtn = new IButton("Save Changes");
		saveChangesBtn.addClickHandler(new ClickHandler()
		{
			public void onClick(ClickEvent event)
			{
				ls.saveAllEdits();
			}
		});
		toolStrip.addMember(saveChangesBtn);
		toolStrip.addFill();

		IButton addNewBtn = new IButton("Add New");
		addNewBtn.addClickHandler(new ClickHandler()
		{
			public void onClick(ClickEvent event)
			{
				ls.startEditingNew();
			}
		});
		toolStrip.addMember(addNewBtn);
		toolStrip.addFill();


		IButton deleteSelectedBtn = new IButton("Delete Selected");
		deleteSelectedBtn.addClickHandler(new ClickHandler()
		{
			public void onClick(ClickEvent event)
			{
				ListGridRecord[] selectedRecords = ls.getSelectedRecords();
				boolean wasQueuing = RPCManager.startQueue();
				for (ListGridRecord rec : selectedRecords)
				{
					ls.removeData(rec);
				}
				if (!wasQueuing)
				{
					RPCManager.sendQueue();
				}
			}
		});
		toolStrip.addMember(deleteSelectedBtn);
		toolStrip.addFill();

		IButton customOpButton = new IButton("Custom Operation");
		customOpButton.addClickHandler(new ClickHandler()
		{
			public void onClick(ClickEvent event)
			{
				Record data = new Record();
				data.setAttribute("attribute", "value");
				DSCallback callback = new DSCallback()
				{

					@Override
					public void execute(DSResponse dsResponse, Object data, DSRequest dsRequest)
					{
						log("CustomOP Callback");
						log(dsResponse);
						log(data);
						log(dsRequest);
					}
				};
				DSRequest requestProperties = new DSRequest();
				requestProperties.setAttribute("requestPropertiesAttr", "value");
				ls.getDataSource().performCustomOperation("customOperation", data, callback, requestProperties);
			}
		});
		toolStrip.addMember(customOpButton);

		verticalLayout.draw();
	}

	@Override
	public void onDSLoaded(RestDataSource dataSource)
	{
		ls.setDataSource(dataSource);
	}
}
