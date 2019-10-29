import smtplib
import os
import os.path
import sys

from email.mime.multipart import MIMEMultipart
from email.mime.base import MIMEBase
from email.mime.text import MIMEText
from email.utils import COMMASPACE, formatdate
from email import encoders

def sendEmail(subject, body, emailFrom, emailTo):
  msg = MIMEMultipart()
  msg['Subject'] = subject
  msg['From'] = emailFrom
  msg['To'] = ", ".join(emailTo)
  msg.attach(MIMEText(body))
  
  server = smtplib.SMTP("box737.bluehost.com")
  server.starttls()
  server.login(emailFrom, "_Terra4Frame")
  server.sendmail(emailFrom, emailTo, msg.as_string())
  server.quit()
  
sendEmail(sys.argv[1], sys.argv[2], "builder@terraframe.com", ["rrowlands@terraframe.com", "nmceachen@terraframe.com", "miguel.orlans@gmail.com", "chris@teravation.com"])