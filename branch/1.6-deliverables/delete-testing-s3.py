
import boto3

def deleteS3():
  s3 = boto3.resource('s3', aws_access_key_id="AKIAJX3T2RYVHDNKEPNQ", aws_secret_access_key="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")
  bucket = s3.Bucket("dss.vector.solutions.ddms")
  
  patcher = s3.Object('dss.vector.solutions.ddms', "testing/patcher.exe")
  patcher.delete()
  
  installer = s3.Object('dss.vector.solutions.ddms', "testing/installer.exe")
  installer.delete()
  
deleteS3()