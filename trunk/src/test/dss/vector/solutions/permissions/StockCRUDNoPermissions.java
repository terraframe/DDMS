package dss.vector.solutions.permissions;

import java.math.BigDecimal;
import java.util.Date;

import junit.framework.Test;

import com.terraframe.mojo.session.CreatePermissionExceptionDTO;

import dss.vector.solutions.MDSSRoleInfo;
import dss.vector.solutions.TestFixture;
import dss.vector.solutions.geo.generated.GeoEntityDTO;
import dss.vector.solutions.ontology.TermDTO;
import dss.vector.solutions.stock.EventOptionDTO;
import dss.vector.solutions.stock.StockEventViewDTO;
import dss.vector.solutions.stock.StockItemDTO;
import dss.vector.solutions.stock.StockItemViewDTO;
import dss.vector.solutions.stock.StockStaffDTO;

public class StockCRUDNoPermissions extends PermissionTest
{
  public static Test suite()
  {
    return TestFixture.getTestSuite(StockCRUDNoPermissions.class, MDSSRoleInfo.MANAGER);
  }

  public void testCreateStockItem()
  {
    TermDTO term = TermDTO.get(request, termId);

    try
    {
      StockItemViewDTO dto = new StockItemViewDTO(request);
      dto.setItemId(TestFixture.getRandomTermId());
      dto.setItemName(term);
      dto.setQuantity(55.5F);
      dto.setUnit(term);
      dto.apply();

      dto.deleteConcrete();

      fail("Able to create a stock item without permissions");
    }
    catch (CreatePermissionExceptionDTO e)
    {
      // This is expected
    }
  }

  public void testCreateStockEvent()
  {
    TermDTO term = TermDTO.get(systemRequest, termId);

    StockItemViewDTO item = new StockItemViewDTO(systemRequest);
    item.setItemId(TestFixture.getRandomTermId());
    item.setItemName(term);
    item.setQuantity(55.5F);
    item.setUnit(term);
    item.apply();

    try
    {
      StockStaffDTO staff = TestFixture.createTestStaff(systemRequest, term);

      try
      {
        StockEventViewDTO dto = new StockEventViewDTO(request);
        dto.addTransactionType(EventOptionDTO.STOCK_IN);
        dto.setCost(new BigDecimal(5.55));
        dto.setEventDate(new Date());
        dto.setItem(StockItemDTO.get(request, item.getConcreteId()));
        dto.setQuantity(5);
        dto.setStockDepot(GeoEntityDTO.searchByGeoId(request, siteGeoId));
        dto.setStaff(staff);
        dto.apply();
        
        dto.deleteConcrete();
        
        fail("Able to create a stock event without permissions");
      }
      catch(CreatePermissionExceptionDTO e)
      {
        //This is expected
      }
      finally
      {
        staff.getPerson().delete();
      }
    }
    finally
    {
      item.deleteConcrete();
    }
  }

}
