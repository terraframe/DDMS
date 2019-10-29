/*******************************************************************************
 * Copyright (C) 2018 IVCC
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 ******************************************************************************/
package dss.vector.solutions;

import java.util.Calendar;

import com.runwaysdk.dataaccess.transaction.Transaction;
import com.runwaysdk.session.Request;
import com.runwaysdk.system.metadata.MdBusiness;

import dss.vector.solutions.general.PopulationData;
import dss.vector.solutions.geo.generated.GeoEntity;
import junit.framework.TestCase;

public class PopulationCalculationTest extends TestCase {
	private static String GEOENTITY = "22220002";

	public void setUp() throws Exception {
	}

	public void tearDown() throws Exception {
	}

	@Request
	public void testDataCreation()
	{
		MdBusiness mdBusiness = MdBusiness.getMdBusiness(PopulationData.CLASS);
	    mdBusiness.deleteAllTableRecords();

		createData(1990, 50000L, null);
		createData(1900, null, 0.027D);
		createData(1902, 100000L, null);
		createData(1905, null, 0.024D);
		createData(1908, null, 0.02D);
		createData(1910, 120000L, null);
		createData(1914, 130000L, null);
		createData(1917, 140000L, 0.01D);
		createData(1921, 150000L, null);
		createData(1924, null, 0.05D);
	}

	@Request
	public void testAnnualPopulations()
	{
		assertEquals(-1L,     PopulationData.calculateAnnualPopulation(GEOENTITY,1901));
		assertEquals(100000L, PopulationData.calculateAnnualPopulation(GEOENTITY,1902));
		assertEquals(102700L, PopulationData.calculateAnnualPopulation(GEOENTITY,1903));
		assertEquals(105473L, PopulationData.calculateAnnualPopulation(GEOENTITY,1904));
		assertEquals(108321L, PopulationData.calculateAnnualPopulation(GEOENTITY,1905));
		assertEquals(110921L, PopulationData.calculateAnnualPopulation(GEOENTITY,1906));
		assertEquals(113583L, PopulationData.calculateAnnualPopulation(GEOENTITY,1907));
		assertEquals(116309L, PopulationData.calculateAnnualPopulation(GEOENTITY,1908));
		assertEquals(118635L, PopulationData.calculateAnnualPopulation(GEOENTITY,1909));
		assertEquals(120000L, PopulationData.calculateAnnualPopulation(GEOENTITY,1910));
		assertEquals(122400L, PopulationData.calculateAnnualPopulation(GEOENTITY,1911));
		assertEquals(124848L, PopulationData.calculateAnnualPopulation(GEOENTITY,1912));
		assertEquals(127345L, PopulationData.calculateAnnualPopulation(GEOENTITY,1913));
		assertEquals(130000L, PopulationData.calculateAnnualPopulation(GEOENTITY,1914));
		assertEquals(132600L, PopulationData.calculateAnnualPopulation(GEOENTITY,1915));
		assertEquals(135252L, PopulationData.calculateAnnualPopulation(GEOENTITY,1916));
		assertEquals(140000L, PopulationData.calculateAnnualPopulation(GEOENTITY,1917));
		assertEquals(141400L, PopulationData.calculateAnnualPopulation(GEOENTITY,1918));
		assertEquals(142814L, PopulationData.calculateAnnualPopulation(GEOENTITY,1919));
		assertEquals(144242L, PopulationData.calculateAnnualPopulation(GEOENTITY,1920));
		assertEquals(150000L, PopulationData.calculateAnnualPopulation(GEOENTITY,1921));
		assertEquals(151500L, PopulationData.calculateAnnualPopulation(GEOENTITY,1922));
		assertEquals(153015L, PopulationData.calculateAnnualPopulation(GEOENTITY,1923));
		assertEquals(154545L, PopulationData.calculateAnnualPopulation(GEOENTITY,1924));
		assertEquals(162272L, PopulationData.calculateAnnualPopulation(GEOENTITY,1925));
		assertEquals(170386L, PopulationData.calculateAnnualPopulation(GEOENTITY,1926));
	}

	@Request
	public void testSeasonalPopulations() {
		assertEquals(117568L, PopulationData.calculateSeasonalPopulation(GEOENTITY, this.getCalendar(1908, 10, 1), this.getCalendar(1909, 4, 30)));
		assertEquals(116312L, PopulationData.calculateSeasonalPopulation(GEOENTITY, this.getCalendar(1908, 6, 1), this.getCalendar(1908, 8, 1)));
	}

	@Transaction
	private void createData(int year, Long population, Double growthRate) {
		PopulationData data = new PopulationData();
		data.setGeoEntity(GeoEntity.searchByGeoId(GEOENTITY));
		data.setYearOfData(year);
		if (population != null) {
			data.setPopulation(population);
		}

		if (growthRate != null) {
			data.setGrowthRate(growthRate);
		}
		data.apply();
	}

	private Calendar getCalendar(int year, int month, int day) {
		Calendar c = Calendar.getInstance();
		c.clear();
		c.set(year, month-1, day);
		return c;
	}
}
