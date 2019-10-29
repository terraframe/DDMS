MDSS INSTALLER README


1.  PreRequsites
    You must uninstall any and all old versions of MDSS for this installer to work properly.  
	To uninstall:   
		Go to Start->MDSS->Stop MDSS
		Press WindowsKey + E to open windows explorer, navigate to C:\MDSS
		Run unstall.exe or uninstall.bat depending on which prior version of MDSS you have.
                A window will pop up asking "Do you want to uninstall postgres and all of its modules?", click "Yes"
                There will be a warning that the data directory has not been removed, this is normal, click "OK"
                After the uninstaller finishes, click the close button.
                The uninstaller may ask you to restart, choose "Restart Later" and manually restart you computer after the uninstall is complete.

    You MUST have a UNIQUE install number to provide the installer.   It is very important that this number be unique for your country or you will not be able to sync your data.     


    Please temporally disable all anti-virus or firewall software. Certain firewall software can prevent MDSS from starting or Prevent communication with the database during the install.     

    Please close FireFox.

    You must have administrative permissions on your machine. 

    The minimum specs for the computer are:
       2GB  of ram
       2GHz or better processor
       10gb of free disk space.
       Windows XP or newer


2.  Installing  
    Click on Start->All Programs ,  Look for an "MDSS"  folder, if you see an MDSS folder, open it and run "Uninstall MDSS", see above for detailed uninstall instructions.
    For regular installs: Open the CD-ROM in in windows explorer and double click on InstallMDSS
    For master installs: WindowsKey+R -> type "D:\InstallMDDS.exe -master" where "D" is the drive letter of your cd-rom.
    Windows Vista or later may ask you if you want to run this program, click "Yes" or "Allow".
    The installer will first verify the integrity of the CD, if this step fails, it means your CD has been damaged. Obtain a new CD.
    The installer will ask you to choose a install number, it is VITAL that your install number be different from all other installs for your country. Master installs do not show this page, as master installs always use 0 for install number.
    The installer will ask you to choose a start menu folder, we recommend you leave it set to "MDSS"
    The installer will now run through,  You may see some black screens pop up with white text in them. This is normal.
    Your firewall may ask if you want to allow or deny network access for "Postgres" or "JAVA", click allow.  You must do this quickly after the prompt comes up or the firewall will prevent proper installation. If you see an error such as "Could not Connect to Postgres" The firewall is the likely source. Add Postgres or port 5444 to your exceptions list, and start the install over. 
    
3. Post Install
   After the install is completed click on Start->All Programs->MDSS->Start MDSS
   The MDSS server will start in the background,  it takes a few minutes to start up.
   After a minute or two you can go to Start->All Programs->MDSS-> Open MDSS
   Firefox may alert you that the Screengrab plugin has been installed, this is normal.
   The default login is MDSS and the password is mdsstest2. 
   After you log in for the first time you will need to make yourself a user with admin rights.
   After you have created a user for yourself, log out and log back in as your user.
   If you have received data from the master server, run the data sync now. 


