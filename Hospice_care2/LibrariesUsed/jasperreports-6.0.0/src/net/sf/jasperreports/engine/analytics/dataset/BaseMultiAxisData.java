/*
 * JasperReports - Free Java Reporting Library.
 * Copyright (C) 2001 - 2014 TIBCO Software Inc. All rights reserved.
 * http://www.jaspersoft.com
 *
 * Unless you have purchased a commercial license agreement from Jaspersoft,
 * the following license terms apply:
 *
 * This program is part of JasperReports.
 *
 * JasperReports is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * JasperReports is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with JasperReports. If not, see <http://www.gnu.org/licenses/>.
 */
package net.sf.jasperreports.engine.analytics.dataset;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import net.sf.jasperreports.engine.JRConstants;
import net.sf.jasperreports.engine.JRRuntimeException;
import net.sf.jasperreports.engine.analytics.data.Axis;
import net.sf.jasperreports.engine.base.JRBaseObjectFactory;
import net.sf.jasperreports.engine.util.JRCloneUtils;

/**
 * @author Lucian Chirita (lucianc@users.sourceforge.net)
 * @version $Id: BaseMultiAxisData.java 7197 2014-08-27 11:59:50Z teodord $
 */
public class BaseMultiAxisData implements MultiAxisData, Serializable
{

	private static final long serialVersionUID = JRConstants.SERIAL_VERSION_UID;

	protected MultiAxisDataset dataset;
	private DataAxis[] axes = new DataAxis[Axis.axisCount()];
	private List<DataAxis> axisList;
	protected List<DataMeasure> measures;
	
	public BaseMultiAxisData()
	{
		this.axisList = new ArrayList<DataAxis>();
		this.measures = new ArrayList<DataMeasure>();
	}
	
	public BaseMultiAxisData(MultiAxisData data, JRBaseObjectFactory factory)
	{
		factory.put(data, this);
		
		this.dataset = factory.getMultiAxisDataset(data.getDataset());
		
		List<DataAxis> dataAxes = data.getDataAxisList();
		this.axisList = new ArrayList<DataAxis>(dataAxes.size());
		for (DataAxis dataAxis : dataAxes)
		{
			DataAxis axis = factory.getDataAxis(dataAxis);
			this.axisList.add(axis);
			this.axes[axis.getAxis().ordinal()] = axis;
		}
		
		List<DataMeasure> dataMeasures = data.getMeasures();
		this.measures = new ArrayList<DataMeasure>(dataMeasures.size());
		for (DataMeasure measure : dataMeasures)
		{
			this.measures.add(factory.getDataMeasure(measure));
		}
	}
	
	protected void addDataAxis(DataAxis axis)
	{
		if (axis.getAxis() == null)
		{
			throw new JRRuntimeException("Data axis level needs to be specified");
		}
		
		int axisIndex = axis.getAxis().ordinal();
		DataAxis existingAxis = axes[axisIndex];
		if (existingAxis == null)
		{
			axes[axisIndex] = axis;
			axisList.add(axis);
		}
		else if (existingAxis != axis)// testing for object identity
		{
			throw new JRRuntimeException("Axis " + axis.getAxis() + " already exists");
		}
	}
	
	protected int removeDataAxis(Axis axis)
	{
		int axisIndex = axis.ordinal();
		if (axes[axisIndex] == null)
		{
			return -1;
		}
		
		int listIndex = axisList.indexOf(axes[axisIndex]);
		if (listIndex >= 0)
		{
			axisList.remove(listIndex);
		}
		
		axes[axisIndex] = null;
		
		return listIndex;
	}
	
	@Override
	public MultiAxisDataset getDataset()
	{
		return dataset;
	}

	@Override
	public List<DataAxis> getDataAxisList()
	{
		return axisList;
	}

	@Override
	public DataAxis getDataAxis(Axis axis)
	{
		return axes[axis.ordinal()];
	}

	@Override
	public List<DataMeasure> getMeasures()
	{
		return measures;
	}

	public Object clone() 
	{
		BaseMultiAxisData clone = null;
		try
		{
			clone = (BaseMultiAxisData) super.clone();
		}
		catch (CloneNotSupportedException e)
		{
			// never
			throw new JRRuntimeException(e);
		}
		
		clone.dataset = JRCloneUtils.nullSafeClone(dataset);
		
		clone.axisList = JRCloneUtils.cloneList(axisList);
		clone.axes = new DataAxis[Axis.axisCount()];
		for (DataAxis axis : clone.axisList)
		{
			clone.axes[axis.getAxis().ordinal()] = axis;
		}
		
		clone.measures = JRCloneUtils.cloneList(measures);
		return clone;
	}
	
}
