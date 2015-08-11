import toolkit
from selenium import webdriver
from selenium.webdriver.common.by import By
from selenium.webdriver.common.keys import Keys
from selenium.webdriver.support.ui import Select
from selenium.webdriver.support.ui import WebDriverWait
from selenium.webdriver.support import expected_conditions as EC
from selenium.common.exceptions import NoSuchElementException
from selenium.common.exceptions import NoAlertPresentException
import unittest, time, re

class India(unittest.TestCase):

  @classmethod
  def setUpClass(self):
    toolkit.install("India")
    toolkit.restore("India")
    toolkit.patch()
    toolkit.startTomcat()
    
    self.driver = webdriver.Firefox()
    self.driver.implicitly_wait(30)
    self.base_url = "http://localhost:8080/India"
    self.accept_next_alert = True
  
    driver = self.driver
    driver.get(self.base_url)
    driver.find_element_by_name("username").clear()
    driver.find_element_by_name("username").send_keys("ddms")
    driver.find_element_by_name("password").clear()
    driver.find_element_by_name("password").send_keys("ddms")
    driver.find_element_by_id("submitLogin").click()
    WebDriverWait(self.driver, 10).until(EC.presence_of_element_located((By.ID, "mainNav")))

  def test_basicGeoserver(self):
    driver = self.driver
    driver.get(self.base_url + "/dss.vector.solutions.query.MappingController.generateMaps.mojo")
    self.waitComServer()
    driver.find_element_by_id("addLayerBtn").click()
    self.waitComServer()
    driver.find_element_by_name("layer.layerName").clear()
    driver.find_element_by_name("layer.layerName").send_keys("layer123")
    Select(driver.find_element_by_id("savedSearchList")).select_by_visible_text("District")
    driver.find_element_by_name("dss.vector.solutions.query.Layer.form.saveLayer.button").click()
    self.waitComServer()
    driver.find_element_by_id("refreshMap").click()
    self.waitComServer()
    WebDriverWait(driver, 10).until(EC.presence_of_element_located((By.CLASS_NAME, "olLayerDiv")))

  @classmethod
  def tearDownClass(self):
    self.driver.get(self.base_url + "/com.runwaysdk.defaults.LoginController.logout.mojo")
    self.driver.quit()
    
    toolkit.manualTesting()
    
    toolkit.stopTomcat()
    toolkit.backup("India")
    toolkit.deleteGeneratedBackups()
    toolkit.uninstall()
    
  def waitComServer(self):
    WebDriverWait(self.driver, 20).until(EC.visibility_of_element_located((By.ID, "wait_for_ajax_c")))
    WebDriverWait(self.driver, 20).until(EC.invisibility_of_element_located((By.ID, "wait_for_ajax_c")))
    
if __name__ == '__main__':
  unittest.main()

