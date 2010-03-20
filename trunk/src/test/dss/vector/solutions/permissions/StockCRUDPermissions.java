package dss.vector.solutions.permissions;

import java.math.BigDecimal;
import java.util.Date;

import junit.framework.Test;

import com.runwaysdk.DoNotWeave;
import com.runwaysdk.ProblemExceptionDTO;
import com.runwaysdk.business.ProblemDTOIF;

import dss.vector.solutions.MDSSRoleInfo;
import dss.vector.solutions.TestFixture;
import dss.vector.solutions.geo.generated.GeoEntityDTO;
import dss.vector.solutions.ontology.TermDTO;
import dss.vector.solutions.stock.EventOptionDTO;
import dss.vector.solutions.stock.StockEventDTO;
import dss.vector.solutions.stock.StockEventViewDTO;
import dss.vector.solutions.stock.StockItemDTO;
import dss.vector.solutions.stock.StockItemViewDTO;
import dss.vector.solutions.stock.StockStaffDTO;

public class StockCRUDPermissions extends PermissionTest implements DoNotWeave
{
  public static Test suite()
  {
    return TestFixture.getTestSuite(StockCRUDPermissions.class, MDSSRoleInfo.MDSS_CORRDINATOR, MDSSRoleInfo.ENTOMOLOGIST, MDSSRoleInfo.OPERATIONAL_MANAGER, MDSSRoleInfo.DATACAPTURER, MDSSRoleInfo.STOCK_STAFF);
  }

  public void testCreateStockItem()
  {
    TermDTO term = TermDTO.get(request, termId);

    StockItemViewDTO dto = new StockItemViewDTO(request);
    dto.setItemId(TestFixture.getRandomTermId());
    dto.setItemName(term);
    dto.setQuantity(55.5F);
    dto.setUnit(term);
    dto.apply();

    try
    {
      StockItemViewDTO edit = StockItemDTO.lockView(request, dto.getConcreteId());
      edit.setQuantity(55.5F);
      edit.apply();

      StockItemViewDTO test = StockItemDTO.getView(request, dto.getConcreteId());

      assertEquals(edit.getItemId(), test.getItemId());
      assertEquals(edit.getItemName().getId(), test.getItemName().getId());
      assertEquals(edit.getQuantity(), test.getQuantity());
      assertEquals(edit.getUnit().getId(), test.getUnit().getId());
    }
    finally
    {
      dto.deleteConcrete();
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
        dto.setStockDepot(GeoEntityDTO.searchByGeoId(request, depotGeoId));
        dto.setStaff(staff);
        dto.apply();
        
        try
        {
          StockEventViewDTO edit = StockEventDTO.lockView(request, dto.getConcreteId());
          edit.clearTransactionType();
          edit.addTransactionType(EventOptionDTO.STOCK_OUT);
          edit.setCost(new BigDecimal(7.55));
          edit.setQuantity(1);
          edit.apply();

          StockEventViewDTO test = StockEventDTO.getView(request, dto.getConcreteId());
          
          assertEquals(edit.getItem().getId(), test.getItem().getId());
          assertTrue(edit.getTransactionType().containsAll(test.getTransactionType()) && test.getTransactionType().containsAll(edit.getTransactionType()));
          assertEquals(edit.getCost(), test.getCost());
          assertEquals(edit.getEventDate(), test.getEventDate());
          assertEquals(edit.getQuantity(), test.getQuantity());
          assertEquals(edit.getStockDepot().getId(), test.getStockDepot().getId());
          assertEquals(edit.getStaff().getId(), test.getStaff().getId());
        }
        finally
        {
          dto.deleteConcrete();
        }        
      }
      catch(ProblemExceptionDTO e)
      {
        for(ProblemDTOIF p : e.getProblems())
        {
          fail(p.getMessage());
        }
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
