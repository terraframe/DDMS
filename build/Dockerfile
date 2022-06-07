#FROM openjdk:8-jdk
FROM ubuntu:trusty

RUN apt update

# Test to make sure we can run Docker
RUN apt-get -y install libltdl7 bridge-utils libapparmor1 perl

# RUN apt-cache madison nsis
# nsis |     2.46-7 | http://archive.ubuntu.com/ubuntu/ trusty/universe amd64 Packages

RUN apt install -y wget unzip ant nsis=2.46-7 git python3 software-properties-common

# The version of Maven that comes with ubuntu:trusty is way too old so we need to do a hack here to upgrade it. (and finding an NSIS on a different OS is too hard)
# These commands pull in dependency 'software-properties-common' (referenced above) since it does 'apt-add-respository'
# Our maven must be above version 3.2.3 otherwise it doesn't default to https: https://maven.apache.org/docs/3.2.3/release-notes.html
# Use to test maven version: sudo docker run --rm --name ddms-build --entrypoint /bin/bash ddms-build -c "mvn --version"
RUN apt-get -y purge maven maven2
RUN apt-add-repository ppa:andrei-pozolotin/maven3
RUN apt-get -y update
RUN apt-get -y install maven3

## Install Java 8
RUN wget $(echo "aHR0cHM6Ly9kbC5jbG91ZHNtaXRoLmlvL3B1YmxpYy90ZXJyYWZyYW1lL3B1YmxpYy9yYXcvbmFtZXMvamRrLWRlYmlhbi1hbWQ2NC04dTIzMS92ZXJzaW9ucy84dTIzMS9qZGstZGViaWFuLWFtZDY0LTh1MjMxLnRhci5nego=" | base64 -d)
RUN mkdir /opt/java && tar xvzf jdk-debian-amd64-8u231.tar.gz -C /opt/java
ENV JAVA_HOME=/opt/java/jdk1.8.0_231
ENV JDK_HOME=/opt/java/jdk1.8.0_231
ENV JRE_HOME=/opt/java/jdk1.8.0_231/jre
RUN update-alternatives --install /usr/bin/java java /opt/java/jdk1.8.0_231/bin/java 20000
RUN update-alternatives --set java /opt/java/jdk1.8.0_231/bin/java
RUN update-alternatives --install /usr/bin/javac javac /opt/java/jdk1.8.0_231/bin/javac 20000
RUN update-alternatives --set javac /opt/java/jdk1.8.0_231/bin/javac
RUN java -version

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

  # Install Simple Service plugin SimpleSC
  RUN wget https://nsis.sourceforge.io/mediawiki/images/c/c9/NSIS_Simple_Service_Plugin_1.30.zip -O /tmp/simplesc.zip
  RUN unzip /tmp/simplesc.zip -d /tmp/simplesc-out
  RUN cp /tmp/simplesc-out/SimpleSC.dll /usr/share/nsis/Plugins/SimpleSC.dll

  # Install UserMgr
  RUN wget https://nsis.sourceforge.io/mediawiki/images/0/08/UserMgr.zip -O /tmp/usrmgr.zip
  RUN unzip /tmp/usrmgr.zip -d /tmp/usrmgr-out
  RUN cp /tmp/usrmgr-out/UserMgr.dll /usr/share/nsis/Plugins/UserMgr.dll

  # Install nsprocess
  RUN wget http://nsis.sourceforge.net/mediawiki/images/archive/1/18/20140806212030!NsProcess.zip -O /tmp/nsprocess.zip
  RUN unzip /tmp/nsprocess.zip -d /tmp/nsprocess-out
  RUN cp /tmp/nsprocess-out/Plugin/nsProcess.dll /usr/share/nsis/Plugins/nsProcess.dll
  RUN cp /tmp/nsprocess-out/Include/nsProcess.nsh /usr/share/nsis/Include/nsProcess.nsh

  # Move File Folder
  ADD build/thirdparty/nsis/move-file-folder/MoveFileFolder.nsh /usr/share/nsis/Include/MoveFileFolder.nsh

  RUN ls -al /usr/share/nsis/Plugins

## Add the ddms github repo ##
# These are split up heavily so that we can take better advantage of docker caching
#ADD .git /ddms/.git
ADD build/thirdparty /ddms/build/thirdparty
ADD tomcat-remote-listener /ddms/tomcat-remote-listener
ADD synch-manager /ddms/synch-manager
ADD runway-patcher /ddms/runway-patcher
ADD MDSSGISI /ddms/MDSSGISI
ADD dss.vector.solutions.kaleidoscope.oda.driver.ui /ddms/dss.vector.solutions.kaleidoscope.oda.driver.ui
ADD dss.vector.solutions.kaleidoscope.oda.driver /ddms/dss.vector.solutions.kaleidoscope.oda.driver
ADD dss.vector.org.json /ddms/dss.vector.org.json
ADD ddms-runway-patcher /ddms/ddms-runway-patcher
ADD ddms-initializer /ddms/ddms-initializer
ADD manager /ddms/manager
ADD backup-manager /ddms/backup-manager
ADD DDMS /ddms/DDMS
ADD build/ddms-revision-info.py /ddms/build/ddms-revision-info.py
ADD build/manager /ddms/build/manager
ADD build/installer /ddms/build/installer
ADD build/patcher /ddms/build/patcher
ADD build/build.xml /ddms/build/build.xml
ADD build/version.properties /ddms/build/version.properties

#RUN mkdir -p /ddms/build/thirdparty/swt/win32
#RUN wget https://search.maven.org/remotecontent?filepath=org/eclipse/swt/org.eclipse.swt.win32.win32.x86_64/4.3/org.eclipse.swt.win32.win32.x86_64-4.3.jar -O /ddms/build/thirdparty/swt/win32/org.eclipse.swt.win32.win32.x86_64-4.3.jar
# TODO : Download from web instead of our github repo

ENTRYPOINT ["ant", "-buildfile", "/ddms/build/build.xml", "build", "-Dproject.root=/ddms", "-Dartifact.out=/ddms-out"]
