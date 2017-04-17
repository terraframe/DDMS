package dss.vector.solutions;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import dss.vector.solutions.installer.InstallerTest;
import dss.vector.solutions.selenium.SeleniumTestSuite;

@RunWith(Suite.class)
@SuiteClasses( {SeleniumTestSuite.class, InstallerTest.class})
public class UeberTestSuite
{
}