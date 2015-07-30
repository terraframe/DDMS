import toolkit
import unittest

class Test1(unittest.TestCase):

  def test_main(self):
    toolkit.install("India")
    toolkit.restore("India")
    toolkit.patch()
    toolkit.startTomcat()
    toolkit.stopTomcat()
    toolkit.backup("India")
    toolkit.deleteGeneratedBackups()

if __name__ == '__main__':
    unittest.main()
    
