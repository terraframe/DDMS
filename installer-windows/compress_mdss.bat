rem -------------------------------------------------------------------------
rem Use this batch file to compress MDSS for distribution
rem -------------------------------------------------------------------------

net start postgresql-8.3
C:\MDSS\PostgreSQL\8.3\bin\vacuumdb.exe -U postgres --all --full --analyze

C:

net stop MDSSMozambique
net stop MDSSMalawi
net stop MDSSZambia

net stop postgresql-8.3
C:\MDSS\PostgreSQL\8.3\bin\pg_resetxlog.exe C:\MDSS\PostgreSQL\8.3\data

cd C:\MDSS
del C:\MDSS\mozambique\logs\* /Q
del C:\MDSS\malawi\logs\* /Q
del C:\MDSS\zambia\logs\* /Q

del *.DS_Store /S /Q /A:H
del geoserver.log* /s /Q

cd C:\

"C:\Program Files\7-Zip"\7z.exe a -t7z c:\MDSS.7z C:\MDSS -mx9

echo MDSS compressed to C:\MDSS.7z



