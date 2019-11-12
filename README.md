# DDMS

The DDMS is an entomology and vector-borne disease data collection, management, and reporting platform. The build system produces installer and patcher Windows executeables.

This DDMS repository is divided into many different sub-projects. The DDMS project is the core DDMS webapp, the rest are supporting projects.

## End-User Documentation

The latest user manual can be found here:

https://github.com/terraframe/DDMS/blob/master/documents/manuals/The%20DDMS%20User%20Manual%201.3%20rev%204.02.pdf

It does not include any documentation about the Kaleidoscope, which is our recommended way to do mapping and reporting. That, and also documentation on using the DHIS2 import/export functionality can be found here:

https://raw.githubusercontent.com/terraframe/DDMS/master/documents/manuals/DDMS%20106.pdf

Training documentation on the 1.5 features can be found here:

https://raw.githubusercontent.com/terraframe/DDMS/master/documents/manuals/DDMS%201.05.docx

which includes information about Form generation, querybuilders, IRS / Entomology, and server administration.

If you are interested in [setting up HTTPS / SSL we have a good tutorial for that on our wiki.](https://github.com/terraframe/DDMS/wiki/HTTPS-Setup-with-Lets-Encrypt)

## System Requirements

System Requirements:
- 64-bit Windows 7+
- 4+ GB RAM

## Releases

The latest releases can be found on our [Github Releases page](https://github.com/terraframe/DDMS/releases). These Windows installers manage all required software automatically for you. They will install and manage:
- Postgres + PostGIS
- Tomcat
- Firefox
- And more

They also include a manager Java application which can be accessed and run from `C:\MDSS\manager\manager.bat`. You may use this manager app to run and configure the DDMS.

## Developer Documentation

The latest developer documentation can be found on our Github Wiki. A great starting place is [Setting Up a Development Environment](https://github.com/terraframe/DDMS/wiki/Setting-Up-A-Development-Environment).

[The DDMS is powered by Runway SDK.](http://terraframe.github.io/Runway-SDK/) Our Runway documentation includes information about the metadata-driven backend which powers the DDMS.
