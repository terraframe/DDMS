FROM openjdk:8-jdk

## Install Basic Linux Packages ##
RUN apt update && apt install -y wget unzip ant nsis=2.51-1+b1 git python3 maven

## Install NSIS Plugins ##
  # Install LogEx NSIS plugin
  RUN wget https://nsis.sourceforge.io/mediawiki/images/d/d1/LogEx.zip -O /tmp/LogEx.zip
  RUN unzip /tmp/LogEx.zip -d /tmp/logex-out
  RUN cp /tmp/logex-out/Plugins/LogEx.dll /usr/share/nsis/Plugins/LogEx.dll

  # Install ExecDos NSIS plugin
  RUN wget https://nsis.sourceforge.io/mediawiki/images/0/0f/ExecDos.zip -O /tmp/ExecDos.zip
  RUN unzip /tmp/ExecDos.zip -d /tmp/execdos-out
  RUN cp /tmp/execdos-out/Plugins/x64-ansi/ExecDos.dll /usr/share/nsis/Plugins/ExecDos64.dll
  RUN cp /tmp/execdos-out/Plugins/x86-ansi/ExecDos.dll /usr/share/nsis/Plugins/ExecDos.dll

  # Install NSIS OS for detecting Windows version
  RUN wget https://nsis.sourceforge.io/mediawiki/images/2/28/Nsisos.zip -O /tmp/Nsisos.zip
  RUN unzip /tmp/Nsisos.zip -d /tmp/Nsisos-out
  RUN cp /tmp/Nsisos-out/nsisos/nsisos.dll /usr/share/nsis/Plugins/nsisos.dll

  # Install SimpleFC plugin for managing firewalls
  RUN wget https://nsis.sourceforge.io/mediawiki/images/d/d7/NSIS_Simple_Firewall_Plugin_1.20.zip -O /tmp/simplefc.zip
  RUN unzip /tmp/simplefc.zip -d /tmp/simplefc-out
  RUN cp /tmp/simplefc-out/SimpleFC.dll /usr/share/nsis/Plugins/SimpleFC.dll

  RUN ls -al /usr/share/nsis/Plugins

## STAGE THIRD PARTY ##
ADD . /ddms
RUN unzip /ddms/build/thirdparty/tomcat/webapps/ODKAggregate.war -d /ddms/build/thirdparty/ODKAggregate

RUN mkdir -p /ddms/build/thirdparty/swt/win32
RUN wget https://search.maven.org/remotecontent?filepath=org/eclipse/swt/org.eclipse.swt.win32.win32.x86_64/4.3/org.eclipse.swt.win32.win32.x86_64-4.3.jar -O /ddms/build/thirdparty/swt/win32/org.eclipse.swt.win32.win32.x86_64-4.3.jar
# TODO : Download from web instead of our github repo

ENTRYPOINT ["ant", "-buildfile", "/ddms/build/build.xml", "build", "-Dproject.root=/ddms"]
#ENTRYPOINT ["makensis", "-V4"]
#ENTRYPOINT ["makensis", "-V4", "/ddms/build/installer/Installer.nsi"]
